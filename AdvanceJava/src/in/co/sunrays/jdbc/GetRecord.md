```java
package in.co.sunrays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * The GetRecord class demonstrates how to retrieve and display records from a MySQL database using JDBC.
 * It connects to the MySQL database, executes a SQL SELECT query, and fetches the data from a table.
 * The data is then displayed in a formatted output.
 * 
 * @author SunilOS
 * @version 1.0
 * @since 2023-09-26
 * @see java.sql.Connection
 * @see java.sql.Statement
 * @see java.sql.ResultSet
 */
public class GetRecord {

    /**
     * The main method is the entry point of the program.
     * It connects to the MySQL database, executes a SQL SELECT query to retrieve records from the table,
     * and prints the results.
     * 
     * @param args Unused.
     * @throws Exception If there is any issue in the JDBC operations such as connection failure or SQL errors.
     */
    public static void main(String args[]) throws Exception {

        // Load the MySQL JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // Establish a connection to the MySQL database.
        // The database is "st_test", username is "root", and password is "root".
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/st_test", "root", "root");

        // Create a statement object to execute SQL queries.
        Statement stmt = conn.createStatement();

        // Execute a SELECT query to fetch 'id', 'name', and 'color' columns from the 'ST_PART' table.
        ResultSet rs = stmt.executeQuery("SELECT id, name, color from ST_PART");

        // Print column headers for the output in a tabular format.
        System.out.println("ID\tName\tColor");
        System.out.println("--\t----\t-----");

        // Loop through the result set and print each record.
        while (rs.next()) {

            // Print the 'id' column value.
            System.out.print(rs.getString(1)); // Retrieve first column (id)

            // Print the 'name' column value.
            System.out.print("\t" + rs.getString(2)); // Retrieve second column (name)

            // Print the 'color' column value using column label instead of index.
            System.out.println("\t" + rs.getString("color")); // Retrieve 'color' column by name
        }

        // Close the statement and connection to release database resources.
        stmt.close();
        conn.close();
    }
}
```

### Explanation of the Program:

1. **JDBC Driver Load (`Class.forName()`)**:
   - This line loads the MySQL JDBC driver, which allows the Java application to interact with the MySQL database.

2. **Database Connection (`DriverManager.getConnection()`)**:
   - This establishes a connection to the MySQL database named `st_test`, using `root` as both the username and password. It connects to the database running locally on port `3306`.

3. **SQL Query Execution (`stmt.executeQuery()`)**:
   - The program executes a `SELECT` query to retrieve the columns `id`, `name`, and `color` from the `ST_PART` table. The results are stored in the `ResultSet` object.

4. **Tabular Output**:
   - The program prints column headers (`ID`, `Name`, `Color`) for a formatted output to display the records in a tabular format.

5. **Loop Through Results (`rs.next()`)**:
   - The `while (rs.next())` loop iterates through the result set row by row. For each row:
     - The `id`, `name`, and `color` values are fetched using the `getString()` method.
     - The first and second columns (`id` and `name`) are accessed by their index (1 and 2, respectively).
     - The `color` column is accessed by its label (`"color"`), which is an alternative to using a column index.

6. **Output**:
   - The results are printed in a formatted manner using tabs (`\t`) to align the columns for better readability.

7. **Resource Cleanup**:
   - The `stmt.close()` and `conn.close()` methods close the statement and the database connection to release the resources after the operation is complete.
