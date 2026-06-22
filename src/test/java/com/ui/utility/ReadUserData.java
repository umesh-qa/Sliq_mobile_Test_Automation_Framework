package com.ui.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.ui.pojo.UserData;

public class ReadUserData {

	public static Iterator<Object[]> readTestData() {
		Properties properties;
		File file = new File(
				System.getProperty("user.dir") + File.separator + "UserData" + File.separator + "UserInfo.properties");
		FileReader reader;
		UserData userData = null;
		List<Object[]> listOfUserdata;
		try {
			properties = new Properties();
			reader = new FileReader(file);
			properties.load(reader);
			listOfUserdata = new ArrayList<Object[]>();
			userData = new UserData(properties.getProperty("MOBILENO"), properties.getProperty("OTP"),
					properties.getProperty("PANNO"), properties.getProperty("PANNAME"),
					properties.getProperty("EMAILID"), properties.getProperty("DOB"),
					properties.getProperty("LOANAMOUNT"), properties.getProperty("DPID"),
					properties.getProperty("CLIENTID"));
			listOfUserdata.add(new Object[] { userData });
			return listOfUserdata.iterator();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
