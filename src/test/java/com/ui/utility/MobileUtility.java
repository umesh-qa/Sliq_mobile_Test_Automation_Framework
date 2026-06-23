package com.ui.utility;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public abstract class MobileUtility {

	private AndroidDriver driver;
	protected WebDriverWait wait;
	private Logger logger = LoggerUtility.getLogger(this.getClass());

	public MobileUtility() {

		UiAutomator2Options options = new UiAutomator2Options();

		logger.info("================== Mobile Configurations ==================");

		options.setPlatformName("Android");
		options.setAutomationName("UiAutomator2");
		options.setAppPackage(ReadConfiguration.getConfig("PACKAGE"));
		options.setAppActivity(ReadConfiguration.getConfig("ACTIVITY"));
		options.setDeviceName(ReadConfiguration.getConfig("DEVICE"));
		options.setUdid(ReadConfiguration.getConfig("UDID"));
		options.setNoReset(true);
		options.setCapability("appium:chromedriverExecutable", System.getProperty("user.dir") + File.separator
				+ "Configuration" + File.separator + "chromedriver.exe");

		// WebView Capabilities
		options.setCapability("appium:autoWebview", false);
		options.setCapability("appium:ensureWebviewsHavePages", true);
		options.setCapability("appium:chromedriverAutodownload", true);

		try {

			logger.info("URL : " + ReadConfiguration.getConfig("URL"));

			driver = new AndroidDriver(new URL(ReadConfiguration.getConfig("URL")), options);

			wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			logger.info("==========================================================");

		} catch (MalformedURLException e) {
			logger.error("Invalid Appium URL", e);
		}
	}

	public MobileUtility(AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public AndroidDriver getDriver() {
		return driver;
	}
	
	public Object getAPKVersion() {
		return getDriver().getCapabilities().getCapability("platformVersion");
	}
	public Object getDeviceInfo() {
		return getDriver().getCapabilities().getCapability("deviceName");
	}

	// =============================
	// Context Methods
	// =============================

	public void printAvailableContexts() {

		Set<String> contexts = driver.getContextHandles();

		System.out.println("\n===== AVAILABLE CONTEXTS =====");

		for (String context : contexts) {
			System.out.println(context);
		}

		System.out.println("Current Context : " + driver.getContext());

		System.out.println("==============================\n");
	}

	public void switchToWebView() {

		logger.info("Available Contexts : " + getDriver().getContextHandles());

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String context : getDriver().getContextHandles()) {

			if (context.contains("WEBVIEW")) {

				getDriver().context(context);

				logger.info("Switched To : " + getDriver().getContext());

				return;
			}
		}

		throw new RuntimeException("No WEBVIEW Context Found. Available Contexts : " + getDriver().getContextHandles());
	}

	public void waitForWebViewToLoad() {

		logger.info("Waiting for WebView context...");

		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

		wait.until(d -> ((AndroidDriver) getDriver()).getContextHandles().stream()
				.anyMatch(context -> context.contains("WEBVIEW")));
	}

	// =============================
	// Element Actions
	// =============================

	public void clickOn(By locator) {

		logger.info("Waiting for element : " + locator);

		try {
			driver.hideKeyboard();
		} catch (Exception e) {
			// Ignore if keyboard not present
		}

		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

		element.click();
	}

	public void clickOnWithoutHideKeyboard(By locator) {

		logger.info("Waiting for element : " + locator);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	public void enterText(By locator, String value) {

		logger.info("Entering text : " + value);

		try {
			driver.hideKeyboard();
		} catch (Exception e) {
			// Ignore
		}

		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

		element.click();
		element.clear();
		element.sendKeys(value);
	}

	public void enterTextAndPressEnter(By locator, String value) {

		logger.info("Entering text : " + value);

		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		element.click();
		element.clear();
		element.sendKeys(value, Keys.ENTER);
	}

	public void scrollToText(String text) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
						+ ".scrollIntoView(new UiSelector().text(\"" + text + "\"))")));
	}

	public void scrollToEnd(By locator) {
		boolean canScrollMore = true;

		while (canScrollMore) {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
					Map.of("left", 100, "top", 100, "width", 800, "height", 1200, "direction", "down", "percent", 50));
			canScrollMore = false;

		}
	}

	public void scrollforAlertPage() {
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd()"));
	}

	public void scrollUntilVisible(By locator) {
		int maxScroll = 15;

		while (!isElementDisplayed(locator) && maxScroll > 0) {
			((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
					Map.of("left", 100, "top", 200, "width", 800, "height", 1200, "direction", "down", "percent", 1.0));
			maxScroll--;

		}
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd()"));
	}

	public void scrollAndClick(By locator) {
		scrollUntilVisible(locator);
		javaScriptClick(locator);
	}

	public WebElement getElement(By locator) {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement getElementToBeClickable(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void selectFromDropdown(By dropdownLocator, String optionText) {

		clickOn(dropdownLocator);

		By option = AppiumBy.androidUIAutomator("new UiSelector().text(\"" + optionText + "\")");

		clickOn(option);
	}

	public void javaScriptClick(By locator) {

		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView(true);", element);

		js.executeScript("arguments[0].click();", element);
	}

	public void javaScriptScroll() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");

	}

	public String getVisibleText(By locator) {

		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		return element.getAttribute("content-desc");

	}

	public boolean isElementDisplayed(By locator) {

		try {

			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	// =============================
	// Debug Methods
	// =============================

	public void printPageSource() {

		System.out.println("========== PAGE SOURCE ==========");

		System.out.println(driver.getPageSource());

		System.out.println("=================================");
	}

	public void switchToNative() {

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		getDriver().context("NATIVE_APP");

		System.out.println("Current Context : " + getDriver().getContext());
	}

	// =============================
	// Screenshot
	// =============================

	public String getScreenshotPath(String screenshotName) {

		String screenShotPath = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator
				+ screenshotName + ".png";

		TakesScreenshot screenshot = driver;

		File source = screenshot.getScreenshotAs(OutputType.FILE);

		File destination = new File(screenShotPath);

		try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return screenShotPath;
	}

	// =============================
	// Driver Close
	// =============================

	public void quiteDriver() {

		logger.info("Closing Application");

		if (driver != null) {
			driver.quit();
		}
	}
}