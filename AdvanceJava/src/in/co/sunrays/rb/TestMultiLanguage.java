package in.co.sunrays.rb;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Test ResourceBundle that reads a value from .properties file using Key.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class TestMultiLanguage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Default Locale
		ResourceBundle rb = ResourceBundle.getBundle("in.co.sunrays.rb.app");

		System.out.println(rb.getString("greeting"));

		// Create a Locale for Spanish and set to RB
		rb = ResourceBundle.getBundle("in.co.sunrays.rb.app", new Locale("sp"));

		System.out.println(rb.getString("greeting"));

		// Create a Locale for Hindi and set to RB
		rb = ResourceBundle.getBundle("in.co.sunrays.rb.app", new Locale("hi"));
		System.out.println(rb.getString("greeting"));

		rb = ResourceBundle.getBundle("in.co.sunrays.rb.app", new Locale("hi",
				"IN"));
		System.out.println(rb.getString("greeting"));

		rb = ResourceBundle.getBundle("in.co.sunrays.rb.app", new Locale("hi",
				"IN", "UP"));
		System.out.println(rb.getString("greeting"));

	}

}
