package com.ui.pojo;

public class SharesUserData {

	private String mobileNo;
	private String otp;
	private String panNo;
	private String panName;
	private String emailID;
	private String DOB;
	private String LoanAmount;
	private String DPID;
	private String ClientID;
	private String SecurityName;
	private String SharesName;
	private String QTY;
	private String Gender;
	private String FatherName;
	private String MotherName;
	private String MaritalStatus;
	private String AnnualIncome;
	private String Occupation;
	private String NatureOfBusiness;

	public SharesUserData(String mobileNo, String otp, String panNo, String panName, String emailID, String dOB,
			String loanAmount, String dPID, String clientID, String securityName, String sharesName, String qTY,
			String gender, String fatherName, String motherName, String maritalStatus, String annualIncome,
			String occupation, String natureOfBusiness) {
		super();
		this.mobileNo = mobileNo;
		this.otp = otp;
		this.panNo = panNo;
		this.panName = panName;
		this.emailID = emailID;
		DOB = dOB;
		LoanAmount = loanAmount;
		DPID = dPID;
		ClientID = clientID;
		SecurityName = securityName;
		SharesName = sharesName;
		QTY = qTY;
		Gender = gender;
		FatherName = fatherName;
		MotherName = motherName;
		MaritalStatus = maritalStatus;
		AnnualIncome = annualIncome;
		Occupation = occupation;
		NatureOfBusiness = natureOfBusiness;
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

	public String getLoanAmount() {
		return LoanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		LoanAmount = loanAmount;
	}

	public String getDPID() {
		return DPID;
	}

	public void setDPID(String dPID) {
		DPID = dPID;
	}

	public String getClientID() {
		return ClientID;
	}

	public void setClientID(String clientID) {
		ClientID = clientID;
	}

	public String getSecurityName() {
		return SecurityName;
	}

	public void setSecurityName(String securityName) {
		SecurityName = securityName;
	}

	public String getSharesName() {
		return SharesName;
	}

	public void setSharesName(String sharesName) {
		SharesName = sharesName;
	}

	public String getQTY() {
		return QTY;
	}

	public void setQTY(String qTY) {
		QTY = qTY;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getFatherName() {
		return FatherName;
	}

	public void setFatherName(String fatherName) {
		FatherName = fatherName;
	}

	public String getMotherName() {
		return MotherName;
	}

	public void setMotherName(String motherName) {
		MotherName = motherName;
	}

	public String getMaritalStatus() {
		return MaritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		MaritalStatus = maritalStatus;
	}

	public String getAnnualIncome() {
		return AnnualIncome;
	}

	public void setAnnualIncome(String annualIncome) {
		AnnualIncome = annualIncome;
	}

	public String getOccupation() {
		return Occupation;
	}

	public void setOccupation(String occupation) {
		Occupation = occupation;
	}

	public String getNatureOfBusiness() {
		return NatureOfBusiness;
	}

	public void setNatureOfBusiness(String natureOfBusiness) {
		NatureOfBusiness = natureOfBusiness;
	}

}
