```java
package in.co.sunrays.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * A simple TCP client that connects to a server using a specified IP address and port number,
 * exchanges greeting messages, and displays the server's response.
 * 
 * @author SunilOS
 * @version 1.0
 * @since 2024
 * @see java.net.Socket
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */
public class HelloTCPClient {

    /**
     * Main method to start the TCP client.
     * Establishes a connection to the server, sends a greeting message, and prints
     * the server's response.
     *
     * @param args Command line arguments (not used in this program)
     * @throws Exception If any error occurs during the network communication
     */
    public static void main(String[] args) throws Exception {
        // Create a Socket object to connect to the server at localhost on port 1234
        Socket client = new Socket("localhost", 1234);

        // Open the input stream to receive data from the server
        DataInputStream in = new DataInputStream(client.getInputStream());

        // Open the output stream to send data to the server
        DataOutputStream out = new DataOutputStream(client.getOutputStream());

        // Send a greeting message to the server
        out.writeBytes("Hello Server\n");

        // Read the response from the server
        String greeting = in.readLine();

        // Print the server's response to the console
        System.out.println(greeting);

        // Close the connection with the server
        client.close();
    }
}
```

### Explanation of the Code
1. **Socket Connection**:
   - The `Socket` object is created using the server's IP address (`localhost`) and port number (`1234`). This establishes a connection with the server.
  
2. **Data Streams**:
   - `DataInputStream` and `DataOutputStream` are used to handle the input and output operations between the client and the server. These streams allow sending and receiving data in a structured way.

3. **Sending a Message**:
   - The client sends a greeting message (`"Hello Server"`) to the server using the `out.writeBytes()` method. This method writes the string as a sequence of bytes.

4. **Receiving the Server's Response**:
   - The client reads the server's response using the `in.readLine()` method, which reads a line of text from the input stream.

5. **Closing the Connection**:
   - Finally, the client closes the socket connection using `client.close()` to free up resources.
