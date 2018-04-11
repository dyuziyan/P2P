package com.wx.carloadmember.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wx.carloadmember.dao.TestDao;
import com.wx.carloadmember.domain.TestModel;
import com.wx.carloadmember.dto.login.LoginDto;
import com.wx.carloadmember.service.testService;
import com.wx.service.BaseService;
import com.wx.service.Result;
import com.wx.service.Results;

import my.comp.config.SysConf;

@Service("testService")
public class testServiceImpl extends BaseService implements testService {
	
	@Resource
    private TestDao tsetDao;

	@Override
	public Result<String> testHessian(LoginDto loginDto) {
		String xxx=SysConf.get("test.get");
		Long testId=(long) 2016090303;
		List<TestModel> testList=tsetDao.testSelect(testId);
		xxx=xxx+loginDto.getPassword();
		xxx=xxx+loginDto.getUsername();
		return Results.success(xxx);
	}
	
}
