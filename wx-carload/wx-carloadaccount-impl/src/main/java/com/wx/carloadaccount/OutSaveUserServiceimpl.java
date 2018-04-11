package com.wx.carloadaccount;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.wx.carloadaccount.dto.UserAddressDTO;
import com.wx.carloadaccount.dto.UserInfoDTO;
import com.wx.carloadaccount.dto.UserLicenseDTO;
import com.wx.carloadaccount.service.OutUserService;
import com.wx.carloadaccount.service.UserService;
import com.wx.service.Result;
import com.wx.service.Results;

import my.comp.lang.DateUtils;

@Component
@Service
public class OutSaveUserServiceimpl implements OutUserService{

	@Resource
	private UserService userService;


	@Override
	public UserInfoDTO queryUserInfoById(String userId) {
		
		return userService.queryUserInfoById(userId);
	}

	@Override
	public Result<String> updateUserPwd(String userId, String password,String mobile) {
		int result = userService.updateUserPwd(userId, password,mobile);
		if(result > 0){
			return Results.success();
		}
		return Results.error();
	}

	@Override
	public boolean updateUserPicture(String userId, String pictureUrl) {
		try{
			userService.updateUserPicture(userId, pictureUrl);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public int updateUserPwdByMobile(String mobile, String password) {
		return userService.updateUserPwdByMobile(mobile, password);
	}

	@Override
	public Result<ArrayList<UserLicenseDTO>> querUserLicense(String userId, String password, String mobile) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Result<Integer> saveUserInfo(UserInfoDTO dto) {
		try {
			//修改用户个人信息
			userService.updateUserInfo(dto);
			/*UserAddressDTO userAddressDTO = new UserAddressDTO();
			userAddressDTO.setUserId(dto.getId());
			userAddressDTO.setAddressDetail(dto.getAddressDetail());*/
			UserLicenseDTO userLicenseDTO = new UserLicenseDTO();
			userLicenseDTO.setGetLicenseTime(dto.getGetLicenseTime());
			userLicenseDTO.setUserId(dto.getId());
			userLicenseDTO.setUserIdentityNum(dto.getUserIdentityNum());
			userLicenseDTO.setLicenseNumber(dto.getLicenseNumber());
			/*//获取用户地址表
			List<UserAddressDTO> userAddress = userService.queryUserAddress(dto.getId());
			if(null != userAddress && userAddress.size() > 0){
				userService.updateUserAddress(userAddressDTO);
			}else{
				userService.saveUserAddress(userAddressDTO);
			}*/
			//获取用户驾驶证信息 
			UserLicenseDTO userLicense = userService.queryUserDriverLicense(dto.getId());
			if(null != userLicense){
				userService.updateDriverLicense(userLicenseDTO);
			}else{
				userService.saveUserDriverLicense(userLicenseDTO);
			}
			
			//修改用户会员信息
			//userService.updateUserList(dto.getId(), dto.getUserPhone());
		} catch (Exception e) {
			e.printStackTrace();
			return Results.error();
		}
		return Results.success();
	}
}
