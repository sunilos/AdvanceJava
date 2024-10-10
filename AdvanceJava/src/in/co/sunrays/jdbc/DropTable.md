```java
package in.co.sunrays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The DropTable class demonstrates how to delete (drop) a table using JDBC (Java Database Connectivity).
 * It connects to a MySQL database and executes a SQL statement to drop an existing table.
 * 
 * The table being dropped is named ST_TESTTABLE.
 * 
 * @author SunilOS
 * @version 1.0
 * @since 2023-09-26
 * @see java.sql.Connection
 * @see java.sql.Statement
 */
public class DropTable {

    /**
     * The main method is the entry point of the program.
     * It connects to the MySQL database, deletes a table, and handles any exceptions that might occur.
     * 
     * @param args Unused.
     */
    public static void main(String[] args) {

        try {

            // Load and register the JDBC driver for MySQL database.
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection to the MySQL database.
            // The URL specifies the database location, username is "root" and password is an empty string.
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/st_test", "root", "");

            // Create a statement object to execute SQL queries.
            Statement stmt = conn.createStatement();

            // SQL query to drop (delete) the table ST_TESTTABLE.
            String sql = "DROP TABLE ST_TESTTABLE";

            // Inform the user that the table deletion process has started.
            System.out.println("Deleting table...");

            // Execute the SQL query to drop the table.
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            // Print SQL-specific error messages if something goes wrong.
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            // Handle exception if the JDBC driver class is not found.
            System.out.println(e.getMessage());
        }

        // Confirm to the user that the table has been successfully deleted.
        System.out.println("Table is successfully deleted.");
    }
}
```

### Explanation of the Program:
1. **JDBC Driver Load (`Class.forName()`)**:
   - This line loads the JDBC driver for MySQL. This is required for the program to interact with the MySQL database.

2. **Database Connection (`DriverManager.getConnection()`)**:
   - This establishes a connection to the MySQL database located at `localhost:3306` with the database name `st_test`. The username is `root` with no password.

3. **SQL Query Creation**:
   - The SQL statement drops (deletes) the table `ST_TESTTABLE` from the database if it exists.

4. **Statement Execution (`stmt.executeUpdate()`)**:
   - This method executes the SQL query to drop the table from the connected database.

5. **Exception Handling**:
   - The program catches and handles both `SQLException` (for SQL-related errors) and `ClassNotFoundException` (if the JDBC driver class is not found).

6. **Program Output**:
   - The program prints messages indicating the progress and success of the table deletion.
