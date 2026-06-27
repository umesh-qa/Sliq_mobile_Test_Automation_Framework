package com.ui.pojo;

/**
 * POJO class representing generic User Data.
 * Standardizes field names and includes a toString method for debug visibility.
 */
public class UserData {
	private String mobileNo;
	private String otp;
	private String panNo;
	private String panName;
	private String emailID;
	private String dob;
	private String loanAmount;
	private String dpId;
	private String clientId;

	// Constructor
	public UserData(String mobileNo, String otp, String panNo, String panName, String emailID, String dob,
			String loanAmount, String dpId, String clientId) {
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
	}

	public String getDPID() {
		return dpId;
	}

	public void setDPID(String dpId) {
		this.dpId = dpId;
	}

	public String getCLIENTID() {
		return clientId;
	}

	public void setCLIENTID(String clientId) {
		this.clientId = clientId;
	}

	public String getLOANAMOUNT() {
		return loanAmount;
	}

	public void setLOANAMOUNT(String loanAmount) {
		this.loanAmount = loanAmount;
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

	@Override
	public String toString() {
		return "UserData [mobileNo=" + mobileNo + ", otp=" + otp + ", panNo=" + panNo + ", panName=" + panName
				+ ", emailID=" + emailID + ", dob=" + dob + ", loanAmount=" + loanAmount + ", dpId=" + dpId
				+ ", clientId=" + clientId + "]";
	}
}
