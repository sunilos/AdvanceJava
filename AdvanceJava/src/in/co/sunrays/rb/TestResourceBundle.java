package in.co.sunrays.rb;

import java.util.ResourceBundle;

/**
 * Test ResourceBundle that reads a value from .properties file using Key.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class TestResourceBundle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ResourceBundle rb = ResourceBundle.getBundle("in.co.sunrays.rb.app");

		String value = rb.getString("organization.name");

		System.out.println(value);

	}

}
