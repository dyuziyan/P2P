package com.wx.carloadaccount.service;

import java.util.ArrayList;
import java.util.List;

import com.wx.carloadaccount.dto.UserAddressDTO;
import com.wx.carloadaccount.dto.UserInfoDTO;
import com.wx.carloadaccount.dto.UserLicenseDTO;
import com.wx.service.Result;


public interface UserService {
	
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
	public int updateUserPwd(String userId,String password,String mobile);
	
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
	public int updateUserPicture(String userId,  String pictureUrl);
	
	public Result<ArrayList<UserLicenseDTO>> querUserLicense(String userId,String password,String mobile);
	
	
	/**
	 * 新增用户地址表
	 * @param dto
	 * @return
	 */
	public int saveUserAddress(UserAddressDTO dto);
	
	/**
	 * 新增用户驾驶证信息 
	 * @param dto
	 * @return
	 */
	public int saveUserDriverLicense(UserLicenseDTO dto);
	
	/**
	 * 获取用户地址表
	 * @param userId
	 * @return
	 */
	public List<UserAddressDTO> queryUserAddress(Long userId);
	
	/**
	 * 获取用户驾驶证信息 
	 * @param userId
	 * @return
	 */
	public UserLicenseDTO queryUserDriverLicense(Long userId);
	
	/**
	 * 修改用户地址
	 * @param dto
	 * @return
	 */
	public int updateUserAddress(UserAddressDTO dto);
	
	/**
	 * 修改用户驾驶证信息
	 * @param dto
	 * @return
	 */
	public int updateDriverLicense(UserLicenseDTO dto);
	
	/**
	 * 修改用户个人信息
	 * @param dto
	 * @return
	 */
	public int updateUserInfo(UserInfoDTO dto);
	
	/**
	 * 修改用户会员信息
	 * @param dto
	 * @return
	 */
	public int updateUserList(Long userId,String userPhone);
}
