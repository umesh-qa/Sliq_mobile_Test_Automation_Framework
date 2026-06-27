package com.ui.utility;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public abstract class MobileUtility {

	private AndroidDriver driver;
	protected WebDriverWait wait;
	private Logger logger = LoggerUtility.getLogger(this.getClass());

	public MobileUtility() {

		logger.info("================== Mobile Configurations ==================");

		String runMode = ReadConfiguration.getConfig("RUN_MODE");
		if (runMode == null || runMode.isEmpty()) {
			runMode = "LOCAL_REAL";
		}
		logger.info("Selected Run Mode: " + runMode);

		URL remoteUrl = null;
		org.openqa.selenium.MutableCapabilities caps = null;

		try {
			if (runMode.equalsIgnoreCase("CLOUD_BROWSERSTACK")) {
				// Cloud Execution (e.g. BrowserStack)
				logger.info("Configuring capabilities for BrowserStack Cloud Execution...");
				remoteUrl = new URL(ReadConfiguration.getConfig("BROWSERSTACK_URL"));

				org.openqa.selenium.MutableCapabilities bsOptions = new org.openqa.selenium.MutableCapabilities();
				bsOptions.setCapability("platformName", "android");
				bsOptions.setCapability("appium:platformVersion", "13.0"); // Target OS Version
				bsOptions.setCapability("appium:deviceName", "Google Pixel 7"); // Target Device
				bsOptions.setCapability("appium:app", ReadConfiguration.getConfig("BROWSERSTACK_APP_URL"));
				bsOptions.setCapability("appium:automationName", "UiAutomator2");

				// BrowserStack specific credentials and options
				java.util.HashMap<String, Object> bstackOptions = new java.util.HashMap<>();
				bstackOptions.put("userName", ReadConfiguration.getConfig("BROWSERSTACK_USERNAME"));
				bstackOptions.put("accessKey", ReadConfiguration.getConfig("BROWSERSTACK_ACCESS_KEY"));
				bstackOptions.put("projectName", "SLIQ Mobile Automation");
				bstackOptions.put("buildName", "Execution_Build_" + new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date()));
				bstackOptions.put("sessionName", "Mutual Fund Loan Journey");
				bsOptions.setCapability("bstack:options", bstackOptions);

				caps = bsOptions;

			} else if (runMode.equalsIgnoreCase("LOCAL_EMULATOR")) {
				// Local Emulator Execution (Free Option)
				logger.info("Configuring capabilities for Local Android Emulator...");
				remoteUrl = new URL(ReadConfiguration.getConfig("URL"));

				UiAutomator2Options emulatorOptions = new UiAutomator2Options();
				emulatorOptions.setPlatformName("Android");
				emulatorOptions.setAutomationName("UiAutomator2");
				emulatorOptions.setAppPackage(ReadConfiguration.getConfig("PACKAGE"));
				emulatorOptions.setAppActivity(ReadConfiguration.getConfig("ACTIVITY"));
				emulatorOptions.setDeviceName("Android Emulator"); // Standard name for emulators
				emulatorOptions.setNoReset(true);

				// Automatically locate and configure APK path from APK directory for installation
				String apkPath = getApkPath();
				if (apkPath != null) {
					logger.info("Automatically detected APK in project workspace: " + apkPath);
					emulatorOptions.setApp(apkPath);
				}

				emulatorOptions.setCapability("appium:unicodeKeyboard", true);
				emulatorOptions.setCapability("appium:resetKeyboard", true);
				emulatorOptions.setCapability("appium:sendKeyStrategy", "setValue");
				emulatorOptions.setIgnoreHiddenApiPolicyError(true);
				emulatorOptions.setCapability("appium:chromedriverExecutable", System.getProperty("user.dir") + File.separator
						+ "Configuration" + File.separator + "chromedriver.exe");
				emulatorOptions.setCapability("appium:autoWebview", false);
				emulatorOptions.setCapability("appium:ensureWebviewsHavePages", true);
				emulatorOptions.setCapability("appium:chromedriverAutodownload", true);

				caps = emulatorOptions;

			} else {
				// Default: Local Real Device
				logger.info("Configuring capabilities for Local Real Device...");
				remoteUrl = new URL(ReadConfiguration.getConfig("URL"));

				UiAutomator2Options localRealOptions = new UiAutomator2Options();
				localRealOptions.setPlatformName("Android");
				localRealOptions.setAutomationName("UiAutomator2");
				localRealOptions.setAppPackage(ReadConfiguration.getConfig("PACKAGE"));
				localRealOptions.setAppActivity(ReadConfiguration.getConfig("ACTIVITY"));
				localRealOptions.setDeviceName(ReadConfiguration.getConfig("DEVICE"));
				localRealOptions.setUdid(ReadConfiguration.getConfig("UDID"));
				localRealOptions.setNoReset(true);
				localRealOptions.setCapability("appium:unicodeKeyboard", true);
				localRealOptions.setCapability("appium:resetKeyboard", true);
				localRealOptions.setCapability("appium:sendKeyStrategy", "setValue");
				localRealOptions.setIgnoreHiddenApiPolicyError(true);
				localRealOptions.setCapability("appium:chromedriverExecutable", System.getProperty("user.dir") + File.separator
						+ "Configuration" + File.separator + "chromedriver.exe");
				localRealOptions.setCapability("appium:autoWebview", false);
				localRealOptions.setCapability("appium:ensureWebviewsHavePages", true);
				localRealOptions.setCapability("appium:chromedriverAutodownload", true);

				caps = localRealOptions;
			}

			logger.info("Connecting to Appium Server at URL: " + remoteUrl);
			driver = new AndroidDriver(remoteUrl, caps);
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

		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

		element.click();
		element.clear();
		element.sendKeys(value);
	}

	public void hideKeyboard() {
		try {
			if (driver != null) {
				driver.hideKeyboard();
			}
		} catch (Exception e) {
			// Ignore if keyboard not present
		}
	}

	public void enterTextAndPressEnter(By locator, String value) {

		logger.info("Entering text : " + value);

		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		element.click();
		element.clear();
		element.sendKeys(value);

		try {
			logger.info("Pressing native Enter key.");
			driver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(
					io.appium.java_client.android.nativekey.AndroidKey.ENTER));
		} catch (Exception e) {
			logger.warn("Could not natively press Enter: " + e.getMessage());
			element.sendKeys(Keys.ENTER);
		}
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
		int width = 1080;
		int height = 1920;
		try {
			org.openqa.selenium.Dimension size = driver.manage().window().getSize();
			width = size.getWidth();
			height = size.getHeight();
		} catch (Exception e) {
			logger.warn("Could not get window size, using default 1080x1920: " + e.getMessage());
		}

		int startX = width / 2;
		int startY = (int) (height * 0.7);
		int endX = width / 2;
		int endY = (int) (height * 0.3);

		String previousPageSource = "";
		while (!isElementDisplayedQuick(locator) && maxScroll > 0) {
			String currentPageSource = "";
			try {
				currentPageSource = driver.getPageSource();
			} catch (Exception ignored) {}

			if (currentPageSource != null && currentPageSource.equals(previousPageSource)) {
				logger.info("Page source did not change. Reached bottom or end of scrollable content.");
				break;
			}
			previousPageSource = currentPageSource;

			try {
				org.openqa.selenium.interactions.PointerInput finger = new org.openqa.selenium.interactions.PointerInput(org.openqa.selenium.interactions.PointerInput.Kind.TOUCH, "finger");
				org.openqa.selenium.interactions.Sequence scroll = new org.openqa.selenium.interactions.Sequence(finger, 1);
				scroll.addAction(finger.createPointerMove(Duration.ZERO, org.openqa.selenium.interactions.PointerInput.Origin.viewport(), startX, startY));
				scroll.addAction(finger.createPointerDown(org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT.asArg()));
				scroll.addAction(finger.createPointerMove(Duration.ofMillis(600), org.openqa.selenium.interactions.PointerInput.Origin.viewport(), endX, endY));
				scroll.addAction(finger.createPointerUp(org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT.asArg()));
				driver.perform(List.of(scroll));
			} catch (Exception e) {
				logger.warn("Scroll gesture failed: " + e.getMessage());
			}
			maxScroll--;
			try {
				Thread.sleep(500); // Allow UI to settle
			} catch (InterruptedException ignored) {}
		}

		if (!isElementDisplayedQuick(locator)) {
			logger.warn("Element " + locator + " is still not visible after scrolling.");
		}
	}

	private boolean isElementDisplayedQuick(By locator) {
		try {
			List<WebElement> elements = driver.findElements(locator);
			return !elements.isEmpty() && elements.get(0).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void scrollAndClick(By locator) {
		scrollUntilVisible(locator);
		clickOn(locator);
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

	

	public String getElementVisibleText(By locator) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		String text = element.getText();
		if (text == null || text.trim().isEmpty()) {
			text = element.getAttribute("text");
		}
		if (text == null || text.trim().isEmpty()) {
			text = element.getAttribute("content-desc");
		}
		return text != null ? text.trim() : "";
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
	
	public void checkLinksAreWork() {
		String originalContext = getDriver().getContext();
		logger.info("Current Appium Context: " + originalContext);
		
		// 1. If currently in NATIVE_APP, try to switch to WebView to access HTML elements
		if (originalContext.equals("NATIVE_APP")) {
			Set<String> contexts = getDriver().getContextHandles();
			logger.info("Available contexts on the page: " + contexts);
			for (String context : contexts) {
				if (context.contains("WEBVIEW")) {
					logger.info("Switching to WEBVIEW context: " + context);
					getDriver().context(context);
					break;
				}
			}
		}
		
		String currentContext = getDriver().getContext();
		
		// 2. Fetch/validate URLs based on the current context
		if (currentContext.contains("WEBVIEW")) {
			Set<String> urlsToValidate = new LinkedHashSet<>();
			// WebView Context: Find all 'a' tag hyperlink elements
			List<WebElement> links = getDriver().findElements(By.tagName("a"));
			logger.info("Webview: Total links ('a' tags) found: " + links.size());
			
			String pageUrl = "";
			try {
				pageUrl = getDriver().getCurrentUrl();
			} catch (Exception e) {
				logger.warn("Could not get current WebView page URL: " + e.getMessage());
			}

			for (WebElement link : links) {
				String url = link.getAttribute("href");
				String linkText = link.getText();
				
				if (url == null || url.trim().isEmpty()) {
					logger.error("Link is NOT linked (missing or empty href attribute) for element: " + linkText);
					Assert.fail("Link is not linked: '" + linkText + "' does not have a valid href attribute.");
					continue;
				}
				
				String urlTrimmed = url.trim();
				if (urlTrimmed.startsWith("javascript:") || urlTrimmed.equals("#") || urlTrimmed.equals("/") ||
					urlTrimmed.startsWith("mailto:") || urlTrimmed.startsWith("tel:")) {
					logger.info("Skipping non-http or placeholder link: " + url);
					continue;
				}
				
				// Resolve relative URLs
				if (!urlTrimmed.toLowerCase().startsWith("http://") && !urlTrimmed.toLowerCase().startsWith("https://") && !urlTrimmed.toLowerCase().startsWith("www.")) {
					if (!pageUrl.isEmpty()) {
						try {
							URL baseUrl = new URL(pageUrl);
							URL resolvedUrl = new URL(baseUrl, urlTrimmed);
							urlTrimmed = resolvedUrl.toString();
						} catch (Exception e) {
							// Keep original if resolution fails
						}
					}
				}
				
				urlsToValidate.add(urlTrimmed);
			}
			
			for (String url : urlsToValidate) {
				validateUrlConnection(url);
			}
		} else {
			// Native App Context: Click on known link elements to open Webview/browser
			logger.info("Native: Validating links by identifying and clicking clickable elements...");
			
			Map<String, By> linkMap = findClickableLinkLocators();
			logger.info("Found " + linkMap.size() + " potential link elements on page: " + linkMap.keySet());
			
			for (Map.Entry<String, By> entry : linkMap.entrySet()) {
				String linkName = entry.getKey();
				By locator = entry.getValue();
				try {
					scrollUntilVisible(locator);
					if (isElementDisplayedQuick(locator)) {
						logger.info("Found link element: " + linkName + ". Validating by clicking...");
						clickOn(locator);
						Thread.sleep(4000); // Allow page/browser to open and switch context if any
						
						// Check if a WebView context becomes available
						Set<String> contexts = getDriver().getContextHandles();
						logger.info("Available contexts after clicking " + linkName + ": " + contexts);
						
						boolean validated = false;
						for (String context : contexts) {
							if (context.contains("WEBVIEW")) {
								getDriver().context(context);
								
								// Switch to the active/latest window handle in Webview context to avoid target window closed errors
								try {
									Set<String> windowHandles = getDriver().getWindowHandles();
									String activeHandle = null;
									for (String handle : windowHandles) {
										activeHandle = handle;
									}
									if (activeHandle != null) {
										getDriver().switchTo().window(activeHandle);
									}
								} catch (Exception we) {
									logger.warn("Could not switch window handle for WebView context: " + we.getMessage());
								}

								String currentUrl = getDriver().getCurrentUrl();
								logger.info("WebView URL for " + linkName + ": " + currentUrl);
								
								// Validate the URL
								validateUrlConnection(currentUrl);
								validated = true;
								
								// Switch back to Native to perform back action
								getDriver().context("NATIVE_APP");
								break;
							}
						}
						
						if (!validated) {
							logger.info("No WebView found. Assumed opened in external browser/tab.");
						}
						
						// Navigate back to return to the app screen
						getDriver().navigate().back();
						Thread.sleep(2000); // Allow native app to load
					}
				} catch (Exception e) {
					logger.error("Failed to validate link " + linkName + ": " + e.getMessage());
					Assert.fail("Failed to validate link " + linkName + " | Exception: " + e.getMessage());
				}
			}
		}

		if (!getDriver().getContext().equals(originalContext)) {
			logger.info("Switching context back to: " + originalContext);
			getDriver().context(originalContext);
		}
	}

	private Map<String, By> findClickableLinkLocators() {
		Map<String, By> links = new java.util.LinkedHashMap<>();
		try {
			List<WebElement> elements = getDriver().findElements(By.xpath("//*[@clickable='true']"));
			for (WebElement el : elements) {
				try {
					String text = el.getText();
					String desc = el.getAttribute("content-desc");
					String resourceId = el.getAttribute("resource-id");
					
					String combined = ((text != null ? text : "") + " " + 
									   (desc != null ? desc : "") + " " + 
									   (resourceId != null ? resourceId : "")).toLowerCase();
					
					if (combined.contains("t&c") || combined.contains("terms") || combined.contains("privacy") || 
						combined.contains("policy") || combined.contains("agreement") || combined.contains("link") || 
						combined.contains("details") || combined.contains("hyperlink") || combined.contains("consent")) {
						
						String key = "";
						By locator = null;
						if (desc != null && !desc.trim().isEmpty()) {
							key = desc.trim();
							locator = AppiumBy.xpath("//*[@content-desc='" + key.replace("'", "\\'") + "']");
						} else if (text != null && !text.trim().isEmpty()) {
							key = text.trim();
							locator = AppiumBy.xpath("//*[@text='" + key.replace("'", "\\'") + "']");
						} else if (resourceId != null && !resourceId.trim().isEmpty()) {
							key = resourceId.substring(resourceId.lastIndexOf('/') + 1);
							locator = AppiumBy.id(resourceId);
						}
						
						if (!key.isEmpty() && locator != null) {
							links.put(key, locator);
						}
					}
				} catch (Exception ignored) {}
			}
		} catch (Exception e) {
			logger.warn("Could not dynamically scan clickable links: " + e.getMessage());
		}
		
		if (links.isEmpty()) {
			links.put("T&C", AppiumBy.xpath("//*[contains(@content-desc, 'T&C') or contains(@text, 'T&C')]"));
			links.put("Privacy Policy", AppiumBy.xpath("//*[contains(@content-desc, 'Privacy Policy') or contains(@text, 'Privacy Policy')]"));
			links.put("View Your MF Details", AppiumBy.xpath("//*[contains(@content-desc, 'View Your MF Details') or contains(@text, 'View Your MF Details')]"));
		}
		return links;
	}

	private void validateUrlConnection(String url) {
		String formattedUrl = url;
		if (!url.toLowerCase().startsWith("http://") && !url.toLowerCase().startsWith("https://")) {
			formattedUrl = "https://" + url;
		}
		logger.info("Validating link connection for URL: " + formattedUrl);
		
		javax.net.ssl.SSLContext sc = null;
		javax.net.ssl.HostnameVerifier allHostsValid = null;
		// Bypass SSL Certificate validation for self-signed or mismatched domains (e.g. staging or dev environments)
		try {
			javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[]{
				new javax.net.ssl.X509TrustManager() {
					public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
					public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
					public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
				}
			};

			sc = javax.net.ssl.SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			allHostsValid = new javax.net.ssl.HostnameVerifier() {
				public boolean verify(String hostname, javax.net.ssl.SSLSession session) { return true; }
			};
			javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		} catch (Exception e) {
			logger.warn("Could not setup trust-all SSL configuration: " + e.getMessage());
		}

		try {
			URL linkUrl = new URL(formattedUrl);
			HttpURLConnection connection = (HttpURLConnection) linkUrl.openConnection();
			
			// Direct assignment on HttpsURLConnection instance to guarantee bypass
			if (connection instanceof javax.net.ssl.HttpsURLConnection && sc != null && allHostsValid != null) {
				javax.net.ssl.HttpsURLConnection httpsConn = (javax.net.ssl.HttpsURLConnection) connection;
				httpsConn.setSSLSocketFactory(sc.getSocketFactory());
				httpsConn.setHostnameVerifier(allHostsValid);
			}
			
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			connection.setRequestMethod("HEAD");
			
			int responseCode = connection.getResponseCode();
			if (responseCode == 405) {
				connection = (HttpURLConnection) linkUrl.openConnection();
				if (connection instanceof javax.net.ssl.HttpsURLConnection && sc != null && allHostsValid != null) {
					javax.net.ssl.HttpsURLConnection httpsConn = (javax.net.ssl.HttpsURLConnection) connection;
					httpsConn.setSSLSocketFactory(sc.getSocketFactory());
					httpsConn.setHostnameVerifier(allHostsValid);
				}
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				connection.setRequestMethod("GET");
				responseCode = connection.getResponseCode();
			}
			
			if (responseCode < 300) {
				logger.info("Link is WORKING. Status code: " + responseCode + " for URL: " + formattedUrl);
			} else {
				logger.error("Link is BROKEN. Status code: " + responseCode + " for URL: " + formattedUrl);
				Assert.fail("Link is broken: '" + formattedUrl + "' returned response code " + responseCode + " (expected < 300).");
			}
		} catch (Exception e) {
			logger.error("Error connecting to link: " + formattedUrl + " | Exception: " + e.getMessage());
			Assert.fail("Failed to connect to link: '" + formattedUrl + "' | Exception: " + e.getMessage());
		}
	}
	
	private void scanNativeElementsForUrls(Set<String> urls) {
		try {
			List<WebElement> textElements = getDriver().findElements(By.xpath("//*[@text!='' or @content-desc!='']"));
			logger.info("Scanning " + textElements.size() + " native elements for URLs.");
			for (WebElement element : textElements) {
				try {
					String text = element.getText();
					String desc = element.getAttribute("content-desc");
					if ((text != null && !text.trim().isEmpty()) || (desc != null && !desc.trim().isEmpty())) {
						logger.info("Scanned element - text: '" + text + "', content-desc: '" + desc + "'");
					}
					if (text != null && !text.trim().isEmpty()) {
						urls.addAll(extractUrlsFromText(text));
					}
					if (desc != null && !desc.trim().isEmpty()) {
						urls.addAll(extractUrlsFromText(desc));
					}
				} catch (Exception ignored) {
				}
			}
		} catch (Exception e) {
			logger.warn("Error scanning elements: " + e.getMessage());
		}
	}
	
	private boolean isValidUrl(String url) {
		if (url == null || url.trim().isEmpty()) {
			return false;
		}
		String lower = url.toLowerCase();
		// Ignore common Android packages, classes and local files/methods
		if (lower.contains("android.widget") || 
			lower.contains("android.view") || 
			lower.contains("android.webkit") ||
			lower.contains("android.graphics") ||
			lower.contains("androidx.") || 
			lower.contains("com.valuenable") ||
			lower.contains("java.lang") ||
			lower.contains("layout") ||
			lower.contains("widget") ||
			lower.contains("frameLayout") ||
			lower.contains("linearLayout") ||
			lower.contains("relativeLayout") ||
			lower.contains("toast") ||
			lower.contains("appium") ||
			lower.contains("activity") ||
			lower.contains("package") ||
			lower.contains("xml")) {
			return false;
		}
		
		// If it's a plain domain (doesn't start with http/https/www), verify it has a valid TLD
		if (!lower.startsWith("http://") && !lower.startsWith("https://") && !lower.startsWith("www.")) {
			// Require common TLDs to avoid matching package/class paths like "a.b.c"
			if (!lower.matches(".*\\.(com|org|net|in|co|edu|gov|io|info|me|us|biz|dev|html|php|aspx|jsp)(\\/.*)?$")) {
				return false;
			}
		}

		try {
			String formattedUrl = lower.startsWith("http") ? url : "http://" + url;
			new URL(formattedUrl).toURI();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private List<String> extractUrlsFromText(String text) {
		List<String> urls = new ArrayList<>();
		if (text == null || text.trim().isEmpty()) {
			return urls;
		}

		// Pattern for URLs with scheme or www
		Pattern pattern = Pattern.compile("(?i)\\b((?:https?://|www\\.)[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])");
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			String candidate = matcher.group();
			if (isValidUrl(candidate)) {
				urls.add(candidate);
			}
		}

		// Additional plain domain pattern (e.g., example.com)
		Pattern plainPattern = Pattern.compile("(?i)\\b([a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})(/\\S*)?");
		Matcher plainMatcher = plainPattern.matcher(text);
		while (plainMatcher.find()) {
			String candidate = plainMatcher.group();
			if (isValidUrl(candidate)) {
				urls.add(candidate);
			}
		}

		return urls;
	}


	// =============================
	// Screenshot
	// =============================

	public String getScreenshotPath(String screenshotName) {

		String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new java.util.Date());
		String screenShotPath = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator
				+ screenshotName + "_" + timestamp + ".png";

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
	
	public void checkMandatoryMessage(By locator) {
		Assert.assertTrue(isElementDisplayed(locator),
				"MANDATORY CHECK FAILED: Error message was not displayed when mandatory fields were missing");
		logger.info("MANDATORY CHECK PASSED: Error message for missing mandatory fields was verified.");
	}

	// =============================
	// Driver Close
	// =============================

	public void quitDriver() {

		logger.info("Closing Application");

		if (driver != null) {
			try {
				// Switch back to Native App context first to ensure native Appium command works safely
				if (driver.getContext() != null && !driver.getContext().equals("NATIVE_APP")) {
					logger.info("Switching to NATIVE_APP before terminating application.");
					driver.context("NATIVE_APP");
				}
				String appPackage = ReadConfiguration.getConfig("PACKAGE");
				if (appPackage != null && !appPackage.isEmpty()) {
					logger.info("Terminating application: " + appPackage);
					driver.terminateApp(appPackage);
				}
			} catch (Exception e) {
				logger.warn("Could not natively terminate app: " + e.getMessage());
			}
			driver.quit();

			// Auto-reset mobile device keyboard to standard default via adb command line execution
			try {
				logger.info("Resetting device keyboard to system default via ADB command...");
				Runtime.getRuntime().exec("adb shell ime reset");
			} catch (Exception adbEx) {
				logger.warn("Could not execute keyboard reset command: " + adbEx.getMessage());
			}
		}
	}

	private String getApkPath() {
		File apkDir = new File(System.getProperty("user.dir") + File.separator + "APK");
		if (apkDir.exists() && apkDir.isDirectory()) {
			File[] files = apkDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".apk"));
			if (files != null && files.length > 0) {
				return files[0].getAbsolutePath();
			}
		}
		return null;
	}
}