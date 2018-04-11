package my.comp.http.impl;

import java.nio.charset.Charset;

import my.comp.http.HttpException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

public class DefaultPostMethod extends PostMethod<String, String>{

	public DefaultPostMethod(String charset) {
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
	protected void doSetParameter(HttpPost httpPost, String params) throws HttpException {
		httpPost.setEntity(new StringEntity(params, super.charset));
	}

}
