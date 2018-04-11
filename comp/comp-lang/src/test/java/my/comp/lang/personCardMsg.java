package my.comp.lang;

import javax.xml.bind.annotation.XmlAccessType; 
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)  
@XmlType(propOrder = { "bankType", "cardNum","mobile" })  
public class personCardMsg {
	
	@XmlElement(name = "cap_corg")  
    private String bankType;  
  
    @XmlElement(name = "cap_crd_no")  
    private String cardNum;  
    
    @XmlElement(name = "mbl_no")  
    private String mobile;

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}  
}
