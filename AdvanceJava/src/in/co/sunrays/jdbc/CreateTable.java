package in.co.sunrays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Demonstrates how to create a table in a MySQL database using JDBC.
 * 
 * This program connects to a MySQL database and creates a table named `ST_TESTTABLE` 
 * with an `id` column (integer, not null, primary key) and a `name` column (varchar 100).
 * 
 * Steps:
 * 1. Load the MySQL JDBC driver.
 * 2. Establish a connection to the MySQL database.
 * 3. Use a `Statement` object to execute the SQL `CREATE TABLE` command.
 * 4. Handle any SQL exceptions that may occur during the operation.
 * 
 * Note: Ensure the MySQL database connection details (URL, username, password) 
 * are properly configured before running the program.
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

public class CreateTable {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/st_test", "root", "");

            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE ST_TESTTABLE "
                    + "(id INTEGER not NULL, name VARCHAR(100), "
                    + " PRIMARY KEY ( id ))";

            System.out.println("Creating table...");

            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Table is successfully Created");
    }
}
