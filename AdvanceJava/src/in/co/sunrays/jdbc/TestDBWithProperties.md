```java
package in.co.sunrays.jdbc;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * This class demonstrates how to connect to a database using JDBC 
 * while avoiding hard-coded credentials by utilizing a properties file.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */
public class TestDBWithProperties {

    /**
     * The main method serves as the entry point for the application.
     * It calls the method to test the database select operation.
     *
     * @param args Command line arguments (not used).
     * @throws Exception if any error occurs during execution.
     */
    public static void main(String args[]) throws Exception {
        testSelect(); // Call the method to perform a SELECT operation
        // testInsert(); // Uncomment to call an insert method if needed
    }

    /**
     * This method establishes a connection to the database and retrieves 
     * data from the ST_PART table, displaying the results in the console.
     *
     * @throws Exception if there is an error with the database connection 
     *                   or SQL operation.
     */
    public static void testSelect() throws Exception {
        
        // Load database credentials from the properties file using ResourceBundle
        ResourceBundle rb = ResourceBundle.getBundle("in.co.sunrays.jdbc.system");

        // Retrieve database connection parameters
        String driverName = rb.getString("database.driver");
        String url = rb.getString("database.url");
        String loginId = rb.getString("database.user");
        String password = rb.getString("database.password");

        // Load the JDBC driver
        Class.forName(driverName);

        // Establish a connection to the database
        Connection conn = DriverManager.getConnection(url, loginId, password);

        // Disable auto-commit mode for manual transaction control
        conn.setAutoCommit(false);

        // Create a Statement object for executing SQL queries
        Statement stmt = conn.createStatement();

        // Execute the SQL SELECT query to retrieve data
        ResultSet rs = stmt.executeQuery("SELECT id, name, color FROM ST_PART");

        // Display the results in a tabular format
        System.out.println("ID\tName\tColor");
        System.out.println("--\t----\t-----");

        // Iterate through the result set and print each row's data
        while (rs.next()) {
            System.out.print(rs.getString(1)); // Get and print ID
            System.out.print("\t" + rs.getString(2)); // Get and print Name
            System.out.println("\t" + rs.getString("color")); // Get and print Color
        }

        // Commit the transaction (although it's not necessary for a SELECT query)
        conn.commit();

        // Close the Statement and Connection to free up resources
        stmt.close();
        conn.close();
    }
}
```

### Explanation:
1. **Package Declaration**: The class is part of the `in.co.sunrays.jdbc` package, indicating its role in JDBC operations.

2. **Imports**: Necessary Java SQL and ResourceBundle classes are imported for database operations and properties file handling.

3. **Class Documentation**: The class-level Javadoc provides a clear overview of its purpose, stating it manages database connections without hardcoding credentials.

4. **Main Method**: 
   - The `main` method is the entry point, invoking the `testSelect` method to fetch and display data from the database.
   - A commented line for `testInsert()` suggests that an insert operation could also be implemented in the future.

5. **testSelect Method**:
   - **ResourceBundle**: Loads database credentials from a properties file, making the code more flexible and secure by avoiding hardcoded values.
   - **Connection Handling**: 
     - Loads the JDBC driver and establishes a connection to the database using credentials from the properties file.
     - Sets auto-commit to false for manual transaction control (although it's not strictly necessary for a SELECT query).
   - **Executing Queries**: 
     - Executes a SQL SELECT query and processes the results.
     - Prints the results in a formatted table.
   - **Resource Management**: 
     - Commits the transaction (not essential for SELECT) and ensures that all resources (statement and connection) are properly closed to prevent memory leaks.
