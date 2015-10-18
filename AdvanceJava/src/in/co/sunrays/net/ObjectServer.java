package in.co.sunrays.net;

import in.co.sunrays.bean.Employee;

import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Object TCP Server, exchanges serializ3ed objects over network.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class ObjectServer {

	public static void main(String[] args) throws Exception {

		ServerSocket serverSocket = null;

		serverSocket = new ServerSocket(4444);

		System.out.println("Object Server Started");

		Socket clientSocket = null;

		boolean flag = true;

		while (flag) {

			clientSocket = serverSocket.accept();

			talk(clientSocket);
		}
		System.out.println("Object Server Stopped");
		serverSocket.close();
	}

	/**
	 * Talks to the Client.
	 * 
	 * @param client
	 * @throws Exception
	 */
	public static void talk(Socket client) throws Exception {

		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		ObjectInputStream in = new ObjectInputStream(client.getInputStream());

		Employee emp = (Employee) in.readObject();

		System.out.println("ID : " + emp.getId());
		System.out.println("First Name : " + emp.getFirstName());
		System.out.println("Last Name : " + emp.getLastName());
		System.out.println("Temp Value: " + emp.getTempValue());

		out.println("Got the Object");
		out.close();
		in.close();
		client.close();

	}

}