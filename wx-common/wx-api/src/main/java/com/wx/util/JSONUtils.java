/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

/**
 * @ClassName: JSONUtils
 * @version 1.0
 * @Desc: JSONUtils
 * @author xiaojun.zhou
 * @date 2017年6月7日上午11:48:05
 * @history v1.0
 *
 */
public class JSONUtils {
	/**
	 * 方法描述: 将List对象序列化为JSON文本 方法名称: toJSONString
	 * 
	 * @param list
	 * @return
	 * @exception @throws
	 */
	public static <T> String toJSONString(List<T> list) {
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
	}

	/**
	 * 方法描述: 将对象序列化为JSON文本 方法名称: toJSONString
	 * 
	 * @param object
	 * @return
	 * @exception @throws
	 */
	public static String toJSONString(Object object) {
		JSONArray jsonArray = JSONArray.fromObject(object);
		return jsonArray.toString();
	}

	/**
	 * 方法描述: 将JSON对象数组序列化为JSON文本 方法名称: toJSONString
	 * 
	 * @param jsonArray
	 * @return
	 * @exception @throws
	 */
	public static String toJSONString(JSONArray jsonArray) {
		return jsonArray.toString();
	}

	/**
	 * 方法描述: 将对象转换为List对象 方法名称: toArrayList
	 * 
	 * @param object
	 * @return
	 * @exception @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toArrayList(Object object) {
		List arrayList = new ArrayList();

		JSONArray jsonArray = JSONArray.fromObject(object);
		Iterator it = jsonArray.iterator();
		while (it.hasNext()) {
			JSONObject jsonObject = (JSONObject) it.next();
			Iterator keys = jsonObject.keys();
			while (keys.hasNext()) {
				Object key = keys.next();
				Object value = jsonObject.get(key);
				arrayList.add(value);
			}
		}
		return arrayList;
	}

	/**
	 * 方法描述: 将对象转换为Collection对象 方法名称: toCollection
	 * 
	 * @param object
	 * @return
	 * @exception @throws
	 */
	@SuppressWarnings("rawtypes")
	public static Collection toCollection(Object object) {
		JSONArray jsonArray = JSONArray.fromObject(object);
		return JSONArray.toCollection(jsonArray);
	}

	/**
	 * 方法描述: 将对象转换为JSON对象 方法名称: toJSONObject
	 * 
	 * @param object
	 * @return
	 * @exception @throws
	 */
	public static JSONObject toJSONObject(Object object) {
		return JSONObject.fromObject(object);
	}

	/**
	 * 方法描述: 将对象转换成HashMap对象 方法名称: toHashMap
	 * 
	 * @param object
	 * @return
	 * @exception @throws
	 */
	@SuppressWarnings("rawtypes")
	public static HashMap toHashMap(Object object) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		JSONObject jsonObject = JSONUtils.toJSONObject(object);
		Iterator it = jsonObject.keys();
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			Object value = jsonObject.get(key);
			data.put(key, value);
		}
		return data;
	}

	/**
	 * 方法描述: 将对象转换为List<Map<String,Object>> 方法名称: toList
	 * 
	 * @param object
	 * @return 返回非实体类型(Map<String,Object>)的List
	 * @exception @throws
	 */
	@SuppressWarnings("rawtypes")
	public static List<Map<String, Object>> toList(Object object) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		JSONArray jsonArray = JSONArray.fromObject(object);
		for (Object obj : jsonArray) {
			JSONObject jsonObject = (JSONObject) obj;
			Map<String, Object> map = new HashMap<String, Object>();
			Iterator it = jsonObject.keys();
			while (it.hasNext()) {
				String key = (String) it.next();
				Object value = jsonObject.get(key);
				map.put((String) key, value);
			}
			list.add(map);
		}
		return list;
	}

	/**
	 * 方法描述: 将JSON对象数组转换为传入类型的List 方法名称: toList
	 * 
	 * @param jsonArray
	 * @param objectClass
	 * @return
	 * @exception @throws
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static <T> List<T> toList(JSONArray jsonArray, Class<T> objectClass) {
		return JSONArray.toList(jsonArray, objectClass);
	}

	/**
	 * 方法描述: 将对象转换为传入类型的List 方法名称: toList
	 * 
	 * @param object
	 * @param objectClass
	 * @return
	 * @exception @throws
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static <T> List<T> toList(Object object, Class<T> objectClass) {
		JSONArray jsonArray = JSONArray.fromObject(object);
		return JSONArray.toList(jsonArray, objectClass);
	}

	/**
	 * 方法描述: 将JSON对象转换为传入类型的对象 方法名称: toBean
	 * 
	 * @param jsonObject
	 * @param beanClass
	 * @return
	 * @exception @throws
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toBean(JSONObject jsonObject, Class<T> beanClass) {
		return (T) JSONObject.toBean(jsonObject, beanClass);
	}

	/**
	 * 方法描述: 将将对象转换为传入类型的对象 方法名称: toBean
	 * 
	 * @param object
	 * @param beanClass
	 * @return
	 * @exception @throws
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toBean(Object object, Class<T> beanClass) {
		JSONObject jsonObject = JSONObject.fromObject(object);
		return (T) JSONObject.toBean(jsonObject, beanClass);
	}

	/***
	 * 方法描述: 将JSON文本反序列化为主从关系的实体 (二层) 方法名称: toBean
	 * 
	 * @param <T>
	 *            泛型T 代表主实体类型
	 * @param <D>
	 *            泛型D 代表从实体类型
	 * @param jsonString
	 *            JSON文本
	 * @param mainClass
	 *            主实体类型
	 * @param detailName
	 *            从实体类在主实体类中的属性名称
	 * @param detailClass
	 *            从实体类型
	 * @return
	 */
	public static <T, D> T toBean(String jsonString, Class<T> mainClass, String detailName, Class<D> detailClass) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONArray jsonArray = (JSONArray) jsonObject.get(detailName);

		T mainEntity = JSONUtils.toBean(jsonObject, mainClass);
		List<D> detailList = JSONUtils.toList(jsonArray, detailClass);

		try {
			BeanUtils.setProperty(mainEntity, detailName, detailList);
		} catch (Exception ex) {
			throw new RuntimeException("主从关系JSON反序列化实体失败！");
		}

		return mainEntity;
	}

	/***
	 * 方法描述: 将JSON文本反序列化为主从关系的实体 (三层) 方法名称: toBean
	 * 
	 * @param <T>泛型T
	 *            代表主实体类型
	 * @param <D1>泛型D1
	 *            代表从实体类型
	 * @param <D2>泛型D2
	 *            代表从实体类型
	 * @param jsonString
	 *            JSON文本
	 * @param mainClass
	 *            主实体类型
	 * @param detailName1
	 *            从实体类在主实体类中的属性
	 * @param detailClass1
	 *            从实体类型
	 * @param detailName2
	 *            从实体类在主实体类中的属性
	 * @param detailClass2
	 *            从实体类型
	 * @return
	 */
	public static <T, D1, D2> T toBean(String jsonString, Class<T> mainClass, String detailName1,
			Class<D1> detailClass1, String detailName2, Class<D2> detailClass2) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONArray jsonArray1 = (JSONArray) jsonObject.get(detailName1);
		JSONArray jsonArray2 = (JSONArray) jsonObject.get(detailName2);

		T mainEntity = JSONUtils.toBean(jsonObject, mainClass);
		List<D1> detailList1 = JSONUtils.toList(jsonArray1, detailClass1);
		List<D2> detailList2 = JSONUtils.toList(jsonArray2, detailClass2);

		try {
			BeanUtils.setProperty(mainEntity, detailName1, detailList1);
			BeanUtils.setProperty(mainEntity, detailName2, detailList2);
		} catch (Exception ex) {
			throw new RuntimeException("主从关系JSON反序列化实体失败！");
		}

		return mainEntity;
	}

	public static String obj2xml(Object obj) {
		XMLSerializer xmlSerializer = new XMLSerializer();
		return xmlSerializer.write(JSONSerializer.toJSON(obj));
	}
}
