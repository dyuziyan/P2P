/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.req;

/**
 * @ClassName: RechargeCardReq
 * @version 1.0
 * @Desc: RechargeCardReq
 * @author xiaojun.zhou
 * @date 2018年3月22日下午5:51:13
 * @history v1.0
 */
public class RechargeCardReq extends BaseReq
{
    private static final long serialVersionUID = -3645685299165071402L;
    public String order_no;// 订单编号 ,必填,(32)位数
    public String bind_card;// 绑定卡卡号 ，必填，esb校验，19
    public String currency;// 币种 ，必填，156，3
    public String amount;// 金额 ，必填，精确到分，12
    public String fee;// 充值手续费 ，必填，精确到分，12
    public String cert_type;// 证件类型 ， 15 ，必填，2
    public String cert_no;// 证件号码 ，必填，18
    public String name;// 姓名 ，必填，60
    public String mobile;// 手机号码 ，必填，12
    public String auth_flag;// ESB代发实名认证标志 ，必填，首次充值上送Y，之后充值上送N，1
    public String auth_seq_id;// 实名认证流水号 ，条件可选
    public String user_bank_code;// 开户银行代码，条件可选
    public String user_bank_name_en;// 开户银行英文缩写，条件可选
    public String user_bank_name_cn;// 开户银行中文名称，条件可选
    public String bank_province;// 开户行省份，条件可选
    public String bank_city;// 开户行城市，条件可选
    public String user_ip;// 客户IP，条件可选
    public String card_no;// 电子账户

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

    public String getBind_card()
    {
        return bind_card;
    }

    public void setBind_card(String bind_card)
    {
        this.bind_card = bind_card;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public String getAmount()
    {
        return amount;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public String getFee()
    {
        return fee;
    }

    public void setFee(String fee)
    {
        this.fee = fee;
    }

    public String getCert_type()
    {
        return cert_type;
    }

    public void setCert_type(String cert_type)
    {
        this.cert_type = cert_type;
    }

    public String getCert_no()
    {
        return cert_no;
    }

    public void setCert_no(String cert_no)
    {
        this.cert_no = cert_no;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getAuth_flag()
    {
        return auth_flag;
    }

    public void setAuth_flag(String auth_flag)
    {
        this.auth_flag = auth_flag;
    }

    public String getAuth_seq_id()
    {
        return auth_seq_id;
    }

    public void setAuth_seq_id(String auth_seq_id)
    {
        this.auth_seq_id = auth_seq_id;
    }

    public String getUser_bank_code()
    {
        return user_bank_code;
    }

    public void setUser_bank_code(String user_bank_code)
    {
        this.user_bank_code = user_bank_code;
    }

    public String getUser_bank_name_en()
    {
        return user_bank_name_en;
    }

    public void setUser_bank_name_en(String user_bank_name_en)
    {
        this.user_bank_name_en = user_bank_name_en;
    }

    public String getUser_bank_name_cn()
    {
        return user_bank_name_cn;
    }

    public void setUser_bank_name_cn(String user_bank_name_cn)
    {
        this.user_bank_name_cn = user_bank_name_cn;
    }

    public String getBank_province()
    {
        return bank_province;
    }

    public void setBank_province(String bank_province)
    {
        this.bank_province = bank_province;
    }

    public String getBank_city()
    {
        return bank_city;
    }

    public void setBank_city(String bank_city)
    {
        this.bank_city = bank_city;
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
        return super.toString() + "RechargeCardReq [order_no=" + order_no + ", bind_card=" + bind_card + ", currency=" + currency + ", amount=" + amount + ", fee=" + fee + ", cert_type=" + cert_type
            + ", cert_no=" + cert_no + ", name=" + name + ", mobile=" + mobile + ", auth_flag=" + auth_flag + ", auth_seq_id=" + auth_seq_id + ", user_bank_code=" + user_bank_code
            + ", user_bank_name_en=" + user_bank_name_en + ", user_bank_name_cn=" + user_bank_name_cn + ", bank_province=" + bank_province + ", bank_city=" + bank_city + ", user_ip=" + user_ip
            + ", card_no=" + card_no + "]";
    }
}
