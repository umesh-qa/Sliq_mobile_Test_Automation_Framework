package com.ui.test.smoke;

import org.testng.annotations.Test;
import com.ui.pojo.SharesUserData;

/**
 * Smoke tests for the Shares loan application journey.
 */
public class Shares extends BaseTest {

	@Test(
		description = "Verify user is able to apply and complete Shares Journey for Loan.", 
		dataProviderClass = com.ui.dataprovider.SharesUserDataProvider.class, 
		dataProvider = "SharesUser"
	)
	public void sharesHappyJourney(SharesUserData data) {

		// =============== Login Page Validation =====================//
		homePage
				.enterMobileNo(data.getMobileNo())
				.clickOnSendOTPButton()
				.enterOTP(data.getOtp())
				.clickOnContinue()		

		// =============== Navigate To Shares and PAN card Validation =====================//
			    .navigateToShares()
			    .enterPanNo(data.getPanNo())
				.enterPanName(data.getPanName())
				.enterSharesDOB(data.getDOB())
				.enterSharesEmail(data.getEmailID())
				.enterDpID(data.getDPID())
				.enterClientID(data.getClientID())
				.sharesAcceptTermsAndConditions()
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
				.selectBajajFinanceLender()
				.clickOnEditLoanAmount(data.getLoanAmount())
				.continueWithBajaj()

		// =============== BFL journey: EMAIL VERIFICATION AND INPUT USER DATA ===================== //
	    	  .verifyEmail() 
			  .enterOtp()
			  .enterPersonalDetails(data.getGender(), data.getFatherName(), data.getMotherName(), data.getMaritalStatus())
			  .enterFinancialDetails(data.getAnnualIncome(), data.getOccupation(), data.getNatureOfBusiness())
			  .acceptTermsConditions();
	}
}
