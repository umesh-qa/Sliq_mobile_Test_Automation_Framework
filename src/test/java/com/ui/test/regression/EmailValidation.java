package com.ui.test.regression;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.pojo.MutualFundUserData;
import com.ui.test.smoke.BaseTest;

/**
 * Regression test verifying that the Email input field validates for correct formats.
 */
public class EmailValidation extends BaseTest {
	
	private static final String INVALID_EMAIL_ID="umeshkute";
	
	@Test(groups = {"regression"}, 
		description = "Verify error message when user enters email in invalid format in pan verification.", 
		dataProviderClass = com.ui.dataprovider.MutualFundUserDataProvider.class, 
		dataProvider = "MutualFundUser"
	)
	public void checkEmailFieldValidation(MutualFundUserData data) {
		
		// =============== Login Page Validation =====================//
		String actualMessage = homePage
				.enterMobileNo(data.getMobileNo())
				.clickOnSendOTPButton()
				.enterOTP(data.getOtp())
				.clickOnContinue()

		// =============== Navigate To Mutual Fund and PAN card Validation =====================//
			    .navigateToMutualFund()
			    .enterPanNo(data.getPanNo())
			    .enterPanName(data.getPanName())
			    .enterEmailId(INVALID_EMAIL_ID)
			    .getInvalidEmailFormatErrorMessage();

		Assert.assertEquals(actualMessage, "Enter a valid email address", 
				"Email Field Validation Failed: System accepted invalid email format.");
	}
}
