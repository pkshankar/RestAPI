package com.qa.util;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestUtil {

	public static String getDataJsonObject(JSONObject responseJson, String element) {

		return responseJson.get(element).toString();
	}
	
	public static String getDataJsonArray(JSONObject responseJson, String arrName, String attribute, int jsonArrayIndex) {
		
		return responseJson.getJSONArray(arrName).getJSONObject(jsonArrayIndex).get(attribute).toString();

		
	}
	
	public static void iterateJsonArray(JSONObject responseJson) {
		
		JSONArray jArray = responseJson.getJSONArray("data");
		
		
		Iterator<Object> itr = jArray.iterator();
		while(itr.hasNext()) {
			
			System.out.println(itr.next());
		}
		
		
	}

}