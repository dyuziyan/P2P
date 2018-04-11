package com.wx.carloadaccount.domain;

import org.apache.ibatis.type.Alias;

import com.wx.dto.BaseObject;

@Alias("baseGeogArea")
public class BaseGeogArea  extends BaseObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7236235700785440499L;
	
	private Long id;
	private String firstLetter;
	private Long areaNo;
	private String areaName;
	private Long parentAreaNo;
	private int areaLevel;
	private Integer startRow;
	private Integer rows;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstLetter() {
		return firstLetter;
	}
	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}
	public Long getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(Long areaNo) {
		this.areaNo = areaNo;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Long getParentAreaNo() {
		return parentAreaNo;
	}
	public void setParentAreaNo(Long parentAreaNo) {
		this.parentAreaNo = parentAreaNo;
	}
	public int getAreaLevel() {
		return areaLevel;
	}
	public void setAreaLevel(int areaLevel) {
		this.areaLevel = areaLevel;
	}
	public Integer getStartRow() {
		return startRow;
	}
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
//	private String areaCode;
//	private String areaType;
//	private String firstLetterEach;
//	private Date createTime;
//	private Date inputtime;
}
