package com.wx.carloadmember.service;


import com.wx.carloadmember.dto.login.LoginDto;
import com.wx.service.Result;

public interface testService {

	Result<String> testHessian(LoginDto loginDto);
}
