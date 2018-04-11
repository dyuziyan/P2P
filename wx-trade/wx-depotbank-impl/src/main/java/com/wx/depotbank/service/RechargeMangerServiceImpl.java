/******************************************************************************
 * Copyright (C) 2016 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wx.depotbank.dto.req.MarketingReq;
import com.wx.depotbank.dto.ret.MarketingRet;
import com.wx.depotbank.enums.ServiceEnum;
import com.wx.depotbank.exception.BankException;
import com.wx.depotbank.utils.RequestUtils;

/**
* @ClassName: RechargeMangerServiceImpl
* @version 1.0 
* @Desc: TODO
* @author shiliang.feng
* @date 2018年3月28日下午3:02:13
* @history v1.0
*
*/
@Service
public class RechargeMangerServiceImpl implements RechargeMangerService
{

    private static Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
    
    @Override
    public MarketingRet rechargeByMarketing(MarketingReq marketingReq) throws BankException
    {
        marketingReq.setService(ServiceEnum.coupon_recharge.getKey());
        logger.info("营销账户充值，MarketingReq = {}", marketingReq);
        return RequestUtils.doRequest(marketingReq, MarketingRet.class);
    }

    @Override
    public MarketingRet withdrawByMarketing(MarketingReq marketingReq) throws BankException
    {
        marketingReq.setService(ServiceEnum.coupon_withdraw.getKey());
        logger.info("营销账户提现，MarketingReq = {}", marketingReq);
        return RequestUtils.doRequest(marketingReq, MarketingRet.class);
    }

    @Override
    public MarketingRet queryMarketing(MarketingReq marketingReq) throws BankException
    {
        marketingReq.setService(ServiceEnum.marketing_query.getKey());
        logger.info("营销账户查询，MarketingReq = {}", marketingReq);
        return RequestUtils.doRequest(marketingReq, MarketingRet.class);
    }

}
