package com.ui.test;

import org.testng.annotations.Test;

import com.ui.pages.BFLKycVerificationPage;
import com.ui.pojo.SharesUserData;
import com.ui.pojo.UserData;

public class Shares extends BaseTest {

	@Test(description = "Verify user able to Apply and complete Shares Journy for Loan.", 
		  dataProviderClass = com.ui.dataprovider.SharesUserDataProvider.class, dataProvider = "SharesUser")
	public void SharesHappyJourny(SharesUserData data) {

		// =============== Login Page Validation =====================//

		homePage
//		.goTologinPage()
		.enterMobileNo(data.getMobileNo())
 		.clickOnSendOTPButton()
		.enterOTP(data.getOtp())
	    .clickOnContinue()		

// =============== Navigate To Shares and Pan card Validation =====================//

	    .navigateToShares()
	    .enterPanNo(data.getPanNo())
		.enterPanName(data.getPanName())
		.enterSharesDOB(data.getDOB())
		.enterSharesEmail(data.getEmailID())
		.enterDpID(data.getDPID())
		.enterClientID(data.getClientID())
		.SharesAcceptTermsAndConditions()
		.clickOnContinueButton()
		

// =============== ADD SHARES TO PORTFOLIO =====================//	
	
		.addHoldingsManually()
		.addSecurity(data.getSecurityName())
		.selectShare(data.getSharesName())
		.enterSharesQTY(data.getQTY())
		.addToPortfolio()
		.addMoreShare()
		.addSecurity("jio")
		.selectShare("Jio Financial Services Limited")
		.enterSharesQTY(data.getQTY())
		.addToPortfolio()
		.clickOnUpdatePortfolio()
		.checkLoanOffer()
		.selectBajajFinalceLender()
		.clickOnEditLoanAmount(data.getLoanAmount())
		.continueWithBajaj()

// =============== BFL journey: EMAIL VERIFICATION AND INPUT USER DATA ===================== //

	
	    	  .VerifyEmail() 
			  .enterOtp()
			  .enterPersonalDetails(data.getGender(), data.getFatherName(),data.getMotherName(),data.getMaritalStatus())
			  .enterFinancialDetails(data.getAnnualIncome(), data.getOccupation(),data.getNatureOfBusiness())
			  .acceptTermsConditions();

	}

}
