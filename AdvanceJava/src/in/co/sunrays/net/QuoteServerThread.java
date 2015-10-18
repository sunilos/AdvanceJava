package in.co.sunrays.net;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Multi-threaded Quote UDP Server.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class QuoteServerThread extends Thread {

	protected DatagramSocket socket = null;

	protected BufferedReader in = null;

	protected boolean moreQuotes = true;

	protected String[] quotes = { "Bura mat Dekho", "Bura Mat kaho",
			"Bura mat suno" };

	public QuoteServerThread() throws IOException {
		this("QuoteServerThread");
	}

	public QuoteServerThread(String name) throws IOException {
		super(name);
		socket = new DatagramSocket(4445);
	}

	public void run() {

		while (moreQuotes) {
			try {
				byte[] buf = new byte[256];

				// receive request
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);

				// figure out response
				String dString = null;
				if (in == null)
					dString = new Date().toString();
				else
					dString = getNextQuote();
				buf = dString.getBytes();

				// send the response to the client at "address" and "port"
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				packet = new DatagramPacket(buf, buf.length, address, port);

				socket.send(packet);

			} catch (IOException e) {
				e.printStackTrace();
				moreQuotes = false;
			}
		}
		socket.close();
	}

	protected String getNextQuote() {
		String returnValue = null;
		try {
			if ((returnValue = in.readLine()) == null) {
				in.close();
				moreQuotes = false;
				returnValue = "No more quotes. Goodbye.";
			}
		} catch (IOException e) {
			returnValue = "IOException occurred in server.";
		}
		return returnValue;
	}

	public static void main(String[] args) throws IOException {
		new QuoteServerThread().start();
	}
}
