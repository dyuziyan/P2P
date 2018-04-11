package com.wx.carloadbase.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wx.carloadbase.dao.BusiRuleDao;
import com.wx.carloadbase.domain.BusiRule;
import com.wx.carloadbase.service.BusiRuleService;


@Service
public class BusiRuleServiceImpl implements BusiRuleService {
	
	@Resource
	private BusiRuleDao busiRuleDao;
	@Override
	public List<BusiRule> listAll() {
		return busiRuleDao.listAll();
	}

	@Override
	public List<BusiRule> listByPattern(String pattern) {
		return busiRuleDao.listByPattern(pattern);
	}

	@Override
	public List<BusiRule> listByType(String type) {
		return busiRuleDao.listByType(type);
	}

	@Override
	public void refresh() {
		
	}

}
