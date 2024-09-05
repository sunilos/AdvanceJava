package in.co.sunrays.log4J;

import org.apache.log4j.Logger;

/**
 * Demonstrates the use of Log4j for logging various levels of messages.
 * 
 * This class provides an example of how to use Log4j to log messages at different levels, including debug, info,
 * warn, error, and fatal. It also demonstrates logging an exception using Log4j.
 * 
 * @author SunilOS
 * @version 1.0
 * @since 2023
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */
public class TestLog {

    static Logger logger = Logger.getLogger(TestLog.class);

    /**
     * The main method that executes the logging demonstrations.
     * 
     * This method logs messages at various levels (debug, info, warn, error, and fatal) to show how Log4j can be used.
     * It also includes an example of logging an exception when an arithmetic error occurs (division by zero).
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        logger.debug("This is Debug Statement");
        logger.info("This is Info Statement");
        logger.warn("This is Warn Statement");
        logger.error("This is Error Statement");
        logger.fatal("This is Fatal Statement");

        int i = 0;

        try {
            int x = 5 / i;
        } catch (RuntimeException e) {
            logger.error("Arithmetic Error", e);
        }

    }

}
