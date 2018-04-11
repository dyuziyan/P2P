/******************************************************************************
 * Copyright (C) 2016 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service;

import java.util.Map;

import com.wx.depotbank.dto.req.BorrowReq;
import com.wx.depotbank.dto.req.InvestReq;
import com.wx.depotbank.dto.ret.BorrowRet;
import com.wx.depotbank.exception.BankException;

import my.comp.rmi.annotation.RemoteService;

/**
 * @ClassName: BorrowService
 * @version 1.0
 * @Desc: BorrowService
 * @author shiliang.feng
 * @date 2018年3月26日下午4:48:35
 * @history v1.0
 */
@RemoteService("/borrowService")
public interface BorrowService
{
    /**
     * 描述：资产登记
     * @author shiliang.feng
     * @date 2018年3月26日下午4:55:25
     * @return
     * @throws BankException
     */
    public BorrowRet assetsEnroll(BorrowReq req) throws BankException;

    /**
     * 描述：投资申请
     * @author xiaojun.zhou
     * @date 2018年4月3日下午2:47:41
     * @param req
     * @return
     * @throws BankException
     */
    public Map<String, Object> bidApplyP(InvestReq req) throws BankException;
    
}
