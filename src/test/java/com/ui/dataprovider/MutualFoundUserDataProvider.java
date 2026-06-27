package com.ui.dataprovider;

import java.util.Iterator;
import org.testng.annotations.DataProvider;
import com.ui.pojo.MutualFoundUserData;
import java.util.ArrayList;
import java.util.List;

/**
 * @deprecated Use {@link MutualFundUserDataProvider} instead.
 * This class is maintained for legacy reference and will be removed in future clean-ups.
 */
@Deprecated
public class MutualFoundUserDataProvider {

	@Deprecated
	@DataProvider(name = "MutualFoundUser")
	public Iterator<MutualFoundUserData> userDataProvider() {
		// Adapt the new provider output to return legacy MutualFoundUserData
		List<MutualFoundUserData> legacyList = new ArrayList<>();
		new MutualFundUserDataProvider().userDataProvider().forEachRemaining(data -> {
			legacyList.add(new MutualFoundUserData(
					data.getMobileNo(), data.getOtp(), data.getPanNo(), data.getPanName(), data.getEmailID(), data.getDOB(),
					data.getLoanAmount(), data.getGender(), data.getFatherName(), data.getMotherName(), data.getMaritalStatus(),
					data.getAnnualIncome(), data.getOccupation(), data.getNatureOfBusiness()
			));
		});
		return legacyList.iterator();
	}
}
