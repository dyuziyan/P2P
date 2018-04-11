package com.wx.msg;

import java.util.Properties;

import my.comp.jms.utils.InetAddressUtils;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class SystemPropertiesFactory implements FactoryBean<Properties> {

	@Override
	public Properties getObject() throws Exception {
		Properties p = new Properties();
		p.put("env.server.ip", InetAddressUtils.getLocalHostAdress());
		
		return p;
	}

	@Override
	public Class<?> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
