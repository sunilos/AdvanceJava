/**
 * Package for demonstrating Log4j2 usage in Java applications.
 * <p>
 * This package contains examples to help beginners understand how to set up
 * and use Log4j2 for logging events, debugging, and error handling.
 * </p>
 */
package in.co.sunrays.Log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Demonstrates basic Log4j2 logging in a simple Java application.
 * <p>
 * This class shows how to:
 * <ul>
 * <li>Initialize a logger instance using Log4j2</li>
 * <li>Log messages at different levels: TRACE, DEBUG, INFO, WARN, ERROR,
 * FATAL</li>
 * <li>Log exceptions with stack traces</li>
 * </ul>
 * </p>
 * 
 * Usage:
 * 
 * <pre>
 *   java in.co.sunrays.Log4j2.TestLog2
 * </pre>
 * 
 * Make sure the Log4j2 configuration file (log4j2.xml or log4j2.properties) is
 * placed in the classpath (e.g., resources folder).
 * 
 * @author SunilOS
 * @version 1.0
 * @since 2025
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */
public class TestLog2 {

	/** Logger instance for this class. */
	private static final Logger log = LogManager.getLogger(TestLog2.class);

	/**
	 * Application entry point. Logs messages at all severity levels, then
	 * demonstrates exception logging.
	 * 
	 * @param args command-line arguments (not used in this example)
	 */
	public static void main(String[] args) {
		log.trace("Trace message");
		log.debug("Debug message");
		log.info("Info message");
		log.warn("Warn message");
		log.error("Error message");
		log.fatal("Fatal message");

		try {
			int x = 5 / 0; // Intentional division by zero to trigger exception
		} catch (RuntimeException e) {
			log.error("Computation failed", e);
		}
	}
}
