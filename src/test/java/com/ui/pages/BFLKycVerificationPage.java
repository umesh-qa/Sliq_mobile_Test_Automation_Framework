package com.ui.pages;

import java.util.List;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ui.utility.LoggerUtility;
import com.ui.utility.MobileUtility;
import com.ui.utility.OTPUtility;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

/**
 * Page object representing the BFL KYC Verification page.
 * Handles email verification, personal/financial details inputs, and lender agreement acceptance.
 */
public class BFLKycVerificationPage extends MobileUtility {

	private final Logger logger = LoggerUtility.getLogger(this.getClass());

	public BFLKycVerificationPage(AndroidDriver driver) {
		super(driver);
	}

	private static final By VERIFY_BUTTON_LOCATOR = AppiumBy.xpath("//android.widget.Button[@text='Verify']");
	private static final By OTP_TEXTBOX_LOCATOR = AppiumBy
			.xpath("//android.webkit.WebView[@text='Login']/android.widget.EditText[2]");
	private static final By CONTINUE_BUTTON_LOCATOR = AppiumBy.xpath("//android.widget.Button[@text='Continue']");

	// ------------------------ Enter Personal Details -----------------------------//
	private static final By GENDER_DROPDOWN_LOCATOR = AppiumBy.xpath("//android.view.View[@text='Select Gender']");
	private static final By FATHER_NAME_TEXTBOX_LOCATOR = AppiumBy
			.xpath("//android.widget.EditText[@resource-id='input-fatherName']");
	private static final By MOTHER_NAME_TEXTBOX_LOCATOR = AppiumBy
			.xpath("//android.widget.EditText[@resource-id='input-motherName']");
	private static final By MARITAL_DROPDOWN_LOCATOR = AppiumBy
			.xpath("//android.view.View[@text='Select Marital Status']");
	private static final By NEXT_BUTTON_LOCATOR = AppiumBy.xpath("//android.widget.Button[@text='Next']");

	// -------------------------- Enter Financial Details -------------------------//
	private static final By SALARY_DD_LOCATOR = AppiumBy.xpath("//android.view.View[@text='Select salary']");
	private static final By OCCUPATION_DD_LOCATOR = AppiumBy.xpath("//android.view.View[@text='Select Occupation']");
	private static final By NATURE_OF_BUSINESS_DD_LOCATOR = AppiumBy
			.xpath("//android.view.View[@text=\"Select Nature Of Business\"]");
	private static final By CHECKBOX1_LOCATOR = AppiumBy.xpath(
			"//android.widget.CheckBox[@text=\" I authorize Valuenable to share my personal data with its lending partners and service providers, including Digio, solely for the purpose of processing my loan application, e-signing, and related verification activities.\"]");
	private static final By CHECKBOX2_LOCATOR = AppiumBy
			.xpath("//android.widget.TextView[@text=\"I accept Valuenable's\"]");
	private static final By SUBMIT_BUTTON_LOCATOR = AppiumBy.xpath("//android.widget.Button[@text='Submit']");

	// ---------------------- ALERT BOX -------------------------------------//
	private static final By ALERT_POLITICAL_RADIO_BUTTON_LOCATOR = By.xpath("//android.widget.RadioButton[@text='No']");
	private static final By ALERT_CHECKBOX1_LOCATOR = By
			.xpath("//span[contains(text(),'I have read, understood, and hereby accept the')]");
	private static final By ALERT_CHECKBOX2_LOCATOR = By.xpath("//span[contains(text(),'I authorize BFL to receive')]");
	private static final By ALERT_CHECKBOX3_LOCATOR = By
			.xpath("//span[contains(text(),'I hereby expressly authorize Bajaj Finance Limited')]");
	private static final By ALERT_OK_BUTTON_LOCATOR = By.xpath("//button[@type='button' and contains(text(),'Ok')]");

	// Alert page element with scroll locator
	private static final By ALERT_DD_SCROLL_LOCATOR = By.xpath("//input[@value='yes']");
	private static final By ALL_MANDATORY_MISSING_TEXT_LOCATOR = AppiumBy.xpath(
			"//android.view.View[@text=\"Father Name, Mother Name, Gender, Marital Status, Occupation, Salary and Nature Of Business are required.\"]");
	private static final By PERSONAL_MANDATORY_MISSING_TEXT_LOCATOR = AppiumBy.xpath(
			"//android.view.View[@text=\"Occupation, Salary and Nature Of Business are required.\"]");

	/**
	 * Clicks the Email Verify button.
	 * 
	 * @return the active page instance
	 */
	public BFLKycVerificationPage verifyEmail() {
		logger.info("Clicking on Email verification 'Verify' button.");
		clickOn(VERIFY_BUTTON_LOCATOR);
		return this;
	}

	/**
	 * Scans OTP from notifications and enters it into the textboxes.
	 * 
	 * @return the active page instance
	 */
	public BFLKycVerificationPage enterOtp() {
		logger.info("Starting OTP retrieval and entry process for Email verification...");
		String otp = OTPUtility.waitForOtp(getDriver(), 30);

		List<WebElement> textBoxes = getDriver().findElements(By.className("android.widget.EditText"));
		logger.info("Total textboxes detected on current screen: " + textBoxes.size());

		if (textBoxes.size() < 6) {
			logger.error("Failed to enter OTP: found less than 6 textboxes.");
			throw new RuntimeException("Less than 6 OTP boxes found");
		}

		// Take the LAST 6 textboxes
		List<WebElement> otpBoxes = textBoxes.subList(textBoxes.size() - 8, textBoxes.size());
		logger.info("Using " + otpBoxes.size() + " textboxes for OTP entry.");

		for (int i = 0; i < 6; i++) {
			otpBoxes.get(i).click();
			otpBoxes.get(i).clear();
			otpBoxes.get(i).sendKeys(String.valueOf(otp.charAt(i)));
		}
		logger.info("OTP entered successfully. Proceeding to submit.");
		clickOn(CONTINUE_BUTTON_LOCATOR);

		return this;
	}

	/**
	 * Fills in the user's personal details.
	 * 
	 * @param gender gender of the user
	 * @param fatherName father's name
	 * @param motherName mother's name
	 * @param maritalStatus marital status
	 * @return the active page instance
	 */
	public BFLKycVerificationPage enterPersonalDetails(String gender, String fatherName, String motherName,
			String maritalStatus) {
		logger.info("Entering personal details - Gender: " + gender + ", Father: " + fatherName + ", Mother: " + motherName + ", Marital Status: " + maritalStatus);
		selectFromDropdown(GENDER_DROPDOWN_LOCATOR, gender);
		enterText(FATHER_NAME_TEXTBOX_LOCATOR, fatherName);
		enterText(MOTHER_NAME_TEXTBOX_LOCATOR, motherName);
		selectFromDropdown(MARITAL_DROPDOWN_LOCATOR, maritalStatus);
		clickOn(NEXT_BUTTON_LOCATOR);
		return this;
	}

	/**
	 * Clicks the Next button on personal details section.
	 * 
	 * @return the active page instance
	 */
	public BFLKycVerificationPage clickOnNext() {
		logger.info("Clicking on 'Next' button.");
		clickOn(NEXT_BUTTON_LOCATOR);
		return this;
	}

	/**
	 * Fills in financial details.
	 * 
	 * @param salary annual salary range
	 * @param occupation employment type
	 * @param businessName business sector name
	 * @return the active page instance
	 */
	public BFLKycVerificationPage enterFinancialDetails(String salary, String occupation, String businessName) {
		logger.info("Entering financial details - Salary: " + salary + ", Occupation: " + occupation + ", Business: " + businessName);
		selectFromDropdown(SALARY_DD_LOCATOR, salary);
		selectFromDropdown(OCCUPATION_DD_LOCATOR, occupation);
		selectFromDropdown(NATURE_OF_BUSINESS_DD_LOCATOR, businessName);
		clickOn(CHECKBOX1_LOCATOR);
		clickOn(CHECKBOX2_LOCATOR);
//		scrollToEnd(SUBMIT_BUTTON_LOCATOR);
//		clickOn(SUBMIT_BUTTON_LOCATOR);
//		javaScriptClick(SUBMIT_BUTTON_LOCATOR);
		return this;
	}

	/**
	 * Accepts authorization check agreements.
	 * 
	 * @return the active page instance
	 */
	public BFLKycVerificationPage acceptTerms() {
		logger.info("Accepting authorization checkboxes.");
		clickOn(CHECKBOX1_LOCATOR);
		clickOn(CHECKBOX2_LOCATOR);
		return this;
	}

	/**
	 * Clicks the Submit button.
	 * 
	 * @return the active page instance
	 */
	public BFLKycVerificationPage clickOnSubmitButton() {
		logger.info("Scrolling and clicking on 'Submit' button.");
		scrollAndClick(SUBMIT_BUTTON_LOCATOR);
		return this;
	}

	/**
	 * Validates that the all-mandatory-fields-missing warning appears.
	 */
	public void checkKYCMandatoryMessage() {
		logger.info("Verifying all KYC fields mandatory error message.");
		checkMandatoryMessage(ALL_MANDATORY_MISSING_TEXT_LOCATOR);
	}

	/**
	 * Validates that the personal-mandatory-fields-missing warning appears.
	 */
	public void checkPersonalMandatoryMessage() {
		logger.info("Verifying personal details fields mandatory error message.");
		checkMandatoryMessage(PERSONAL_MANDATORY_MISSING_TEXT_LOCATOR);
	}

	/**
	 * Completes the final terms and conditions agreement in WebView, returning to PledgeFundPage.
	 * 
	 * @return new PledgeFundPage instance
	 */
	public PledgeFundPage acceptTermsConditions() {
		logger.info("Accepting final lender terms and conditions natively (NATIVE_APP context)...");
		
		// Ensure we are in NATIVE_APP context to execute native commands and scroll gestures reliably
		try {
			if (getDriver().getContext() != null && !getDriver().getContext().equals("NATIVE_APP")) {
				logger.info("Switching to NATIVE_APP context for native element interaction.");
				getDriver().context("NATIVE_APP");
			}
		} catch (Exception e) {
			logger.warn("Could not check or switch context, proceeding: " + e.getMessage());
		}

		// 1. Locate the native radio button for 'Yes' (matching value='yes' selection)
		By nativeRadioYes = AppiumBy.xpath(
				"//android.widget.RadioButton[contains(@text, 'Yes') or contains(@text, 'yes') or contains(@content-desc, 'Yes') or contains(@content-desc, 'yes')]");
		
		logger.info("Scrolling and selecting 'Yes' radio button.");
		scrollAndClick(nativeRadioYes);

		// 2. Locate and check the 3 mandatory checkboxes natively by matching text/content-desc
		By checkbox1 = AppiumBy.xpath("//*[contains(@text, 'I have read') or contains(@content-desc, 'I have read')]");
		By checkbox2 = AppiumBy.xpath("//*[contains(@text, 'Politically Exposed') or contains(@content-desc, 'Politically Exposed')]");
		By checkbox3 = AppiumBy.xpath("//*[contains(@text, 'authorize Valueable') or contains(@content-desc, 'authorize Valueable')]");

		logger.info("Scrolling and checking agreement checkboxes.");
		scrollAndClick(checkbox1);
		scrollAndClick(checkbox2);
		scrollAndClick(checkbox3);

		// 3. Locate and click the native 'Ok' button
		By nativeOkButton = AppiumBy.xpath(
				"//android.widget.Button[contains(@text, 'Ok') or contains(@text, 'OK') or contains(@content-desc, 'Ok') or contains(@content-desc, 'OK')]");

		logger.info("Scrolling and clicking 'Ok' button.");
		scrollAndClick(nativeOkButton);

		logger.info("Final terms accepted natively. Returning to Pledge Fund page.");
		return new PledgeFundPage(getDriver());
	}
}
