package in.co.sunrays.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


/**
 * Quote UDP Server, replies to the Client with a random quote of the day.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class QuoteServer {

	public static void main(String[] args) throws IOException {

		String[] quotes = { "Bura Mat Dekho", "Bura Mat kaho", "Bura Mat suno" };

		// Start UDP Socket on port number 4445
		DatagramSocket socket = new DatagramSocket(4445);

		// Create a byte buffer
		byte[] buf = new byte[256];

		// Create an empty data packet to receive sender's packet
		DatagramPacket packet = new DatagramPacket(buf, buf.length);

		boolean flag = true;

		// Listen Packets until flag is false
		while (flag) {

			// Wait for a packet and receive
			socket.receive(packet);

			// Get Sender's IP Address
			InetAddress address = packet.getAddress();

			// Get Sender's Port Number
			int port = packet.getPort();

			// Get random index number between 0 to 2
			int ind = (int) (Math.random() * 2);

			// Get random quote and convert into byte array
			byte[] quote = quotes[ind].getBytes();

			// Create a new packet with quote and address to Sender's IP and
			// Port
			DatagramPacket quotePkt = new DatagramPacket(quote, quote.length,
					address, port);

			// Send packet
			socket.send(quotePkt);
		}

		// Close the socket
		socket.close();

	}
}
