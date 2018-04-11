package com.wx.trade.enums;

/**
 * 对账状态
 */
@Deprecated
public enum BalanceOfAccountStateEnum {

	UNCOMPARE(0,"未对账"),COMPARED(1,"已对账"),NEEDCORRECT(2,"需校准"),CORRECTED(3,"已校准");
	private BalanceOfAccountStateEnum(Integer key, String value) {
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
