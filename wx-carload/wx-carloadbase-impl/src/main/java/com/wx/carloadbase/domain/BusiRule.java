package com.wx.carloadbase.domain;

import org.apache.ibatis.type.Alias;

/**
 * 基础数据-业务规则
 */
@Alias("busiRule")
public class BusiRule {
	private Long id; // 规则代码
	private String type; // 规则类型
	private String name; // 规则名称
	private String value; // 规则配置
	private String remark; // 备注

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

    @Override
    public String toString()
    {
        return "BusiRule [id=" + id + ", type=" + type + ", name=" + name + ", value=" + value + ", remark=" + remark
                + "]";
    }
	
	

}
