package com.wx.carloadbase.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.wx.carloadbase.dto.AdvertDto;

import my.comp.dao.mybatis.MybatisDao;

@MybatisDao
public interface LogDao 
{
	/**
	 * 保存用户操作日志
	 * @param userId 用户编号
	 * @param ipAddress 操作ip
	 * @param operateType 操作类型
	 * @param operateDesc 操作描述
	 */
	
	public int saveUserOperateLog(@Param("userId")Long userId,@Param("ipAddress")String ipAddress,
			@Param("operateType")Long operateType,@Param("operateDesc")String operateDesc);
	
	/**
	 * 保存用户登录日志
	 * @param userId 用户编号
	 * @param ipAddress 操作ip
	 * @param platformType 	平台类型 1：PC 2：微信 3：苹果APP 4：安卓APP
	 * @return
	 */
	public int saveUserLoginLog(@Param("userId")Long userId,@Param("ipAddress")String ipAddress,@Param("platformType")String platformType);
	
	
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
	 * @param orderNum 养护订单号
	 * @param handleType 操作类型  1待支付，2已取消（包含用户自动取消和订单超时取消），3服务中，4退款中（用户发起退款），5退款已取消（用户取消退款），6退款已拒绝，7已退款，8已完成
	 * @param remark 备注
	 * @return
	 */
	public int saveMaintainOrderLog(@Param("mainOrderNum")String mainOrderNum,@Param("orderNum")String orderNum,@Param("handleType")Integer handleType,@Param("remark")String remark);
	
	/**
	 * 维修-订单操作日志
	 * @param mainOrderNum 主订单号
	 * @param orderNum 养护订单号
	 * @param handleType 操作类型 1待支付，2已取消（包含用户自动取消和订单超时取消），3服务中，4退款中（用户发起退款），5退款已取消（用户取消退款），6退款已拒绝，7已退款，8已完成
	 * @param remark 备注
	 * @return
	 */
	public int saveRepairOrderLog(@Param("mainOrderNum")String mainOrderNum,@Param("orderNum")String orderNum,@Param("handleType")Integer handleType,@Param("remark")String remark);
		
}
