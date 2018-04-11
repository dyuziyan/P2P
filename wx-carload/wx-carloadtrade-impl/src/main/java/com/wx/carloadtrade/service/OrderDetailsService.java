package com.wx.carloadtrade.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wx.carloadtrade.dao.OrderDao;
import com.wx.carloadtrade.domain.CarServicePart;
import com.wx.carloadtrade.dto.OrderMainListDto;
import com.wx.service.Result;

/**
 * 订单服务
 * @author 13697
 *
 */
public interface OrderDetailsService {
	
	/**
	 * 生成订单
	 * @param dto
	 * @param orderDao
	 * @return
	 */
	public BigDecimal saveOrder(OrderMainListDto dto,OrderDao orderDao);
	
	/**
	 * 查询汽车服务项目
	 * @param ids
	 * @return
	 */
	public List<CarServicePart> queryCarServicePart(String ids,OrderDao orderDao);
	
	/**
	 * 修改订单状态(主订单/维修子订单/保养子订单/养护子订单)
	 * @param orderState 订单状态
	 * @param payState 支付状态
	 * @param mainOrderNum 主订单号
	 * @return
	 */
	public Result<String> udpateOrderStatus(String orderState, String payState, String mainOrderNum,OrderDao orderDao);
	
	/**
	 * 生成子訂單日誌
	 * @param mainOrderNum 主订单号
	 * @param handleType 操作类型
	 * @param remark 备注
	 * @param orderDao
	 * @return
	 */
	public Result<String> saveOrderLog(String mainOrderNum, Integer handleType, String remark, OrderDao orderDao);
	
	/**
	 * 退款申请
	 * @param userId 用户编号
	 * @param mainOrderNum 主订单号
	 * @param refundReason 退款原因
	 * @param refundDesc 退款问题描述
	 * @return
	 */
	public boolean orderRefundApply(Map<String,String> map,OrderDao orderDao);
	
	
	
}
