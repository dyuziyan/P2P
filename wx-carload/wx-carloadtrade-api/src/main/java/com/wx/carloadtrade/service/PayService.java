package com.wx.carloadtrade.service;

import java.math.BigDecimal;
import java.util.Map;

import com.wx.service.Result;

public interface PayService {
	
	
	/**
	 * 提交支付
	 * @return
	 */
	public Result<String> subZfbZfPayService(Map<String, String> paramMap);
	
	/**
	 * 支付宝异步通知
	 * @param zfbSyncMap
	 * @return
	 */
	public Result<String> zfbZfSyncService(Map<String, String> zfbSyncMap);
	
	/**
	 * * 支付宝同步回调时客户端主动查询支付结果方法 查询该订单是否支付成功 1:成功 -1:不成功
	 * @param outTradeNo 支付宝订单号
	 * @param tradeNo 支付宝交易号
	 * @return
	 */
	public Result<String> zfbSynchroNotify(String outTradeNo,String  tradeNo);
	
	  /**
     * 生成支付流水
     * @param mainOrderNum 主订单号
     * @param body 描述
     * @param orderTotalPrice 订单金额
     * @param payType 支付类型1：支付宝，2：微信
     */
    public String savePaymentSerial(String mainOrderNum, String body, BigDecimal orderTotalPrice,String payType);
    
   
	
}
