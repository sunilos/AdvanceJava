```java
package in.co.sunrays.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

/**
 * This class implements a simple UDP server that sends random quotes to clients.
 * The server listens for incoming datagrams on a specified port and responds
 * with a randomly selected quote from a predefined list.
 */
public class SingleQuoteServer {

    /**
     * The main method that starts the UDP server.
     *
     * @param args Command line arguments (not used).
     * @throws Exception If an error occurs during socket operations.
     */
    public static void main(String[] args) throws Exception {
        
        // Array of quotes to send to clients
        String[] quotes = { "Bura mat Dekho", "Bura Mat kaho", "Bura mat suno" };
        
        // Create a DatagramSocket to listen on port 4445
        DatagramSocket socket = new DatagramSocket(4445);
        
        // Buffer to hold incoming datagram data
        byte[] buf = new byte[256];
        
        // Create a DatagramPacket to receive incoming packets
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        
        // Flag to control the server loop
        boolean flag = true;
        
        // Loop to continuously receive and respond to client requests
        while (flag) {
            // Receive an incoming packet
            socket.receive(packet);
            
            // Get the client's address and port from the received packet
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            
            // Initialize a string to hold the quote to send
            String dString = null;
            
            // Generate a random index to select a quote from the array
            int ind = (int) (Math.random() * quotes.length);
            dString = quotes[ind]; // Select a random quote
            
            // Convert the quote to bytes for sending
            byte[] newBuf = dString.getBytes();
            
            // Create a new DatagramPacket for sending the quote back to the client
            DatagramPacket newPacket = new DatagramPacket(newBuf, newBuf.length, address, port);
            
            // Send the response packet to the client
            socket.send(newPacket);
        }

        // The socket is never closed as this server runs indefinitely
        // Ideally, implement a way to break the loop and close the socket
    }
}
```

### Explanation of the Code:
1. **Package Declaration and Imports:**
   - The code is declared under the package `in.co.sunrays.net`.
   - It imports necessary classes from the `java.net` package for handling UDP communications.

2. **Class Description (`SingleQuoteServer`):**
   - This class implements a simple UDP server that listens for incoming datagram packets and sends back a random quote from a predefined list.

3. **Main Method:**
   - The main method serves as the entry point of the program.
   - It initializes an array of quotes that the server will send to clients.

4. **DatagramSocket Creation:**
   - A `DatagramSocket` is created to listen for incoming packets on port `4445`.
   - A byte array buffer is allocated to hold incoming data.

5. **Receiving Incoming Packets:**
   - A `DatagramPacket` is created to receive incoming packets.
   - The server enters a loop that continuously listens for incoming packets from clients.

6. **Client Address and Port:**
   - Upon receiving a packet, the server retrieves the client's address and port from the `DatagramPacket`.

7. **Random Quote Selection:**
   - A random index is generated to select a quote from the `quotes` array.
   - The selected quote is stored in a string variable.

8. **Sending Response:**
   - The selected quote is converted to a byte array.
   - A new `DatagramPacket` is created for sending the quote back to the client.
   - The packet is sent using the `socket.send(newPacket)` method.

9. **Infinite Loop:**
   - The server runs indefinitely, continuously receiving requests and sending quotes.
   - Note that the socket is never closed, which means this server will run until the application is terminated. It is recommended to implement a method to break the loop and close the socket gracefully.

This implementation demonstrates the use of UDP for simple messaging, allowing clients to request and receive random quotes effectively.
