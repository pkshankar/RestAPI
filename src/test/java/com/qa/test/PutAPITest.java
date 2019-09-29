package com.qa.test;

import java.io.File;
import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.data.CustomerPutResponse;
import com.qa.data.UpdateCustomerInfo;
import com.qa.restclient.RestClient;

public class PutAPITest extends TestBase {

	String url, serviceUrl, uri;
	RestClient restClient;

	public PutAPITest() {

		super();
	}

	@BeforeMethod
	public void setUp() {

		uri = prop.getProperty("url") + prop.getProperty("serviceUrl") + "/2";
		restClient = new RestClient();

	}
	
	@Test
	public void putTest() throws JsonGenerationException, JsonMappingException, IOException {
		
		// PREPARE THE JSON PAYLOAD
		
		ObjectMapper objMapper = new ObjectMapper();
		
		UpdateCustomerInfo updateCustomerInfo = new UpdateCustomerInfo("SHANKAR","SENIOR AUTOMATION ENGINEER");
		
		// SERIALIZATION
		objMapper.writeValue(new File("C:\\Users\\pkshank\\eclipse-workspace\\HTTPClient_RestAPI\\src\\main\\java\\com\\qa\\data\\updateCustomerInfo.json"), updateCustomerInfo);
		String customerInfo = objMapper.writeValueAsString(updateCustomerInfo);
		CloseableHttpResponse closeableHttpResponse = restClient.put(uri, customerInfo);
		
		// VALIDATE STATUS CODE
		System.out.println(closeableHttpResponse.getStatusLine().getStatusCode());
		String response = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		System.out.println("RESPONSE " + response);
		JSONObject jObj = new JSONObject(response);
		//System.out.println(jObj.get("name"));
		
		// DESERIALIZATION
		
		CustomerPutResponse obj = objMapper.readValue(response, CustomerPutResponse.class);
		System.out.println(obj.getUpdatedAt());
		
		
		
	}

}
