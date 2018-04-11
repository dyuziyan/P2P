package com.wx.enums.market;

public enum BusiType {
	/**
	 * 送现金红包
	 */
	CASHREDPACKET("R0"),
	
	/**
	 * 标的返现
	 */
	CASHBACK("R1"),
	
	/**
	 * 其他
	 */
	OTHER("99");

	private String code;

	private BusiType(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

}
