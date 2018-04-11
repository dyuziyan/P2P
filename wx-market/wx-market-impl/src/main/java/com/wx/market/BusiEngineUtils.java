package com.wx.market;

public class BusiEngineUtils {

	private static BusiEngine engine;

	public void setEngine(BusiEngine engine) {
		BusiEngineUtils.engine = engine;
	}

	static BusiEngine getEngine() {
		return engine;
	}

}
