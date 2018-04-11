package com.wx.carloadaccount.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.wx.carloadaccount.dao.UserFeedbackDao;
import com.wx.carloadaccount.dto.FeedbackPictureDTO;
import com.wx.carloadaccount.dto.UserFeedbackDTO;
import com.wx.service.BaseService;
import com.wx.service.Result;
import com.wx.service.Results;

@Component
@Service
public class UserFeedbackServiceImpl extends BaseService implements UserFeedbackService {
	
	@Resource
    private UserFeedbackDao userFeedbackDao;
	
	@Override
	public Result<Integer> saveFeedbackInfoList(UserFeedbackDTO dto) {
		try{
			userFeedbackDao.saveFeedbackInfoList(dto);
			return Results.success();
		}catch(Exception e){
			e.printStackTrace();
			return Results.error();
		}
	}

	@Override
	public Result<Integer> saveFeedbackPicture(FeedbackPictureDTO dto) {
		try{
			userFeedbackDao.saveFeedbackPicture(dto);
			return Results.success();
		}catch(Exception e){
			e.printStackTrace();
			return Results.error();
		}
	}
}
