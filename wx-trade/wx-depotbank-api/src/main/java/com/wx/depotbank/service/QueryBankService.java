/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service;

import java.util.Map;

import com.wx.depotbank.dto.req.AccountReq;
import com.wx.depotbank.dto.req.InvestReq;
import com.wx.depotbank.dto.req.OrderQueryReq;
import com.wx.depotbank.dto.ret.AccountRet;
import com.wx.depotbank.exception.BankException;

import my.comp.rmi.annotation.RemoteService;

/**
 * @ClassName: QueryBankService
 * @version 1.0
 * @Desc: QueryBankService
 * @author xiaojun.zhou
 * @date 2018年3月28日下午9:57:05
 * @history v1.0
 */
@RemoteService("/queryBankService")
public interface QueryBankService
{
    /**
     * 描述：资金交易状态查询
     * @author xiaojun.zhou
     * @date 2018年3月28日下午10:00:55
     * @param req
     * @return
     */
    public Map<String, Object> moneyQuery(OrderQueryReq req) throws BankException;

    /**
     * 描述：开户结果查询
     * @author xiaojun.zhou
     * @date 2018年3月21日下午5:46:55
     * @param req
     * @return
     * @throws BankException
     */
    public AccountRet queryCreateAccount(OrderQueryReq req) throws BankException;

    /**
     * 描述：网关重置密码查询
     * @author xiaojun.zhou
     * @date 2018年3月21日下午6:02:20
     * @param req
     * @return
     * @throws BankException
     */
    public Map<String, Object> querySetPassword(OrderQueryReq req) throws BankException;

    /**
     * 描述：电子账户余额查询
     * @author xiaojun.zhou
     * @date 2018年3月28日下午5:49:12
     * @param req
     * @return
     * @throws BankException
     */
    public Map<String, Object> accountBalance(AccountReq req) throws BankException;

    /**
     * 描述：投资人投标申请查询
     * @author xiaojun.zhou
     * @date 2018年4月3日下午4:51:11
     * @param req
     * @return
     * @throws BankException
     */
    public Map<String, Object> applyBiduery(InvestReq req) throws BankException;
}
