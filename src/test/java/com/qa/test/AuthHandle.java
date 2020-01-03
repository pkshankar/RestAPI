package com.qa.test;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class AuthHandle {

	@Test
	public void handleBasicAuth() throws ClientProtocolException, IOException {

		CredentialsProvider creds = new BasicCredentialsProvider();
		creds.setCredentials(new AuthScope("httpbin.org", 80), new UsernamePasswordCredentials("user", "passwd"));
		CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(creds).build();
		HttpGet httpGet = new HttpGet("http://httpbin.org/basic-auth/user/passwd");
		CloseableHttpResponse response = client.execute(httpGet);
		HttpEntity entity = response.getEntity();
		String responseString = EntityUtils.toString(entity);
		JSONObject jsonObject = new JSONObject(responseString);
		String authenticatedValue = jsonObject.get("authenticated").toString();
		String userValue = jsonObject.get("user").toString();
		System.out.println("VALUE OF AUTHENTICATED IS : " + authenticatedValue);
		System.out.println("VALUE OF USER IS : " + userValue);

	}

}
