package com.wx.trade.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wx.service.BaseService;
import com.wx.service.Result;
import com.wx.service.Results;
import com.wx.trade.dao.OrderDao;
import com.wx.trade.dto.OrderDto;
import com.wx.trade.service.OrderService;

/**
 * 订单
 */
@Service
public class OrderServiceImpl extends BaseService implements OrderService {

	@Resource
	private OrderDao orderDao;
	
	@Override
	public Result<OrderDto> getOrder(long orderId) {
		OrderDto orderResult=orderDao.getOrder(orderId);
		if(orderResult==null){
			return Results.error();
		}
		return Results.success(orderResult);
	}

}

