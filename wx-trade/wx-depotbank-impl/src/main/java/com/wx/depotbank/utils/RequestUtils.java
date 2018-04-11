/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.wx.depotbank.common.BankConstant;
import com.wx.depotbank.dto.req.BaseReq;
import com.wx.depotbank.enums.ClientEnum;
import com.wx.depotbank.enums.ResponseCode;
import com.wx.depotbank.exception.BankErrorCode;
import com.wx.depotbank.exception.BankException;
import com.wx.depotbank.service.request.BankRequestService;
import com.wx.depotbank.service.request.ResponceThread;
import com.wx.depotbank.service.request.ResponceThreadPool;
import com.wx.support.ChartSet;

import my.comp.http.HttpException;
import my.comp.lang.StringUtils;

/**
 * @ClassName: RequestUtils
 * @version 1.0
 * @Desc: RequestUtils
 * @author xiaojun.zhou
 * @date 2018年3月19日下午3:37:59
 * @history v1.0
 */
public class RequestUtils
{
    private static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);

    /**
     * 描述：封装请求参数
     * @author xiaojun.zhou
     * @date 2018年3月19日下午3:42:20
     * @param obj
     * @return
     * @throws BankException
     */
    private static Map<String, Object> packageBasicParams(Object obj) throws BankException
    {
        try
        {
            Map<String, Object> requestParams = new HashMap<String, Object>();
            Class<?> clazz = obj.getClass();
            Field[] fields = clazz.getFields();
            for (Field field : fields)
            {
                field.setAccessible(true);
                if (field.get(obj) == null)
                    continue;
                requestParams.put(field.getName(), field.get(obj));
            }
            requestParams.put("timestamp", OrderUtil.timestamp());
            Object uuid = requestParams.get("uuid");
            Object client = requestParams.get("client");
            // 通用唯一识别码
            if (uuid == null)
            {
                requestParams.put("uuid", OrderUtil.uuid());
            }
            // 交易终端,默认web
            if (client == null)
            {
                requestParams.put("client", ClientEnum.WEB.getKey());
            }
            requestParams.put("sign_type", BankConstant.SIGN_TYPE);
            requestParams.put("encode", ChartSet.UTF8.getKey());
            requestParams.put("version", BankConstant.VERSION_NO);
            String sign = SignUtils.createSign(requestParams, BankConstant.RFT_KEY, BankConstant.RFT_SECRET);
            requestParams.put("sign", sign);
            return requestParams;
        }
        catch (Exception e)
        {
            throw new BankException(BankErrorCode.WXCF01.getCode(), "封装公用的参数异常");
        }
    }

    /**
     * 描述：请求银行接口
     * @author xiaojun.zhou
     * @date 2018年3月19日下午5:09:21
     * @param requestParams
     * @param clazz
     * @return
     * @throws BankException
     */
    public static <T> T doRequest(BaseReq req, Class<T> clazz) throws BankException
    {
        Map<String, Object> requestParams = packageBasicParams(req);
        logger.info("请求的参数，requestParams={}", requestParams);
        // 保存银行请求信息
        BankRequestService bankRequestService = (BankRequestService) SpringSupport.getBean("bankRequestServiceImpl");
        int requestId = bankRequestService.saveRequest(requestParams);
        if (requestId == -1)
        {
            logger.error("保存银行请求信息失败:数据异常");
            throw new BankException(BankErrorCode.WXCF01.getCode(), "数据异常");
        }
        if (requestId == -2)
        {
            logger.error("保存银行请求信息失败:请求过于频繁，请稍后再试");
            throw new BankException(BankErrorCode.WXCF02.getCode(), "请求过于频繁，请稍后再试");
        }

        String response = null;
        try
        {
            // 加密
            String param = SignUtils.RSAEncryptRequest(requestParams);
            logger.info("请求密文：{}", param);
            // 发送请求,得到响应数据
            HttpClient rr = new HttpClient();
            response = rr.sendRequestForPost(BankConstant.BANK_URL, param) + "";
        }
        catch (HttpException e)
        {
            logger.error("请求银行接口异常", e);
            throw new BankException(BankErrorCode.WXCF04.getCode(), "请求银行HTTP接口异常 :" + e.getMessage());
        }
        logger.info("银行返回信息response：{}", response);
        if (StringUtils.isNull(response))
        {
            throw new BankException(BankErrorCode.WXCF03.getCode(), "银行没有返回信息");
        }

        // 解密
        String resStr = SignUtils.RSADecryptResponse(String.valueOf(response));
        // // 转换银行数据成jsonObject
        JSONObject responcemap = JSONObject.parseObject(resStr);
        // // 保存银行返回信息
        ResponceThreadPool.pull(new ResponceThread(responcemap));
        // 将json转换成map
        String respCode = responcemap.getString("code");
        if (!StringUtils.equals(ResponseCode.SUCCESS.getValue(), respCode))
        {
            String respDesc = responcemap.getString("msg");
            throw new BankException(respCode, respDesc);
        }
        return JSONObject.parseObject(responcemap.toJSONString(), clazz);
    }
}
