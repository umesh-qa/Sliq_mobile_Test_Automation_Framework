package com.ui.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.ui.test.BaseTest;
import com.ui.utility.ExtentReportUtility;
import com.ui.utility.MobileUtility;

public class MyTestListners implements ITestListener {

	public void onStart(ITestContext context) {
		ExtentReportUtility.setUpSparkReprort("reports.html");

	}

	public void onTestStart(ITestResult result) {
		System.out.println("On test metodod started ");
		ExtentReportUtility.creatExtentTest(result.getMethod().getMethodName());
		System.out.println("Extent is created");

	}

	public void onTestSuccess(ITestResult result) {
		ExtentReportUtility.getExtentTest().log(Status.PASS, result.getMethod().getMethodName());

	}

	public void onTestFailure(ITestResult result) {

//		if (ExtentReportUtility.getExtentTest() != null) {

			Object object = result.getInstance();
			MobileUtility mobileUtility = ((BaseTest) object).getInstance();

			String screenShotPath = mobileUtility.getScreenshotPath(result.getMethod().getMethodName());
			ExtentReportUtility.getExtentTest().addScreenCaptureFromPath(screenShotPath);

			ExtentReportUtility.getExtentTest().log(Status.FAIL, result.getMethod().getMethodName());
//		} 

	}

	public void onTestSkipped(ITestResult result) {
		ExtentReportUtility.getExtentTest().log(Status.SKIP, result.getMethod().getMethodName());

	}

	public void onFinish(ITestContext context) {
		ExtentReportUtility.flushReport();
	}

}
