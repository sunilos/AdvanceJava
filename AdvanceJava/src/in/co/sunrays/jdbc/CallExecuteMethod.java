package in.co.sunrays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Gets Records from My SQL Database.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class CallExecuteMethod {

	public static void main(String args[]) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");

		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/st_java", "root", "");

		Statement stmt = conn.createStatement();

		String sql = "SELECT id, name, color from ST_PART";

		boolean status = stmt.execute(sql);

		if (!status) {
			System.out.println("Error : SELECT did not executed !");
		}

		ResultSet rs = stmt.getResultSet();

		System.out.println("ID\tName\tColor");
		System.out.println("--\t----\t-----");

		while (rs.next()) {

			System.out.print(rs.getString(1));
			System.out.print("\t" + rs.getString(2));
			System.out.println("\t" + rs.getString("color"));
		}

		stmt.close();
		conn.close();

	}

}