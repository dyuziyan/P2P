package my.comp.pay.zfb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;

import my.comp.config.SysConf;
import net.sf.json.JSONObject;

public class zfbUtils {

	public static Logger logger = Logger.getLogger(zfbUtils.class);


	/**
	 * 支付宝app预支付
	 * @param outTradeNo  支付流水号
	 * @param totalAmount 支付金额
	 * @param subject 订单详情
	 * @param body
	 * @return
	 */
	public static String subZfbZfPay(String outTradeNo, String totalAmount, String subject, String body) {

		SysConfigValue sfv = SysConfigValue.getInstance();
		String  APP_ID = sfv.ZFB_PID;// 填写您的appid
		String  APP_PRIVATE_KEY = sfv.SH_PRIVATE_KEY;// 填写您的pkcs8的私钥
		String  ALIPAY_PUBLIC_KEY = sfv.ALI_PUBLIC_KEY;// 填写您的吧支付宝公钥
		String  ZFB_NOTIFY_UR = sfv.ZFB_NOTIFY_UR;// 异步回调地址
		
		// 签名方式
		String sign_type = "RSA2";
		// 编码格式
		String CHARSET = "utf-8";
		// 正式环境支付宝网关，如果是沙箱环境需更改成https://openapi.alipaydev.com/gateway.do
		String url = "https://openapi.alipay.com/gateway.do";
		// 实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient(url, APP_ID, APP_PRIVATE_KEY, "json", CHARSET,
				ALIPAY_PUBLIC_KEY, sign_type);
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();

		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody(body);
		model.setSubject(subject);
		// 请保证OutTradeNo值每次保证唯一
		model.setOutTradeNo(outTradeNo);
		model.setTimeoutExpress("30m");
		model.setTotalAmount(totalAmount);
		//测试阶段间金额设置为0.01
		if(1 == sfv.testSwitch){
			model.setTotalAmount("0.01");
		}
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl(ZFB_NOTIFY_UR);// 商户外网可以访问的异步地址
		try {
			// 这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			// 就是orderString 可以直接给客户端请求，无需再做处理。
			logger.info("*****************************支付宝预支付：" + response.getBody());
			return response.getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 支付宝交易查询
	 * @param outTradeNo 支付订单导
	 * @param tradeNo 支付宝交易号
	 * @return
	 */
	public static JSONObject alipayTradeQuery(String outTradeNo, String tradeNo) {
		try {
			// String zfb_pid="2018032002412480";
			// String
			// sh_private_key="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCtwXux8QPegKbSKrc6xRvzhqGRICxbpA2KCYV4TARQxmohE3H3nCxuoNtcITPk0fBtJmf7/Dq3FTZiR/JHJA+hRv/rLoAqno+C26qsImoVCrCkNmXZLRY6L7Adzzj36IEz8pTocCCxrftjl3sZXPZKMZ12E6KCOA36TfDKAPGng0wAMMeIlwvoSm1bHLpy3bhJSQqd4/WvPNL7Kd8gb5nbKL2NeDcgASvAXNPshPk0J9TwWpsoZYgtDSwpSHQ9S2L+IoChPpkv75U+Rl5EwIyIh8MESOeVErETOnjmxXJ4vHV5Z3cSlU5IZOfmgWPW0DkMBKfTcXVlPJ3WHrPV3GPzAgMBAAECggEAVxP8NbUfx0aaVAFsSlu2Ill6ZwzErjiNQHx9E0ilcQW+8kyYdQsjiV28EXPiWe6Mm2b//dzuYAqVcrOzy8taueynHOn0+Wb1bRNBeXrVxy3QULuiVzCtTwPPM2FCn5s6BRqxXbuz5tfWgJbN7mwUUiKDU0WLz87f0m11GwdWFCh3EhQZBT8cTJ/msOgnlNRDD8gnQdPQmRBTJ55E9OyCgLSjzT/iXxdqEW+oTOyIykSsQH8NOgWvYkTwYIJXotkETNwLN7fb7DM0OWhOoueN9YYuPwQ/kc3CpDRVDa3Ss6xoSrdWZ2QcjpsMqcberE5KfGAftNPMDZnXhyzneTsRAQKBgQD00wU6+vMrhlwcAp5Twnb7ChUksZyRd1TPuJhc1TpBBPSXrCQUfc2x3qgKYt7rlH5puak1wqEskYT9hPFbIQWJKZEuiioGbv87HHVmGlo0//JKh4oHD80qatJxVco0IKzab5wJnk0+lY7q92gbLCOiizjjo2XwWXawI1d3vMEO0wKBgQC1r/eNOozy53ahxx8GV6PBPL7tS/By/WDrCRDNkCF9xdeT1ESEqgs/crM++sE6D6v8pgCy3Z9ewcDXtQ+7MafZWRnw0U/YR4ot8tPOZMdMPSH9g1305RH8XpXpzqDjOyqLHQDDi7UfDEmpttucYp1x6TCQLQZOYW9G48lFlzNiYQKBgQC5FWLJ4Pe++tddkZkyRzx9snSjheaQjj8+vhXpcIWWq/FhTJEGMMk1Y9dz6qUOQfeDZAmJC/ee4vBAXKW+N+ugcx0/qtH0hStM+8nBA8EYejoon+j4R7jaDWHEro+r8tvaUzAKkXNiEdy2w40fpbHW6xt59IhXOLsik8VHTxpAYwKBgQCISz/uTwzrLx28UI6CiH4MeNBVXNE8wbzRtBRJ0KR/IEoO2H6p8pHLf/Tc+ui28Ii5V6JQi5aBU+GhL29kouRiVMfz8mDuQwRyYaXoP4uOMpHsBn8WNj0ez6mwIlRpAbpxb0liZ8xwRRyBVu45y3SGvkKy9UO0yrzaNgiHztw6IQKBgQDsPzOwHWs/ksDkVRwAgWsliTMATd1tsXjD6oJ+lwL8ZRh4M8kNrq+O7+vaCbS1f1kvkPVfWfp4Z/vC7OibQEXQ2bd4FWAVd/dDC4SjZ+3hFe2b1S8Xu8nH82SzU8CmQr+6Xeip+eBy77xuHtlwAyk1Els0dU4aUd/QJloRNnA4GQ==";
			// String
			// ali_public_key="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq9euhD926Q+QDdF9tHNa/7+cDmPi6smYytmcJKC7NLApxxVGyrX+offrJg+HkTKpue5vASzs5leZd/QEadLy98fgNzHCt+YnHrk6PU2N9qBPWrG/lL8NhhH4fiqNWtOElRR/KIGK+/0AoNIWkycp6Lfvr8ej9U7luMpirPnpRCOUCu0KWzYTgMBRnfk+spEOSkg9iD4uWjSUNzrEa2Tj4q5TsEZ88dk8WVBIvrB68nCnOvYQWqt84tKdKM+gCWXHCnoxwWUgEKQBfAcsPa90ThFGNaP/d4nWYaOebDdd6k1oAD2T2/jYYEyXorcZjyfr3WEH+VUtiiWxqfLAFbKrGwIDAQAB";

			if (StringUtils.isBlank(outTradeNo) || StringUtils.isBlank(tradeNo)) {
				return null;
			}
			
			SysConfigValue sfv = SysConfigValue.getInstance();
			String  APP_ID = sfv.ZFB_PID;// 填写您的appid
			String  APP_PRIVATE_KEY = sfv.SH_PRIVATE_KEY;// 填写您的pkcs8的私钥
			String  ALIPAY_PUBLIC_KEY = sfv.ALI_PUBLIC_KEY;// 填写您的吧支付宝公钥
			String  ZFB_NOTIFY_UR = sfv.ZFB_NOTIFY_UR;// 异步回调地址

			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID,
					APP_PRIVATE_KEY, "json", "GBK", ALIPAY_PUBLIC_KEY, "RSA2");
			AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
			JSONObject paramJson = new JSONObject();
			paramJson.put("out_trade_no", outTradeNo);
			paramJson.put("trade_no", tradeNo);
			request.setBizContent(paramJson.toString());
			AlipayTradeQueryResponse response = alipayClient.execute(request);
			JSONObject json = new JSONObject();
			if (response.isSuccess()) {
				// 交易状态：WAIT_BUYER_PAY（交易创建，等待买家付款）、TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、
				// TRADE_SUCCESS（交易支付成功）、TRADE_FINISHED（交易结束，不可退款）
				json.accumulate("tradeStatus", response.getTradeStatus());
				json.accumulate("alipayStoreId", response.getAlipayStoreId());// 商户门店编号
				json.accumulate("buyerLogonId", response.getBuyerLogonId());// 买家支付宝账号
				json.accumulate("buyerPayAmount", response.getBuyerPayAmount());// 买家实付金额
				json.accumulate("buyerUserId", response.getBuyerUserId());// 买家在支付宝的用户id
				json.accumulate("discountGoodsDetail", response.getDiscountGoodsDetail());
				json.accumulate("industrySepcDetail", response.getIndustrySepcDetail());
				json.accumulate("invoiceAmount", response.getInvoiceAmount());
				json.accumulate("outTradeNo", response.getOutTradeNo());
				json.accumulate("pointAmount", response.getPointAmount());
				json.accumulate("receiptAmount", response.getReceiptAmount());
				json.accumulate("storeId", response.getStoreId());
				json.accumulate("storeName", response.getStoreName());
				json.accumulate("terminalId", response.getTerminalId());
				json.accumulate("totalAmount", response.getTotalAmount());
				json.accumulate("tradeNo", response.getTradeNo());
				json.accumulate("sendPayDate", response.getSendPayDate());// 本次交易打款给卖家的时间
			}
			return json;
		} catch (Exception e) {
			logger.error("支付宝交易查询发生异常:" + e);
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		/*zfbUtils zfb = new zfbUtils();
		JSONObject str = zfb.alipayTradeQuery("P20180326093058692026", "2018032621001004480571082804");
		System.out.println(str);*/
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("bjid", "1");
		params.put("okkdl", "2");
		params.put("aklo", "3");
		params.put("mlood", "4");
		params.put("aaaer", "5");
		System.out.println(createLinkString(params));
	}
	
	public static String createLinkString(Map<String, String> params)
    {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++)
        {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1)
            {
                // 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + "\"" + value + "\"";
            }
            else
            {
                prestr = prestr + key + "=" + "\"" + value + "\"" + "&";
            }
        }

        logger.info(prestr);
        return prestr;
    }
	
}
