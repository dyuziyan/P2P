/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.ret;

import java.io.Serializable;

/**
 * @ClassName: BaseRet
 * @version 1.0
 * @Desc: TODO
 * @author xiaojun.zhou
 * @date 2018年3月19日上午11:34:24
 * @history v1.0
 */
public class BaseRet implements Serializable
{

    private static final long serialVersionUID = 1117311187127009122L;

    private String code;// 结果编码
    private String msg;// 错误信息——错误信息描述，详见错误代码列表
    private String service; // 接口名称——必须,接口名称对应表
    private String timestamp; // 时间戳——商户发起接口调用时服务器时间。采用Unix时间戳格式：1489000690
    private String uuid; // 通用唯一识别码——将商户生成的uuid返回给商户系统，以标识接口调用对应关系。(生成规则同demo中uuid)
    private String sign_type; // 签名类型——默认为MD5，目前仅支持MD5。
    private String sign; // 签名——按照sign_type参数指定的签名算法对待签名数据进行签名。目前仅支持MD5详见数字签名。
    private String encode; // 参数编码——可选,默认为UTF-8。
    private String version; // 版本号——可选,默认为当前文档版本号2.0.0。
    private String client; // 交易终端 ,必填，000001手机APP 000002网页 000003微信 000004柜面
    private String custom; // 商户自定义数据——商户发起接口调用是传递的自定义数据。如果商户没有传递此参数则返回空。
    private String sequence_id; // 钜石处理流水号——商户发起的接口请求都对应钜石系统内唯一的处理流水号

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
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

    public String getSequence_id()
    {
        return sequence_id;
    }

    public void setSequence_id(String sequence_id)
    {
        this.sequence_id = sequence_id;
    }
}
