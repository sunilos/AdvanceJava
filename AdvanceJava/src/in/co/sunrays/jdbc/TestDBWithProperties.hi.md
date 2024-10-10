```java
package in.co.sunrays.jdbc;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * यह क्लास JDBC का उपयोग करके डेटाबेस से कनेक्ट करने का प्रदर्शन करती है
 * जबकि हार्ड-कोडेड क्रेडेंशियल्स से बचने के लिए एक प्रॉपर्टीज फ़ाइल का उपयोग करती है।
 * 
 * @Copyright (c) SunilOS. सभी अधिकार सुरक्षित।
 * @URL www.SunilOS.com
 */
public class TestDBWithProperties {

    /**
     * मुख्य मेथड जो एप्लिकेशन के लिए एंट्री पॉइंट के रूप में कार्य करता है।
     * यह डेटाबेस चयन संचालन का परीक्षण करने के लिए मेथड को कॉल करता है।
     *
     * @param args कमांड लाइन तर्क (अप्रयुक्त)।
     * @throws Exception यदि कोई त्रुटि होती है तो।
     */
    public static void main(String args[]) throws Exception {
        testSelect(); // SELECT संचालन करने के लिए मेथड को कॉल करें
        // testInsert(); // यदि आवश्यक हो तो INSERT मेथड को कॉल करने के लिए अनकमेंट करें
    }

    /**
     * यह मेथड डेटाबेस से कनेक्शन स्थापित करता है और 
     * ST_PART तालिका से डेटा प्राप्त करता है, 
     * परिणाम को कंसोल में प्रदर्शित करता है।
     *
     * @throws Exception यदि डेटाबेस कनेक्शन 
     *                   या SQL संचालन के दौरान कोई त्रुटि होती है।
     */
    public static void testSelect() throws Exception {
        
        // ResourceBundle का उपयोग करके प्रॉपर्टीज फ़ाइल से डेटाबेस क्रेडेंशियल्स पढ़ें
        ResourceBundle rb = ResourceBundle.getBundle("in.co.sunrays.jdbc.system");

        // डेटाबेस कनेक्शन पैरामीटर प्राप्त करें
        String driverName = rb.getString("database.driver");
        String url = rb.getString("database.url");
        String loginId = rb.getString("database.user");
        String password = rb.getString("database.password");

        // JDBC ड्राइवर लोड करें
        Class.forName(driverName);

        // डेटाबेस से कनेक्शन स्थापित करें
        Connection conn = DriverManager.getConnection(url, loginId, password);

        // ऑटो-कमिट मोड को मैनुअल लेन-देन नियंत्रण के लिए बंद करें
        conn.setAutoCommit(false);

        // SQL क्वेरी निष्पादित करने के लिए एक स्टेटमेंट ऑब्जेक्ट बनाएं
        Statement stmt = conn.createStatement();

        // डेटा प्राप्त करने के लिए SQL SELECT क्वेरी को निष्पादित करें
        ResultSet rs = stmt.executeQuery("SELECT id, name, color FROM ST_PART");

        // परिणाम को तालिका के रूप में प्रदर्शित करें
        System.out.println("ID\tName\tColor");
        System.out.println("--\t----\t-----");

        // परिणाम सेट के माध्यम से इटरेट करें और प्रत्येक पंक्ति का डेटा प्रिंट करें
        while (rs.next()) {
            System.out.print(rs.getString(1)); // ID प्राप्त करें और प्रिंट करें
            System.out.print("\t" + rs.getString(2)); // नाम प्राप्त करें और प्रिंट करें
            System.out.println("\t" + rs.getString("color")); // रंग प्राप्त करें और प्रिंट करें
        }

        // किए गए परिवर्तनों को कमिट करें (हालांकि यह SELECT के लिए आवश्यक नहीं है)
        conn.commit();

        // संसाधनों को मुक्त करने के लिए स्टेटमेंट और कनेक्शन को बंद करें
        stmt.close();
        conn.close();
    }
}
```

### स्पष्टीकरण:
1. **पैकेज घोषणा**: यह क्लास `in.co.sunrays.jdbc` पैकेज का हिस्सा है, जो JDBC संचालन में इसकी भूमिका को दर्शाता है।

2. **आयात**: आवश्यक Java SQL और ResourceBundle कक्षाओं को डेटाबेस संचालन और प्रॉपर्टीज फ़ाइल हैंडलिंग के लिए आयात किया गया है।

3. **क्लास दस्तावेज़ीकरण**: क्लास-स्तरीय Javadoc एक स्पष्ट अवलोकन प्रदान करता है कि इसका उद्देश्य क्या है, यह बताता है कि यह क्रेडेंशियल्स को हार्डकोडिंग से बचाता है।

4. **मुख्य मेथड**: 
   - `main` मेथड एंट्री पॉइंट है, जो `testSelect` मेथड को डेटाबेस से डेटा प्राप्त करने और प्रदर्शित करने के लिए बुलाता है।
   - `testInsert()` के लिए एक टिप्पणी की गई पंक्ति यह संकेत देती है कि भविष्य में एक INSERT संचालन भी लागू किया जा सकता है।

5. **testSelect मेथड**:
   - **ResourceBundle**: प्रॉपर्टीज फ़ाइल से डेटाबेस क्रेडेंशियल्स को लोड करता है, जिससे कोड अधिक लचीला और सुरक्षित बनता है।
   - **कनेक्शन प्रबंधन**: 
     - JDBC ड्राइवर को लोड करता है और प्रॉपर्टीज फ़ाइल से प्राप्त क्रेडेंशियल्स का उपयोग करके डेटाबेस से कनेक्शन स्थापित करता है।
     - मैनुअल लेन-देन नियंत्रण के लिए ऑटो-कमिट को बंद करता है (हालांकि SELECT के लिए यह आवश्यक नहीं है)।
   - **क्वेरी निष्पादन**: 
     - SQL SELECT क्वेरी निष्पादित करता है और परिणामों को संसाधित करता है।
     - परिणामों को एक तालिका के रूप में प्रदर्शित करता है।
   - **संसाधन प्रबंधन**: 
     - परिवर्तन को कमिट करता है (जो SELECT के लिए आवश्यक नहीं है) और यह सुनिश्चित करता है कि सभी संसाधन (स्टेटमेंट और कनेक्शन) ठीक से बंद हों ताकि मेमोरी लीक से बचा जा सके।
