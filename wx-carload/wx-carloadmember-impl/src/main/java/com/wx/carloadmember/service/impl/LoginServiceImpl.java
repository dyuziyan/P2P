package com.wx.carloadmember.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.wx.carloadmember.dao.TestDao;
import com.wx.carloadmember.domain.TestModel;
import com.wx.carloadmember.dto.login.LoginDto;
import com.wx.carloadmember.service.LoginService;
import com.wx.service.BaseService;
import com.wx.service.Result;
import com.wx.service.Results;

import my.comp.config.SysConf;

@Component
//@Service-------dubbo包service   hessian用spring 提供的@service
@Service(validation="true")
public class LoginServiceImpl extends BaseService implements LoginService {
	
	@Resource
    private TestDao tsetDao;

	@Override
	public Result<String> test(LoginDto loginDto) {
		String xxx=SysConf.get("test.get");
		Long testId=(long) 2016090303;
//		List<TestModel> testList=tsetDao.testSelect(testId);
		xxx=xxx+loginDto.getPassword();
		xxx=xxx+loginDto.getUsername();
		return Results.success(xxx);
	}
	
}
