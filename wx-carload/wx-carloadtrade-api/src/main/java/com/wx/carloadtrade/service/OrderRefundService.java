package com.wx.carloadtrade.service;

import java.util.ArrayList;

import com.wx.carloadtrade.dto.OrderRefundReasonDto;
import com.wx.service.Result;

public interface OrderRefundService {
	
	/**
	 * 退款原因类型
	 * @param advertAddr 广告位置
	 * @return
	 */
	public Result<ArrayList<OrderRefundReasonDto>> queryOrderRefundReason();
}
