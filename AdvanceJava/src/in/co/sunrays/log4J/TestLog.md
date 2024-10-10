This class demonstrates the usage of Apache Log4J for logging various levels of log messages (`DEBUG`, `INFO`, `WARN`, `ERROR`, and `FATAL`). The log levels represent the importance or severity of the log messages.

Log4J is a Java-based logging utility widely used for keeping track of system events, errors, or other important information during the execution of a program.

### Code with Javadoc and Comments:
```java
package in.co.sunrays.log4J;

import org.apache.log4j.Logger;

/**
 * TestLog is a simple demonstration of Log4J logging levels.
 * It logs messages at different levels (DEBUG, INFO, WARN, ERROR, FATAL).
 * Additionally, it handles an exception and logs an error message when an arithmetic error occurs.
 * 
 * @author Sunil
 * @version 1.0
 * @since 2024
 */
public class TestLog {

    // Creates a logger instance for this class using Log4J
    static Logger logger = Logger.getLogger(TestLog.class);

    /**
     * The main method is the entry point of the program.
     * It logs messages at various log levels and handles an exception.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {

        // Logging different levels of messages to demonstrate the use of Log4J
        logger.debug("This is Debug Statement");  // DEBUG: Detailed information, typically useful during development
        logger.info("This is Info Statement");    // INFO: General information about the application's running status
        logger.warn("This is Warn Statement");    // WARN: A warning that something may cause problems later
        logger.error("This is Error Statement");  // ERROR: Indicates a serious issue that needs attention
        logger.fatal("This is Fatal Statement");  // FATAL: Critical issue that may cause the application to crash

        // Example of an arithmetic operation that will cause a runtime exception
        int i = 0;

        try {
            // Attempting to divide by zero, which will cause ArithmeticException
            int x = 5 / i;
        } catch (RuntimeException e) {
            // Logs the exception message and stack trace at the ERROR level
            logger.error("Arithmetic Error: Division by zero occurred", e);
        }

    }
}
```

### Breakdown of Changes:

1. **Javadoc Comments**:
   - Added class-level, method-level, and inline comments to explain the purpose and functionality of the code.
   - The class-level Javadoc explains the general use of the class.
   - The method-level Javadoc describes the `main()` method and what it does.
   - Comments inside the `main()` method explain each log level and the arithmetic error handling.

2. **Logging Levels**:
   - **DEBUG**: Useful during development to provide detailed information about the flow of the program.
   - **INFO**: Logs informative messages about the general state of the application.
   - **WARN**: Logs warning messages that could indicate a potential problem.
   - **ERROR**: Logs error messages when something goes wrong.
   - **FATAL**: Logs critical issues that may cause the application to crash.

3. **Exception Handling**:
   - The division by zero (`5 / i`) triggers a `RuntimeException` (specifically `ArithmeticException`), which is caught, and the error message with the stack trace is logged using the `error` level.
