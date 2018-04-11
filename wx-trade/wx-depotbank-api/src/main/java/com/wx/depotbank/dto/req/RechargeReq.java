/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.req;

/**
 * @ClassName: RechargeReq
 * @version 1.0
 * @Desc: RechargeReq
 * @author xiaojun.zhou
 * @date 2018年3月22日下午5:35:42
 * @history v1.0
 */
public class RechargeReq extends BaseReq
{

    private static final long serialVersionUID = 452478712767248828L;

    public String bank_type;// 账户类型 private（个人银行卡）,public（企业银行卡）默认public（企业银行卡）
    public String card_no;// 电子账户
    public String customer_no;// 客户号，条件选填
    public String redirect_url;// 支付成功跳转url
    public String product_name;// 产品名称，条件选填
    public String product_detail;// 产品详情，条件选填
    public String order_no;// 订单号，必填
    public String bank_name;// 银行名称，必填
    public String bank_id_no;// 银行代码，必填
    public String amount;// 充值金额，必填

    public String getBank_type()
    {
        return bank_type;
    }

    public void setBank_type(String bank_type)
    {
        this.bank_type = bank_type;
    }

    public String getCard_no()
    {
        return card_no;
    }

    public void setCard_no(String card_no)
    {
        this.card_no = card_no;
    }

    public String getCustomer_no()
    {
        return customer_no;
    }

    public void setCustomer_no(String customer_no)
    {
        this.customer_no = customer_no;
    }

    public String getRedirect_url()
    {
        return redirect_url;
    }

    public void setRedirect_url(String redirect_url)
    {
        this.redirect_url = redirect_url;
    }

    public String getProduct_name()
    {
        return product_name;
    }

    public void setProduct_name(String product_name)
    {
        this.product_name = product_name;
    }

    public String getProduct_detail()
    {
        return product_detail;
    }

    public void setProduct_detail(String product_detail)
    {
        this.product_detail = product_detail;
    }

    public String getOrder_no()
    {
        return order_no;
    }

    public void setOrder_no(String order_no)
    {
        this.order_no = order_no;
    }

    public String getBank_name()
    {
        return bank_name;
    }

    public void setBank_name(String bank_name)
    {
        this.bank_name = bank_name;
    }

    public String getBank_id_no()
    {
        return bank_id_no;
    }

    public void setBank_id_no(String bank_id_no)
    {
        this.bank_id_no = bank_id_no;
    }

    public String getAmount()
    {
        return amount;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    @Override
    public String toString()
    {
        return super.toString() + "RechargeReq [bank_type=" + bank_type + ", card_no=" + card_no + ", customer_no=" + customer_no + ", redirect_url=" + redirect_url + ", product_name=" + product_name
            + ", product_detail=" + product_detail + ", order_no=" + order_no + ", bank_name=" + bank_name + ", bank_id_no=" + bank_id_no + ", amount=" + amount + "]";
    }
}
