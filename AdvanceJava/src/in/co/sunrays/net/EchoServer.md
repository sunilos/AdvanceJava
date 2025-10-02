This Java program implements a simple TCP server, called an **Echo Server**, which listens for connections from clients. The server can handle multiple clients sequentially. When a client sends a message to the server, the server echoes it back to the client with a repeated format. If the client sends the message `"Bye"`, the connection closes. The server continues to run and accept new client connections until it's manually stopped.

### Code with Javadoc and Comments:
```java
package in.co.sunrays.net;

import java.io.*;
import java.net.*;

/**
 * EchoServer is a TCP server that communicates with multiple clients sequentially.
 * It listens on port 4444 for client connections. When a client connects, 
 * the server echoes back the messages sent by the client.
 * 
 * The server continues to accept client requests until it is manually stopped.
 * If a client sends "Bye", the connection with that client is closed.
 * 
 * @author Sunil
 * @version 1.0
 * @since 2024
 */
public class EchoServer {

    /**
     * The main method starts the server, listens for client connections,
     * and handles client communication sequentially. It runs until manually
     * stopped.
     * 
     * @param a Command-line arguments (not used in this implementation)
     * @throws IOException If an I/O error occurs while handling sockets
     */
    public static void main(String[] a) throws IOException {

        // Start the server on port 4444
        ServerSocket sSocket = new ServerSocket(4444);

        System.out.println("Echo Server is Started");

        // Socket object to handle client connections
        Socket client = null;

        // Flag to keep the server running
        boolean flag = true;

        // Keep listening for client connections until the flag is set to false
        while (flag) {
            // Accept a new client's request and create a socket for communication
            client = sSocket.accept();

            // Handle communication with the connected client
            talk(client);
        }

        // Close the server socket when done
        sSocket.close();

        System.out.println("Echo Server is Closed");
    }

    /**
     * The talk method handles communication with the connected client. 
     * It reads messages sent by the client, echoes them back, and 
     * continues until the client sends "Bye".
     * 
     * @param cSocket The socket object used to communicate with the client
     * @throws IOException If an I/O error occurs during communication
     */
    public static void talk(Socket cSocket) throws IOException {

        // Create a PrintWriter to send data to the client through the socket's output stream
        PrintWriter out = new PrintWriter(cSocket.getOutputStream(), true);

        // Create a BufferedReader to read data sent by the client through the socket's input stream
        BufferedReader in = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));

        // Read the first line of text sent by the client
        String line = in.readLine();

        // Continue communication until the client sends "Bye" or null
        while (line != null) {

            // Print the received message on the server console
            System.out.println("Server Received: " + line);

            // Echo the message back to the client, with a repeated format
            out.println(line + " .. " + line);

            // If the client sends "Bye", break the loop and close the connection
            if (line.equals("Bye")) {
                break;
            }

            // Read the next line from the client
            line = in.readLine();
        }

        // Close the output and input streams
        out.close();
        in.close();

        // Close the client socket
        cSocket.close();
    }
}
```

### Breakdown of Changes:

1. **Javadoc Comments**:
   - Class-level, method-level, and inline comments have been added to explain the functionality of the code.
   - The class-level Javadoc explains the purpose of the `EchoServer` class and how it communicates with clients.
   - The method-level Javadoc describes the `main()` and `talk()` methods in detail.

2. **Sequential Client Communication**:
   - The server listens for client connections using `ServerSocket` on port `4444`.
   - Each time a client connects, the `talk()` method is called to handle communication with that specific client.
   - The server processes one client at a time (sequentially), meaning multiple clients cannot connect and interact simultaneously.

3. **Echo Behavior**:
   - When a client sends a message, the server prints the message to its own console (`System.out.println("Server Received: " + line);`) and echoes the message back to the client, with a repeated format (`out.println(line + " .. " + line);`).
   - The loop continues until the client sends `"Bye"`, at which point the server closes the connection with that client.

4. **Server Lifecycle**:
   - The server continues to accept client connections in a loop as long as the `flag` is `true`.
   - The server socket is closed manually when needed (currently when `flag` is false, though no specific mechanism is provided to set it to false in this example).

5. **Exception Handling**:
   - The program handles potential `IOException` for socket communication, ensuring proper handling of I/O errors when clients connect or communicate with the server.
