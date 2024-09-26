```java
package in.co.sunrays.jdbc;

import in.co.sunrays.bean.Marksheet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Service class to provide operations for creating, reading, updating, and deleting Marksheet records.
 * This class interacts with the database to perform CRUD (Create, Read, Update, Delete) operations 
 * on the Marksheet data.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */
public class MarksheetService {

    // Resource bundle to read database configuration from properties file
    private static ResourceBundle rb = ResourceBundle.getBundle("in.co.sunrays.jdbc.system");

    // Static block executed when the class is loaded into memory, used to load the database driver
    static {
        String driverName = rb.getString("database.driver");
        try {
            // Load JDBC driver for the database
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Print stack trace if driver loading fails
        }
    }

    /**
     * Retrieves a Marksheet by the given roll number.
     * 
     * @param rollNo The roll number of the student whose marksheet is to be retrieved.
     * @return Marksheet object containing the student's details, or null if not found.
     * @throws Exception If any error occurs during database operations.
     */
    public Marksheet getMarkSheet(String rollNo) throws Exception {
        // Establish connection to the database
        Connection conn = DriverManager.getConnection(
                rb.getString("database.url"), rb.getString("database.user"),
                rb.getString("database.password"));

        Statement stmt = conn.createStatement();

        // Execute SQL query to fetch the marksheet details for the given roll number
        ResultSet rs = stmt.executeQuery("SELECT rollNo, name, physics, chemistry, maths FROM ST_STUDENT WHERE rollNo = '" + rollNo + "'");

        Marksheet bean = null;

        // Check if a record was found and populate the Marksheet bean
        if (rs.next()) {
            bean = new Marksheet();
            bean.setRollNo(rollNo);
            bean.setName(rs.getString(2));
            bean.setPhysics(rs.getInt(3));
            bean.setChemistry(rs.getInt(4));
            bean.setMaths(rs.getInt(5));
        }

        // Clean up resources
        rs.close(); // Close ResultSet
        stmt.close(); // Close Statement
        conn.close(); // Close Connection

        return bean; // Return the populated Marksheet bean or null
    }

    /**
     * Retrieves a list of all Marksheet records.
     * 
     * @return ArrayList of Marksheet objects containing details of all students.
     * @throws Exception If any error occurs during database operations.
     */
    public ArrayList<Marksheet> getMarksheetList() throws Exception {
        // Establish connection to the database
        Connection conn = DriverManager.getConnection(
                rb.getString("database.url"), rb.getString("database.user"),
                rb.getString("database.password"));

        Statement stmt = conn.createStatement();

        String sql = "SELECT rollNo, name, physics, chemistry, maths FROM ST_STUDENT ORDER BY rollNo";

        System.out.println(sql); // Log the SQL query for debugging

        ResultSet rs = stmt.executeQuery(sql);

        ArrayList<Marksheet> marksheetList = new ArrayList<>(); // List to hold the Marksheet objects
        Marksheet bean;

        // Iterate through the result set and populate the list
        while (rs.next()) {
            bean = new Marksheet();
            bean.setRollNo(rs.getString(1));
            bean.setName(rs.getString(2));
            bean.setPhysics(rs.getInt(3));
            bean.setChemistry(rs.getInt(4));
            bean.setMaths(rs.getInt(5));
            marksheetList.add(bean); // Add the populated Marksheet to the list
        }

        return marksheetList; // Return the list of Marksheet objects
    }

    /**
     * Retrieves a merit list of top 10 students based on total marks.
     * 
     * @return ArrayList of Marksheet objects containing details of top students.
     * @throws Exception If any error occurs during database operations.
     */
    public ArrayList<Marksheet> getMeritList() throws Exception {
        // Establish connection to the database
        Connection conn = DriverManager.getConnection(
                rb.getString("database.url"), rb.getString("database.user"),
                rb.getString("database.password"));

        Statement stmt = conn.createStatement();

        // SQL query to fetch top 10 students based on total marks
        ResultSet rs = stmt.executeQuery("SELECT rollNo, name, physics, chemistry, maths, (physics + chemistry + maths) AS total FROM ST_STUDENT ORDER BY total DESC LIMIT 10");

        ArrayList<Marksheet> marksheetList = new ArrayList<>(); // List to hold the Marksheet objects
        Marksheet bean;

        int count = 0; // Counter to limit the number of records

        // Iterate through the result set and populate the list
        while (rs.next()) {
            bean = new Marksheet();
            bean.setRollNo(rs.getString(1));
            bean.setName(rs.getString(2));
            bean.setPhysics(rs.getInt(3));
            bean.setChemistry(rs.getInt(4));
            bean.setMaths(rs.getInt(5));
            marksheetList.add(bean); // Add the populated Marksheet to the list
            count++;
            if (count == 10) { // Limit to 10 records
                break;
            }
        }

        return marksheetList; // Return the list of top Marksheet objects
    }

    /**
     * Adds a new Marksheet record to the database.
     * 
     * @param bean The Marksheet object containing details of the student to be added.
     * @return The number of records updated (should be 1 for successful insert).
     * @throws Exception If any error occurs during database operations.
     */
    public int addMarkSheet(Marksheet bean) throws Exception {
        // Establish connection to the database
        Connection conn = DriverManager.getConnection(
                rb.getString("database.url"), rb.getString("database.user"),
                rb.getString("database.password"));

        Statement stmt = conn.createStatement();

        // SQL query to insert a new Marksheet record
        String sql = "INSERT INTO ST_STUDENT (rollNo, name, chemistry, physics, maths) VALUES ('"
                + bean.getRollNo() + "', '"
                + bean.getName() + "', "
                + bean.getChemistry() + ", "
                + bean.getPhysics() + ", "
                + bean.getMaths() + ")";

        System.out.println(sql); // Log the SQL query for debugging

        int recordCount = stmt.executeUpdate(sql); // Execute the insert operation

        // Clean up resources
        stmt.close(); // Close Statement
        conn.close(); // Close Connection

        return recordCount; // Return the number of records updated
    }

    /**
     * Updates an existing Marksheet record in the database.
     * 
     * @param bean The Marksheet object containing updated details of the student.
     * @return The number of records updated (should be 1 for successful update).
     * @throws Exception If any error occurs during database operations.
     */
    public int updateMarkSheet(Marksheet bean) throws Exception {
        // Establish connection to the database
        Connection conn = DriverManager.getConnection(
                rb.getString("database.url"), rb.getString("database.user"),
                rb.getString("database.password"));

        Statement stmt = conn.createStatement();

        // SQL query to update an existing Marksheet record
        String sql = "UPDATE ST_STUDENT SET physics = " + bean.getPhysics()
                + ", chemistry = " + bean.getChemistry()
                + ", maths = " + bean.getMaths()
                + ", name = '" + bean.getName() + "' WHERE rollNo = '" + bean.getRollNo() + "'";

        System.out.println(sql); // Log the SQL query for debugging

        int recordCount = stmt.executeUpdate(sql); // Execute the update operation

        // Clean up resources
        stmt.close(); // Close Statement
        conn.close(); // Close Connection

        return recordCount; // Return the number of records updated
    }

    /**
     * Deletes a Marksheet record from the database.
     * 
     * @param bean The Marksheet object representing the student to be deleted.
     * @return The number of records deleted (should be 1 for successful deletion).
     * @throws Exception If any error occurs during database operations.
     */
    public int deleteMarkSheet(Marksheet bean) throws Exception {
        // Establish connection to the database
        Connection conn = DriverManager.getConnection(
                rb.getString("database.url"), rb.getString("database.user"),
                rb.getString("database.password"));

        Statement stmt = conn.createStatement();

        // SQL query to delete a Marksheet record
        String sql = "DELETE FROM ST_STUDENT WHERE rollNo = '" + bean.getRollNo() + "'";

        int recordCount = stmt.executeUpdate(sql); // Execute the delete operation

        // Clean up resources
        stmt.close(); // Close Statement
        conn.close(); // Close Connection

        return recordCount; // Return the number of records deleted
    }
}
```

### Explanation of Changes:
1. **Javadoc Comments

**: Added for each public method to explain its purpose, parameters, return type, and exceptions thrown.
2. **Inline Comments**: Included explanations throughout the code to clarify what each part does, especially around SQL queries and resource management.
3. **Generic ArrayList**: Specified `ArrayList<Marksheet>` instead of just `ArrayList` to enforce type safety and improve code readability.
4. **Consistency in SQL Logging**: Retained the logging of SQL queries for debugging, making it easier to track what operations are performed on the database.
