package in.co.sunrays.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

/**
 * Calls a Stored Function call.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class CallStoredFunction {

	/**
	 * @param args
	 */
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
