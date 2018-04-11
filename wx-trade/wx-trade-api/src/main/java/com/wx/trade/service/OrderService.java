package com.wx.trade.service;

import com.wx.service.Result;
import com.wx.trade.dto.OrderDto;

import my.comp.rmi.annotation.RemoteService;


/**
 * 订单接口
 */
@RemoteService("/orderService")
public interface OrderService {

	/**
	 * 根据订单ID获取订单信息
	 */
	Result<OrderDto> getOrder(long orderId);
}
