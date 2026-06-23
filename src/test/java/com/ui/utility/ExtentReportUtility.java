package com.ui.utility;

import java.io.File;
import java.sql.Driver;

import org.testng.ITestListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.test.BaseTest;

public class ExtentReportUtility implements ITestListener {

	static ExtentReports extentReport;
	static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public static void setUpSparkReprort(String reportName) {

		ExtentSparkReporter extentSparkReport = new ExtentSparkReporter(
				System.getProperty("user.dir") + File.separator + "Reports" + File.separator + reportName);
		extentReport = new ExtentReports();
//		Object object = new Object();
//		MobileUtility mobileUtility=((BaseTest) object).getInstance();
		extentReport.attachReporter(extentSparkReport);
		extentReport.setSystemInfo("Platform", "Android ");
		extentReport.setSystemInfo("Device", "POCO");
		extentReport.setSystemInfo("os", "Android 15");
		extentReport.setSystemInfo("APK Version","APK_DEV");
		extentReport.setSystemInfo("Environment", "DEV");
		


	}

	public static void creatExtentTest(String testName) {
		ExtentTest test = extentReport.createTest(testName);
		extentTest.set(test);
	}

	public static ExtentTest getExtentTest() {
		return extentTest.get();
	}

	public static void flushReport() {
		extentReport.flush();

	}

}