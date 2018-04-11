package com.wx.carloadmember.domain;

import org.apache.ibatis.type.Alias;

import com.wx.support.BaseModel;

@Alias("testModel")
public class TestModel extends BaseModel {

	private static final long serialVersionUID = 1L;
	private Long ID;
	private String NAME;
	private String LINK;
	private String COVER_IMG;
	private String FILE_PATH;
	private Integer STATUS;
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getLINK() {
		return LINK;
	}
	public void setLINK(String lINK) {
		LINK = lINK;
	}
	public String getCOVER_IMG() {
		return COVER_IMG;
	}
	public void setCOVER_IMG(String cOVER_IMG) {
		COVER_IMG = cOVER_IMG;
	}
	public String getFILE_PATH() {
		return FILE_PATH;
	}
	public void setFILE_PATH(String fILE_PATH) {
		FILE_PATH = fILE_PATH;
	}
	public Integer getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(Integer sTATUS) {
		STATUS = sTATUS;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public Long getId() {
		return ID;
	}

}
