package com.qa.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.restclient.RestClient;

public class DeleteAPITest extends TestBase {
	
	String url, serviceUrl, uri;
	RestClient restClient;
	
	public DeleteAPITest() {
		
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		
		restClient = new RestClient();
		uri = prop.getProperty("url")+prop.getProperty("serviceUrl")+"/2";
		
	}
	
	@Test
	public void deleteTest() throws ClientProtocolException, IOException {
		
		CloseableHttpResponse closeableHttpResponse = restClient.delete(uri);
		
		System.out.println(closeableHttpResponse.getStatusLine().getStatusCode());
	}

}
