package in.co.sunrays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Demonstrates inserting a record into a table with transaction handling using JDBC.
 * 
 * This class provides two methods for inserting records into the `part` table in a MySQL database:
 * 1. `main`: Uses a `Statement` object to execute a simple SQL `INSERT` statement with transaction management.
 * 2. `testPreparedInsert`: Uses a `PreparedStatement` to execute a parameterized SQL `INSERT` statement with transaction handling 
 *    and error handling for SQLException.
 * 
 * Steps for `main` method:
 * 1. Load the MySQL JDBC driver.
 * 2. Establish a connection to the MySQL database.
 * 3. Disable auto-commit mode to start a transaction.
 * 4. Execute an SQL `INSERT` statement using a `Statement` object.
 * 5. Commit the transaction.
 * 6. Close `Statement` and `Connection` objects.
 * 
 * Steps for `testPreparedInsert` method:
 * 1. Load the MySQL JDBC driver.
 * 2. Establish a connection to the MySQL database.
 * 3. Disable auto-commit mode to start a transaction.
 * 4. Use a `PreparedStatement` to execute a parameterized SQL `INSERT` statement.
 * 5. Commit the transaction.
 * 6. Close `PreparedStatement` and `Connection` objects.
 * 7. Handle any `SQLException` that may occur and print the stack trace.
 * 
 * Note: Ensure the database connection details (URL, username, password) and the 
 * table schema (`part`) are correctly configured before running the program.
 * 
 * @author SunilOS
 * @version 1.0
 * @since 2023
 * @see java.sql.Connection
 * @see java.sql.DriverManager
 * @see java.sql.Statement
 * @see java.sql.PreparedStatement
 * @see java.sql.SQLException
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class InsertRecordWithTx {

    public static void main(String args[]) throws Exception {

        Class.forName("com.mysql.jdbc.Driver").newInstance();

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/test", "root", "");

        conn.setAutoCommit(false); // Start Transaction

        Statement stmt = conn.createStatement();

        int i = stmt.executeUpdate("INSERT into part values (5,'Plat','Green',1)");

        System.out.print(i + " Record(s) Updated");

        conn.commit(); // End Transaction

        stmt.close();
        conn.close();
    }

    /**
     * Executes an `INSERT` statement using a `PreparedStatement` to insert a record 
     * into the `part` table, with transaction handling and error management.
     * 
     * @throws Exception if a database access error occurs or other exceptions are thrown.
     */
    public static void testPreparedInsert() throws Exception {

        Class.forName("com.mysql.jdbc.Driver").newInstance();

        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/test", "root", "root");

            conn.setAutoCommit(false); // Start Transaction

            PreparedStatement ps = conn.prepareStatement("INSERT into part values (?,?,?,?)");

            ps.setInt(1, 7);
            ps.setString(2, "Plat2");
            ps.setString(3, "Red");
            ps.setInt(4, 1);
            int recCount = ps.executeUpdate();

            System.out.println("# of Records " + recCount);

            System.out.print(recCount + " Record(s) Updated");

            conn.commit(); // End Transaction
            ps.close();
            conn.close();

        } catch (SQLException e) {
            // Handle SQL exception
            e.printStackTrace();
        }
    }
}
