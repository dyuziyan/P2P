package com.wx.carloadbase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.wx.carloadbase.BRule;
import com.wx.carloadbase.BRuleConfig;
import com.wx.carloadbase.domain.BusiRule;
import com.wx.carloadbase.service.BusiRuleService;


@Component("bRuleConfigImpl")
public class BRuleConfigImpl implements BRuleConfig{
	
	@Resource
	private BusiRuleService busiRuleService;

	public BRule byType(String type) {
		BRule rule = new BRule();
		List<BusiRule> maplist = busiRuleService.listByType(type);

		for (int i = 0; i < maplist.size(); i++) {
			BusiRule map = maplist.get(i);
			if (i == 0) {
				rule.setType(map.getType());
			}

			String key = map.getName();
			String value = map.getValue();

			rule.setProperty(key, value);
		}

		return rule;
	}

	public Map<String, BRule> byPattern(String pattern) {
		Map<String, BRule> rules = new HashMap<String, BRule>();
		List<BusiRule> maplist = busiRuleService.listByPattern(pattern);
		for (BusiRule map : maplist) {
			String type = map.getType();
			BRule rule = rules.get(type);
			if (rule == null) {
				rule = new BRule();
				rule.setType(type);
				rules.put(type, rule);
			}
			String key = map.getName();
			String value = map.getValue();
			rule.setProperty(key, value);
		}

		return rules;
	}

}
