package com.wx.carloadbase.service.impl;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wx.carloadbase.dao.CarServiceDao;
import com.wx.carloadbase.dto.CarServiceDto;
import com.wx.carloadbase.service.CarServiceBaseService;


@Service
public class CarServiceBaseServiceImpl implements CarServiceBaseService {

	@Resource
	private CarServiceDao CarServiceDao;
	
	
	@Override
	public ArrayList<CarServiceDto> getCarServiceList(Map<String, Object> map) {
		return CarServiceDao.getCarServiceList(map);
	}
	
	
}
