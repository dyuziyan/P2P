/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.account.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wx.account.dao.NoticeTemplateDao;
import com.wx.service.BaseService;

/**
 * @ClassName: NoticeTemplateService
 * @version 1.0
 * @Desc: TODO
 * @author xiaojun.zhou
 * @date 2017年9月15日下午2:59:29
 * @history v1.0
 *
 */
@Service
public class NoticeTemplateService extends BaseService {

	@Resource
	private NoticeTemplateDao noticeTemplateDao;

	public String queryMsgTemplate(String nid) {
		return noticeTemplateDao.queryMsgTemplate(nid);
	}
}
