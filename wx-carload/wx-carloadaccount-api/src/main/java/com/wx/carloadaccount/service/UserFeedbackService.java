package com.wx.carloadaccount.service;

import com.wx.carloadaccount.dto.FeedbackPictureDTO;
import com.wx.carloadaccount.dto.UserFeedbackDTO;
import com.wx.service.Result;

public interface UserFeedbackService {
	
	/**
	 * 添加用户反馈
	 * @param feedbackType 反馈类型
	 * @param content 反馈内容
	 * @param contactWay 用户联系方式
	 * @param feedbackNum 
	 * @return
	 */
	public Result<Integer> saveFeedbackInfoList (UserFeedbackDTO dto);
	
	/**
	 * 添加用户反馈-图片凭证
	 * @param dto
	 * @return
	 */
	public Result<Integer> saveFeedbackPicture (FeedbackPictureDTO dto);
	
}
