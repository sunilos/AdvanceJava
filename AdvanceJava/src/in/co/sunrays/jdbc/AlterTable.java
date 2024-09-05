package in.co.sunrays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Demonstrates how to alter a table by adding a new column using JDBC.
 * 
 * This program connects to a MySQL database, alters an existing table by 
 * adding a new column, and handles potential exceptions that may occur 
 * during the process.
 * 
 * Steps:
 * 1. Load the MySQL JDBC driver.
 * 2. Establish a connection to the database.
 * 3. Create a SQL statement to alter the table.
 * 4. Execute the statement to update the table structure.
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

public class AlterTable {
    public static void main(String[] args) {

        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/st_test", "root", "");

            Statement stmt = conn.createStatement();

            String sql = "ALTER TABLE  ST_TESTTABLE ADD DOB date ";

            System.out.println("Altering table...");

            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Table is successfully altered");

    }
}
