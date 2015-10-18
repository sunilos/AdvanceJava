package in.co.sunrays.net;

import in.co.sunrays.bean.Employee;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Object TCP Client, exchanges serializ3ed objects over network.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class ObjectClient {
	public static void main(String[] args) throws Exception {

		Socket echoSocket = new Socket("127.0.0.1", 4444);

		ObjectOutputStream out = new ObjectOutputStream(
				echoSocket.getOutputStream());

		BufferedReader in = new BufferedReader(new InputStreamReader(
				echoSocket.getInputStream()));

		Employee emp = new Employee(1, "Sunrays", "Technologies");
		emp.setTempValue("8 A Shalimar Corporate");

		out.writeObject(emp);

		String line = in.readLine();

		System.out.println("Received From Server : " + line);

		out.close();
		in.close();
		echoSocket.close();
	}
}