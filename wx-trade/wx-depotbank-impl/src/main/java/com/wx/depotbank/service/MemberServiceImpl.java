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
import com.wx.depotbank.dto.req.BindBankCardReq;
import com.wx.depotbank.dto.req.OrderQueryReq;
import com.wx.depotbank.dto.ret.AccountRet;
import com.wx.depotbank.enums.ServiceEnum;
import com.wx.depotbank.exception.BankException;
import com.wx.depotbank.utils.RequestUtils;

/**
 * @ClassName: MemberServiceImpl
 * @version 1.0
 * @Desc: MemberServiceImpl
 * @author xiaojun.zhou
 * @date 2018年3月19日下午5:27:16
 * @history v1.0
 */
@SuppressWarnings("all")
@Service
public class MemberServiceImpl implements MemberService
{
    private static Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Override
    public Map<String, Object> createAccount(AccountReq req) throws BankException
    {
        req.setService(ServiceEnum.create_account_p.getKey());
        logger.info("个人开户接口(页面)，req = {}", req);
        return RequestUtils.doRequest(req, Map.class);
    }

    @Override
    public Map<String, Object> bindBankCard(BindBankCardReq bankCardReq) throws BankException
    {
        bankCardReq.setService(ServiceEnum.BindBankCard.getKey());
        logger.info("绑定银行卡，BindBankCardReq = {}", bankCardReq);
        return RequestUtils.doRequest(bankCardReq, Map.class);
    }

    @Override
    public Map<String, Object> unBindBankCard(BindBankCardReq bankCardReq) throws BankException
    {
        bankCardReq.setService(ServiceEnum.UnBindBankCard.getKey());
        logger.info("解绑银行卡，BindBankCardReq = {}", bankCardReq);
        return RequestUtils.doRequest(bankCardReq, Map.class);
    }

    @Override
    public Map<String, Object> resetPassword(AccountReq req) throws BankException
    {
        req.setService(ServiceEnum.reset_password.getKey());
        logger.info("重置密码，UserReq = {}", req);
        return RequestUtils.doRequest(req, Map.class);
    }

    @Override
    public Map<String, Object> changeMobile(AccountReq req) throws BankException
    {
        req.setService(ServiceEnum.change_mobile.getKey());
        logger.info("修改手机号码，UserReq = {}", req);
        return RequestUtils.doRequest(req, Map.class);
    }
}
