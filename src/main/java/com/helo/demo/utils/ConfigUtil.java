package com.helo.demo.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @Classname ConfigUtil
 * @Description TODO
 * @Date 2019/12/4 9:07 下午
 * @Created by wangxianlin
 */
public class ConfigUtil {
    //初始化配置文件
    private static Properties pro = null;
    static {
        Resource resource = new ClassPathResource("config.properties");
        try {
            pro = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取配置文件的中配置的值
    public static String getValue(String key){
        return pro.getProperty(key);
    }

    //根据类名获取类对象
    public static Object getInstance(String className){
        Object obj = null;
        try {
            obj = Class.forName(getValue(className)).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
