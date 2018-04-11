package com.wx.trade.enums;

/**
 * 对账类型
 */
public enum CompareTypeEnum {

	RECHARGECOMPARE(1,"充值对账"),WITHDRAWCOMPARE(2,"提现对账"),REDPACKETCOMPARE(3,"红包对账"),FUNDTRANSFERCOMPARE(4,"资金迁移对账")
	,INVESTCOMPARE(5,"投资对账");
	private CompareTypeEnum(Integer key, String value) {
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
