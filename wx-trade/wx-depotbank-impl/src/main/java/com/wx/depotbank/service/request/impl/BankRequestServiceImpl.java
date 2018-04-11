/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service.request.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wx.depotbank.dao.BankRequestDao;
import com.wx.depotbank.enums.ServiceEnum;
import com.wx.depotbank.service.request.BankRequestService;

import my.comp.lang.DateUtils;
import my.comp.lang.StringUtils;
import my.comp.sn.SnBuilder;

/**
 * @ClassName: BankRequestServiceImpl
 * @version 1.0
 * @Desc: BankRequestServiceImpl
 * @author xiaojun.zhou
 * @date 2018年3月20日下午4:35:21
 * @history v1.0
 */
@Service
public class BankRequestServiceImpl implements BankRequestService
{

    private static final Logger logger = LoggerFactory.getLogger(BankRequestServiceImpl.class);

    @Resource
    private BankRequestDao bankRequestDao;

    @Override
    public int saveRequest(Map<String, Object> requestParams)
    {
        try
        {
            logger.info("保存请求信息");
            Map<String, Object> params = new HashMap<String, Object>();
            params.putAll(requestParams);
            ServiceEnum se = ServiceEnum.get(requestParams.get("service") + "");
            params.put("service_desc", se.getValue());
            params.put("request_data", requestParams.toString());
            String custom = requestParams.get("custom") + "";
            params.put("custom", custom);

            String card_no = requestParams.get("card_no") + "";
            // 生成serialNumber
            String serialNumber = StringUtils.EMPTY;
            if (!StringUtils.isNull(card_no))
            {
                serialNumber = SnBuilder.create(card_no, se.getKey(), DateUtils.formatDate(new Date(), "yyyyMMddHHmm")).toString();
            }
            else if (ServiceEnum.create_account_p == se)
            {
                // 开户custom==userId
                serialNumber = SnBuilder.create(custom, se.getKey(), DateUtils.formatDate(new Date(), "yyyyMMddHHmm")).toString();
            }
            // 判断是否重复提交
            if (!StringUtils.isNull(serialNumber))
            {
                // 查询存在的记录数
                int count = bankRequestDao.queryBankRequestBySerialNumber(serialNumber);
                if (count > 2)
                {
                    // 一分钟之内不能提交超过3次
                    logger.info("提交太频繁，{}提交次数{}", serialNumber, count);
                    return -2;
                }
            }
            params.put("serialNumber", serialNumber);
            bankRequestDao.saveBankRequest(params);
            return Integer.valueOf(params.get("id") + "");
        }
        catch (Exception e)
        {
            logger.error("保存银行请求信息失败", e);
            return -1;
        }
    }

    @Override
    public void saveResponse(JSONObject responseData)
    {
        try
        {
            logger.info("保存银行返回信息");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("uuid", responseData.getString("uuid"));
            params.put("order_id", responseData.getString("order_id"));
            params.put("sequence_id", responseData.getString("sequence_id"));
            String respCode = responseData.getString("code");
            String respDesc = responseData.getString("msg");
            params.put("responce_code", respCode);
            params.put("responce_msg", respDesc);
            params.put("responce_data", responseData.toString());
            bankRequestDao.saveBankResponse(params);
        }
        catch (Exception e)
        {
            logger.error("保存银行返回信息失败", e);
        }
    }

}
