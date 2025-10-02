package in.co.sunrays.jdbc;

import in.co.sunrays.bean.Marksheet;

/**
 * Test case for the {@link MarksheetService} class.
 * 
 * This class provides test methods for the `MarksheetService` class, which handles operations related to marksheets,
 * such as retrieving and adding records. The test methods demonstrate the functionality of the service methods by 
 * interacting with the database and outputting the results to the console.
 * 
 * @author SunilOS
 * @version 1.0
 * @since 2023
 * @see MarksheetService
 * @see Marksheet
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class TestMarksheetService {

    public static void main(String[] args) throws Exception {
        testAdd();
    }

    /**
     * Tests the `getMarkSheet` method of the {@link MarksheetService} class.
     * 
     * This method retrieves a marksheet by its roll number and prints its details to the console. It demonstrates
     * how to use the `getMarkSheet` method to fetch and display marksheet data.
     * 
     * @throws Exception if an error occurs while retrieving the marksheet or processing the results
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
     * Tests the `addMarkSheet` method of the {@link MarksheetService} class.
     * 
     * This method adds a new marksheet record to the database. It demonstrates how to use the `addMarkSheet` method
     * by creating a new {@link Marksheet} object and passing it to the service for insertion.
     * 
     * @throws Exception if an error occurs while adding the marksheet to the database
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
