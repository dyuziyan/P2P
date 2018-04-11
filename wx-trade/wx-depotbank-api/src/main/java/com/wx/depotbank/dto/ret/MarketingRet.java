/******************************************************************************
 * Copyright (C) 2016 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.ret;

/**
* @ClassName: MarketingRet
* @version 1.0 
* @Desc: 营销账户 交易相关返回实体
* @author shiliang.feng
* @date 2018年3月28日下午2:31:57
* @history v1.0
*
*/
public class MarketingRet extends BaseRet
{

    /**
     * TODO
     */
    private static final long serialVersionUID = 195474885317588939L;

    /**
     * 操作金额
     */
    public String amount;
    
    public String serial_no;//交易流水号
  
    public String order_no; //订单编号
    
    /**
     * 操作后账户余额
     */
    public String balance;
    
    public String card_no;//电子账号
    
    public String customer_no;//客户号
    
    public String name;//客户姓名
    
    public String cert_no;//客户证件
    
    public String date;//开户日期
    
    public String frozen_money;//账户冻结余额
    
    public String state;//账户状态 0 正常；1 冻结；2 注销
    
    public String type;//营销户类型，1：服务费账户；2：红包账户

    public String getAmount()
    {
        return amount;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public String getSerial_no()
    {
        return serial_no;
    }

    public void setSerial_no(String serial_no)
    {
        this.serial_no = serial_no;
    }

    public String getOrder_no()
    {
        return order_no;
    }

    public void setOrder_no(String order_no)
    {
        this.order_no = order_no;
    }

    public String getBalance()
    {
        return balance;
    }

    public void setBalance(String balance)
    {
        this.balance = balance;
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCert_no()
    {
        return cert_no;
    }

    public void setCert_no(String cert_no)
    {
        this.cert_no = cert_no;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getFrozen_money()
    {
        return frozen_money;
    }

    public void setFrozen_money(String frozen_money)
    {
        this.frozen_money = frozen_money;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
    
    
    
    
}
