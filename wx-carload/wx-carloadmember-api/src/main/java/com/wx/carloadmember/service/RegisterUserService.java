package com.wx.carloadmember.service;

import java.util.List;
import java.util.Map;

import com.wx.carloadmember.dto.login.UserlistDTO;


public interface RegisterUserService {

	/**
	 * 注册用户
	 * @param mobile 手机号码
	 * @param password 密码
	 * @return
	 */
	public Map<String,String> registerUser(String mobile,String password);
	
	/**
	 * 用户登录
	 * @param mobile
	 * @param password
	 * @return
	 */
	public List<UserlistDTO> userAppLogin(String mobile,String password);
	
}
