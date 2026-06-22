package com.ui.dataprovider;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.ui.pojo.SharesUserData;
import com.ui.utility.ReadSharesUserExcel;

public class SharesUserDataProvider {
	
	@DataProvider(name = "SharesUser")
	public Iterator<SharesUserData> userDataProvider() {
		return ReadSharesUserExcel.readExcelFile("UserData.xlsx");
	}

}
