```java
package in.co.sunrays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * InsertRecord क्लास JDBC का उपयोग करके डेटाबेस तालिका में एक रिकॉर्ड डालने का प्रदर्शन करती है।
 * यह दो विधियों को दिखाती है: एक सीधी SQL INSERT के लिए Statement का उपयोग करते हुए और दूसरी
 * एक Parameterized INSERT के लिए PreparedStatement का उपयोग करते हुए, जो सुरक्षा और 
 * पुनरावृत्ति क्वेरी के लिए अधिक कुशल होती है।
 * 
 * @लेखक SunilOS
 * @संस्करण 1.0
 * @तारीख 2023-09-26
 * @see java.sql.Connection
 * @see java.sql.Statement
 * @see java.sql.PreparedStatement
 */
public class InsertRecord {

    /**
     * main विधि प्रोग्राम का प्रवेश बिंदु है।
     * यह MySQL डेटाबेस से कनेक्ट होता है और Statement का उपयोग करके SQL INSERT ऑपरेशन को निष्पादित करता है।
     * 
     * @param args अप्रयुक्त।
     * @throws Exception अगर ड्राइवर लोड करने या डेटाबेस से कनेक्ट करने में कोई समस्या होती है।
     */
    public static void main(String args[]) throws Exception {

        // MySQL JDBC ड्राइवर लोड करें
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        // MySQL डेटाबेस से कनेक्शन स्थापित करें
        // डेटाबेस: test, उपयोगकर्ता नाम: root, पासवर्ड: (खाली)
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");

        // SQL क्वेरियों को निष्पादित करने के लिए एक स्टेटमेंट ऑब्जेक्ट बनाएं
        Statement stmt = conn.createStatement();

        // part तालिका में एक रिकॉर्ड जोड़ने के लिए SQL INSERT क्वेरी निष्पादित करें
        int i = stmt.executeUpdate("INSERT INTO part VALUES (5, 'Plat', 'Green', 1)");

        // यह प्रिंट करें कि कितने रिकॉर्ड अपडेट हुए
        System.out.print(i + " रिकॉर्ड अपडेट किए गए");

        // स्टेटमेंट और कनेक्शन बंद करें
        stmt.close();
        conn.close();
    }

    /**
     * Prepared Statement का उपयोग करके INSERT स्टेटमेंट निष्पादित करता है।
     * यह विधि parameterized क्वेरी का उपयोग करके part तालिका में एक रिकॉर्ड सुरक्षित रूप से डालने का प्रदर्शन करती है।
     * 
     * @throws Exception अगर ड्राइवर लोड करने या डेटाबेस से कनेक्ट करने में कोई समस्या होती है।
     */
    public static void testPreparedInsert() throws Exception {

        // MySQL JDBC ड्राइवर लोड करें
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        // MySQL डेटाबेस से कनेक्शन स्थापित करें
        // डेटाबेस: test, उपयोगकर्ता नाम: root, पासवर्ड: root
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "root");

        // लेनदेन प्रबंधन के लिए ऑटो-कमिट मोड को बंद करें
        conn.setAutoCommit(false);

        // Parameterized SQL क्वेरियों के लिए एक PreparedStatement बनाएं
        PreparedStatement ps = conn.prepareStatement("INSERT INTO part VALUES (?, ?, ?, ?)");

        // PreparedStatement के लिए पैरामीटर सेट करें
        ps.setInt(1, 7);           // पहला पैरामीटर (id) सेट करें
        ps.setString(2, "Plat2"); // दूसरा पैरामीटर (name) सेट करें
        ps.setString(3, "Red");   // तीसरा पैरामीटर (color) सेट करें
        ps.setInt(4, 1);          // चौथा पैरामीटर (quantity) सेट करें

        // अपडेट निष्पादित करें और प्रभावित रिकॉर्ड की संख्या प्राप्त करें
        int recCount = ps.executeUpdate();

        // डाले गए रिकॉर्ड की संख्या प्रिंट करें
        System.out.println("# रिकॉर्ड: " + recCount);
        System.out.print(recCount + " रिकॉर्ड अपडेट किए गए");

        // लेनदेन को कमिट करें
        conn.commit();

        // PreparedStatement और कनेक्शन बंद करें
        ps.close();
        conn.close();
    }
}
```

### प्रोग्राम का विवरण:

1. **क्लास अवलोकन**:
   - `InsertRecord` क्लास JDBC का उपयोग करके MySQL डेटाबेस तालिका में रिकॉर्ड डालने का प्रदर्शन करती है।
   - इसमें दो विधियाँ शामिल हैं: एक `Statement` का उपयोग करके सीधी SQL निष्पादन के लिए और दूसरी `PreparedStatement` का उपयोग करके सुरक्षित पैरामीटरयुक्त क्वेरी के लिए।

2. **मुख्य विधि (`main`)**:
   - यह विधि प्रोग्राम का प्रवेश बिंदु है जहां MySQL डेटाबेस से कनेक्शन स्थापित किया जाता है।
   - यह `Statement` का उपयोग करके `part` तालिका में एक रिकॉर्ड डालने के लिए SQL INSERT क्वेरी निष्पादित करती है।

3. **JDBC ड्राइवर लोड करना**:
   - `Class.forName("com.mysql.jdbc.Driver").newInstance();` MySQL JDBC ड्राइवर को लोड करता है, जिससे डेटाबेस से कनेक्ट किया जा सके।

4. **कनेक्शन स्थापित करना**:
   - `DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");` `test` डेटाबेस से कनेक्शन स्थापित करता है, जिसमें उपयोगकर्ता नाम `root` है और पासवर्ड खाली है।

5. **SQL INSERT निष्पादन (`stmt.executeUpdate`)**:
   - SQL क्वेरी `INSERT INTO part VALUES (5, 'Plat', 'Green', 1)` को निष्पादित किया जाता है, जिससे `part` तालिका में एक नया रिकॉर्ड डाला जाता है।
   - यह विधि प्रभावित रिकॉर्ड की संख्या लौटाती है, जिसे कंसोल पर प्रिंट किया जाता है।

6. **संसाधनों को बंद करना**:
   - `Statement` और `Connection` ऑब्जेक्ट्स को बंद किया जाता है ताकि डेटाबेस संसाधनों को मुक्त किया जा सके।

7. **Parameterized Insert विधि (`testPreparedInsert`)**:
   - यह विधि `PreparedStatement` का उपयोग करके एक सुरक्षित पैरामीटरयुक्त INSERT का प्रदर्शन करती है, जो SQL इंजेक्शन के खिलाफ सुरक्षा प्रदान करती है।
   - लेनदेन को प्रबंधित करने के लिए ऑटो-कमिट मोड को बंद किया गया है, जिससे आवश्यकता पड़ने पर रोलबैक किया जा सके।

8. **पैरामीटर सेट करना**:
   - SQL INSERT स्टेटमेंट के लिए पैरामीटर को `ps.setInt()` और `ps.setString()` जैसी विधियों का उपयोग करके सेट किया जाता है, जो उपयोगकर्ता-प्रदत्त डेटा को सुरक्षित रूप से डालने में मदद करता है।

9. **निष्पादन और कमिट करना**:
   - `PreparedStatement` पर `executeUpdate()` विधि को कॉल किया जाता है ताकि INSERT ऑपरेशन को निष्पादित किया जा सके।
   - परिवर्तन को `conn.commit()` का उपयोग करके कमिट किया जाता है।

10. **संसाधनों को बंद करना**:
    - अंततः, `PreparedStatement` और `Connection` ऑब्जेक्ट्स को बंद किया जाता है ताकि संसाधनों को ठीक से रिलीज किया जा सके।
