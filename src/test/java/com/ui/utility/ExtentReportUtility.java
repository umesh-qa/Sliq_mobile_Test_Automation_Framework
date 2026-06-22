package com.ui.utility;

import java.io.File;

import org.testng.ITestListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtility implements ITestListener {

	static ExtentReports extentReport;
	static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public static void setUpSparkReprort(String reportName) {

		ExtentSparkReporter extentSparkReport = new ExtentSparkReporter(
				System.getProperty("user.dir") + File.separator + "Reports" + File.separator + reportName);
		extentReport = new ExtentReports();
		extentReport.attachReporter(extentSparkReport);

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