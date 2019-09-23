package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.restclient.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {

	RestClient restClient;
	String url, serviceUrl, uri;
	CloseableHttpResponse closeableHttpResponse;

	public GetAPITest() {

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
	public void getTestWithoutHedaers() {

		try {

			closeableHttpResponse = restClient.get(uri);

			/* STATUS CODE */
			int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
			System.out.println(" Status code " + statusCode);
			Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "STATUS CODE MISMATCH");

			/* JSON STRING */
			String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
			JSONObject jsonObj = new JSONObject(responseString);
			System.out.println("JSON Response " + jsonObj);

			/* ALL HEADERS */
			Header[] allHeader = closeableHttpResponse.getAllHeaders();
			HashMap<String, String> headerMap = new HashMap<String, String>();
			for (Header header : allHeader) {

				headerMap.put(header.getName(), header.getValue());

			}

			System.out.println("ALL HEADERS " + headerMap);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void getTestWithHeaders() {

		HashMap<String, String> requestHeaderMap = new HashMap<String, String>();
		requestHeaderMap.put("Content-Type", "application/json");

//		requestHeaderMap.put("username", "test@amazon.com");
//		requestHeaderMap.put("password", "test213");
//		requestHeaderMap.put("Auth Token", "12345");

		try {
			restClient.get(uri, requestHeaderMap);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void parseJson() throws ClientProtocolException, IOException {
		
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uri);
		CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
		JSONObject jsonResponse = new JSONObject(EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8"));
		TestUtil.getValueByJPath(jsonResponse, "data");
		//System.out.println(str.length);
//		for(String s: str) {
//			
//			System.out.println(s);
//		}
//		
	}

}
