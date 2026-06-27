package com.ui.test.regression;

import com.ui.pojo.MutualFoundUserData;
import org.testng.annotations.Test;

/**
 * @deprecated Use {@link PanMandatoryCheck} instead.
 * This class is maintained for legacy reference and will be removed in future clean-ups.
 */
@Deprecated
public class PanMendetoryCheck extends PanMandatoryCheck {

	@Deprecated
	@Test(
		description = "Legacy entry point. Please run PanMandatoryCheck class instead.",
		dataProviderClass = com.ui.dataprovider.MutualFoundUserDataProvider.class,
		dataProvider = "MutualFoundUser",
		enabled = false
	)
	public void MFPanAllFieldsMendetoryCheck(MutualFoundUserData data) {
		// Enabled is false to prevent execution. Legacy code should use PanMandatoryCheck.
	}

	@Deprecated
	@Test(
		description = "Legacy entry point. Please run PanMandatoryCheck class instead.",
		dataProviderClass = com.ui.dataprovider.MutualFoundUserDataProvider.class,
		dataProvider = "MutualFoundUser",
		enabled = false
	)
	public void MFPanNoMendetoryCheck(MutualFoundUserData data) {
		// Enabled is false to prevent execution. Legacy code should use PanMandatoryCheck.
	}
}
