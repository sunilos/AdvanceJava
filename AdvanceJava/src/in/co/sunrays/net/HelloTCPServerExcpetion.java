package in.co.sunrays.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP Server exchanges Hello with Client. Depicts Exception Handling.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class HelloTCPServerExcpetion {

	public static void main(String[] args) {

		// Create a Server
		ServerSocket server;

		try {
			server = new ServerSocket(1234);

			// Wait for Client
			Socket client;
			client = server.accept();
			// Open Client’s Input Stream
			DataInputStream in = new DataInputStream(client.getInputStream());
			// Open Client’s Output Stream
			DataOutputStream out = new DataOutputStream(
					client.getOutputStream());
			// Read greeting sent by Client
			String greeting = in.readLine();
			// Open Client’s Input Stream
			System.out.println(greeting);

			// Write greetings to Client
			out.writeBytes("Hello Client\n");
			// Close connection with Client
			client.close();
			// Close Server
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
