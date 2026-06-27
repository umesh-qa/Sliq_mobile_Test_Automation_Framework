package com.ui.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.ui.test.smoke.BaseTest;
import com.ui.utility.ExtentReportUtility;
import com.ui.utility.MobileUtility;

/**
 * Custom TestNG Test Listener to link test execution states to ExtentReports.
 */
public class MyTestListeners implements ITestListener {

	@Override
	public void onStart(ITestContext context) {
		String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new java.util.Date());
		String suiteName = context.getSuite().getName();
		if (suiteName == null || suiteName.trim().isEmpty()) {
			suiteName = "ExecutionReport";
		}
		// Replace spaces and special characters with underscores to make it file-system safe
		String cleanedSuiteName = suiteName.replaceAll("[^a-zA-Z0-9\\-_]", "_");
		ExtentReportUtility.setUpSparkReport(cleanedSuiteName + "_" + timestamp + ".html");
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentReportUtility.createExtentTest(result.getMethod().getMethodName());
		ExtentReportUtility.getExtentTest().info("Test Execution Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentReportUtility.getExtentTest().log(Status.PASS, result.getMethod().getMethodName());
		ExtentReportUtility.getExtentTest().info(result.getMethod().getDescription());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentReportUtility.getExtentTest().log(Status.FAIL, result.getMethod().getMethodName());
		ExtentReportUtility.getExtentTest().info(result.getMethod().getDescription());
		ExtentReportUtility.getExtentTest().fail(result.getThrowable());

		try {
			Object object = result.getInstance();
			MobileUtility mobileUtility = ((BaseTest) object).getInstance();

			String screenShotPath = mobileUtility.getScreenshotPath(result.getMethod().getMethodName());
			ExtentReportUtility.getExtentTest().addScreenCaptureFromPath(screenShotPath, "Failure Screenshot");
		} catch (Exception e) {
			ExtentReportUtility.getExtentTest().warning("Unable to capture screenshot: " + e.getMessage());
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentReportUtility.getExtentTest().log(Status.SKIP, result.getMethod().getMethodName());
		ExtentReportUtility.getExtentTest().info(result.getMethod().getDescription());

		if (result.getThrowable() != null) {
			ExtentReportUtility.getExtentTest().skip(result.getThrowable());
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		ExtentReportUtility.flushReport();
	}
}
