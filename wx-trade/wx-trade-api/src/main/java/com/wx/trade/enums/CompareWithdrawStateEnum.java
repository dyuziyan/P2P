package com.wx.trade.enums;

/**
 * 提现对账状态
 */
public enum CompareWithdrawStateEnum {
	WITHDRAWACCEPT("W3","系统受理中"),WITHDRAWING("W4","银行受理中"),WITHDRAWSUCCESS("S1","银行交易成功"),
	WITHDRAWFAIL("F1","付款失败"),CANCELAFTERVERIFICATION("F2","付款核销"),WITHDRAWREFUSE("R9","审批拒绝");
	private CompareWithdrawStateEnum(String key, String value) {
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
