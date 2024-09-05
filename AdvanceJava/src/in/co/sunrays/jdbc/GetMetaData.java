package in.co.sunrays.jdbc;

import java.sql.*;

/**
 * Demonstrates how to retrieve and print metadata of a SQL statement result using JDBC.
 * 
 * This program connects to a MySQL database, executes a SQL `SELECT` query, and 
 * retrieves metadata about the result set using `ResultSetMetaData`. It prints details 
 * such as the catalog name, table name, and column attributes (label, name, type, size, precision) 
 * for each column in the result set.
 * 
 * Steps:
 * 1. Load the MySQL JDBC driver.
 * 2. Establish a connection to the MySQL database.
 * 3. Execute a SQL `SELECT` statement using `Statement`.
 * 4. Use `ResultSetMetaData` to retrieve and print metadata information of the result set.
 * 5. Handle any SQL exceptions that may occur during the operation.
 * 
 * Note: Ensure the database connection details (URL, username, password) 
 * and the schema (table names, column names) are configured correctly before running the program.
 * 
 * @author SunilOS
 * @version 1.0
 * @since 2023
 * @see java.sql.Connection
 * @see java.sql.DriverManager
 * @see java.sql.Statement
 * @see java.sql.ResultSet
 * @see java.sql.ResultSetMetaData
 * @see java.sql.SQLException
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class GetMetaData {

    public static void main(String args[]) throws Exception {

        Class.forName("com.mysql.jdbc.Driver").newInstance();

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/ST_TEST", "root", "root");

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT id, name, color from ST_PART");

        ResultSetMetaData rsmt = rs.getMetaData();

        System.out.println("Catalog Name : " + rsmt.getCatalogName(1));
        System.out.println("Table Name : " + rsmt.getTableName(1));

        int columnCount = rsmt.getColumnCount();
        System.out.println("Total Columns :" + columnCount);
        System.out.println();

        for (int i = 1; i <= columnCount; i++) {
            System.out.println("Column :" + i);
            System.out.println("Label : " + rsmt.getColumnLabel(i));
            System.out.println("Name : " + rsmt.getColumnName(i));
            System.out.println("Type : " + rsmt.getColumnTypeName(i));
            System.out.println("Size : " + rsmt.getColumnDisplaySize(i));
            System.out.println("Precision : " + rsmt.getPrecision(i));
            System.out.println();
        }

        stmt.close();
        conn.close();
    }
}
