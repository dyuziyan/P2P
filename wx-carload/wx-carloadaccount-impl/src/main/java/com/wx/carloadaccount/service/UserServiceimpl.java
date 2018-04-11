package com.wx.carloadaccount.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.wx.carloadaccount.dao.UserDao;
import com.wx.carloadaccount.dto.UserAddressDTO;
import com.wx.carloadaccount.dto.UserInfoDTO;
import com.wx.carloadaccount.dto.UserLicenseDTO;
import com.wx.service.Result;

@Component
@Service
public class UserServiceimpl implements UserService{
	
	@Resource
    private UserDao dao;

	@Override
	public int saveUserAddress(UserAddressDTO dto) {
		return dao.saveUserAddress(dto);
	}

	@Override
	public int saveUserDriverLicense(UserLicenseDTO dto) {
		return dao.saveUserDriverLicense(dto);
	}
	
	@Override
	public UserInfoDTO queryUserInfoById(String userId) {
		
		return dao.queryUserInfoById(userId);
	}

	@Override
	public int updateUserPwd(String userId, String password,String mobile) {
		return dao.updateUserPwd(userId, password,mobile);
	}

	@Override
	public int updateUserPicture(String userId, String pictureUrl) {

		return dao.updateUserPicture(userId, pictureUrl);
	}

	@Override
	public int updateUserPwdByMobile(String mobile, String password) {
		return dao.updateUserPwdByMobile(mobile, password);
	}

	@Override
	public Result<ArrayList<UserLicenseDTO>> querUserLicense(String userId, String password, String mobile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserAddressDTO> queryUserAddress(Long userId) {
		
		return dao.queryUserAddress(userId);
	}

	@Override
	public UserLicenseDTO queryUserDriverLicense(Long userId) {
		return dao.queryUserDriverLicense(userId);
	}

	@Override
	public int updateUserAddress(UserAddressDTO dto) {
		return dao.updateUserAddress(dto);
	}

	@Override
	public int updateDriverLicense(UserLicenseDTO dto) {
		return dao.updateDriverLicense(dto);
	}

	@Override
	public int updateUserInfo(UserInfoDTO dto) {
		return dao.updateUserInfo(dto);
	}

	@Override
	public int updateUserList(Long userId, String userPhone) {
		return dao.updateUserList(userId, userPhone);
	}

}
