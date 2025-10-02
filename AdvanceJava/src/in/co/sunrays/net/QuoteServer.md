```java
package in.co.sunrays.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Quote UDP Server, replies to the Client with a random quote of the day.
 * This server listens for incoming UDP packets from clients and responds 
 * with a random quote from a predefined list.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */
public class QuoteServer {

    public static void main(String[] args) throws IOException {

        // Array of quotes that the server can send to clients
        String[] quotes = { "Bura Mat Dekho", "Bura Mat Kaho", "Bura Mat Suno" };

        // Create a DatagramSocket to listen for UDP packets on port number 4445
        DatagramSocket socket = new DatagramSocket(4445);

        // Create a byte buffer to store incoming data packets
        byte[] buf = new byte[256];

        // Create an empty DatagramPacket to receive incoming data from clients
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        boolean flag = true;

        // Loop to continuously listen for incoming packets until the flag is set to false
        while (flag) {

            // Wait for an incoming packet from a client and receive it
            socket.receive(packet);

            // Extract the IP address of the client that sent the packet
            InetAddress address = packet.getAddress();

            // Extract the port number of the client that sent the packet
            int port = packet.getPort();

            // Generate a random index between 0 and 2 to select a quote from the array
            int ind = (int) (Math.random() * 3); // Using 3 because there are 3 quotes

            // Convert the selected quote into a byte array
            byte[] quote = quotes[ind].getBytes();

            // Create a new packet that contains the quote and is addressed to the client's IP and port
            DatagramPacket quotePkt = new DatagramPacket(quote, quote.length, address, port);

            // Send the packet containing the quote back to the client
            socket.send(quotePkt);
        }

        // Close the DatagramSocket when done
        socket.close();
    }
}
```

### Explanation of the Code:

1. **Package and Import Statements:**
   - `package in.co.sunrays.net;`: Declares the package for this class.
   - The necessary classes are imported from the `java.io` and `java.net` packages to handle I/O operations and networking.

2. **Class Declaration and JavaDoc:**
   - The class `QuoteServer` is declared as a public class with a JavaDoc comment explaining its purpose.
   - This class acts as a UDP server that responds to client requests with random quotes.

3. **Main Method:**
   - The `main` method is the entry point of the program and it is designed to handle potential `IOException` errors.

4. **Quote Array:**
   - `String[] quotes = { "Bura Mat Dekho", "Bura Mat Kaho", "Bura Mat Suno" };`: This array stores the quotes that the server will send to the client.

5. **Creating a DatagramSocket:**
   - `DatagramSocket socket = new DatagramSocket(4445);`: Creates a new UDP socket that listens for packets on port 4445.

6. **Receiving Data Packets:**
   - A byte buffer is created to store the data received from clients.
   - A new `DatagramPacket` object is created to encapsulate the received data.

7. **Listening for Client Requests:**
   - The server enters a `while` loop, continuously listening for incoming UDP packets from clients.
   - The `socket.receive(packet);` line blocks until a packet is received.

8. **Extracting Client Information:**
   - The client's IP address and port number are extracted from the received packet so that the server knows where to send the response.

9. **Generating a Random Quote:**
   - A random quote is selected from the `quotes` array using a random index.

10. **Sending the Quote to the Client:**
    - A new `DatagramPacket` is created with the selected quote, and it is sent to the client's address and port.
    - `socket.send(quotePkt);`: This sends the packet to the client.

11. **Closing the Socket:**
    - Once the server is stopped, the socket is closed using `socket.close();`.

### Summary:
The `QuoteServer` class is a UDP server that continuously listens for requests from clients and responds with a random quote. It demonstrates how to use `DatagramSocket` and `DatagramPacket` for communication over a network using UDP protocol. The server operates in a loop, receiving requests and responding until it is explicitly stopped.
