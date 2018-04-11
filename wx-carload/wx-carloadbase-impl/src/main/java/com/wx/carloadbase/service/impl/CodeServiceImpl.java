package com.wx.carloadbase.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wx.carloadbase.dto.Code;
import com.wx.carloadbase.service.CodeService;
import com.wx.carloadbase.dao.CodeDao;

@Service
public class CodeServiceImpl implements CodeService
{

    @Resource
    private CodeDao codeDao;
    @Override
    public Code getCode(String type, String code)
    {
        Map<String,String> map  = new HashMap<String, String>();
        map.put("type", type);
        map.put("code", code);
        return codeDao.getCode(map);
    }
    
    @Override
    public List<Code> getCodesByType(String type){
    	return codeDao.getCodesByType(type);
    }

}
