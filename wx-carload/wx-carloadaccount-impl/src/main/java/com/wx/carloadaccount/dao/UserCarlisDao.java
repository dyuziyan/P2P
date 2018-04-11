package com.wx.carloadaccount.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wx.carloadaccount.domain.UserCarlis;
import com.wx.carloadaccount.dto.UserCarlistDTO;

import my.comp.dao.mybatis.BaseDao;
import my.comp.dao.mybatis.MybatisDao;

@MybatisDao
public interface UserCarlisDao extends BaseDao{

	/**
	 * 添加座驾
	 * @param userCarlis
	 * @return
	 */
	public int saveUserCarlis(UserCarlis userCarlis);
	
	/**
	 * 修改座驾
	 * @param userCarlis
	 * @return
	 */
	public int updateUserCarLsit(UserCarlis userCarlis);
	
	/**
	 * 设置默认座驾
	 * @param userId
	 * @param id
	 * @param isDefault
	 * @return
	 */
	public int updateIsDefault(@Param("userId") String userId,@Param("id")String id,@Param("isDefault")String isDefault);
	
	/**
	 * 删除座驾
	 * @param id
	 */
	public int deleteUserCarLsit(@Param("id") String id) ;
	
	/**
	 * 获取我的车辆
	 * @param userId
	 * @return
	 
	public List<UserCarlistDTO> queryUserCarlist(@Param("userId") String userId,@Param("isDefault") String isDefault,@Param("carBrandId") String carBrandId );
	*/
	public List<UserCarlistDTO> queryUserCarlist(Map<String ,Object> map);
	/**
	 * 验证该车是否存在服务中订单
	 * @param userId   用户编号
	 * @param userCarId 车辆编号
	 * @param orderState 需要排除的订单状态
	 * @return
	 */
	public List<Map<String,Object>> validateUserCar(@Param("userId") String userId,@Param("userCarId") String userCarId,@Param("orderState") String orderState);
	
	
	/**
	 * 修改车辆的行驶里程
	 * @param carId
	 * @param carRunKm
	 * @return
	 */
	public int updateCarRunKm(@Param("carId") String carId,@Param("carRunKm") String carRunKm);
}
