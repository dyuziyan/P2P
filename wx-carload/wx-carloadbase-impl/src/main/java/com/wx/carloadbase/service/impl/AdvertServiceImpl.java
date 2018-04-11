package com.wx.carloadbase.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wx.carloadbase.dao.AdvertDao;
import com.wx.carloadbase.dto.AdvertDto;
import com.wx.carloadbase.service.AdvertService;
import com.wx.service.Result;
import com.wx.service.Results;


@Service
public class AdvertServiceImpl implements AdvertService {
	
	@Resource
	private AdvertDao advertDao;
	
	@Override
	public Result<Map<String, Object>> queryAdvertList() {
		ArrayList<Map<String, Object>> indexMap = new ArrayList<Map<String, Object>>();
		ArrayList<Map<String, Object>> startUpMap = new ArrayList<Map<String, Object>>();
		ArrayList<Map<String, Object>> videoMap =new ArrayList<Map<String, Object>>();
		ArrayList<Map<String, Object>> deliveryMap =new ArrayList<Map<String, Object>>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> dataMap = null;
		ArrayList<AdvertDto> advertDto = advertDao.queryAdvertList("");
		for (AdvertDto dto : advertDto) {
			dataMap = new HashMap<String, Object>();
			dataMap.put("pictureUrl", dto.getPictureUrl());//图片地址
			dataMap.put("linkUrl", dto.getLinkUrl());//链接地址
			dataMap.put("advertPicWidth", dto.getAdvertPicWidth());//广告图片宽度
			dataMap.put("advertPicHeight", dto.getAdvertPicHeight());//广告图片高度
				if("首页".equals(dto.getAdvertPlaceName())){
					indexMap.add(dataMap);
				}else if("启动页".equals(dto.getAdvertPlaceName())){
					startUpMap.add(dataMap);
				}else if("视频".equals(dto.getAdvertPlaceName())){
					videoMap.add(dataMap);
				}else if("取送广告位".equals(dto.getAdvertPlaceName())){
					deliveryMap.add(dataMap);
				}
				
		}
		resultMap.put("index",indexMap);//首页
		resultMap.put("startUp",startUpMap);// 启动页
		resultMap.put("video",videoMap);//视频
		resultMap.put("delivery",deliveryMap);//上门取送广告位
		return Results.success(resultMap);
	}
	
}
