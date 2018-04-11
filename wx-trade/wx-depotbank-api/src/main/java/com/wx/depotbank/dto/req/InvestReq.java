/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.req;

/**
 * @ClassName: InvestReq
 * @version 1.0
 * @Desc: 投资
 * @author xiaojun.zhou
 * @date 2018年4月3日下午2:39:14
 * @history v1.0
 */
public class InvestReq extends BaseReq
{
    private static final long serialVersionUID = -2601993143631644886L;

    public String card_no;// 电子账号 , 必填,(19)位数
    public String out_serial_no;// 申请流水号 ,必填，用于交易的唯一性标识,(32)位数
    public String amount;// 投标金额 ，必填,13位保留两位
    public String asset_no;// 标的编号 ,必填，标的信息录入时的标的编号,(40)位数
    public String interest_date;// 起息日 ,必填，YYYYMMDD,(8)位数
    public String interest_type;// 付息方式 ,必填 1：等额本息； 2：每月付息，到期还本；,(1)位数
    public String interest_day;// 利息每月支付日, 条件选填 ，DD ，付息方式为2时必填；,2
    public String end_date;// 产品到期日 ,必填，YYYYMMDD,(8)位数
    public String interest_rate;// 预期年化收益率
                                // ,必填，是5位小数如年化收益率为10%，需上送10.00000,8位保留5位
    public String frozen_flag;// 是否冻结金额 ,必填,0：不冻结； 1：冻结 ,(1)位数
    public String use_bonus;// 是否使用红包 ,必填,0：不使用红包;1：使用红包,(1)位数
    public String bonus_amount;// 抵扣红包金额 ,必填,两位小数,9位保留两位
    public String mobile;// 手机号（电子账户绑定手机号），必填,（11）位
    public String transact_date;// 交易时间 ,条件选填,(10)位数
    public String third_custom;// 第三方保留域 ,条件选填，原样返回,(100)位数
    public String forget_pwd_url;// 忘记密码跳转链接，256

    public String getCard_no()
    {
        return card_no;
    }

    public void setCard_no(String card_no)
    {
        this.card_no = card_no;
    }

    public String getOut_serial_no()
    {
        return out_serial_no;
    }

    public void setOut_serial_no(String out_serial_no)
    {
        this.out_serial_no = out_serial_no;
    }

    public String getAmount()
    {
        return amount;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public String getAsset_no()
    {
        return asset_no;
    }

    public void setAsset_no(String asset_no)
    {
        this.asset_no = asset_no;
    }

    public String getInterest_date()
    {
        return interest_date;
    }

    public void setInterest_date(String interest_date)
    {
        this.interest_date = interest_date;
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

    public String getEnd_date()
    {
        return end_date;
    }

    public void setEnd_date(String end_date)
    {
        this.end_date = end_date;
    }

    public String getInterest_rate()
    {
        return interest_rate;
    }

    public void setInterest_rate(String interest_rate)
    {
        this.interest_rate = interest_rate;
    }

    public String getFrozen_flag()
    {
        return frozen_flag;
    }

    public void setFrozen_flag(String frozen_flag)
    {
        this.frozen_flag = frozen_flag;
    }

    public String getUse_bonus()
    {
        return use_bonus;
    }

    public void setUse_bonus(String use_bonus)
    {
        this.use_bonus = use_bonus;
    }

    public String getBonus_amount()
    {
        return bonus_amount;
    }

    public void setBonus_amount(String bonus_amount)
    {
        this.bonus_amount = bonus_amount;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getTransact_date()
    {
        return transact_date;
    }

    public void setTransact_date(String transact_date)
    {
        this.transact_date = transact_date;
    }

    public String getThird_custom()
    {
        return third_custom;
    }

    public void setThird_custom(String third_custom)
    {
        this.third_custom = third_custom;
    }

    public String getForget_pwd_url()
    {
        return forget_pwd_url;
    }

    public void setForget_pwd_url(String forget_pwd_url)
    {
        this.forget_pwd_url = forget_pwd_url;
    }

    @Override
    public String toString()
    {
        return super.toString() + "InvestReq [card_no=" + card_no + ", out_serial_no=" + out_serial_no + ", amount=" + amount + ", asset_no=" + asset_no + ", interest_date=" + interest_date
            + ", interest_type=" + interest_type + ", interest_day=" + interest_day + ", end_date=" + end_date + ", interest_rate=" + interest_rate + ", frozen_flag=" + frozen_flag + ", use_bonus="
            + use_bonus + ", bonus_amount=" + bonus_amount + ", mobile=" + mobile + ", transact_date=" + transact_date + ", third_custom=" + third_custom + ", forget_pwd_url=" + forget_pwd_url + "]";
    }

}
