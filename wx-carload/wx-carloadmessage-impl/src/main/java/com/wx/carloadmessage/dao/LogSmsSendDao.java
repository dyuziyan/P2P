package com.wx.carloadmessage.dao;

import java.util.List;
import java.util.Map;

import com.wx.carloadmessage.domain.LogSmsSend;

import my.comp.dao.mybatis.BaseDao;
import my.comp.dao.mybatis.MybatisDao;

@MybatisDao
public interface LogSmsSendDao extends BaseDao {
	
	public int saveLogSmsSend(List<LogSmsSend> logSmsSend);
	
	
	public List<Map> getPhoneCodeMap(String phone);
	
}
