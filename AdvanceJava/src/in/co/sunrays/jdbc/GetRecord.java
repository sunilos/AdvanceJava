package in.co.sunrays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Retrieves and prints records from a MySQL database using JDBC.
 * 
 * This program connects to a MySQL database, executes a SQL `SELECT` query to retrieve 
 * records from the `ST_PART` table, and prints the results to the console. It fetches 
 * columns `id`, `name`, and `color` and displays them in a tabular format.
 * 
 * Steps:
 * 1. Load the MySQL JDBC driver.
 * 2. Establish a connection to the MySQL database.
 * 3. Execute a SQL `SELECT` statement to fetch data from the `ST_PART` table.
 * 4. Iterate through the `ResultSet` and print the data.
 * 5. Close the `Statement` and `Connection` objects to free resources.
 * 
 * Note: Ensure the database connection details (URL, username, password) and the 
 * table schema (`ST_PART`) are correctly configured before running the program.
 * 
 * @author SunilOS
 * @version 1.0
 * @since 2023
 * @see java.sql.Connection
 * @see java.sql.DriverManager
 * @see java.sql.Statement
 * @see java.sql.ResultSet
 * @see java.sql.SQLException
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class GetRecord {

    public static void main(String args[]) throws Exception {

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
}
