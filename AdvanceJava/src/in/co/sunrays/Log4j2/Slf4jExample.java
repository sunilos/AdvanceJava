/**
 * Package for demonstrating integration of SLF4J with Log4j2.
 * <p>
 * This package contains examples to help Java beginners understand how to use
 * the SLF4J API as a logging facade while using Log4j2 as the underlying logging backend.
 * </p>
 */
package in.co.sunrays.Log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstrates logging with the SLF4J API, using Log4j2 as the backend
 * implementation.
 * <p>
 * SLF4J (Simple Logging Facade for Java) is not a logging framework by itself.
 * It acts as a facade (abstraction layer) that allows developers to write
 * logging code independent of the actual logging backend. In this example:
 * <ul>
 * <li>SLF4J API is used for logging calls</li>
 * <li>Log4j2 is the configured backend that handles the actual logging</li>
 * </ul>
 * </p>
 *
 * Example output may look like:
 * 
 * <pre>
 * [2025-10-02 15:20:45] INFO  Slf4jExample:14 - Hello from SLF4J with Log4j2 as backend!
 * [2025-10-02 15:20:45] DEBUG Slf4jExample:15 - Debug details: id=101, status=active
 * </pre>
 *
 * Ensure that the following jars are present in the classpath:
 * <ul>
 * <li>slf4j-api-x.x.x.jar</li>
 * <li>log4j-api-x.x.x.jar</li>
 * <li>log4j-core-x.x.x.jar</li>
 * <li>log4j-slf4j2-impl-x.x.x.jar</li>
 * </ul>
 * and that a valid Log4j2 configuration file (log4j2.xml or log4j2.properties)
 * is available in the resources folder or classpath.
 * 
 * @author SunilOS
 * @version 1.0
 * @since 2025
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */
public class Slf4jExample {

	/** Logger instance created via SLF4J facade. */
	private static final Logger log = LoggerFactory.getLogger(Slf4jExample.class);

	/**
	 * Application entry point.
	 * <p>
	 * Demonstrates:
	 * <ul>
	 * <li>Logging an INFO-level message</li>
	 * <li>Logging a parameterized DEBUG-level message</li>
	 * </ul>
	 * </p>
	 *
	 * @param args command-line arguments (not used in this example)
	 */
	public static void main(String[] args) {
		log.info("Hello from SLF4J with Log4j2 as backend!");
		log.debug("Debug details: id={}, status={}", 101, "active");
	}
}
