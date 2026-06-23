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
		ExtentReportUtility.creatExtentTest(result.getMethod().getMethodName());
		ExtentReportUtility.getExtentTest().info("Test Execution Started");
	}

	public void onTestSuccess(ITestResult result) {
		ExtentReportUtility.getExtentTest().log(Status.PASS, result.getMethod().getMethodName());
		ExtentReportUtility.getExtentTest().info(result.getMethod().getDescription());
	}

	public void onTestFailure(ITestResult result) {

		ExtentReportUtility.getExtentTest().log(Status.FAIL, result.getMethod().getMethodName());
		ExtentReportUtility.getExtentTest().info(result.getMethod().getDescription());
		ExtentReportUtility.getExtentTest().fail(result.getThrowable());

		try {
		Object object = result.getInstance();
		MobileUtility mobileUtility = ((BaseTest) object).getInstance();

		String screenShotPath = mobileUtility.getScreenshotPath(result.getMethod().getMethodName());
		ExtentReportUtility.getExtentTest().addScreenCaptureFromPath(screenShotPath,"Failure Screenshot");
		
		}catch(Exception e) {
			ExtentReportUtility.getExtentTest().warning("Unable to capture screenshot : "+e.getMessage());
		}

	}

	public void onTestSkipped(ITestResult result) {
		ExtentReportUtility.getExtentTest().log(Status.SKIP, result.getMethod().getMethodName());
		ExtentReportUtility.getExtentTest().info(result.getMethod().getDescription());

		
		if(result.getThrowable()!=null) {
			ExtentReportUtility.getExtentTest().skip(result.getThrowable());
		}

	}

	public void onFinish(ITestContext context) {
		ExtentReportUtility.flushReport();
	}

}
