package com.wx.market.dao;

import my.comp.dao.mybatis.BaseDao;
import my.comp.dao.mybatis.MybatisDao;

import org.apache.ibatis.annotations.Param;

import com.wx.market.domain.CashRedPacket;

@MybatisDao
public interface CashRedPacketDao extends BaseDao{
	
	/**
	 * 查询红包具体信息
	 */
	CashRedPacket getCashRedPacketForUpdate(@Param("cashRedPacketId") String cashRedPacketId
			,@Param("state") int state,@Param("userId") long userId) ;
}
