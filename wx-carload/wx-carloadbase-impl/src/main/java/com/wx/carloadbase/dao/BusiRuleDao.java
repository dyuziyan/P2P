package com.wx.carloadbase.dao;

import java.util.List;

import my.comp.dao.mybatis.MybatisDao;

import org.apache.ibatis.annotations.Param;

import com.wx.carloadbase.domain.BusiRule;


@MybatisDao
public interface BusiRuleDao {

	List<BusiRule> listAll();

	/**
	 * 获取发布时间
	 * 
	 * @return
	 */
	BusiRule listByName(@Param("type") String type, @Param("name") String name);

	/**
	 * 根据类型获取规则列表
	 * 
	 * @return
	 */
	List<BusiRule> listByType(@Param("type") String type);

	int delete(Long[] ids);

	List<BusiRule> listByPattern(@Param("type") String pattern);
	
	int  judit(BusiRule busiRule);
	
	int  createNew(BusiRule busiRule);
	
}
