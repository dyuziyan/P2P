 package com.wx.enums.message;

public enum SmsVerifyResult {
	/**
	 * 成功
	 */
	SUCCESS("0", ""),
	/**
	 * 验证码错误
	 */
	VERIFY_CODE_ERROR("1", "验证码错误"),
	/**
	 * 超时
	 */
	VERIFY_TIMEOUT("2", "验证码已失效"),
	/**
	 * 验证异常
	 */
	VERIFY_DATA_ERROR("3", "验证异常"),
	/**
	 * 验证码发送次数超限
	 */
	VERIFY_OVER_LIMIT("4", "验证码发送次数超限"),
	/**
	 * 短信请求间隔太短请稍后尝试
	 */
	VERIFY_TIME_RANGE("5", "短信请求间隔太短请稍后尝试");

	private String code;
	private String message;

	private SmsVerifyResult(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
