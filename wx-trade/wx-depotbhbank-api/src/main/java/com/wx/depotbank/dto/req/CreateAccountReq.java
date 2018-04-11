package com.wx.depotbank.dto.req;

import com.alibaba.fastjson.annotation.JSONField;
import com.wx.depotbank.enums.BizType;

/**
 * 开户请求参数
 */
public class CreateAccountReq extends BaseReq {
	private static final long serialVersionUID = 3507123502936212872L;
	public String OpenType;// 开户类型
	public String IdentType;// 证件类型
	public String IdentNo;// 证件号码
	public String UsrName;// 姓名
	public String MobileNo;// 手机号
	public String OpenBankId;// 开户银行代号
	public String OpenAcctId;// 开户银行账号

	public CreateAccountReq() {
		super();
		this.biz_type = BizType.RealNameWeb.getKey();
	}

	@JSONField(name="OpenType")
	public String getOpenType() {
		return OpenType;
	}

	public void setOpenType(String openType) {
		OpenType = openType;
	}

	@JSONField(name="IdentType")
	public String getIdentType() {
		if (IdentType == null)
			return "";
		return IdentType;
	}

	public void setIdentType(String identType) {
		IdentType = identType;
	}

	@JSONField(name="IdentNo")
	public String getIdentNo() {
		if (IdentNo == null)
			return "";
		return IdentNo;
	}

	public void setIdentNo(String identNo) {
		IdentNo = identNo;
	}

	@JSONField(name="UsrName")
	public String getUsrName() {
		if (UsrName == null)
			return "";
		return UsrName;
	}

	public void setUsrName(String usrName) {
		UsrName = usrName;
	}

	@JSONField(name="MobileNo")
	public String getMobileNo() {
		return MobileNo;
	}

	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}

	@JSONField(name="OpenBankId")
	public String getOpenBankId() {
		if (OpenBankId == null)
			return "";
		return OpenBankId;
	}

	public void setOpenBankId(String openBankId) {
		OpenBankId = openBankId;
	}

	@JSONField(name="OpenAcctId")
	public String getOpenAcctId() {
		if (OpenAcctId == null)
			return "";
		return OpenAcctId;
	}

	public void setOpenAcctId(String openAcctId) {
		OpenAcctId = openAcctId;
	}

	/**
	 * 
	 * 描述：新用户注册(页面方式) -- 签名
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月22日上午10:15:37
	 * @return
	 */
	public String getRealNameWebMac(String partner_id, String version_no) {
		this.biz_type = BizType.RealNameWeb.getKey();
		String macHead = getMacHead(partner_id, version_no);
		String str = macHead + getOpenType() + getIdentType() + getIdentNo() + getUsrName() + getMobileNo()
				+ getOpenBankId() + getOpenAcctId() + getPageReturnUrl() + getBgRetUrl() + getMerPriv();
		logger.info("待签名串：mac={}", str);
		return str;
	}

	/**
	 * 
	 * 描述：修改绑定银行卡(页面方式) -- 签名
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月22日上午10:17:37
	 * @return
	 */
	public String getBindCardWebMac(String partner_id, String version_no) {
		this.biz_type = BizType.BindCardWeb.getKey();
		String macHead = getMacHead(partner_id, version_no);
		String str = macHead + getPlaCustId() + getPageReturnUrl() + getBgRetUrl() + getMerPriv();
		logger.info("待签名串：mac={}", str);
		return str;
	}

	/**
	 * 
	 * 描述：修改手机号(页面方式) -- 签名
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月22日上午10:18:33
	 * @return
	 */
	public String getBindMobileNoMac(String partner_id, String version_no) {
		this.biz_type = BizType.BindMobileNo.getKey();
		String macHead = getMacHead(partner_id, version_no);
		String str = macHead + getPlaCustId() + getMobileNo() + getPageReturnUrl() + getBgRetUrl() + getMerPriv();
		logger.info("待签名串：mac={}", str);
		return str;
	}

	/**
	 * 
	 * 描述：修改/找回支付密码（页面方式） -- 签名
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月22日上午10:19:30
	 * @return
	 */
	public String getBindPassMac(String partner_id, String version_no) {
		this.biz_type = BizType.BindPass.getKey();
		String macHead = getMacHead(partner_id, version_no);
		String str = macHead + getPlaCustId() + getPageReturnUrl() + getBgRetUrl() + getMerPriv();
		logger.info("待签名串：mac={}", str);
		return str;
	}

	/**
	 * 
	 * 描述：动态口令申请（后台方式） -- 签名
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月22日上午10:21:19
	 * @return
	 */
	public String getSendUapMsgMac(String partner_id, String version_no) {
		this.biz_type = BizType.sendUapMsg.getKey();
		String macHead = getMacHead(partner_id, version_no);
		String str = macHead+ getMobileNo();
		logger.info("待签名串：mac={}", str);
		return str;
	}

	/**
	 * 
	 * 描述：用户信息查询（后台方式） -- 签名
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月29日下午4:10:42
	 * @return
	 */
	public String getQueryUserInfMac(String partner_id, String version_no) {
		this.biz_type = BizType.QueryUserInf.getKey();
		String macHead = getMacHead(partner_id, version_no);
		String str = macHead + getPlaCustId() + getMobileNo();
		logger.info("待签名串：mac={}", str);
		return str;
	}
	
	public String getQueryBalanceMac(String partner_id, String version_no) {
		this.biz_type = BizType.QueryBalance.getKey();
		String macHead = getMacHead(partner_id, version_no);
		String str = macHead + getPlaCustId();
		logger.info("待签名串：mac={}", str);
		return str;
	}
}
