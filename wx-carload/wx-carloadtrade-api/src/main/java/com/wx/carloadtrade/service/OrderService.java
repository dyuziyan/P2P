package com.wx.carloadtrade.service;

import java.util.List;
import java.util.Map;

import com.wx.carloadtrade.dto.OrderMainListDto;
import com.wx.service.Result;

/**
 * 订单服务端
 * @author 13697
 *
 */
public interface OrderService {
	
	/**
	 * 生成订单
	 * @param parameterMap 订单数据
	 * @param userId 用户编号
	 * @return
	 */
	public Result<Map<String,Object>> saveOrder(OrderMainListDto dto);
	
	/**
	 * 查询主订单
	 * @param mainOrderNum  主订单号码
	 * @param orderState 订单状态
	 * @param userId 用户编号
	 */
	public  Result<List<Map<String, String>>>  queryOrderMainList(String mainOrderNum,String orderState,String userId,Integer startRow,Integer rows);
	
	/**
	 * 查询订单详情
	 * @param mainOrderNum
	 * @return
	 */
	public Result<Map<String,Object>> queryOrderDetails(String mainOrderNum,String userId);
	
	/**
	 * 修改订单状态
	 * @param orderState 订单状态
	 * @param payState 订单支付状态
	 * @param mainOrderNum 主订单号码
	 * @return
	 */
//	public Result<String> udpateOrderStatus(String orderState,String payState,String mainOrderNum);
	
	/**
	 * 取消订单
	 * @param userId
	 * @param mainOrderNum
	 * @return
	 */
	public Result<String> cancelOrder(String userId,String mainOrderNum);
	
	/**
	 * 支付成功
	 * @param userId
	 * @param mainOrderNum
	 * @return
	 */
	public Result<String> paySuccess(String userId,String mainOrderNum,String operateDesc);

	/**
	 * 退款申请
	 * @param userId 用户编号
	 * @param mainOrderNum 主订单号
	 * @param refundReason 退款原因
	 * @param refundDesc 退款问题描述
	 * @return
	 */
	public Result<String> orderRefundApply(String userId,String mainOrderNum,String refundReason,String refundDesc);
	
	
	/**
     * 修改订单状态(主订单/维修子订单/保养子订单/养护子订单)
     * @param orderState 订单状态
     * @param payState 支付状态
     * @param mainOrderNum 主订单号
     * @return
     */
    public Result<String> udpateOrderStatus(String orderState, String payState, String mainOrderNum);
    
}
