package in.co.sunrays.net;

import java.net.*;
import java.util.Scanner;
import java.io.*;

/**
 * Writes Parameter to a URL.
 * 
 * You need Internet connection to see the output of this program.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class URLWriter {

	public static void main(String[] args) throws Exception {

		URL url = new URL("http://www.ask.com/web");

		String question = "java";

		// Create URLConnection object
		URLConnection conn = url.openConnection();

		conn.setDoOutput(true);

		// Open output stream
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());

		// Write question parameter
		out.write("q=" + question);

		// Close output stream
		out.close();

		// Connect to the Server
		conn.connect();

		// Open input channel to read data
		InputStream iStr = conn.getInputStream();

		// Scanner will convert bytes into text
		Scanner in = new Scanner(iStr);

		System.out.print("URL contents ***");

		// Read text line by line from URL
		while (in.hasNext()) {
			String html = in.nextLine();
			System.out.println(html);
		}

		in.close();

	}
}
