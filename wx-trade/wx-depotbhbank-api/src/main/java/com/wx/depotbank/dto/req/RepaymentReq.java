package com.wx.depotbank.dto.req;

public class RepaymentReq extends BaseReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2320507985070738951L;

	public String BorrowId;
	
	public int RepayTyp;
	
	public int repay_inst_tot;
	
	public int repay_inst_cur;
	
	public String FileName;
	
	public String MerPriv;

	public String getBorrowId() {
		return BorrowId;
	}

	public void setBorrowId(String borrowId) {
		BorrowId = borrowId;
	}

	/**
	 *  还款类型 默认正常还款
	 */
	public int getRepayTyp() {
		if(this.RepayTyp == 0){
			return 1;
		}
		return RepayTyp;
	}

	public void setRepayTyp(int repayTyp) {
		RepayTyp = repayTyp;
	}
 
	/**
	 *  还款期数(不分期为0)
	 */
	public int getRepay_inst_tot() {
		return repay_inst_tot;
	}

	public void setRepay_inst_tot(int repay_inst_tot) {
		this.repay_inst_tot = repay_inst_tot;
	}

	/**
	 *  当前期数(不分期为0)
	 */
	public int getRepay_inst_cur() {
		return repay_inst_cur;
	}

	public void setRepay_inst_cur(int repay_inst_cur) {
		this.repay_inst_cur = repay_inst_cur;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	public String getMerPriv() {
		if(MerPriv == null){
			return "";
		}
		return MerPriv;
	}

	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}
	
	
	public RepaymentReq(){
		this.RepayTyp = 1;
		this.repay_inst_tot = 0;
		this.repay_inst_cur = 0;
	}
	/**
	 * 
	 * 描述：还款 -- 签名
	 * 
	 * @author shiliang.feng
	 * @date 2017年7月17日下午3:49:53
	 * @return
	 */
	public String getRepaymentMac(String partner_id, String version_no) {
		String macHead = getMacHead(partner_id,version_no);
		String str = macHead + getBorrowId()+getRepayTyp()+getBgRetUrl()+getRepay_inst_tot()+getRepay_inst_cur()+getFileName()+ getMerPriv();
		logger.info("待签名串：mac={}", str);
		return str;
	}
	
	
}
