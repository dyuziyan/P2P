package com.wx.depotbank.dto.ret;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

public class BaseRet implements Serializable {

	private static final long serialVersionUID = 1L;

	public final static String CODE_SUCCESS = "000000"; // 成功

	public String partner_id; // 商户号
	public String version_no; // 版本号
	public String biz_type; // 消息类型
	public String sign_type; // 签名方式
	public String MerBillNo; // 流水号
	public String RespCode; // 应答返回码
	public String RespDesc; // 应答描述
	public String mac; // 签名
	private String MerPriv; // 商户保留域---userId

	// app
	private String RpCode;
	private String RpDesc;

	public static BaseRet error(String RespCode, String RespDesc) {
		BaseRet ret = new BaseRet();
		ret.setRespDesc(RespDesc);
		ret.setRespCode(RespCode);
		return ret;
	}

	public String getRpCode() {
		return StringUtils.isEmpty(RpCode) ? this.RespCode : RpCode;
	}

	public void setRpCode(String rpCode) {
		RpCode = rpCode;
	}

	public String getRpDesc() {
		return StringUtils.isEmpty(RpDesc) ? this.RespDesc : RpDesc;
	}

	public void setRpDesc(String rpDesc) {
		RpDesc = rpDesc;
	}

	public boolean success() {
		return getRpCode() != null && (CODE_SUCCESS.equals(getRpCode()));
	}

	public String getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}

	public String getVersion_no() {
		return version_no;
	}

	public void setVersion_no(String version_no) {
		this.version_no = version_no;
	}

	public String getBiz_type() {
		return biz_type;
	}

	public void setBiz_type(String biz_type) {
		this.biz_type = biz_type;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getMerBillNo() {
		return MerBillNo;
	}

	public void setMerBillNo(String merBillNo) {
		MerBillNo = merBillNo;
	}

	public String getRespCode() {
		return RespCode;
	}

	public void setRespCode(String respCode) {
		RespCode = respCode;
	}

	public String getRespDesc() {
		if (RespDesc == null)
			return "";
		return RespDesc;
	}

	public void setRespDesc(String respDesc) {
		RespDesc = respDesc;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getMerPriv() {
		if (MerPriv == null)
			return "";
		return MerPriv;
	}

	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	private static String str(String str) {
		return StringUtils.isEmpty(str) ? "" : str;
	}

	protected static String getMacHead(Map<String, String> params) {
		String str = params.get("partner_id") + params.get("version_no") + params.get("biz_type")
				+ params.get("sign_type") + params.get("MerBillNo") + str(params.get("PlaCustId"))
				+ str(params.get("RespCode")) + str(params.get("RespDesc"));
		return str;
	}

	/**
	 * 
	 * 描述：实名认证，修改银行卡，修改手机号
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年9月11日下午2:34:59
	 * @param params
	 * @return
	 */
	public static String getRealNameWebMac(Map<String, String> params) {
		String str = getMacHead(params) + str(params.get("OpenBankId")) + str(params.get("OpenAcctId"))
				+ str(params.get("IdentType")) + str(params.get("IdentNo")) + str(params.get("UsrName"))
				+ str(params.get("MobileNo")) + str(params.get("FEE_AMT")) + str(params.get("MerPriv"));
		return str;
	}

	/**
	 * 
	 * 描述：找回密码
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年9月11日下午2:39:27
	 * @param params
	 * @return
	 */
	public static String getBindPassMac(Map<String, String> params) {
		String str = str(params.get("char_set")) + str(params.get("partner_id")) + str(params.get("version_no"))
				+ str(params.get("biz_type")) + str(params.get("sign_type")) + str(params.get("MerBillNo"))
				+ str(params.get("RespCode")) + str(params.get("RespDesc")) + str(params.get("PlaCustId"));
		return str;
	}

	/**
	 * 
	 * 描述：充值，提现
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年9月11日下午2:39:19
	 * @param params
	 * @return
	 */
	public static String getWebRechargeMac(Map<String, String> params) {
		String str = getMacHead(params) + str(params.get("TransAmt")) + str(params.get("TransId"))
				+ str(params.get("MerFeeAmt")) + str(params.get("FeeAmt")) + str(params.get("MerPriv"));
		return str;
	}
}
