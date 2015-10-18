package in.co.sunrays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Example SQLException handling.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class HandleSQLException {

	public static void main(String args[]) throws Exception {

		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = null;
		Statement stmt = null;

		try {

			conn = DriverManager.getConnection("jdbc:mysql://localhost/test",
					"root", "");

			stmt = conn.createStatement();

			int i = stmt
					.executeUpdate("INSERT into Person (name,age) values ('Ram',25)");

			System.out.print(i + " Record(s) Updated");

		} catch (SQLException e) {// Handle exception
			System.out.println("Error: " + e.getMessage());
			System.out.println("SQL State: " + e.getSQLState());
			System.out.println("Error Code: " + e.getErrorCode());
			System.out.println("Cause: " + e.getCause());
			e.printStackTrace();

		} finally {// Close resources
			stmt.close();
			conn.close();
		}
	}

}