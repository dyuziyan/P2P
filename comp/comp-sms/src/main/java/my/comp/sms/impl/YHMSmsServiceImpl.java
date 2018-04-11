package my.comp.sms.impl;

import my.comp.sms.SmsService;
import my.comp.sms.constant.YHMSmsDto;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class YHMSmsServiceImpl extends AbstractSmsService implements SmsService {

	private static final Logger logger = LoggerFactory.getLogger(YHMSmsServiceImpl.class);

	private static String userName;
	private static String password;
	private static String signature;

	private static final String joburl ="https://service.wellemail.com/Service/CreateTriggerSMS";
	private static final String smsurl="https://service.wellemail.com/Service/TriggerSMS";
	
	public void setSignature(String signature) {
		YHMSmsServiceImpl.signature = signature;
	}

	public void setUserName(String userName) {
		YHMSmsServiceImpl.userName = userName;
	}

	public void setPassword(String password) {
		YHMSmsServiceImpl.password = password;
	}
	
	public YHMSmsServiceImpl() {
		super();
	}

	public YHMSmsServiceImpl(String userName, String password, String signature,String sign){
		YHMSmsServiceImpl.userName = userName ;
		YHMSmsServiceImpl.password = password ;
		YHMSmsServiceImpl.signature = signature ;
		setSign(sign);
		try {
			this.setSign(sign);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String send(String mobile, String message) {
		return null;
	}

	@Override
	public String send(YHMSmsDto yHMSmsDto) {
		logger.debug("发送短信开始:"+yHMSmsDto.getMobiles().toString());
		String jsonResult = "";
		try {
			jsonResult = sendSms(yHMSmsDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("易回眸-发送完毕"+"jsonResult"+jsonResult+"/userName=="+userName+"/password=="+password+"/sign=="+getSign()+"/message=="+yHMSmsDto.getSmsTemplet());
		logger.debug("发送短信结束：" + jsonResult);
		return jsonResult;
	}
	
	
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
	
//	public static void createJob() throws Exception {
//		//String url ="https://service.wellemail.com/Service/CreateTriggerSMS";
//		List<NameValuePair> nvpr = new ArrayList<NameValuePair>();
//		nvpr.add(new BasicNameValuePair("userName", userName));
//		nvpr.add(new BasicNameValuePair("subAccount", ""));
//		nvpr.add(new BasicNameValuePair("password", passWord));
//		nvpr.add(new BasicNameValuePair("groupName", ""));
//		nvpr.add(new BasicNameValuePair("title", ""));
//		nvpr.add(new BasicNameValuePair("content", content));
//		nvpr.add(new BasicNameValuePair("jobName", jobName)); //用URLUTF8编码
//		nvpr.add(new BasicNameValuePair("mobileList", ""));
//		nvpr.add(new BasicNameValuePair("signature",signature)); //用URLUTF8编码
//		String retCode = openUrl(joburl, nvpr);
//	}

	public static String sendSms(YHMSmsDto YHMSmsDto) throws Exception {
		StringBuffer mobileList = new StringBuffer();
		mobileList.append("mobile,good,code");
//		for(int i = 0; i < YHMSmsDto.getCode().size(); i++) {  
//			mobileList.append(",code"+i);
//        }  
		/// 增加一行
		for (int i = 0; i < YHMSmsDto.getMobiles().size(); i++) {
			mobileList.append("\r\n");
			mobileList.append(YHMSmsDto.getMobiles().get(i));
			for(int j = 0; j < YHMSmsDto.getCode().size(); j++) {  
				mobileList.append(",");
				mobileList.append(URLEncoder.encode(YHMSmsDto.getCode().get("code"+j).get(i), "utf8"));
//				mobileList.append(YHMSmsDto.getCode().get("code"+j).get(i));
	        }
			mobileList.append(";");
		}
		List<NameValuePair> nvpr = new ArrayList<NameValuePair>();
		nvpr.add(new BasicNameValuePair("userName", userName));
		nvpr.add(new BasicNameValuePair("password", password));
		nvpr.add(new BasicNameValuePair("jobName", YHMSmsDto.getSmsTemplet())); 
		nvpr.add(new BasicNameValuePair("mobileList", mobileList.toString()));
//		nvpr.add(new BasicNameValuePair("signature",URLEncoder.encode(new String(signature.toString().getBytes("UTF-8")), "UTF-8")));
		nvpr.add(new BasicNameValuePair("signature",URLEncoder.encode(signature, "UTF-8")));
		logger.info("发送短信开始:"+nvpr);
		String retCode =openUrl(smsurl, nvpr);
		return retCode;
	}
}
