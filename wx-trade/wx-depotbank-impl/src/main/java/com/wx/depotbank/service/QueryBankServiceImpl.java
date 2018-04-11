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

import com.wx.depotbank.dto.req.AccountReq;
import com.wx.depotbank.dto.req.InvestReq;
import com.wx.depotbank.dto.req.OrderQueryReq;
import com.wx.depotbank.dto.ret.AccountRet;
import com.wx.depotbank.enums.ServiceEnum;
import com.wx.depotbank.exception.BankException;
import com.wx.depotbank.utils.RequestUtils;

/**
 * @ClassName: QueryBankServiceImpl
 * @version 1.0
 * @Desc: QueryBankServiceImpl
 * @author xiaojun.zhou
 * @date 2018年3月28日下午9:59:39
 * @history v1.0
 */
@SuppressWarnings("all")
@Service
public class QueryBankServiceImpl implements QueryBankService
{
    private static Logger logger = LoggerFactory.getLogger(QueryBankServiceImpl.class);

    @Override
    public Map<String, Object> moneyQuery(OrderQueryReq req) throws BankException
    {
        req.setService(ServiceEnum.money_query.getKey());
        logger.info("资金交易状态查询,req={}", req);
        return RequestUtils.doRequest(req, Map.class);
    }

    @Override
    public AccountRet queryCreateAccount(OrderQueryReq req) throws BankException
    {
        req.setService(ServiceEnum.create_account_sr_query.getKey());
        logger.info("开户结果查询，req = {}", req);
        return RequestUtils.doRequest(req, AccountRet.class);
    }

    @Override
    public Map<String, Object> querySetPassword(OrderQueryReq req) throws BankException
    {
        req.setService(ServiceEnum.set_password_query.getKey());
        logger.info("网关重置密码查询开户结果查询，req = {}", req);
        return RequestUtils.doRequest(req, Map.class);
    }

    @Override
    public Map<String, Object> accountBalance(AccountReq req) throws BankException
    {
        req.setService(ServiceEnum.account_balance.getKey());
        logger.info("电子账户余额查询，req = {}", req);
        return RequestUtils.doRequest(req, Map.class);
    }

    @Override
    public Map<String, Object> applyBiduery(InvestReq req) throws BankException
    {
        req.setService(ServiceEnum.apply_bid_query.getKey());
        logger.info("投资人投标申请查询，req = {}", req);
        return RequestUtils.doRequest(req, Map.class);
    }
}
