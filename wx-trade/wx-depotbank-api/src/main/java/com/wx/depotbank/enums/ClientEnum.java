/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.enums;

/**
 * @ClassName: ClientEnum
 * @version 1.0
 * @Desc: ClientEnum
 * @author xiaojun.zhou
 * @date 2018年3月19日下午5:55:09
 * @history v1.0
 */
public enum ClientEnum
{
    APP("APP", "000001"), WEB("WEB", "000002"), WX("WX", "000003"), COUNTER("COUNTER", "000004");
    private ClientEnum(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    private String key;
    private String value;

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }
    
    public static ClientEnum get(String key)
    {
        ClientEnum[] codes = ClientEnum.values();
        for (ClientEnum code : codes)
        {
            if (code.getKey().equals(key))
            {
                return code;
            }
        }
        return ClientEnum.WEB;
    }
}
