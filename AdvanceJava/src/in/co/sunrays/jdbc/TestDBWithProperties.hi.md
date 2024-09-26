```java
package in.co.sunrays.jdbc;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * इस क्लास का उपयोग डेटाबेस से कनेक्ट करने और डेटा को प्राप्त करने के लिए किया जाता है।
 * यह कनेक्शन के लिए आवश्यक सभी क्रेडेंशियल्स को हार्ड कोडिंग से बचने के लिए एक प्रॉपर्टीज फ़ाइल से पढ़ता है।
 * 
 * @Copyright (c) SunilOS. सभी अधिकार सुरक्षित।
 * @URL www.SunilOS.com
 */
public class TestDBWithProperties {

    /**
     * मुख्य मेथड जो प्रोग्राम को निष्पादित करता है।
     * 
     * @param args कमांड लाइन तर्क (अप्रयुक्त)
     * @throws Exception यदि कोई त्रुटि होती है।
     */
    public static void main(String args[]) throws Exception {
        testSelect(); // डेटाबेस से डेटा प्राप्त करने के लिए testSelect मेथड का कॉल करें
        // testInsert(); // यहाँ आप एक insert मेथड भी कॉल कर सकते हैं, यदि आवश्यक हो
    }

    /**
     * डेटाबेस से डेटा को SELECT करने के लिए मेथड।
     * 
     * @throws Exception यदि डेटाबेस कनेक्शन या SQL ऑपरेशन के दौरान कोई त्रुटि होती है।
     */
    public static void testSelect() throws Exception {

        // ResourceBundle का उपयोग करके प्रॉपर्टीज फ़ाइल से डेटाबेस क्रेडेंशियल्स पढ़ें
        ResourceBundle rb = ResourceBundle.getBundle("in.co.sunrays.jdbc.system");

        // डेटाबेस कनेक्शन के लिए आवश्यक जानकारी प्राप्त करें
        String driverName = rb.getString("database.driver");
        String url = rb.getString("database.url");
        String loginId = rb.getString("database.user");
        String password = rb.getString("database.password");

        // JDBC ड्राइवर लोड करें
        Class.forName(driverName);

        // डेटाबेस से कनेक्शन स्थापित करें
        Connection conn = DriverManager.getConnection(url, loginId, password);

        // ऑटो-कमिट को false पर सेट करें ताकि हम मैन्युअल रूप से कमिट कर सकें
        conn.setAutoCommit(false);

        // एक स्टेटमेंट ऑब्जेक्ट बनाएं
        Statement stmt = conn.createStatement();

        // SQL क्वेरी को निष्पादित करें
        ResultSet rs = stmt.executeQuery("SELECT id, name, color from ST_PART");

        // परिणाम प्रदर्शित करें
        System.out.println("ID\tName\tColor");
        System.out.println("--\t----\t-----");

        // परिणाम सेट के माध्यम से इटरेट करें और डेटा प्रदर्शित करें
        while (rs.next()) {
            System.out.print(rs.getString(1)); // ID प्राप्त करें
            System.out.print("\t" + rs.getString(2)); // नाम प्राप्त करें
            System.out.println("\t" + rs.getString("color")); // रंग प्राप्त करें
        }

        // कनेक्शन में किए गए परिवर्तनों को कमिट करें
        conn.commit();

        // स्टेटमेंट और कनेक्शन को बंद करें
        stmt.close();
        conn.close();
    }
}
```

### Explanation:
1. **Package Declaration**: The class belongs to the package `in.co.sunrays.jdbc`, which indicates it is part of a JDBC application.

2. **Imports**: The necessary Java SQL and ResourceBundle classes are imported for database operations and properties file handling.

3. **Class Documentation**: A class-level Javadoc comment provides an overview of the class's purpose and details about copyright and URL.

4. **Main Method**: 
   - The `main` method serves as the entry point of the program. 
   - It calls the `testSelect` method to fetch and display data from the database.
   - There’s a commented-out line for `testInsert()` for potential future use.

5. **testSelect Method**:
   - It reads database credentials from a properties file using `ResourceBundle`.
   - JDBC driver is loaded using `Class.forName()`.
   - A connection to the database is established with the credentials obtained from the properties file.
   - Auto-commit is disabled to allow for manual transaction control.
   - A SQL SELECT query is executed to retrieve data from the `ST_PART` table.
   - The results are printed in a tabular format.
   - The connection is committed to save any changes (though there are no changes in a SELECT query).
   - Finally, the statement and connection are closed to free up resources.
