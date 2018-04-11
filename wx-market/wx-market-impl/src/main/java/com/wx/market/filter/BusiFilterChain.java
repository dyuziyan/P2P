package com.wx.market.filter;

import java.util.List;

import com.wx.enums.common.BusiEvent;
import com.wx.market.BusiParam;
import com.wx.market.BusiResult;

public class BusiFilterChain {

	private BusiEvent event;

	private List<BusiFilter> filters;

	public BusiEvent getEvent() {
		return event;
	}

	public void setEvent(BusiEvent event) {
		this.event = event;
	}

	public List<BusiFilter> getFilters() {
		return filters;
	}

	public void setFilters(List<BusiFilter> filters) {
		this.filters = filters;
	}

	public BusiResult doFilter(BusiParam param) {
		BusiResult result = null;
		try {
			for (BusiFilter filter : filters) {
				filter.doFilter(param);
			}
			result = BusiResult.success();
		} catch (Exception e) {
			result = BusiResult.error(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
