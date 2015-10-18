package in.co.sunrays.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

/*
 * copyright (c) sunRays Technologies Indore
 * @author: sunRays Developer
 * @url : www.sunrays.co.in
 *
 */

/**
 * Tests Stored Procedure call.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class CallStoredProcedure {

	/**
	 * @param args
	 */
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
