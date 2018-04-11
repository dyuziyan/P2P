package my.comp.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import my.comp.http.impl.DefaultGetMethod;

public class GetMethodTest {

	public static void main(String[] args) throws IOException {
		String url = "https://www.wuxingjinrong.com/activity/draw";
		DefaultGetMethod post = new DefaultGetMethod("GBK");
		Map<String,Object> params =new HashMap<String, Object>();
		params.put("activityKey", "7yi");
		String response = post.invoke(url, params);
		System.out.println(response);
	}

}
