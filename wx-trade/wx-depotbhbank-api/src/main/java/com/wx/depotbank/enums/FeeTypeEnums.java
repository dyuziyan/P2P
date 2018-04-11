package com.wx.depotbank.enums;

/**
 * 手续费
 */
public enum FeeTypeEnums {

	NOCHARGE("0", "不收取"),CHARGE("1", "收取"),;

	private FeeTypeEnums(String key, String value) {
		this.key = key;
		this.value = value;
	}

	private String key;
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
