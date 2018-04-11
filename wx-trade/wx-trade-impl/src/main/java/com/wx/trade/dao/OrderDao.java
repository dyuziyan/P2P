package com.wx.trade.dao;

import org.apache.ibatis.annotations.Param;

import com.wx.trade.dto.OrderDto;

import my.comp.dao.mybatis.BaseDao;
import my.comp.dao.mybatis.MybatisDao;


@MybatisDao
public interface OrderDao extends BaseDao<OrderDto>{

	//通过订单ID获取订单详细信息
	OrderDto getOrder(@Param("orderId") long orderId) ;
	
}
