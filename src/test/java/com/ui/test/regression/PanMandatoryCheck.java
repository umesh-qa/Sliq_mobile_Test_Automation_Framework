package com.ui.test.regression;

import org.testng.annotations.Test;

import com.ui.pojo.MutualFundUserData;
import com.ui.test.smoke.BaseTest;

/**
 * Regression test suite verifying PAN form input validation behavior.
 */
public class PanMandatoryCheck extends BaseTest {

	@Test(groups = {"regression"}, 
		description = "Verify error message when all mandatory fields are left blank by user to check loan eligibility.", 
		dataProviderClass = com.ui.dataprovider.MutualFundUserDataProvider.class, 
		dataProvider = "MutualFundUser"
	)
	public void mfPanAllFieldsMandatoryCheck(MutualFundUserData data) {
		
		// =============== Login Page Validation =====================//
		homePage
				.enterMobileNo("9999999965")
				.clickOnSendOTPButton()
				.enterOTP(data.getOtp())
				.clickOnContinue()

		// =============== Navigate To Mutual Fund and PAN card Validation =====================//
			    .navigateToMutualFund()
			    .acceptTermsAndConditions()
			    .checkLoanEligibility()
			    .checkAllMandatoryMessage(); 
	}
	
	@Test(groups = {"regression"}, 
		description = "Verify error message when PAN card number mandatory fields are left blank by user to check loan eligibility.", 
		dataProviderClass = com.ui.dataprovider.MutualFundUserDataProvider.class, 
		dataProvider = "MutualFundUser"
	)
	public void mfPanNoMandatoryCheck(MutualFundUserData data) {
		
		// =============== Login Page Validation =====================//
		homePage
				.enterMobileNo("9999999965")
				.clickOnSendOTPButton()
				.enterOTP(data.getOtp())
				.clickOnContinue()

		// =============== Navigate To Mutual Fund and PAN card Validation =====================//
			    .navigateToMutualFund()
			    .enterPanNo("")
			    .enterPanName(data.getPanName())
				.enterEmailId(data.getEmailID())
				.enterDateOfBirth(data.getDOB())
				.acceptTermsAndConditions()
				.checkLoanEligibility()
				.checkPanNoMandatoryMessage();
	}
}
