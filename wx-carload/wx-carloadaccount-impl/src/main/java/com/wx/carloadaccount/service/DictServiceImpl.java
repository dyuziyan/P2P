package com.wx.carloadaccount.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.wx.carloadaccount.dao.DictDao;
import com.wx.carloadaccount.domain.BaseGeogArea;
import com.wx.carloadaccount.domain.CarBrandBs;
import com.wx.carloadaccount.domain.CarBrandTypeBs;
import com.wx.carloadaccount.domain.CarModleBs;
import com.wx.carloadaccount.domain.Caryearstylebs;
import com.wx.carloadaccount.dto.AppInfoDTO;
import com.wx.carloadaccount.dto.BaseGeogAreaDto;
import com.wx.carloadaccount.dto.CarBrandBsDto;
import com.wx.carloadaccount.dto.CarBrandTypeBsDto;
import com.wx.carloadaccount.dto.CarModleBsDto;
import com.wx.carloadaccount.dto.CaryearstylebsDto;
import com.wx.carloadaccount.dto.TypeCodeLibDTO;
import com.wx.service.BaseService;
import com.wx.service.Result;
import com.wx.service.Results;
import com.wx.support.Messages;

@Component
@Service
public class DictServiceImpl extends BaseService implements DictService {
	
	@Resource
    private DictDao dictDao;
	
	@Override
	public List<BaseGeogAreaDto> queryArea(String areaLevel, String parentAreaNo,String areaName,Integer startRow,Integer rows){
		BaseGeogArea baseGeogArea = new BaseGeogArea();
		if(StringUtils.isNotBlank(areaLevel)){
			baseGeogArea.setAreaLevel(Integer.parseInt(areaLevel));
		}
		if(StringUtils.isNotBlank(parentAreaNo)){
			baseGeogArea.setParentAreaNo(Long.parseLong(parentAreaNo));
		}
		baseGeogArea.setAreaName(areaName);
		baseGeogArea.setStartRow(startRow);
		baseGeogArea.setRows(rows);
		List<BaseGeogArea> bgaList = dictDao.queryArea(baseGeogArea);
		ArrayList<BaseGeogAreaDto> 	dtoList = new ArrayList<BaseGeogAreaDto>();
		try {
			for (BaseGeogArea bga : bgaList) {
				BaseGeogAreaDto  dto = new BaseGeogAreaDto();
				BeanUtils.copyProperties(dto,bga);  
				dtoList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return dtoList;
	}

	@Override
	public Result<ArrayList<CarBrandBsDto>> queryCarBrandBs(String id,Integer startRow,Integer rows){
		List<CarBrandBs> cbbList = dictDao.queryCarBrandBs(id,startRow,rows);
		ArrayList<CarBrandBsDto> dtoList = new ArrayList<CarBrandBsDto>();
		try {
			for (CarBrandBs cbb : cbbList) {
				CarBrandBsDto  dto = new CarBrandBsDto();
				BeanUtils.copyProperties(dto,cbb);  
				dtoList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return Results.success(dtoList);
	}

	@Override
	public Result<ArrayList<CarBrandTypeBsDto>> queryCarBrandTypeBs(String id,String brandId, Integer startRow, Integer rows)
			{
		List<CarBrandTypeBs> cbbList = dictDao.queryCarBrandTypeBs(id,brandId,startRow,rows);
		ArrayList<CarBrandTypeBsDto> dtoList = new ArrayList<CarBrandTypeBsDto>();
		try {
			for (CarBrandTypeBs cbb : cbbList) {
				CarBrandTypeBsDto  dto = new CarBrandTypeBsDto();
				BeanUtils.copyProperties(dto,cbb);  
				dtoList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return Results.success(dtoList);
	}

	@Override
	public Result<ArrayList<CarModleBsDto>> queryCarModleBs(String id,String brandId,String brandTypeId, Integer startRow, Integer rows)
			{
		List<CarModleBs> cbbList = dictDao.queryCarModleBs(id,brandId,brandTypeId,startRow,rows);
		ArrayList<CarModleBsDto> dtoList = new ArrayList<CarModleBsDto>();
		try {
			for (CarModleBs cbb : cbbList) {
				CarModleBsDto  dto = new CarModleBsDto();
				BeanUtils.copyProperties(dto,cbb);  
				dtoList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Results.success(dtoList);
	}

	@Override
	public Result<ArrayList<CaryearstylebsDto>> queryCaryearstylebs(String id,String brandId,String brandTypeId,String modleId, Integer startRow, Integer rows)
			 {
		List<Caryearstylebs> cbbList = dictDao.queryCaryearstylebs(id,brandId,brandTypeId,modleId,startRow,rows);
		ArrayList<CaryearstylebsDto> dtoList = new ArrayList<CaryearstylebsDto>();
		try {
			for (Caryearstylebs cbb : cbbList) {
				CaryearstylebsDto  dto = new CaryearstylebsDto();
				BeanUtils.copyProperties(dto,cbb);  
				dtoList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return Results.success(dtoList);
	}

	@Override
	public Result<ArrayList<TypeCodeLibDTO>> queryTypeCodeLib(TypeCodeLibDTO dto) {
		return Results.success(dictDao.queryTypeCodeLib(dto));
	}

	@Override
	public String getPropertiesByName(String fileName) {
		if(StringUtils.isBlank(fileName)){
			return null;
		}
		try{
			return Messages.getMessage(fileName);
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public Result<ArrayList<AppInfoDTO>> queryAppInfo(int appType) {
		ArrayList<AppInfoDTO> appList = dictDao.queryAppInfo(appType);
		if(null != null && appList.size() >0){
			return Results.success(appList);
		}
		return Results.error();
	}

	@Override
	public Result<Map<String, String>> getDrivePrice(String provinceCode, String cityCode, double kilometres) {
		try {
			List<Map<String, String>> resultList = dictDao.getDrivePrice(provinceCode, cityCode, kilometres);
			if(null != resultList && resultList.size() > 0){
				return	Results.success(resultList.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Results.error();
	}

	@Override
	public String getCityName(String adCode) {
		return dictDao.getCityName(adCode);
	}

	@Override
	public Result<Map<String, Object>> getUserLiveBroadcast(String userId,String mainOrderNum,Integer startRow,Integer rows) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		List<Map<String, String>> liveOnlineList = null;
		if(StringUtils.isNotBlank(mainOrderNum)){
			liveOnlineList = dictDao.getLiveOnlineList(mainOrderNum);
		}
		map.put("liveOfflineList", dictDao.getLiveOfflineList(startRow, rows));
		map.put("liveOnlineList", liveOnlineList);
		Result result =	Results.byMessage("1", "成功");
		result.setData(map);
		return result;
	}
}
