package com.wx.carloadbase;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.wx.carloadbase.dto.CarServiceDto;
import com.wx.carloadbase.service.CarServiceBaseService;
import com.wx.carloadbase.service.CarServiceService;
import com.wx.service.Result;
import com.wx.service.Results;


@Component("carServiceImpl")
public class CarServiceImpl implements CarServiceService{
	
	@Resource
	private CarServiceBaseService carServiceBaseService;

	@Override
	public Result<ArrayList<CarServiceDto>> getCarServiceList(Map<String, Object> map) {
		return Results.success(carServiceBaseService.getCarServiceList(map));
	}
	
}
