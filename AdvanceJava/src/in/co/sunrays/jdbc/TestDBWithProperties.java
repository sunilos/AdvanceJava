package in.co.sunrays.jdbc;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * Remove hard coding of Database credentials and other information.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class TestDBWithProperties {

	public static void main(String args[]) throws Exception {
		testSelect();
		// testInsert();
	}

	public static void testSelect() throws Exception {

		ResourceBundle rb = ResourceBundle
				.getBundle("in.co.sunrays.jdbc.system");

		String driverName = rb.getString("database.driver");
		String url = rb.getString("database.url");
		String loginId = rb.getString("database.user");
		String password = rb.getString("database.password");

		Class.forName(driverName);

		Connection conn = DriverManager.getConnection(url, loginId, password);

		conn.setAutoCommit(false);

		Statement stmt = conn.createStatement();

		ResultSet rs = rs = stmt
				.executeQuery("SELECT id, name, color from ST_PART");

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