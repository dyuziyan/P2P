package com.wx.trade.enums;

/**
 * 对账状态
 */
public enum CompareStateEnum {

	NOTCOMPARE(0,"未对账"),LOSING(1,"丢单"),DATAASYMMETRY(2,"数据不对称");
	private CompareStateEnum(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	private Integer key;
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}
}
