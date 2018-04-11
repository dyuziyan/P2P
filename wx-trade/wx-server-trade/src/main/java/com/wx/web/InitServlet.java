/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.wx.depotbank.common.BankConstant;
import com.wx.support.PropertyLoader;

/**
 * @ClassName: InitSevelet
 * @version 1.0
 * @Desc: InitServlet
 * @author xiaojun.zhou
 * @date 2017年7月13日下午8:43:58
 * @history v1.0
 */
public class InitServlet extends HttpServlet
{

    private static final long serialVersionUID = 4826433582650440868L;

    @Override
    public void init(ServletConfig config) throws ServletException
    {
        String rootPath = config.getServletContext().getRealPath("/");
        // 证书的地址
        BankConstant.BANK_PUBLIC_KEY = rootPath + PropertyLoader.getKey("bank.properties", "bank.publicKey");
        // 证书的地址
        BankConstant.BANK_PRIVATE_KEY = rootPath + PropertyLoader.getKey("bank.properties", "bank.privateKey");
    }
}
