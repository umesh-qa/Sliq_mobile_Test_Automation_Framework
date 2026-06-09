package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.ui.utility.LoggerUtility;
import com.ui.utility.MobileUtility;

import io.appium.java_client.AppiumBy;

public class LoginPage extends MobileUtility {

	public LoginPage() {
		super();
	}
	
	private Logger logger = LoggerUtility.getLogger(this.getClass());

	private static final By NEXT_BUTTON_LOCATOR = AppiumBy.accessibilityId("Next");
	private static final By GET_START_BUTTON_LOCATOR = AppiumBy.accessibilityId("Get Started");
	private static final By MOBILE_NO_TEXT_LOCATOR = AppiumBy
			.xpath("//android.widget.EditText[@hint='Enter mobile number']");
	private static final By SEND_OTP_BUTTON_LOCATOR = AppiumBy
			.xpath("//android.widget.Button[@content-desc='Send OTP']");
	private static final By ENTER_OTP_TEXTBOX_LOCATOR = AppiumBy.className("android.widget.EditText");
	private static final By CONTINUE_BUTTON_LOCATOR = AppiumBy
			.xpath("//android.widget.Button[@content-desc=\"Continue\"]");

	public LoginPage goTologinPage() {
		logger.info("Navigate to Login Page");
		clickOn(NEXT_BUTTON_LOCATOR);
		clickOn(GET_START_BUTTON_LOCATOR);
		return this;
	}

	public LoginPage enterMobileNo(String number) {
		logger.info("Entering Mobile No: "+ number);
		clickOn(MOBILE_NO_TEXT_LOCATOR);
		enterText(MOBILE_NO_TEXT_LOCATOR, number);
		return this;
	}

	public LoginPage clickOnSendOTPButton() {
		logger.info("click on Send OTP button");
		clickOn(SEND_OTP_BUTTON_LOCATOR);
		return this;
	}

	public LoginPage enterOTP(String otp) {
		logger.info("Entering OTP "+ otp);
		enterText(ENTER_OTP_TEXTBOX_LOCATOR, otp);
		return this;
	}

	public HomePage clickOnContinue() {
		logger.info("click on Continue button");
		clickOn(CONTINUE_BUTTON_LOCATOR);
		return new HomePage(getDriver());
	}

}
