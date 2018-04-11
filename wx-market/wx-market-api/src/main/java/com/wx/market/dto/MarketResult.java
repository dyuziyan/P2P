package com.wx.market.dto;

import com.wx.service.Result;

public class MarketResult extends Result<Void> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9023154517991292620L;

	public MarketResult(String code, String message) {
		super(code, message);
	}

	public static MarketResult bySuc() {
		return new MarketResult(Result.SUCCESS, "");
	}

	public static MarketResult byExc(Exception e) {
		return new MarketResult(Result.ERROR, e.getMessage());
	}

	public static MarketResult byErr(String message) {
		return new MarketResult(Result.ERROR, message);
	}

}
