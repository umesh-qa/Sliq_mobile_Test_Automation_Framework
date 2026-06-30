package com.ui.test.smoke;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.ui.pages.LoginPage;
import com.ui.utility.MobileUtility;

/**
 * Base test runner configuration for Appium automation suites.
 * Integrates custom test listeners and tear-down sequences.
 */
@Listeners(com.ui.listeners.MyTestListeners.class)
public abstract class BaseTest {
	
	protected LoginPage homePage;

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		homePage = new LoginPage();
	}
	
	@AfterMethod(alwaysRun = true)
	public void quit() {
		if (homePage != null) {
			homePage.quitDriver();
		}
	}
	
	/**
	 * Gets the current active MobileUtility driver instance context for screenshot capture.
	 * 
	 * @return active LoginPage instance acting as driver utility context
	 */
	public MobileUtility getInstance() {
		return homePage;
	}
}
