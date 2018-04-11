/******************************************************************************
 * Copyright (C) 2016 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.req;

/**
 * @ClassName: BorrowReq
 * @version 1.0
 * @Desc: 标的相关请求实体
 * @author shiliang.feng
 * @date 2018年3月26日下午3:14:36
 * @history v1.0
 */
public class BorrowReq extends BaseReq
{

    /**
     * TODO
     */
    private static final long serialVersionUID = 610018837829120154L;

    public String asset_no;// 标的编号
    public String asset_brief;// 标的描述，必填，理财产品中文描述
    public String card_no;// 借款人电子账号，必填
    public String amount;// 借款金额，必填，两位小数
    public String interest_type;// 付息方式 ,必填
                                // 1：等额本息；2：每月付息，到期还本；3：等额本金；4：等比累进；5：等额累进；6：组合还款；7：其他，
    public String interest_day;// 利息每月支付日, 条件选填 ，DD ，付息方式为2时必填；
    public String loan_term;// 项目期限，必填，单位为天
    public String interest_rate;// 预计年化收益率，必填，五位小数 如年化收益率为10%，需上送10.00000
    public String warrant_card_no;// 担保人电子账号，条件选填
    public String borrow_card_no;// 名义借款人电子账户，条件选填，名义借款人电子账号
    public String debtor_card_no;// 收款人电子账户，条件选填，多种借款人模式下必送
    public String trustee_pay_flag;// 标的类型，必填，1：普通；2：受托支付
    public String third_custom;// 第三方平台保留域，条件选填，第三方平台保留使用，原样返回

    public String getAsset_no()
    {
        return asset_no;
    }

    public void setAsset_no(String asset_no)
    {
        this.asset_no = asset_no;
    }

    public String getAsset_brief()
    {
        return asset_brief;
    }

    public void setAsset_brief(String asset_brief)
    {
        this.asset_brief = asset_brief;
    }

    public String getCard_no()
    {
        return card_no;
    }

    public void setCard_no(String card_no)
    {
        this.card_no = card_no;
    }

    public String getAmount()
    {
        return amount;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public String getInterest_type()
    {
        return interest_type;
    }

    public void setInterest_type(String interest_type)
    {
        this.interest_type = interest_type;
    }

    public String getInterest_day()
    {
        return interest_day;
    }

    public void setInterest_day(String interest_day)
    {
        this.interest_day = interest_day;
    }

    public String getLoan_term()
    {
        return loan_term;
    }

    public void setLoan_term(String loan_term)
    {
        this.loan_term = loan_term;
    }

    public String getInterest_rate()
    {
        return interest_rate;
    }

    public void setInterest_rate(String interest_rate)
    {
        this.interest_rate = interest_rate;
    }

    public String getWarrant_card_no()
    {
        return warrant_card_no;
    }

    public void setWarrant_card_no(String warrant_card_no)
    {
        this.warrant_card_no = warrant_card_no;
    }

    public String getBorrow_card_no()
    {
        return borrow_card_no;
    }

    public void setBorrow_card_no(String borrow_card_no)
    {
        this.borrow_card_no = borrow_card_no;
    }

    public String getDebtor_card_no()
    {
        return debtor_card_no;
    }

    public void setDebtor_card_no(String debtor_card_no)
    {
        this.debtor_card_no = debtor_card_no;
    }

    public String getTrustee_pay_flag()
    {
        return trustee_pay_flag;
    }

    public void setTrustee_pay_flag(String trustee_pay_flag)
    {
        this.trustee_pay_flag = trustee_pay_flag;
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
