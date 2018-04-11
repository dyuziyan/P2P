/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.req;

/**
 * @ClassName: WithdrawReq
 * @version 1.0
 * @Desc: 提现
 * @author xiaojun.zhou
 * @date 2018年3月27日下午5:48:17
 * @history v1.0
 */
public class WithdrawReq extends BaseReq
{
    private static final long serialVersionUID = 2254789275773792758L;
    public String order_no;// 订单编号,必填,(20)位数，必须20位数以内
    public String card_no;// 账户,必填,(20)位数
    public String bank_name;// 银行名称,条件选填,(60)位数
    public String bind_card;// 绑定卡号,必填,(32)位数
    public String name;// 姓名,必填,(60)位数
    public String cert_no;// 证件号码,必填,(18)位数
    public String cert_type;// 证件类型,必填，15-身份证18位，20-其它，25-
    // 企业社会信用代码 注：企业开户时上送20或社会信用号25,(2)位数
    public String mobile;// 手机号码，必填,(12)位数
    public String amount;// 提现金额，必填,12 位保留两位
    public String fee;// 手续费，必填,(8,2)位数
    public String channel_flag;// 是否指定通道,条件选填，Y-
    // 指定资金通道(默认人民银行)， N-不填资金通道(ESB选择),(1)位数
    public String channel_code;// 通道标识(如果channel_flag选Y就填G1)
    public String union_bank_code;// 开户银行联行号,条件选填，(
    // 如果channel_code填了 G1就填写人民银行分配联行号,如果没填，就不用分配。),(20)位数
    public String open_bank_code;// 开户银行代码,条件选填,(20)位数
    public String bank_name_en;// 开户银行英文缩写,条件选填,(20)位数
    public String bank_name_cn;// 开户银行中文名称,条件选填,(50)位数
    public String bank_province;// 开户行省份,条件选填,(20)位数
    public String bank_city;// 开户行城市,条件选填,(50)位数

    public String getOrder_no()
    {
        return order_no;
    }

    public void setOrder_no(String order_no)
    {
        this.order_no = order_no;
    }

    public String getCard_no()
    {
        return card_no;
    }

    public void setCard_no(String card_no)
    {
        this.card_no = card_no;
    }

    public String getBank_name()
    {
        return bank_name;
    }

    public void setBank_name(String bank_name)
    {
        this.bank_name = bank_name;
    }

    public String getBind_card()
    {
        return bind_card;
    }

    public void setBind_card(String bind_card)
    {
        this.bind_card = bind_card;
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

    public String getCert_type()
    {
        return cert_type;
    }

    public void setCert_type(String cert_type)
    {
        this.cert_type = cert_type;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
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

    public String getChannel_flag()
    {
        return channel_flag;
    }

    public void setChannel_flag(String channel_flag)
    {
        this.channel_flag = channel_flag;
    }

    public String getChannel_code()
    {
        return channel_code;
    }

    public void setChannel_code(String channel_code)
    {
        this.channel_code = channel_code;
    }

    public String getUnion_bank_code()
    {
        return union_bank_code;
    }

    public void setUnion_bank_code(String union_bank_code)
    {
        this.union_bank_code = union_bank_code;
    }

    public String getOpen_bank_code()
    {
        return open_bank_code;
    }

    public void setOpen_bank_code(String open_bank_code)
    {
        this.open_bank_code = open_bank_code;
    }

    public String getBank_name_en()
    {
        return bank_name_en;
    }

    public void setBank_name_en(String bank_name_en)
    {
        this.bank_name_en = bank_name_en;
    }

    public String getBank_name_cn()
    {
        return bank_name_cn;
    }

    public void setBank_name_cn(String bank_name_cn)
    {
        this.bank_name_cn = bank_name_cn;
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

    @Override
    public String toString()
    {
        return super.toString() + "WithdrawReq [order_no=" + order_no + ", card_no=" + card_no + ", bank_name=" + bank_name + ", bind_card=" + bind_card + ", name=" + name + ", cert_no=" + cert_no
            + ", cert_type=" + cert_type + ", mobile=" + mobile + ", amount=" + amount + ", fee=" + fee + ", channel_flag=" + channel_flag + ", channel_code=" + channel_code + ", union_bank_code="
            + union_bank_code + ", open_bank_code=" + open_bank_code + ", bank_name_en=" + bank_name_en + ", bank_name_cn=" + bank_name_cn + ", bank_province=" + bank_province + ", bank_city="
            + bank_city + "]";
    }
}
