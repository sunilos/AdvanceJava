package in.co.sunrays.jdbc;

import in.co.sunrays.bean.Marksheet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Service provide Marksheet create, read, update, and delete operations.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 * 
 * */

public class MarksheetService {

	// Resource bundle will read data from .properties file
	private static ResourceBundle rb = ResourceBundle
			.getBundle("in.co.sunrays.jdbc.system");

	// Static block will be executed when Class is loaded in memory.
	static {

		String driverName = rb.getString("database.driver");

		try {
			// Load Driver
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Gets a Marksheet by Roll Number
	 * 
	 * @param rollNo
	 * @return
	 * @throws Exception
	 */
	public Marksheet getMarkSheet(String rollNo) throws Exception {

		Connection conn = DriverManager.getConnection(
				rb.getString("database.url"), rb.getString("database.user"),
				rb.getString("database.password"));

		Statement stmt = conn.createStatement();

		ResultSet rs = stmt
				.executeQuery("select rollNo,name,physics,chemistry,maths from ST_STUDENT where rollNo= '"
						+ rollNo + "'");

		Marksheet bean = null;

		if (rs.next()) {

			bean = new Marksheet();
			bean.setRollNo(rollNo);
			bean.setName(rs.getString(2));
			bean.setPhysics(rs.getInt(3));
			bean.setChemistry(rs.getInt(4));
			bean.setMaths(rs.getInt(5));
		}

		rs.close(); // memory will be cleaned
		stmt.close(); // Cursor will be closed
		conn.close(); // Connection will be closed.

		return bean;
	}

	/**
	 * Gets list of Marksheet
	 * 
	 * @return
	 * @throws Exception
	 */

	public ArrayList getMarksheetList() throws Exception {

		Connection conn = DriverManager.getConnection(
				rb.getString("database.url"), rb.getString("database.user"),
				rb.getString("database.password"));

		Statement stmt = conn.createStatement();

		String sql = "select rollNo,name,physics,chemistry,maths from ST_STUDENT order by rollNo";

		System.out.println(sql);

		ResultSet rs = stmt.executeQuery(sql);

		ArrayList marksheetList = new ArrayList();

		Marksheet bean = null;

		while (rs.next()) {

			bean = new Marksheet();
			bean.setRollNo(rs.getString(1));
			bean.setName(rs.getString(2));
			bean.setPhysics(rs.getInt(3));
			bean.setChemistry(rs.getInt(4));
			bean.setMaths(rs.getInt(5));

			marksheetList.add(bean);
		}

		return marksheetList;
	}

	/**
	 * Gets merit list
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList getMeritList() throws Exception {

		Connection conn = DriverManager.getConnection(
				rb.getString("database.url"), rb.getString("database.user"),
				rb.getString("database.password"));

		Statement stmt = conn.createStatement();

		ResultSet rs = stmt
				.executeQuery("select rollNo,name,physics,chemistry,maths, (physics+chemistry+ maths) as total  "
						+ "from ST_STUDENT limit 10 " + "order by total desc");

		ArrayList marksheetList = new ArrayList();

		Marksheet bean = null;

		int count = 0;

		while (rs.next()) {

			bean = new Marksheet();
			bean.setRollNo(rs.getString(1));
			bean.setName(rs.getString(2));
			bean.setPhysics(rs.getInt(3));
			bean.setChemistry(rs.getInt(4));
			bean.setMaths(rs.getInt(5));

			marksheetList.add(bean);

			count++;
			if (count == 10) {
				break;
			}

		}

		return marksheetList;
	}

	/**
	 * Adds a Marksheet from Marksheet bean
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */

	public int addMarkSheet(Marksheet bean) throws Exception {

		Connection conn = DriverManager.getConnection(
				rb.getString("database.url"), rb.getString("database.user"),
				rb.getString("database.password"));

		Statement stmt = conn.createStatement();

		String sql = "insert into ST_STUDENT (rollNo,name, chemistry,physics,maths ) value ( '"
				+ bean.getRollNo()
				+ "', '"
				+ bean.getName()
				+ "' , "
				+ bean.getChemistry()
				+ ", "
				+ bean.getPhysics()
				+ " , "
				+ bean.getMaths() + ") ";

		System.out.println(sql);

		int recordCount = stmt.executeUpdate(sql);

		stmt.close(); // Cursor will be closed
		conn.close(); // Connection will be closed.

		return recordCount;
	}

	/**
	 * Updates a marksheet
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public int updateMarkSheet(Marksheet bean) throws Exception {

		Connection conn = DriverManager.getConnection(
				rb.getString("database.url"), rb.getString("database.user"),
				rb.getString("database.password"));

		Statement stmt = conn.createStatement();

		String sql = "update ST_STUDENT set physics = " + bean.getPhysics()
				+ ", chemistry=" + bean.getChemistry() + ", maths="
				+ bean.getMaths() + ", name='" + bean.getName()
				+ "' where rollNo = '" + bean.getRollNo() + "'";

		System.out.println(sql);

		int recordCount = stmt.executeUpdate(sql);

		stmt.close(); // Cursor will be closed
		conn.close(); // Connection will be closed.

		return recordCount;
	}

	/**
	 * Deletes Marksheet
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * 
	 */
	public int deleteMarkSheet(Marksheet bean) throws Exception {

		Connection conn = DriverManager.getConnection(
				rb.getString("database.url"), rb.getString("database.user"),
				rb.getString("database.password"));

		Statement stmt = conn.createStatement();

		String sql = "delete from ST_STUDENT where rollNo= '"
				+ bean.getRollNo() + "'";

		int recordCount = stmt.executeUpdate(sql);

		stmt.close(); // Cursor will be closed
		conn.close(); // Connection will be closed.

		return recordCount;
	}

}
