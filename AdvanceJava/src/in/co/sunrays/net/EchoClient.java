package in.co.sunrays.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * TCP Client reads text from Console and send to the Server. Client sends "Bye"
 * if wanted to close conversation *
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class EchoClient {

	public static void main(String[] args) throws IOException {

		// Connect to server running on given IP and Port
		Socket client = new Socket("127.0.0.1", 4444);

		// Open Socket's Output Stream to write to the Server
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);

		// Open Socket's Input Stream to read from the Server
		BufferedReader in = new BufferedReader(new InputStreamReader(
				client.getInputStream()));

		System.out.println("Echo Client Started");

		// Open Input Stream to read text from Keyboard ( System.in )
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(
				System.in));

		// Read text from Keyboard
		String line = stdIn.readLine();

		// Execute loop until line is not null
		while (line != null) {

			// Write text to Server
			out.println(line);

			// Received echo string and print
			System.out.println("Echo: " + in.readLine());

			// If text is "Bye" then break the loop.
			if ("Bye".equals(line)) {
				break;
			}
			// Read next line from Keyboard
			line = stdIn.readLine();
		}

		// Close streams
		out.close();
		in.close();
		stdIn.close();

		// Close socket
		client.close();
	}
}