package com.ui.test;

import org.testng.annotations.Test;

import com.ui.pages.BFLKycVerificationPage;
import com.ui.pages.PledgeFoundPage;
import com.ui.pojo.MutualFoundUserData;

public class MutualFound extends BaseTest {

	@Test(description = "Verify user able to Apply and complete Mutula Found Journy for Loan. J", dataProviderClass = com.ui.dataprovider.MutualFoundUserDataProvider.class, dataProvider = "MutualFoundUser")
	public void MutualFoundLoginCheck(MutualFoundUserData data) {

// =============== Login Page Validation =====================//

		homePage
//		.goTologinPage()
				.enterMobileNo(data.getMobileNo())
				.clickOnSendOTPButton()
				.enterOTP(data.getOtp())
				.clickOnContinue()
//	
// =============== Navigate To Mutual Found and Pan card Validation =====================//

	    .navigateToMutualFound()
		.enterPanNo(data.getPanNo())
		.enterPanName(data.getPanName())
		.enterEmailId(data.getEmailID())
		.enterDateOfBirth(data.getDOB())
		.AcceptTermsAndConditions()
		.clickOnCheckLoanEligibility()

// =============== BFL journey: LENDER SELECTION AND lOAN AMOUNT EDIT =====================//

		.enterOtp(data.getOtp())
		.clickOnAuthenticateWithOTP()
		.selectAllFound()
		.continuePortfolioImport()
		.checkLoanOffer()
		.selectBajajFinalceLender()
		.clickOnEditLoanAmount(data.getLoanAmount())
		.continueWithBajaj()

// =============== BFL journey: EMAIL VERIFICATION AND INPUT USER DATA ===================== //

	    	  .VerifyEmail() 
			  .enterOtp()
			  .enterPersonalDetails(data.getGender(), data.getFatherName(),data.getMotherName(),data.getMaritalStatus())
			  .enterFinancialDetails(data.getAnnualIncome(), data.getOccupation(),data.getNatureOfBusiness())
			  .acceptTermsConditions()
     
// =============== BFL journey: DIGI LOCKER  ===================== //

		// skipped

// =============== Pledge found: confirm with otp===================== //

			.enterOTPForConfirmPledgeFound(data.getOtp())
			.setDisbursalLimitAndConfirm(data.getLoanAmount());

	}

}
