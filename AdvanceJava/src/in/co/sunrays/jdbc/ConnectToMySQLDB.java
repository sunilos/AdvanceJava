package in.co.sunrays.jdbc;

import java.sql.*;

/**
 * Demonstrates how to connect to a MySQL database and perform SELECT and INSERT operations using JDBC.
 * 
 * This program establishes a connection to a MySQL database and provides methods 
 * to perform SELECT queries, basic INSERT operations, and INSERT operations using 
 * a `PreparedStatement`. It supports both auto-commit and manual transaction management.
 * 
 * Steps:
 * 1. Load the MySQL JDBC driver.
 * 2. Establish a connection to the MySQL database.
 * 3. Perform SQL operations such as SELECT and INSERT.
 * 4. Use `PreparedStatement` for parameterized INSERT operations.
 * 5. Handle transactions and commit changes where necessary.
 * 
 * Note: The database connection parameters (URL, username, password) and the database 
 * schema (e.g., table names and columns) should be adjusted as per the actual setup.
 * 
 * @author SunilOS
 * @version 1.0
 * @since 2023
 * @see java.sql.Connection
 * @see java.sql.DriverManager
 * @see java.sql.Statement
 * @see java.sql.PreparedStatement
 * @see java.sql.ResultSet
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class ConnectToMySQLDB {

    public static void main(String args[]) throws Exception {
        testSelect();
        // testInsert();
    }

    /**
     * Executes a SELECT statement and prints the retrieved records.
     * 
     * This method connects to the `st_test` database, executes a SELECT query 
     * on the `ST_PART` table, and prints the resulting records with columns `id`, 
     * `name`, and `color`.
     * 
     * @throws Exception if a database access error occurs
     */
    public static void testSelect() throws Exception {

        Class.forName("com.mysql.jdbc.Driver");

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/st_test", "root", "root");

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT id, name, color from ST_PART");

        System.out.println("ID\tName\tColor");
        System.out.println("--\t----\t-----");

        while (rs.next()) {
            System.out.print(rs.getString(1));
            System.out.print("\t" + rs.getString(2));
            System.out.println("\t" + rs.getString("color"));
        }

        stmt.close();
        conn.close();
    }

    /**
     * Executes an INSERT statement to add a record to the `part` table.
     * 
     * This method inserts a new record into the `part` table with hardcoded values. 
     * The operation is executed within a manual transaction that is explicitly committed.
     * 
     * @throws Exception if a database access error occurs
     */
    public static void testInsert() throws Exception {

        Class.forName("com.mysql.jdbc.Driver").newInstance();

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/test", "root", "root");

        conn.setAutoCommit(false);

        Statement stmt = conn.createStatement();

        int i = stmt.executeUpdate("INSERT into part values (5,'plat1','Green',1)");

        System.out.print(i + " Record(s) Updated");

        conn.commit();
        stmt.close();
        conn.close();
    }

    /**
     * Executes an INSERT statement using a `PreparedStatement` to add a record to the `part` table.
     * 
     * This method inserts a new record into the `part` table using a parameterized query, 
     * allowing for more flexible and secure SQL operations. The operation is executed 
     * within a manual transaction.
     * 
     * @throws Exception if a database access error occurs
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

        System.out.println("# of Records: " + recCount);

        System.out.print(recCount + " Record(s) Updated");

        conn.commit();
        ps.close();
        conn.close();
    }
}
