package com.wx.market.jms;

import java.util.ArrayList;
import java.util.List;

public class MarketMessageList implements MarketMessageWrapper {

	private List<MarketMessageEntry> list;

	public MarketMessageList() {
		list = new ArrayList<MarketMessageEntry>();
	}

	public void add(MarketMessageEntry entry) {
		list.add(entry);
	}

	public void clear() {
		list.clear();
	}

	@Override
	public MarketMessageEntry[] array() {
		return list.toArray(new MarketMessageEntry[0]);
	}

}
