package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

	public int RESPONSE_STATUS_CODE_200 = 200;

	public static Properties prop;

	public TestBase() {

		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\pkshank\\eclipse-workspace\\HTTPClient_RestAPI\\src\\main\\java\\com\\qa\\config\\config.properties");
			try {
				prop.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}

}
