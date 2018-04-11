package com.wx.carloadtrade.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.wx.carloadtrade.dto.ShopsPaymentDto;
import com.wx.service.Result;

public interface ShopsPaymentService {
	
	/**
	 * 获取商家支付方式参数
	 * @param dto
	 * @return
	 */
	public  Result<ShopsPaymentDto> getShopsPayment(ShopsPaymentDto dto);
	
	/**
	 * 生成支付流水
	 * @param mainOrderNum 主订单号
	 * @param payTypeName 支付方式名称
	 * @param payTypeCode 支付方式代号
	 * @param userId 用户编号
	 * @return
	 */
	public Result<Object> savePaymentSerial(String mainOrderNum,String payTypeName,String payTypeCode,String userId);
	
	
	/**
	 * 生成退款单
	 * @param mainOrderNum 主订单号
	 * @param refundReason 退款原因
	 * @param userId 用户编号
	 * @param refundDesc 问题描述
	 * @return
	 */
	public Result<String> saveOrderRefundList(String mainOrderNum,String refundReason,String userId,String refundDesc);
	
	/**
	 * 生成退款单-操作日志
	 * @param refundNum 退款订单单号
	 * @param handleUser 操作人
	 * @param refundAmount 退款金额
	 * @param refundReason 退款原因
	 * @return
	 */
	public Result<String> saveOrderRefundlog(String refundNum,String handleUser,BigDecimal refundAmount,String refundReason);
	
	
	/**
	 * 生成退款单-退款等有效图片凭证
	 * @param refundNum 退款订单单号
	 * @param picUrlSrc 原图地址
	 * @param serialNumber 序号（排序用）
	 * @return
	 */
	public Result<String> saveOrderRefundPicture(String refundNum,String picUrlSrc,String serialNumber);
	
	/**
	 * 查询支付方式
	 * @return
	 */
	public Result<List<Map>> queryPaymentList();
	
	 /**
     * 查询支付流水
     * @param payNum 支付流水单号/交易单号
     * @param mainOrderNum 主订单号码
     * @param payState 支付状态 0:支付中 1:支付成功 2:支付失败
     * @param payTypeCode 支付方式代号
     * @return
     */
    public List<Map<String,String>> queryPaymentSerial(String payNum,String mainOrderNum,String payState,String payTypeCode);
    /**
     * 更新支付流水状态
     * 描述：TODO
     * @author qingyan.wu 
     * @date 2018年3月28日上午9:36:11
     * @param payNum
     * @param payState
     * @return
     */
    public int udpateMainPaymentSerial(String payNum,String payState);
	
}
