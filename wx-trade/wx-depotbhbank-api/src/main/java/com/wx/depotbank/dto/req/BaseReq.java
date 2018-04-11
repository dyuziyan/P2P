package com.wx.depotbank.dto.req;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.annotation.JSONField;
import com.wx.depotbank.enums.ChartSet;

/**
 * 存管基础通用请求参数
 */
public class BaseReq implements Serializable {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = 1L;
	public String char_set = ChartSet.GBK.getValue(); // 字符集
	public String biz_type; // 消息类型
	public String sign_type = "RSA"; // 签名方式
	public String MerBillNo; // 流水号
	public String PlaCustId; // 用户在存管系统的唯一标识
	public String MerPriv; // 商户保留域
	public String PageReturnUrl;// 页面返回url
	public String BgRetUrl;// 后台通知url

	// app
	public String BackUrl;// 返回链接(app异步)
	public String FrontUrl;// 前置链接(app同步)
	public String RpCode;// 应答返回码
	public String RpDesc;// 应答返回码描述信息
	public String Transid;//app消息类型

	public static BaseReq build(String PlaCustId, String MerBillNo) {
		BaseReq req = new BaseReq();
		if (PlaCustId != null) {
			req.setPlaCustId(PlaCustId);
		}
		req.setMerBillNo(MerBillNo);
		return req;
	}

	@JSONField(name = "char_set")
	public String getChar_set() {
		return char_set;
	}

	public void setChar_set(String char_set) {
		this.char_set = char_set;
	}

	@JSONField(name = "biz_type")
	public String getBiz_type() {
		return biz_type;
	}

	public void setBiz_type(String biz_type) {
		this.biz_type = biz_type;
	}

	@JSONField(name = "sign_type")
	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	@JSONField(name = "MerBillNo")
	public String getMerBillNo() {
		if (MerBillNo == null)
			return "";
		return MerBillNo;
	}

	public void setMerBillNo(String merBillNo) {
		MerBillNo = merBillNo;
	}

	@JSONField(name = "PlaCustId")
	public String getPlaCustId() {
		if (PlaCustId == null)
			return "";
		return PlaCustId;
	}

	public void setPlaCustId(String plaCustId) {
		PlaCustId = plaCustId;
	}

	@JSONField(name = "MerPriv")
	public String getMerPriv() {
		if (MerPriv == null)
			return "";
		return MerPriv;
	}

	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}

	@JSONField(name = "PageReturnUrl")
	public String getPageReturnUrl() {
		return PageReturnUrl;
	}

	public void setPageReturnUrl(String pageReturnUrl) {
		PageReturnUrl = pageReturnUrl;
	}

	@JSONField(name = "BgRetUrl")
	public String getBgRetUrl() {
		return BgRetUrl;
	}

	public void setBgRetUrl(String bgRetUrl) {
		BgRetUrl = bgRetUrl;
	}

	@JSONField(name = "BackUrl")
	public String getBackUrl() {
		return BackUrl;
	}

	public void setBackUrl(String backUrl) {
		BackUrl = backUrl;
	}

	@JSONField(name = "FrontUrl")
	public String getFrontUrl() {
		return FrontUrl;
	}

	public void setFrontUrl(String frontUrl) {
		FrontUrl = frontUrl;
	}

	@JSONField(name = "RpCode")
	public String getRpCode() {
		return RpCode;
	}

	public void setRpCode(String rpCode) {
		RpCode = rpCode;
	}

	@JSONField(name = "RpDesc")
	public String getRpDesc() {
		return RpDesc;
	}

	public void setRpDesc(String rpDesc) {
		RpDesc = rpDesc;
	}

	@JSONField(name = "Transid")
	public String getTransid() {
		return Transid;
	}

	public void setTransid(String transid) {
		Transid = transid;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * 
	 * 描述：签名公用头部信息
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月21日下午6:02:46
	 * @return
	 */
	public String getMacHead(String partner_id, String version_no) {
		return getChar_set() + partner_id + version_no + getBiz_type() + getSign_type() + getMerBillNo();
	}
}
