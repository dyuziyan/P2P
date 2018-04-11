package com.wx.carloadaccount.service;

import java.util.List;
import java.util.Map;

import com.wx.carloadaccount.dto.UserCarlistDTO;
import com.wx.service.Result;

public interface UserCarlisService {

	/**
	 * 添加座驾
	 * @param UserCarlistDto
	 * @return
	 */
	public Result<Long> saveUserCarLsit(UserCarlistDTO dto)  ;
	
	/**
	 * 修改座驾信息
	 * @param dto
	 */
	public Result<Integer> updateUserCarLsit(UserCarlistDTO dto) ;
	
	/**
	 * 设置默认座驾
	 * @param userId
	 * @param id
	 * @param isDefault
	 * @return
	 */
	public Result<Integer> updateUIsDefault(String userId,String id,String isDefault);
	
	/**
	 * 删除座驾
	 * @param id
	 */
	public Result<Integer> deleteUserCarLsit(String id) ;
	
	/**
	 * 获取我的车辆
	 * @param userId 用户编号
	 * @param isDefault 是否默认车辆0：否 1：是
	 * @param carBrandId 品牌编号
	 * @param id 车辆编号
	 * @return
	 */
	public List<UserCarlistDTO>  queryUserCarlist(Map<String ,Object> map);
	
	/**
	 * 验证该车是否存在服务中订单
	 * @param userId
	 * @param userCarId
	 * @param orderState
	 * @return
	 */
	public List<Map<String,Object>> validateUserCar(String userId,String userCarId,String orderState);
	
	/**
	 * 修改车辆的行驶里程
	 * @param carId
	 * @param carRunKm
	 * @return
	 */
	public Result<String> updateCarRunKm(String carId,String carRunKm);
	
}
