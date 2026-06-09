package com.ui.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ui.utility.MobileUtility;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class MutualFond_centeralPage extends MobileUtility {

	public MutualFond_centeralPage(AndroidDriver driver) {
		super(driver);
	}

	private static final By SELECT_ALL_FOUNDS = AppiumBy.xpath(
			"//android.widget.TextView[@text='Select All Eligible AMCs']/preceding-sibling::android.widget.CheckBox");
	private static final By AUTHENTICATION_BUTTON_LOCATOR = By
			.xpath("//button[contains(text(),'Authenticate with OTP')]");
	private static final By CONTINUE_TO_PORTFOLIO_IMPORT_LOCATOR = By
			.xpath("//android.widget.Button[@text='Continue to Portfolio Import']");
	private static final By SEE_LOAN_OFFER_BUTTON_LOCATOR = AppiumBy.accessibilityId("See Loan Offers");

	public MutualFond_centeralPage enterOtp(String otp) {

		switchToWebView();

		wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

		wait.until(d -> d.findElements(By.tagName("input")).size() >= 6);

		List<WebElement> otpBoxes = getDriver().findElements(By.tagName("input"));

		System.out.println("OTP BOX COUNT : " + otpBoxes.size());

		for (int i = 0; i < Math.min(otp.length(), otpBoxes.size()); i++) {

			otpBoxes.get(i).sendKeys(String.valueOf(otp.charAt(i)));
		}
		return this;
	}

	public MutualFond_centeralPage clickOnAuthenticateWithOTP() {
		clickOn(AUTHENTICATION_BUTTON_LOCATOR);
		return this;
	}

	public MutualFond_centeralPage selectAllFound() {
		switchToNative();
		WebElement checkbox = getElementToBeClickable(SELECT_ALL_FOUNDS);
		checkbox.click();
		return this;
	}

	public MutualFond_centeralPage continuePortfolioImport() {
		WebElement continue_PortfolioImport = getElementToBeClickable(CONTINUE_TO_PORTFOLIO_IMPORT_LOCATOR);
		continue_PortfolioImport.click();
		return this;
	}

	public LenderSelectionPage checkLoanOffer() {
		WebElement SEE_LOAN_OFFER_LOCATOR = getElementToBeClickable(SEE_LOAN_OFFER_BUTTON_LOCATOR);
		SEE_LOAN_OFFER_LOCATOR.click();
		return new LenderSelectionPage(getDriver());
	}

}
