package in.co.sunrays.jdbc;

import java.sql.*;

/**
 * Tests MS Access Database connectivity.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class ConnectToMSAccessDB {

	public static void main(String args[]) throws Exception {

		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

		Connection conn = DriverManager.getConnection("jdbc:odbc:BATCH06", "",
				"");

		Statement stmt = conn.createStatement();

		ResultSet rs = null;

		rs = stmt.executeQuery("SELECT id,firstName,lastname from Employee");

		System.out.println("ID\tName\t\tLast Color");
		System.out.println("--\t----\t\t-----");

		while (rs.next()) {

			System.out.print(rs.getString(1));
			System.out.print("\t" + rs.getString("firstName"));
			System.out.println("\t\t" + rs.getString(3));
		}

		stmt.close();
		conn.close();

	}

}