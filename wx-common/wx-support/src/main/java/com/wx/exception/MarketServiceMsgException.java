package com.wx.exception;

import com.wx.support.Messages;

import my.comp.lang.StringUtils;

/**
 * 业务异常类
 * 
 */
public class MarketServiceMsgException {


	/**
	 * 创建异常
	 * 
	 * @param key
	 *            对应Messages.properties中key
	 * @param args
	 *            对应Messages.properties中消息模板预留参数
	 * @return
	 */
	public static MarketServiceException byKey(String key, Object... args) {
		return new MarketServiceException(key, Messages.getMessage(key, args));
	}

	/**
	 * 创建异常
	 * 
	 * @param message
	 *            异常消息
	 * @return
	 */
	public static MarketServiceException byMsg(String message) {
		return new MarketServiceException(message);
	}

	/**
	 * 创建异常
	 * 
	 * @param cause
	 * @return
	 */
	public static MarketServiceException byExc(Throwable cause) {
		return new MarketServiceException(cause);
	}

	/**
	 * 创建异常
	 * 
	 * @param cause
	 * @param message
	 * @return
	 */
	public static MarketServiceException byExc(Throwable cause, String message) {
		return new MarketServiceException(cause, message);
	}

	/**
	 * 创建异常
	 * 
	 * @param template
	 *            异常模板，如你的用户名是{0}
	 * @param args
	 *            模板参数
	 * @return
	 */
	public static MarketServiceException byTpl(String template, Object... args) {
		return byMsg(StringUtils.format(template, args));
	}

}
