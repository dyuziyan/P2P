/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dao;

import java.util.Map;

import my.comp.dao.mybatis.MybatisDao;

/**
 * @ClassName: BankRequestDao
 * @version 1.0
 * @Desc: BankRequestDao
 * @author xiaojun.zhou
 * @date 2018年3月20日下午4:37:06
 * @history v1.0
 */
@MybatisDao
public interface BankRequestDao
{
    /**
     * 描述：保存银行请求信息
     * @author xiaojun.zhou
     * @date 2017年6月23日下午5:20:38
     * @param params
     * @return
     */
    public int saveBankRequest(Map<String, Object> params);

    /**
     * 描述：保存银行响应信息
     * @author xiaojun.zhou
     * @date 2017年6月23日下午5:20:54
     * @param params
     * @return
     */
    public int saveBankResponse(Map<String, Object> params);
    
    /**
     * 
     * 描述：根据serialNumber查询记录数
     * 
     * @author xiaojun.zhou
     * @date 2017年9月7日下午5:25:01
     * @param serialNumber
     * @return
     */
    public int queryBankRequestBySerialNumber(String serialNumber);
}
