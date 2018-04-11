/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.ret;

/**
 * @ClassName: AccountRet
 * @version 1.0
 * @Desc: AccountRet
 * @author xiaojun.zhou
 * @date 2018年3月19日下午3:35:46
 * @history v1.0
 */
public class AccountRet extends BaseRet
{

    private static final long serialVersionUID = -8597973667461348931L;

    public String cert_type;// 证件类型,通请求
    public String cert_no;// 证件号码 ，同请求
    public String name;// 姓名，同请求
    public String card_no;// 电子账户
    public String customer_no;// 客户号（开户返回的客户号）
    public String serial_no;// 三方绑定编号(绑卡是返回的编号，解绑的时候要根据这个绑定编号解绑)
    public String bank_card_no;// 银行卡号
    public String mobile;// 手机号
    public String bank_name;// 银行名称
    public String rsp_code;// 返回错误码
    public String account_type;// 账户类型
    public String out_serial_no;// 申请流水号
    public String status;// 订单状态 1 下单（等待网关访问后提交） 2 处理中（客户已提交，正在处理）3 订单关闭 4
                         // 超时关闭(超时时间:5分钟)

    public String getCert_type()
    {
        return cert_type;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
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

    public String getSerial_no()
    {
        return serial_no;
    }

    public void setSerial_no(String serial_no)
    {
        this.serial_no = serial_no;
    }

    public String getBank_card_no()
    {
        return bank_card_no;
    }

    public void setBank_card_no(String bank_card_no)
    {
        this.bank_card_no = bank_card_no;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getBank_name()
    {
        return bank_name;
    }

    public void setBank_name(String bank_name)
    {
        this.bank_name = bank_name;
    }

    public String getRsp_code()
    {
        return rsp_code;
    }

    public void setRsp_code(String rsp_code)
    {
        this.rsp_code = rsp_code;
    }

    public String getAccount_type()
    {
        return account_type;
    }

    public void setAccount_type(String account_type)
    {
        this.account_type = account_type;
    }

    public String getOut_serial_no()
    {
        return out_serial_no;
    }

    public void setOut_serial_no(String out_serial_no)
    {
        this.out_serial_no = out_serial_no;
    }
}
