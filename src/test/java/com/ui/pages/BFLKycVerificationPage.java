package com.ui.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ui.utility.MobileUtility;
import com.ui.utility.OTPUtility;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class BFLKycVerificationPage extends MobileUtility {

	public BFLKycVerificationPage(AndroidDriver driver) {
		super(driver);
	}

	private static final By VERIFY_BUTTON_LOCATOR = AppiumBy.xpath("//android.widget.Button[@text='Verify']");
	private static final By OTP_TEXTBOX_LOCATOR = AppiumBy
			.xpath("//android.webkit.WebView[@text='Login']/android.widget.EditText[2]");
	private static final By CONTINUE_BUTTON_LOCATOR = AppiumBy.xpath("//android.widget.Button[@text='Continue']");

	// ------------------------ Enter Personal Details
	// -----------------------------//

	private static final By GENDER_DROPDOWN_LOCATOR = AppiumBy.xpath("//android.view.View[@text='Select Gender']");
	private static final By FATHER_NAME_TEXTBOX_LOCATOR = AppiumBy
			.xpath("//android.widget.EditText[@resource-id='input-fatherName']");
	private static final By MOTHER_NAME_TEXTBOX_LOCATOR = AppiumBy
			.xpath("//android.widget.EditText[@resource-id='input-motherName']");
	private static final By MARITAL_DROPDOWN_LOCATOR = AppiumBy
			.xpath("//android.view.View[@text='Select Marital Status']");
	private static final By NEXT_BUTTON_LOCATOR = AppiumBy.xpath("//android.widget.Button[@text='Next']");

	// -------------------------- Enter Financial Details
	// -------------------------//

	private static final By SALARY_DD_LOCATOR = AppiumBy.xpath("//android.view.View[@text='Select salary']");
	private static final By OCCUPATION_DD_LOCATOR = AppiumBy.xpath("//android.view.View[@text='Select Occupation']");

	private static final By NATURE_OF_BUSINESS_DD_LOCATOR = AppiumBy
			.xpath("//android.view.View[@text=\"Select Nature Of Business\"]");

	private static final By FINANCIALL_DEPARTMENT_DD_LOCATOR = AppiumBy
			.xpath("//android.view.View[@text='Select Option']");
	private static final By CHECKBOX1_LOCATOR = AppiumBy.xpath(
			"//android.widget.CheckBox[@text=\" I authorize Valuenable to share my personal data with its lending partners and service providers, including Digio, solely for the purpose of processing my loan application, e-signing, and related verification activities.\"]");
	private static final By CHECKBOX2_LOCATOR = AppiumBy
			.xpath("//android.widget.TextView[@text=\"I accept Valuenable's\"]");
	private static final By SUMBIT_BUTTON_LOCATOR = AppiumBy.xpath("//android.widget.Button[@text='Submit']");

//	private static final By SUMBIT_BUTTON_LOCATOR = AppiumBy.androidUIAutomator(
//			"new UiScrollable(new UiSelector().scrollable(true))" + ".scrollIntoView(new UiSelector().text('Submit'))");

	// ---------------------- ALERT BOX -------------------------------------//
	private static final By ALERT_POLITICAL_RADIO_BUTTON_LOCATOR = By.xpath("//android.widget.RadioButton[@text='No']");

	private static final By ALERT_CHECKBOX1_LOCATOR = By
			.xpath("//span[contains(text(),'I have read, understood, and hereby accept the')]");

	private static final By ALERT_CHECKBOX2_LOCATOR = By.xpath("//span[contains(text(),'I authorize BFL to receive')]");
	// span[contains(text(),'I hereby expressly authorize Bajaj Finance Limited')]
	private static final By ALERT_CHECKBOX3_LOCATOR = By
			.xpath("//span[contains(text(),'I hereby expressly authorize Bajaj Finance Limited')]");

	private static final By ALERT_OK_BUTTON_LOCATOR = By.xpath("//button[@type='button' and contains(text(),'Ok')]");

	// alert page element with scrolllocator
	private static final By ALERT_DD_SCROLL_LOCATOR = By.xpath("//input[@value='no']");

	// --------------------------------------------------------------------------------//
	public BFLKycVerificationPage VerifyEmail() {
		clickOn(VERIFY_BUTTON_LOCATOR);
		return this;
	}

	public BFLKycVerificationPage enterOtp() {

		String otp = OTPUtility.waitForOtp(getDriver(), 30);

		List<WebElement> textBoxes = getDriver().findElements(By.className("android.widget.EditText"));

		System.out.println("Total Textboxes = " + textBoxes.size());

		if (textBoxes.size() < 6) {

			throw new RuntimeException("Less than 6 OTP boxes found");
		}

		// Take the LAST 6 textboxes
		List<WebElement> otpBoxes = textBoxes.subList(textBoxes.size() - 8, textBoxes.size());

		System.out.println("OTP BOX COUNT = " + otpBoxes.size());

		for (int i = 0; i < 6; i++) {

			otpBoxes.get(i).click();

			otpBoxes.get(i).clear();

			otpBoxes.get(i).sendKeys(String.valueOf(otp.charAt(i)));
		}
		clickOn(CONTINUE_BUTTON_LOCATOR);

		return this;
	}

	public BFLKycVerificationPage enterPersonalDetails(String Gender, String FatherName, String MotherName,
			String MaritalStatus) {
		selectFromDropdown(GENDER_DROPDOWN_LOCATOR, Gender);
		enterText(FATHER_NAME_TEXTBOX_LOCATOR, FatherName);
		enterText(MOTHER_NAME_TEXTBOX_LOCATOR, MotherName);
		selectFromDropdown(MARITAL_DROPDOWN_LOCATOR, MaritalStatus);
		clickOn(NEXT_BUTTON_LOCATOR);
		return this;
	}

	public BFLKycVerificationPage enterFinancialDetails(String Salary, String Occupation, String BusinessName) {
		selectFromDropdown(SALARY_DD_LOCATOR, Salary);
		selectFromDropdown(OCCUPATION_DD_LOCATOR, Occupation);
		selectFromDropdown(NATURE_OF_BUSINESS_DD_LOCATOR, BusinessName);
//		selectFromDropdown(FINANCIALL_DEPARTMENT_DD_LOCATOR, financialDepartment);
		clickOn(CHECKBOX1_LOCATOR);
		clickOn(CHECKBOX2_LOCATOR);
		scrollToEnd(SUMBIT_BUTTON_LOCATOR);
		clickOn(SUMBIT_BUTTON_LOCATOR);
//		javaScriptClick(SUMBIT_BUTTON_LOCATOR);

		return this;
	}

	// Alert Page Action
	public PledgeFoundPage acceptTermsConditions() {
//		scrollToEnd();
		switchToWebView();
		scrollToEnd(ALERT_DD_SCROLL_LOCATOR);
		clickOn(ALERT_DD_SCROLL_LOCATOR);
		scrollToEnd(ALERT_CHECKBOX1_LOCATOR);
		javaScriptClick(ALERT_CHECKBOX1_LOCATOR);
		scrollToEnd(ALERT_CHECKBOX2_LOCATOR);
		javaScriptClick(ALERT_CHECKBOX2_LOCATOR);
		scrollToEnd(ALERT_CHECKBOX3_LOCATOR);
		javaScriptClick(ALERT_CHECKBOX3_LOCATOR);
		scrollToEnd(ALERT_OK_BUTTON_LOCATOR);
		javaScriptClick(ALERT_OK_BUTTON_LOCATOR);
		return new PledgeFoundPage(getDriver());
	}
}
