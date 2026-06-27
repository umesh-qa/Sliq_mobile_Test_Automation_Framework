package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.ui.utility.LoggerUtility;
import com.ui.utility.MobileUtility;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

/**
 * Page object representing the home dashboard of the application.
 * Handles navigation entry points to Shares and Mutual Fund flows.
 */
public class HomePage extends MobileUtility {

	private final Logger logger = LoggerUtility.getLogger(this.getClass());

	public HomePage(AndroidDriver driver) {
		super(driver);
	}

	// Navigate to Mutual Fund
	private static final By APPLY_NOW_BUTTON_LOCATOR = AppiumBy.accessibilityId("Apply Now");
	private static final By MUTUAL_FUND_OPTION_LOCATOR = AppiumBy.accessibilityId("Mutual Fund");
	private static final By CONTINUE_AND_CONFIRM_LOCATOR = AppiumBy.accessibilityId("Confirm & Continue");

	// Navigate to Shares
	private static final By SHARES_OPTION_LOCATOR = AppiumBy.xpath("//android.view.View[@content-desc=\"Shares\"]");

	/**
	 * Navigates to the Mutual Fund flow and returns the PanVerificationPage.
	 * 
	 * @return new PanVerificationPage instance
	 */
	public PanVerificationPage navigateToMutualFund() {
		logger.info("Navigating to Mutual Fund flow...");
		clickOn(MUTUAL_FUND_OPTION_LOCATOR);
		clickOn(CONTINUE_AND_CONFIRM_LOCATOR);
		return new PanVerificationPage(getDriver());
	}

	/**
	 * Tries to navigate to the Mutual Fund flow, handling optional "Apply Now" overlays.
	 * 
	 * @return the active page instance
	 */
	public HomePage goToMutualFund() {
		logger.info("Navigating to Mutual Fund flow (handling optional Apply overlay)...");
		try {
			clickOn(APPLY_NOW_BUTTON_LOCATOR);
		} catch (Exception e) {
			logger.warn("Apply Now overlay button did not appear, proceeding directly: " + e.getMessage());
		} finally {
			clickOn(MUTUAL_FUND_OPTION_LOCATOR);
			clickOn(CONTINUE_AND_CONFIRM_LOCATOR);
		}
		return this;
	}

	/**
	 * Navigates to the Shares flow and returns the PanVerificationPage.
	 * 
	 * @return new PanVerificationPage instance
	 */
	public PanVerificationPage navigateToShares() {
		logger.info("Navigating to Shares flow...");
		clickOn(SHARES_OPTION_LOCATOR);
		clickOn(CONTINUE_AND_CONFIRM_LOCATOR);
		return new PanVerificationPage(getDriver());
	}
}
