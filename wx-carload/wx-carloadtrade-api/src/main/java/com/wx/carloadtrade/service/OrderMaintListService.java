package com.wx.carloadtrade.service;

import java.util.List;
import java.util.Map;

import com.wx.carloadtrade.dto.OrderMainListDto;
       
import com.wx.service.Result;

import net.sf.json.JSONObject;

public interface OrderMaintListService {

	
	/**
	 * 验证历史订单并返回商家id
	 * @param map
	 * @return
	 */
	public Result<List<String>> validateHistoryOrder(Map<String, Object> map);
	
	
	
	
	/**
	 * 生成订单
	 * @param oml
	 * @return
	 */
	public Result<Integer> saveOrderMaintList(JSONObject oml,Long userId);
	
	/**
	 * 生成订单详情
	 * @param omld
	 * @return
	 */
	public int saveOrderMaintDetails(JSONObject omd);
	
	
	/**
	 * 查询全部订单
	 */
	public  Result<List<JSONObject>> queryTendingOrder(Map<String, Object> map);
	

	/**
	 * 订单日志
	 * @param userId 用户编号
	 * @param orderStates 订单状态（排除）
	 * @param orderNum
	 * @return
	 */
	public  Result<List<Map<String,Object>>> queryOrderLog(String userId,String orderStates,String orderNum);
	
	
	/**
	 * 修改订单状态
	 * @param id 订单编号
	 * @param orderState  订单状态
	 * @param payState  订单支付状态
	 * @return
	 */
	public Result<Integer> udpateOrderStatus(String id,String orderState,String payState);
	
	/**
	 * 查询订单信息
	 * @param id
	 * @return
	 */
	public Result<Map<String, Object>> queryOrderInfoById(String id);
	
	/**
	 * 查询维护保养订单详情
	 * @param id 订单详情编号 
	 * @param userId 用户编号
	 * @param orderNum 订单号码
	 * @return
	 */
	public Result<Map<String, Object>> queryReport(String id,String userId,String orderNum);
	
	/**
	 * 维护报告详情
	 * @param orderId
	 * @return
	 */
	public Result<Map<String, Object>> queryReportDetails(String orderNum,boolean flag);
	
	/**
	 * 修改订单金额
	 * @param id 订单编号
	 * @param orderTotalPrice  订单总价
	 * @return
	 */
	public Result<Integer> udpateOrderTotalPrice(OrderMainListDto dto);
	
	/**
	 * 支付成功后
	 * @param orderId  订单编号
	 * @param partIds  用户不要的配件编号
	 * @param money  总金额
	 * @param selectedPartIds  用户选中的配件编号
	 * @return
	 */
	public Result<String> payment(String orderId,String partIds,String money,String payType,String selectedPartIds);
	
	
	/**
	 * 维护保养记录
	 * @param
	 * @param userId 用户编号
	 * @param orderState 订单状态
	 * @return
	 */
	public Result<List<Map<String,Object>>> maintainRecord(String userId,String orderState,String startRow,String rows);
	
	/**
	 * 验证“服务中”订单
	 * @param userId
	 * @param userCarId
	 * @return
	 */
	public Result<Map<String,Object>> validateOrder(Long userId,Long userCarId);
	
	public void queryBusiShopsList(String id);
	
	
	/**
	 * 接受调整预约保养时间/取车时间
	 * @param orderNum  订单号码
	 * @param handleId   订单日志编号
	 * @return
	 */
	public Result<String> acceptAdjustDate(String orderNum,Long handleId);

	/**
	 * 生成订单日志
	 * @param orderNum 订单号码
	 * @param handleInfo 描述
	 * @param handleType 状态
	 * @return
	 */
	public Result<String> saveOrderOperationLog(String orderNum,String  handleInfo,int handleType);
	
	/**
	 * 查询订单
	 * @param userId 用户编号
	 * @param orderState 需要排除的状态
	 * @return
	 */
	public Result<List<OrderMainListDto>> queryOrderMaint(String userId,String orderState);
	
	public Result<Map<String, Object>> newPaymentInfo(String orderNum,boolean flag);
	
}
