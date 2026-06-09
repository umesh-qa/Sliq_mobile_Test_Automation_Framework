package com.ui.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.ui.test.BaseTest;
import com.ui.utility.ExtentReport;
import com.ui.utility.MobileUtility;

public class MyTestListners implements ITestListener {

	public void onStart(ITestContext context) {
		ExtentReport.setUpSparkReprort("report.html");

	}

	public void onTestStart(ITestResult result) {
		ExtentReport.creatExtentTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		ExtentReport.getExtentTest().log(Status.PASS, result.getMethod().getMethodName());

	}

	public void onTestFailure(ITestResult result) {
		ExtentReport.getExtentTest().log(Status.FAIL, result.getMethod().getMethodName());
		ExtentReport.getExtentTest().log(Status.FAIL, result.getThrowable().getMessage());

		Object object = result.getInstance();
		MobileUtility mobileUtility = ((BaseTest) object).getInstance();

		String screenShotPath = mobileUtility.getScreenshotPath(result.getMethod().getMethodName());
		ExtentReport.getExtentTest().addScreenCaptureFromPath(screenShotPath);

	}

	public void onTestSkipped(ITestResult result) {
		ExtentReport.getExtentTest().log(Status.SKIP, result.getMethod().getMethodName());

	}

	public void onFinish(ITestContext context) {
		ExtentReport.flushReport();
	}

}
