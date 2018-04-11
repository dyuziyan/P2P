package com.wx.support;

import java.util.ResourceBundle;

import my.comp.lang.StringUtils;

/**
 * ResourceBundle 解决国际化业务...对应的配置文件应当以iso8859-1编码存储
 */
public abstract class Messages {

	private static ResourceBundle bundle = ResourceBundle.getBundle("Messages");

	public static final String getMessage(String key) {
		return bundle.getString(String.valueOf(key));
	}

	public static final String getMessage(String key, Object... args) {
		String message = getMessage(key);
		return StringUtils.format(message, args);
	}

}
