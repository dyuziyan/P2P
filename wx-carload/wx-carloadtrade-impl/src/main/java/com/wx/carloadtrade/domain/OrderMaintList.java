package com.wx.carloadtrade.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.wx.dto.BaseObject;
@Alias("orderMaintList")
public class OrderMaintList extends BaseObject {/*
	
	
	*//**
	 * 
	 */
	private static final long serialVersionUID = 9035380554844119485L;
	
	private Long id;//ID
	private String orderNum;//订单号码
	private Long userId;// 用户ID （用户表用户ID）
	private Long busiShopId;// 商家ID （商家表商家ID）
	private String busiShopName;// 商家名称 （冗余）
	private String busiShopAddress;// 商家地址 （冗余）
	private String busiShopPhone;// 商家电话 （冗余）
	private Integer orderState;// 订单状态
	private Integer payState;// 支付状态
	private String receiverName;//	 联系人 （默认拉取用户地址表默认设置）
	private String receiverPhone;// 联系人电话 （默认拉取用户地址表默认设置）
	private String province;// 省份 （默认拉取用户地址表默认设置）
	private String city;// 城市 （默认拉取用户地址表默认设置）
	private String country;// 区县 （默认拉取用户地址表默认设置）
	private String street;// 街道 （默认拉取用户地址表默认设置）
	private String adressDetail;// 详情地址 （默认拉取用户地址表默认设置）
	private Integer isDoorService;//　是否上门去送
	private Long userCarId;//　用户车辆ID （默认拉取用户车辆表默认设置）
	private String carBrandName;//　车辆品牌（默认拉取用户车辆表默认设置）
	private String carBrandType;// 车辆品牌类型（默认拉取用户车辆表默认设置）
	private String carModleName;// 车辆车型（默认拉取用户车辆表默认设置）
	private String carYearstyleName;// 车辆年款（默认拉取用户车辆表默认设置）
	private String carNumber;// 车牌号码
	private Double carRunKm	;//行驶里程（KM）（默认拉取用户车辆表默认设置）
	private String payTypeCode;// 支付方式代号
	private String payTypeName	;// 支付方式
	private String invoiceTypeCode;//	 发票类型代号
	private String invoiceTitle;// 发票抬头
	private Double goodsTotalPrice;// 商品总价
	private Double transportPrice;// 运费
	private Double checkPrice	;// 检查费
	private Double doorServicePrice;// 上门服务费
	private double orderTotalPrice;// 订单总价
	private Date maintTime;// 保养时间
	private Date orderTime	;// 下单时间
	private Date payTime;// 支付时间
	private String userRemark;// 用户备注
	private Integer orderInputType;//订单来源 1：安卓 2：IOS 3：PC 4：微信
	private Integer receiverSex ;// 联系人性别 1：男  2：女
	private Date carFetchTime ;// 取车时间
	private String carFetchAddress;// 取车地址
	private String carDeliverAddress ;//送车地址
	private Long prmyOperBrand;//商家主营品牌id
	private Date carBuyTime;//购车时间
	private Date carDeliverTime;//送车时间
	private String addressDetail;
	private Date busiMaintTime;//商家调整保养时间
	private Date busiCarFetchTime;//商家调整取车时间
	private int userIsAgree;//用户是否同意商家调整时间 0：未处理 1：同意 2：拒绝
	private int fetchDeliverState;//送车状态 1：代驾取车  2：代驾还车  3：代驾取车还车' 
	private int orderType;//订单类型 1：保养 2：维修
	private String busiShopRemark;//商家备注
	
	
	public OrderMaintList() {
		this.orderState = 0;
		this.payState = 0;
		this.orderTime = new Date();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
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
	public Double getGoodsTotalPrice() {
		return goodsTotalPrice;
	}
	public void setGoodsTotalPrice(Double goodsTotalPrice) {
		this.goodsTotalPrice = goodsTotalPrice;
	}
	public Double getTransportPrice() {
		return transportPrice;
	}
	public void setTransportPrice(Double transportPrice) {
		this.transportPrice = transportPrice;
	}
	public Double getCheckPrice() {
		return checkPrice;
	}
	public void setCheckPrice(Double checkPrice) {
		this.checkPrice = checkPrice;
	}
	public Double getDoorServicePrice() {
		return doorServicePrice;
	}
	public void setDoorServicePrice(Double doorServicePrice) {
		this.doorServicePrice = doorServicePrice;
	}
	public double getOrderTotalPrice() {
		return orderTotalPrice;
	}
	public void setOrderTotalPrice(double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}
	public Date getMaintTime() {
		return maintTime;
	}
	public void setMaintTime(Date maintTime) {
		this.maintTime = maintTime;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
	public Integer getOrderInputType() {
		return orderInputType;
	}
	public void setOrderInputType(Integer orderInputType) {
		this.orderInputType = orderInputType;
	}
	public Integer getReceiverSex() {
		return receiverSex;
	}
	public void setReceiverSex(Integer receiverSex) {
		this.receiverSex = receiverSex;
	}
	public Date getCarFetchTime() {
		return carFetchTime;
	}
	public void setCarFetchTime(Date carFetchTime) {
		this.carFetchTime = carFetchTime;
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

	public Long getPrmyOperBrand() {
		return prmyOperBrand;
	}

	public void setPrmyOperBrand(Long prmyOperBrand) {
		this.prmyOperBrand = prmyOperBrand;
	}

	public Date getCarBuyTime() {
		return carBuyTime;
	}

	public void setCarBuyTime(Date carBuyTime) {
		this.carBuyTime = carBuyTime;
	}

	public Date getCarDeliverTime() {
		return carDeliverTime;
	}

	public void setCarDeliverTime(Date carDeliverTime) {
		this.carDeliverTime = carDeliverTime;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public Date getBusiMaintTime() {
		return busiMaintTime;
	}

	public void setBusiMaintTime(Date busiMaintTime) {
		this.busiMaintTime = busiMaintTime;
	}

	public Date getBusiCarFetchTime() {
		return busiCarFetchTime;
	}

	public void setBusiCarFetchTime(Date busiCarFetchTime) {
		this.busiCarFetchTime = busiCarFetchTime;
	}

	public int getUserIsAgree() {
		return userIsAgree;
	}

	public void setUserIsAgree(int userIsAgree) {
		this.userIsAgree = userIsAgree;
	}

	public int getFetchDeliverState() {
		return fetchDeliverState;
	}

	public void setFetchDeliverState(int fetchDeliverState) {
		this.fetchDeliverState = fetchDeliverState;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public String getBusiShopRemark() {
		return busiShopRemark;
	}

	public void setBusiShopRemark(String busiShopRemark) {
		this.busiShopRemark = busiShopRemark;
	}
}
