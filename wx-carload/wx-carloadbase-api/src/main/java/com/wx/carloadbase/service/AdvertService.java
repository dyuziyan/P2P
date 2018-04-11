package com.wx.carloadbase.service;

import java.util.Map;

import com.wx.service.Result;

public interface AdvertService {
	
	/**
	 * 广告图册
	 * @param advertAddr 广告位置
	 * @return
	 */
	public Result<Map<String, Object>> queryAdvertList();
}
