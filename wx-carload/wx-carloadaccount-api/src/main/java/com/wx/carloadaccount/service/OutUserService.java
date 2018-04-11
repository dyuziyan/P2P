package com.wx.carloadaccount.service;

import java.util.ArrayList;

import com.wx.carloadaccount.dto.UserInfoDTO;
import com.wx.carloadaccount.dto.UserLicenseDTO;
import com.wx.service.Result;


public interface OutUserService {
	
	/**
	 * 获取用户信息
	 * @param userId
	 * @return
	 */
	public UserInfoDTO queryUserInfoById(String userId);
	
	/**
	 * 修改用户密码
	 * @param userId 用编号
	 * @param password 密码
	 * @param mobile 手机号
	 * @return
	 */
	public Result<String> updateUserPwd(String userId,String password,String mobile);
	
	/**
	 * 忘记密码
	 * @param userId
	 * @param password
	 * @return
	 */
	public int updateUserPwdByMobile(String mobile,String password);
	
	
	/**
	 * 修改用户头像
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean updateUserPicture(String userId,  String pictureUrl);
	
	public Result<ArrayList<UserLicenseDTO>> querUserLicense(String userId,String password,String mobile);
	
	/**
	 * 保存用户信息
	 * @param dto
	 * @return
	 */
	public Result<Integer> saveUserInfo(UserInfoDTO dto);
}
