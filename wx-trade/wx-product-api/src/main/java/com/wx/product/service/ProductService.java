package com.wx.product.service;

import com.wx.product.dto.ProductDto;
import com.wx.service.Result;

import my.comp.rmi.annotation.RemoteService;


/**
 * 标的
 */
@RemoteService("/productService")
public interface ProductService {

	/**
	 * 根据标的ID获取标的信息
	 */
	Result<ProductDto> getProduct(long productId);
}
