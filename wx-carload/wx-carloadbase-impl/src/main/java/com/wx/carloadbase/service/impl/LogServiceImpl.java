package com.wx.carloadbase.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wx.carloadbase.dao.LogDao;
import com.wx.carloadbase.service.LogService;


@Service
public class LogServiceImpl implements LogService {
	
	@Resource
	private LogDao logDao;

	@Override
	public int saveUserOperateLog(Long userId, String ipAddress, Long operateType, String operateDesc) {
		try {
			return logDao.saveUserOperateLog(userId, ipAddress, operateType, operateDesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int saveUserLoginLog(Long userId, String ipAddress, String platformType) {
		try {
			return logDao.saveUserLoginLog(userId, ipAddress, platformType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int saveCareOrderLog(String mainOrderNum, String orderNum, Integer handleType, String remark) {
		
		return logDao.saveCareOrderLog(mainOrderNum, orderNum, handleType, remark);
	}

	@Override
	public int saveMaintainOrderLog(String mainOrderNum, String orderNum, Integer handleType, String remark) {
		return logDao.saveMaintainOrderLog(mainOrderNum, orderNum, handleType, remark);
	}

	@Override
	public int saveRepairOrderLog(String mainOrderNum, String orderNum, Integer handleType, String remark) {
		return logDao.saveRepairOrderLog(mainOrderNum, orderNum, handleType, remark);
	}
}
