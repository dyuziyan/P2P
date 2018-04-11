package my.comp.hessian.client;

import java.net.URL;
import java.util.Map;

import com.caucho.hessian.client.HessianConnection;
import com.caucho.hessian.client.HessianProxy;
import com.caucho.hessian.client.HessianProxyFactory;

public class HessianProxy2 extends HessianProxy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, String> headers;

	protected HessianProxy2(URL url, HessianProxyFactory factory, Class<?> type, Map<String, String> headers) {
		super(url, factory, type);
		this.headers = headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	protected void addRequestHeaders(HessianConnection conn) {
		super.addRequestHeaders(conn);
		if (headers == null) return;
		for (String key : headers.keySet()) {
			conn.addHeader(key, headers.get(key));
		}
	}

}
