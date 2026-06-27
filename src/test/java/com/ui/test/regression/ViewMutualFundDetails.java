package com.ui.test.regression;

import org.testng.annotations.Test;
import com.ui.pojo.MutualFundUserData;
import com.ui.test.smoke.BaseTest;

/**
 * Regression test suite verifying Mutual Fund detail breakdown visibility.
 */
public class ViewMutualFundDetails extends BaseTest {

	@Test(
		description = "Verify user is able to view Mutual Fund details.",
		dataProviderClass = com.ui.dataprovider.MutualFundUserDataProvider.class, 
		dataProvider = "MutualFundUser"
	)
	public void viewMutualFundDetails(MutualFundUserData data) {

		// =============== Login Page Validation =====================//
		homePage
				.enterMobileNo(data.getMobileNo())
				.clickOnSendOTPButton()
				.enterOTP(data.getOtp())
				.clickOnContinue()
	
		// =============== Navigate To Mutual Fund and PAN card Validation =====================//
			    .navigateToMutualFund()
				.enterPanNo(data.getPanNo())
				.enterPanName(data.getPanName())
				.enterEmailId(data.getEmailID())
				.enterDateOfBirth(data.getDOB())
				.acceptTermsAndConditions()
				.clickOnCheckLoanEligibility()

		// =============== BFL journey: LENDER SELECTION AND LOAN DETAILS =====================//
				.enterOtp(data.getOtp())
				.clickOnAuthenticateWithOTP()
				.selectAllFunds()
				.continuePortfolioImport()
				.checkLoanOffer()
				.clickOnMFDetails()
				.checkPledgeableFundsContent()
				.checkNonPledgeableFundsContent();
	}
}
