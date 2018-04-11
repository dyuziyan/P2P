/******************************************************************************
 * Copyright (C) 2015 ShenZhen HeShiDai Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为合时代控股有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.carloadtrade.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * @ClassName: ImPayService
 * @version 1.0
 * @Desc: TODO
 * @history v1.0
 */
public interface ZfbPayService
{
    /**
     * 描述：提交支付
     * @param paramMap
     * @param str
     * @return
     * @throws Exception
     */
    public String subZfbZfPayService(Map<String, String> paramMap);

    /**
     * 描述： 支付宝 异步通知支付结果
     * @param zfbSyncMap
     * @return
     */
    public String zfbZfSyncService(Map<String, String> zfbSyncMap);

    /**
     * 描述： 验证支付结果
     * @param outTradeNo 支付宝订单号
     * @param tradeNo 支付宝交易号
     * @return
     */
    public String zfbCheckZfResultService(String outTradeNo,String  tradeNo);
    
    /**
     * 生成支付流水
     * @param mainOrderNum 主订单号
     * @param body 描述
     * @param orderTotalPrice 订单金额
     * @param payType 支付类型 1：支付宝，2微信
     */
    public String savePaymentSerial(String mainOrderNum, String body, BigDecimal orderTotalPrice,String payType);

    
}
