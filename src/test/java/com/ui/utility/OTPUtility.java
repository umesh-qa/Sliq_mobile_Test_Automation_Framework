package com.ui.utility;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public abstract class OTPUtility extends MobileUtility {

	public static String waitForOtp(AndroidDriver driver, int timeoutSeconds) {

		long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000L);

		Pattern pattern = Pattern.compile("\\b\\d{6}\\b");

		while (System.currentTimeMillis() < endTime) {

			driver.openNotifications();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			List<WebElement> textViews = driver.findElements(AppiumBy.className("android.widget.TextView"));

			for (WebElement element : textViews) {

				String text = element.getText();

				System.out.println("Notification Text : " + text);

				if (text.contains("OTP")) {

					Matcher matcher = pattern.matcher(text);

					if (matcher.find()) {

						String otp = matcher.group();

						System.out.println("OTP Found : " + otp);

						driver.navigate().back();

						return otp;
					}
				}
			}

			driver.navigate().back();

			try {
				Thread.sleep(3000); // wait before next check
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		throw new RuntimeException("OTP not received within " + timeoutSeconds + " seconds");
	}
}
