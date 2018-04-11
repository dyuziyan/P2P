/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service.request;

import com.alibaba.fastjson.JSONObject;
import com.wx.depotbank.utils.SpringSupport;

/**
 * @ClassName: ResponceThread
 * @version 1.0
 * @Desc: ResponceThread
 * @author xiaojun.zhou
 * @date 2017年6月27日下午5:37:49
 * @history v1.0
 */
public class ResponceThread implements Runnable
{
    private JSONObject responseData;

    public ResponceThread(JSONObject responseData)
    {
        this.responseData = responseData;
    }

    @Override
    public void run()
    {
        BankRequestService bankRequestService = (BankRequestService) SpringSupport.getBean("bankRequestServiceImpl");
        // 保存响应信息
        bankRequestService.saveResponse(responseData);
    }
}
