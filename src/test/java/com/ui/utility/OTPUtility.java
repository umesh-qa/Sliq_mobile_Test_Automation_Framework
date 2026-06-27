package com.ui.utility;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

/**
 * Utility helper to handle OTP scanning from system notifications.
 */
public class OTPUtility {

	private static final Logger logger = LoggerUtility.getLogger(OTPUtility.class);

	private OTPUtility() {
		// Private constructor to prevent instantiation
	}

	/**
	 * Scans the Android notification shade to intercept and return a 6-digit OTP code.
	 * 
	 * @param driver active AndroidDriver instance
	 * @param timeoutSeconds duration to search before timing out
	 * @return 6-digit OTP string if retrieved
	 * @throws RuntimeException if the OTP is not received within the specified timeout
	 */
	public static String waitForOtp(AndroidDriver driver, int timeoutSeconds) {
		long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000L);
		Pattern pattern = Pattern.compile("\\b\\d{6}\\b");

		logger.info("Waiting up to " + timeoutSeconds + " seconds to capture OTP from notifications...");

		while (System.currentTimeMillis() < endTime) {
			driver.openNotifications();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				logger.warn("OTP wait sleep was interrupted: " + e.getMessage());
			}

			List<WebElement> textViews = driver.findElements(AppiumBy.className("android.widget.TextView"));

			for (WebElement element : textViews) {
				String text = element.getText();
				if (text != null && text.contains("OTP")) {
					Matcher matcher = pattern.matcher(text);
					if (matcher.find()) {
						String otp = matcher.group();
						logger.info("Successfully intercepted OTP code: " + otp);
						driver.navigate().back();
						return otp;
					}
				}
			}

			driver.navigate().back();

			try {
				Thread.sleep(3000); // wait before next check
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				logger.warn("OTP loop interval sleep interrupted: " + e.getMessage());
			}
		}

		logger.error("Failed to retrieve OTP from notifications within " + timeoutSeconds + " seconds.");
		throw new RuntimeException("OTP not received within " + timeoutSeconds + " seconds");
	}
}
