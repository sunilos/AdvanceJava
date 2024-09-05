package in.co.sunrays.jdbc;

import java.sql.*;

/**
 * Demonstrates how to connect to a Microsoft Access database using JDBC.
 * 
 * This program connects to an MS Access database, retrieves records from 
 * the `Employee` table, and displays the results. It uses the 
 * `JdbcOdbcDriver` for establishing the connection.
 * 
 * Steps:
 * 1. Load the JDBC-ODBC bridge driver for MS Access.
 * 2. Establish a connection to the MS Access database.
 * 3. Execute a SQL SELECT query to retrieve data from the `Employee` table.
 * 4. Process and display the results from the query.
 * 5. Close the database resources after the operation is complete.
 * 
 * The program assumes the MS Access database DSN (Data Source Name) is 
 * configured as "BATCH06".
 * 
 * @author SunilOS
 * @version 1.0
 * @since 2023
 * @see java.sql.Connection
 * @see java.sql.DriverManager
 * @see java.sql.Statement
 * @see java.sql.ResultSet
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class ConnectToMSAccessDB {

    public static void main(String args[]) throws Exception {

        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

        Connection conn = DriverManager.getConnection("jdbc:odbc:BATCH06", "", "");

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT id, firstName, lastName from Employee");

        System.out.println("ID\tName\t\tLast Name");
        System.out.println("--\t----\t\t---------");

        while (rs.next()) {
            System.out.print(rs.getString(1));
            System.out.print("\t" + rs.getString("firstName"));
            System.out.println("\t\t" + rs.getString(3));
        }

        stmt.close();
        conn.close();
    }
}
