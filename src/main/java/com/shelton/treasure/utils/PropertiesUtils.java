package com.shelton.treasure.utils;

import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;

/**
 * @ClassName PropertiesUtils
 * @Description TODO
 * @Author xiaosheng1.li
 **/
public class PropertiesUtils {
    private static Environment environmentProperties = new StandardEnvironment();

    public PropertiesUtils(Environment environment){
        environmentProperties = environment;
    }

    public static String getProperty(String key){
        return environmentProperties.getProperty(key);
    }
    public static String getProperty(String key, String defaultValue){
        String value = environmentProperties.getProperty(key);
        return value == null ? defaultValue : value;
    }
}
