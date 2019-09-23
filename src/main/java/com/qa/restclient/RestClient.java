package com.qa.restclient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {

	/* AUTOMATE A REST GET CALL WITHOUT HEADERS */

	public CloseableHttpResponse get(String uri) throws ClientProtocolException, IOException {

		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uri);
		return closeableHttpClient.execute(httpGet);

	}

	/* AUTOMATE A REST GET CALL WITHOUT HEADERS */

	public CloseableHttpResponse get(String uri, HashMap<String, String> requestHeaderMap)
			throws ClientProtocolException, IOException {

		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uri);
		for (Map.Entry<String, String> headerMap : requestHeaderMap.entrySet()) {

			httpGet.addHeader(headerMap.getKey(), headerMap.getValue());
		}

		return closeableHttpClient.execute(httpGet);

	}

}
