```java
package in.co.sunrays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * HandleSQLException क्लास JDBC ऑपरेशन में SQL अपवादों को कैसे हैंडल किया जाता है, यह प्रदर्शित करता है।
 * यह डेटाबेस तालिका में एक रिकॉर्ड डालने का प्रयास करता है और SQL त्रुटियों को संभालता है।
 * SQL अपवादों को कैच किया जाता है और जैसे त्रुटि संदेश, SQL स्थिति, और त्रुटि कोड जैसे विस्तृत जानकारी प्रदर्शित की जाती है।
 * 
 * @लेखक SunilOS
 * @संस्करण 1.0
 * @तारीख 2023-09-26
 * @see java.sql.Connection
 * @see java.sql.Statement
 * @see java.sql.SQLException
 */
public class HandleSQLException {

    /**
     * main विधि प्रोग्राम का प्रवेश बिंदु है।
     * यह MySQL डेटाबेस से कनेक्ट होता है, SQL INSERT ऑपरेशन को निष्पादित करने का प्रयास करता है, और SQLExceptions को संभालता है।
     * 
     * @param args अप्रयुक्त।
     * @throws Exception अगर ड्राइवर लोड करने या संसाधनों को बंद करने में कोई समस्या होती है।
     */
    public static void main(String args[]) throws Exception {

        // MySQL JDBC ड्राइवर लोड करें
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        
        // कनेक्शन और स्टेटमेंट वेरिएबल्स प्रारंभ करें
        Connection conn = null;
        Statement stmt = null;

        try {
            // MySQL डेटाबेस से कनेक्शन स्थापित करें
            // डेटाबेस: test, उपयोगकर्ता नाम: root, पासवर्ड: (खाली)
            conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");

            // SQL क्वेरी निष्पादित करने के लिए एक स्टेटमेंट ऑब्जेक्ट बनाएं
            stmt = conn.createStatement();

            // SQL INSERT क्वेरी निष्पादित करें ताकि Person तालिका में एक रिकॉर्ड जोड़ा जा सके
            int i = stmt.executeUpdate("INSERT INTO Person (name, age) VALUES ('Ram', 25)");

            // अपडेट किए गए रिकॉर्ड की संख्या प्रिंट करें
            System.out.print(i + " रिकॉर्ड अपडेट किया गया");

        } catch (SQLException e) { // SQLExceptions को कैच करें
            // SQLException से त्रुटि संदेश प्रिंट करें
            System.out.println("त्रुटि: " + e.getMessage());
            
            // SQL स्थिति स्ट्रिंग प्रिंट करें जो त्रुटि प्रकार की पहचान करने में मदद करती है
            System.out.println("SQL स्थिति: " + e.getSQLState());
            
            // वेंडर-विशिष्ट त्रुटि कोड प्रिंट करें
            System.out.println("त्रुटि कोड: " + e.getErrorCode());
            
            // अपवाद का कारण प्रिंट करें यदि उपलब्ध हो
            System.out.println("कारण: " + e.getCause());
            
            // अपवाद का स्टैक ट्रेस प्रिंट करें ताकि डिबग किया जा सके
            e.printStackTrace();

        } finally { // संसाधनों को बंद करना सुनिश्चित करें
            // अगर स्टेटमेंट और कनेक्शन null नहीं हैं तो उन्हें बंद करें
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
}
```

### प्रोग्राम का विवरण:

1. **JDBC ड्राइवर लोड करना (`Class.forName()`)**:
   - यह लाइन MySQL के JDBC ड्राइवर को लोड करती है, जिससे यह प्रोग्राम MySQL डेटाबेस के साथ इंटरैक्ट कर सके।

2. **डेटाबेस कनेक्शन (`DriverManager.getConnection()`)**:
   - यह MySQL डेटाबेस `test` से कनेक्शन स्थापित करता है, जिसमें उपयोगकर्ता नाम `root` है और पासवर्ड खाली है।

3. **SQL क्वेरी निष्पादन (`stmt.executeUpdate()`)**:
   - यह SQL क्वेरी `Person` तालिका में एक रिकॉर्ड डालती है, जिसमें `name` और `age` कॉलम में क्रमशः `'Ram'` और `25` की मानें डाली जाती हैं।
   - `executeUpdate()` विधि उस क्वेरी से प्रभावित पंक्तियों की संख्या लौटाती है।

4. **अपवाद हैंडलिंग (`catch (SQLException e)`)**:
   - अगर SQL क्वेरी या डेटाबेस ऑपरेशन के दौरान कोई SQL त्रुटि होती है, तो प्रोग्राम `SQLException` को पकड़ता है।
   - निम्नलिखित विवरण प्रिंट किए जाते हैं:
     - **त्रुटि संदेश**: त्रुटि का विवरण।
     - **SQL स्थिति**: एक स्ट्रिंग जो SQL स्थिति को इंगित करती है।
     - **त्रुटि कोड**: वेंडर-विशिष्ट त्रुटि कोड, जो त्रुटि के बारे में अधिक जानकारी देता है।
     - **कारण**: अपवाद का कारण, यदि उपलब्ध हो।
     - **स्टैक ट्रेस**: त्रुटि का पूरा विवरण, जिससे इसे डिबग किया जा सके।

5. **संसाधनों को साफ़ करना (`finally`)**:
   - `finally` ब्लॉक यह सुनिश्चित करता है कि डेटाबेस के संसाधनों (जैसे कि `Statement` और `Connection` ऑब्जेक्ट्स) को बंद कर दिया जाए, चाहे कोई अपवाद हो या न हो।
   - संसाधनों को बंद करना महत्वपूर्ण है ताकि सिस्टम पर अनावश्यक लोड न पड़े और कनेक्शन को ठीक से रिलीज किया जा सके।
