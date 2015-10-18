package in.co.sunrays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Alter a table using JDBC.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class AlterTable {
	public static void main(String[] args) {

		try {

			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/st_test", "root", "");

			Statement stmt = conn.createStatement();

			String sql = "ALTER TABLE  ST_TESTTABLE ADD DOB date ";

			System.out.println("Altering table...");

			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Table is successfuly Creatred");

	}
}
