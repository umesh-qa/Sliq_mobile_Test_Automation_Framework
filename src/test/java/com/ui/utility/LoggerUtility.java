package com.ui.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility wrapper class for Log4j2 Logger initialization.
 */
public class LoggerUtility {

	private LoggerUtility() {
		// Private constructor to prevent instantiation
	}

	/**
	 * Returns a Logger instance for the specified class.
	 * 
	 * @param clazz the class to retrieve the logger for
	 * @return configured Log4j2 Logger instance
	 */
	public static Logger getLogger(Class<?> clazz) {
		return LogManager.getLogger(clazz);
	}
}
