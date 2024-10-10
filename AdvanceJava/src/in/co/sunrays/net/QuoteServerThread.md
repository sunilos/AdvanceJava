```java
package in.co.sunrays.net;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Multi-threaded Quote UDP Server.
 * This server listens for client requests and responds with a random quote or the current date.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class QuoteServerThread extends Thread {

    // DatagramSocket is used to send and receive UDP packets
    protected DatagramSocket socket = null;

    // BufferedReader is used to read quotes from a file (if required)
    protected BufferedReader in = null;

    // Boolean flag to keep the server running or stop it
    protected boolean moreQuotes = true;

    // Array of predefined quotes that can be sent to the clients
    protected String[] quotes = { "Bura mat Dekho", "Bura Mat kaho", "Bura mat suno" };

    /**
     * Default constructor for the QuoteServerThread class.
     * It initializes the thread and the socket.
     * 
     * @throws IOException if an input or output error occurs.
     */
    public QuoteServerThread() throws IOException {
        this("QuoteServerThread");
    }

    /**
     * Constructor with a name parameter that initializes the thread with a specific name.
     * It opens a DatagramSocket on port 4445 to listen for client requests.
     * 
     * @param name The name of the thread.
     * @throws IOException if an input or output error occurs.
     */
    public QuoteServerThread(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(4445); // Initialize the socket on port 4445
    }

    /**
     * The run method contains the logic for handling client requests.
     * It continuously listens for incoming packets and sends a response back to the client.
     */
    public void run() {

        // Keep listening for requests while moreQuotes is true
        while (moreQuotes) {
            try {
                // Buffer to store incoming data from client
                byte[] buf = new byte[256];

                // Create a DatagramPacket to receive the data from the client
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet); // Receive the client's request

                // Determine the response to send back to the client
                String dString = null;
                if (in == null) {
                    dString = new Date().toString(); // If no quotes are read, send the current date and time
                } else {
                    dString = getNextQuote(); // Get the next quote to send
                }
                buf = dString.getBytes(); // Convert the response into bytes

                // Get the client's address and port from the received packet
                InetAddress address = packet.getAddress();
                int port = packet.getPort();

                // Create a new packet to send the response back to the client
                packet = new DatagramPacket(buf, buf.length, address, port);

                // Send the response packet to the client
                socket.send(packet);

            } catch (IOException e) {
                e.printStackTrace();
                moreQuotes = false; // Stop the server in case of an error
            }
        }
        socket.close(); // Close the socket when the server stops
    }

    /**
     * Retrieves the next quote from the BufferedReader.
     * If no quotes are available, it closes the reader and sets the flag to stop the server.
     * 
     * @return The next quote or a message if no more quotes are available.
     */
    protected String getNextQuote() {
        String returnValue = null;
        try {
            // Read the next line from the BufferedReader (file)
            if ((returnValue = in.readLine()) == null) {
                in.close();
                moreQuotes = false; // Stop the server if there are no more quotes
                returnValue = "No more quotes. Goodbye."; // Send a message to the client if no quotes are left
            }
        } catch (IOException e) {
            returnValue = "IOException occurred in server."; // Handle exceptions during reading
        }
        return returnValue;
    }

    /**
     * The main method that starts the QuoteServerThread.
     * 
     * @param args Command-line arguments (not used).
     * @throws IOException if an input or output error occurs.
     */
    public static void main(String[] args) throws IOException {
        new QuoteServerThread().start(); // Create and start a new server thread
    }
}
```

### Explanation of the Code:
1. **Package and Imports:**
   - The package `in.co.sunrays.net` defines the location of the class.
   - The code imports classes required for networking (`DatagramSocket`, `DatagramPacket`, `InetAddress`) and input/output operations (`BufferedReader`, `IOException`).

2. **Class and Fields:**
   - `QuoteServerThread` extends the `Thread` class to create a multi-threaded server.
   - Fields like `DatagramSocket`, `BufferedReader`, and `boolean moreQuotes` manage server operations and client communication.

3. **Constructors:**
   - The default constructor and parameterized constructor initialize the thread and open a socket on port `4445` for UDP communication.

4. **Run Method:**
   - The main logic of the server resides in the `run` method.
   - It waits for incoming client requests, processes them, and sends back either a quote or the current date and time as a response.

5. **getNextQuote Method:**
   - This method reads quotes from a file (if available) and returns them to the client.
   - If no quotes are left, it sends a message indicating the end of quotes.

6. **Main Method:**
   - The `main` method starts the server thread by creating a new instance of `QuoteServerThread` and calling its `start()` method.

### Key Features:
- **Multi-threading:** The server can handle multiple client requests efficiently.
- **UDP Communication:** It uses DatagramSocket for sending and receiving packets, making it suitable for simple, fast message exchange.
- **Graceful Error Handling:** It handles exceptions gracefully and shuts down the server when an error occurs.
