package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.data.TestData;
import com.qa.restclient.RestClient;

public class PostAPTTest extends TestBase {

	RestClient restClient;
	String url, serviceUrl, uri;
	CloseableHttpResponse closeableHttpResponse;

	public PostAPTTest() {

		super();
	}

	@BeforeMethod
	public void setUp() {

		restClient = new RestClient();
		String url = prop.getProperty("url");
		serviceUrl = prop.getProperty("serviceUrl");
		uri = url + serviceUrl;

	}

	@Test
	public void postTestWithHeaders() throws JsonGenerationException, JsonMappingException, IOException {

		// HashMap<String, String> headerMap = new HashMap<String, String>();

		/* SEND HEADERS IF REQUIRED */

		ObjectMapper mapper = new ObjectMapper();
		TestData testData = new TestData("TIM", "JAVA DEVELOPER");

		mapper.writeValue(
				new File("C:\\Users\\pkshank\\eclipse-workspace\\RestAPI\\src\\main\\java\\com\\qa\\data\\data.json"),
				testData);
		String jsonString = mapper.writeValueAsString(testData);
		CloseableHttpResponse closeableHttpResponse = restClient.post(uri, jsonString);
		
		//status
		Assert.assertEquals(closeableHttpResponse.getStatusLine().getStatusCode(), 201);
		
		String jsonResponseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject jsonObj = new JSONObject(jsonResponseString);
		System.out.println("JSON RESPONSE " +jsonObj);
		

	}

}
