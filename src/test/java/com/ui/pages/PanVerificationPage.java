package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.ui.utility.LoggerUtility;
import com.ui.utility.MobileUtility;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class PanVerificationPage extends MobileUtility {

	public PanVerificationPage(AndroidDriver driver) {
		super(driver);
	}

	private Logger logger = LoggerUtility.getLogger(this.getClass());

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

	public PanVerificationPage enterPanNo(String panNumber) {
		logger.info("Entering Pan number: " + panNumber);
		enterText(PAN_CARD_NUMBER_TEXTBOX_LOCATOR, panNumber);
		return this;
	}

	public PanVerificationPage enterPanName(String name) {
		logger.info("Entering Pan Name: " + name);
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

	public PanVerificationPage AcceptTermsAndConditions() {
		logger.info("Accepting Terms and Conditions ");
		clickOn(TERMS1_CHECKBOX_LOCATOR);
		clickOn(TERMS2_CHECKBOX_LOCATOR);
		return this;
	}

		public MutualFond_centeralPage clickOnCheckLoanEligibility() {

		    logger.info("Checking Loan Eligibility");
		    clickOn(CONTINUE_BUTTON_LOCATOR);
		    switchToWebView();

		    return new MutualFond_centeralPage(getDriver());
		}
	
}
