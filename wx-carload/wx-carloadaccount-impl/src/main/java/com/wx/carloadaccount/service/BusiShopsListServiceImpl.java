package com.wx.carloadaccount.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.wx.carloadaccount.dao.BusiShopsListDao;
import com.wx.service.Result;
import com.wx.service.Results;

@Component
@Service
public class BusiShopsListServiceImpl implements BusiShopsListService{

	@Resource
    private BusiShopsListDao busiShopsListDao;

	@Override
	public Result<List<Map<String, Object>>> queryBusiShopsList(Map<String, Object> map) {
		List<Map<String, Object>> list = busiShopsListDao.queryBusiShopsList(map);
		if(list != null && list.size() > 0){
			return Results.success(list);
		}
		return Results.error();
	}

	@Override
	public Result<List<Map<String, Object>>> queryBusiShopsPictures(Map<String, Object> map) {
		List<Map<String, Object>> mapList = busiShopsListDao.queryBusiShopsPictures(map);
		if(mapList != null && mapList.size() > 0){
			return Results.success(mapList);
		}
		return Results.error();
	}

	@Override
	public Map<String, List<Map<String, String>>> queryAppointTime(String busiShopId, String targetTime) {
		List<Map<String, String>> atMap = busiShopsListDao.queryAppointTime(busiShopId, targetTime);
		return convert(atMap);
	}
	
	/**
	 * 将时间段集合重新组装
	 * @param list
	 * @return
	 */
	 private Map<String, List<Map<String, String>>> convert(List<Map<String, String>> list) {
        // 这是要返回的map对象
        HashMap<String, List<Map<String, String>>> map = new HashMap<>();
        // 对应的list
        List<Map<String, String>> mapList = null;
        Object obj = null;
        // 遍历list
        for (Map<String, String> item : list) {
        	obj = item.get("busiShopId");
        	if(map.containsKey(obj.toString())){
        		map.get(obj.toString()).add(item);
        	}else{
        		mapList = new ArrayList<>();
        		mapList.add(item);
        		map.put(obj.toString(), mapList);
        	}
        }
        return map;
    }

	@Override
	public Map<String, List<Map<String, String>>> queryShopBossInfo(String busiShopId) {
		List<Map<String, String>> sbiMap = busiShopsListDao.queryShopBossInfo(busiShopId);
		return convert(sbiMap);
	}

	
}
