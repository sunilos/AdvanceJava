```java
package in.co.sunrays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This program demonstrates how to alter a table using JDBC (Java Database Connectivity).
 * Specifically, it adds a new column 'DOB' of data type 'date' to an existing table.
 * 
 * Steps:
 * - Load the MySQL JDBC driver.
 * - Establish a connection to the MySQL database.
 * - Execute the SQL query to alter the table.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */
public class AlterTable {
    public static void main(String[] args) {

        try {
            // Step 1: Load the MySQL JDBC driver
            // This is essential for the application to interact with the MySQL database
            Class.forName("com.mysql.jdbc.Driver");

            // Step 2: Establish a connection to the MySQL database
            // It connects to the database named 'st_test' on the local server (localhost)
            // The username is 'root' and the password is an empty string
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/st_test", "root", "");

            // Step 3: Create a statement object to send SQL queries to the database
            Statement stmt = conn.createStatement();

            // Step 4: Write an SQL query to alter the table 'ST_TESTTABLE'
            // This query adds a new column 'DOB' of type 'date'
            String sql = "ALTER TABLE ST_TESTTABLE ADD DOB date";

            // Inform the user that the table is being altered
            System.out.println("Altering table...");

            // Step 5: Execute the SQL update
            // This will execute the above query and add the new column to the table
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            // Step 6: Catch and handle SQL related exceptions
            // Print the error message if there is any issue with SQL or connection
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            // Step 7: Catch and handle exceptions related to the JDBC driver
            // Print the error message if the JDBC driver is not found
            System.out.println(e.getMessage());
        }

        // Step 8: Indicate the success of the table alteration
        // Inform the user that the table was successfully altered
        System.out.println("Table is successfully altered");
    }
}
```

### Explanation:

1. **Class Declaration**:
   - The `AlterTable` class demonstrates how to alter an existing table using JDBC.

2. **Driver Loading**:
   - `Class.forName("com.mysql.jdbc.Driver");` loads the MySQL JDBC driver dynamically. This line ensures that the application can communicate with a MySQL database.

3. **Connection Establishment**:
   - `DriverManager.getConnection("jdbc:mysql://localhost:3306/st_test", "root", "");` establishes a connection to the MySQL database called `st_test` on the local server (`localhost`). The `root` user is used to access the database, with an empty password.

4. **Statement Creation**:
   - `Statement stmt = conn.createStatement();` creates a `Statement` object, which is used to send SQL queries to the database.

5. **SQL Query**:
   - `String sql = "ALTER TABLE ST_TESTTABLE ADD DOB date";` is the SQL command that modifies the structure of the table `ST_TESTTABLE` by adding a new column `DOB` (Date of Birth) of type `date`.

6. **Executing the SQL Query**:
   - `stmt.executeUpdate(sql);` sends the SQL command to the database for execution. This alters the table by adding the new column.

7. **Exception Handling**:
   - The code uses two `catch` blocks to handle exceptions:
     - `SQLException`: Handles any SQL-related errors such as connection issues or incorrect SQL syntax.
     - `ClassNotFoundException`: Handles errors related to the JDBC driver not being found.

8. **Success Message**:
   - After successfully altering the table, the message `"Table is successfully altered"` is printed to indicate the successful completion of the operation.
