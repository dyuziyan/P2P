package com.wx.carloadbase.service;

public interface LogService {
	
	/**
	 * 保存用户操作日志
	 * @param userId 用户编号
	 * @param ipAddress 操作ip
	 * @param operateType 操作类型
	 * @param operateDesc 操作描述
	 */
	public int saveUserOperateLog(Long userId,String ipAddress,Long operateType,String operateDesc);
	
	/**
	 * 保存用户登录日志
	 * @param userId 用户编号
	 * @param ipAddress 操作ip
	 * @param platformType 	平台类型 1：PC 2：微信 3：苹果APP 4：安卓APP
	 * @return
	 */
	public int saveUserLoginLog(Long userId,String ipAddress,String platformType);
	
	/**
	 * 养护-订单操作日志
	 * @param mainOrderNum 主订单号
	 * @param orderNum 养护订单号
	 * @param handleType 操作类型  1待支付，2已取消（包含用户自动取消和订单超时取消），3服务中，4退款中（用户发起退款），5退款已取消（用户取消退款），6退款已拒绝，7已退款，8已完成
	 * @param remark 备注
	 * @return
	 */
	public int saveCareOrderLog(String mainOrderNum,String orderNum,Integer handleType,String remark);
	
	/**
	 * 保养-订单操作日志
	 * @param mainOrderNum 主订单号
	 * @param orderNum 养护订单号
	 * @param handleType 操作类型  1待支付，2已取消（包含用户自动取消和订单超时取消），3服务中，4退款中（用户发起退款），5退款已取消（用户取消退款），6退款已拒绝，7已退款，8已完成
	 * @param remark 备注
	 * @return
	 */
	public int saveMaintainOrderLog(String mainOrderNum,String orderNum,Integer handleType,String remark);
	
	/**
	 * 维修-订单操作日志
	 * @param mainOrderNum 主订单号
	 * @param orderNum 养护订单号
	 * @param handleType 操作类型  1待支付，2已取消（包含用户自动取消和订单超时取消），3服务中，4退款中（用户发起退款），5退款已取消（用户取消退款），6退款已拒绝，7已退款，8已完成
	 * @param remark 备注
	 * @return
	 */
	public int saveRepairOrderLog(String mainOrderNum,String orderNum,Integer handleType,String remark);
}
