/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.enums;

/**
 * @ClassName: BankType
 * @version 1.0
 * @Desc: 银行账户类型
 * @author xiaojun.zhou
 * @date 2018年3月23日上午11:08:01
 * @history v1.0
 */
public enum BankType
{
    private_type("private"), public_type("public");
    private String value;

    private BankType(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}
