package com.ui.pages;

import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ui.utility.LoggerUtility;
import com.ui.utility.MobileUtility;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

/**
 * Page object representing the Mutual Fund Central page.
 * Handles portfolio AMC selection, OTP authentication, and loan offer checking.
 */
public class MutualFundCentralPage extends MobileUtility {

	private final Logger logger = LoggerUtility.getLogger(this.getClass());

	public MutualFundCentralPage(AndroidDriver driver) {
		super(driver);
	}

	private static final By SELECT_ALL_FUNDS = AppiumBy.xpath(
			"//android.widget.TextView[@text='Select All Eligible AMCs']/preceding-sibling::android.widget.CheckBox");

	private static final By AUTHENTICATION_BUTTON_LOCATOR = AppiumBy.xpath("//*[contains(@text,'Authenticate with OTP') or contains(@content-desc,'Authenticate with OTP')]");
	private static final By CONTINUE_TO_PORTFOLIO_IMPORT_LOCATOR = By
			.xpath("//android.widget.Button[@text='Continue to Portfolio Import']");
	private static final By SEE_LOAN_OFFER_BUTTON_LOCATOR = AppiumBy.accessibilityId("See Loan Offers");

	/**
	 * Enters the OTP into the verification input fields.
	 * 
	 * @param otp the OTP code to enter
	 * @return the active page instance
	 */
	public MutualFundCentralPage enterOtp(String otp) {
		logger.info("Entering OTP for portfolio authentication...");
		for (int i = 0; i < otp.length() && i < 6; i++) {
			By otpBoxLocator = AppiumBy.xpath("//android.view.View[@resource-id=\"root\"]/android.widget.EditText[" + (i + 1) + "]");
			WebElement element = getElementToBeClickable(otpBoxLocator);
			element.click();
			element.sendKeys(String.valueOf(otp.charAt(i)));
			try { Thread.sleep(500); } catch (InterruptedException ignored) {}
		}
		hideKeyboard();
		return this;
	}

	/**
	 * Clicks the "Authenticate with OTP" button.
	 * 
	 * @return the active page instance
	 */
	public MutualFundCentralPage clickOnAuthenticateWithOTP() {
		logger.info("Clicking on 'Authenticate with OTP' button.");
		clickOn(AUTHENTICATION_BUTTON_LOCATOR);
		return this;
	}

	/**
	 * Selects all eligible AMCs/funds.
	 * 
	 * @return the active page instance
	 */
	public MutualFundCentralPage selectAllFunds() {
		logger.info("Selecting all eligible AMCs/Funds.");
		WebElement checkbox = getElementToBeClickable(SELECT_ALL_FUNDS);
		checkbox.click();
		return this;
	}

	/**
	 * Continues the portfolio import process.
	 * 
	 * @return the active page instance
	 */
	public MutualFundCentralPage continuePortfolioImport() {
		logger.info("Continuing with portfolio import.");
		WebElement continuePortfolioImport = getElementToBeClickable(CONTINUE_TO_PORTFOLIO_IMPORT_LOCATOR);
		continuePortfolioImport.click();
		return this;
	}

	/**
	 * Navigates to the Lender Selection page.
	 * 
	 * @return new instance of LenderSelectionPage
	 */
	public LenderSelectionPage checkLoanOffer() {
		logger.info("Navigating to view available loan offers.");
		WebElement seeLoanOffer = getElementToBeClickable(SEE_LOAN_OFFER_BUTTON_LOCATOR);
		seeLoanOffer.click();
		return new LenderSelectionPage(getDriver());
	}
	
	public MutualFundCentralPage isSeeLoanOfferDisplay() {
		Assert.assertTrue(isElementDisplayed(SEE_LOAN_OFFER_BUTTON_LOCATOR),"======================== Loan offer button is not displayed ========================");
		logger.info("======================== Loan offer button is displayed ========================");
	    return this;
	}
}
