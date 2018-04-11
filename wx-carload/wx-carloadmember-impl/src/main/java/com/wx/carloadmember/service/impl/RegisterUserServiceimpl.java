package com.wx.carloadmember.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.wx.carloadaccount.dto.UserInfoDTO;
import com.wx.carloadmember.dao.RegisterUserDao;
import com.wx.carloadmember.domain.UserInfo;
import com.wx.carloadmember.domain.Userlist;
import com.wx.carloadmember.dto.login.UserlistDTO;
import com.wx.carloadmember.service.RegisterUserService;
import com.wx.service.Result;
import com.wx.service.Results;

@Component
@Service
public class RegisterUserServiceimpl implements RegisterUserService{
	@Resource
    private RegisterUserDao dao;
	
	@Override
	public Map<String,String> registerUser(String mobile, String password) {
		Userlist user = new Userlist();
		user.setUserAccount(mobile);
		user.setUserPwd(password);
		//user.setInputtime(new Date());
		//user.setIsEmailCheck(1);
		//user.setIsPhoneCheck(1);
		//user.setIsQqBind(1);
		//user.setLastLoginTime(new Date());
		//user.setLoginCount(2L);
		//user.setRegisterTime(new Date());
		//user.setUserScore(1L);
		//user.setUserState(1);
		//user.setUserType(1);
		user.setUserPhone(mobile);
		dao.registerUser(user);//新增用户会员
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(user.getId());
	//	userInfo.setInputtime(new Date());
	//	userInfo.setCreateTime(new Date());
		dao.saveUserInfo(userInfo);//新增用户个人信息
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", user.getId()+"");
		return map; 
	}

	@Override
	public List<UserlistDTO> userAppLogin(String mobile, String password) {
		List<Userlist> list = dao.userAppLogin(mobile, password);
		ArrayList<UserlistDTO> dtoList = new ArrayList<UserlistDTO>();
		try {
			for (Userlist user : list) {
				UserlistDTO  dto = new UserlistDTO();
				BeanUtils.copyProperties(dto,user);  
				dtoList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}

}
