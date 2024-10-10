```java
package in.co.sunrays.net;

import in.co.sunrays.bean.Employee;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Object TCP Client that exchanges serialized objects over the network.
 * This class establishes a connection to a TCP server, sends a serialized Employee object,
 * and receives a response from the server.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */
public class ObjectClient {
    public static void main(String[] args) throws Exception {
        // Create a socket to connect to the server running on localhost at port 4444
        Socket echoSocket = new Socket("127.0.0.1", 4444);

        // Create an ObjectOutputStream to send serialized objects to the server
        ObjectOutputStream out = new ObjectOutputStream(echoSocket.getOutputStream());

        // Create a BufferedReader to read the server's response
        BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

        // Create an Employee object with ID, name, and company
        Employee emp = new Employee(1, "Sunrays", "Technologies");
        // Set a temporary address value for the employee
        emp.setTempValue("8 A Shalimar Corporate");

        // Send the Employee object to the server
        out.writeObject(emp);

        // Read a line of response from the server
        String line = in.readLine();

        // Print the response received from the server
        System.out.println("Received From Server: " + line);

        // Close the output stream, input stream, and socket connection
        out.close();
        in.close();
        echoSocket.close();
    }
}
```

### Explanation of the Code:

1. **Package Declaration**:
   - The class is declared within the package `in.co.sunrays.net`, which groups related classes.

2. **Imports**:
   - The `Employee` class is imported from `in.co.sunrays.bean`, which likely contains the definition of the `Employee` object to be sent over the network.
   - Classes from `java.io` and `java.net` are imported to handle input/output streams and networking.

3. **JavaDoc Comments**:
   - The class is documented with JavaDoc comments that describe its purpose, functionality, and copyright information.

4. **Main Method**:
   - The main method is the entry point of the program. It can throw exceptions, which allows for streamlined error handling.

5. **Socket Creation**:
   - `Socket echoSocket = new Socket("127.0.0.1", 4444);` creates a new socket connection to the server located at `127.0.0.1` (localhost) on port `4444`.

6. **Output Stream**:
   - `ObjectOutputStream out = new ObjectOutputStream(echoSocket.getOutputStream());` initializes an `ObjectOutputStream` that allows sending serialized objects to the server.

7. **Input Stream**:
   - `BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));` creates a `BufferedReader` to read text from the input stream of the socket.

8. **Creating Employee Object**:
   - An `Employee` object is created with an ID, name, and company. The method `emp.setTempValue("8 A Shalimar Corporate");` sets a temporary address for the employee.

9. **Sending the Object**:
   - `out.writeObject(emp);` sends the serialized `Employee` object to the server.

10. **Receiving Server Response**:
    - The line `String line = in.readLine();` reads a response from the server, which is expected to be a string.

11. **Printing the Response**:
    - The response received from the server is printed to the console with `System.out.println("Received From Server: " + line);`.

12. **Closing Streams and Socket**:
    - Finally, the output stream, input stream, and socket connection are closed using `out.close();`, `in.close();`, and `echoSocket.close();`, ensuring that resources are properly released.

### Summary:
This code snippet illustrates how to create a TCP client in Java that connects to a server, sends a serialized object, and receives a response. It demonstrates the use of sockets for network communication and the serialization of Java objects.
