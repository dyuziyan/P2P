package com.wx.carloadmember.enums;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 *  读取属性文件
 * @author 13697
 *
 */
public class PropertyLoaderSupport
{

    private static Map<String, Properties> propertiesMap = new HashMap<String, Properties>();

    /**
     * 读取属性文件
     * @param fileName  文件名
     * @param key 
     * @return
     */
    public static String read(String fileName, String key)
    {

        if (!propertiesMap.containsKey(fileName) || propertiesMap.get(fileName) == null)
        {
            loadProperty(fileName);
        }

        Properties properties = propertiesMap.get(fileName);

        return properties.getProperty(key);
    }


    /**
     * 加载属性文件
     * @param fileName
     */
    private static void loadProperty(String fileName)
    {
        Properties properties = new Properties();

        try
        {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource resource = resolver.getResource(fileName);
            InputStream in = new BufferedInputStream(resource.getInputStream());
            properties.load(in);
            propertiesMap.put(fileName, properties);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    private static String proFileName = "/wx-server-carload/src/main/resources/sms.properties";
    private static Properties pro;  
    static{  
        try {             
            pro = new Properties();  
            InputStream in = ClassLoader.class.getResourceAsStream(proFileName);  
            pro.load(in);  
            in.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
      
    public static String getValue(String key){  
        String value = pro.getProperty(key);  
        return value;  
    }  
}
