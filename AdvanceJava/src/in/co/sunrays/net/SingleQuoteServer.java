package in.co.sunrays.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class SingleQuoteServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		String[] quotes = { "Bura mat Dekho", "Bura Mat kaho", "Bura mat suno" };
		DatagramSocket socket = new DatagramSocket(4445);
		byte[] buf = new byte[256];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		boolean flag = true;
		while (flag) {
			socket.receive(packet);
			InetAddress address = packet.getAddress();
			int port = packet.getPort();
			String dString = null;
			int ind = (int) (Math.random() * 2);
			dString = quotes[ind];// new Date().toString() + " : Aaj ka
			// Vichar";
			byte[] newBuf = dString.getBytes();
			DatagramPacket newPacket = new DatagramPacket(newBuf,
					newBuf.length, address, port);
			socket.send(newPacket);
		}

	}

}
