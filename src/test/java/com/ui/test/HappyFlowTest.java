package com.ui.test;

import org.testng.annotations.Test;

import com.ui.pages.KycVerificationPage;
import com.ui.pojo.UserData;
import com.ui.utility.OTPUtility;

public class HappyFlowTest extends BaseTest { 

	@Test(dataProviderClass = com.ui.dataprovider.UserDataProvider.class, dataProvider = "userData")
	public void demoTest(UserData data) {
		
		homePage
//		.goTologinPage()
		.enterMobileNo(data.getMobileNo())
 		.clickOnSendOTPButton()
		.enterOTP(data.getOtp()) 
	    .clickOnContinue()
		.navigateToMutualFound()
		.enterPanNo(data.getPanNo())
		.enterPanName(data.getPanName())
		.enterEmailId(data.getEmailID())
		.enterDateOfBirth(data.getDOB())
		.AcceptTermsAndConditions()
		.clickOnCheckLoanEligibility()
		.enterOtp("123456")
		.clickOnAuthenticateWithOTP()
		.selectAllFound()
		.continuePortfolioImport()
		.checkLoanOffer()
		.selectBajajFinalceLender()
		.clickOnEditLoanAmount("3000000")
		.continueWithBajaj();
		
		try {
			KycVerificationPage kycVerification = new KycVerificationPage(homePage.getDriver());
			String otp=OTPUtility.waitForOtp(homePage.getDriver(), 30);
			kycVerification.enterOtp(otp);
		}catch(Exception e) {
			System.out.println(e.toString());
		}

		
	}

}
