package in.co.sunrays.net;

import java.io.*;
import java.net.*;

/**
 * Echo Server talks to multiple Clients sequentially
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class EchoServer {

	public static void main(String[] a) throws IOException {

		// Start Server on port number 4444
		ServerSocket sSocket = new ServerSocket(4444);

		System.out.println("Echo Server is Started");

		// Socket object
		Socket client = null;

		boolean flag = true;
		// Listen Clients until flag is false
		while (flag) {
			// Receive a new Client's request
			client = sSocket.accept();
			// Talk to the Client
			talk(client);
		}

		// Closing Server
		sSocket.close();

		System.out.println("Echo Server is Closed");
	}

	public static void talk(Socket cSocket) throws IOException {

		// Open Socket's Output Stream to write to the Client
		PrintWriter out = new PrintWriter(cSocket.getOutputStream(), true);

		// Open Socket's Input Stream to read from the Client
		BufferedReader in = new BufferedReader(new InputStreamReader(
				cSocket.getInputStream()));

		// Read text from Client
		String line = in.readLine();

		// Execute loop until line is null
		while (line != null) {

			System.out.println("Server Recived : " + line);

			// Echo line back to the Client
			out.println(line + " .. " + line);

			// If Client sent "Bye" then break the loop.
			if (line.equals("Bye")) {
				break;
			}
			// Read next line
			line = in.readLine();
		}

		// Close streams
		out.close();
		in.close();

		// Close socket
		cSocket.close();

	}

}