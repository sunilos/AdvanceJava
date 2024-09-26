```java
package in.co.sunrays.jdbc;

import java.sql.*;

/**
 * GetMetaData क्लास यह प्रदर्शित करता है कि JDBC (Java Database Connectivity) का उपयोग करके SQL क्वेरी से मेटाडाटा जानकारी कैसे प्राप्त की जाती है और प्रदर्शित की जाती है।
 * यह MySQL डेटाबेस से कनेक्ट करता है, एक क्वेरी निष्पादित करता है, और फिर ResultSetMetaData क्लास का उपयोग करके मेटाडाटा जैसे कैटलॉग नाम, तालिका नाम, 
 * और कॉलम के विवरण प्राप्त करता है।
 * 
 * यह प्रोग्राम क्वेरी के परिणाम से प्राप्त कॉलमों के बारे में विवरण प्रदान करता है।
 * 
 * @लेखक SunilOS
 * @संस्करण 1.0
 * @तारीख 2023-09-26
 * @see java.sql.ResultSetMetaData
 * @see java.sql.ResultSet
 * @see java.sql.Statement
 * @see java.sql.Connection
 */
public class GetMetaData {

    /**
     * main विधि प्रोग्राम का प्रवेश बिंदु है।
     * यह MySQL डेटाबेस से कनेक्ट होता है, SQL SELECT क्वेरी निष्पादित करता है, और परिणाम सेट के मेटाडाटा को प्राप्त करता है।
     * 
     * @param args अप्रयुक्त।
     * @throws Exception अगर JDBC संचालन में कोई समस्या होती है।
     */
    public static void main(String args[]) throws Exception {

        // MySQL JDBC ड्राइवर लोड और इंस्टेंटिएट करें।
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        // MySQL डेटाबेस से कनेक्शन स्थापित करें।
        // डेटाबेस का नाम "ST_TEST" है, उपयोगकर्ता नाम "root" और पासवर्ड "root" है।
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/ST_TEST", "root", "root");

        // SQL क्वेरी निष्पादित करने के लिए एक स्टेटमेंट ऑब्जेक्ट बनाएँ।
        Statement stmt = conn.createStatement();

        // ST_PART तालिका से 'id', 'name', और 'color' कॉलम प्राप्त करने के लिए SELECT क्वेरी निष्पादित करें।
        ResultSet rs = stmt.executeQuery("SELECT id, name, color from ST_PART");

        // ResultSet का मेटाडाटा प्राप्त करें, जिसमें कॉलमों के बारे में जानकारी होती है।
        ResultSetMetaData rsmt = rs.getMetaData();

        // पहले कॉलम का कैटलॉग नाम प्रिंट करें।
        System.out.println("कैटलॉग नाम : " + rsmt.getCatalogName(1));

        // पहले कॉलम का तालिका नाम प्रिंट करें।
        System.out.println("तालिका नाम : " + rsmt.getTableName(1));

        // परिणाम सेट में कुल कॉलमों की संख्या प्राप्त करें और प्रिंट करें।
        int columnCount = rsmt.getColumnCount();
        System.out.println("कुल कॉलम : " + columnCount);
        System.out.println();

        // प्रत्येक कॉलम का मेटाडाटा प्राप्त करें और प्रिंट करें।
        for (int i = 1; i <= columnCount; i++) {
            System.out.println("कॉलम : " + i);  // कॉलम संख्या प्रिंट करें।
            System.out.println("लेबल : " + rsmt.getColumnLabel(i));  // कॉलम लेबल (उपनाम या वास्तविक नाम) प्रिंट करें।
            System.out.println("नाम : " + rsmt.getColumnName(i));  // वास्तविक कॉलम नाम प्रिंट करें।
            System.out.println("प्रकार : " + rsmt.getColumnTypeName(i));  // कॉलम का डेटा प्रकार (जैसे, VARCHAR, INT) प्रिंट करें।
            System.out.println("आकार : " + rsmt.getColumnDisplaySize(i));  // कॉलम का डिस्प्ले आकार प्रिंट करें।
            System.out.println("प्रेसिशन : " + rsmt.getPrecision(i));  // न्यूमेरिक प्रकार के लिए अंकों की संख्या (प्रेसिशन) प्रिंट करें।
            System.out.println();
        }

        // डेटाबेस संसाधनों को मुक्त करने के लिए स्टेटमेंट और कनेक्शन को बंद करें।
        stmt.close();
        conn.close();
    }
}
```

### प्रोग्राम का विवरण:

1. **JDBC ड्राइवर लोड करना (`Class.forName()`)**:
   - यह लाइन MySQL के लिए JDBC ड्राइवर को लोड और इंस्टेंटिएट करती है। यह प्रोग्राम को MySQL डेटाबेस के साथ इंटरैक्ट करने के लिए आवश्यक है।

2. **डेटाबेस कनेक्शन (`DriverManager.getConnection()`)**:
   - यह MySQL डेटाबेस `ST_TEST` से कनेक्शन स्थापित करता है, जिसमें उपयोगकर्ता नाम `root` और पासवर्ड `root` है।

3. **SQL क्वेरी निष्पादन (`stmt.executeQuery()`)**:
   - यह `SELECT` क्वेरी निष्पादित करता है ताकि `ST_PART` तालिका से `id`, `name`, और `color` कॉलमों को प्राप्त किया जा सके। इस क्वेरी का परिणाम `ResultSet` ऑब्जेक्ट में संग्रहीत होता है।

4. **ResultSetMetaData ऑब्जेक्ट (`rs.getMetaData()`)**:
   - यह ऑब्जेक्ट परिणाम सेट का मेटाडाटा (जैसे कॉलमों की संख्या, कॉलम नाम, प्रकार आदि) प्राप्त करता है।

5. **मेटाडाटा प्राप्त करना**:
   - प्रोग्राम पहले कॉलम के कैटलॉग नाम, तालिका नाम और कॉलमों का विवरण (जैसे लेबल, नाम, प्रकार, आकार, और प्रेसिशन) प्रिंट करता है।

6. **कॉलमों के माध्यम से लूप**:
   - प्रोग्राम सभी कॉलमों के मेटाडाटा के माध्यम से लूप करता है और संबंधित विवरण प्रिंट करता है।

7. **स्टेटमेंट और कनेक्शन बंद करना**:
   - डेटाबेस संसाधनों को मुक्त करने के लिए `stmt.close()` और `conn.close()` का उपयोग किया जाता है।
