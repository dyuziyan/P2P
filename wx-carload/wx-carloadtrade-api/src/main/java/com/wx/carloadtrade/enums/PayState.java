package com.wx.carloadtrade.enums;

/**
 * 支付状态
 * 
 * @author 13697
 *
 */
public enum PayState {
	/**
	 * 待支付
	 */
	TO_BE_PAID("待支付", 0),
	/**
	 * 已支付
	 */
	ALREADY_PAID("已支付", 1),
	/**
	 * 失败支付
	 */
	section_PAID("失败支付", 2);

	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private PayState(String name, int index) {
		this.name = name;
		this.index = index;
	}

	// get set 方法
	public String getName() {
		return name;
	}

	public int getIndex() {
		return index;
	}

	public static String getName(int index) {
		for (PayState c : PayState.values()) {
			if (c.getIndex() == index) {
				return c.name;
			}
		}
		return null;
	}
}
