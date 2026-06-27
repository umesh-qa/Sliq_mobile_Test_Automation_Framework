package com.ui.utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import org.apache.logging.log4j.Logger;

import com.ui.pojo.UserData;

/**
 * Utility to read generic user test data from properties configuration files.
 */
public class ReadUserData {

	private static final Logger logger = LoggerUtility.getLogger(ReadUserData.class);

	public static Iterator<Object[]> readTestData() {
		File file = new File(
				System.getProperty("user.dir") + File.separator + "UserData" + File.separator + "UserInfo.properties");
		List<Object[]> listOfUserData = new ArrayList<>();

		logger.info("Reading generic user test data from properties: " + file.getAbsolutePath());

		try (FileReader reader = new FileReader(file)) {
			Properties properties = new Properties();
			properties.load(reader);

			UserData userData = new UserData(
					properties.getProperty("MOBILENO"),
					properties.getProperty("OTP"),
					properties.getProperty("PANNO"),
					properties.getProperty("PANNAME"),
					properties.getProperty("EMAILID"),
					properties.getProperty("DOB"),
					properties.getProperty("LOANAMOUNT"),
					properties.getProperty("DPID"),
					properties.getProperty("CLIENTID")
			);
			listOfUserData.add(new Object[] { userData });
			logger.info("Successfully loaded generic user details properties record.");
			return listOfUserData.iterator();

		} catch (IOException e) {
			logger.error("Error reading generic user test data properties file: " + file.getName(), e);
		}

		return null;
	}
}
