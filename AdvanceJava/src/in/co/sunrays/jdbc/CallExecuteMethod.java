package in.co.sunrays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Demonstrates how to retrieve records from a MySQL database using JDBC.
 * 
 * This program connects to a MySQL database, executes a SQL SELECT query, 
 * and retrieves records from the specified table. It checks whether the 
 * query was successfully executed and displays the results if available.
 * 
 * Steps:
 * 1. Load the MySQL JDBC driver.
 * 2. Establish a connection to the MySQL database.
 * 3. Create and execute a SQL SELECT statement.
 * 4. Process and display the retrieved records.
 * 5. Handle possible exceptions and ensure the resources are closed properly.
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

public class CallExecuteMethod {

    public static void main(String args[]) throws Exception {

        Class.forName("com.mysql.jdbc.Driver");

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/st_java", "root", "");

        Statement stmt = conn.createStatement();

        String sql = "SELECT id, name, color from ST_PART";

        boolean status = stmt.execute(sql);

        if (!status) {
            System.out.println("Error: SELECT did not execute!");
        }

        ResultSet rs = stmt.getResultSet();

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
}
