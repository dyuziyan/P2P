package com.wx.carloadtrade.dao;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.wx.carloadtrade.dto.OrderRefundDto;
import com.wx.carloadtrade.dto.OrderRefundReasonDto;

import my.comp.dao.mybatis.MybatisDao;

@MybatisDao
public interface OrderRefundDao 
{
	
	/**
	 * 查询退款原因类型
	 * @return
	 */
	public ArrayList<OrderRefundReasonDto> queryOrderRefundReason();
	
	/**
	 * 生成退款单
	 * @param dto
	 * @return
	 */
	public int saveOrderRefundList(OrderRefundDto dto);
	
	/**
	 * 生成退款单-操作日志
	 * @param refundNum 退款订单单号
	 * @param handleUser 操作人
	 * @param refundAmount 退款金额
	 * @param refundReason 退款原因
	 * @return
	 */
	public int saveOrderRefundlog(@Param("refundNum")String refundNum,@Param("handleUser")String handleUser,
			@Param("refundAmount")BigDecimal refundAmount,@Param("refundReason")String refundReason);
	
	
	/**
	 * 生成退款单-退款等有效图片凭证
	 * @param refundNum 退款订单单号
	 * @param picUrlSrc 原图地址
	 * @param serialNumber 序号（排序用）
	 * @return
	 */
	public int saveOrderRefundPicture(@Param("refundNum")String refundNum,@Param("picUrlSrc")String picUrlSrc,
			@Param("serialNumber")String serialNumber);
}
