package my.comp.jms.logger;

import java.util.Date;

import my.comp.jms.DestType;

public class JmsLog {

	public static enum State {
		/***
		 * 已发送
		 */
		SENDED,
		/**
		 * 已重发
		 */
		RESENDED,
		/**
		 * 消费中
		 */
		CONSUMING,
		/**
		 * 已消费
		 */
		CONSUMED
	}

	private Long id;
	private DestType destType;// topic|queue
	private String destName;
	private String busiType;
	private String busiId;
	private String sn;
	private State state;//
	private String msgClass;
	private String msgData;
	private Integer sendCount = 0;
	private Date lastSendTime;
	private Integer consumeCount = 0;
	private Date lastConsumeTime;
	private String lastClientId;
	private Date createdDate;
	private Date lastModifiedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DestType getDestType() {
		return destType;
	}

	public void setDestType(DestType destType) {
		this.destType = destType;
	}

	public String getDestName() {
		return destName;
	}

	public void setDestName(String destName) {
		this.destName = destName;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getMsgClass() {
		return msgClass;
	}

	public void setMsgClass(String msgClass) {
		this.msgClass = msgClass;
	}

	public String getMsgData() {
		return msgData;
	}

	public void setMsgData(String msgData) {
		this.msgData = msgData;
	}

	public Integer getSendCount() {
		return sendCount;
	}

	public void setSendCount(Integer sendCount) {
		this.sendCount = sendCount;
	}

	public Date getLastSendTime() {
		return lastSendTime;
	}

	public void setLastSendTime(Date lastSendTime) {
		this.lastSendTime = lastSendTime;
	}

	public Integer getConsumeCount() {
		return consumeCount;
	}

	public void setConsumeCount(Integer consumeCount) {
		this.consumeCount = consumeCount;
	}

	public String getLastClientId() {
		return lastClientId;
	}

	public void setLastClientId(String lastClientId) {
		this.lastClientId = lastClientId;
	}

	public Date getLastConsumeTime() {
		return lastConsumeTime;
	}

	public void setLastConsumeTime(Date lastConsumeTime) {
		this.lastConsumeTime = lastConsumeTime;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

}
