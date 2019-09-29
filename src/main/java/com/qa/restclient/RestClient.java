package com.qa.restclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {

	/* AUTOMATE A REST GET CALL WITHOUT HEADERS */

	public CloseableHttpResponse get(String uri) throws ClientProtocolException, IOException {

		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uri);
		return closeableHttpClient.execute(httpGet);

	}

	/* AUTOMATE A REST GET CALL WITH HEADERS */

	public CloseableHttpResponse get(String uri, HashMap<String, String> requestHeaderMap)
			throws ClientProtocolException, IOException {

		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uri);
		for (Map.Entry<String, String> headerMap : requestHeaderMap.entrySet()) {

			httpGet.addHeader(headerMap.getKey(), headerMap.getValue());
		}

		return closeableHttpClient.execute(httpGet);

	}

	public CloseableHttpResponse post(String uri, String entityString) throws ClientProtocolException, IOException {

		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(uri);
//		for (Map.Entry<String, String> headerMap : postHeaderMap.entrySet()) {
//
//			httpPost.addHeader(headerMap.getKey(), headerMap.getValue());
//		}

		httpPost.setEntity(new StringEntity(entityString));

		return closeableHttpClient.execute(httpPost);
	}

	// AUTOMATE PUT CALL

	public CloseableHttpResponse put(String url, String entityString) throws ClientProtocolException, IOException {

		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut(url);

		// SET THE ENTITY
		httpPut.setEntity(new StringEntity(entityString));
		return closeableHttpClient.execute(httpPut);

	}

	public CloseableHttpResponse delete(String url) throws ClientProtocolException, IOException {

		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpDelete httpDelete = new HttpDelete(url);
		return closeableHttpClient.execute(httpDelete);
	}

}
