package com.wx.carloadmember.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wx.carloadaccount.dto.UserInfoDTO;
import com.wx.carloadmember.domain.UserInfo;
import com.wx.carloadmember.domain.Userlist;

import my.comp.dao.mybatis.MybatisDao;

@MybatisDao
public interface RegisterUserDao {
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	public int  registerUser(Userlist user);
	
	/**
	 * 用户登录
	 * @param mobile
	 * @param password
	 * @return
	 */
	public List<Userlist> userAppLogin(@Param("mobile") String mobile,@Param("password") String password);
	
	
	
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
	public int saveUserInfo(UserInfo userinfo); 
	
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
}
