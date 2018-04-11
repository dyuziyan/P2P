/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service.request;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName: ResponceThreadPool
 * @version 1.0
 * @Desc: ResponceThreadPool
 * @author xiaojun.zhou
 * @date 2017年9月19日下午8:33:08
 * @history v1.0
 */
public class ResponceThreadPool
{

    private static AtomicReference<Runnable> runnableList = new AtomicReference<Runnable>();
    private static ExecutorService pool = Executors.newFixedThreadPool(10);

    public static void pull(Runnable runnable)
    {
        runnableList.set(runnable);
        execute();
    }

    private static void execute()
    {
        Runnable runnable;
        while ((runnable = runnableList.getAndSet(null)) != null)
        {
            pool.execute(runnable);
        }
    }
}
