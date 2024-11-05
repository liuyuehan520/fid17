package com.fdi17.common.domain;

import com.fdi17.common.domain.ShareInterface;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Utils {

    /**
     * 处理RequestParam注解
     */
    static Map<String,Object> processRequestParam(String controllerCls, String methodName) {
        try {
            Class cls = Class.forName(controllerCls);
            Method[] methods = cls.getMethods();
            Map<String,Object> result = new HashMap<String, Object>();
            for (Method m : methods) {
                if (methodName.equals(m.getName())) {
                    DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
                    if(m.isAnnotationPresent(GetMapping.class)){
                        if (m.getParameterAnnotations().length == 0 || m.getParameterAnnotations()[0].length == 0 ){
                            result.put("interfaceType","GET");
                            Map<String,Object> paramInfo = new HashMap<String, Object>();
                            for (int num = 0;num < m.getParameters().length;num++) {
                                Map<String,String> data = new HashMap<>();
                                data.put("parameterType",m.getParameters()[num].getType().getSimpleName());
                                if(null!=m.getParameters()[num].getAnnotation(ShareInterface.class))
                                    data.put("parameterDesc",m.getParameters()[num].getAnnotation(ShareInterface.class).description());
                                else
                                    data.put("parameterDesc", Objects.requireNonNull(discoverer.getParameterNames(m))[num]);
                                paramInfo.put(discoverer.getParameterNames(m)[num], data);
                            }
                            result.put("parameters", paramInfo);
                            return result;
                        }
                    }
                    Annotation[][] an = m.getParameterAnnotations();
                    Map<String,Object> requestParamMap = new HashMap<String, Object>();
                    for (int i = 0; i < an.length; i++) {
                        if (an[i].length == 0) continue;
                        for (Annotation annotations : an[i]) {
                            if (annotations instanceof RequestParam){
                                RequestParam requestParam = (RequestParam) annotations;
                                result.put("interfaceType","RequestParam");
                                Map<String,String> data = new HashMap<>();
                                data.put("parameterType",m.getParameters()[i].getType().getSimpleName());
                                if(null!=m.getParameters()[i].getAnnotation(ShareInterface.class))
                                    data.put("parameterDesc",m.getParameters()[i].getAnnotation(ShareInterface.class).description());
                                else
                                    data.put("parameterDesc", Objects.requireNonNull(discoverer.getParameterNames(m))[i]);
                                requestParamMap.put(requestParam.value(), data);
                            }
                            if (annotations instanceof RequestBody){
                                result.put("interfaceType","RequestBody");
                                Map<String,Object> paramInfo = new HashMap<String, Object>();
                                for (int num = 0;num < m.getParameters().length;num++) {
                                    Map<String,String> data = new HashMap<>();
                                    data.put("parameterType",m.getParameters()[num].getType().getSimpleName());
                                    if(null!=m.getParameters()[num].getAnnotation(ShareInterface.class))
                                        data.put("parameterDesc",m.getParameters()[num].getAnnotation(ShareInterface.class).description());
                                    else
                                        data.put("parameterDesc", Objects.requireNonNull(discoverer.getParameterNames(m))[num]);
                                    paramInfo.put(discoverer.getParameterNames(m)[num], data);                                }
                                result.put("parameters", paramInfo);
                                return result;
                            }
                            if (annotations instanceof PathVariable){
                                result.put("interfaceType","REST");
                                Map<String,Object> paramInfo = new HashMap<>();
                                for (Parameter param : m.getParameters()) {
                                    Map<String,String> data = new HashMap<>();
                                    data.put("parameterType",param.getType().getSimpleName());
                                    if(null!=param.getAnnotation(ShareInterface.class))
                                        data.put("parameterDesc",param.getAnnotation(ShareInterface.class).description());
                                    else
                                        data.put("parameterDesc", Objects.requireNonNull(((PathVariable) annotations).value()));
                                    paramInfo.put(((PathVariable) annotations).value(), data);
                                }
                                result.put("parameters", paramInfo);
                                return result;
                            }
                        }
                    }
                    result.put("parameters", requestParamMap);
                    return result;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static RequestParam findRequestParam(Annotation[] annotations) {
        for (Annotation an : annotations) {
            if (an instanceof RequestParam) return (RequestParam) an;
        }
        return null;
    }
}
