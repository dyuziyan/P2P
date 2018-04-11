package com.wx.carloadbase;

import java.util.Map;

import javax.annotation.Resource;

public class BRuleUtils {

	private static BRuleConfig bRuleConfig;

	@Resource
	public void setBRuleConfig(BRuleConfig bRuleConfig) {
		BRuleUtils.bRuleConfig = bRuleConfig;
	}

	public static BRule byType(String type) {
		return bRuleConfig.byType(type);
	}

	public static Map<String, BRule> byPattern(String pattern) {
		return bRuleConfig.byPattern(pattern);
	}

}
