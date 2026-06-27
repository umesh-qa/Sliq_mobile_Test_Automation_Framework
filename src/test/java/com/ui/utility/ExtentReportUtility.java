package com.ui.utility;

import java.io.File;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * Utility to configure, generate, and manage ExtentReports for test execution feedback.
 */
public class ExtentReportUtility {

	private static ExtentReports extentReport;
	private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	/**
	 * Configures Spark HTML reporter and attaches system configurations.
	 * 
	 * @param reportName name of the output HTML report file
	 */
	public static void setUpSparkReport(String reportName) {
		ExtentSparkReporter extentSparkReport = new ExtentSparkReporter(
				System.getProperty("user.dir") + File.separator + "Reports" + File.separator + reportName);
		
		extentReport = new ExtentReports();
		extentReport.attachReporter(extentSparkReport);
		extentReport.setSystemInfo("Platform", "Android");
		extentReport.setSystemInfo("Device", "POCO");
		extentReport.setSystemInfo("OS", "Android 15");
		extentReport.setSystemInfo("APK Version", "APK_DEV");
		extentReport.setSystemInfo("Environment", "DEV");
	}

	/**
	 * Creates a new ExtentTest instance for the active thread.
	 * 
	 * @param testName name of the test case
	 */
	public static void createExtentTest(String testName) {
		ExtentTest test = extentReport.createTest(testName);
		extentTest.set(test);
	}

	/**
	 * Gets the current thread's ExtentTest instance.
	 * 
	 * @return active ExtentTest
	 */
	public static ExtentTest getExtentTest() {
		return extentTest.get();
	}

	/**
	 * Flushes the report content to the output HTML file.
	 */
	public static void flushReport() {
		if (extentReport != null) {
			extentReport.flush();
		}
	}
}