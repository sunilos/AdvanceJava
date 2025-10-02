```java
package in.co.sunrays.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/**
 * This class reads text from a URL and displays it on the console.
 * It replaces occurrences of the word "Yahoo" with "Sunrays".
 * 
 * Note: An active internet connection is required to see the output of this program.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */
public class ReadURL {

    /**
     * The main method of the program that reads data from a specified URL and processes it.
     * 
     * @param args Command line arguments (not used).
     * @throws IOException If there is an error in input/output operations.
     */
    public static void main(String[] args) throws IOException {

        // Declare an InputStream to hold the data from the URL
        InputStream inStream = null;

        // Declare a URL object
        URL u = null;
        try {
            // Create a new URL object pointing to the specified website
            u = new URL("http://www.yahoo.com");

            // Open an input stream to read data from the URL
            inStream = u.openStream();

        } catch (Exception e) {
            // If there is an error with the URL, display an error message and exit the program
            System.out.println("Error in URL");
            System.exit(0); // Terminate program execution.
        }

        // Create a Scanner to read the input stream line by line
        Scanner in = new Scanner(inStream);

        // Loop through the input stream until all data is read
        while (in.hasNext()) {
            // Read each line from the input stream and replace occurrences of "Yahoo" with "Sunrays"
            String str = in.nextLine().replace("Yahoo", "Sunrays");

            // Print the modified line to the console
            System.out.println(str);
        }

        // Close the Scanner to release resources
        in.close();
    }
}
```

### Explanation of the code:
1. **Package and Imports:**
   - `package in.co.sunrays.net;` defines the package to which this class belongs.
   - The code imports necessary Java classes for input/output operations, URL handling, and reading text.

2. **Class Description (`ReadURL`):**
   - This class reads data from a specified URL (`http://www.yahoo.com`) and replaces occurrences of "Yahoo" with "Sunrays" before displaying the data.
   - It uses a URL and an InputStream to fetch and read the data.

3. **Main Method:**
   - The main method serves as the entry point of the program.
   - It initializes the URL and opens an input stream to read data from the specified URL.

4. **Error Handling:**
   - The `try-catch` block is used to handle any exceptions that might occur when opening the URL.
   - If an exception is caught (e.g., if the URL is incorrect or there is no internet connection), it prints an error message and terminates the program.

5. **Data Processing:**
   - The `Scanner` object is used to read data from the input stream line by line.
   - Each line of data fetched from the URL is checked, and all occurrences of the word "Yahoo" are replaced with "Sunrays".

6. **Output:**
   - The modified text is printed to the console.
   - Once all data is read and displayed, the `Scanner` is closed to release system resources.
