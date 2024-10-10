```java
package in.co.sunrays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * The InsertRecord class demonstrates how to insert a record into a database table using JDBC.
 * It shows two methods: one using a Statement for a direct SQL insert and another using a 
 * PreparedStatement for a parameterized insert, which is safer and more efficient for repeated queries.
 * 
 * @author SunilOS
 * @version 1.0
 * @since 2023-09-26
 * @see java.sql.Connection
 * @see java.sql.Statement
 * @see java.sql.PreparedStatement
 */
public class InsertRecord {

    /**
     * The main method is the entry point of the program.
     * It connects to the MySQL database and executes an SQL INSERT operation using Statement.
     * 
     * @param args Unused.
     * @throws Exception If there is any issue in loading the driver or connecting to the database.
     */
    public static void main(String args[]) throws Exception {

        // Load the MySQL JDBC driver
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        // Establish a connection to the MySQL database
        // Database: test, Username: root, Password: (empty)
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");

        // Create a statement object to execute SQL queries
        Statement stmt = conn.createStatement();

        // Execute an SQL INSERT query to add a record to the part table
        int i = stmt.executeUpdate("INSERT INTO part VALUES (5, 'Plat', 'Green', 1)");

        // Print how many records were updated
        System.out.print(i + " Record(s) Updated");

        // Close the statement and connection
        stmt.close();
        conn.close();
    }

    /**
     * Executes an INSERT statement using a Prepared Statement.
     * This method demonstrates how to safely insert a record into the part table using parameterized queries.
     * 
     * @throws Exception If there is any issue in loading the driver or connecting to the database.
     */
    public static void testPreparedInsert() throws Exception {

        // Load the MySQL JDBC driver
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        // Establish a connection to the MySQL database
        // Database: test, Username: root, Password: root
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "root");

        // Disable auto-commit mode for transaction management
        conn.setAutoCommit(false);

        // Create a PreparedStatement for executing parameterized SQL queries
        PreparedStatement ps = conn.prepareStatement("INSERT INTO part VALUES (?, ?, ?, ?)");

        // Set parameters for the prepared statement
        ps.setInt(1, 7);           // Set first parameter (id)
        ps.setString(2, "Plat2"); // Set second parameter (name)
        ps.setString(3, "Red");   // Set third parameter (color)
        ps.setInt(4, 1);          // Set fourth parameter (quantity)

        // Execute the update and get the number of records affected
        int recCount = ps.executeUpdate();

        // Print the number of records inserted
        System.out.println("# of Records: " + recCount);
        System.out.print(recCount + " Record(s) Updated");

        // Commit the transaction
        conn.commit();

        // Close the prepared statement and connection
        ps.close();
        conn.close();
    }
}
```

### Explanation of the Program:

1. **Class Overview**:
   - The `InsertRecord` class demonstrates how to insert records into a MySQL database table using JDBC.
   - It includes two methods: one using a `Statement` for direct SQL execution and another using a `PreparedStatement` for safer parameterized queries.

2. **Main Method (`main`)**:
   - This method is the entry point of the program where the connection to the MySQL database is established.
   - It executes an SQL INSERT query to add a record to the `part` table using a `Statement`.

3. **Loading the JDBC Driver**:
   - `Class.forName("com.mysql.jdbc.Driver").newInstance();` loads the MySQL JDBC driver for connecting to the database.

4. **Establishing Connection**:
   - `DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");` establishes a connection to the `test` database using the specified credentials.

5. **Executing SQL Insert (`stmt.executeUpdate`)**:
   - The SQL query `INSERT INTO part VALUES (5, 'Plat', 'Green', 1)` is executed, inserting a new record into the `part` table.
   - The method returns the number of records affected by the operation, which is printed to the console.

6. **Closing Resources**:
   - The `Statement` and `Connection` objects are closed to free up database resources.

7. **Parameterized Insert Method (`testPreparedInsert`)**:
   - This method demonstrates using a `PreparedStatement`, which allows parameterized queries that can be reused and provide security against SQL injection attacks.
   - Auto-commit mode is disabled to manage transactions manually, allowing for rollback if needed.

8. **Setting Parameters**:
   - Parameters for the SQL INSERT statement are set using methods like `ps.setInt()` and `ps.setString()`, which help in safely inserting user-supplied data.

9. **Executing and Committing**:
   - The `executeUpdate()` method is called on the `PreparedStatement` to perform the insert operation.
   - The transaction is committed using `conn.commit()` to save the changes.

10. **Closing Resources**:
    - Finally, the `PreparedStatement` and `Connection` objects are closed to ensure that resources are properly released.
