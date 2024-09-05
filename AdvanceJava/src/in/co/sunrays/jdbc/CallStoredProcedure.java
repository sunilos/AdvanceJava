package in.co.sunrays.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

/**
 * Demonstrates how to call a stored procedure in a MySQL database using JDBC.
 * 
 * This program establishes a connection to a MySQL database, calls a stored 
 * procedure using a `CallableStatement`, and retrieves the result via an 
 * output parameter. It handles a stored procedure that returns an integer 
 * value.
 * 
 * Steps:
 * 1. Load the MySQL JDBC driver.
 * 2. Establish a connection to the MySQL database.
 * 3. Use a `CallableStatement` to call the stored procedure.
 * 4. Register the output parameter to capture the integer result.
 * 5. Execute the callable statement and retrieve the output.
 * 
 * The stored procedure `ProTest(?)` is expected to return an integer value, 
 * which is printed to the console.
 * 
 * @author SunRays Developer
 * @version 1.0
 * @since 2023
 * @see java.sql.Connection
 * @see java.sql.DriverManager
 * @see java.sql.CallableStatement
 * @see java.sql.Types
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class CallStoredProcedure {

    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.jdbc.Driver").newInstance();

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/ST_TEST", "root", "root");

        CallableStatement callStmt = conn.prepareCall("{CALL ProTest(?)}");

        callStmt.registerOutParameter(1, Types.INTEGER);

        callStmt.execute();

        System.out.println(" Count " + callStmt.getInt(1));
    }
}
