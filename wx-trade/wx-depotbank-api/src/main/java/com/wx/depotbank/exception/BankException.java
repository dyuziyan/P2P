/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.exception;

/**
 * @ClassName: BankException
 * @version 1.0
 * @Desc: BankException
 * @author xiaojun.zhou
 * @date 2017年6月6日下午4:48:34
 * @history v1.0
 *
 */
public class BankException extends Exception {

	private static final long serialVersionUID = -7812803213785653378L;
	private String code;
	private String message;

	public String getMessage() {
		return message;
	}

	public String getCode() {
		return code;
	}

	public BankException() {
		super();
	}

	public BankException(String code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public BankException(String message) {
		super(message);
		this.message = message;
	}

	public BankException(String message, Throwable throwable) {
		super(message, throwable);
		this.message = message;
	}

	public BankException(String message, String errMsg, Throwable throwable) {
		super(message + " [" + errMsg + "] ", throwable);
		this.message = message;
	}
}
