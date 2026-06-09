package com.ui.pojo;

public class UserData {
	private String mobileNo;
	private String otp;
	private String panNo;
	private String panName;
	private String emailID;
	private String DOB;
	
	// constructor
	public UserData(String mobileNo, String otp, String panNo, String panName, String emailID, String dOB) {
		super();
		this.mobileNo = mobileNo;
		this.otp = otp;
		this.panNo = panNo;
		this.panName = panName;
		this.emailID = emailID;
		DOB = dOB;
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
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	

}
