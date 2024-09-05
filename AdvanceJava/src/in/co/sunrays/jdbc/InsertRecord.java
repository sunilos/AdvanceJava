package in.co.sunrays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Demonstrates inserting records into a MySQL table using JDBC.
 * 
 * This class contains two methods for inserting records into a `part` table in a MySQL database:
 * 1. `main`: Uses a `Statement` object to execute a simple SQL `INSERT` statement.
 * 2. `testPreparedInsert`: Uses a `PreparedStatement` to execute a parameterized SQL `INSERT` statement, 
 *    demonstrating a more secure and efficient way to handle SQL operations with dynamic values.
 * 
 * Steps:
 * 1. Load the MySQL JDBC driver.
 * 2. Establish a connection to the MySQL database.
 * 3. Use a `Statement` or `PreparedStatement` to execute `INSERT` operations.
 * 4. Handle transactions and commit changes when using `PreparedStatement`.
 * 5. Ensure that `Statement`, `PreparedStatement`, and `Connection` objects are closed after use.
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

public class InsertRecord {

    public static void main(String args[]) throws Exception {

        Class.forName("com.mysql.jdbc.Driver").newInstance();

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/test", "root", "");

        Statement stmt = conn.createStatement();

        int i = stmt.executeUpdate("INSERT into part values (5,'Plat','Green',1)");

        System.out.print(i + " Record(s) Updated");

        stmt.close();
        conn.close();
    }

    /**
     * Executes an `INSERT` statement using a `PreparedStatement` to insert a record 
     * into the `part` table.
     * 
     * @throws Exception if a database access error occurs.
     */
    public static void testPreparedInsert() throws Exception {

        Class.forName("com.mysql.jdbc.Driver").newInstance();

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/test", "root", "root");

        conn.setAutoCommit(false);

        PreparedStatement ps = conn.prepareStatement("INSERT into part values (?,?,?,?)");

        ps.setInt(1, 7);
        ps.setString(2, "Plat2");
        ps.setString(3, "Red");
        ps.setInt(4, 1);
        int recCount = ps.executeUpdate();

        System.out.println("# of Records " + recCount);

        System.out.print(recCount + " Record(s) Updated");

        conn.commit();
        ps.close();
        conn.close();
    }
}
