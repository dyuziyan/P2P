/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.account.dao;

import my.comp.dao.mybatis.BaseDao;
import my.comp.dao.mybatis.MybatisDao;

/**
 * @ClassName: NoticeTemplateDao
 * @version 1.0
 * @Desc: NoticeTemplateDao
 * @author xiaojun.zhou
 * @date 2017年9月15日下午2:55:45
 * @history v1.0
 *
 */
@MybatisDao
public interface NoticeTemplateDao extends BaseDao<Object> {

	/**
	 * 
	 * 描述：查询消息模版
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年9月15日下午2:54:27
	 * @param nid
	 * @return
	 */
	String queryMsgTemplate(String nid);
}
