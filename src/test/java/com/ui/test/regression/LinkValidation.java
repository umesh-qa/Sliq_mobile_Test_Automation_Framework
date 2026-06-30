package com.ui.test.regression;

import org.testng.annotations.Test;

import com.ui.pojo.MutualFundUserData;
import com.ui.test.smoke.BaseTest;

/**
 * Regression test verifying that hyperlinks (e.g. Terms & Conditions) are active and functional.
 */
public class LinkValidation extends BaseTest {

	@Test(groups = {"regression"}, 
		description = "Verify T&C and Privacy Policy hyperlinks are linked and working.", 
		dataProviderClass = com.ui.dataprovider.MutualFundUserDataProvider.class, 
		dataProvider = "MutualFundUser"
	)
	public void checkLinksOnPage(MutualFundUserData data) {
		
		// =============== Login Page Validation =====================//
		homePage
				.enterMobileNo("9999999965")
				.clickOnSendOTPButton()
				.enterOTP(data.getOtp())
				.clickOnContinue()
		
		// =============== Navigate To Mutual Fund and PAN card Validation =====================//
			    .navigateToMutualFund()
			    .checkLinksAreWork();
	}
}
