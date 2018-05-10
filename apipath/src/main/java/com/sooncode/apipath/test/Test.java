package com.sooncode.apipath.test;

import com.sooncode.apipath.APINode;
import com.sooncode.apipath.util.HttpRequest;

public class Test {

	public static void main(String[] args) {
		APINode.run("com.sooncode", "00001", "http://127.0.0.1:9003/apiNodeController/addApiNodes");
	
	
		String url = "http://127.0.0.1:9003/apiNodeController/getApiNodes";
		String str = "{\"projectId\":\"00001\",\"apiUrl\":\"http://127.0.0.1:8080/userController/addUser\"}";
		//String data = HttpRequest.postRequest(url,str );
		//System.out.println(data);
	
	}
}
