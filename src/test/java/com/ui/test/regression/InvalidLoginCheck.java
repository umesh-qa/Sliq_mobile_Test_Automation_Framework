package com.ui.test.regression;

import org.testng.annotations.Test;
import com.ui.test.smoke.BaseTest;

/**
 * Regression test verifying that the application rejects invalid OTP login attempts.
 */
public class InvalidLoginCheck extends BaseTest {
	
	private static final String MOBILE_NO = "9999999960";
	private static final String INVALID_OTP = "100002";
	
	@Test(description = "Verify user is not able to login with invalid credentials.")
	public void invalidLoginCredentialCheck() {
		
		homePage
				.enterMobileNo(MOBILE_NO)
				.clickOnSendOTPButton()
				.enterOTP(INVALID_OTP)
				.clickOnContinueAndGetInvalidResponse();
	}
}
