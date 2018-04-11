package com.wx.support;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer version = 0 ;
	private Date createdDate = new Date();
	private Date lastModifiedDate = new Date();


	public abstract Long getId();

	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		if (this.lastModifiedDate == null) this.setLastModifiedDate(this.createdDate);
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}
