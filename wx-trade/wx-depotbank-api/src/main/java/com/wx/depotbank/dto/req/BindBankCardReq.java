/******************************************************************************
 * Copyright (C) 2016 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.req;

/**
 * @ClassName: BindBankCardReq
 * @version 1.0
 * @Desc: 绑定(解绑)银行卡 请求实体
 * @author shiliang.feng
 * @date 2018年3月20日下午2:18:42
 * @history v1.0
 */
public class BindBankCardReq extends BaseReq
{

    /**
     * TODO
     */
    private static final long serialVersionUID = 7225118246794655912L;

    public String card_no;// 电子账号

    public String bank_card_no;// 绑定卡号

    public String bank_id_no;// 银行编码

    public String name;// 姓名

    public String cert_type;// 证件类型

    public String customer_no;// 客户号

    public String cert_no;// 证件号码

    public String card_type;// 主副卡类型 ,0,位主卡

    public String bank_mobile;// 绑定卡手机号码

    public String user_ip;// 客户IP

    public String serial_no;// 三方绑定编号

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

    public String getBank_card_no()
    {
        return bank_card_no;
    }

    public void setBank_card_no(String bank_card_no)
    {
        this.bank_card_no = bank_card_no;
    }

    public String getBank_id_no()
    {
        return bank_id_no;
    }

    public void setBank_id_no(String bank_id_no)
    {
        this.bank_id_no = bank_id_no;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCert_type()
    {
        return cert_type;
    }

    public void setCert_type(String cert_type)
    {
        this.cert_type = cert_type;
    }

    public String getCustomer_no()
    {
        return customer_no;
    }

    public void setCustomer_no(String customer_no)
    {
        this.customer_no = customer_no;
    }

    public String getCert_no()
    {
        return cert_no;
    }

    public void setCert_no(String cert_no)
    {
        this.cert_no = cert_no;
    }

    public String getCard_type()
    {
        return card_type;
    }

    public void setCard_type(String card_type)
    {
        this.card_type = card_type;
    }

    public String getBank_mobile()
    {
        return bank_mobile;
    }

    public void setBank_mobile(String bank_mobile)
    {
        this.bank_mobile = bank_mobile;
    }

    public String getUser_ip()
    {
        return user_ip;
    }

    public void setUser_ip(String user_ip)
    {
        this.user_ip = user_ip;
    }

    @Override
    public String toString()
    {
        return super.toString() + "BindBankCardReq [card_no=" + card_no + ", bank_card_no=" + bank_card_no + ", bank_id_no=" + bank_id_no + ", name=" + name + ", cert_type=" + cert_type
            + ", customer_no=" + customer_no + ", cert_no=" + cert_no + ", card_type=" + card_type + ", bank_mobile=" + bank_mobile + ", user_ip=" + user_ip + ", serial_no=" + serial_no + "]";
    }

}
