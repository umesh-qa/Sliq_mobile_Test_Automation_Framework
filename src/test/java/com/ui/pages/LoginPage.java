package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.ui.utility.LoggerUtility;
import com.ui.utility.MobileUtility;

import io.appium.java_client.AppiumBy;

/**
 * Page object representing the Login Page of the application.
 * Handles credential entry, sending OTP, validating OTP responses, and entry routing to home dashboard.
 */
public class LoginPage extends MobileUtility {

	private final Logger logger = LoggerUtility.getLogger(this.getClass());

	public LoginPage() {
		super();
	}
	public LoginPage(io.appium.java_client.android.AndroidDriver driver) {
		super(driver);
	}

	private static final By NEXT_BUTTON_LOCATOR = AppiumBy.accessibilityId("Next");
	private static final By GET_START_BUTTON_LOCATOR = AppiumBy.accessibilityId("Get Started");
	private static final By MOBILE_NO_TEXT_LOCATOR = AppiumBy
			.xpath("//android.widget.EditText[@hint='Enter mobile number']");
	private static final By SEND_OTP_BUTTON_LOCATOR = AppiumBy
			.xpath("//android.widget.Button[@content-desc='Send OTP']");
	private static final By ENTER_OTP_TEXTBOX_LOCATOR = AppiumBy.className("android.widget.EditText");
	private static final By CONTINUE_BUTTON_LOCATOR = AppiumBy
			.xpath("//android.widget.Button[@content-desc=\"Continue\"]");
	
	private static final By OTP_SUCCESS_TEXT_LOCATOR = AppiumBy.xpath("//android.view.View[@content-desc='OTP Verified Successfully!']");
	private static final By OTP_FAILED_TEXT_LOCATOR = AppiumBy.xpath("//android.view.View[@content-desc='Otp incorrect']");

	/**
	 * Navigates past intro tutorial slides to the login credential form.
	 * 
	 * @return the active page instance
	 */
	public LoginPage goToLoginPage() {
		logger.info("Navigating to Login Page via intro screens.");
		clickOn(NEXT_BUTTON_LOCATOR);
		clickOn(GET_START_BUTTON_LOCATOR);
		return this;
	}

	/**
	 * Enters the mobile number in the credential field.
	 * 
	 * @param number mobile number to log in with
	 * @return the active page instance
	 */
	public LoginPage enterMobileNo(String number) {
		logger.info("Entering Mobile No: " + number);
		clickOn(MOBILE_NO_TEXT_LOCATOR); // Kept original click-before-type sequencing for focus assurance
		enterText(MOBILE_NO_TEXT_LOCATOR, number);
		return this;
	}

	/**
	 * Clicks the Send OTP button.
	 * 
	 * @return the active page instance
	 */
	public LoginPage clickOnSendOTPButton() {
		logger.info("Clicking on 'Send OTP' button.");
		clickOn(SEND_OTP_BUTTON_LOCATOR);
		return this;
	}

	/**
	 * Enters the OTP string in the textbox.
	 * 
	 * @param otp the OTP code received
	 * @return the active page instance
	 */
	public LoginPage enterOTP(String otp) {
		logger.info("Entering OTP code: " + otp);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(SEND_OTP_BUTTON_LOCATOR));
		enterText(ENTER_OTP_TEXTBOX_LOCATOR, otp);
		return this;
	}

	/**
	 * Submits and continues with valid credentials, verifying successful login.
	 * 
	 * @return new HomePage instance
	 */
	public HomePage clickOnContinue() {
		logger.info("Clicking on 'Continue' button.");
		clickOn(CONTINUE_BUTTON_LOCATOR);
		Assert.assertTrue(isElementDisplayed(OTP_SUCCESS_TEXT_LOCATOR),
				"========================== LOGIN FAILED: Dashboard was not visible after OTP entry ==========================");
		logger.info("========================== LOGIN PASSED: Dashboard is visible ==========================");
		return new HomePage(getDriver());
	}

	/**
	 * Submits invalid credentials and verifies that the failure alert is shown.
	 * 
	 * @return new HomePage instance
	 */
	public HomePage clickOnContinueAndGetInvalidResponse() {
		logger.info("Clicking on 'Continue' button with invalid credentials.");
		clickOn(CONTINUE_BUTTON_LOCATOR);
		Assert.assertTrue(isElementDisplayed(OTP_FAILED_TEXT_LOCATOR),
				"========================== LOGIN CHECK FAILED: System did not show validation error for invalid credentials ==========================");
		logger.info("========================== LOGIN CHECK PASSED: Validation error shown for invalid credentials ==========================");
		return new HomePage(getDriver());
	}
}
