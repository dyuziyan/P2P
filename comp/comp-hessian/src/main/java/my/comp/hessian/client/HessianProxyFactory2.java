package my.comp.hessian.client;

import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.Map;

import com.caucho.hessian.client.HessianProxyFactory;
import com.caucho.hessian.io.HessianRemoteObject;

public class HessianProxyFactory2 extends HessianProxyFactory {

	private Map<String, String> headers;

	protected HessianProxyFactory2(Map<String, String> headers, long readTimeout, long connectTimeout) {
		this.headers = headers;
		// 设置超时时间
		this.setReadTimeout(readTimeout);
		this.setConnectTimeout(connectTimeout);
		this.setOverloadEnabled(true);
	}

	public Object create(Class<?> api, URL url, ClassLoader loader) {
		if (api == null) throw new NullPointerException("api must not be null for HessianProxyFactory.create()");
		HessianProxy2 handler = null;

		handler = new HessianProxy2(url, this, api, headers);

		return Proxy.newProxyInstance(loader, new Class[] { api, HessianRemoteObject.class }, handler);
	}
}
