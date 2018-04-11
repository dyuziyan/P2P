package com.wx.carloadbase.dao;

import java.util.List;
import java.util.Map;

import my.comp.dao.mybatis.BaseDao;
import my.comp.dao.mybatis.MybatisDao;

import com.wx.carloadbase.dto.Code;

@MybatisDao
public interface CodeDao extends BaseDao<Code>
{
    Code getCode(@SuppressWarnings("rawtypes") Map map);
    
    List<Code> getCodesByType(String type);
}
