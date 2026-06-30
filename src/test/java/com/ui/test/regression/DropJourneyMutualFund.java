package com.ui.test.regression;

import org.testng.annotations.Test;
import com.ui.pages.HomePage;
import com.ui.pages.LenderSelectionPage;
import com.ui.pages.MutualFundCentralPage;
import com.ui.pages.PanVerificationPage;
import com.ui.pages.BFLKycVerificationPage;
import com.ui.pojo.MutualFundUserData;
import com.ui.test.smoke.BaseTest;

/**
 * Regression test for verifying the drop/resume journey functionality.
 */
public class DropJourneyMutualFund extends BaseTest {

	@Test(
		groups = {"regression"},
		description = "Verify user is able to resume Mutual Fund Journey after dropping at each step.", 
		dataProviderClass = com.ui.dataprovider.MutualFundUserDataProvider.class, 
		dataProvider = "MutualFundUser",
		retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class
	)
	public void dropJourneyMutualFundFlow(MutualFundUserData data) {

		// 1. Initial Login and Start Journey
		homePage
			.enterMobileNo(data.getMobileNo())
			.clickOnSendOTPButton()
			.enterOTP(data.getOtp())
			.clickOnContinue()
			.navigateToMutualFund();
		
		// 2. Fill PAN details and complete it, then drop journey
		PanVerificationPage panVerificationPage = new PanVerificationPage(getInstance().getDriver());
		panVerificationPage
			.enterPanNo(data.getPanNo())
			.enterPanName(data.getPanName())
			.enterEmailId(data.getEmailID())
			.enterDateOfBirth(data.getDOB())
			.acceptTermsAndConditions()
			.clickOnCheckLoanEligibility()
			.enterOtp("123123")
			.clickOnAuthenticateWithOTP()
			.selectAllFunds()
			.continuePortfolioImport()
			.checkLoanOffer();
//			.continuePortfolioImport()
			// Drop 1: Restart App, land on Dashboard, and click Continue Application
			homePage.closeAndOpenApp();
			new HomePage(getInstance().getDriver()).resumeDroppedJourney();
			MutualFundCentralPage BFLPage= new MutualFundCentralPage(homePage.getDriver());	
			BFLPage
			.isSeeLoanOfferDisplay()
	        .checkLoanOffer()
			.selectBajajFinanceLender()
			.clickOnEditLoanAmount(data.getLoanAmount())
			.continueWithBajaj();
			
		// Drop 2: Restart App, land on Dashboard, and click Continue Application
			homePage.closeAndOpenApp();
			new HomePage(getInstance().getDriver()).resumeDroppedJourney();

		// 4. Fill KYC Verification and finish
		BFLKycVerificationPage bflKycVerificationPage = new BFLKycVerificationPage(getInstance().getDriver());
		bflKycVerificationPage
		    .isKYCPageDisplay()
			.verifyEmail() 
			.enterOtp()
			.enterPersonalDetails(data.getGender(), data.getFatherName(), data.getMotherName(), data.getMaritalStatus())
			.enterFinancialDetails(data.getAnnualIncome(), data.getOccupation(), data.getNatureOfBusiness())
			.clickOnSubmitButton();
//			.acceptTermsConditions();
	}
}
