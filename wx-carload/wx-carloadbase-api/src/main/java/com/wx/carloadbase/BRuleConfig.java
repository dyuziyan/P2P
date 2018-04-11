package com.wx.carloadbase;

import java.util.Map;

import my.comp.rmi.annotation.RemoteService;
@RemoteService("/bRuleConfig")
public interface BRuleConfig {

	BRule byType(String type);

	Map<String, BRule> byPattern(String pattern);

}
