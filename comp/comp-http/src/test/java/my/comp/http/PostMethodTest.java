package my.comp.http;

import java.io.File;
import java.io.IOException;

import my.comp.http.impl.DefaultPostMethod;

import org.apache.commons.io.FileUtils;

public class PostMethodTest {

	public static void main(String[] args) throws IOException {
		String json = FileUtils.readFileToString(new File("C:/json.txt"), "UTF-8");
		String url = "";
		DefaultPostMethod post = new DefaultPostMethod("GBK");
		String response = post.invoke(url, json);
		System.out.println(response);
	}

}
