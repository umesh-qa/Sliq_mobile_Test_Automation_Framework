package com.ui.listeners;

import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import com.ui.utility.LoggerUtility;

/**
 * Custom Retry Analyzer to automatically retry failed tests up to a maximum limit.
 */
public class MyRetryAnalyzer implements IRetryAnalyzer {

	private static final Logger logger = LoggerUtility.getLogger(MyRetryAnalyzer.class);

	private int count = 0;
	private final int maxCount = 3;

	@Override
	public boolean retry(ITestResult result) {
		if (count < maxCount) {
			count++;
			logger.warn("Test execution for '" + result.getMethod().getMethodName() 
					+ "' failed. Retrying execution (" + count + "/" + maxCount + ")...");
			return true;
		}
		logger.info("Maximum retry attempts reached (" + maxCount + ") for test '" + result.getMethod().getMethodName() + "'.");
		return false;
	}
}
