package com.fdi17.common.domain;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import springfox.documentation.schema.Model;
import springfox.documentation.schema.ModelProperty;
import springfox.documentation.service.*;
import springfox.documentation.spring.web.DocumentationCache;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ScanInterface {
    final DocumentationCache documentationCache;
    final WebApplicationContext applicationContext;

    @Autowired
    RedisTemplate redisTemplate;

    public ScanInterface(DocumentationCache documentationCache, WebApplicationContext applicationContext) {
        this.documentationCache = documentationCache;
        this.applicationContext = applicationContext;
        System.out.println("=======init easy swagger======");
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (documentationCache.all().size() > 0) {
                        ScanInterface.this.run();
                        break;
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void run() {
        //read from local
//        checkSrc();
        docInfo = new DocInfo();
        scanAllUrl();
        Map<String, Documentation> map = documentationCache.all();
        for (Documentation doc : map.values()) {
            hookSwaggerDoc(doc);
        }
        saveDocInfo();
        System.out.println("===== ShareInterface finished =====");
    }

    private void readDocInfoFromJson() {
        try {
            docInfo = new ObjectMapper().readValue(new ClassPathResource(docFilePath).getInputStream(), DocInfo.class);
            System.out.println("readDocInfoFromJson...");
        } catch (IOException e) {
            System.out.println("failed readDocInfoFromJson..."+e.getMessage());
        }
    }

    private String docFilePath = "swagger/doc-info.json";

    private void saveDocInfo() {
        try {
            String docInfoJson = new ObjectMapper().writeValueAsString(docInfo);
            System.out.println("本模块下的共享接口"+docInfoJson);
            System.out.println("==================================");
            Object doc = redisTemplate.opsForValue().get("doc");
            //DocInfo docInfo = new DocInfo();
            if(Objects.isNull(doc)){
                redisTemplate.opsForValue().set("doc", docInfoJson);
            }else{
                String docJson = String.valueOf(doc);
                DocInfo redisDoc = JSON.parseObject(docJson, DocInfo.class);
                System.out.println("已有的接口"+docJson);
                String jsonString = JSON.toJSONString(this.mergerDocInfo(this.docInfo, redisDoc));
                redisTemplate.opsForValue().set("doc",jsonString);
            }
            //合并的
            System.out.println("=========合并后的接口==============");
         System.out.println(redisTemplate.opsForValue().get("doc"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public DocInfo mergerDocInfo(DocInfo docInfo1,DocInfo redisDoc){
//        Map<String, ApiDoc> apiMap = redisDoc.apiMap;
//        Map<String, ModelDoc> modelMap = redisDoc.modelMap;
//        docInfo1.apiMap.putAll(apiMap);
//        docInfo1.modelMap.putAll(modelMap);
//        return docInfo1;
        redisDoc.apiMap.putAll(docInfo1.apiMap);
        redisDoc.modelMap.putAll(docInfo1.modelMap);
        return redisDoc;
    }




    private DocInfo docInfo;
    private String projectDir;
    private Set<String> controllerClasses = new HashSet<>();
    private boolean hasSrc;//是否有源码
    private File srcFile;

    /**
     * 获取所有url
     */
    private void scanAllUrl() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        for (RequestMappingInfo req : map.keySet()) {
            HandlerMethod hm = map.get(req);
            String url = req.getPatternsCondition().getPatterns().iterator().next();
            //修改url路径
            if(url.startsWith("/job") || url.startsWith("/detail")){
                url = "/task-service"+url;
            }
            if(url.startsWith("/table/editor") || url.startsWith("/CustomInterface") || url.startsWith("/colHeader") || url.startsWith("/paramMapping")|| url.startsWith("/data") || url.startsWith("/checkRules")){
                url = "/fdi17-page-service"+url;
            }
            if(url.startsWith("/system")){
                url ="/fdi17-system-service"+url;
            }
            String className = hm.getBeanType().getName();
            if(hm.getMethod().isAnnotationPresent(ShareInterface.class)){
                ApiDoc apiDoc = new ApiDoc();
                apiDoc.controllerClass = className;
                controllerClasses.add(className);
                apiDoc.methodName = hm.getMethod().getName();
                try {
					apiDoc.description = new String(hm.getMethod().getAnnotation(ShareInterface.class).description().getBytes(), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
                Map<String,Object> ParamList = new HashMap<>();
                ParamList = Utils.processRequestParam(className, apiDoc.methodName);
                apiDoc.params = (Map<String, Object>) ParamList.get("parameters");
                apiDoc.interfaceType = ParamList.get("interfaceType").toString();
                docInfo.apiMap.put(url, apiDoc);
            }
        }
    }


    private void checkSrc() {
        projectDir = System.getProperty("user.dir");
        File parent = new File(projectDir);
        //遍历二级目录（多模块）
        for (File file : Objects.requireNonNull(parent.listFiles())) {
            if (file.isDirectory()) {
                if (file.getName().equals("src")) {
                    hasSrc = true;
                    return;
                }
                for (File child : Objects.requireNonNull(file.listFiles(fileFilter))) {
                    if (child.isDirectory() && child.getName().equals("src")) {
                        hasSrc = true;
                        return;
                    }
                }
            }
        }
    }

    /**
     * 扫描controller文档
     */
    private void scanControllerDoc() {
        for (String cls : controllerClasses) {
            File sourceFile = getSourceFile(projectDir, cls);
            if (sourceFile != null) {
                parseControllerDoc(sourceFile, cls);
            }
        }
    }

    private static final String DOC_START = "^\\s*(/\\*\\*)$";
    private static final String DOC_END = "^\\s*\\*/$";
    private static final String DOC_CLASS = "^.* *class .+$";
    private static final String DOC_METHOD_KT = "^\\s*fun (\\S*)\\(.*$";
    private static final String DOC_METHOD_JAVA = "^\\s*public \\S+ (\\S*)\\(.*$";
    private static final String DOC_PARAMS = "^\\s*\\* @param\\s+(.*)$";
    private static final String DOC_FIELD_JAVA = "^\\s*(public|private) \\S+ (\\S+);\\s*//(\\S+)$";
    private static final String DOC_FIELD_KT = "^\\s*(var|val) (\\S+):.*//(\\S+)$";

    boolean hasDoc = true;
    int docIndex = -1;

    private void parseControllerDoc(File sourceFile, String controllerClass) {
        BufferedReader reader = null;
        boolean isJava = sourceFile.getName().endsWith(".java");
        try {
            reader = new BufferedReader(new FileReader(sourceFile));
            String line;

            String docDescription = "";
            Map<String, String> params = new HashMap<>();
            String controllerDescription = "";
            while ((line = reader.readLine()) != null) {
                if (line.matches(DOC_START)) {
                    docIndex = 0;
                    params = new HashMap<>();
                    hasDoc = true;
                }
                if (line.matches(DOC_END)) {
                    docIndex = -2;
                }
                if (docIndex == 1) {
                    docDescription = trimDocDescription(line);
                }
                if (line.matches(DOC_CLASS)) {
                    docIndex = -1;
                    if (hasDoc) {
                        controllerDescription = docDescription;
                        hasDoc = false;
                    }
                }
                if (line.matches(DOC_PARAMS)) {
                    String namedParam = getRexValue(DOC_PARAMS, line);
                    if (namedParam != null) {
                        String[] array = namedParam.split("\\s+");
                        if (array.length >= 2) {
                            params.put(array[0], substringArray(1, array));
                        }
                    }
                }
                scanControllerMethodDoc(
                        isJava,
                        controllerClass,
                        controllerDescription,
                        line, docDescription, params
                );
                if (docIndex != -2) docIndex++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 扫描方法注释
     */
    private void scanControllerMethodDoc(boolean isJava, String controllerName, String controllerDescription,
                                         String line, String docDescription, Map<String, String> params) {
        String regex = isJava ? DOC_METHOD_JAVA : DOC_METHOD_KT;
        if (line.matches(regex)) {
            String methodName = getRexValue(regex, line);
            methodName = fixMethodName(methodName);
            ApiDoc apiDoc = getApiDocBy(controllerName, methodName);
            if (apiDoc != null) {
                apiDoc.description = docDescription;
                Map<String,Object> ParamList = new HashMap<>();
                ParamList = Utils.processRequestParam(controllerName, methodName);
                apiDoc.params = (Map<String, Object>) ParamList.get("parameters");
                apiDoc.interfaceType = ParamList.get("interfaceType").toString();
//                apiDoc.controllerDescription = controllerDescription;
            }
            if (hasDoc) hasDoc = false;
        }
    }

    private static String fixMethodName(String methodName) {
        int index = methodName.indexOf("(");
        if (index != -1) return methodName.substring(0, index);
        return methodName;
    }


    private ApiDoc getApiDocBy(String controllerCls, String methodName) {
        for (ApiDoc ad : docInfo.apiMap.values()) {
            if (ad.controllerClass.equals(controllerCls) && ad.methodName.equals(methodName)) {
                return ad;
            }
        }
        return null;
    }

    private String trimDocDescription(String line) {
        int index = line.indexOf("*");
        if (index != -1) return line.substring(index + 1);
        return "";
    }

    private String substringArray(int start, String[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < array.length; i++) {
            sb.append(array[i]).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private String getRexValue(String pattern, String text) {
        Matcher matcher = Pattern.compile(pattern).matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

    private File getSourceFile(String parent, String cls) {
        File src = new File(parent, "src");
        if (src.exists() && src.isDirectory()) {
            srcFile = src;
            String ktPath = "main/kotlin/" + getClassPath(cls);
            File sourceFile = new File(src, ktPath + ".kt");
            if (sourceFile.exists()) return sourceFile;
            sourceFile = new File(src, ktPath + ".java");
            if (sourceFile.exists()) return sourceFile;
            String javaPath = "main/java/" + getClassPath(cls);
            sourceFile = new File(src, javaPath + ".kt");
            if (sourceFile.exists()) return sourceFile;
            sourceFile = new File(src, javaPath + ".java");
            if (sourceFile.exists()) return sourceFile;
        } else {//从子目录目录中寻找
            return getSourceFileInChild(parent, cls);
        }
        return null;
    }


    private File getSourceFileInChild(String parent, String cls) {
        File file = new File(parent);
        if (file.isDirectory()) {
            for (File dir : Objects.requireNonNull(file.listFiles(fileFilter))) {
                File sourceFile = getSourceFile(dir.getAbsolutePath(), cls);
                if (sourceFile != null) return sourceFile;
            }
        }
        return null;
    }

    private FileFilter fileFilter = pathname -> pathname.isDirectory() && !pathname.getName().startsWith(".");


    private String getClassPath(String cls) {
        return cls.replace(".", "/");
    }

    private void hookSwaggerDoc(Documentation doc) {
        Map<String, List<ApiListing>> apiListMap = doc.getApiListings();
        for(List<ApiListing>apiList:apiListMap.values()){
            for (ApiListing apiListing : apiList) {
//                for (Model model : apiListing.getModels().values()) {
//                    scanModelAndReplace(model);
//                }
                for (ApiDescription apiDescription : apiListing.getApis()) {
                    String path = apiDescription.getPath();
                    ApiDoc apiDoc = docInfo.apiMap.get(path);
                    if (apiDoc != null) {
                        apiDoc.controllerClass = null;
                        apiDoc.methodName = null;
                        apiDoc.controllerDescription = null;
                        replaceParameter(apiDescription, apiDoc);
                        setOperationTags(apiDescription, apiDoc.controllerDescription);
//                        setTags(apiListing.getTags(), apiDoc.controllerDescription, apiDoc.controllerClass);
                    }

                }
            }
        }
    }

    private void setOperationTags(ApiDescription apiDescription, String controllerDescription) {
        if (StringUtils.isEmpty(controllerDescription)) return;
        Set<String> tags = new HashSet<>();
        tags.add(controllerDescription);
        for (Operation opt : apiDescription.getOperations()) {
            setField(opt, "tags", tags);
        }

    }


    /**
     * 替换实体文档
     *
     * @param model
     */
    private void scanModelAndReplace(Model model) {
        String cls = model.getType().getErasedType().getName();
        ModelDoc modelDoc = docInfo.modelMap.get(cls);
        if (modelDoc != null) {
            hookModelSwaggerDoc(model, modelDoc);
            return;
        }
        File sourceFile = getSourceFile(projectDir, cls);
        if (sourceFile == null) {
            return;
        }
        modelDoc = new ModelDoc();
        Map<String, String> fieldMap = new HashMap<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(sourceFile));
            String line;
            String docDescription = "";
            int docIndex = -1;
            while ((line = reader.readLine()) != null) {
                if (line.matches(DOC_START)) {
                    docIndex = 0;
                }
                if (docIndex == 1) {
                    docDescription = trimDocDescription(line);
                }
                setFileMap(fieldMap, line, DOC_FIELD_JAVA);
                setFileMap(fieldMap, line, DOC_FIELD_KT);
                docIndex++;
            }
            modelDoc.description = docDescription;
            modelDoc.fieldMap = fieldMap;
//            docInfo.modelMap.put(cls, modelDoc);
//            hookModelSwaggerDoc(model, modelDoc);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void hookModelSwaggerDoc(Model model, ModelDoc modelDoc) {
        setField(model, "description", modelDoc.description);
        Map<String, String> fieldMap = modelDoc.fieldMap;
        for (String key : fieldMap.keySet()) {
            ModelProperty property = model.getProperties().get(key);
            if (property != null) {
                setField(property, "description", fieldMap.get(key));
            }
        }
    }

    private void setFileMap(Map<String, String> fieldMap, String line, String rex) {
        Matcher matcher = Pattern.compile(rex).matcher(line);
        if (matcher.find()) {
            fieldMap.put(matcher.group(2), matcher.group(3));
        }
    }

    private void setTags(Set<Tag> tags, String description, String controllerCls) {
        if (StringUtils.isEmpty(description)) return;
        String simpleClassName = getSimpleClassName(controllerCls);
        for (Tag tag : tags) {
            setField(tag, "name", description);
            setField(tag, "description", simpleClassName);
        }
    }

    private String getSimpleClassName(String controllerCls) {
        try {
            Class cls = Class.forName(controllerCls);
            return cls.getSimpleName();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void replaceParameter(ApiDescription apiDescription, ApiDoc apiDoc) {
        if (apiDoc.params == null) return;
        for (Operation opt : apiDescription.getOperations()) {
            setField(opt, "summary", apiDoc.description);
            for (RequestParameter parameter : opt.getQueryParameters()) {
                String pn = parameter.getName();
                String desc = JSON.toJSONString(apiDoc.params.get(pn));
                if (!desc.isEmpty()) {
                    setParameter(parameter, desc);
                }
            }
        }
    }

    private void setParameter(RequestParameter parameter, String desc) {
        String[] array = desc.split("\\|");
        if (array.length > 0) {
            setField(parameter, "description", array[0]);
        }
        if (array.length > 1) {
            if ("required".equals(array[1])) {
                setField(parameter, "required", true);
            } else {
                setField(parameter, "defaultValue", array[1]);
            }
        }
        if (array.length > 2) {
            setField(parameter, "defaultValue", array[2]);
        }
    }

    private void setField(Object obj, String name, Object value) {
        try {
            Field field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
