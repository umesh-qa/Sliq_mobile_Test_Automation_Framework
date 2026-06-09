package com.ui.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ui.utility.MobileUtility;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class KycVerificationPage extends MobileUtility {

	public KycVerificationPage(AndroidDriver driver) {
		super(driver);
	}

	private static final By VERIFY_BUTTON_LOCATOR = AppiumBy.xpath("//android.view.View[@content-desc='Link account']");
	private static final By OTP_TEXTBOX_LOCATOR=AppiumBy.xpath("//android.webkit.WebView[@text='Login']/android.widget.EditText[2]");

		public KycVerificationPage enterOtp(String otp) {

		    switchToWebView();

		    List<WebElement> otpBoxes =
		            getDriver().findElements(
		                    By.className("android.widget.EditText"));

		    System.out.println(
		            "OTP BOX COUNT : "
		            + otpBoxes.size());

		    if (otpBoxes.size() < 6) {

		        throw new RuntimeException(
		                "Expected 6 OTP boxes but found "
		                + otpBoxes.size());
		    }

		    for (int i = 0; i < otp.length(); i++) {

		        otpBoxes.get(i).click();

		        otpBoxes.get(i).sendKeys(
		                String.valueOf(
		                        otp.charAt(i)));
		    }

		    return this;
		}
	}


