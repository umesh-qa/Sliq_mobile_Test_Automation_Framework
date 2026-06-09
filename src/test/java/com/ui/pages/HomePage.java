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

	private static final By APPLY_NOW_BUTTON_LOCATOR = AppiumBy.accessibilityId("Apply Now");
	private static final By MUTUAL_FOUND_OPTION_LOCATOR = AppiumBy.accessibilityId("Mutual Fund");
	private static final By CONTINUE_AND_CONFIRM_LOCATOR = AppiumBy.accessibilityId("Confirm & Continue");

	public PanVerificationPage navigateToMutualFound() {
		logger.info("Navigate To Mutula Fond ");
//		clickOn(APPLY_NOW_BUTTON_LOCATOR);
		clickOn(MUTUAL_FOUND_OPTION_LOCATOR);
		clickOn(CONTINUE_AND_CONFIRM_LOCATOR);
		return new PanVerificationPage(getDriver());
	}

}
