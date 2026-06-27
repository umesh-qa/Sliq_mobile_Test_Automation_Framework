package com.ui.test.regression;

import com.ui.pojo.MutualFoundUserData;
import org.testng.annotations.Test;

/**
 * @deprecated Use {@link KYCPersonalDetailsMandatoryCheck} instead.
 * This class is maintained for legacy reference and will be removed in future clean-ups.
 */
@Deprecated
public class KYCPersonalDetailsMendetoryCheck extends KYCPersonalDetailsMandatoryCheck {

	@Deprecated
	@Test(
		description = "Legacy entry point. Please run KYCPersonalDetailsMandatoryCheck class instead.",
		dataProviderClass = com.ui.dataprovider.MutualFoundUserDataProvider.class,
		dataProvider = "MutualFoundUser",
		enabled = false
	)
	public void ValidateAllKYCMendetoryCheck(MutualFoundUserData data) {
		// Enabled is false to prevent execution. Legacy code should use KYCPersonalDetailsMandatoryCheck.
	}

	@Deprecated
	@Test(
		description = "Legacy entry point. Please run KYCPersonalDetailsMandatoryCheck class instead.",
		dataProviderClass = com.ui.dataprovider.MutualFoundUserDataProvider.class,
		dataProvider = "MutualFoundUser",
		enabled = false
	)
	public void ValidateKYCPersoanlMendetoryCheck(MutualFoundUserData data) {
		// Enabled is false to prevent execution. Legacy code should use KYCPersonalDetailsMandatoryCheck.
	}

	@Deprecated
	@Test(
		description = "Legacy entry point. Please run KYCPersonalDetailsMandatoryCheck class instead.",
		dataProviderClass = com.ui.dataprovider.MutualFoundUserDataProvider.class,
		dataProvider = "MutualFoundUser",
		enabled = false
	)
	public void ValidateKYCFinancialMendetoryCheck(MutualFoundUserData data) {
		// Enabled is false to prevent execution. Legacy code should use KYCPersonalDetailsMandatoryCheck.
	}
}
