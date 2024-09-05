package in.co.sunrays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Demonstrates how to delete (drop) a table in a MySQL database using JDBC.
 * 
 * This program connects to a MySQL database and deletes the table `ST_TESTTABLE` 
 * using the `DROP TABLE` SQL command. 
 * 
 * Steps:
 * 1. Load the MySQL JDBC driver.
 * 2. Establish a connection to the MySQL database.
 * 3. Use a `Statement` object to execute the SQL `DROP TABLE` command.
 * 4. Handle any SQL exceptions that may occur during the operation.
 * 
 * Note: Ensure the table exists in the specified database, and the connection 
 * details (URL, username, password) are correctly configured.
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

public class DropTable {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/st_test", "root", "");

            Statement stmt = conn.createStatement();

            String sql = "DROP TABLE ST_TESTTABLE";

            System.out.println("Deleting table...");

            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Table is successfully Deleted");
    }
}
