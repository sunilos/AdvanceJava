```java
package in.co.sunrays.net;

import in.co.sunrays.bean.Employee;

import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Object TCP Server, exchanges serialized objects over the network.
 * This server listens on port 4444 for incoming connections from clients.
 * Upon receiving a connection, it processes the serialized Employee object.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */
public class ObjectServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = null;

        // Create a ServerSocket that listens on port 4444
        serverSocket = new ServerSocket(4444);

        System.out.println("Object Server Started");

        Socket clientSocket = null;

        // Continuously listen for client connections
        boolean flag = true;
        while (flag) {
            // Accept a client connection
            clientSocket = serverSocket.accept();

            // Talk to the client
            talk(clientSocket);
        }

        System.out.println("Object Server Stopped");
        serverSocket.close(); // Close the server socket
    }

    /**
     * Talks to the Client by processing the received Employee object.
     *
     * @param client The socket connected to the client
     * @throws Exception if an I/O error occurs during communication
     */
    public static void talk(Socket client) throws Exception {
        // Create a PrintWriter for sending responses to the client
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        
        // Create an ObjectInputStream for receiving serialized objects
        ObjectInputStream in = new ObjectInputStream(client.getInputStream());

        // Read the Employee object sent by the client
        Employee emp = (Employee) in.readObject();

        // Print the details of the received Employee object
        System.out.println("ID : " + emp.getId());
        System.out.println("First Name : " + emp.getFirstName());
        System.out.println("Last Name : " + emp.getLastName());
        System.out.println("Temp Value: " + emp.getTempValue());

        // Send a confirmation message back to the client
        out.println("Got the Object");

        // Close the streams and socket
        out.close();
        in.close();
        client.close();
    }
}
```

### Explanation of the Code:

1. **Package Declaration**:
   - The class is part of the `in.co.sunrays.net` package, which groups related classes.

2. **Imports**:
   - Imports the `Employee` class from `in.co.sunrays.bean` to handle the employee data.
   - Imports necessary classes from `java.io` and `java.net` for input/output operations and network communication.

3. **JavaDoc Comments**:
   - The class and methods are documented using JavaDoc comments, providing information about their purpose, functionality, and copyright.

4. **Main Method**:
   - This is the entry point of the program. It initializes the server socket and listens for incoming connections.
   - It prints a message indicating that the server has started.

5. **ServerSocket Initialization**:
   - `ServerSocket serverSocket = new ServerSocket(4444);`: This line creates a server socket that listens on port 4444 for incoming connections.

6. **Accepting Client Connections**:
   - A loop (`while (flag)`) keeps the server running, accepting client connections using `serverSocket.accept()`.
   - For each accepted client, the `talk()` method is called to handle communication.

7. **Talk Method**:
   - This method handles the interaction with the connected client.
   - **PrintWriter** is created to send responses back to the client.
   - **ObjectInputStream** is used to read the serialized `Employee` object sent by the client.

8. **Processing the Received Employee Object**:
   - The server reads the object using `in.readObject()` and casts it to `Employee`.
   - It prints the properties of the `Employee` object, such as ID, first name, last name, and temporary value.

9. **Sending Response**:
   - After processing the object, a confirmation message ("Got the Object") is sent back to the client.

10. **Closing Resources**:
    - The output stream, input stream, and client socket are closed to free up resources.

### Summary:
The `ObjectServer` class demonstrates how to create a TCP server in Java that listens for incoming client connections, receives a serialized object, processes it, and sends a response back. This example illustrates network programming concepts, including sockets, streams, and object serialization in Java.
