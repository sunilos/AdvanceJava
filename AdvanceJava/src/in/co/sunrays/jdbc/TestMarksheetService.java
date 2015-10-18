package in.co.sunrays.jdbc;

import in.co.sunrays.bean.Marksheet;

/**
 * Testcase of MarksheetService class.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class TestMarksheetService {

	public static void main(String[] args) throws Exception {
		testAdd();
	}

	/**
	 * Tests get method of MarksheetService
	 * 
	 * @throws Exception
	 */

	public static void testGet() throws Exception {

		// Create Service instance
		MarksheetService service = new MarksheetService();

		Marksheet bean = service.getMarkSheet("B1");

		System.out.println("Roll No " + bean.getRollNo());
		System.out.println("Name " + bean.getName());
		System.out.println("Maths " + bean.getMaths());
		System.out.println("Chemistry " + bean.getChemistry());
		System.out.println("Physics " + bean.getPhysics());

	}

	/**
	 * Tests add method of MarksheetService
	 * 
	 * @throws Exception
	 */
	public static void testAdd() throws Exception {

		Marksheet bean = new Marksheet();

		bean.setRollNo("A1");
		bean.setName("Yuvraj");
		bean.setMaths(99);
		bean.setChemistry(99);
		bean.setPhysics(99);

		// Create Service instance
		MarksheetService service = new MarksheetService();

		// Add a Marksheet
		service.addMarkSheet(bean);

	}

}
