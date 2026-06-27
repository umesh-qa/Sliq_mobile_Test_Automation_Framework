package com.ui.pojo;

/**
 * @deprecated Use {@link MutualFundUserData} instead.
 * This class is maintained for legacy reference and will be removed in future clean-ups.
 */
@Deprecated
public class MutualFoundUserData extends MutualFundUserData {

	public MutualFoundUserData(String mobileNo, String otp, String panNo, String panName, String emailID, String dob,
			String loanAmount, String gender, String fatherName, String motherName, String maritalStatus,
			String annualIncome, String occupation, String natureOfBusiness) {
		super(mobileNo, otp, panNo, panName, emailID, dob, loanAmount, gender, fatherName, motherName, maritalStatus,
				annualIncome, occupation, natureOfBusiness);
	}
}
