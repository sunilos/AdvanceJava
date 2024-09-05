package in.co.sunrays.jdbc;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * Demonstrates how to use external properties to configure database credentials and other settings.
 * 
 * This class retrieves database connection details from a properties file using a `ResourceBundle`. It then
 * establishes a connection to a MySQL database and performs a SELECT query to fetch records from the `ST_PART` table.
 * 
 * The database credentials and connection details are read from the properties file, removing the need for hardcoded values
 * in the source code.
 * 
 * @author SunilOS
 * @version 1.0
 * @since 2023
 * @see java.sql.Connection
 * @see java.sql.DriverManager
 * @see java.sql.ResultSet
 * @see java.sql.Statement
 * @see java.util.ResourceBundle
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class TestDBWithProperties {

    public static void main(String args[]) throws Exception {
        testSelect();
        // testInsert();
    }

    /**
     * Executes a SELECT query to retrieve records from the `ST_PART` table.
     * 
     * This method demonstrates how to use a `ResourceBundle` to load database connection details from a properties file. 
     * It then establishes a database connection, performs a SELECT query, and prints the results.
     * 
     * @throws Exception if an error occurs while establishing the connection, executing the query, or processing the results
     */
    public static void testSelect() throws Exception {
        ResourceBundle rb = ResourceBundle.getBundle("in.co.sunrays.jdbc.system");

        String driverName = rb.getString("database.driver");
        String url = rb.getString("database.url");
        String loginId = rb.getString("database.user");
        String password = rb.getString("database.password");

        Class.forName(driverName);

        Connection conn = DriverManager.getConnection(url, loginId, password);

        conn.setAutoCommit(false);

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT id, name, color FROM ST_PART");

        System.out.println("ID\tName\tColor");
        System.out.println("--\t----\t-----");

        while (rs.next()) {
            System.out.print(rs.getString(1));
            System.out.print("\t" + rs.getString(2));
            System.out.println("\t" + rs.getString("color"));
        }

        conn.commit();
        stmt.close();
        conn.close();
    }
}
