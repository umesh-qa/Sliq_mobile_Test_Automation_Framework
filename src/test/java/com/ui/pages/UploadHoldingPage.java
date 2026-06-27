package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ui.utility.LoggerUtility;
import com.ui.utility.MobileUtility;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

/**
 * Page object representing the Upload Holdings Page (specifically manual security portfolio editing).
 */
public class UploadHoldingPage extends MobileUtility {

	private final Logger logger = LoggerUtility.getLogger(this.getClass());

	public UploadHoldingPage(AndroidDriver driver) {
		super(driver);
	}

	private static final By ADD_HOLDING_MANUALLY_OPTION_LOCATOR = AppiumBy
			.xpath("//android.view.View[@content-desc='Add Holdings Manually']");
	private static final By CONTINUE_BUTTON_LOCATOR = AppiumBy
			.xpath("//android.widget.Button[@content-desc=\"Continue\"]");

	/**
	 * Chooses to add holdings manually.
	 * 
	 * @return the active page instance
	 */
	public UploadHoldingPage addHoldingsManually() {
		logger.info("Choosing manual holdings input options.");
		clickOn(ADD_HOLDING_MANUALLY_OPTION_LOCATOR);
		clickOn(CONTINUE_BUTTON_LOCATOR);
		clickOn(ADD_SECURITY_BUTTON_LOCATOR);
		return this;
	}

	// ================== ADD/EDIT PORTFOLIO DOCUMENTS ==================//
	private static final By ADD_SECURITY_BUTTON_LOCATOR = AppiumBy.accessibilityId("+ Add Security");
	private static final By TOTAL_SHARES_QTY_TEXTBOX_LOCATOR = AppiumBy.xpath("//android.widget.EditText");
	private static final By SHARE_NAME_TEXTBOX_LOCATOR = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)");
	private static final By ADD_TO_PORTFOLIO_BUTTON_LOCATOR = AppiumBy
			.xpath("//android.widget.Button[@content-desc='Add to Portfolio']");
	private static final By EDIT_BUTTON_LOCATOR = AppiumBy.xpath("//android.view.View[@content-desc=\"Edit\"]");
	private static final By ADD_MORE_SHARE_BUTTON_LOCATOR = AppiumBy
			.xpath("//android.widget.ImageView[@content-desc=\"Powered by\"]/android.view.View/android.view.View[6]");
	private static final By UPDATE_PORTFOLIO_BUTTON_LOCATOR = AppiumBy
			.xpath("//android.widget.Button[@content-desc=\"Update Portfolio\"]");
	private static final By CONFIRM_CONTINUE_BUTTON_LOCATOR = AppiumBy
			.xpath("//android.widget.Button[@content-desc=\"Confirm & Continue\"]");

	/**
	 * Inputs target security name for portfolio creation.
	 * 
	 * @param shareName name of the share/stock
	 * @return the active page instance
	 */
	public UploadHoldingPage addSecurity(String shareName) {
		logger.info("Adding security: " + shareName);
		enterText(SHARE_NAME_TEXTBOX_LOCATOR, shareName);
		return this;
	}

	/**
	 * Selects a specific share element from the matching auto-suggestions.
	 * 
	 * @param shareName precise name of the share
	 * @return the active page instance
	 */
	public UploadHoldingPage selectShare(String shareName) {
		logger.info("Selecting share match: " + shareName);
		By shareLocator = By.xpath("//android.widget.Button[contains(@content-desc,'" + shareName + "')]");
		WebElement shareElement = wait.until(ExpectedConditions.visibilityOfElementLocated(shareLocator));
		shareElement.click();
		return this;
	}

	/**
	 * Inputs the number of shares.
	 * 
	 * @param sharesQty quantity of shares
	 * @return the active page instance
	 */
	public UploadHoldingPage enterSharesQTY(String sharesQty) {
		logger.info("Entering quantity: " + sharesQty);
		enterText(TOTAL_SHARES_QTY_TEXTBOX_LOCATOR, sharesQty);
		return this;
	}

	/**
	 * Adds the selected security details to the portfolio list.
	 * 
	 * @return the active page instance
	 */
	public UploadHoldingPage addToPortfolio() {
		logger.info("Adding item to portfolio.");
		clickOnWithoutHideKeyboard(ADD_TO_PORTFOLIO_BUTTON_LOCATOR);
		return this;
	}

	/**
	 * Initiates the addition of extra shares.
	 * 
	 * @return the active page instance
	 */
	public UploadHoldingPage addMoreShare() {
		logger.info("Adding more shares to portfolio list.");
		clickOn(EDIT_BUTTON_LOCATOR);
		clickOn(ADD_MORE_SHARE_BUTTON_LOCATOR);
		return this;
	}

	/**
	 * Confirms all portfolio modifications and routes to MutualFundCentralPage.
	 * 
	 * @return new MutualFundCentralPage instance
	 */
	public MutualFundCentralPage clickOnUpdatePortfolio() {
		logger.info("Updating portfolio changes.");
		try {
			clickOn(UPDATE_PORTFOLIO_BUTTON_LOCATOR);
			clickOn(CONFIRM_CONTINUE_BUTTON_LOCATOR);
		} catch (Exception e) {
			logger.warn("Update overlay did not present, executing confirm action directly: " + e.getMessage());
			clickOn(CONFIRM_CONTINUE_BUTTON_LOCATOR);
		}
		return new MutualFundCentralPage(getDriver());
	}
}
