```java
package in.co.sunrays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The HandleSQLException class demonstrates how to handle SQL exceptions in JDBC operations.
 * It tries to insert a record into a database table and handles any SQL errors that may occur.
 * SQL exceptions are caught and detailed information is displayed such as error message, SQL state, and error code.
 * 
 * @author SunilOS
 * @version 1.0
 * @since 2023-09-26
 * @see java.sql.Connection
 * @see java.sql.Statement
 * @see java.sql.SQLException
 */
public class HandleSQLException {

    /**
     * The main method is the entry point of the program.
     * It connects to the MySQL database, tries to execute an SQL INSERT operation, and handles SQLExceptions.
     * 
     * @param args Unused.
     * @throws Exception If there is any issue in loading the driver or closing resources.
     */
    public static void main(String args[]) throws Exception {

        // Load the MySQL JDBC driver
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        
        // Initialize connection and statement variables
        Connection conn = null;
        Statement stmt = null;

        try {
            // Establish a connection to the MySQL database
            // Database: test, Username: root, Password: (empty)
            conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");

            // Create a statement object to execute SQL queries
            stmt = conn.createStatement();

            // Execute an SQL INSERT query to add a record to the Person table
            int i = stmt.executeUpdate("INSERT INTO Person (name, age) VALUES ('Ram', 25)");

            // Print how many records were updated
            System.out.print(i + " Record(s) Updated");

        } catch (SQLException e) { // Catch any SQLExceptions that occur
            // Print the error message from the SQLException
            System.out.println("Error: " + e.getMessage());
            
            // Print the SQL state string which helps identify the error type
            System.out.println("SQL State: " + e.getSQLState());
            
            // Print the vendor-specific error code
            System.out.println("Error Code: " + e.getErrorCode());
            
            // Print the cause of the exception if available
            System.out.println("Cause: " + e.getCause());
            
            // Print the stack trace of the exception for debugging
            e.printStackTrace();

        } finally { // Finally block to ensure resources are closed
            // Close the statement and connection if they are not null
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
}
```

### Explanation of the Program:

1. **JDBC Driver Loading (`Class.forName()`)**:
   - This line loads the MySQL JDBC driver to allow the Java application to communicate with the MySQL database.

2. **Database Connection (`DriverManager.getConnection()`)**:
   - It establishes a connection to the MySQL database named `test`, with `root` as the username and no password.

3. **SQL Query Execution (`stmt.executeUpdate()`)**:
   - The SQL query inserts a record into the `Person` table with the values `('Ram', 25)` for the `name` and `age` columns.
   - The `executeUpdate()` method returns the number of rows affected by the query.

4. **Exception Handling (`catch (SQLException e)`)**:
   - If any SQL error occurs during the execution of the query or database operations, the program catches the `SQLException`.
   - The following details are printed:
     - **Error Message**: A description of the error.
     - **SQL State**: A string that provides information about the SQL state.
     - **Error Code**: A vendor-specific error code that provides more detailed information about the problem.
     - **Cause**: The root cause of the exception if available.
     - **Stack Trace**: A detailed trace of the exception for debugging purposes.

5. **Resource Cleanup (`finally`)**:
   - The `finally` block ensures that the database resources (the `Statement` and `Connection` objects) are closed even if an exception occurs.
   - Closing the resources prevents memory leaks and ensures that the database connection is properly released after the operation is complete.
