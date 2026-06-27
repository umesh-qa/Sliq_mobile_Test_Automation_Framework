package com.ui.pojo;

/**
 * POJO class representing User Data for Mutual Fund Loan application flows.
 * Follows standard Java naming conventions and includes a helper toString method for easy debugging.
 */
public class MutualFundUserData {

	private String mobileNo;
	private String otp;
	private String panNo;
	private String panName;
	private String emailID;
	private String dob;
	private String loanAmount;
	private String gender;
	private String fatherName;
	private String motherName;
	private String maritalStatus;
	private String annualIncome;
	private String occupation;
	private String natureOfBusiness;

	// Default Constructor
	public MutualFundUserData() {
		super();
	}

	// Parameterized Constructor
	public MutualFundUserData(String mobileNo, String otp, String panNo, String panName, String emailID, String dob,
			String loanAmount, String gender, String fatherName, String motherName, String maritalStatus,
			String annualIncome, String occupation, String natureOfBusiness) {
		super();
		this.mobileNo = mobileNo;
		this.otp = otp;
		this.panNo = panNo;
		this.panName = panName;
		this.emailID = emailID;
		this.dob = dob;
		this.loanAmount = loanAmount;
		this.gender = gender;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.maritalStatus = maritalStatus;
		this.annualIncome = annualIncome;
		this.occupation = occupation;
		this.natureOfBusiness = natureOfBusiness;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getPanName() {
		return panName;
	}

	public void setPanName(String panName) {
		this.panName = panName;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getDOB() {
		return dob;
	}

	public void setDOB(String dob) {
		this.dob = dob;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getNatureOfBusiness() {
		return natureOfBusiness;
	}

	public void setNatureOfBusiness(String natureOfBusiness) {
		this.natureOfBusiness = natureOfBusiness;
	}

	@Override
	public String toString() {
		return "MutualFundUserData [mobileNo=" + mobileNo + ", otp=" + otp + ", panNo=" + panNo + ", panName=" + panName
				+ ", emailID=" + emailID + ", dob=" + dob + ", loanAmount=" + loanAmount + ", gender=" + gender
				+ ", fatherName=" + fatherName + ", motherName=" + motherName + ", maritalStatus=" + maritalStatus
				+ ", annualIncome=" + annualIncome + ", occupation=" + occupation + ", natureOfBusiness="
				+ natureOfBusiness + "]";
	}
}
