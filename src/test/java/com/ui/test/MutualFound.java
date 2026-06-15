package com.ui.test;

import org.testng.annotations.Test;

import com.ui.pages.BFLKycVerificationPage;
import com.ui.pages.LenderSelectionPage;
import com.ui.pojo.UserData;

public class MutualFound extends BaseTest {
	
//	private KycVerificationPage kycVerification;
//
//	
//	@BeforeTest
//	public void setUp() {
//		kycVerification	 = new KycVerificationPage(homePage.getDriver());
//		
//	}

	@Test(dataProviderClass = com.ui.dataprovider.UserDataProvider.class, dataProvider = "userData")
	public void MutualFoundLoginCheck(UserData data) {
		

// =============== Login Page Validation =====================//
		
//		homePage
//		.goTologinPage()
//		.enterMobileNo(data.getMobileNo())
// 		.clickOnSendOTPButton()
//		.enterOTP(data.getOtp())
//	    .clickOnContinue()
//	
//// =============== Navigate To Mutual Found and Pan card Validation =====================//
////	
//	    .navigateToMutualFound()
//		.enterPanNo(data.getPanNo())
//		.enterPanName(data.getPanName())
//		.enterEmailId(data.getEmailID())
//		.enterDateOfBirth(data.getDOB())
//		.AcceptTermsAndConditions()
//		.clickOnCheckLoanEligibility()


// =============== BFL journey: LENDER SELECTION AND lOAN AMOUNT EDIT =====================//


//		.enterOtp(data.getOtp())
//		.clickOnAuthenticateWithOTP()
//		.selectAllFound()
//		.continuePortfolioImport()
//		.checkLoanOffer()
//		.selectBajajFinalceLender()
//		.clickOnEditLoanAmount("3000000")
//		.continueWithBajaj();
		
// =============== BFL journey: EMAIL VERIFICATION AND INPUT USER DATA ===================== //

	      try{
	    	  BFLKycVerificationPage page= new BFLKycVerificationPage(homePage.getDriver());
	    	  page.VerifyEmail()
			  .enterOtp()
					.enterPersonalDetails("Male", "Balu","monda","Single")
					.enterFinancialDetails("Upto Rs. 5 Lakhs", "Salaried","Information Technology")
					.acceptTermsConditions();
	      }catch(Exception e) {
	    	 System.out.println(e.toString());
	      } 

	}
	
}
