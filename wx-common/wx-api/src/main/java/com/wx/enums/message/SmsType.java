package com.wx.enums.message;

public enum SmsType {
	/**
	 * 注册
	 */
	REGIST(1, "注册"),
	/**
	 * 重置密码
	 */
	RESETPWD(2, "重置密码"),
	/**
	 * 取现
	 */
	WITHDRAW(3, "取现"),
	/**
	 * 修改交易密码
	 */
	UPDATE_TRADEPWD(4, "修改交易密码"),
	/**
	 * 验证原手机
	 */
	VERIFY_OLD_MOBILE(5, "验证原手机"),
	/**
	 * 验证绑定新手机
	 */
	VERIFY_NEW_MOBILE(6, "验证绑定新手机"),
	/**
	 * 短信登录
	 */
	SMS_LOGIN(7, "快捷登录"),
	/**
	 * 绑定银行卡
	 */
	BINDBANKCARD(8, "实名认证"),

	/**
	 * 未知
	 */
	NONE(-1, "未知");

	private int code;
	private String label;

	private SmsType(int code, String label) {
		this.code = code;
		this.label = label;
	}

	public int code() {
		return code;
	}

	public String label() {
		return label;
	}

	public static final SmsType from(int code) {
		for (SmsType type : SmsType.values()) {
			if (type.code == code) { return type; }
		}
		return NONE;
	}

	public static final SmsType fromName(String name) {
		for (SmsType type : SmsType.values()) {
			if (type.name().equalsIgnoreCase(name)) { return type; }
		}
		return NONE;
	}
}
