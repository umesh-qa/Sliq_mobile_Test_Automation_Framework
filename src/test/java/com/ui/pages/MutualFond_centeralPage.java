package com.ui.pages;

import io.appium.java_client.android.AndroidDriver;

/**
 * @deprecated Use {@link MutualFundCentralPage} instead.
 * This class is maintained for legacy reference and will be removed in future clean-ups.
 */
@Deprecated
public class MutualFond_centeralPage extends MutualFundCentralPage {

	public MutualFond_centeralPage(AndroidDriver driver) {
		super(driver);
	}

	@Deprecated
	public MutualFond_centeralPage selectAllFound() {
		selectAllFunds();
		return this;
	}
}
