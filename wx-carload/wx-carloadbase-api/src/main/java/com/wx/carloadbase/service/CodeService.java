package com.wx.carloadbase.service ;

import java.util.List;

import com.wx.carloadbase.dto.Code;

import my.comp.rmi.annotation.RemoteService;



@RemoteService("/codeService")
public interface CodeService
{
    Code getCode(String type,String code);
 
    List<Code> getCodesByType(String type);
}
