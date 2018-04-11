/******************************************************************************
 * Copyright (C) 2016 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wx.depotbank.dto.req.BorrowReq;
import com.wx.depotbank.dto.req.InvestReq;
import com.wx.depotbank.dto.ret.BorrowRet;
import com.wx.depotbank.enums.ServiceEnum;
import com.wx.depotbank.exception.BankException;
import com.wx.depotbank.utils.RequestUtils;

/**
* @ClassName: BorrowServiceImpl
* @version 1.0 
* @Desc: BorrowServiceImpl
* @author shiliang.feng
* @date 2018年3月26日下午4:50:21
* @history v1.0
*
*/
@Service
public class BorrowServiceImpl implements BorrowService
{
    private static Logger logger = LoggerFactory.getLogger(BorrowServiceImpl.class);

    @Override
    public BorrowRet assetsEnroll(BorrowReq req) throws BankException
    {
        req.setService(ServiceEnum.assets_enroll.getKey());
        logger.info("资产登记，BorrowReq = {}", req);
        return RequestUtils.doRequest(req, BorrowRet.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> bidApplyP(InvestReq req) throws BankException
    {
        req.setService(ServiceEnum.bid_apply_p.getKey());
        logger.info("投资申请，InvestReq = {}", req);
        return RequestUtils.doRequest(req, Map.class);
    }

}
