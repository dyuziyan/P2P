/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * @ClassName: SignUtils
 * @version 1.0
 * @Desc: SignUtils
 * @author xiaojun.zhou
 * @date 2018年3月19日下午3:51:06
 * @history v1.0
 */
public class SignUtils
{
    private static Logger logger = LoggerFactory.getLogger(SignUtils.class);

    public static String RSAEncryptRequest(Map<String, Object> reqData)
    {
        return encrypt(JSONObject.fromObject(reqData).toString());
    }

    public static String RSADecryptResponse(String repData)
    {
        return decrypt(repData);
    }

    public static String encrypt(String data)
    {
        try
        {
            String encryptedStr = RSATool.RSAEncode(data);
            logger.info("生成的请求密文为：" + encryptedStr);
            return encryptedStr;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String data)
    {
        try
        {
            String decryptedStr = RSATool.RSADecode(data);
            logger.info("响应解密明文为：" + decryptedStr);
            return decryptedStr;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String createSign(Map<String, Object> requestBody, String signKey, String signSecret)
    {
        String data = convertMapToJson(requestBody);
        logger.info("签名原始串：" + data);
        String key = DigestUtils.md5Hex(signKey + signSecret);
        logger.info("签名密钥串：" + key);
        String sign = DigestUtils.md5Hex(data + key);
        logger.info("签名结果串：" + sign);
        return sign;
    }

    public static Boolean signVerify(String key, String secret, Map<String, Object> data, String sign)
    {
        String newSign = createSign(data, key, secret);
        return newSign.equals(sign);
    }

    public static String convertMapToJson(Map<String, Object> requestBody)
    {
        requestBody.remove("sign");
        TreeSet<String> sortedKey = new TreeSet<String>(requestBody.keySet());
        StringBuilder builer = new StringBuilder();
        for (String key : sortedKey)
        {
            builer.append(key).append("=").append(convertObjectToJson(requestBody.get(key))).append("&");
        }
        String result = builer.toString();
        logger.info("result：" + result);
        return result.substring(0, result.length() - 1);
    }

    @SuppressWarnings("unchecked")
    public static String convertObjectToJson(Object obj)
    {
        if (obj == null)
        {
            return "";
        }
        if (obj.getClass().isArray())
        {
            return StringUtils.join((Object[]) obj, "&");
        }
        else if (obj instanceof Map)
        {
            return convertMapToJson((Map<String, Object>) obj);
        }
        else if (obj.getClass().isPrimitive() || obj.getClass() == String.class)
        {
            return String.valueOf(obj);
        }
        else if (obj instanceof Collection)
        {
            StringBuilder builder = new StringBuilder();
            for (Object _obj : (List<Object>) obj)
            {
                builder.append(convertObjectToJson(_obj)).append("&");
            }
            String result = builder.toString();
            return result.substring(0, result.length() - 1);
        }
        else
        {
            return String.valueOf(obj);
        }
    }

    public static Map<String, Object> convertJsonToMap(String jsonStr) throws JSONException
    {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if (jsonStr != null)
        {
            JSONObject json = JSONObject.fromObject(jsonStr);
            retMap = jsonToMap(json);
        }
        return retMap;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException
    {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = json.keys();
        while (keysItr.hasNext())
        {
            String key = keysItr.next();
            Object value = json.get(key);

            if (value instanceof JSONArray)
            {
                value = jsonToList((JSONArray) value);
            }

            else if (value instanceof JSONObject)
            {
                value = jsonToMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> jsonToList(JSONArray array) throws JSONException
    {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.size(); i++)
        {
            Object value = array.get(i);
            if (value instanceof JSONArray)
            {
                value = jsonToList((JSONArray) value);
            }

            else if (value instanceof JSONObject)
            {
                value = jsonToMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }
}
