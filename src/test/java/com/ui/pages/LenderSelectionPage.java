package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.ui.utility.LoggerUtility;
import com.ui.utility.MobileUtility;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class LenderSelectionPage extends MobileUtility {

	private Logger logger = LoggerUtility.getLogger(this.getClass());

	public LenderSelectionPage(AndroidDriver driver) {
		super(driver);
	}

	private static final By BAJAJ_FINANCE_OPTION_LOCATOR = AppiumBy
			.androidUIAutomator("new UiSelector().descriptionContains(\"Bajaj Finance Ltd\")");
	private static final By EDIT_LOAN_AMOUNT_BUTTON_LOCATOR = AppiumBy
			.xpath("//android.widget.Button[@content-desc='Edit Loan Amount']");
	private static final By EIDT_LOAN_AMOUNT_TEXTBOX_LOCATOR = AppiumBy.className("android.widget.EditText");
	private static final By SAVE_EDIT_LOAN_AMOUNT_BUTTON_LOCATOR = AppiumBy
			.xpath("//android.widget.Button[@content-desc='Save']");
	private static final By CONTINUE_BUTTON_FOR_BAJAJ = AppiumBy
			.xpath("//android.widget.Button[@content-desc='Continue']");
	private static final By CONFIRM_BUTTON_FOR_BAJAJ = AppiumBy
			.xpath("//android.widget.Button[@content-desc='Continue with Bajaj Finance Ltd']");
	private static final By LOAN_AMOUNT_EDITED_SUCCESSS_TEXT_LOCATOR = AppiumBy
			.xpath("//android.view.View[@content-desc='Loan amount updated successfully!']");
	private static final By KYC_VERIFICATION_BFL_TEXT_LOCATOR = AppiumBy
			.xpath("//android.widget.ImageView[@content-desc='KYC Verification']");

	public LenderSelectionPage selectBajajFinalceLender() {
		WebElement BAJAJ_FINANCE_OPTION = wait
				.until(ExpectedConditions.elementToBeClickable(BAJAJ_FINANCE_OPTION_LOCATOR));
		BAJAJ_FINANCE_OPTION.click();
		return this;
	}

	public LenderSelectionPage clickOnEditLoanAmount(String amount) {
		clickOn(EDIT_LOAN_AMOUNT_BUTTON_LOCATOR);
	    enterTextAndPressEnter(EIDT_LOAN_AMOUNT_TEXTBOX_LOCATOR, amount);

		

		logger.info("============================ LOAN AMOUNT EDITED EDITED TO : "+amount);
		return this;
	}

	public BFLKycVerificationPage continueWithBajaj() {
		clickOn(CONTINUE_BUTTON_FOR_BAJAJ);
		clickOn(CONFIRM_BUTTON_FOR_BAJAJ);
		Assert.assertTrue(isElementDisplayed(KYC_VERIFICATION_BFL_TEXT_LOCATOR),
				"============================ LENDER SELECTED SUCCESSFULLY ============================");
		logger.info("============================ LENDER IS NOT SELECTED  ============================");
		return new BFLKycVerificationPage(getDriver());
	}

}
