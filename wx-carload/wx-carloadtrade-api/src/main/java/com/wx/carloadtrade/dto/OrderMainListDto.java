package com.wx.carloadtrade.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.wx.dto.BaseObject;
/**
 * 主订单列表
 * @author 13697
 *
 */
public class OrderMainListDto  extends BaseObject{
	
	private Long id;//ID
	private	String mainOrderNum;//主订单号码
	private String orderNum;//子订单编号
	private	String orderType;//订单服务类型
	private	Long userId;//用户ID （用户表用户ID）
	private	Long busiShopId;//商家ID （商家表商家ID）
	private	String busiShopName;//商家名称 （冗余）
	private	String busiShopAddress;//商家地址 （冗余）
	private	String busiShopPhone;//商家电话 （冗余）
	private	Integer orderState;//订单状态 0待确认，1已确认，2检查中，3待付款，4已付款，5维护中，6返程中，7已完成，8商家取消，9用户取消
	private	Integer payState;//支付状态 0：未支付 1：已支付 2：部分支付
	private	String receiverName;//联系人 （默认拉取用户地址表默认设置）
	private	Integer receiverSex;//联系人性别 1：男 2：女
	private	String receiverPhone;//联系人电话 （默认拉取用户地址表默认设置）
	private	String province;//省份 （默认拉取用户地址表默认设置）
	private	String city;//城市 （默认拉取用户地址表默认设置）
	private	String country;//区县 （默认拉取用户地址表默认设置）
	private	String street;//街道 （默认拉取用户地址表默认设置）
	private	String adressDetail;//详情地址 （默认拉取用户地址表默认设置）
	private	Integer isDoorService;//是否上门取送 1：是 0：否
	private	Long userCarId;//用户车辆ID （默认拉取用户车辆表默认设置）
	private	String carBrandName;//车辆品牌（默认拉取用户车辆表默认设置）
	private	String carBrandType;//车辆品牌类型（默认拉取用户车辆表默认设置）
	private	String carModleName;//车辆车型（默认拉取用户车辆表默认设置）
	private	String carYearstyleName;//车辆年款（默认拉取用户车辆表默认设置）
	private	String carNumber;//车牌号码
	private	Double carRunKm;//行驶里程（KM）（默认拉取用户车辆表默认设置）
	private	Date carBuyTime;//车辆购买日期
	private	String payTypeCode;//支付方式代号
	private	String payTypeName;//支付方式
	private	String invoiceTypeCode;//发票类型代号
	private	String invoiceTitle;//发票抬头
	private	BigDecimal marketTotalPrice;//市场价（指导价）
	private	BigDecimal goodsTotalPrice;//商品总价
	private	BigDecimal manHourFee;//工时费
	private	BigDecimal discountPrice;//优惠价格
	private	BigDecimal doorServicePrice;//上门服务费
	private	BigDecimal payAmount;//已付金额
	private	BigDecimal orderTotalPrice;//订单总价
	private Long appointTimeId;//预约时间区间ID
	private	Date appointmentTime;//预约时间
	private	Date orderTime;//下单时间
	private	Date payTime;//支付时间
	private	Date carFetchTime;//取车时间
	private	Date carDeliverTime;//送车时间
	private	String carFetchAddress;//取车地址
	private	String carDeliverAddress;//送车地址
	private	Integer fetchDeliverState;//代驾取、送车状态 1：代驾取车 2：代驾还车 3：代驾取车还车
	private	Integer isFetchDeliverAddrSame;//取车 还车地址是否相同 0：否 1：是 （默认不选中 客户填还车地址，选中，填充取车地址到还车地址）
	private	String userRemark;//用户备注
	private	String busiShopRemark;//商家备注
	private	Integer orderInputType;//订单来源 1：安卓 2：IOS 3：PC 4：微信
	private	Integer isDel;//是否已删除 0：未删除 1：已删除
	private	String maintains;//保养服务2,3,5,6
	private	String cares;//养护服务2,3,5,6
	private	String repairs;//维修服务2,3,5,6
	private	String carinfo;//车辆信息
	private	String orderStateName;//订单状态名称
	private	String orderTypeName;//订单类型名称
	private	String playUrl;//播放地址
	private	String liveState;//直播状态
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMainOrderNum() {
		return mainOrderNum;
	}
	public void setMainOrderNum(String mainOrderNum) {
		this.mainOrderNum = mainOrderNum;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getBusiShopId() {
		return busiShopId;
	}
	public void setBusiShopId(Long busiShopId) {
		this.busiShopId = busiShopId;
	}
	public String getBusiShopName() {
		return busiShopName;
	}
	public void setBusiShopName(String busiShopName) {
		this.busiShopName = busiShopName;
	}
	public String getBusiShopAddress() {
		return busiShopAddress;
	}
	public void setBusiShopAddress(String busiShopAddress) {
		this.busiShopAddress = busiShopAddress;
	}
	public String getBusiShopPhone() {
		return busiShopPhone;
	}
	public void setBusiShopPhone(String busiShopPhone) {
		this.busiShopPhone = busiShopPhone;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	public Integer getPayState() {
		return payState;
	}
	public void setPayState(Integer payState) {
		this.payState = payState;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public Integer getReceiverSex() {
		return receiverSex;
	}
	public void setReceiverSex(Integer receiverSex) {
		this.receiverSex = receiverSex;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getAdressDetail() {
		return adressDetail;
	}
	public void setAdressDetail(String adressDetail) {
		this.adressDetail = adressDetail;
	}
	public Integer getIsDoorService() {
		return isDoorService;
	}
	public void setIsDoorService(Integer isDoorService) {
		this.isDoorService = isDoorService;
	}
	public Long getUserCarId() {
		return userCarId;
	}
	public void setUserCarId(Long userCarId) {
		this.userCarId = userCarId;
	}
	public String getCarBrandName() {
		return carBrandName;
	}
	public void setCarBrandName(String carBrandName) {
		this.carBrandName = carBrandName;
	}
	public String getCarBrandType() {
		return carBrandType;
	}
	public void setCarBrandType(String carBrandType) {
		this.carBrandType = carBrandType;
	}
	public String getCarModleName() {
		return carModleName;
	}
	public void setCarModleName(String carModleName) {
		this.carModleName = carModleName;
	}
	public String getCarYearstyleName() {
		return carYearstyleName;
	}
	public void setCarYearstyleName(String carYearstyleName) {
		this.carYearstyleName = carYearstyleName;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public Double getCarRunKm() {
		return carRunKm;
	}
	public void setCarRunKm(Double carRunKm) {
		this.carRunKm = carRunKm;
	}
	public Date getCarBuyTime() {
		return carBuyTime;
	}
	public void setCarBuyTime(Date carBuyTime) {
		this.carBuyTime = carBuyTime;
	}
	public String getPayTypeCode() {
		return payTypeCode;
	}
	public void setPayTypeCode(String payTypeCode) {
		this.payTypeCode = payTypeCode;
	}
	public String getPayTypeName() {
		return payTypeName;
	}
	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}
	public String getInvoiceTypeCode() {
		return invoiceTypeCode;
	}
	public void setInvoiceTypeCode(String invoiceTypeCode) {
		this.invoiceTypeCode = invoiceTypeCode;
	}
	public String getInvoiceTitle() {
		return invoiceTitle;
	}
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}
	public BigDecimal getMarketTotalPrice() {
		return marketTotalPrice;
	}
	public void setMarketTotalPrice(BigDecimal marketTotalPrice) {
		this.marketTotalPrice = marketTotalPrice;
	}
	public BigDecimal getGoodsTotalPrice() {
		return goodsTotalPrice;
	}
	public void setGoodsTotalPrice(BigDecimal goodsTotalPrice) {
		this.goodsTotalPrice = goodsTotalPrice;
	}
	public BigDecimal getManHourFee() {
		return manHourFee;
	}
	public void setManHourFee(BigDecimal manHourFee) {
		this.manHourFee = manHourFee;
	}
	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}
	public BigDecimal getDoorServicePrice() {
		return doorServicePrice;
	}
	public void setDoorServicePrice(BigDecimal doorServicePrice) {
		this.doorServicePrice = doorServicePrice;
	}
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	public BigDecimal getOrderTotalPrice() {
		return orderTotalPrice;
	}
	public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}
	public Long getAppointTimeId() {
		return appointTimeId;
	}
	public void setAppointTimeId(Long appointTimeId) {
		this.appointTimeId = appointTimeId;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	public Date getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	public Date getCarFetchTime() {
		return carFetchTime;
	}
	public void setCarFetchTime(Date carFetchTime) {
		this.carFetchTime = carFetchTime;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCarDeliverTime() {
		return carDeliverTime;
	}
	public void setCarDeliverTime(Date carDeliverTime) {
		this.carDeliverTime = carDeliverTime;
	}
	public String getCarFetchAddress() {
		return carFetchAddress;
	}
	public void setCarFetchAddress(String carFetchAddress) {
		this.carFetchAddress = carFetchAddress;
	}
	public String getCarDeliverAddress() {
		return carDeliverAddress;
	}
	public void setCarDeliverAddress(String carDeliverAddress) {
		this.carDeliverAddress = carDeliverAddress;
	}
	public Integer getFetchDeliverState() {
		return fetchDeliverState;
	}
	public void setFetchDeliverState(Integer fetchDeliverState) {
		this.fetchDeliverState = fetchDeliverState;
	}
	public Integer getIsFetchDeliverAddrSame() {
		return isFetchDeliverAddrSame;
	}
	public void setIsFetchDeliverAddrSame(Integer isFetchDeliverAddrSame) {
		this.isFetchDeliverAddrSame = isFetchDeliverAddrSame;
	}
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
	public String getBusiShopRemark() {
		return busiShopRemark;
	}
	public void setBusiShopRemark(String busiShopRemark) {
		this.busiShopRemark = busiShopRemark;
	}
	public Integer getOrderInputType() {
		return orderInputType;
	}
	public void setOrderInputType(Integer orderInputType) {
		this.orderInputType = orderInputType;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public String getMaintains() {
		return maintains;
	}
	public void setMaintains(String maintains) {
		this.maintains = maintains;
	}
	public String getCares() {
		return cares;
	}
	public void setCares(String cares) {
		this.cares = cares;
	}
	public String getRepairs() {
		return repairs;
	}
	public void setRepairs(String repairs) {
		this.repairs = repairs;
	}
	public String getCarinfo() {
		return carinfo;
	}
	public void setCarinfo(String carinfo) {
		this.carinfo = carinfo;
	}
	public String getOrderStateName() {
		return orderStateName;
	}
	public void setOrderStateName(String orderStateName) {
		this.orderStateName = orderStateName;
	}
	public String getOrderTypeName() {
		return orderTypeName;
	}
	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}
	public String getPlayUrl() {
		return playUrl;
	}
	public void setPlayUrl(String playUrl) {
		this.playUrl = playUrl;
	}
	public String getLiveState() {
		return liveState;
	}
	public void setLiveState(String liveState) {
		this.liveState = liveState;
	}
	
}
