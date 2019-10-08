package com.qa.test;

import java.io.IOException;

import org.apache.http.ParseException;
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
import org.testng.annotations.Test;

public class BasicAuthHandle {
	
	@Test
	public void handleBasicAuthHttpClient() {
		
		CloseableHttpResponse closeableHttpResponse = null;
		
		CredentialsProvider creds = new BasicCredentialsProvider();
		creds.setCredentials(new AuthScope("the-internet.herokuapp.com", 80), new UsernamePasswordCredentials("admin", "admin"));
		CloseableHttpClient closeableHttpClient = HttpClients.custom().setDefaultCredentialsProvider(creds).build();
		HttpGet httpGet = new HttpGet("http://the-internet.herokuapp.com/basic_auth");
		try {
			closeableHttpResponse = closeableHttpClient.execute(httpGet);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println(EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

}
