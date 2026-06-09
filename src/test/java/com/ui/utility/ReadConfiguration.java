package com.ui.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadConfiguration {

	public static String getConfig(String propertyValue) {

		Properties properties;

		File file = new File(System.getProperty("user.dir") + File.separator + "Configuration" + File.separator
				+ "DeviceSpecification.properties");
		FileReader reader;
		try {
			reader = new FileReader(file);
			properties = new Properties();
			properties.load(reader);

			String value = properties.getProperty(propertyValue.toUpperCase());
			return value;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";

	}

}
