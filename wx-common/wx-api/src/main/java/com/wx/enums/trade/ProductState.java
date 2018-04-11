package com.wx.enums.trade;

import java.util.HashMap;
import java.util.Map;

public enum ProductState {
	
	INVESTING(2, "投资中"),
	ALL_SALE(3, "已幕满"),
	REFUNDING(4, "兑付中"),
	DONE(5, "已完成"),

	/**
	 * 未知
	 */
	NONE(-1, "未知");

	private int code;
	private String label;

	private ProductState(int code, String label) {
		this.code = code;
		this.label = label;
	}

	public int code() {
		return code;
	}

	public String label() {
		return label;
	}

	private static final Map<Integer, ProductState> map = new HashMap<Integer, ProductState>();

	static {
		ProductState[] values = ProductState.values();
		for (ProductState productType : values) {
			map.put(productType.code, productType);
		}
	}

	public static ProductState getEnum(Integer code) {
		return map.get(code);
	}
}
