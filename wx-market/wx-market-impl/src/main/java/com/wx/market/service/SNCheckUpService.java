package com.wx.market.service;

public interface SNCheckUpService {
	//后面抽取到base去..不同业务查询不同流水表
	//SN号是否存在
	boolean checkSnExist(String sn);
}
