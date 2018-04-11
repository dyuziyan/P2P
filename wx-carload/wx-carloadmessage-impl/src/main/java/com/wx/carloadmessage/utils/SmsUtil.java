package com.wx.carloadmessage.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.wx.support.Messages;

public class SmsUtil {
	private static CloseableHttpResponse getResponse(
			String url,
			List<NameValuePair> nvps,
			CloseableHttpClient httpclient) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));

		return httpclient.execute(httpPost);
	}

	private static String getInputStream(CloseableHttpResponse response)
			throws Exception {
		InputStream instream = null;
		String entity = EntityUtils.toString(response.getEntity(), "utf-8");
//		if (entity != null) {
//			instream = entity.getContent();
//		}
		return entity;
	}

	private static String streamToString(InputStream in) throws IOException {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}
		return out.toString();
	}

	private static String openUrl(String url, List<NameValuePair> nvps)
			throws Exception {
		CloseableHttpResponse response = null;
		CloseableHttpClient httpclient = null;

		try {
			if (url.startsWith("https")) {
				httpclient = createSSLClientDefault();
			} else {
				httpclient = HttpClients.createDefault();
			}
			response = getResponse(url, nvps, httpclient);
			return getInputStream(response);
//			try {
//				return streamToString(instream);
//			} catch (IOException ex) {
//				throw ex;
//			} finally {
//				instream.close();
//			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				response.close();
				httpclient.close();
			} catch (IOException e) {
				throw e;
			}
		}
	}
	

	public static CloseableHttpClient createSSLClientDefault() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
					null,
					new TrustStrategy() {
						//信任所有
						public boolean isTrusted(
								X509Certificate[] chain,
								String authType) throws CertificateException {
							return true;
						}
					}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();
	}

	/*//测试任务名，正式中不要使用
	private static String jobName = "wxcf";
	//private String content = "您好，您在易回眸官网购买的商品已发货，$$data.good$$ 快递单号：$$data.code$$，请注意查收，如有问题请联系客服。";
	private static String content = "您好，您在五星财富注册账号验证码为，$$data.good$$";
	//private static String userName = "dxjp";//正式环境
	//private static String passWord = "91999cff";//正式环境
	private static String userName = "dxjptest";
	private static String passWord = "77e2c98a";
	private static String signature = "易回眸";
	private static joburl ="https://service.wellemail.com/Service/CreateTriggerSMS";
	private static smsurl ="https://service.wellemail.com/Service/TriggerSMS";
	*/
	
	//测试任务名，正式中不要使用
	private static String jobName = Messages.getMessage("jobName");
	//private String content = "您好，您在易回眸官网购买的商品已发货，$$data.good$$ 快递单号：$$data.code$$，请注意查收，如有问题请联系客服。";
	private static String content = Messages.getMessage("content");
	//private static String userName = "dxjp";//正式环境
	//private static String passWord = "91999cff";//正式环境
	private static String userName = Messages.getMessage("userName");
	private static String passWord = Messages.getMessage("passWord");
	private static String signature = Messages.getMessage("signature");
	private static String joburl =Messages.getMessage("joburl");
	private static String smsurl =Messages.getMessage("smsurl");
	
	/**
	 * 添加模板
	 * @param _content 模板
	 * @param _jobName 模板名称
	 * @return
	 * @throws Exception
	 */
	public static String createJob(String _content,String _jobName) throws Exception {
		//String url ="https://service.wellemail.com/Service/CreateTriggerSMS";
		List<NameValuePair> nvpr = new ArrayList<NameValuePair>();
		nvpr.add(new BasicNameValuePair("userName", userName));
		nvpr.add(new BasicNameValuePair("subAccount", ""));
		nvpr.add(new BasicNameValuePair("password", passWord));
		nvpr.add(new BasicNameValuePair("groupName", ""));
		nvpr.add(new BasicNameValuePair("title", ""));
		if(StringUtils.isNotBlank(_content)&&StringUtils.isNotBlank(_jobName)){
			content = _content;
			jobName = _jobName;
		}
		nvpr.add(new BasicNameValuePair("content", URLEncoder.encode(content,"utf8")));
		nvpr.add(new BasicNameValuePair("jobName", URLEncoder.encode(jobName,"utf8"))); //用URLUTF8编码
		nvpr.add(new BasicNameValuePair("mobileList", ""));
		nvpr.add(new BasicNameValuePair("signature",URLEncoder.encode(signature,"utf8"))); //用URLUTF8编码
		String retCode = openUrl(joburl, nvpr);
		System.out.println(retCode);
		return retCode;
	}
	/**
	 * 
	 * @param mobiles 手机号（可多个）
	 * @param good 内容
	 * @param code 内容
	 * @param _jobName 模板名称（不传使用默认配置模板）
	 * @return
	 * @throws Exception
	 */
	public static String sendSms(String mobiles,String good,String code,String _jobName) throws Exception {
		//String url = "https://service.wellemail.com/Service/TriggerSMS";
		StringBuffer sb = new StringBuffer();
		sb.append("mobile,good,code");
		/// 增加一行
		String[] temp = mobiles.split(",");
		for (int i = 0; i < temp.length; i++) {
			sb.append("\r\n");
			sb.append(temp[i]);
			sb.append(",");
			sb.append(URLEncoder.encode(good, "utf8"));
			if(StringUtils.isNotEmpty(code)){
				sb.append(",");
				sb.append(code);
			}
			if(temp.length > 1){
				sb.append(";");
			}
		}
		List<NameValuePair> nvpr = new ArrayList<NameValuePair>();
		nvpr.add(new BasicNameValuePair("userName", userName));
		nvpr.add(new BasicNameValuePair("password", passWord));
		if(StringUtils.isNotBlank(_jobName)){
			jobName = _jobName;
		}
		nvpr.add(new BasicNameValuePair("jobName", URLEncoder.encode(jobName,"utf8"))); //用URLUTF8编码
		nvpr.add(new BasicNameValuePair("mobileList", sb.toString()));
		nvpr.add(new BasicNameValuePair("signature", URLEncoder.encode(signature,"utf8"))); //用URLUTF8编码
		String retCode =openUrl(smsurl, nvpr);
		System.out.println(retCode);
		return retCode;
	}

	public static void main(String[] args) throws Exception {
	//	SmsUtil testPost = new SmsUtil();
		// 一个任务只用创建一次
//		testPost.createJob();
	//	testPost.sendSms("13005462992","验证码：456656","党纪国法");
		
		//System.out.println("****************"+SysConf.get("wx.market.url"));
	}
}
