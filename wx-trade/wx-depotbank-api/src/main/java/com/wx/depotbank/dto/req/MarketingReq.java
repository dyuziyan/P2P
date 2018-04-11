/******************************************************************************
 * Copyright (C) 2016 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.req;

/**
* @ClassName: MarketingReq
* @version 1.0 
* @Desc: 营销户相关请求实体
* @author shiliang.feng
* @date 2018年3月28日下午2:31:31
* @history v1.0
*
*/
public class MarketingReq extends BaseReq
{

    /**
     * TODO
     */
    private static final long serialVersionUID = -8652293962850424323L;

    public String card_no;
    
    public String type;//1：服务费账户；2：红包户

    public String amount;//交易金额

    public String serial_no;
    
    
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

    public String getCard_no()
    {
        return card_no;
    }

    public void setCard_no(String card_no)
    {
        this.card_no = card_no;
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
