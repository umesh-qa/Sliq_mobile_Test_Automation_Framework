package com.ui.utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.Logger;

/**
 * Utility to load active device and app execution configurations.
 */
public class ReadConfiguration {

	private static final Logger logger = LoggerUtility.getLogger(ReadConfiguration.class);

	public static String getConfig(String propertyValue) {
		File file = new File(System.getProperty("user.dir") + File.separator + "Configuration" + File.separator
				+ "DeviceSpecification.properties");

		try (FileReader reader = new FileReader(file)) {
			Properties properties = new Properties();
			properties.load(reader);

			String value = properties.getProperty(propertyValue.toUpperCase());
			return value != null ? value.trim() : "";

		} catch (IOException e) {
			logger.error("Failed to read configuration property: " + propertyValue + " from file: " + file.getName(), e);
		}
		return "";
	}
}
