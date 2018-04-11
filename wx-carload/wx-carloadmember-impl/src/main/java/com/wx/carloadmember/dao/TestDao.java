package com.wx.carloadmember.dao;

import my.comp.dao.mybatis.BaseDao;
import my.comp.dao.mybatis.MybatisDao;
import org.apache.ibatis.annotations.Param;

import com.wx.carloadmember.domain.TestModel;

import java.util.List;

@MybatisDao
public interface TestDao extends BaseDao {

    List<TestModel> testSelect(@Param("ID") Long ID);
  
}
