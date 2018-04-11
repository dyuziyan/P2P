package com.wx.service;

import my.comp.lang.StringUtils;

import com.wx.exception.MarketServiceException;
import com.wx.support.Messages;

public class Results {

	public static <T> Result<T> byCode(String code, Object... args) {
		String message = Messages.getMessage(code, args);
		return byMessage(code, message);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> Result<T> byMessage(String code, String message) {
		return new Result(code, message);
	}

	public static <T> Result<T> byTpl(String code, String pattern, Object... args) {
		String message = StringUtils.format(pattern, args);
		return byMessage(code, message);
	}
	
	public static <T> Result<T> byExc(Exception e) {
		return byMessage(Result.ERROR, e.getMessage());
	}

	public static <T> Result<T> byExc(MarketServiceException e) {
		String code = e.getCode();
		code = (e.getCode() == null || e.getCode().trim().length() == 0) ? Result.ERROR : code;
		return byMessage(code, e.getMessage());
	}

	public static <T> Result<T> success() {
		return byMessage(Result.SUCCESS, "");
	}

	public static <T> Result<T> error() {
		return byMessage(Result.ERROR, "");
	}
	
	public static <T> Result<T> success(T target) {
		Result<T> result = success();
		result.setData(target);
		return result;
	}

	public static <T> Result<T> error(String message) {
		return byMessage(Result.ERROR, message);
	}
	
	public static <T> Result<T> error(String message,T target) {
		Result<T> result = byMessage(Result.ERROR, message);
		result.setData(target);
		return result;
	}
}
