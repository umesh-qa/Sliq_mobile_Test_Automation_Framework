package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ui.utility.MobileUtility;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class LenderSelectionPage extends MobileUtility {

	public LenderSelectionPage(AndroidDriver driver) {
		super(driver);
	}

	private static final By BAJAJ_FINANCE_OPTION_LOCATOR =
	        AppiumBy.androidUIAutomator(
	                "new UiSelector().descriptionContains(\"Bajaj Finance Ltd\")");
	private static final By EDIT_LOAN_AMOUNT_BUTTON_LOCATOR = AppiumBy
			.xpath("//android.widget.Button[@content-desc='Edit Loan Amount']");
	private static final By EIDT_LOAN_AMOUNT_TEXTBOX_LOCATOR = AppiumBy.className("android.widget.EditText");
	private static final By SAVE_EDIT_LOAN_AMOUNT_BUTTON_LOCATOR = AppiumBy
			.xpath("//android.widget.Button[@content-desc='Save']");
	private static final By CONTINUE_BUTTON_FOR_BAJAJ = AppiumBy
			.xpath("//android.widget.Button[@content-desc='Continue']");
	private static final By CONFIRM_BUTTON_FOR_BAJAJ = AppiumBy
			.xpath("//android.widget.Button[@content-desc='Continue with Bajaj Finance Ltd']");

	public LenderSelectionPage selectBajajFinalceLender() {
		WebElement BAJAJ_FINANCE_OPTION = wait
				.until(ExpectedConditions.elementToBeClickable(BAJAJ_FINANCE_OPTION_LOCATOR));
		BAJAJ_FINANCE_OPTION.click();
		return this;
	}

	public LenderSelectionPage clickOnEditLoanAmount(String amount) {
		WebElement EDIT_LOAN_AMOUNT = wait
				.until(ExpectedConditions.elementToBeClickable(EDIT_LOAN_AMOUNT_BUTTON_LOCATOR));
		EDIT_LOAN_AMOUNT.click();
		enterText(EIDT_LOAN_AMOUNT_TEXTBOX_LOCATOR, amount);
		WebElement SaveButton = wait
				.until(ExpectedConditions.elementToBeClickable(SAVE_EDIT_LOAN_AMOUNT_BUTTON_LOCATOR));
		SaveButton.click();
		return this;
	}

	public KycVerificationPage continueWithBajaj() {
		clickOn(CONTINUE_BUTTON_FOR_BAJAJ);
		clickOn(CONFIRM_BUTTON_FOR_BAJAJ);
		return new KycVerificationPage(getDriver());
	}

}
