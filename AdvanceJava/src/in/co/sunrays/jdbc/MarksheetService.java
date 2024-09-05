package in.co.sunrays.jdbc;

import in.co.sunrays.bean.Marksheet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Provides operations for creating, reading, updating, and deleting Marksheet records.
 * 
 * This service interacts with a MySQL database to manage records in the `ST_STUDENT` table. It uses JDBC 
 * for database connectivity and supports various operations including:
 * - Fetching a Marksheet by roll number
 * - Retrieving a list of all Marksheet records
 * - Getting a merit list based on total marks
 * - Adding a new Marksheet record
 * - Updating an existing Marksheet record
 * - Deleting a Marksheet record
 * 
 * The database connection details are read from a properties file.
 * 
 * @author SunilOS
 * @version 1.0
 * @since 2023
 * @see java.sql.Connection
 * @see java.sql.DriverManager
 * @see java.sql.ResultSet
 * @see java.sql.Statement
 * @see in.co.sunrays.bean.Marksheet
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class MarksheetService {

    // Resource bundle to read database configuration from properties file
    private static ResourceBundle rb = ResourceBundle
            .getBundle("in.co.sunrays.jdbc.system");

    // Static block to load JDBC driver when the class is loaded
    static {
        String driverName = rb.getString("database.driver");
        try {
            // Load JDBC driver
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a Marksheet by roll number.
     * 
     * @param rollNo the roll number of the Marksheet to retrieve
     * @return a Marksheet object corresponding to the given roll number, or null if not found
     * @throws Exception if a database access error occurs
     */
    public Marksheet getMarkSheet(String rollNo) throws Exception {
        Connection conn = DriverManager.getConnection(
                rb.getString("database.url"), rb.getString("database.user"),
                rb.getString("database.password"));

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT rollNo, name, physics, chemistry, maths FROM ST_STUDENT WHERE rollNo= '"
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

        rs.close();
        stmt.close();
        conn.close();

        return bean;
    }

    /**
     * Retrieves a list of all Marksheet records.
     * 
     * @return an ArrayList of Marksheet objects representing all records in the ST_STUDENT table
     * @throws Exception if a database access error occurs
     */
    public ArrayList<Marksheet> getMarksheetList() throws Exception {
        Connection conn = DriverManager.getConnection(
                rb.getString("database.url"), rb.getString("database.user"),
                rb.getString("database.password"));

        Statement stmt = conn.createStatement();

        String sql = "SELECT rollNo, name, physics, chemistry, maths FROM ST_STUDENT ORDER BY rollNo";

        System.out.println(sql);

        ResultSet rs = stmt.executeQuery(sql);

        ArrayList<Marksheet> marksheetList = new ArrayList<>();

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

        rs.close();
        stmt.close();
        conn.close();

        return marksheetList;
    }

    /**
     * Retrieves the top 10 students based on total marks.
     * 
     * @return an ArrayList of Marksheet objects representing the top 10 students based on total marks
     * @throws Exception if a database access error occurs
     */
    public ArrayList<Marksheet> getMeritList() throws Exception {
        Connection conn = DriverManager.getConnection(
                rb.getString("database.url"), rb.getString("database.user"),
                rb.getString("database.password"));

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT rollNo, name, physics, chemistry, maths, "
                + "(physics + chemistry + maths) AS total "
                + "FROM ST_STUDENT ORDER BY total DESC LIMIT 10");

        ArrayList<Marksheet> marksheetList = new ArrayList<>();

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

        rs.close();
        stmt.close();
        conn.close();

        return marksheetList;
    }

    /**
     * Adds a new Marksheet record to the database.
     * 
     * @param bean the Marksheet object containing the details to be inserted
     * @return the number of records inserted
     * @throws Exception if a database access error occurs
     */
    public int addMarkSheet(Marksheet bean) throws Exception {
        Connection conn = DriverManager.getConnection(
                rb.getString("database.url"), rb.getString("database.user"),
                rb.getString("database.password"));

        Statement stmt = conn.createStatement();

        String sql = "INSERT INTO ST_STUDENT (rollNo, name, chemistry, physics, maths) VALUES ('"
                + bean.getRollNo()
                + "', '"
                + bean.getName()
                + "', "
                + bean.getChemistry()
                + ", "
                + bean.getPhysics()
                + ", "
                + bean.getMaths() + ")";

        System.out.println(sql);

        int recordCount = stmt.executeUpdate(sql);

        stmt.close();
        conn.close();

        return recordCount;
    }

    /**
     * Updates an existing Marksheet record.
     * 
     * @param bean the Marksheet object containing updated details
     * @return the number of records updated
     * @throws Exception if a database access error occurs
     */
    public int updateMarkSheet(Marksheet bean) throws Exception {
        Connection conn = DriverManager.getConnection(
                rb.getString("database.url"), rb.getString("database.user"),
                rb.getString("database.password"));

        Statement stmt = conn.createStatement();

        String sql = "UPDATE ST_STUDENT SET physics = " + bean.getPhysics()
                + ", chemistry = " + bean.getChemistry() + ", maths = "
                + bean.getMaths() + ", name = '" + bean.getName()
                + "' WHERE rollNo = '" + bean.getRollNo() + "'";

        System.out.println(sql);

        int recordCount = stmt.executeUpdate(sql);

        stmt.close();
        conn.close();

        return recordCount;
    }

    /**
     * Deletes a Marksheet record by roll number.
     * 
     * @param bean the Marksheet object containing the roll number of the record to be deleted
     * @return the number of records deleted
     * @throws Exception if a database access error occurs
     */
    public int deleteMarkSheet(Marksheet bean) throws Exception {
        Connection conn = DriverManager.getConnection(
                rb.getString("database.url"), rb.getString("database.user"),
                rb.getString("database.password"));

        Statement stmt = conn.createStatement();

        String sql = "DELETE FROM ST_STUDENT WHERE rollNo = '"
                + bean.getRollNo() + "'";

        int recordCount = stmt.executeUpdate(sql);

        stmt.close();
        conn.close();

        return recordCount;
    }
}
