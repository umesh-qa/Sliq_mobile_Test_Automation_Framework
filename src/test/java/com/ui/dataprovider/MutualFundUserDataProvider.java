package com.ui.dataprovider;

import java.util.Iterator;
import org.testng.annotations.DataProvider;

import com.ui.pojo.MutualFundUserData;
import com.ui.utility.ReadMutualFundUserExcel;

/**
 * Data provider class for Mutual Fund test cases.
 */
public class MutualFundUserDataProvider {
	
	@DataProvider(name = "MutualFundUser")
	public Iterator<MutualFundUserData> userDataProvider() {
		return ReadMutualFundUserExcel.readExcelFile("UserData.xlsx");
	}
}
