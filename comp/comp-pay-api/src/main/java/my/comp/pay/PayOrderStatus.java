package my.comp.pay;

import java.util.Map;

public class PayOrderStatus {
	private PayOrderStatusEnum status;
	private Map<String, Object> response;
	
	public PayOrderStatus(PayOrderStatusEnum status,
			Map<String, Object> response) {
		this.status = status;
		this.response = response;
	}
	public PayOrderStatusEnum getStatus() {
		return status;
	}
	public void setStatus(PayOrderStatusEnum status) {
		this.status = status;
	}
	public Map<String, Object> getResponse() {
		return response;
	}
	public void setResponse(Map<String, Object> response) {
		this.response = response;
	}
	
	
}
