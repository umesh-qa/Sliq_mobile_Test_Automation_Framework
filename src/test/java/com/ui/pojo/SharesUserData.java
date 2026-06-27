package com.ui.pojo;

/**
 * POJO class representing User Data specifically for Shares Loan flows.
 * Follows standard Java naming conventions and includes a helper toString method.
 */
public class SharesUserData {

	private String mobileNo;
	private String otp;
	private String panNo;
	private String panName;
	private String emailID;
	private String dob;
	private String loanAmount;
	private String dpId;
	private String clientId;
	private String securityName;
	private String sharesName;
	private String qty;
	private String gender;
	private String fatherName;
	private String motherName;
	private String maritalStatus;
	private String annualIncome;
	private String occupation;
	private String natureOfBusiness;

	// Constructor
	public SharesUserData(String mobileNo, String otp, String panNo, String panName, String emailID, String dob,
			String loanAmount, String dpId, String clientId, String securityName, String sharesName, String qty,
			String gender, String fatherName, String motherName, String maritalStatus, String annualIncome,
			String occupation, String natureOfBusiness) {
		super();
		this.mobileNo = mobileNo;
		this.otp = otp;
		this.panNo = panNo;
		this.panName = panName;
		this.emailID = emailID;
		this.dob = dob;
		this.loanAmount = loanAmount;
		this.dpId = dpId;
		this.clientId = clientId;
		this.securityName = securityName;
		this.sharesName = sharesName;
		this.qty = qty;
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

	public String getDPID() {
		return dpId;
	}

	public void setDPID(String dpId) {
		this.dpId = dpId;
	}

	public String getClientID() {
		return clientId;
	}

	public void setClientID(String clientId) {
		this.clientId = clientId;
	}

	public String getSecurityName() {
		return securityName;
	}

	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}

	public String getSharesName() {
		return sharesName;
	}

	public void setSharesName(String sharesName) {
		this.sharesName = sharesName;
	}

	public String getQTY() {
		return qty;
	}

	public void setQTY(String qty) {
		this.qty = qty;
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
		return "SharesUserData [mobileNo=" + mobileNo + ", otp=" + otp + ", panNo=" + panNo + ", panName=" + panName
				+ ", emailID=" + emailID + ", dob=" + dob + ", loanAmount=" + loanAmount + ", dpId=" + dpId
				+ ", clientId=" + clientId + ", securityName=" + securityName + ", sharesName=" + sharesName + ", qty="
				+ qty + ", gender=" + gender + ", fatherName=" + fatherName + ", motherName=" + motherName
				+ ", maritalStatus=" + maritalStatus + ", annualIncome=" + annualIncome + ", occupation=" + occupation
				+ ", natureOfBusiness=" + natureOfBusiness + "]";
	}
}
