package in.co.sunrays.jdbc;

import java.sql.*;

/**
 * Makes MySQL database connection and performs Select and Insert operations.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class ConnectToMySQLDB {

	public static void main(String args[]) throws Exception {
		testSelect();
		// testInsert();
	}

	/**
	 * Executes SELECT statement and print records.
	 * 
	 * @throws Exception
	 */

	public static void testSelect() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");

		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/st_test", "root", "root");

		Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT id, name, color from ST_PART");

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

	/**
	 * Executes INSERT statement
	 * 
	 * @throws Exception
	 */
	public static void testInsert() throws Exception {

		Class.forName("com.mysql.jdbc.Driver").newInstance();

		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/test", "root", "root");

		conn.setAutoCommit(false);

		Statement stmt = conn.createStatement();

		int i = stmt
				.executeUpdate("INSERT into part values (5,'plat1','Green',1)");

		System.out.print(i + " Record(s) Updated");

		conn.commit();
		stmt.close();
		conn.close();
	}

	/**
	 * Executes INSERT statement with help of Prepared Statement.
	 * 
	 * @throws Exception
	 */

	public static void testPreparedInsert() throws Exception {

		Class.forName("com.mysql.jdbc.Driver").newInstance();

		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/test", "root", "root");

		conn.setAutoCommit(false);

		PreparedStatement ps = conn
				.prepareStatement("INSERT into part values (?,?,?,?)");

		ps.setInt(1, 7);
		ps.setString(2, "Plat2");
		ps.setString(3, "Red");
		ps.setInt(4, 1);
		int recCount = ps.executeUpdate();

		System.out.println("# of Records" + recCount);

		System.out.print(recCount + " Record(s) Updated");

		conn.commit();
		ps.close();
		conn.close();
	}

}