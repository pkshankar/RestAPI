package com.qa.test;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.pojo.CreateUser;

public class GoRestPost {
	
	@Test
	public void postTestGoRest() throws Exception {
		
		CredentialsProvider creds = new BasicCredentialsProvider();
		creds.setCredentials(new AuthScope("gorest.co.in",80), new UsernamePasswordCredentials("7_7bk8nRTMj2mI9UbmtNLVmunksMPAfWcBYv", "AnGU6sxe"));
		CloseableHttpClient closeableHttpClient = 	HttpClients.custom().setDefaultCredentialsProvider(creds).build();
		HttpPost httpPost = new HttpPost("https://gorest.co.in/public-api/users");
		CreateUser createUser = new CreateUser("shan","lui","male","shan@zmail.com","active");
		ObjectMapper mapper = new ObjectMapper();
		String jBody = mapper.writeValueAsString(createUser);
		httpPost.setEntity(new StringEntity(jBody));
		CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);
		String jsonResponse = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		System.out.println(jsonResponse);
	}

}
