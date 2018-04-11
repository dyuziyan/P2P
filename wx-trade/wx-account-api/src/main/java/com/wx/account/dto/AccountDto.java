package com.wx.account.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.wx.dto.BaseObject;

//对应表 t_user_deposit_mng t_user
public class AccountDto extends BaseObject{
	
	private static final long serialVersionUID = -7949837471967795530L;
	
	private long id ;    		 						//ID
	private long user_id ;    							//用户ID
	private BigDecimal usable_sum = BigDecimal.ZERO;  	//可用金额
	private BigDecimal freeze_sum = BigDecimal.ZERO;	//冻结金额
	private boolean is_deposit_check;					//是否开通存管
	private String deposit_account;						//存管账户
	private Date inputtime=new Date();					//时间戳
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public BigDecimal getUsable_sum() {
		return usable_sum;
	}
	public void setUsable_sum(BigDecimal usable_sum) {
		this.usable_sum = usable_sum;
	}
	public BigDecimal getFreeze_sum() {
		return freeze_sum;
	}
	public void setFreeze_sum(BigDecimal freeze_sum) {
		this.freeze_sum = freeze_sum;
	}
	public boolean isIs_deposit_check() {
		return is_deposit_check;
	}
	public void setIs_deposit_check(boolean is_deposit_check) {
		this.is_deposit_check = is_deposit_check;
	}
	public String getDeposit_account() {
		return deposit_account;
	}
	public void setDeposit_account(String deposit_account) {
		this.deposit_account = deposit_account;
	}
	public Date getInputtime() {
		return inputtime;
	}
	public void setInputtime(Date inputtime) {
		this.inputtime = inputtime;
	}
	
	
}
