/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.ret;

/**
 * @ClassName: RechargeRet
 * @version 1.0
 * @Desc: RechargeRet
 * @author xiaojun.zhou
 * @date 2018年3月23日下午2:32:09
 * @history v1.0
 */
public class RechargeRet extends BaseRet
{

    private static final long serialVersionUID = -6409053504959958741L;

    public String amount;// 充值金额
    public String card_no;// 电子账户
    public String order_no;// 订单编号
    public String url;// 页面跳转url
    public String order_id;// 订单号
    public String fee;// 手续费

    public String getAmount()
    {
        return amount;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public String getCard_no()
    {
        return card_no;
    }

    public void setCard_no(String card_no)
    {
        this.card_no = card_no;
    }

    public String getOrder_no()
    {
        return order_no;
    }

    public void setOrder_no(String order_no)
    {
        this.order_no = order_no;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getOrder_id()
    {
        return order_id;
    }

    public void setOrder_id(String order_id)
    {
        this.order_id = order_id;
    }

    public String getFee()
    {
        return fee;
    }

    public void setFee(String fee)
    {
        this.fee = fee;
    }
}
