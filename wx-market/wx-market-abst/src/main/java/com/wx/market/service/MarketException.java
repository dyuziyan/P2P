package com.wx.market.service;

public class MarketException extends Exception {

	private static final long serialVersionUID = 8688988502773103566L;

	public MarketException() {
		super();
	}

	public MarketException(String message) {
		super(message);
	}

	public MarketException(Throwable cause) {
		super(cause);
	}

	public MarketException(Throwable cause, String message) {
		super(message, cause);
	}

}
