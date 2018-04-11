package com.wx.carloadbase;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import my.comp.lang.MapUtils;
import my.comp.lang.StringUtils;


public class BRule implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8883629247830793346L;
	private String type;
	private Integer typeId;
	private Map<String, String> properties;

	public BRule() {
		properties = new HashMap<String, String>();
	}

	public String getValue(String name) {
		return properties.get(name);
	}
	
	public String getValue(String name, String defaultValue) {
		return MapUtils.getString(properties, name, defaultValue);
	}

	public int getIntValue(String name) {
		return MapUtils.getIntValue(properties, name, 0);
	}

	public int getIntValue(String name, int defaultValue) {
		return MapUtils.getIntValue(properties, name, defaultValue);
	}

	public long getLongValue(String name) {
		return MapUtils.getLongValue(properties, name, 0L);
	}

	public long getLongValue(String name, long defaultValue) {
		return MapUtils.getLongValue(properties, name, defaultValue);
	}

	public BigDecimal getBigValue(String name, BigDecimal defaultValue) {
		String value = getValue(name);
		if (StringUtils.isNotEmpty(value)) return new BigDecimal(value);
		return defaultValue;
	}

	public boolean contains(String name) {
		return properties.containsKey(name);
	}

	public Iterator<String> nameIterator() {
		return properties.keySet().iterator();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public void setProperty(String key, String value) {
		this.properties.put(key, value);
	}

}
