package com.wx.carloadtrade.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wx.carloadtrade.domain.CarServicePart;
import com.wx.carloadtrade.dto.OrderDetailsDto;
import com.wx.carloadtrade.dto.OrderMainListDto;

import my.comp.dao.mybatis.MybatisDao;

@MybatisDao
public interface OrderDao 
{
	/**
	 * 新增主订单
	 * @param dto
	 * @return
	 */
	public int saveOrderMainList(OrderMainListDto dto);
	
	/**
	 * 查询车辆服务项
	 * @param ids
	 * @return
	 */
	public List<CarServicePart> queryCarServicePart(@Param("ids")String ids,@Param("serviceProNum")String serviceProNum);
	
	/**
	 * 新增保养订单
	 * @param dto
	 * @return
	 */
	public int saveOrderMaintainList(OrderMainListDto dto);
	
	/**
	 * 生成保养订单详情
	 * @param dto
	 * @return
	 */
	public int saveOrderMaintainDetails(List<OrderDetailsDto> dtoList);
	
	/**
	 * 新增养护订单
	 * @param dto
	 * @return
	 */
	public int saveOrderCareList(OrderMainListDto dto);
	
	/**
	 * 生成养护订单详情
	 * @param dto
	 * @return
	 */
	public int saveOrderCareDetails(List<OrderDetailsDto> dtoList);
	
	/**
	 * 新增维修订单
	 * @param dto
	 * @return
	 */
	public int saveOrderRepairList(OrderMainListDto dto);
	
	/**
	 * 生成维修订单详情
	 * @param dto
	 * @return
	 */
	public int saveOrderRepairDetails(List<OrderDetailsDto> dtoList);
	
	/**
	 * 查询主订单信息
	 * @param mainOrderNum
	 * @param orderState
	 * @param userId
	 * @return
	 */
	public List<OrderMainListDto> queryOrderMainList(@Param("mainOrderNum") String mainOrderNum,@Param("orderState") String orderState,
			@Param("userId") String userId,@Param("startRow")Integer startRow,@Param("rows")Integer rows);
	
	/**
	 * 查询维修订单详情
	 * @param mainOrderNum
	 * @param userId
	 * @return
	 */
	public List<Map<String,String>> queryOrderRepairDetails(@Param("mainOrderNum") String mainOrderNum,@Param("userId") String userId);
	
	/**
	 * 查询养护订单详情
	 * @param mainOrderNum
	 * @param userId
	 * @return
	 */
	public List<Map<String,String>> queryOrderCareDetails(@Param("mainOrderNum") String mainOrderNum,@Param("userId") String userId);
	
	/**
	 * 查询保养订单详情
	 * @param mainOrderNum
	 * @param userId
	 * @return
	 */
	public List<Map<String,String>> queryOrderMaintainDetails(@Param("mainOrderNum") String mainOrderNum,@Param("userId") String userId);
	
	/**
	 * 获取时间段优惠金额 
	 * @param appointTimeId 时间段id
	 * @return
	 */
	public Map<String,String> getAppointTimeDiscount(@Param("appointTimeId") Long appointTimeId);
	
	/**
	 * 修改主订单状态
	 * @param table 要操作的数据库表
	 * @param orderState 订单状态
	 * @param payState 订单支付状态
	 * @param mainOrderNum 主订单号码
	 * @return
	 */
	public int udpateMainOrderStatus(@Param("orderState") String orderState,@Param("payState") String payState,@Param("mainOrderNum") String mainOrderNum);
	
	/**
	 * 修改养护订单状态或支付状态
	 * @param table
	 * @param orderState
	 * @param payState
	 * @param mainOrderNum
	 * @return
	 */
	public int udpateCareOrderStatus(@Param("orderState") String orderState,@Param("payState") String payState,@Param("mainOrderNum") String mainOrderNum);
	
	/**
	 * 修改保养订单状态或支付状态
	 * @param table
	 * @param orderState
	 * @param payState
	 * @param mainOrderNum
	 * @return
	 */
	public int udpateMaintainOrderStatus(@Param("orderState") String orderState,@Param("payState") String payState,@Param("mainOrderNum") String mainOrderNum);
	
	/**
	 * 修改维修订单状态或支付状态 
	 * @param table
	 * @param orderState
	 * @param payState
	 * @param mainOrderNum
	 * @return
	 */
	public int udpateRepairOrderStatus(@Param("orderState") String orderState,@Param("payState") String payState,@Param("mainOrderNum") String mainOrderNum);

	/**
	 * 查询维修订单
	 * @param mainOrderNum
	 * @return
	 */
	public List<OrderMainListDto> queryOrderRepair(@Param("mainOrderNum") String mainOrderNum);
	
	/**
	 * 查询养护订单
	 * @param mainOrderNum
	 * @return
	 */
	public List<OrderMainListDto> queryOrderCare(@Param("mainOrderNum") String mainOrderNum);
	
	/**
	 * 查询保养订单
	 * @param mainOrderNum
	 * @return
	 */
	public List<OrderMainListDto> queryOrderMaintain(@Param("mainOrderNum") String mainOrderNum);
	
	/**
	 * 养护-订单操作日志
	 * @param mainOrderNum 主订单号
	 * @param orderNum 养护订单号
	 * @param handleType 操作类型  1待支付，2已取消（包含用户自动取消和订单超时取消），3服务中，4退款中（用户发起退款），5退款已取消（用户取消退款），6退款已拒绝，7已退款，8已完成
	 * @param remark 备注
	 * @return
	 */
	public int saveCareOrderLog(@Param("mainOrderNum")String mainOrderNum,@Param("orderNum")String orderNum,@Param("handleType")Integer handleType,@Param("remark")String remark);
	
	/**
	 * 保养-订单操作日志
	 * @param mainOrderNum 主订单号
	 * @param orderNum 保养订单号
	 * @param handleType 操作类型  1待支付，2已取消（包含用户自动取消和订单超时取消），3服务中，4退款中（用户发起退款），5退款已取消（用户取消退款），6退款已拒绝，7已退款，8已完成
	 * @param remark 备注
	 * @return
	 */
	public int saveMaintainOrderLog(@Param("mainOrderNum")String mainOrderNum,@Param("orderNum")String orderNum,@Param("handleType")Integer handleType,@Param("remark")String remark);
	
	/**
	 * 维修-订单操作日志
	 * @param mainOrderNum 主订单号
	 * @param orderNum 维修订单号
	 * @param handleType 操作类型  1待支付，2已取消（包含用户自动取消和订单超时取消），3服务中，4退款中（用户发起退款），5退款已取消（用户取消退款），6退款已拒绝，7已退款，8已完成
	 * @param remark 备注
	 * @return
	 */
	public int saveRepairOrderLog(@Param("mainOrderNum")String mainOrderNum,@Param("orderNum")String orderNum,@Param("handleType")Integer handleType,@Param("remark")String remark);
	
	/**
	 * 生成退款订单
	 * @param refundNum 退款编号
	 * @param orderNum 订单号码
	 * @param refundAmount 退款金额
	 * @param userId 客户ID
	 * @param refundReason 退款原因
	 * @param refundDesc 退款问题描述
	 * @return
	 */
	/*public int saveOrderRefundList(@Param("refundNum")String refundNum,@Param("orderNum")String orderNum,
	@Param("refundAmount")Integer refundAmount,@Param("userId")String userId,
	@Param("refundReason")Integer refundReason,@Param("refundDesc")String refundDesc);*/
	public int saveOrderRefundList(Map<String ,String> map);
	
	
}
