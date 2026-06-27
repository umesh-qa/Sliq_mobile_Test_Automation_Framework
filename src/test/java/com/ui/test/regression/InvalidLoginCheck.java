package com.ui.test.regression;

import org.testng.annotations.Test;
import com.ui.test.smoke.BaseTest;

/**
 * Regression test verifying that the application rejects invalid OTP login attempts.
 */
public class InvalidLoginCheck extends BaseTest {
	
	private final String mobileNo = "9999999969";
	private final String invalidOTP = "100002";
	
	@Test(description = "Verify user is not able to login with invalid credentials.")
	public void invalidLoginCredentialCheck() {
		
		homePage
				.enterMobileNo(mobileNo)
				.clickOnSendOTPButton()
				.enterOTP(invalidOTP)
				.clickOnContinueAndGetInvalidResponse();
	}
}
