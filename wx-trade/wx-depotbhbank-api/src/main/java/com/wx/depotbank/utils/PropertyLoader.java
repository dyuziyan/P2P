/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.utils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * @ClassName: PropertyLoader
 * @version 1.0
 * @Desc: PropertyLoader
 * @author xiaojun.zhou
 * @date 2017年6月29日上午11:25:19
 * @history v1.0
 *
 */
public class PropertyLoader {

	private static Map<String, Properties> propertiesMap = new HashMap<String, Properties>();

	public static String getKey(String fileName, String key) {

		if (!propertiesMap.containsKey(fileName) || propertiesMap.get(fileName) == null) {
			loadProperty(fileName);
		}

		Properties properties = propertiesMap.get(fileName);

		return properties.getProperty(key);
	}

	public static String getKey(String key) {
		return getKey("config.properties", key);
	}

	/**
	 * 
	 * 描述：加载属性文件
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月29日上午11:25:29
	 * @param fileName
	 */
	private static void loadProperty(String fileName) {
		Properties properties = new Properties();
		try {
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			Resource resource = resolver.getResource(fileName);
			InputStream in = new BufferedInputStream(resource.getInputStream());
			properties.load(in);
			propertiesMap.put(fileName, properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println(getKey("tyj_rate"));
	}
}
