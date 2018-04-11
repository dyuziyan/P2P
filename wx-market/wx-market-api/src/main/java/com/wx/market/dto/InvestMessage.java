package com.wx.market.dto;

import com.wx.enums.common.BusiEvent;
import com.wx.enums.common.ClientType;

import my.comp.sn.SnBuilder;


/**
 * 投资消息
 */
public class InvestMessage extends MarketMessage {
	
	private static final long serialVersionUID = 13479L;
	private Long productId; // 项目ID
	private Long orderId; // 订单ID
	
	private String cashRedpacketId;//使用现金红包ID

	protected InvestMessage() {
	}

	public InvestMessage(long userId, long orderId, ClientType clientType) {
		super(userId, clientType);
		this.orderId = orderId;
	}
	
	public InvestMessage(long userId, long productId,long orderId, ClientType clientType,String cashRedpacketId) {
		super(userId, clientType);
		this.orderId = orderId;
		this.productId = productId;
		this.cashRedpacketId = cashRedpacketId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long projectId) {
		this.productId = projectId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public String getCashRedpacketId() {
		return cashRedpacketId;
	}

	public void setCashRedpacketId(String cashRedpacketId) {
		this.cashRedpacketId = cashRedpacketId;
	}



	@Override
	public String getSn() {
		BusiEvent event = BusiEvent.INVEST;
		SnBuilder sn = SnBuilder.create(event.getCode(), getUserId(), getOrderId());
		return sn.toString();
	}
}
