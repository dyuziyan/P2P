package com.wx.carloadaccount.dao;

import com.wx.carloadaccount.dto.FeedbackPictureDTO;
import com.wx.carloadaccount.dto.UserFeedbackDTO;

import my.comp.dao.mybatis.MybatisDao;

@MybatisDao
public interface UserFeedbackDao {
	
	/**
	 * 添加用户反馈意见
	 * @param dto
	 * @return
	 */
	public int saveFeedbackInfoList (UserFeedbackDTO dto);
	
	/**
	 * 添加用户反馈-图片凭证
	 * @param dto
	 * @return
	 */
	public int saveFeedbackPicture (FeedbackPictureDTO dto);
	
}
