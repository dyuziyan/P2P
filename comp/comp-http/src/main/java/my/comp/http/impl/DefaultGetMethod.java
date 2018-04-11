package my.comp.http.impl;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import my.comp.http.HttpException;
import my.comp.lang.XMapUtils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class DefaultGetMethod extends GetMethod<String, Map<String, Object>> {

	public DefaultGetMethod(String charset) {
		super.charset = charset;
	}

	@Override
	protected String doGetResult(HttpResponse response) throws HttpException {
		try {
			return EntityUtils.toString(response.getEntity(), Charset.forName(super.charset));
		} catch (Exception e) {
			throw new HttpException(e);
		}
	}

	@Override
	protected void doSetParameter(HttpGet httpGet, Map<String, Object> params) throws HttpException {
		List<NameValuePair> paramlist = new ArrayList<NameValuePair>();
		for (String name : params.keySet()) {
			paramlist.add(new BasicNameValuePair(name, XMapUtils.getString(params, name)));
		}

		String paramstr = URLEncodedUtils.format(paramlist, super.charset);

		String url = httpGet.getURI().toString();
		String s = url.indexOf("?") > 0 ? "&" : "?";
		String paramurl = url + s + paramstr;
		httpGet.setURI(URI.create(paramurl));
	}

}
