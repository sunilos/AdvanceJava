package in.co.sunrays.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

/**
 * Demonstrates how to call a stored function in a MySQL database using JDBC.
 * 
 * This program connects to a MySQL database, calls a stored function using 
 * a `CallableStatement`, and retrieves the result. It registers an output 
 * parameter for the function's return value and displays it.
 * 
 * Steps:
 * 1. Load the MySQL JDBC driver.
 * 2. Establish a connection to the MySQL database.
 * 3. Use a `CallableStatement` to call the stored function.
 * 4. Register the output parameter to capture the returned value.
 * 5. Execute the callable statement and retrieve the result.
 * 
 * The stored function `FuncTest()` is expected to return a value of type VARCHAR, 
 * which is displayed on the console.
 * 
 * @author SunilOS
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

public class CallStoredFunction {

    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.jdbc.Driver");

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/st_test", "root", "root");

        CallableStatement callStmt = conn.prepareCall("{? = CALL FuncTest()}");
        callStmt.registerOutParameter(1, Types.VARCHAR);
        callStmt.execute();

        System.out.println(" Count " + callStmt.getString(1));
    }
}
