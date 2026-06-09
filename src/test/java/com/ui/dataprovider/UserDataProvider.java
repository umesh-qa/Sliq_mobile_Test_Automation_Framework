package com.ui.dataprovider;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.ui.utility.ReadUserData;

public class UserDataProvider {

	@DataProvider(name = "userData")
	public Iterator<Object[]> userDataProvider() {
		return ReadUserData.readTestData();
	}

}
