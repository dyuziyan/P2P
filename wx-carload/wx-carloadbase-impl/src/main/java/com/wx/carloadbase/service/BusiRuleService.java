package com.wx.carloadbase.service;

import java.util.List;

import com.wx.carloadbase.domain.BusiRule;



public interface BusiRuleService {
	/**
	 * 获取所有配置信息
	 * @return
	 */
	List<BusiRule> listAll();
	/**
	 * 根据配置类型正则表达式获取配置信息
	 * @return
	 */
	List<BusiRule> listByPattern(String pattern);
	/**
	 * 根据配置类型获取配置信息
	 * @return
	 */
	List<BusiRule> listByType(String type);
	/**
	 * 刷新配置
	 * @return
	 */
	void refresh();
}
