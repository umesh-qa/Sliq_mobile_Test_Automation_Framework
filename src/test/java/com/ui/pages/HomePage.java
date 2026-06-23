package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.ui.utility.LoggerUtility;
import com.ui.utility.MobileUtility;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class HomePage extends MobileUtility {

	private Logger logger = LoggerUtility.getLogger(this.getClass());

	public HomePage(AndroidDriver driver) {
		super(driver);
	}

	// Navigate to Mutual Found
	private static final By APPLY_NOW_BUTTON_LOCATOR = AppiumBy.accessibilityId("Apply Now");
	private static final By MUTUAL_FOUND_OPTION_LOCATOR = AppiumBy.accessibilityId("Mutual Fund");
	private static final By CONTINUE_AND_CONFIRM_LOCATOR = AppiumBy.accessibilityId("Confirm & Continue");

	// Navigate to Shares
	private static final By SHARES_OPTION_LOCATOR = AppiumBy.xpath("//android.view.View[@content-desc=\"Shares\"]");

	public PanVerificationPage navigateToMutualFound() {
		logger.info("Navigate To Mutula Fond ");
		try {
		clickOn(APPLY_NOW_BUTTON_LOCATOR);
		}catch(Exception e) {
			logger.info("Appy button not appear");
		}
		finally {
			clickOn(MUTUAL_FOUND_OPTION_LOCATOR);
			clickOn(CONTINUE_AND_CONFIRM_LOCATOR);
		}
		return new PanVerificationPage(getDriver());
	}

	public PanVerificationPage navigateToShares() {
		logger.info("Navigate To Shares");
		clickOn(SHARES_OPTION_LOCATOR);
		clickOn(CONTINUE_AND_CONFIRM_LOCATOR);
		return new PanVerificationPage(getDriver());
	}

}
