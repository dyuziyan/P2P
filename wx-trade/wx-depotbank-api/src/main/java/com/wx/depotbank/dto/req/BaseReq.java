/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.req;

import java.io.Serializable;

/**
 * @ClassName: BaseReq
 * @version 1.0
 * @Desc: BaseReq
 * @author xiaojun.zhou
 * @date 2018年3月19日上午11:22:24
 * @history v1.0
 */
public class BaseReq implements Serializable
{

    private static final long serialVersionUID = 8920068309526626879L;

    public String service; // 接口名称
    public String timestamp;// 时间戳，必须,商户发起接口调用时服务器时间。采用Unix时间戳格式：1489000690(10)位
    public String uuid;// 通用唯一识别码一识别码——必须,商户发起接口调用之前必须生成一个唯一标识发送给服务器。
    public String sign_type;// 签名类型——可选,默认为MD5，目前仅支持MD5。
    public String sign;// 签名——必须,按照sign_type参数指定的签名算法对待签名数据进行签名。目前仅支持MD5.详见数字签名。
    public String encode;// 参数编码，可选,默认为UTF-8。
    public String version;// 版本号——可选,默认为当前文档版本号2.0.0。
    public String client;// 交易终端 ,必填，000001手机APP 000002网页 000003微信 000004柜面
    public String custom;// 商户自定义数据，不允许使用以下敏感字符集: ”|”、“&”、“#”、“%”等
    public String success_url; // 成功跳转地址 成功地址
    public String fail_url; // 失败跳转地址 失败跳转地址
    public String callback_url; // 回调地址

    public String getSuccess_url()
    {
        return success_url;
    }

    public void setSuccess_url(String success_url)
    {
        this.success_url = success_url;
    }

    public String getFail_url()
    {
        return fail_url;
    }

    public void setFail_url(String fail_url)
    {
        this.fail_url = fail_url;
    }

    public String getCallback_url()
    {
        return callback_url;
    }

    public void setCallback_url(String callback_url)
    {
        this.callback_url = callback_url;
    }

    public String getService()
    {
        return service;
    }

    public void setService(String service)
    {
        this.service = service;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public String getSign_type()
    {
        return sign_type;
    }

    public void setSign_type(String sign_type)
    {
        this.sign_type = sign_type;
    }

    public String getSign()
    {
        return sign;
    }

    public void setSign(String sign)
    {
        this.sign = sign;
    }

    public String getEncode()
    {
        return encode;
    }

    public void setEncode(String encode)
    {
        this.encode = encode;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getClient()
    {
        return client;
    }

    public void setClient(String client)
    {
        this.client = client;
    }

    public String getCustom()
    {
        return custom;
    }

    public void setCustom(String custom)
    {
        this.custom = custom;
    }

    @Override
    public String toString()
    {
        return "BaseReq [service=" + service + ", timestamp=" + timestamp + ", uuid=" + uuid + ", sign_type=" + sign_type + ", sign=" + sign + ", encode=" + encode + ", version=" + version
            + ", client=" + client + ", custom=" + custom + ", success_url=" + success_url + ", fail_url=" + fail_url + ", callback_url=" + callback_url + "]";
    }
}
