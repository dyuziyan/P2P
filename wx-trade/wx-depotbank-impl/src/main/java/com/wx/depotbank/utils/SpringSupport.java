/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationObjectSupport;

/**
 * @ClassName: SpringSupport
 * @version 1.0
 * @Desc: SpringSupport
 * @author xiaojun.zhou
 * @date 2017年6月27日下午4:58:16
 * @history v1.0
 */
@Component
public class SpringSupport extends WebApplicationObjectSupport
{

    private static ApplicationContext applicationContext = null;

    @Override
    protected void initApplicationContext(ApplicationContext context)
    {
        super.initApplicationContext(context);

        if (applicationContext == null)
        {
            applicationContext = context;
        }
    }

    public static ApplicationContext getAppContext()
    {
        return applicationContext;
    }

    public static Object getBean(String name)
    {
        try
        {
            return applicationContext.getBean(name);
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
