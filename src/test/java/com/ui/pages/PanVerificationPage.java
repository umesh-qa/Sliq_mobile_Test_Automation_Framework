package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.ui.utility.LoggerUtility;
import com.ui.utility.MobileUtility;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

/**
 * Page object representing the PAN verification and details entry screen.
 * Handles forms for PAN number, name, DOB, email, and security holdings validation metadata.
 */
public class PanVerificationPage extends MobileUtility {

	private final Logger logger = LoggerUtility.getLogger(this.getClass());

	public PanVerificationPage(AndroidDriver driver) {
		super(driver);
	}

	private static final By PAN_CARD_NUMBER_TEXTBOX_LOCATOR = AppiumBy
			.xpath("//android.widget.ScrollView/android.widget.EditText[1]");
	private static final By PAN_CARD_NAME_TEXTBOX_LOCATOR = AppiumBy
			.xpath("//android.widget.ScrollView/android.widget.EditText[2]");
	private static final By EMAIL_ID_TEXTBOX_LOCATOR = AppiumBy
			.xpath("//android.widget.ScrollView/android.widget.EditText[3]");
	private static final By DATE_OF_BIRTH_TEXTBOX_LOCATOR = AppiumBy
			.xpath("//android.widget.ScrollView/android.widget.EditText[4]");
	private static final By TERMS1_CHECKBOX_LOCATOR = AppiumBy
			.xpath("//android.widget.ScrollView/android.widget.CheckBox[1]");
	private static final By TERMS2_CHECKBOX_LOCATOR = AppiumBy
			.xpath("//android.widget.ScrollView/android.widget.CheckBox[2]");

	private static final By CONTINUE_BUTTON_LOCATOR = AppiumBy
			.xpath("//android.widget.Button[@content-desc=\"Check Loan Eligibility\"]");

	// ============= Shares Locator =============//
	private static final By SHARES_DOB_TEXTBOX_LOCATOR = AppiumBy
			.xpath("//android.widget.ScrollView/android.widget.EditText[3]");
	private static final By SHARE_EMAIL_TEXTBOX_LOCATOR = AppiumBy
			.xpath("//android.widget.ScrollView/android.widget.EditText[4]");
	private static final By DP_ID_TEXTBOX_LOCATOR = AppiumBy
			.xpath("//android.widget.ScrollView/android.widget.EditText[5]");
	private static final By CLIENT_ID_TEXTBOX_LOCATOR = AppiumBy
			.xpath("//android.widget.ScrollView/android.widget.EditText[6]");

	private static final By SHARES_CONTINUE_BUTTON_LOCATOR = AppiumBy
			.xpath("//android.widget.Button[@content-desc='Continue']");
	
	// Validation Locators
	private static final By PAN_VERIFICATION_TEXT_LOCATOR = AppiumBy
			.xpath("//android.view.View[@content-desc='PAN Verified successfully']");
	private static final By UPLOAD_HOLDINGS_VERIFICATION_TEXT_LOCATOR = AppiumBy
			.xpath("//android.view.View[@content-desc=\"Upload Share Holdings\"]");

	private static final By ALL_MANDATORY_FIELD_ERROR_MESSAGE = AppiumBy
			.xpath("//android.view.View[@content-desc='Please enter a valid Date of Birth']");
	private static final By INVALID_PAN_FORMAT_ERROR_MESSAGE = AppiumBy
			.xpath("//android.view.View[@content-desc='Invalid PAN format']");
	private static final By INVALID_EMAIL_FORMAT_ERROR_MESSAGE = AppiumBy
			.xpath("//android.view.View[@content-desc=\"Enter a valid email address\"]");
	private static final By PAN_NO_MANDATORY_FIELD_ERROR_MESSAGE = AppiumBy
			.xpath("//android.view.View[@content-desc='Please enter a valid PAN number (e.g., ABCDE1234F)']");

	public PanVerificationPage enterPanNo(String panNumber) {
		logger.info("Entering PAN number: " + panNumber);
		enterText(PAN_CARD_NUMBER_TEXTBOX_LOCATOR, panNumber);
		return this;
	}

	public String getInvalidPanFormatErrorMessage() {
		logger.info("Retrieving invalid PAN format error message.");
		return getElementVisibleText(INVALID_PAN_FORMAT_ERROR_MESSAGE);
	}

	public String getInvalidEmailFormatErrorMessage() {
		logger.info("Retrieving invalid Email format error message.");
		return getElementVisibleText(INVALID_EMAIL_FORMAT_ERROR_MESSAGE);
	}

	public PanVerificationPage enterPanName(String name) {
		logger.info("Entering PAN Name: " + name);
		enterText(PAN_CARD_NAME_TEXTBOX_LOCATOR, name);
		return this;
	}

	public PanVerificationPage enterEmailId(String emailID) {
		logger.info("Entering Email ID: " + emailID);
		enterText(EMAIL_ID_TEXTBOX_LOCATOR, emailID);
		return this;
	}

	public PanVerificationPage enterDateOfBirth(String dateOfBirth) {
		logger.info("Entering Date of Birth: " + dateOfBirth);
		enterText(DATE_OF_BIRTH_TEXTBOX_LOCATOR, dateOfBirth);
		return this;
	}

	public PanVerificationPage acceptTermsAndConditions() {
		logger.info("Accepting terms and conditions (scrolling to click).");
		scrollAndClick(TERMS1_CHECKBOX_LOCATOR);
		scrollAndClick(TERMS2_CHECKBOX_LOCATOR);
		return this;
	}

	public PanVerificationPage sharesAcceptTermsAndConditions() {
		logger.info("Accepting terms and conditions for Shares flow.");
		clickOn(TERMS1_CHECKBOX_LOCATOR);
		return this;
	}

	/**
	 * Submits verification and routes to MutualFundCentralPage.
	 * 
	 * @return new MutualFundCentralPage instance
	 */
	public MutualFundCentralPage clickOnCheckLoanEligibility() {
		logger.info("Clicking on check loan eligibility...");
		scrollAndClick(CONTINUE_BUTTON_LOCATOR);

		Assert.assertTrue(isElementDisplayed(PAN_VERIFICATION_TEXT_LOCATOR),
				"============================ PAN VALIDATION FAILED: Mutual Fund Central Page is not visible ============================");

		logger.info("============================ PAN VALIDATION PASSED: Mutual Fund Central Page is visible ============================");
		switchToWebView();
		return new MutualFundCentralPage(getDriver());
	}

	/**
	 * Submits verification and returns self.
	 * 
	 * @return the active page instance
	 */
	public PanVerificationPage clickCheckLoanEligibility() {
		logger.info("Clicking on check loan eligibility (remaining on PAN screen)...");
		scrollAndClick(CONTINUE_BUTTON_LOCATOR);

		Assert.assertTrue(isElementDisplayed(PAN_VERIFICATION_TEXT_LOCATOR),
				"============================ PAN VALIDATION FAILED: Mutual Fund Central Page is not visible ============================");

		logger.info("============================ PAN VALIDATION PASSED: Mutual Fund Central Page is visible ============================");
		switchToWebView();
		return this;
	}
	
	public PanVerificationPage checkLoanEligibility() {
		logger.info("Submitting eligibility check.");
		scrollAndClick(CONTINUE_BUTTON_LOCATOR);
		return this;
	}

	// SHARES Methods
	public PanVerificationPage enterSharesDOB(String dateOfBirth) {
		logger.info("Entering DOB for Shares validation: " + dateOfBirth);
		enterText(SHARES_DOB_TEXTBOX_LOCATOR, dateOfBirth);
		return this;
	}

	public PanVerificationPage enterSharesEmail(String email) {
		logger.info("Entering Email for Shares validation: " + email);
		enterText(SHARE_EMAIL_TEXTBOX_LOCATOR, email);
		return this;
	}

	public PanVerificationPage enterDpID(String dpID) {
		logger.info("Entering DP ID: " + dpID);
		enterText(DP_ID_TEXTBOX_LOCATOR, dpID);
		return this;
	}

	public PanVerificationPage enterClientID(String clientID) {
		logger.info("Entering Client ID: " + clientID);
		enterText(DP_ID_TEXTBOX_LOCATOR, clientID);
		return this;
	}

	/**
	 * Navigates to UploadHoldingPage.
	 * 
	 * @return new UploadHoldingPage instance
	 */
	public UploadHoldingPage clickOnContinueButton() {
		logger.info("Submitting details and proceeding to Upload Holdings...");
		clickOn(SHARES_CONTINUE_BUTTON_LOCATOR);

		Assert.assertTrue(isElementDisplayed(UPLOAD_HOLDINGS_VERIFICATION_TEXT_LOCATOR),
				"============================ PAN VALIDATION FAILED: Upload holdings Page is not visible ============================");

		logger.info("============================ PAN VALIDATION PASSED: Upload holdings Page is visible ============================");
		return new UploadHoldingPage(getDriver());
	}

	// VALIDATION METHODS
	public void checkAllMandatoryMessage() {
		logger.info("Verifying all mandatory field validation warning messages.");
		checkMandatoryMessage(ALL_MANDATORY_FIELD_ERROR_MESSAGE);
	}
	
	public void checkPanNoMandatoryMessage() {
		logger.info("Verifying PAN field mandatory validation message.");
		checkMandatoryMessage(PAN_NO_MANDATORY_FIELD_ERROR_MESSAGE);
	}
}
