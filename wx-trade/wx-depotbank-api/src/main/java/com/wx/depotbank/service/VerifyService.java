/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service;

import my.comp.rmi.annotation.RemoteService;

/**
 * @ClassName: VerifyService
 * @version 1.0
 * @Desc: VerifyService
 * @author xiaojun.zhou
 * @date 2018年3月19日下午5:17:17
 * @history v1.0
 */
@RemoteService("/verifyService")
public interface VerifyService
{
    /**
     * 描述：银行验签
     * @author xiaojun.zhou
     * @date 2018年3月20日下午8:22:41
     * @param result 银行返回的字符串
     * @return
     */
    public boolean verifySign(String result);
}
