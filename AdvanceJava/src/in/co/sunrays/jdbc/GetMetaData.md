```java
package in.co.sunrays.jdbc;

import java.sql.*;

/**
 * The GetMetaData class demonstrates how to retrieve and display metadata information from a SQL query using JDBC.
 * It connects to a MySQL database, executes a query, and then fetches metadata such as catalog name, table name, 
 * and column details using the ResultSetMetaData class.
 * 
 * This program provides details about the columns retrieved from the query result.
 * 
 * @author SunilOS
 * @version 1.0
 * @since 2023-09-26
 * @see java.sql.ResultSetMetaData
 * @see java.sql.ResultSet
 * @see java.sql.Statement
 * @see java.sql.Connection
 */
public class GetMetaData {

    /**
     * The main method is the entry point of the program.
     * It connects to the MySQL database, executes a SQL SELECT query, and retrieves metadata about the result set.
     * 
     * @param args Unused.
     * @throws Exception If there is any issue in the JDBC operations.
     */
    public static void main(String args[]) throws Exception {

        // Load and instantiate the MySQL JDBC driver.
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        // Establish a connection to the MySQL database.
        // The database is "ST_TEST", username is "root" and password is "root".
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/ST_TEST", "root", "root");

        // Create a statement object to execute SQL queries.
        Statement stmt = conn.createStatement();

        // Execute a SELECT query to fetch 'id', 'name', and 'color' columns from the 'ST_PART' table.
        ResultSet rs = stmt.executeQuery("SELECT id, name, color from ST_PART");

        // Get the metadata of the result set using ResultSetMetaData.
        ResultSetMetaData rsmt = rs.getMetaData();

        // Print the catalog name of the first column in the result set.
        System.out.println("Catalog Name : " + rsmt.getCatalogName(1));

        // Print the table name of the first column in the result set.
        System.out.println("Table Name : " + rsmt.getTableName(1));

        // Get and print the total number of columns in the result set.
        int columnCount = rsmt.getColumnCount();
        System.out.println("Total Columns : " + columnCount);
        System.out.println();

        // Loop through each column and print its metadata.
        for (int i = 1; i <= columnCount; i++) {
            System.out.println("Column : " + i);  // Print column number.
            System.out.println("Label : " + rsmt.getColumnLabel(i));  // Print column label (alias or actual name).
            System.out.println("Name : " + rsmt.getColumnName(i));  // Print actual column name.
            System.out.println("Type : " + rsmt.getColumnTypeName(i));  // Print the column data type (e.g., VARCHAR, INT).
            System.out.println("Size : " + rsmt.getColumnDisplaySize(i));  // Print the display size of the column.
            System.out.println("Precision : " + rsmt.getPrecision(i));  // Print the precision (number of digits) for numeric types.
            System.out.println();
        }

        // Close the statement and connection to release database resources.
        stmt.close();
        conn.close();
    }
}
```

### Explanation of the Program:

1. **JDBC Driver Load (`Class.forName()`)**:
   - This line loads and instantiates the JDBC driver for MySQL. It allows the program to interact with the MySQL database.

2. **Database Connection (`DriverManager.getConnection()`)**:
   - This establishes a connection to the MySQL database named `ST_TEST`, using `root` as the username and `root` as the password.

3. **SQL Query Execution (`stmt.executeQuery()`)**:
   - Executes a `SELECT` query to fetch columns `id`, `name`, and `color` from the table `ST_PART`. The result of this query is stored in a `ResultSet` object.

4. **ResultSetMetaData Object (`rs.getMetaData()`)**:
   - This object contains metadata about the `ResultSet`. It can be used to retrieve information like the number of columns, column names, data types, etc.

5. **Metadata Retrieval**:
   - The program prints the catalog name, table name, and details about each column in the result set (like label, name, type, size, and precision).

6. **Looping Through Columns**:
   - The program iterates through all the columns in the result set and prints their metadata.

7. **Statement and Connection Closure**:
   - The `stmt.close()` and `conn.close()` methods are used to close the database resources after completing the operation.
