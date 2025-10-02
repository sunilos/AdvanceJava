```java
package in.co.sunrays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * GetRecord क्लास यह प्रदर्शित करता है कि कैसे JDBC का उपयोग करके MySQL डेटाबेस से रिकॉर्ड प्राप्त और प्रदर्शित किए जाते हैं।
 * यह MySQL डेटाबेस से कनेक्ट करता है, एक SQL SELECT क्वेरी निष्पादित करता है, और टेबल से डेटा प्राप्त करता है।
 * फिर डेटा को फॉर्मेटेड आउटपुट में प्रदर्शित किया जाता है।
 * 
 * @लेखक SunilOS
 * @संस्करण 1.0
 * @तारीख 2023-09-26
 * @see java.sql.Connection
 * @see java.sql.Statement
 * @see java.sql.ResultSet
 */
public class GetRecord {

    /**
     * main विधि प्रोग्राम का प्रवेश बिंदु है।
     * यह MySQL डेटाबेस से कनेक्ट होता है, SQL SELECT क्वेरी निष्पादित करता है जिससे टेबल से रिकॉर्ड प्राप्त किए जाते हैं,
     * और परिणामों को प्रिंट करता है।
     * 
     * @param args अप्रयुक्त।
     * @throws Exception अगर JDBC संचालन में कोई समस्या होती है, जैसे कनेक्शन विफलता या SQL त्रुटियाँ।
     */
    public static void main(String args[]) throws Exception {

        // MySQL JDBC ड्राइवर लोड करें।
        Class.forName("com.mysql.jdbc.Driver");

        // MySQL डेटाबेस से कनेक्शन स्थापित करें।
        // डेटाबेस का नाम "st_test", उपयोगकर्ता नाम "root", और पासवर्ड "root" है।
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/st_test", "root", "root");

        // SQL क्वेरी निष्पादित करने के लिए एक स्टेटमेंट ऑब्जेक्ट बनाएं।
        Statement stmt = conn.createStatement();

        // ST_PART तालिका से 'id', 'name', और 'color' कॉलम प्राप्त करने के लिए SELECT क्वेरी निष्पादित करें।
        ResultSet rs = stmt.executeQuery("SELECT id, name, color from ST_PART");

        // आउटपुट को टैब्यूलर प्रारूप में प्रदर्शित करने के लिए कॉलम हैडर प्रिंट करें।
        System.out.println("ID\tName\tColor");
        System.out.println("--\t----\t-----");

        // परिणाम सेट में से प्रत्येक रिकॉर्ड को प्रदर्शित करने के लिए लूप चलाएं।
        while (rs.next()) {

            // 'id' कॉलम का मान प्रिंट करें।
            System.out.print(rs.getString(1)); // पहला कॉलम (id) प्राप्त करें

            // 'name' कॉलम का मान प्रिंट करें।
            System.out.print("\t" + rs.getString(2)); // दूसरा कॉलम (name) प्राप्त करें

            // 'color' कॉलम का मान प्रिंट करें (कॉलम लेबल का उपयोग करके)।
            System.out.println("\t" + rs.getString("color")); // 'color' कॉलम का नाम देकर प्राप्त करें
        }

        // स्टेटमेंट और कनेक्शन को बंद करें ताकि डेटाबेस संसाधनों को मुक्त किया जा सके।
        stmt.close();
        conn.close();
    }
}
```

### प्रोग्राम का विवरण:

1. **JDBC ड्राइवर लोड करना (`Class.forName()`)**:
   - यह लाइन MySQL के JDBC ड्राइवर को लोड करती है, जो प्रोग्राम को MySQL डेटाबेस के साथ इंटरैक्ट करने की अनुमति देता है।

2. **डेटाबेस कनेक्शन (`DriverManager.getConnection()`)**:
   - यह MySQL डेटाबेस `st_test` से कनेक्शन स्थापित करता है, जहां उपयोगकर्ता नाम और पासवर्ड दोनों `root` हैं। यह डेटाबेस लोकलहोस्ट पर `3306` पोर्ट पर चल रहा है।

3. **SQL क्वेरी निष्पादन (`stmt.executeQuery()`)**:
   - प्रोग्राम एक `SELECT` क्वेरी निष्पादित करता है ताकि `ST_PART` तालिका से `id`, `name`, और `color` कॉलमों को प्राप्त किया जा सके। परिणाम `ResultSet` ऑब्जेक्ट में संग्रहीत होता है।

4. **टैब्यूलर आउटपुट**:
   - प्रोग्राम कॉलम हैडर (`ID`, `Name`, `Color`) को प्रिंट करता है ताकि आउटपुट एक प्रारूपित रूप में प्रदर्शित किया जा सके।

5. **लूप द्वारा परिणामों तक पहुँचना (`rs.next()`)**:
   - `while (rs.next())` लूप परिणाम सेट में प्रत्येक रिकॉर्ड को पढ़ता है। प्रत्येक पंक्ति के लिए:
     - `id`, `name`, और `color` मानों को `getString()` विधि द्वारा प्राप्त किया जाता है।
     - पहले और दूसरे कॉलम (`id` और `name`) को उनके अनुक्रमांक (1 और 2) द्वारा प्राप्त किया जाता है।
     - `color` कॉलम को उसके नाम (`"color"`) का उपयोग करके प्राप्त किया जाता है।

6. **आउटपुट**:
   - परिणामों को टैब द्वारा विभाजित कर प्रिंट किया जाता है, ताकि कॉलम स्पष्ट रूप से प्रदर्शित हों।

7. **संसाधन साफ़ करना**:
   - `stmt.close()` और `conn.close()` विधियों का उपयोग करके स्टेटमेंट और कनेक्शन को बंद कर दिया जाता है, जिससे ऑपरेशन पूरा होने के बाद संसाधन मुक्त हो जाते हैं।
