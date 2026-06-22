package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ui.utility.LoggerUtility;
import com.ui.utility.MobileUtility;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class UploadHoldingPage extends MobileUtility {

	private Logger logger = LoggerUtility.getLogger(this.getClass());

	public UploadHoldingPage(AndroidDriver driver) {
		super(driver);
	}

	private static final By ADD_HOLDING_MANUALLY_OPTION_LOCATOR = AppiumBy
			.xpath("//android.view.View[@content-desc='Add Holdings Manually']");

	private static final By CONTINUE_BUTTON_LOCATOR = AppiumBy
			.xpath("//android.widget.Button[@content-desc=\"Continue\"]");

	public UploadHoldingPage addHoldingsManually() {
		logger.info("Adding Holding documents manually.");
		clickOn(ADD_HOLDING_MANUALLY_OPTION_LOCATOR);
		clickOn(CONTINUE_BUTTON_LOCATOR);
		clickOn(ADD_SECURITY_BUTTON_LOCATOR);
		return this;
	}

	// ================== ADD/EDIT PORTFOLIO DOCUMETS ==================//
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

	public UploadHoldingPage addSecurity(String shareName) {
		logger.info("Adding securities");
		enterText(SHARE_NAME_TEXTBOX_LOCATOR, shareName);
		return this;
	}

	public UploadHoldingPage selectShare(String shareName) {
		By SHARE_LOCATOR = By.xpath("//android.widget.Button[contains(@content-desc,'" + shareName + "')]");
		WebElement shareElement = wait.until(ExpectedConditions.visibilityOfElementLocated(SHARE_LOCATOR));
		shareElement.click();
		return this;
	}

	public UploadHoldingPage enterSharesQTY(String sharesQty) {
		logger.info("Adding Shares QTY");
		enterText(TOTAL_SHARES_QTY_TEXTBOX_LOCATOR, sharesQty);
		return this;
	}

	public UploadHoldingPage addToPortfolio() {
		logger.info("Adding shares to portfolio");
		clickOnWithoutHideKeyboard(ADD_TO_PORTFOLIO_BUTTON_LOCATOR);
		return this;
	}

	public UploadHoldingPage addMoreShare() {
		logger.info("Adding More shares to portfolio");
		clickOn(EDIT_BUTTON_LOCATOR);
		clickOn(ADD_MORE_SHARE_BUTTON_LOCATOR);
		return this;
	}

	public MutualFond_centeralPage clickOnUpdatePortfolio() {
		logger.info("Updating added share to portfolio");
		try {
			clickOn(UPDATE_PORTFOLIO_BUTTON_LOCATOR);
			clickOn(CONFIRM_CONTINUE_BUTTON_LOCATOR);
		} catch (Exception e) {
			clickOn(CONFIRM_CONTINUE_BUTTON_LOCATOR);
		}
		return new MutualFond_centeralPage(getDriver());

	}

}
