package com.wx.product.dao;

import org.apache.ibatis.annotations.Param;

import com.wx.product.dto.ProductDto;

import my.comp.dao.mybatis.BaseDao;
import my.comp.dao.mybatis.MybatisDao;

@MybatisDao
public interface ProductDao extends BaseDao<ProductDto>{
	
	//通过标的ID获取标的详细信息
	ProductDto getProduct(@Param("productId") long productId) ;
	
}
