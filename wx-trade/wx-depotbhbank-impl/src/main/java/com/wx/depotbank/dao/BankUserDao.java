package com.wx.depotbank.dao;
/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
import java.util.HashMap;

import my.comp.dao.mybatis.MybatisDao;

/**
 * @ClassName: BankUserDao 
 * @version 1.0
 * @Desc: BankUserDao
 * @author  shiliang.feng
 * @date 2017年8月04 日下午11:20:38
 * @history v1.0
 *
 */
@MybatisDao
public interface BankUserDao {
	
	/**
	 * 查询用户信息 t_user
 	 * @param id
	 * @author shiliang.feng
	 * @date 2017年8月04 日下午11:20:38
	 * @return
	 */
	public HashMap<String,String> queryUserInfoById(String id);
	
}
