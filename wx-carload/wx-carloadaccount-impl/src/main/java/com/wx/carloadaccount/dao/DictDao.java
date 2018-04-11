package com.wx.carloadaccount.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wx.carloadaccount.domain.BaseGeogArea;
import com.wx.carloadaccount.domain.CarBrandBs;
import com.wx.carloadaccount.domain.CarBrandTypeBs;
import com.wx.carloadaccount.domain.CarModleBs;
import com.wx.carloadaccount.domain.Caryearstylebs;
import com.wx.carloadaccount.dto.AppInfoDTO;
import com.wx.carloadaccount.dto.TypeCodeLibDTO;
import com.wx.service.Result;

import my.comp.dao.mybatis.BaseDao;
import my.comp.dao.mybatis.MybatisDao;

@MybatisDao
public interface DictDao extends BaseDao {
	
	/**
	 * 获取区域省、市、区
	 * @param areaLevel  区域级别1：省/2：市/3：区
	 * @param parentAreaNo 父级区域代码
	 * @param areaName 
	 * @return
	 */
	public List<BaseGeogArea> queryArea(BaseGeogArea bga); 
	
	/**
	 * 获取汽车品牌
	 * @param id
	 * @param startRow
	 * @param rows
	 * @return
	 */
	public List<CarBrandBs> queryCarBrandBs(@Param("id") String id,@Param("startRow") Integer startRow,@Param("rows") Integer rows) ;
	
	/**
	 * 汽车品牌类型
	 * @param id
	 * @param startRow
	 * @param rows
	 * @return
	 */
	public List<CarBrandTypeBs> queryCarBrandTypeBs(@Param("id") String id,@Param("brandId") String brandId,@Param("startRow") Integer startRow,@Param("rows") Integer rows) ;
	
	/**
	 *  汽车车型
	 * @param id
	 * @param startRow
	 * @param rows
	 * @return
	 */
	public List<CarModleBs> queryCarModleBs(@Param("id") String id,@Param("brandId") String brandId,@Param("brandTypeId") String brandTypeId,@Param("startRow") Integer startRow,@Param("rows") Integer rows) ;
	
	/**
	 * 汽车年款
	 * @param id
	 * @param startRow
	 * @param rows
	 * @return
	 */
	public List<Caryearstylebs> queryCaryearstylebs(@Param("id") String id,@Param("brandId") String brandId,@Param("brandTypeId") String brandTypeId,@Param("modleId") String modleId,@Param("startRow") Integer startRow,@Param("rows") Integer rows) ;
	
	
	/**
	 * 类型代号
	 * @param dto
	 * @return
	 */
	public   ArrayList<TypeCodeLibDTO> queryTypeCodeLib(TypeCodeLibDTO dto);
	
	/**
	 * 查询APP版本信息
	 * @param appType App类型，1:android，2:ios
	 * @return
	 */
	public  ArrayList<AppInfoDTO> queryAppInfo(@Param("appType")int appType); 
	
	/**
	 * 获取代驾费用
	 * @param provinceCode 省高德地图code
	 * @param cityCode 市高德地图code
	 * @param kilometres 距离
	 * @return
	 */
	public List<Map<String, String>> getDrivePrice(@Param("provinceCode")String provinceCode, @Param("cityCode")String cityCode, @Param("kilometres")double kilometres);
	
	/**
	 * 获取高德地图城市名称
	 * @param adCode
	 * @return
	 */
	public String getCityName(@Param("adCode")String adCode);
	
	/**
	 * 获取点播列表
	 * @return
	 */
	public List<Map<String, String>> getLiveOfflineList(@Param("startRow") Integer startRow,@Param("rows") Integer rows);
	
	/**
	 * 获取直播列表
	 */
	public List<Map<String, String>> getLiveOnlineList(@Param("mainOrderNum") String mainOrderNum);
	
	
}
