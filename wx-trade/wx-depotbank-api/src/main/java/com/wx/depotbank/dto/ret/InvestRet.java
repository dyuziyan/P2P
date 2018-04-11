/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.ret;

/**
 * @ClassName: InvestRet
 * @version 1.0
 * @Desc: InvestRet
 * @author xiaojun.zhou
 * @date 2018年4月3日下午5:39:22
 * @history v1.0
 */
public class InvestRet extends BaseRet
{

    private static final long serialVersionUID = 5276239482506248078L;

    public String out_serial_no; // 申请流水号,(32)位数
    public String card_no; // 电子账户 ,(19)位数
    public String asset_no; // 标的编号,(40)位数
    public String bid_amount; // 投标金额,13位保留两位
    public String forcast_income; // 预期收益,13位保留两位
    public String buy_date; // 投标日期,(8)位数
    public String state; // 记录状态,1：投标中 2：放款中 3：计息中 4：本息已返回还
    public String auth_code; // 投标申请授权码,(20)位数
    public String third_custom; // 第三方保留域,(100)位数

    public String getOut_serial_no()
    {
        return out_serial_no;
    }

    public void setOut_serial_no(String out_serial_no)
    {
        this.out_serial_no = out_serial_no;
    }

    public String getCard_no()
    {
        return card_no;
    }

    public void setCard_no(String card_no)
    {
        this.card_no = card_no;
    }

    public String getAsset_no()
    {
        return asset_no;
    }

    public void setAsset_no(String asset_no)
    {
        this.asset_no = asset_no;
    }

    public String getBid_amount()
    {
        return bid_amount;
    }

    public void setBid_amount(String bid_amount)
    {
        this.bid_amount = bid_amount;
    }

    public String getForcast_income()
    {
        return forcast_income;
    }

    public void setForcast_income(String forcast_income)
    {
        this.forcast_income = forcast_income;
    }

    public String getBuy_date()
    {
        return buy_date;
    }

    public void setBuy_date(String buy_date)
    {
        this.buy_date = buy_date;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getAuth_code()
    {
        return auth_code;
    }

    public void setAuth_code(String auth_code)
    {
        this.auth_code = auth_code;
    }

    public String getThird_custom()
    {
        return third_custom;
    }

    public void setThird_custom(String third_custom)
    {
        this.third_custom = third_custom;
    }
}
