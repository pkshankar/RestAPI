package com.qa.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestUtil {

	public static String getValueByJPath(JSONObject responsejson, String jpath) {
		Object obj = responsejson;
	System.out.println("hello " +responsejson.get("data"));
		
		// "per_page": 6
		
//		for(String s : jpath.split("/")) {
//			
//			if(s.contains("["))
//				System.out.println("ok");
//			else
//				System.out.println("not ok");
//		}
	
		for(String s : jpath.split("/")) {
			
			System.out.println(((JSONObject)obj).get(s));
		}
		for (String s : jpath.split("/"))
			if (!s.isEmpty())
				if (!(s.contains("[") || s.contains("]"))) {
					
					System.out.println("CONTAINS");
					obj = ((JSONObject) obj).get(s);
					
				}
					
				else if (s.contains("[") || s.contains("]")) {
					System.out.println("NOT CONTAINS");
					obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0]))
							.get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
					
				}
		return obj.toString();
	}

	}


