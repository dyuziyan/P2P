package com.wx.market.jms;

public class MarketMessageHolder {

	private static final ThreadLocal<MarketMessageWrapper> messageHolder = new ThreadLocal<MarketMessageWrapper>();

	public static MarketMessageWrapper get() {
		return messageHolder.get();
	}

	public static void set(MarketMessageWrapper wrapper) {
		messageHolder.set(wrapper);
	}

	public static MarketMessageWrapper remove() {
		MarketMessageWrapper wrapper = get();
		messageHolder.remove();
		return wrapper;
	}

}
