package com.wx.market.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wx.market.dao.SNCheckUpDao;
import com.wx.market.service.SNCheckUpService;

@Service
public class SNCheckUpServiceImpl implements SNCheckUpService{

	@Resource SNCheckUpDao snCkeckUpDao;
	
	@Override
	public boolean checkSnExist(String sn) {
		return snCkeckUpDao.checkSnExist(sn)>0;
	}

}
