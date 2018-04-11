package com.wx.carloadaccount.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import com.wx.service.Result;

import my.comp.dao.mybatis.BaseDao;
import my.comp.dao.mybatis.MybatisDao;

@MybatisDao
public interface BusiShopsListDao extends BaseDao {
	
	/**
	 * 获取商家
	 * @param 
	 * @param 
	 * @return
	 */
	public List<Map<String, Object>> queryBusiShopsList(Map<String, Object> map); 
	
	/**
	 * 查询商家图册
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> queryBusiShopsPictures(Map<String, Object> map);
	
	/**
	 * 查询商家可预约时间段（排除某些特定日期不可预约时间段）
	 * @param busiShopId 商家编号
	 * @param targetTime 预约日期
	 * @return
	 */
	public List<Map<String, String>> queryAppointTime(@Param("busiShopId")String busiShopId,@Param("targetTime")String targetTime);
	
	/**
	 * 查询商家老板(明星老板)
	 * @param busiShopId 商家编号
	 * @return
	 */
	public List<Map<String, String>> queryShopBossInfo(@Param("busiShopId")String busiShopId);
}
