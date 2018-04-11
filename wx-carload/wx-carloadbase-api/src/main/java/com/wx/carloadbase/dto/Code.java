package com.wx.carloadbase.dto;

import java.io.Serializable;


public class Code implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
    //类型
    private String type;
    //编码
    private String code;
    //中文文本
    private String label;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    @Override
    public String toString()
    {
        return "Code [id=" + id + ", type=" + type + ", code=" + code + ", label=" + label + "]";
    }
    

}
