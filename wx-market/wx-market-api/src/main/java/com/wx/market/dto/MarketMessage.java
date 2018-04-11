package com.wx.market.dto;

import java.io.Serializable;

import my.comp.sn.SerialNumberable;

import com.wx.enums.common.ClientType;

public abstract class MarketMessage implements SerialNumberable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userId;
	private ClientType clientType;

	protected MarketMessage() {
	}

	protected MarketMessage(Long userId, ClientType clientType) {
		this.userId = userId;
		this.clientType = clientType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

}
