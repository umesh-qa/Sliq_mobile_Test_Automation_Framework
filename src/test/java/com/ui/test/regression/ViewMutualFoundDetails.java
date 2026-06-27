package com.ui.test.regression;

import com.ui.pojo.MutualFoundUserData;
import org.testng.annotations.Test;

/**
 * @deprecated Use {@link ViewMutualFundDetails} instead.
 * This class is maintained for legacy reference and will be removed in future clean-ups.
 */
@Deprecated
public class ViewMutualFoundDetails extends ViewMutualFundDetails {

	@Deprecated
	@Test(
		description = "Legacy entry point for View Mutual Found Details tests. Please run ViewMutualFundDetails class instead.",
		dataProviderClass = com.ui.dataprovider.MutualFoundUserDataProvider.class,
		dataProvider = "MutualFoundUser",
		enabled = false
	)
	public void MutualFoundHappyJourny(MutualFoundUserData data) {
		// Enabled is false to prevent execution. Legacy code should use ViewMutualFundDetails.
	}
}
