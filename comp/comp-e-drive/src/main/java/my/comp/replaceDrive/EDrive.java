package my.comp.replaceDrive;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import my.comp.codec.MD5Utils;
import my.comp.http.impl.DefaultGetMethod;
import my.comp.http.impl.DefaultPostMethod;
import my.comp.lang.StringUtils;
import net.sf.json.JSONObject;

/**
 * e代驾api
 * @author 13697
 *
 */
public class EDrive{
	
	private String channel;//渠道号
	private String customerId;//商户id
	private String privateKey;//私钥
	
	private static final Logger logger = LoggerFactory.getLogger(EDrive.class);
	
	public EDrive(){
		this.channel = "40";
		this.customerId = "CH0110000223";
		this.privateKey = "a";
	}
	
	public static void main(String[] args) {
		EDrive test = new EDrive();
		SortedMap<String, Object> params = new TreeMap<String, Object>();
		
		params.put("createMobile","13005462991");//	下单人手机号
		params.put("mobile","13005462991");//	车主手机号
		params.put("username","测试用户1");//	车主姓名
		params.put("pickupContactName","测试用户2");//	取车地址联系人姓名
		params.put("pickupContactPhone","13005462991");//	取车地址联系人手机号
		params.put("pickupAddress","八卦三路301号中国平安大厦");//	取车地址
		params.put("pickupAddressLng",Double.valueOf("114.094679"));//	取车地址经度
		params.put("pickupAddressLat",Double.valueOf("22.56162"));//	取车地址纬度
		params.put("returnContactName","测试商家用户");//	还车地址联系人姓名
		params.put("returnContactPhone","13777777777");//	还车地址联系人手机号
		params.put("returnAddress","滨河金田立交与金田路交叉口西北100米");//	还车地址
		params.put("returnAddressLng",Double.valueOf("114.062479"));//	还车地址经度
		params.put("returnAddressLat",Double.valueOf("22.530821"));//	还车地址纬度
		params.put("bookingTime","20180411154022");//	预约时间  yyyyMMddHHmmss
		params.put("carNo","粤nb888");//	车牌号
		params.put("carBrandName","布加迪龙威");//	车辆品牌名称
		params.put("carSeriesName","16.4");//	车辆车系名称
		
		JSONObject map = test.createOrder(params);
	}
	
	/***
     * 将对象转换为HashMap
     * @param object
     * @return
     */
    public static HashMap toHashMap(Object object)
    {
        HashMap<String, Object> data = new HashMap<String, Object>();
        JSONObject jsonObject = JSONObject.fromObject(object);
        Iterator it = jsonObject.keys();
        while (it.hasNext())
        {
            String key = String.valueOf(it.next());
            Object value = jsonObject.get(key);
            data.put(key, value);
        }
        return data;
    }
    
    
	/**
	 * 按照第一个字符的键值ASCII码递增排序（字母升序排序），如果遇到相同字符则按照第二个字符的键值ASCII码递增排序，以此类推。
	 * @param params
	 * @return Map<String, String> value paramPrestr : a="v1"&b="v2"&c="v3";  prestr : a="v1"b="v2"c="v3"
	 */
	public Map<String, Object> createLinkString(SortedMap<String, Object> params)
    {
		Map<String, Object> map = new HashMap<String, Object>();
        List<String> keys = new ArrayList<String>(params.keySet());
        String paramPrestr = "";
        String prestr = "";
        for (int i = 0; i < keys.size(); i++)
        {
            String key = keys.get(i);
            Object value = params.get(key);
            if (i == keys.size() - 1)
            {
                // 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" +  value;
                paramPrestr = paramPrestr + key + "=" +  value;
            }
            else
            {
                prestr = prestr + key + "="+ value ;
                paramPrestr = paramPrestr + key + "=" +  value+ "&";
            }
        }
        map.put("prestr", prestr);
        map.put("paramPrestr", paramPrestr);
        return map;
    }
		
	
	/**
	 *  签名示例
	 *	传入参数：b=v2&a=v1&c=v3
	 *	排序后字符串：a=v1b=v2c=v3
	 *	md5后字符串：8ef1bd921d358a6db0f84ff1924bfb5c
	 *	md5拼上私钥后字符串：8ef1bd921d358a6db0f84ff1924bfb5ca
	 *  最终加密后字符串：0a915eca055e0c829f65ffd2b065cd84
	 *  最终传入参数：b=v2&a=v1&c=v3&sign=0a915eca055e0c829f65ffd2b065cd84
	 * @param resultParam
	 */
	public Map<String, String> autograph(SortedMap<String, Object> params){
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			Map<String, Object> paramSort = createLinkString(params);
			String paramMD5 =  MD5Utils.md5(paramSort.get("prestr").toString(), "UTF-8");
			String paramMD5PrivateKey =  MD5Utils.md5(paramMD5+privateKey, "UTF-8");
			String url = paramSort.get("paramPrestr") + "&sign=" + paramMD5PrivateKey;
			resultMap.put("sign", paramMD5PrivateKey);
			resultMap.put("url", url);
			return resultMap;
//			return paramMD5PrivateKey;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 3.2	获取商户的账号余额
	 * @return
	 */
	public JSONObject businessBalance(){
		SortedMap<String, Object> params = new TreeMap<String, Object>();
		params.put("channel", channel);
		params.put("customerId",customerId);
		try {
			String WEB_URL ="https://baoyang.d.edaijia.cn/api/third/2/business/balance";
			DefaultGetMethod post = new DefaultGetMethod("GBK");
			String sign = this.autograph(params).get("sign");
			params.put("sign",sign);
			String ret = post.invoke(WEB_URL, params);
			if (StringUtils.isEmpty(ret)) {
				return null;
			}
			//HashMap<String,Object> map = toHashMap(ret);
			System.out.println("************  获取商户的账号余额      *******:"+ret);
			return JSONObject.fromObject(ret);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 3.5	获取预估价格
	 * @param startLng 出发地经度
	 * @param startLat 出发地纬度
	 * @param endLng 目的地经度
	 * @param endLat 目的地纬度
	 * @param bookingTime 预约时间 yyyyMMddHHmmss
	 * @return
	 */
	public JSONObject estimatePrice(Double startLng,Double startLat,Double endLng,Double endLat,String bookingTime){
		try {
			
			SortedMap<String, Object> params = new TreeMap<String, Object>();
			params.put("channel", channel);
			params.put("customerId",customerId);
			params.put("startLng",startLng.toString());
			params.put("startLat",startLat.toString());
			params.put("endLng",endLng.toString());
			params.put("endLat",endLat.toString());
			params.put("bookingTime",bookingTime);
			
			String WEB_URL ="https://baoyang.d.edaijia.cn/api/third/2/price";
			DefaultGetMethod post = new DefaultGetMethod("GBK");
			String sign = this.autograph(params).get("sign");
			params.put("sign",sign);
			
			String ret = post.invoke(WEB_URL, params);
			if (StringUtils.isEmpty(ret)) {
				return null;
			}
			System.out.println("************  获取预估价格      *******:"+ret);
			return JSONObject.fromObject(ret);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 生成代驾订单
	 * @param params{
	 *   createMobile	下单人手机号
	 *   mobile	车主手机号
	 *   username	车主姓名
	 *   pickupContactName	取车地址联系人姓名
	 *   pickupContactPhone	取车地址联系人手机号
	 *   pickupAddress	取车地址
	 *   pickupAddressLng	取车地址经度
	 *   pickupAddressLat	取车地址纬度
	 *   returnContactName	还车地址联系人姓名
	 *   returnContactPhone	还车地址联系人手机号
	 *   returnAddress	还车地址
	 *   returnAddressLng	还车地址经度
	 *   returnAddressLat	还车地址纬度
	 *   bookingTime	预约时间  yyyyMMddHHmmss
	 *   carNo	车牌号
	 *   carBrandName	车辆品牌名称
	 *   carSeriesName	车辆车系名称
	 * }
	 * @return {"code": 0, "message": "success", "data": 335}  data代驾订单
	 */
	public JSONObject createOrder(SortedMap <String, Object> params){
		try {
			
			params.put("channel", channel);
			params.put("customerId",customerId);
			params.put("type",1);
			params.put("mode",1);
			
			String WEB_URL ="https://baoyang.d.edaijia.cn/api/third/2/order/create";
			DefaultPostMethod post = new DefaultPostMethod("UTF-8");
			post.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=GBK");
			String ret = post.invoke(WEB_URL, this.autograph(params).get("url"));
			
//			 List<String> keys = new ArrayList<String>(params.keySet());
//			 List<NameValuePair> params1 = new ArrayList<NameValuePair>();
//		        for (int i = 0; i < keys.size(); i++)
//		        {
//		            String key = keys.get(i);
//		            Object value = params.get(key);
//		            params1.add(new BasicNameValuePair(key, value+""));
//		        }
//		        
//			HttpClientHelp httpClientHelp = new HttpClientHelp();
//			String ret = httpClientHelp.byPostMethod(WEB_URL, params1, "UTF-8");
			
		
			
			if (StringUtils.isEmpty(ret)) {
				return null;
			}
			System.out.println("************  生成代驾订单      *******:"+ret);
			return JSONObject.fromObject(ret);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 取消订单
	 * @param orderId e代驾订单号
	 * @return
	 */
	public JSONObject cancelOrder(String orderId){
		try {
			SortedMap<String, Object> params = new TreeMap<String, Object>();
			params.put("channel", channel);
			params.put("orderId",orderId);
			String WEB_URL ="https://baoyang.d.edaijia.cn/api/third/2/order/cancel";
			DefaultPostMethod post = new DefaultPostMethod("GBK");
			String sign = this.autograph(params).get("sign");
			params.put("sign",sign);
			JSONObject json = JSONObject.fromObject(params);
			String ret = post.invoke(WEB_URL, json.toString());
			if (StringUtils.isEmpty(ret)) {
				return null;
			}
			System.out.println("************  取消订单      *******:"+ret);
			return JSONObject.fromObject(ret);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 3.8	获取订单详情
	 * @param orderId
	 * @return
	 */
	public JSONObject getOrderDetail(String orderId){
		try {
			SortedMap<String, Object> params = new TreeMap<String, Object>();
			params.put("channel", channel);
			params.put("orderId",orderId);
			String WEB_URL ="https://baoyang.d.edaijia.cn/api/third/2/order/detail";
			DefaultGetMethod post = new DefaultGetMethod("GBK");
			String sign = this.autograph(params).get("sign");
			params.put("sign",sign);
			
			String ret = post.invoke(WEB_URL, params);
			if (StringUtils.isEmpty(ret)) {
				return null;
			}
			System.out.println("************  获取订单详情      *******:"+ret);
			return JSONObject.fromObject(ret);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 3.10	获取司机代驾轨迹
	 * @param orderId
	 * @return
	 */
	public JSONObject getOrderTrace(String orderId){
		try {
			SortedMap<String, Object> params = new TreeMap<String, Object>();
			params.put("channel", channel);
			params.put("orderId",orderId);
			params.put("type",1);
			String WEB_URL ="https://baoyang.d.edaijia.cn/api/third/2/order/trace";
			DefaultGetMethod post = new DefaultGetMethod("GBK");
			String sign = this.autograph(params).get("sign");
			params.put("sign",sign);
			
			String ret = post.invoke(WEB_URL, params);
			if (StringUtils.isEmpty(ret)) {
				return null;
			}
			System.out.println("************  获取司机代驾轨迹      *******:"+ret);
			return JSONObject.fromObject(ret);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

	/**
	 * 3.11	获取司机信息
	 * @param orderId
	 * @return
	 */
	public JSONObject getOrderDriverInfo(String orderId){
		try {
			SortedMap<String, Object> params = new TreeMap<String, Object>();
			params.put("channel", channel);
			params.put("orderId",orderId);
			params.put("type",1);
			String WEB_URL ="https://baoyang.d.edaijia.cn/api/third/2/order/driverInfo";
			DefaultGetMethod post = new DefaultGetMethod("GBK");
			String sign = this.autograph(params).get("sign");
			params.put("sign",sign);
			
			String ret = post.invoke(WEB_URL, params);
			if (StringUtils.isEmpty(ret)) {
				return null;
			}
			System.out.println("************  获取司机信息      *******:"+ret);
			return JSONObject.fromObject(ret);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	  
	    /** 
	     *  
	     * @param uri 
	     * @param param 
	     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。 
	     * @param charset 
	     * @return 
	     */  
	    public static String sendPost(String uri, String param, String charset) {  
	        String result = null;  
	        PrintWriter out = null;  
	        InputStream in = null;  
	        try {  
	            URL url = new URL(uri);  
	            HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();  
	            urlcon.setDoInput(true);  
	            urlcon.setDoOutput(true);  
	            urlcon.setUseCaches(false);  
	            urlcon.setRequestMethod("POST");  
	            urlcon.connect();// 获取连接  
	            out = new PrintWriter(urlcon.getOutputStream());  
	            out.print(param);  
	            out.flush();  
	            in = urlcon.getInputStream();  
	            BufferedReader buffer = new BufferedReader(new InputStreamReader(  
	                    in, charset));  
	            StringBuffer bs = new StringBuffer();  
	            String line = null;  
	            while ((line = buffer.readLine()) != null) {  
	                bs.append(line);  
	            }  
	            result = bs.toString();  
	        } catch (Exception e) {  
	            System.out.println("[请求异常][地址：" + uri + "][参数：" + param + "][错误信息："  
	                    + e.getMessage() + "]");  
	        } finally {  
	            try {  
	                if (null != in)  
	                    in.close();  
	                if (null != out)  
	                    out.close();  
	            } catch (Exception e2) {  
	                System.out.println("[关闭流异常][错误信息：" + e2.getMessage() + "]");  
	            }  
	        }  
	        return result;  
	    }  
}
