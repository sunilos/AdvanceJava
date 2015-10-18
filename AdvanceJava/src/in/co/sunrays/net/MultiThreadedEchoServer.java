package in.co.sunrays.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Echo TCP Server handles multiple Clients concurrently.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class MultiThreadedEchoServer extends Thread {

	private Socket client = null;

	public MultiThreadedEchoServer(Socket clientSocket) {
		this.client = clientSocket;
	}

	public void run() {
		try {
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			String inputLine, outputLine;
			inputLine = in.readLine();
			while (inputLine != null) {
				System.out.println("Server Recived " + inputLine);
				out.println(inputLine + " .. " + inputLine);
				if (inputLine.equals("Bye."))
					break;
				inputLine = in.readLine();
			}
			out.close();
			in.close();
			client.close();

		} catch (IOException e) {

		}

	}

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = null;

		MultiThreadedEchoServer multiThreadedEchoServer = null;

		serverSocket = new ServerSocket(4444);

		System.out.println("Echo Server Started");

		Socket clientSocket = null;

		boolean flag = true;

		while (flag) {

			clientSocket = serverSocket.accept();

			multiThreadedEchoServer = new MultiThreadedEchoServer(clientSocket);

			multiThreadedEchoServer.start();

		}
		System.out.println("Echo Server Stoped");
		serverSocket.close();
	}

}