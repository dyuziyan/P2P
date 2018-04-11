package com.wx.trade.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import my.comp.dao.mybatis.MybatisDao;


@MybatisDao
public interface ServiceChargeDao {

	//获取用户免提额度
	BigDecimal getUserFreeCharge(@Param("userId") long userId) ;
	
}
