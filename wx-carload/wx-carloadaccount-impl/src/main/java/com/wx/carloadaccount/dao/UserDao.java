package com.wx.carloadaccount.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wx.carloadaccount.dto.UserAddressDTO;
import com.wx.carloadaccount.dto.UserInfoDTO;
import com.wx.carloadaccount.dto.UserLicenseDTO;

import my.comp.dao.mybatis.MybatisDao;

@MybatisDao
public interface UserDao {
	
	
	/**
	 * 获取要用户信息
	 * @param userId
	 * @return
	 */
	public UserInfoDTO queryUserInfoById(String userId);
	
	/**
	 * 新增用户个人信息
	 * @param userinfo
	 * @return
	 */
	public int saveUserInfo(UserInfoDTO userinfo); 
	
	/**
	 * 修改用户密码
	 * @param userId 用户编号
	 * @param password 密码
	 * @param mobile 手机号
	 * @return
	 */
	public int updateUserPwd(@Param("userId")  String userId,@Param("password")  String password,@Param("mobile")  String mobile);
	
	/**
	 * 忘记密码
	 * @param mobile
	 * @param password
	 * @return
	 */
	public int updateUserPwdByMobile(@Param("mobile")  String mobile,@Param("password") String password);
	/**
	 * 修改用户头像
	 * @param userId
	 * @param password
	 * @return
	 */
	public int updateUserPicture(@Param("userId")  String userId,@Param("pictureUrl")  String pictureUrl);
	
	/**
	 * 获取用户证件
	 * @param userId
	 * @param type
	 */
	public ArrayList<UserLicenseDTO> queryUserLicense(@Param("userId")  String userId,@Param("type")  String type);
	
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
	 * 修改用户信息
	 * @param dto
	 * @return
	 */
	public int updateUserInfo(UserInfoDTO dto);
	
	/**
	 * 修改用户会员信息
	 * @param dto
	 * @return
	 */
	public int updateUserList(@Param("userId")  Long userId,@Param("userPhone") String userPhone);
	
}
