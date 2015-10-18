package in.co.sunrays.net;

import java.io.*;

/*
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class MultiThreadQuoteServer {
	public static void main(String[] args) throws IOException {
		new QuoteServerThread().start();
	}
}
