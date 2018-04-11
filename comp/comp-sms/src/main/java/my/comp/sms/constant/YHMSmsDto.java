package my.comp.sms.constant;

import java.util.List;
import java.util.Map;

public class YHMSmsDto {
	
	private List<String> mobiles;			//待发送手机号码集
 	private String smsTemplet;				//短信模板
 	private Map<String,List<String>> code;	//变参--参数名用code0，code1的模式     每个list子集的数据，根据mobiles的排序来对应
 	
	public List<String> getMobiles() {
		return mobiles;
	}
	public void setMobiles(List<String> mobiles) {
		this.mobiles = mobiles;
	}
	public String getSmsTemplet() {
		return smsTemplet;
	}
	public void setSmsTemplet(String smsTemplet) {
		this.smsTemplet = smsTemplet;
	}
	public Map<String, List<String>> getCode() {
		return code;
	}
	public void setCode(Map<String, List<String>> code) {
		this.code = code;
	}
}
