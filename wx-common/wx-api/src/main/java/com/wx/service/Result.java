package com.wx.service;

import java.io.Serializable;

import com.wx.dto.BaseObject;

/**
 * 用于返回操作信息
 * 
 */
public class Result<T> extends BaseObject implements Serializable {

	private static final long serialVersionUID = -936937909310071523L;
	public static final String SUCCESS = "000";
	public static final String ERROR = "999";

	private String code;
	private String message;
	private T data;

	protected Result() {
	}

	protected Result(String code, String message) {
		this(code, message, null);
	}

	protected Result(String code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public boolean success() {
		return SUCCESS.equals(code);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T target) {
		this.data = target;
	}

}
