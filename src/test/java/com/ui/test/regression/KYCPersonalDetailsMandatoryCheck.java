package com.ui.test.regression;

import org.testng.annotations.Test;

import com.ui.pojo.MutualFundUserData;
import com.ui.test.smoke.BaseTest;

/**
 * Regression test suite verifying KYC form inputs validation behavior (missing mandatory personal/financial details).
 */
public class KYCPersonalDetailsMandatoryCheck extends BaseTest {

	@Test(groups = {"regression"}, 
		description = "Verify user is not able to proceed further if all mandatory information is missing in Personal and Financial Details for KYC Verification.", 
		dataProviderClass = com.ui.dataprovider.MutualFundUserDataProvider.class, 
		dataProvider = "MutualFundUser"
	)
	public void validateAllKYCMandatoryCheck(MutualFundUserData data) {

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
			  .clickOnNext()
			  .acceptTerms()
			  .clickOnSubmitButton()
			  .checkKYCMandatoryMessage();
	}
	
	@Test(groups = {"regression"}, 
		description = "Verify user is not able to proceed further if mandatory information is missing in Personal Details for KYC Verification.", 
		dataProviderClass = com.ui.dataprovider.MutualFundUserDataProvider.class, 
		dataProvider = "MutualFundUser"
	)
	public void validateKYCPersonalMandatoryCheck(MutualFundUserData data) {

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
			  .clickOnNext()
			  .enterFinancialDetails(data.getAnnualIncome(), data.getOccupation(), data.getNatureOfBusiness())
			  .clickOnSubmitButton()
			  .checkKYCMandatoryMessage();
	}
	
	@Test(groups = {"regression"}, 
		description = "Verify user is not able to proceed further if mandatory information is missing in financial Details for KYC Verification.", 
		dataProviderClass = com.ui.dataprovider.MutualFundUserDataProvider.class, 
		dataProvider = "MutualFundUser"
	)
	public void validateKYCFinancialMandatoryCheck(MutualFundUserData data) {

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
		.acceptTerms()
		.clickOnSubmitButton()
		.checkPersonalMandatoryMessage();
	}
}
