/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wx.depotbank.dto.req.RechargeCardReq;
import com.wx.depotbank.dto.req.RechargeReq;
import com.wx.depotbank.enums.ServiceEnum;
import com.wx.depotbank.exception.BankException;
import com.wx.depotbank.utils.RequestUtils;

/**
 * @ClassName: RechargeServiceImpl
 * @version 1.0
 * @Desc: 充值
 * @author xiaojun.zhou
 * @date 2018年3月22日下午5:47:35
 * @history v1.0
 */
@Service
public class RechargeServiceImpl implements RechargeService
{
    private static Logger logger = LoggerFactory.getLogger(RechargeServiceImpl.class);

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> recharge(RechargeReq req) throws BankException
    {
        req.setService(ServiceEnum.bank_recharge.getKey());
        logger.info("网关充值，req = {}", req);
        return RequestUtils.doRequest(req, Map.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> rechargeByCard(RechargeCardReq req) throws BankException
    {
        req.setService(ServiceEnum.recharge_p.getKey());
        logger.info("绑定卡到电子账户充值，req = {}", req);
        return RequestUtils.doRequest(req, Map.class);
    }

}
