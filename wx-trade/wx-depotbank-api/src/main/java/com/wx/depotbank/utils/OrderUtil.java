/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.utils;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @ClassName: OrderUtil
 * @version 1.0
 * @Desc: OrderUtil
 * @author xiaojun.zhou
 * @date 2017年6月22日下午5:22:44
 * @history v1.0
 */
public class OrderUtil
{
    private final static long workerId = 2;
    private final static long twepoch = 1288834974657L;
    private static long sequence = 0L;
    private final static long workerIdBits = 4L;
    private final static long sequenceBits = 10L;
    private final static long workerIdShift = sequenceBits;
    private final static long timestampLeftShift = sequenceBits + workerIdBits;
    private final static long sequenceMask = -1L ^ -1L << sequenceBits;
    private static long lastTimestamp = -1L;

    /**
     * 描述：生成唯一的编号
     * @return
     */
    public static synchronized long nextId()
    {
        long timestamp = timeGen();
        if (lastTimestamp == timestamp)
        {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0)
            {
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        else
        {
            sequence = 0;
        }
        if (timestamp < lastTimestamp)
        {
            try
            {
                throw new Exception(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        lastTimestamp = timestamp;
        long nextId = ((timestamp - twepoch << timestampLeftShift)) | (workerId << workerIdShift) | (sequence);
        return nextId;
    }

    public static String nextIdToString()
    {
        return String.valueOf(nextId());
    }

    private static long tilNextMillis(final long lastTimestamp)
    {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp)
        {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private static long timeGen()
    {
        return System.currentTimeMillis();
    }

    public static String timestamp()
    {
        return String.valueOf(timeGen() / 1000);
    }

    public static String uuid()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) throws UnsupportedEncodingException
    {
        System.out.println(OrderUtil.nextId());
        System.out.println(OrderUtil.timestamp().length());
        System.out.println(OrderUtil.uuid().length());
    }
}
