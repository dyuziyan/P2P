package com.wx.carloadbase.service;

import java.util.ArrayList;
import java.util.Map;

import com.wx.carloadbase.dto.CarServiceDto;

/**
 * 汽车基础服务
 * @author 13697
 *
 */
public interface CarServiceBaseService {
	/**
	 * 获取车辆服务项
	 * @param serviceTypeCode 服务类型代号 （保养 、养护、维修的代号）
	 * @param provinceCode	省份代号
	 * @param cityCode	城市代号
	 * @param brandId 车辆品牌ID
	 * @param brandTypeId 车辆品牌类型ID
	 * @param modleId 车辆车型ID
	 * @param serviceProNum 服务项目编号
	 * @return
	 */
	public ArrayList<CarServiceDto> getCarServiceList(Map<String, Object> map);
}
