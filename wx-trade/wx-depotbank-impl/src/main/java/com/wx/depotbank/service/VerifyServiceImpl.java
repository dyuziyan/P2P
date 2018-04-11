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

import com.wx.depotbank.common.BankConstant;
import com.wx.depotbank.utils.SignUtils;

/**
 * @ClassName: VerifyServiceImpl
 * @version 1.0
 * @Desc: VerifyServiceImpl
 * @author xiaojun.zhou
 * @date 2018年3月19日下午5:20:46
 * @history v1.0
 */
@Service
public class VerifyServiceImpl implements VerifyService
{
    private static final Logger logger = LoggerFactory.getLogger(VerifyServiceImpl.class);

    @Override
    public boolean verifySign(String result)
    {
        logger.info("验签字符串result:{}", result);
        Map<String, Object> resMap = SignUtils.convertJsonToMap(result);
        String signature = (String) resMap.get("sign");
        resMap.remove("sign");
        if (SignUtils.signVerify(BankConstant.RFT_KEY, BankConstant.RFT_SECRET, resMap, signature))
        {
            logger.info("验签成功");
            return true;
        }
        return false;
    }

}
