package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.ui.utility.LoggerUtility;
import com.ui.utility.MobileUtility;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

/**
 * Page object representing the Pledge Fund page.
 * Handles OTP confirmation for pledging funds and disbursal limit setups.
 */
public class PledgeFundPage extends MobileUtility {
	
	private final Logger logger = LoggerUtility.getLogger(this.getClass());

	public PledgeFundPage(AndroidDriver driver) {
		super(driver);
	}

	private static final By OTP_TEXTBOX_LOCATOR = AppiumBy.xpath("//android.widget.EditText");
	private static final By SUBMIT_COMPLETE_APPLICATION = AppiumBy
			.xpath("//android.widget.Button[@content-desc='Submit & Complete Application']");

	private static final By LOAN_AMOUNT_TEXTBOX_LOCATOR = AppiumBy.xpath("//android.widget.EditText");
	private static final By SUBMIT_BUTTON_LOCATOR = AppiumBy
			.xpath("//android.widget.Button[@content-desc='Submit']");

	/**
	 * Enters OTP to confirm the pledge fund transaction.
	 * 
	 * @param otp the confirmation OTP
	 * @return the active page instance
	 */
	public PledgeFundPage enterOTPForConfirmPledgeFund(String otp) {
		logger.info("Entering OTP for confirming Pledge Fund transaction...");
		enterText(OTP_TEXTBOX_LOCATOR, otp);
		clickOn(SUBMIT_COMPLETE_APPLICATION);
		return this;
	}

	/**
	 * Sets the disbursal limit and submits the confirmation.
	 * 
	 * @param disbursalAmount the limit amount to set
	 */
	public void setDisbursalLimitAndConfirm(String disbursalAmount) {
		logger.info("Setting loan disbursal limit to: " + disbursalAmount);
		enterText(LOAN_AMOUNT_TEXTBOX_LOCATOR, disbursalAmount);
		// clickOn(SUBMIT_BUTTON_LOCATOR); // Kept commented out as per original logic constraint
	}
}
