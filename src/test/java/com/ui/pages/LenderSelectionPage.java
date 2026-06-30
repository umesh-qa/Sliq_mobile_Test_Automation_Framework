package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.ui.utility.LoggerUtility;
import com.ui.utility.MobileUtility;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

/**
 * Page object representing the Lender Selection page.
 * Handles selecting lending partners, reviewing and editing loan offer amounts, and portfolio details breakdown.
 */
public class LenderSelectionPage extends MobileUtility {

	private final Logger logger = LoggerUtility.getLogger(this.getClass());

	public LenderSelectionPage(AndroidDriver driver) {
		super(driver);
	}

	private static final By BAJAJ_FINANCE_OPTION_LOCATOR = AppiumBy
			.androidUIAutomator("new UiSelector().descriptionContains(\"Bajaj Finance Ltd\")");
	private static final By EDIT_LOAN_AMOUNT_BUTTON_LOCATOR = AppiumBy
			.xpath("//android.widget.Button[@content-desc='Edit Loan Amount']");
	private static final By EDIT_LOAN_AMOUNT_TEXTBOX_LOCATOR = AppiumBy.className("android.widget.EditText");
	private static final By SAVE_EDIT_LOAN_AMOUNT_BUTTON_LOCATOR = AppiumBy
			.xpath("//android.widget.Button[@content-desc='Save']");
	private static final By CONTINUE_BUTTON_FOR_BAJAJ = AppiumBy
			.xpath("//android.widget.Button[@content-desc='Continue']");
	private static final By CONFIRM_BUTTON_FOR_BAJAJ = AppiumBy
			.xpath("//android.widget.Button[@content-desc='Continue with Bajaj Finance Ltd']");
	private static final By LOAN_AMOUNT_EDITED_SUCCESS_TEXT_LOCATOR = AppiumBy
			.xpath("//android.view.View[@content-desc='Loan amount updated successfully!']");
	private static final By KYC_VERIFICATION_BFL_TEXT_LOCATOR = AppiumBy
			.xpath("//android.widget.ImageView[@content-desc='KYC Verification']");
	private static final By MF_DETAILS_HYPERLINK_LOCATOR = AppiumBy
			.xpath("//android.view.View[@content-desc='View Your MF Details']");
	
	// PLEDGEABLE FUNDS option
	private static final By PLEDGEFUND_OPTION_LOCATOR = AppiumBy
			.xpath("//*[contains(@content-desc, 'Pledgeable Funds') or contains(@text, 'Pledgeable Funds')]");
	private static final By PLEDGEABLE_FUNDS_CONTENT_LOCATOR = AppiumBy
			.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View");
		
	// HDFC Funds Option
	private static final By HDFC_FUNDS_OPTION_LOCATOR=AppiumBy.xpath("//android.view.View[contains(@content-desc, 'HDFC Liquid Fund-Direct Plan-Growth Option')]");
	private static final By HDFC_FUNDS_VALUE_EDIT_BUTTON_LOCATOR=AppiumBy.xpath("//*[contains(@content-desc,'Total Unit')or contains(@text,'Total Unit')]/android.view.View[2]");
	private static final By EDIT_FUND_VALUE=AppiumBy.xpath("//android.widget.EditText[@text='2180']");
	private static final By PROCEED_TO_CONTINUE_EDIT_FUND_AMOUNT=AppiumBy.xpath("//android.widget.Button[@content-desc='Proceed']");
	private static final By UPDATE_FUNDS_BUTTON_LOCATOR=AppiumBy.xpath("//android.widget.Button[@content-desc='Update Funds']");
	private static final By LOAN_AMOUNT_AFTER_FUND_EDIT=AppiumBy.xpath("//android.view.View[@content-desc='₹ 99,754']");
	private static final By LOAN_AMOUNT_AFTER_UNCHECK_FUNDS=AppiumBy.xpath("//android.view.View[@content-desc='₹ 90,154']");
	private static final By HDFC_MID_CAP_FUND_CHECBOX_LOCATOR=AppiumBy.xpath("//android.view.View[contains(@content-desc,'HDFC Mid-Cap')or contains(@text,'HDFC Mid-Cap')]/android.widget.CheckBox");
	
	
	//android.view.View[@content-desc="₹ 99,754"]
	// Non-Pledgeable funds option
	private static final By PLEDGEABLE_FUNDS_BACK_OPTION_LOCATOR = AppiumBy.xpath("//*[contains(@content-desc, 'Portfolio Breakdown') or contains(@text, 'Portfolio Breakdown')]");
	private static final By NON_PLEDGEABLE_FUNDS_OPTION_LOCATOR = AppiumBy.xpath("//*[contains(@content-desc, 'Non Pledgeable Funds') or contains(@text, 'Non Pledgeable Funds')]");
	
	// 
	/**
	 * Navigates to and checks non-pledgeable funds content visibility.
	 * 
	 * @return the active page instance
	 */
	public LenderSelectionPage checkNonPledgeableFundsContent() {
		logger.info("Checking non-pledgeable funds details breakdown.");
		clickOn(PLEDGEABLE_FUNDS_BACK_OPTION_LOCATOR);
		clickOn(NON_PLEDGEABLE_FUNDS_OPTION_LOCATOR);
		Assert.assertTrue(isElementDisplayed(PLEDGEABLE_FUNDS_CONTENT_LOCATOR), "Non-Pledgeable Funds contents were not displayed.");
		return this;
	}
	
	/**
	 * Clicks on view Mutual Fund Details link.
	 * 
	 * @return the active page instance
	 */
	public LenderSelectionPage clickOnMFDetails() {
		logger.info("Clicking on 'View Your MF Details' link.");
		clickOn(MF_DETAILS_HYPERLINK_LOCATOR);
		return this;
	}
	
	/**
	 * Navigates to and checks pledgeable funds content visibility.
	 * 
	 * @return the active page instance
	 */
	public LenderSelectionPage checkPledgeableFundsContent() {
		logger.info("Checking pledgeable funds details breakdown.");
		clickOn(PLEDGEFUND_OPTION_LOCATOR);
		Assert.assertTrue(isElementDisplayed(PLEDGEABLE_FUNDS_CONTENT_LOCATOR), "Pledgeable Funds contents were not displayed.");
		return this;
	}

	/**
	 * Selects Bajaj Finance as the lending partner.
	 * 
	 * @return the active page instance
	 */
	public LenderSelectionPage selectBajajFinanceLender() {
		logger.info("Selecting Bajaj Finance Ltd as the preferred lender.");
//		WebElement bajajOption = wait.until(ExpectedConditions.elementToBeClickable(BAJAJ_FINANCE_OPTION_LOCATOR));
//		bajajOption.click();
		clickOn(BAJAJ_FINANCE_OPTION_LOCATOR);
		return this;
	}

	/**
	 * Edits the requested loan offer amount.
	 * 
	 * @param amount new target loan amount
	 * @return the active page instance
	 */
	public LenderSelectionPage clickOnEditLoanAmount(String amount) {
		logger.info("Editing loan amount to: " + amount);
		clickOn(EDIT_LOAN_AMOUNT_BUTTON_LOCATOR);
		enterText(EDIT_LOAN_AMOUNT_TEXTBOX_LOCATOR, amount);
		hideKeyboard();
		clickOn(SAVE_EDIT_LOAN_AMOUNT_BUTTON_LOCATOR);
		logger.info("============================ LOAN AMOUNT EDITED TO: " + amount + " ============================");
		return this;
	}

	/**
	 * Continues the journey with Bajaj Finance.
	 * 
	 * @return new BFLKycVerificationPage instance
	 */
	public BFLKycVerificationPage continueWithBajaj() {
		logger.info("Continuing checkout flow with Bajaj Finance...");
		clickOn(CONTINUE_BUTTON_FOR_BAJAJ);
		clickOn(CONFIRM_BUTTON_FOR_BAJAJ);
		
		// Assert that the next page (KYC verification) is displayed
		Assert.assertTrue(isElementDisplayed(KYC_VERIFICATION_BFL_TEXT_LOCATOR),
				"============================ LENDER SELECTION FAILED: KYC Verification screen was not displayed. ============================");
		
		logger.info("============================ LENDER SELECTED SUCCESSFULLY: KYC Verification screen is visible. ============================");
		return new BFLKycVerificationPage(getDriver());
	}
	
	
	// just to select bajaj as lender not confirm yet.
	public LenderSelectionPage selectBajaj() {
		logger.info("Continuing checkout flow with Bajaj Finance...");
		clickOn(CONTINUE_BUTTON_FOR_BAJAJ);
		return this;
	}
	public LenderSelectionPage editFundsValue(String value) {
		logger.info("Editing Fundes Value...");
		clickOn(HDFC_FUNDS_OPTION_LOCATOR);
		clickOn(HDFC_FUNDS_VALUE_EDIT_BUTTON_LOCATOR);
		enterText(EDIT_FUND_VALUE, value);
//		hideKeyboard();
		clickOn(PROCEED_TO_CONTINUE_EDIT_FUND_AMOUNT);
		clickOn(UPDATE_FUNDS_BUTTON_LOCATOR);
		Assert.assertTrue(isElementDisplayed(LOAN_AMOUNT_AFTER_FUND_EDIT),"============================ EDIT FUND AMOUNT FAILED: Loan amount not update after funds value edited ============================");
		logger.info("============================ EDIT FUND AMOUNT PASSED: Loan amount updated after funds value edited ============================");
	
		return this;
	}
	
	public void unCheckHDFCMidCapFund() {
		logger.info("Unchecking HDFC fund...");

		clickOn(HDFC_MID_CAP_FUND_CHECBOX_LOCATOR);
		clickOn(UPDATE_FUNDS_BUTTON_LOCATOR);
		Assert.assertTrue(isElementDisplayed(LOAN_AMOUNT_AFTER_UNCHECK_FUNDS),"============================ UNCHECK FUNDS AMOUNT FAILED: Loan amount not update after unchecked funds Value ============================");
		logger.info("============================ UNCHECK FUNDS AMOUNT PASSED: Loan amount update after unchecked funds Value ============================");

	}
}
