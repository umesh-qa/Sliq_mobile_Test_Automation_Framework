package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.ui.utility.LoggerUtility;
import com.ui.utility.MobileUtility;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class PledgeFoundPage extends MobileUtility {
	
	private Logger logger = LoggerUtility.getLogger(this.getClass());

	public PledgeFoundPage(AndroidDriver driver) {
		super(driver);
	}

	private static final By OTP_TEXTBOX_LOCATOR = AppiumBy.xpath("//android.widget.EditText");
	private static final By SUBMIT_COMPLETE_APPLICATION = AppiumBy
			.xpath("//android.widget.Button[@content-desc='Submit & Complete Application']");

	// set auto disbursal limit

	private static final By LOAN_AMOUNT_TEXTBOX_LOCATOR = AppiumBy.xpath("//android.widget.EditText");
	// android.widget.Button[@content-desc="Submit"]
	private static final By SUMBIT_BUTTON_LOCATOR = AppiumBy
			.xpath("//android.widget.Button[@content-desc='Submit']");

	public PledgeFoundPage enterOTPForConfirmPledgeFound(String otp) {
		enterText(OTP_TEXTBOX_LOCATOR, otp);
		clickOn(SUBMIT_COMPLETE_APPLICATION);
		return this;
	}
	public void setDisbursalLimitAndConfirm(String disbursalAmount) {
		
		enterText(LOAN_AMOUNT_TEXTBOX_LOCATOR, disbursalAmount);
//		clickOn(SUMBIT_BUTTON_LOCATOR);
	}

}
