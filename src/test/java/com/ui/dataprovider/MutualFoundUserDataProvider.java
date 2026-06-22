package com.ui.dataprovider;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.ui.pojo.MutualFoundUserData;
import com.ui.utility.ReadMutualFoundUserExcel;

public class MutualFoundUserDataProvider {
	
	
	@DataProvider(name = "MutualFoundUser")
	public Iterator<MutualFoundUserData> userDataProvider() {
		return ReadMutualFoundUserExcel.readExcelFile("UserData.xlsx");
	}

}
