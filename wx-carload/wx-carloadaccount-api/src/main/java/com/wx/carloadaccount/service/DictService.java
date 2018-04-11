package com.wx.carloadaccount.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wx.carloadaccount.dto.AppInfoDTO;
import com.wx.carloadaccount.dto.BaseGeogAreaDto;
import com.wx.carloadaccount.dto.CarBrandBsDto;
import com.wx.carloadaccount.dto.CarBrandTypeBsDto;
import com.wx.carloadaccount.dto.CarModleBsDto;
import com.wx.carloadaccount.dto.CaryearstylebsDto;
import com.wx.carloadaccount.dto.TypeCodeLibDTO;
import com.wx.service.Result;

public interface DictService {

	/**
	 * 获取区域省、市、区
	 * @param areaLevel  区域级别1：省/2：市/3：区
	 * @param parentAreaNo 父级区域代码
	 * @param areaName 区域名称
	 * @return
	 */
	public List<BaseGeogAreaDto> queryArea(String areaLevel,String parentAreaNo,String areaName,Integer startRow,Integer rows);
	
	/**
	 * 获取车辆品牌
	 * @param id
	 * @param startRow
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	
	
	public   Result<ArrayList<CarBrandBsDto>> queryCarBrandBs(String id,Integer startRow,Integer rows);
	/**
	 * 汽车品牌类型
	 * @param id
	 * @param brandId 品牌编号
	 * @param startRow
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	public   Result<ArrayList<CarBrandTypeBsDto>> queryCarBrandTypeBs(String id,String brandId,Integer startRow,Integer rows);
	
	/**
	 * 汽车车型
	 * @param id
	 * @param startRow
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	public   Result<ArrayList<CarModleBsDto>> queryCarModleBs(String id,String brandId,String brandTypeId,Integer startRow,Integer rows);
	
	/**
	 * 汽车年款
	 * @param id
	 * @param startRow
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	public   Result<ArrayList<CaryearstylebsDto>> queryCaryearstylebs(String id,String brandId,String brandTypeId,String modleId,Integer startRow,Integer rows);
	
	/**
	 * 类型代号
	 * @param dto
	 * @return
	 */
	public   Result<ArrayList<TypeCodeLibDTO>> queryTypeCodeLib(TypeCodeLibDTO dto);
	
	/**
	 * 获取配置中图片服务器地址Messages.properties
	 * @return
	 */
	public String getPropertiesByName(String fileName);
	
	/**
	 * 查询APP版本信息
	 * @param appType App类型，1:android，2:ios
	 * @return
	 */
	public  Result<ArrayList<AppInfoDTO>> queryAppInfo(int appType); 
	
	/**
	 * 获取代驾费用
	 * @param provinceCode 省高德地图code
	 * @param cityCode 市高德地图code
	 * @param kilometres 距离
	 * @return
	 */
	public Result<Map<String ,String>> getDrivePrice(String provinceCode,String cityCode,double kilometres);
	
	/**
	 * 获取高德地图城市名称
	 * @param adCode 
	 * @return
	 */
	public String getCityName(String adCode);
	
	/**
	 * 获取用户服务中订单的直播列表and点播列表
	 * @param userId
	 * @param mainOrderNum 主订单
	 * @return
	 */
	public Result<Map<String ,Object>> getUserLiveBroadcast(String userId,String mainOrderNum,Integer startRow,Integer rows);
	
}
