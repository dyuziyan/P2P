package com.wx.product.service;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wx.product.dao.ProductDao;
import com.wx.product.dto.ProductDto;
import com.wx.service.BaseService;
import com.wx.service.Result;
import com.wx.service.Results;

/**
 * 标的
 */
@Service
public class ProductServiceImpl extends BaseService implements ProductService,Serializable {

	private static final long serialVersionUID = -4714527635900342314L;
	@Resource
	private ProductDao productDao;
	
	@Override
	public Result<ProductDto> getProduct(long productId) {
		ProductDto productResult=productDao.getProduct(productId);
		if(productResult==null){
			return Results.error();
		}
		return Results.success(productResult);
	}

}

