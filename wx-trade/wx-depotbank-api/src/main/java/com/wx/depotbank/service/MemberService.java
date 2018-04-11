/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service;

import java.util.Map;

import com.wx.depotbank.dto.req.AccountReq;
import com.wx.depotbank.dto.req.BindBankCardReq;
import com.wx.depotbank.exception.BankException;

import my.comp.rmi.annotation.RemoteService;

/**
 * @ClassName: MemberService
 * @version 1.0
 * @Desc: MemberService
 * @author xiaojun.zhou
 * @date 2018年3月19日下午3:34:44
 * @history v1.0
 */
@RemoteService("/memberService")
public interface MemberService
{
    /**
     * 描述：开户
     * @author xiaojun.zhou
     * @date 2018年3月19日下午5:32:59
     * @param req
     * @return
     * @throws BankException
     */
    public Map<String, Object> createAccount(AccountReq req) throws BankException;

    /**
     * 描述：绑定银行卡
     * @author shiliang.feng
     * @date 2018年3月20日下午2:27:48
     * @param bankCardReq
     * @return
     * @throws BankException
     */
    public Map<String, Object> bindBankCard(BindBankCardReq bankCardReq) throws BankException;

    /**
     * 描述：解绑银行卡
     * @author shiliang.feng
     * @date 2018年3月20日下午2:27:48
     * @param bankCardReq
     * @return
     * @throws BankException
     */
    public Map<String, Object> unBindBankCard(BindBankCardReq bankCardReq) throws BankException;

    /**
     * 描述：重置密码
     * @author shiliang.feng
     * @date 2018年3月21日下午3:34:13
     * @param req
     * @return
     * @throws BankException
     */
    public Map<String, Object> resetPassword(AccountReq req) throws BankException;

    /**
     * 描述：修改手机号码
     * @author shiliang.feng
     * @date 2018年3月21日下午3:34:29
     * @param req
     * @return
     * @throws BankException
     */
    public Map<String, Object> changeMobile(AccountReq req) throws BankException;

}