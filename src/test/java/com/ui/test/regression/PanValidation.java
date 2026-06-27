package com.ui.test.regression;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.pojo.MutualFundUserData;
import com.ui.test.smoke.BaseTest;

/**
 * Regression test verifying that PAN entry validates format patterns correctly.
 */
public class PanValidation extends BaseTest {
	
	@Test(
		description = "Verify error message when user enters PAN in invalid format.", 
		dataProviderClass = com.ui.dataprovider.MutualFundUserDataProvider.class, 
		dataProvider = "MutualFundUser"
	)
	public void mfPanInvalidFormatCheck(MutualFundUserData data) {
		
		// =============== Login Page Validation =====================//
		String actualMessage = homePage
				.enterMobileNo("9999999965")
				.clickOnSendOTPButton()
				.enterOTP(data.getOtp())
				.clickOnContinue()

		// =============== Navigate To Mutual Fund and PAN card Validation =====================//
			    .navigateToMutualFund()
			    .enterPanNo(data.getPanNo())
			    .getInvalidPanFormatErrorMessage();

		Assert.assertEquals(actualMessage, "Invalid PAN format", 
				"PAN Field Validation Failed: System accepted invalid PAN format.");
	}
}
