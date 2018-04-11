package com.wx.depotbank.dao;

import java.util.Map;

import my.comp.dao.mybatis.MybatisDao;


@MybatisDao
public interface QueryUserListDao {


	/**
	 * 查询所有未开通存管账户的用户列表---在某个时间段之前----应为一次性操作
	 */
	public int queryUnRegistDepotbankUserList(Map<String, Object> params);
}
