package com.fdi17.common.domain;

import java.util.HashMap;
import java.util.Map;

public class ApiDoc {
    public String description;
    public String controllerDescription;
    public String controllerClass;
    public String methodName;
    public Map<String, Object> params = new HashMap<>();
    public String interfaceType;
}
