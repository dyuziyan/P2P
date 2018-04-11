package com.wx.carloadtrade.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wx.carloadtrade.dto.OrderPaymentSerialDto;
import com.wx.carloadtrade.dto.ShopsPaymentDto;

import my.comp.dao.mybatis.BaseDao;
import my.comp.dao.mybatis.MybatisDao;

@MybatisDao
public interface ShopsPaymentDao extends BaseDao {
	
	/**
	 * 获取商家支付方式参数
	 * @param dto
	 * @return
	 */
	public  ShopsPaymentDto getShopsPayment(ShopsPaymentDto dto);
	
	/**
	 * 获取平台支付方式
	 * @return
	 */
	public List<Map> queryPaymentList();
	
	/**
	 * 生成支付流水
	 * @param mainOrderNum 主订单号
	 * @param payTypeName 支付方式名称
	 * @param payTypeCode 支付方式代号
	 * @return
	 */
	public int savePaymentSerial(OrderPaymentSerialDto dto);
	
	/**
	 * 删除支付流水
	 * @param mainOrderNum
	 * @return
	 */
	public int deletePaymentSerial(@Param("mainOrderNum")String mainOrderNum);
	
	/**
	 * 查询支付流水
	 * @param payNum 支付流水单号/交易单号
	 * @param mainOrderNum 主订单号码
	 * @param payState 支付状态 0:支付中 1:支付成功 2:支付失败
	 * @param payTypeCode 支付方式代号
	 * @return
	 */
	public List<Map<String,String>> queryPaymentSerial(@Param("payNum")String payNum,@Param("mainOrderNum")String mainOrderNum,@Param("payState")String payState,@Param("payTypeCode")String payTypeCode);
	
	
	/**
	 * 修改支付流水状态
	 * @param payNum
	 * @param payState
	 * @return
	 */
	public int udpateMainPaymentSerial(@Param("payNum")String payNum,@Param("payState")String payState);
	
	
}
