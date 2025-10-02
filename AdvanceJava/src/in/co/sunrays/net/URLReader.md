```java
package in.co.sunrays.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * This class reads and displays the content from a specified URL.
 * It also prints the protocol, host name, port number, and file name of the URL.
 * 
 * Note: An active internet connection is required to see the output of this program.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */
public class URLReader {

    /**
     * The main method serves as the entry point for the program.
     * It creates a URL object and reads content from the specified URL.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {

        // Declare a URL object
        URL u = null;

        try {
            // Create a new URL object with the specified URL
            u = new URL("https://www.google.co.in/search");

            // Print the components of the URL
            System.out.println("Protocol: " + u.getProtocol()); // Output the protocol (http or https)
            System.out.println("Host Name: " + u.getHost()); // Output the host name (domain)
            System.out.println("Port Number: " + u.getPort()); // Output the port number (default is -1 if not specified)
            System.out.println("File Name: " + u.getFile()); // Output the file name part of the URL

            // Open an input stream to read data from the URL
            InputStream iStr = u.openStream();

            // Create a Scanner to convert bytes from the InputStream into text
            Scanner in = new Scanner(iStr);

            // Indicate the beginning of URL content output
            System.out.print("URL contents ***\n");

            // Read text line by line from the URL
            while (in.hasNext()) {
                String html = in.nextLine(); // Read the next line from the InputStream
                System.out.println(html); // Print the line to the console
            }

            // Close the Scanner to release resources
            in.close();

        } catch (MalformedURLException e) {
            // Handle the case where the URL is invalid
            System.out.println("Invalid URL");
        } catch (IOException e) {
            // Handle I/O errors while reading from the URL
            System.out.println("Input Output Error");
        }
    }
}
```

### Explanation of the Code:

1. **Package and Imports:**
   - The code is defined in the `in.co.sunrays.net` package.
   - It imports necessary classes for input/output operations, URL handling, and reading text.

2. **Class Description (`URLReader`):**
   - The `URLReader` class is designed to read and display content from a specified URL.
   - It outputs the URL's protocol, host name, port number, and file name.

3. **Main Method:**
   - The `main` method serves as the entry point for the program.
   - It initializes a URL object and handles exceptions related to URL formatting and I/O operations.

4. **URL Initialization:**
   - A `URL` object is created using a specified URL (`https://www.google.co.in/search`).
   - The components of the URL (protocol, host, port, and file name) are printed to the console.

5. **Opening Input Stream:**
   - The program opens an input stream using `u.openStream()`, which allows for reading data from the URL.

6. **Reading URL Contents:**
   - A `Scanner` is created to read the input stream line by line.
   - The program outputs "URL contents ***" to indicate the start of the content being printed.
   - Inside a `while` loop, the program reads and prints each line of HTML content from the URL until no more lines are left.

7. **Closing Resources:**
   - The `Scanner` is closed to free up resources after reading is complete.

8. **Error Handling:**
   - The code includes `try-catch` blocks to handle potential exceptions:
     - `MalformedURLException` is caught when the URL format is invalid.
     - `IOException` is caught for errors related to input/output operations.

This program efficiently demonstrates how to retrieve and display web content using Java's URL and input stream functionalities, along with proper error handling.
