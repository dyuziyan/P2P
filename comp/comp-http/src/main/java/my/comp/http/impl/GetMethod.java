package my.comp.http.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import my.comp.http.HttpException;
import my.comp.http.HttpMethod;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

public abstract class GetMethod<R, P> implements HttpMethod<R, P> {

	protected int timeout = 60 * 60 * 1000;// 默认一小时
	protected String charset = "UTF-8";
	protected Map<String, String> headers;

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}
	
	public void setHeader(String name, String value) {
		if (headers == null) headers = new HashMap<String, String>();
		headers.put(name, value);
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	@Override
	public R invoke(String url, P params) throws HttpException {
		HttpClient httpclient = doGetHttpClient(url);
		HttpGet httpPost = doGetHttpGet(url, params);
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
			return doGetResult(response);
		} catch (Exception e) {
			throw new HttpException(e);
		} finally {
			// 关闭连接.
			httpPost.releaseConnection();
			HttpClientUtils.closeQuietly(response);
			HttpClientUtils.closeQuietly(httpclient);
		}
	}

	protected boolean isSSL(String url) {
		return url != null && url.toLowerCase().trim().startsWith("https");
	}

	protected HttpClient doGetHttpClient(String url) throws HttpException {
		if (isSSL(url)) return doGetSSLClient();
		return HttpClients.createDefault();
	}

	protected HttpClient doGetSSLClient() throws HttpException {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new AllTrustStrategy()).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			HttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			return httpclient;
		} catch (Exception e) {
			throw new HttpException(e);
		}
	}

	protected HttpGet doGetHttpGet(String url, P params) throws HttpException {
		HttpGet httpGet = new HttpGet(url);
		doSetParameter(httpGet, params);
		final int TIMEOUT = timeout;
		if (headers != null) {
			for (Entry<String, String> header : headers.entrySet()) {
				httpGet.setHeader(header.getKey(), header.getValue());
			}
		}
		httpGet.setConfig(RequestConfig.custom().setConnectTimeout(TIMEOUT).setConnectionRequestTimeout(TIMEOUT).setSocketTimeout(TIMEOUT)
				.build());// 设置2分钟50秒连接超时时间

		return httpGet;
	}

	protected abstract R doGetResult(HttpResponse response) throws HttpException;

	protected abstract void doSetParameter(HttpGet httpGet, P params) throws HttpException;
}
