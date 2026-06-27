package com.ui.test.regression;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.pojo.MutualFundUserData;
import com.ui.test.smoke.BaseTest;

/**
 * Regression test verifying that the Email input field validates for correct formats.
 */
public class EmailValidation extends BaseTest {
	
	@Test(
		description = "Verify error message when user enters email in invalid format.", 
		dataProviderClass = com.ui.dataprovider.MutualFundUserDataProvider.class, 
		dataProvider = "MutualFundUser"
	)
	public void checkEmailFieldValidation(MutualFundUserData data) {
		
		// =============== Login Page Validation =====================//
		String actualMessage = homePage
				.enterMobileNo("9999999965")
				.clickOnSendOTPButton()
				.enterOTP(data.getOtp())
				.clickOnContinue()

		// =============== Navigate To Mutual Fund and PAN card Validation =====================//
			    .navigateToMutualFund()
			    .enterPanNo(data.getPanNo())
			    .enterPanName(data.getPanName())
			    .enterEmailId("umeshkute")
			    .getInvalidEmailFormatErrorMessage();

		Assert.assertEquals(actualMessage, "Enter a valid email address", 
				"Email Field Validation Failed: System accepted invalid email format.");
	}
}
