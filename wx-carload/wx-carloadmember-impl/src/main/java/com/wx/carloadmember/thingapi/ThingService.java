package com.wx.carloadmember.thingapi;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public interface ThingService {
	/**
	 * 设置默认座驾
	 * @param userId
	 * @param id
	 * @param isDefault
	 * @return
	 */
	public int updateUIsDefault(String userId,String id,String isDefault);
}
