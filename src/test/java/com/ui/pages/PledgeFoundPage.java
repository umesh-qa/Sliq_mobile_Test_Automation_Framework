package com.ui.pages;

import io.appium.java_client.android.AndroidDriver;

/**
 * @deprecated Use {@link PledgeFundPage} instead.
 * This class is maintained for legacy reference and will be removed in future clean-ups.
 */
@Deprecated
public class PledgeFoundPage extends PledgeFundPage {

	public PledgeFoundPage(AndroidDriver driver) {
		super(driver);
	}

	@Deprecated
	public PledgeFoundPage enterOTPForConfirmPledgeFound(String otp) {
		enterOTPForConfirmPledgeFund(otp);
		return this;
	}
}
