package com.wx.carloadmember.service;


import com.wx.carloadmember.dto.login.LoginDto;
import com.wx.service.Result;


/**
 * 登录
 */
public interface LoginService {

//	/**
//	 * 登录验证
//	 * 
//	 * @param loginDto
//	 * @return LoginResultEnum value
//	 */
//	Result<Long> login(LoginDto loginDto);
//
//	/**
//	 * 快捷登录成功后触发事件
//	 * 
//	 * @param loginDto
//	 * @return
//	 */
//	Result<Void> loginAfter(LoginDto loginDto);
//	/**
//	 * 快捷登录发送验证码
//	 * @param sendCodeDto
//	 * @return
//	 */
//	Result<String> quickLoginSmsSend(SendCodeDto sendCodeDto);
//	/**
//	 * 快捷登录
//	 * @param quickLoginDto
//	 * @return
//	 */
//	Result<SmsVerifyResult> quickLogin(QuickLoginDto loginDto);
	
	
	Result<String> test(LoginDto loginDto);
}
