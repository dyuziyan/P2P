package com.wx.market.handler;

import com.wx.enums.common.ClientType;
import com.wx.enums.market.BusiType;


public abstract class TypedBusiHandler extends AbstractHandler {

	private BusiType type; //奖品类型
	private Integer periods ; //活动期数
	private ClientType clientType ; //生效客户端类型

	public void setType(BusiType type) {
		this.type = type;
	}
	public BusiType getType() {
		return type;
	}

	public Integer getPeriods() {
		return periods;
	}
	public void setPeriods(Integer periods) {
		this.periods = periods;
	}
	public ClientType getClientType() {
		return clientType;
	}
	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}
	
	protected TypedBusiHandler() {

	}
}
