package in.co.sunrays.net;

import java.io.*;
import java.net.*;

/**
 * Quote UDP Client, requests and gets quote of the day from Quote UDP Server.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class QuoteClient {

	public static void main(String[] args) throws IOException {

		// Start UDP Socket on a port assigned by OS
		DatagramSocket socket = new DatagramSocket();

		// Create a byte buffer
		byte[] buf = new byte[256];

		// Encapsulate Server's IP Address
		InetAddress address = InetAddress.getByName("127.0.0.1");

		// Create a new packet with Server IP Address and Port Number
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address,
				4445);

		// Send packet
		socket.send(packet);

		// Create an empty data packet to receive Server's packet
		packet = new DatagramPacket(buf, buf.length);

		// Wait for a packet and receive
		socket.receive(packet);

		// Display response
		String received = new String(packet.getData());

		System.out.println("Quote of the Moment: " + received);

		// Close Socket
		socket.close();
	}
}
