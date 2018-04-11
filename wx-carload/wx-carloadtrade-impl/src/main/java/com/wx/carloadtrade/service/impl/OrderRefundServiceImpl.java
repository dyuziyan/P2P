package com.wx.carloadtrade.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wx.carloadtrade.dao.OrderRefundDao;
import com.wx.carloadtrade.dto.OrderRefundReasonDto;
import com.wx.carloadtrade.service.OrderRefundService;
import com.wx.service.Result;
import com.wx.service.Results;


@Service
public class OrderRefundServiceImpl implements OrderRefundService {
	
	@Resource
	private OrderRefundDao orderRefundDao;

	@Override
	public Result<ArrayList<OrderRefundReasonDto>> queryOrderRefundReason() {
		return Results.success(orderRefundDao.queryOrderRefundReason());
	}

}
