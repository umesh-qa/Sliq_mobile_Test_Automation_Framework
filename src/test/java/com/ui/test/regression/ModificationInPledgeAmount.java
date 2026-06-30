package com.ui.test.regression;

import org.testng.annotations.Test;

import com.ui.pages.LenderSelectionPage;
import com.ui.pojo.MutualFundUserData;
import com.ui.test.smoke.BaseTest;

public class ModificationInPledgeAmount extends BaseTest{
	
	
	@Test(groups = {"regression"}, 
			description = "Verify user should able to edit pledgable funds amounds and unchecked funds from selected Lender. ", 
			dataProviderClass = com.ui.dataprovider.MutualFundUserDataProvider.class, 
			dataProvider = "MutualFundUser"
		)
	public void editAndUncheckedPledgeAmountTo(MutualFundUserData data) {
	
	// =============== Login Page Validation =====================//
//	homePage
//		.enterMobileNo(data.getMobileNo())
//		.clickOnSendOTPButton()
//		.enterOTP(data.getOtp())
//		.clickOnContinue()

// =============== Navigate To Mutual Fund and PAN card Validation =====================//
//	    .navigateToMutualFund()
//		.enterPanNo(data.getPanNo())
//		.enterPanName(data.getPanName())
//		.enterEmailId(data.getEmailID())
//		.enterDateOfBirth(data.getDOB())
//		.acceptTermsAndConditions()
//		.clickOnCheckLoanEligibility()

// =============== BFL journey: LENDER SELECTION AND LOAN AMOUNT EDIT =====================//
//		.enterOtp(data.getOtp())
//		.clickOnAuthenticateWithOTP()
//		.selectAllFunds()
//		.continuePortfolioImport()
//		.checkLoanOffer()
//		.selectBajajFinanceLender()
	    LenderSelectionPage lenderSelectionPage = new LenderSelectionPage(homePage.getDriver());
		lenderSelectionPage
		.clickOnEditLoanAmount("100000")
		.selectBajaj()
		.editFundsValue("1900")
		.unCheckHDFCMidCapFund();

	}
}
