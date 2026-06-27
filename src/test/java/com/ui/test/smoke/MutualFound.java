package com.ui.test.smoke;

import com.ui.pojo.MutualFoundUserData;
import org.testng.annotations.Test;

/**
 * @deprecated Use {@link MutualFund} instead.
 * This class is maintained for legacy reference and will be removed in future clean-ups.
 */
@Deprecated
public class MutualFound extends MutualFund {

	@Deprecated
	@Test(
		description = "Legacy entry point for Mutual Found tests. Please run MutualFund class instead.",
		dataProviderClass = com.ui.dataprovider.MutualFoundUserDataProvider.class,
		dataProvider = "MutualFoundUser",
		enabled = false
	)
	public void MutualFoundHappyJourny(MutualFoundUserData data) {
		// Enabled is false to prevent execution. Legacy code should use MutualFund.
	}
}
