package com.wx.market.jms;

import my.comp.jms.DestType;

import com.wx.market.dto.MarketMessage;

public class MarketMessageEntry implements MarketMessageWrapper {
	private MarketMessage message;
	private String destName;
	private DestType destType;

	protected MarketMessageEntry(MarketMessage message, DestType destType, String destName) {
		this.message = message;
		this.destName = destName;
		this.destType = destType;
	}

	public MarketMessage getMessage() {
		return message;
	}

	public void setMessage(MarketMessage message) {
		this.message = message;
	}

	public String getDestName() {
		return destName;
	}

	public void setDestName(String destName) {
		this.destName = destName;
	}

	public DestType getDestType() {
		return destType;
	}

	public void setDestType(DestType destType) {
		this.destType = destType;
	}

	public String toString() {
		StringBuilder s = new StringBuilder("[MarketMessageList:");
		s.append("destType:" + destType.name());
		s.append(", destName:" + destName);
		s.append(", message:" + message.toString());
		return s.toString();

	}

	@Override
	public MarketMessageEntry[] array() {
		return new MarketMessageEntry[] { this };
	}

}
