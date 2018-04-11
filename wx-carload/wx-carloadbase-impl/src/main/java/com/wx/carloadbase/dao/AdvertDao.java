package com.wx.carloadbase.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.wx.carloadbase.dto.AdvertDto;

import my.comp.dao.mybatis.MybatisDao;

@MybatisDao
public interface AdvertDao 
{
	/**
	 * 广告位图册
	 * @param advertPlaceName 广告位名称
	 * @return
	 */
	public ArrayList<AdvertDto> queryAdvertList(@Param("advertPlaceName") String advertPlaceName);
	
}
