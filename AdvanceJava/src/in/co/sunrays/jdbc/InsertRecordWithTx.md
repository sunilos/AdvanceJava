```java
package in.co.sunrays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class provides functionality to insert records into a database table
 * with transaction handling. It demonstrates the use of both Statement and
 * PreparedStatement for executing SQL INSERT commands.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */
public class InsertRecordWithTx {

    /**
     * Main method to execute the insertion of a record using Statement.
     * 
     * @param args Command line arguments (not used).
     * @throws Exception if any error occurs during the database operations.
     */
    public static void main(String args[]) throws Exception {
        // Load MySQL JDBC driver
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        // Establish a connection to the database
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/test", "root", "");

        // Start transaction by disabling auto-commit mode
        conn.setAutoCommit(false);

        // Create a statement object to execute SQL queries
        Statement stmt = conn.createStatement();

        // Execute an INSERT SQL command and retrieve the number of affected rows
        int i = stmt.executeUpdate("INSERT into part values (5,'Plat','Green',1)");

        // Output the number of records updated
        System.out.print(i + " Record(s) Updated");

        // Commit the transaction to the database
        conn.commit();

        // Close the statement and connection objects
        stmt.close();
        conn.close();
    }

    /**
     * Executes an INSERT statement using a PreparedStatement to enhance
     * security and performance.
     * 
     * @throws Exception if any error occurs during the database operations.
     */
    public static void testPreparedInsert() throws Exception {
        // Load MySQL JDBC driver
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        try {
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/test", "root", "root");

            // Start transaction by disabling auto-commit mode
            conn.setAutoCommit(false);

            // Prepare an SQL INSERT statement with placeholders
            PreparedStatement ps = conn.prepareStatement("INSERT into part values (?,?,?,?)");

            // Set values for the placeholders in the prepared statement
            ps.setInt(1, 7);
            ps.setString(2, "Plat2");
            ps.setString(3, "Red");
            ps.setInt(4, 1);

            // Execute the prepared statement and retrieve the number of affected rows
            int recCount = ps.executeUpdate();

            // Output the number of records inserted
            System.out.println("# of Records: " + recCount);
            System.out.print(recCount + " Record(s) Updated");

            // Commit the transaction to the database
            conn.commit();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            // Rollback the transaction in case of an error
            conn.rollback();
            // Print the stack trace for debugging
            e.printStackTrace();
        }
    }
}
```

### Changes Made:
1. **Javadoc Comments**: Added Javadoc for the class and methods, explaining their purpose, parameters, and exceptions.
2. **Inline Comments**: Added comments throughout the code to explain what each significant step does, especially around connection handling, transaction management, and SQL execution.
3. **Error Handling**: Added a rollback statement in the `catch` block of `testPreparedInsert` to ensure that transactions are rolled back in case of an error, maintaining database integrity. 
