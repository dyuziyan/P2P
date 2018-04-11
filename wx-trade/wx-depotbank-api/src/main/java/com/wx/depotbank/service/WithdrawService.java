/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service;

import java.util.Map;

import com.wx.depotbank.dto.req.WithdrawReq;
import com.wx.depotbank.exception.BankException;

import my.comp.rmi.annotation.RemoteService;

/**
 * @ClassName: WithdrawService
 * @version 1.0
 * @Desc: 提现
 * @author xiaojun.zhou
 * @date 2018年3月27日下午5:54:07
 * @history v1.0
 */
@RemoteService("/withdrawService")
public interface WithdrawService
{
    /**
     * 描述：提现
     * @author xiaojun.zhou
     * @date 2018年3月27日下午5:54:45
     * @param req
     * @return
     * @throws BankException
     */
    public Map<String, Object> withdraw(WithdrawReq req) throws BankException;
}
