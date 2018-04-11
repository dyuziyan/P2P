package com.wx.account.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wx.account.dao.MSGDao;
import com.wx.account.domain.MSGDomain;
import com.wx.service.BaseService;

//TODO 待重构----该业务后面全部挪去base做消息中心处理
@Service
public class MSGService extends BaseService{
	@Resource
	private MSGDao MSGDao;

	public void createMsg(MSGDomain MSGDomain) {
		MSGDao.create(MSGDomain);
	}
}
