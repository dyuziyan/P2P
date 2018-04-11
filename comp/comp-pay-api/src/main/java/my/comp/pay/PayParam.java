package my.comp.pay;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 客户端提交参数
 */
public class PayParam implements Serializable {

	private static final long serialVersionUID = -8250734245878993063L;
	private Long userId;
	private BigDecimal amount;
	private Date orderTime;
	private PayClient payClient;
	private String phone;

	//是否首次充值
	private boolean first = true;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public PayClient getPayClient() {
		return payClient;
	}

	public void setPayClient(PayClient payClient) {
		this.payClient = payClient;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}


	
}
