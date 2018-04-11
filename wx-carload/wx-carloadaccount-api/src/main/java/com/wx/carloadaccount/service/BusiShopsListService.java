package com.wx.carloadaccount.service;

import java.util.List;
import java.util.Map;

import com.wx.service.Result;

public interface BusiShopsListService {
	/**
	 * 首页商家列表
	 * @param dto
	 * @return
	 */
	public Result<List<Map<String, Object>>> queryBusiShopsList(Map<String, Object> map);
	
	/**
	 * 查询商家图册
	 * @param map
	 * @return
	 */
	public Result<List<Map<String, Object>>> queryBusiShopsPictures(Map<String, Object> map);
	
	/**
	 * 查询商家可预约时间段（排除某些特定日期不可预约时间段）
	 * @param busiShopId 商家编号
	 * @param targetTime 预约日期
	 * @return
	 */
	public Map<String, List<Map<String, String>>> queryAppointTime(String busiShopId,String targetTime);
	
	/**
	 * 获取商家老板(明星老板)
	 * @param busiShopId
	 * @return
	 */
	public Map<String, List<Map<String, String>>> queryShopBossInfo(String busiShopId);
	
}
