package com.ui.utility;

import java.util.Iterator;
import com.ui.pojo.MutualFoundUserData;
import java.util.ArrayList;
import java.util.List;

/**
 * @deprecated Use {@link ReadMutualFundUserExcel} instead.
 * This class is maintained for legacy reference and will be removed in future clean-ups.
 */
@Deprecated
public class ReadMutualFoundUserExcel {

	@Deprecated
	public static Iterator<MutualFoundUserData> readExcelFile(String fileName) {
		// Delegates to the new class and adapts the return list for backward compatibility
		List<MutualFoundUserData> legacyList = new ArrayList<>();
		ReadMutualFundUserExcel.readExcelFile(fileName).forEachRemaining(data -> {
			legacyList.add(new MutualFoundUserData(
					data.getMobileNo(), data.getOtp(), data.getPanNo(), data.getPanName(), data.getEmailID(), data.getDOB(),
					data.getLoanAmount(), data.getGender(), data.getFatherName(), data.getMotherName(), data.getMaritalStatus(),
					data.getAnnualIncome(), data.getOccupation(), data.getNatureOfBusiness()
			));
		});
		return legacyList.iterator();
	}
}
