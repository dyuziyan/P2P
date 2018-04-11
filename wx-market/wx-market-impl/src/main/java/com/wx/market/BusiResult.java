package com.wx.market;

import my.comp.lang.StringUtils;

public class BusiResult {
	private boolean success;
	private String message;
	private Object result;

	protected BusiResult(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public static BusiResult create(boolean success, String message) {
		return new BusiResult(success, message);
	}

	public static BusiResult success() {
		return create(true, null);
	}

	public static BusiResult error(String message) {
		return create(false, message);
	}

	public static BusiResult error(String pattern, Object... args) {
		String message = StringUtils.format(pattern, args);
		return create(false, message);
	}

	public void setResult(Object result) {
		this.result = result;
	}

	@SuppressWarnings("unchecked")
	public <T> T getResult() {
		return (T) result;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}
}
