package my.comp.lang;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;  
import javax.xml.bind.annotation.XmlAccessorType;  
import javax.xml.bind.annotation.XmlElement;  
import javax.xml.bind.annotation.XmlRootElement;  
import javax.xml.bind.annotation.XmlType;  
import javax.xml.bind.annotation.XmlElementWrapper;  

@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement(name = "viin")  
@XmlType(propOrder = {"personCardMsgList"})  
public class cardMsg {
	
	 @XmlElementWrapper(name = "rs")  
	 @XmlElement(name = "province")  
	 private List<personCardMsg> personCardMsgList;

	public List<personCardMsg> getPersonCardMsgList() {
		return personCardMsgList;
	}

	public void setPersonCardMsgList(List<personCardMsg> personCardMsgList) {
		this.personCardMsgList = personCardMsgList;
	}  
}
