package com.ui.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.ui.pages.LoginPage;
import com.ui.utility.MobileUtility;

public abstract class BaseTest {
	
	protected LoginPage homePage;

	@BeforeMethod
	public void setup(){
		homePage = new LoginPage();
	}
	
	@AfterMethod
	public void quite() {
	    homePage.quiteDriver();
	}
	
	public MobileUtility getInstance() {
		return homePage;
	}


}
