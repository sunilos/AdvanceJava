```java
package in.co.sunrays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * यह क्लास डेटाबेस तालिका में रिकॉर्ड डालने की कार्यक्षमता प्रदान करती है
 * लेन-देन प्रबंधन के साथ। यह SQL INSERT कमांड निष्पादित करने के लिए 
 * स्टेटमेंट और प्रीपेयर्ड स्टेटमेंट दोनों के उपयोग को प्रदर्शित करता है।
 * 
 * @Copyright (c) SunilOS. सभी अधिकार सुरक्षित।
 * @URL www.SunilOS.com
 */
public class InsertRecordWithTx {

    /**
     * स्टेटमेंट का उपयोग करके रिकॉर्ड डालने के लिए मुख्य विधि।
     * 
     * @param args कमांड लाइन तर्क (उपयोग नहीं किया गया)।
     * @throws Exception यदि डेटाबेस संचालन के दौरान कोई त्रुटि होती है।
     */
    public static void main(String args[]) throws Exception {
        // MySQL JDBC ड्राइवर लोड करें
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        // डेटाबेस से कनेक्शन स्थापित करें
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/test", "root", "");

        // स्वचालित कमिट मोड को बंद करके लेन-देन शुरू करें
        conn.setAutoCommit(false);

        // SQL क्वेरियों को निष्पादित करने के लिए स्टेटमेंट ऑब्जेक्ट बनाएं
        Statement stmt = conn.createStatement();

        // INSERT SQL कमांड निष्पादित करें और प्रभावित पंक्तियों की संख्या प्राप्त करें
        int i = stmt.executeUpdate("INSERT into part values (5,'Plat','Green',1)");

        // अपडेट किए गए रिकॉर्ड की संख्या आउटपुट करें
        System.out.print(i + " रिकॉर्ड(ों) को अपडेट किया गया");

        // डेटाबेस में लेन-देन को कमिट करें
        conn.commit();

        // स्टेटमेंट और कनेक्शन ऑब्जेक्ट बंद करें
        stmt.close();
        conn.close();
    }

    /**
     * प्रीपेयर्ड स्टेटमेंट का उपयोग करके INSERT स्टेटमेंट निष्पादित करता है
     * जो सुरक्षा और प्रदर्शन को बढ़ाता है।
     * 
     * @throws Exception यदि डेटाबेस संचालन के दौरान कोई त्रुटि होती है।
     */
    public static void testPreparedInsert() throws Exception {
        // MySQL JDBC ड्राइवर लोड करें
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        try {
            // डेटाबेस से कनेक्शन स्थापित करें
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/test", "root", "root");

            // स्वचालित कमिट मोड को बंद करके लेन-देन शुरू करें
            conn.setAutoCommit(false);

            // प्लेसहोल्डर के साथ SQL INSERT स्टेटमेंट तैयार करें
            PreparedStatement ps = conn.prepareStatement("INSERT into part values (?,?,?,?)");

            // प्रीपेयर्ड स्टेटमेंट में प्लेसहोल्डर्स के लिए मान सेट करें
            ps.setInt(1, 7);
            ps.setString(2, "Plat2");
            ps.setString(3, "Red");
            ps.setInt(4, 1);

            // प्रीपेयर्ड स्टेटमेंट को निष्पादित करें और प्रभावित पंक्तियों की संख्या प्राप्त करें
            int recCount = ps.executeUpdate();

            // डाले गए रिकॉर्ड की संख्या आउटपुट करें
            System.out.println("# रिकॉर्ड: " + recCount);
            System.out.print(recCount + " रिकॉर्ड(ों) को अपडेट किया गया");

            // डेटाबेस में लेन-देन को कमिट करें
            conn.commit();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            // त्रुटि के मामले में लेन-देन को रोलबैक करें
            conn.rollback();
            // डिबगिंग के लिए स्टैक ट्रेस प्रिंट करें
            e.printStackTrace();
        }
    }
}
```

### परिवर्तनों का विवरण:
1. **Javadoc टिप्पणियाँ**: कक्षा और विधियों के लिए Javadoc टिप्पणियाँ जोड़ी गईं, जिनमें उनके उद्देश्य, पैरामीटर और अपवादों का विवरण दिया गया है।
2. **इनलाइन टिप्पणियाँ**: कोड के विभिन्न महत्वपूर्ण कदमों को समझाने के लिए कोड में टिप्पणियाँ जोड़ी गईं, खासकर कनेक्शन प्रबंधन, लेन-देन प्रबंधन, और SQL निष्पादन के आसपास।
3. **त्रुटि प्रबंधन**: `testPreparedInsert` के `catch` ब्लॉक में एक रोलबैक कथन जोड़ा गया है ताकि त्रुटि की स्थिति में लेन-देन को वापस किया जा सके, जिससे डेटाबेस की संपूर्णता बनी रहे। 
