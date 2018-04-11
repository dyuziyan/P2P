/******************************************************************************
 * Copyright (C) 2016 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.ret;

/**
* @ClassName: BorrowRet
* @version 1.0 
* @Desc: 标的相关返回实体
* @author shiliang.feng
* @date 2018年3月26日下午3:19:03
* @history v1.0
*
*/
public class BorrowRet extends BaseRet
{

    /**
     * TODO
     */
    private static final long serialVersionUID = -7354556054089768058L;

    
    public String asset_no;//标的编号
    public String asset_brief;//产品描述
    public String card_no;//借款人电子账号
    public String name;//借款人姓名,借款人为企业机构时返回企业名称
    public String amount;//借款金额
    public String loan_term;//项目期限
    public String issue_date;//发标日期，YYYYmmdd
    public String state;//记录状态，0：空标 1：投标 2：流标 3：满标 4：已撤销
    public String warrant_card_no;//担保人电子账号
    public String warrant_name;//担保人姓名 ，担保人为企业机构时返回企业名称
    public String borrow_card_no;//名义借款人电子账号
    public String debtor_card_no;//收款人电子账号
    public String third_custom;//第三方平台保留域
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
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getAmount()
    {
        return amount;
    }
    public void setAmount(String amount)
    {
        this.amount = amount;
    }
    public String getLoan_term()
    {
        return loan_term;
    }
    public void setLoan_term(String loan_term)
    {
        this.loan_term = loan_term;
    }
    public String getIssue_date()
    {
        return issue_date;
    }
    public void setIssue_date(String issue_date)
    {
        this.issue_date = issue_date;
    }
    public String getState()
    {
        return state;
    }
    public void setState(String state)
    {
        this.state = state;
    }
    public String getWarrant_card_no()
    {
        return warrant_card_no;
    }
    public void setWarrant_card_no(String warrant_card_no)
    {
        this.warrant_card_no = warrant_card_no;
    }
    public String getWarrant_name()
    {
        return warrant_name;
    }
    public void setWarrant_name(String warrant_name)
    {
        this.warrant_name = warrant_name;
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
    public String getThird_custom()
    {
        return third_custom;
    }
    public void setThird_custom(String third_custom)
    {
        this.third_custom = third_custom;
    }
    
    
    
}
