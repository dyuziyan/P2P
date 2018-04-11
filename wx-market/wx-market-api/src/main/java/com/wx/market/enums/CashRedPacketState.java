package com.wx.market.enums;

/**
 * 现金红包使用状态
 */
public enum CashRedPacketState {

	UNUSED(0,"未使用"),USED(1,"已使用"),USING(2,"使用中");

	private CashRedPacketState(Integer key, String value) {
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
