package com.ui.test.smoke;

import org.testng.annotations.Test;

import com.ui.pages.BFLKycVerificationPage;
import com.ui.pojo.MutualFundUserData;

/**
 * Smoke tests for the Mutual Fund loan application journey.
 */
public class MutualFund extends BaseTest {

	@Test(groups = {"smoke"}, 
		description = "Verify user is able to apply and complete Mutual Fund Journey for Loan.", 
		dataProviderClass = com.ui.dataprovider.MutualFundUserDataProvider.class, 
		dataProvider = "MutualFundUser",
		retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class
	)
	public void mutualFundHappyJourney(MutualFundUserData data) {

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

		// =============== BFL journey: LENDER SELECTION AND LOAN AMOUNT EDIT =====================//
				.enterOtp(data.getOtp())
				.clickOnAuthenticateWithOTP()
				.selectAllFunds()
				.continuePortfolioImport()
				.checkLoanOffer()
				.selectBajajFinanceLender()
				.clickOnEditLoanAmount(data.getLoanAmount())
				.continueWithBajaj()

		// =============== BFL journey: EMAIL VERIFICATION AND INPUT USER DATA ===================== //
			  .verifyEmail() 
			  .enterOtp()
			  .enterPersonalDetails(data.getGender(), data.getFatherName(), data.getMotherName(), data.getMaritalStatus())
			  .enterFinancialDetails(data.getAnnualIncome(), data.getOccupation(), data.getNatureOfBusiness())
			  .clickOnSubmitButton()
			  .acceptTermsConditions();
     
		// =============== BFL journey: DIGI LOCKER ===================== //
		// skipped as per business rules

		// =============== Pledge Fund: confirm with OTP ===================== //
//			.enterOTPForConfirmPledgeFund(data.getOtp())
//			.setDisbursalLimitAndConfirm(data.getLoanAmount());
	}
}
