This Java program implements a simple TCP client using the `Socket` class. The client reads text from the console (keyboard) and sends it to a server. The server, in turn, sends back the same text (an echo). The conversation continues until the client sends the string "Bye", which signals the end of communication.

### Code with Javadoc and Comments:
```java
package in.co.sunrays.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * EchoClient is a TCP client that connects to a server and sends text input 
 * from the console. The server responds with the same text (echo). 
 * The conversation ends when the client sends the message "Bye".
 * 
 * This class demonstrates basic client-server communication using TCP sockets.
 * 
 * @author Sunil
 * @version 1.0
 * @since 2024
 */
public class EchoClient {

    /**
     * The main method establishes the client-server connection, reads input 
     * from the console, sends it to the server, and prints the server's echo 
     * response.
     * 
     * @param args Command-line arguments (not used in this implementation)
     * @throws IOException If an I/O error occurs while handling sockets
     */
    public static void main(String[] args) throws IOException {

        // Connect to the server running on localhost (127.0.0.1) at port 4444
        Socket client = new Socket("127.0.0.1", 4444);

        // Create a PrintWriter to send text to the server through the socket's output stream
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);

        // Create a BufferedReader to receive text from the server through the socket's input stream
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        System.out.println("Echo Client Started");

        // Create a BufferedReader to read input from the console (keyboard)
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        // Read the first line of text from the keyboard
        String line = stdIn.readLine();

        // Continue sending and receiving messages until "Bye" is typed
        while (line != null) {

            // Send the input text to the server
            out.println(line);

            // Print the server's response (echoed text)
            System.out.println("Echo: " + in.readLine());

            // If the user types "Bye", exit the loop and close the connection
            if ("Bye".equals(line)) {
                break;
            }

            // Read the next line of text from the keyboard
            line = stdIn.readLine();
        }

        // Close all I/O streams (output to server, input from server, and keyboard input)
        out.close();
        in.close();
        stdIn.close();

        // Close the socket connection
        client.close();
    }
}
```

### Breakdown of Changes:

1. **Javadoc Comments**:
   - Class-level, method-level, and inline comments have been added to explain the functionality of the code.
   - The class-level Javadoc explains the purpose of the `EchoClient` class and its use in TCP communication.
   - The method-level Javadoc describes the `main()` method, explaining the steps of client-server communication.
   
2. **TCP Communication**:
   - The client connects to a server using the IP address `127.0.0.1` (localhost) and port `4444`. This is typical for testing on the local machine.
   - The `PrintWriter` is used to send data (strings) to the server, and the `BufferedReader` is used to receive the echoed data from the server.

3. **Looping until "Bye"**:
   - The client continues to read from the console and send messages to the server until the user types `"Bye"`. At that point, the loop breaks, and the connection is closed.
   - Each input string from the console is sent to the server, which responds with an echo of the same string. This echoed response is printed to the client console.

4. **Exception Handling**:
   - The `IOException` is thrown by the `Socket` class and various I/O streams, ensuring that any potential input/output error is properly handled.
