package com.wx.market.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.type.Alias;
/**
 * 现金红包
 */
@Alias("cashRedPacket")
public class CashRedPacket implements Serializable{
	private static final long serialVersionUID = 167233422223L;
	
	private long userid ;    		 //用户ID
	private BigDecimal redwrap;	 	 //红包额度
	private Date STRAT_DATE;	 	 //有效区间开始时间
	private Date END_DATE ;    		 //有效区间结束时间
	private Date receivedate;		 //发放时间
	private Integer IS_VID;    	 	 //是否有效
	private Date usedate;	 	     //使用时间
	private Integer use_state;		 //使用状态 	0未使用 1已使用
	private String redproportion;	 //红包比例
	private String GIVEREDDESC;    	 //红包描述
	private String redpacketCode;	 //红包代码
	private String REDPACKETTYPE;	 //红包类型(1、注册 2、投资  3、充值)
	private Integer productday;	 	 //项目期限 0代表不限
	private Integer read_flag ; 	 //红包是否已读
	
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public BigDecimal getRedwrap() {
		return redwrap;
	}
	public void setRedwrap(BigDecimal redwrap) {
		this.redwrap = redwrap;
	}
	public Date getSTRAT_DATE() {
		return STRAT_DATE;
	}
	public void setSTRAT_DATE(Date sTRAT_DATE) {
		STRAT_DATE = sTRAT_DATE;
	}
	public Date getEND_DATE() {
		return END_DATE;
	}
	public void setEND_DATE(Date eND_DATE) {
		END_DATE = eND_DATE;
	}
	public Date getReceivedate() {
		return receivedate;
	}
	public void setReceivedate(Date receivedate) {
		this.receivedate = receivedate;
	}
	public Integer getIS_VID() {
		return IS_VID;
	}
	public void setIS_VID(Integer iS_VID) {
		IS_VID = iS_VID;
	}
	public Date getUsedate() {
		return usedate;
	}
	public void setUsedate(Date usedate) {
		this.usedate = usedate;
	}
	public Integer getUse_state() {
		return use_state;
	}
	public void setUse_state(Integer use_state) {
		this.use_state = use_state;
	}
	public String getRedproportion() {
		return redproportion;
	}
	public void setRedproportion(String redproportion) {
		this.redproportion = redproportion;
	}
	public String getGIVEREDDESC() {
		return GIVEREDDESC;
	}
	public void setGIVEREDDESC(String gIVEREDDESC) {
		GIVEREDDESC = gIVEREDDESC;
	}
	public String getRedpacketCode() {
		return redpacketCode;
	}
	public void setRedpacketCode(String redpacketCode) {
		this.redpacketCode = redpacketCode;
	}
	public String getREDPACKETTYPE() {
		return REDPACKETTYPE;
	}
	public void setREDPACKETTYPE(String rEDPACKETTYPE) {
		REDPACKETTYPE = rEDPACKETTYPE;
	}
	public Integer getProductday() {
		return productday;
	}
	public void setProductday(Integer productday) {
		this.productday = productday;
	}
	public Integer getRead_flag() {
		return read_flag;
	}
	public void setRead_flag(Integer read_flag) {
		this.read_flag = read_flag;
	}
	
}




