```java
package in.co.sunrays.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A simple TCP server that waits for a client connection, exchanges greeting messages,
 * and displays the message received from the client.
 * 
 * @author SunilOS
 * @version 1.0
 * @since 2024
 * @see java.net.ServerSocket
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */
public class HelloTCPServer {

    /**
     * Main method to start the TCP server.
     * Sets up the server to listen for client connections, reads the client's message,
     * sends a response, and then closes the connection.
     *
     * @param args Command line arguments (not used in this program)
     * @throws Exception If any error occurs during the network communication
     */
    public static void main(String[] args) throws Exception {

        // Create a ServerSocket object to listen on port 1234
        ServerSocket server = new ServerSocket(1234);
        System.out.println("Server started, waiting for a client...");

        // Wait for a client to connect and accept the connection
        Socket client = server.accept();
        System.out.println("Client connected.");

        // Open the input stream to receive data from the client
        DataInputStream in = new DataInputStream(client.getInputStream());

        // Open the output stream to send data to the client
        DataOutputStream out = new DataOutputStream(client.getOutputStream());

        // Read the greeting message sent by the client
        String greeting = in.readLine();

        // Print the client's greeting message to the console
        System.out.println("Client says: " + greeting);

        // Send a response greeting message to the client
        out.writeBytes("Hello Client\n");

        // Close the connection with the client
        client.close();
        System.out.println("Client connection closed.");

        // Close the server socket
        server.close();
        System.out.println("Server shut down.");
    }
}
```

### Explanation of the Code
1. **Creating the Server**:
   - The `ServerSocket` object is created on port `1234` which listens for incoming client connections. The server will wait here until a client attempts to connect.

2. **Accepting Client Connection**:
   - The `server.accept()` method is called, which pauses the execution until a client connects. Once a connection is made, it returns a `Socket` object representing the client.

3. **Data Streams**:
   - `DataInputStream` and `DataOutputStream` are used to handle the input and output operations with the client. These streams are responsible for reading and writing data.

4. **Reading Client's Message**:
   - The server reads the message sent by the client using `in.readLine()` and then prints that message to the console.

5. **Sending a Response**:
   - The server sends a response message (`"Hello Client"`) back to the client using `out.writeBytes()`.

6. **Closing Connections**:
   - Finally, the connection to the client is closed with `client.close()`, and the server socket is closed with `server.close()` to free up resources.
