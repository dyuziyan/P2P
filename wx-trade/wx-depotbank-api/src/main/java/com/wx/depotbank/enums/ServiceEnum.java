/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.enums;

/**
 * @ClassName: ServiceEnum
 * @version 1.0
 * @Desc: ServiceEnum
 * @author xiaojun.zhou
 * @date 2018年3月19日下午6:33:19
 * @history v1.0
 */
public enum ServiceEnum
{
    BindBankCard("bind_bank_card", "绑定银行卡"), 
    UnBindBankCard("unbind_bank_card", "解绑银行卡"),
    create_account_p("create_account_p","个人开设电子银行账户接口（页面）"),
    reset_password("set_password_p","重置密码（页面）"),
    change_mobile("change_mobile","修改手机号"),
    create_account_sr_query("create_account_sr_query","开户结果查询接口"),
    set_password_query("set_password_query","网关重置密码查询"),
    bank_recharge("bank_recharge","网关充值"),
    recharge_p("recharge_p","绑定卡到电子账户充值（页面）"),
    withdraw_p("withdraw_p","提现（页面）"),
    account_balance("account_balance","电子账户余额查询"),
    assets_enroll("assets_enroll","资产登记"),
    marketing_query("marketing_query","营销户信息查询"),
    coupon_recharge("coupon_recharge","营销账户充值"),
    coupon_withdraw("coupon_withdraw","营销账户提现"),
    money_query("money_query","资金交易状态查询"),
    bid_apply_p("bid_apply_p","投资人投标申请（页面）"),
    apply_bid_query("apply_bid_query","投资人投标申请查询")
    ;

    private ServiceEnum(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    private String key;
    private String value;

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public static ServiceEnum get(String key)
    {
        ServiceEnum[] codes = ServiceEnum.values();
        for (ServiceEnum code : codes)
        {
            if (code.getKey().equals(key))
            {
                return code;
            }
        }
        return null;
    }
}
