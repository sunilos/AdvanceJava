```java
package in.co.sunrays.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Echo TCP Server that handles multiple clients concurrently.
 * This server echoes back the messages received from clients
 * until the client sends the message "Bye." to terminate the connection.
 * 
 * @author SunilOS
 * @version 1.0
 * @since 2024
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */
public class MultiThreadedEchoServer extends Thread {

    private Socket client = null;

    /**
     * Constructor to initialize the MultiThreadedEchoServer with a client socket.
     * 
     * @param clientSocket the socket through which the server communicates with the client
     */
    public MultiThreadedEchoServer(Socket clientSocket) {
        this.client = clientSocket;
    }

    /**
     * The run method is executed when the thread is started.
     * It handles the communication with the client by reading input
     * and sending back the same input as output until "Bye." is received.
     */
    public void run() {
        try {
            // Create a PrintWriter to send output to the client
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            // Create a BufferedReader to read input from the client
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String inputLine;

            // Read input line from the client
            inputLine = in.readLine();
            while (inputLine != null) {
                // Print the received input on the server console
                System.out.println("Server Received: " + inputLine);
                // Echo the received input back to the client
                out.println(inputLine + " .. " + inputLine);
                // Break the loop if the input is "Bye."
                if (inputLine.equals("Bye.")) break;
                // Read the next line of input from the client
                inputLine = in.readLine();
            }
            // Close the resources
            out.close();
            in.close();
            client.close();

        } catch (IOException e) {
            // Handle any IOException that occurs
            e.printStackTrace();
        }
    }

    /**
     * The main method to start the Echo Server.
     * It listens for incoming client connections and starts a new thread
     * for each client to handle the communication.
     * 
     * @param args command line arguments (not used in this implementation)
     * @throws IOException if an I/O error occurs when opening the server socket
     */
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;

        // Initialize the server socket to listen on port 4444
        serverSocket = new ServerSocket(4444);

        System.out.println("Echo Server Started");

        Socket clientSocket = null;

        // Infinite loop to accept incoming client connections
        while (true) {
            // Accept a client connection
            clientSocket = serverSocket.accept();
            // Create a new instance of MultiThreadedEchoServer for the client
            MultiThreadedEchoServer multiThreadedEchoServer = new MultiThreadedEchoServer(clientSocket);
            // Start the thread to handle the client
            multiThreadedEchoServer.start();
        }

        // This part of code will not be reached due to the infinite loop above
        // System.out.println("Echo Server Stopped");
        // serverSocket.close();
    }
}
```

### Explanation of the Code

1. **Package Declaration**:
   - The `package in.co.sunrays.net;` statement organizes the class into a specific namespace.

2. **Imports**:
   - The required classes from `java.io` and `java.net` are imported to handle input/output operations and network communication.

3. **JavaDoc Comments**:
   - The class and methods are documented with JavaDoc comments, providing information about their purpose, parameters, and behavior. This helps other developers understand the functionality and context of the class.

4. **Class Declaration**:
   - The `MultiThreadedEchoServer` class extends the `Thread` class, allowing it to run concurrently with other threads.

5. **Instance Variable**:
   - `private Socket client`: This variable holds the client socket, which is used to communicate with the connected client.

6. **Constructor**:
   - The constructor initializes the instance with the given `clientSocket`, allowing each server instance to manage a separate client connection.

7. **Run Method**:
   - The `run()` method contains the logic to handle client communication:
     - It creates `PrintWriter` and `BufferedReader` objects for output and input.
     - It reads lines from the client and echoes them back until it receives the message "Bye.".
     - The server prints the received input to the console for monitoring.
     - It closes the resources when done.

8. **Main Method**:
   - The `main` method starts the Echo Server:
     - It initializes a `ServerSocket` on port `4444`.
     - It enters an infinite loop to accept incoming client connections.
     - For each accepted connection, it creates a new `MultiThreadedEchoServer` instance and starts it, allowing concurrent handling of multiple clients.

9. **Error Handling**:
   - The catch block in the `run()` method handles any `IOException` that might occur during communication, ensuring that exceptions are logged for troubleshooting.

### Note
- The `System.out.println("Echo Server Stopped");` and `serverSocket.close();` lines in the `main` method are commented out because the infinite loop prevents the server from reaching those lines. If needed, additional logic would be required to exit the loop and stop the server gracefully.
