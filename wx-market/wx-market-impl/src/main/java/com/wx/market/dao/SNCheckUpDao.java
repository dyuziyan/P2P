package com.wx.market.dao;

import my.comp.dao.mybatis.BaseDao;
import my.comp.dao.mybatis.MybatisDao;

import org.apache.ibatis.annotations.Param;

@MybatisDao
public interface SNCheckUpDao extends BaseDao{
	
	/**
	 * 查询SN是否存在--------t_fundrecord 需拆分   不同业务流水应分开查询
	 */
	int checkSnExist(@Param("sn") String sn) ;
}
