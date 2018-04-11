package com.wx.carloadaccount.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.wx.carloadaccount.dao.UserCarlisDao;
import com.wx.carloadaccount.domain.UserCarlis;
import com.wx.carloadaccount.dto.UserCarlistDTO;
import com.wx.service.BaseService;
import com.wx.service.Result;
import com.wx.service.Results;


@Component
@Service
public class UserCarlisServiceImpl extends BaseService implements UserCarlisService {
	
	@Resource
    private UserCarlisDao userCarlisDao;
	
	@Override
	public Result<Long> saveUserCarLsit(UserCarlistDTO dto) {
		UserCarlis uc = new UserCarlis();
		uc.setUserId(dto.getUserId());
		uc.setCarBrandId(dto.getCarBrandId());
		uc.setCarModleId(dto.getCarModleId());
		uc.setCarYearstyleId(dto.getCarYearstyleId());
		uc.setCarBrand(dto.getCarBrand());
		uc.setCarModle(dto.getCarModle());
		uc.setCarYearstyle(dto.getCarYearstyle());
		uc.setCarBuyTime(dto.getCarBuyTime());
		uc.setCarRunKm(dto.getCarRunKm());
		uc.setCarBrandType(dto.getCarBrandType());
		uc.setLastMaintainTime(dto.getLastMaintainTime());
		uc.setNextYearlyCheckTime(dto.getNextYearlyCheckTime());
		uc.setCarBrandTypeId(dto.getCarBrandTypeId());
		uc.setIsDefault(dto.getIsDefault());
		uc.setVinNumber(dto.getVinNumber());
		uc.setEngineNumber(dto.getEngineNumber());
		uc.setLastMaintainTime(dto.getLastMaintainTime());
		uc.setNextYearlyCheckTime(dto.getNextYearlyCheckTime());
		uc.setNextPolicyTime(dto.getNextPolicyTime());
		uc.setCarNumber(dto.getCarNumber());
	//	BeanUtils.copyProperties(uc,dto);  
		userCarlisDao.saveUserCarlis(uc);
		if(uc.getId() == null){
			return Results.error();
		}
		return Results.success(uc.getId());
	}

	@Override
	public Result<Integer> updateUserCarLsit(UserCarlistDTO dto){
			UserCarlis 	uc = new UserCarlis();
			try {
				
				uc.setVinNumber(dto.getVinNumber());;//车架号
				uc.setEngineNumber(dto.getEngineNumber());//发动机号码
				uc.setId(dto.getId());
				uc.setCarBuyTime(dto.getCarBuyTime());
				uc.setCarRunKm(dto.getCarRunKm());
				uc.setCarNumber(dto.getCarNumber());
				uc.setLastMaintainTime(dto.getLastMaintainTime());
				uc.setNextYearlyCheckTime(dto.getNextYearlyCheckTime());
				 int result = userCarlisDao.updateUserCarLsit(uc);
				 return Results.success(result);
			} catch (Exception e) {
				e.printStackTrace();
				return Results.error();
			} 
			
	}

	@Override
	public Result<Integer> updateUIsDefault(String userId, String id, String isDefault) {
		//设置默认座驾前先将已默认改为未默认
		 	userCarlisDao.updateIsDefault(userId, null, "0");
		 	Integer  result = userCarlisDao.updateIsDefault(null, id, isDefault);
	//	ThingService th = new ThingServiceImpl();
	//	int  result = th.updateUIsDefault(userId, id, isDefault);
		return Results.success(result);
	}

	@Override
	public Result<Integer> deleteUserCarLsit(String id) {
		 int result = userCarlisDao.deleteUserCarLsit(id);
		 return Results.success(result);
	}

	@Override
	public List<UserCarlistDTO> queryUserCarlist(Map<String ,Object> map) {
		
		return userCarlisDao.queryUserCarlist(map);
	}

	@Override
	public List<Map<String, Object>> validateUserCar(String userId, String userCarId, String orderState) {
		
		return userCarlisDao.validateUserCar(userId, userCarId, orderState);
	}

	@Override
	public Result<String> updateCarRunKm(String carId, String carRunKm) {
		try {
			int result = userCarlisDao.updateCarRunKm(carId, carRunKm);
		} catch (Exception e) {
			e.printStackTrace();
			return Results.error();
		}
		return Results.success();
	}
}
