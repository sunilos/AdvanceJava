package in.co.sunrays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Demonstrates handling SQL exceptions in JDBC.
 * 
 * This program connects to a MySQL database and attempts to execute an SQL `INSERT` 
 * statement to add a record to the `Person` table. It includes error handling for 
 * `SQLException` to display detailed information about the error, such as the error message, 
 * SQL state, error code, and stack trace. The program also ensures that resources are closed 
 * properly in the `finally` block.
 * 
 * Steps:
 * 1. Load the MySQL JDBC driver.
 * 2. Establish a connection to the MySQL database.
 * 3. Execute an SQL `INSERT` statement to add a record to the `Person` table.
 * 4. Handle any `SQLException` that may occur and print detailed error information.
 * 5. Ensure that `Statement` and `Connection` objects are closed in the `finally` block.
 * 
 * Note: Ensure the database connection details (URL, username, password) and 
 * the table schema (`Person`) are correctly configured before running the program.
 * 
 * @author SunilOS
 * @version 1.0
 * @since 2023
 * @see java.sql.Connection
 * @see java.sql.DriverManager
 * @see java.sql.Statement
 * @see java.sql.SQLException
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class HandleSQLException {

    public static void main(String args[]) throws Exception {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/test",
                    "root", "");

            stmt = conn.createStatement();

            int i = stmt.executeUpdate("INSERT into Person (name,age) values ('Ram',25)");

            System.out.print(i + " Record(s) Updated");

        } catch (SQLException e) { // Handle exception
            System.out.println("Error: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Cause: " + e.getCause());
            e.printStackTrace();

        } finally { // Close resources
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
