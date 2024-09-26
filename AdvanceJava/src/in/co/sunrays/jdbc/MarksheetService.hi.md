```java
package in.co.sunrays.jdbc;

import in.co.sunrays.bean.Marksheet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * सेवा वर्ग जो मार्कशीट रिकॉर्ड बनाने, पढ़ने, अपडेट करने और हटाने के लिए संचालन प्रदान करता है।
 * यह वर्ग CRUD (बनाना, पढ़ना, अपडेट करना, हटाना) संचालन को करने के लिए डेटाबेस के साथ बातचीत करता है
 * मार्कशीट डेटा पर।
 * 
 * @Copyright (c) SunilOS. सभी अधिकार सुरक्षित।
 * @URL www.SunilOS.com
 */
public class MarksheetService {

    // डेटाबेस कॉन्फ़िगरेशन को प्रॉपर्टीज फ़ाइल से पढ़ने के लिए रिसोर्स बंडल
    private static ResourceBundle rb = ResourceBundle.getBundle("in.co.sunrays.jdbc.system");

    // स्टेटिक ब्लॉक जो वर्ग को मेमोरी में लोड होने पर निष्पादित होता है, डेटाबेस ड्राइवर लोड करने के लिए उपयोग किया जाता है
    static {
        String driverName = rb.getString("database.driver");
        try {
            // डेटाबेस के लिए JDBC ड्राइवर लोड करें
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // यदि ड्राइवर लोड करने में विफल होता है तो स्टैक ट्रेस प्रिंट करें
        }
    }

    /**
     * दिए गए रोल नंबर द्वारा मार्कशीट प्राप्त करता है।
     * 
     * @param rollNo उस छात्र का रोल नंबर जिसका मार्कशीट प्राप्त किया जाना है।
     * @return मार्कशीट ऑब्जेक्ट जिसमें छात्र का विवरण होता है, या नहीं मिलने पर null।
     * @throws Exception यदि डेटाबेस संचालन के दौरान कोई त्रुटि होती है।
     */
    public Marksheet getMarkSheet(String rollNo) throws Exception {
        // डेटाबेस से कनेक्शन स्थापित करें
        Connection conn = DriverManager.getConnection(
                rb.getString("database.url"), rb.getString("database.user"),
                rb.getString("database.password"));

        Statement stmt = conn.createStatement();

        // दिए गए रोल नंबर के लिए मार्कशीट विवरण प्राप्त करने के लिए SQL क्वेरी निष्पादित करें
        ResultSet rs = stmt.executeQuery("SELECT rollNo, name, physics, chemistry, maths FROM ST_STUDENT WHERE rollNo = '" + rollNo + "'");

        Marksheet bean = null;

        // यदि रिकॉर्ड मिला है तो मार्कशीट बीन को पॉपुलेट करें
        if (rs.next()) {
            bean = new Marksheet();
            bean.setRollNo(rollNo);
            bean.setName(rs.getString(2));
            bean.setPhysics(rs.getInt(3));
            bean.setChemistry(rs.getInt(4));
            bean.setMaths(rs.getInt(5));
        }

        // संसाधनों को साफ करें
        rs.close(); // ResultSet बंद करें
        stmt.close(); // Statement बंद करें
        conn.close(); // कनेक्शन बंद करें

        return bean; // पॉपुलेटेड मार्कशीट बीन या null लौटाएं
    }

    /**
     * सभी मार्कशीट रिकॉर्ड की सूची प्राप्त करता है।
     * 
     * @return मार्कशीट ऑब्जेक्ट की ArrayList जिसमें सभी छात्रों का विवरण होता है।
     * @throws Exception यदि डेटाबेस संचालन के दौरान कोई त्रुटि होती है।
     */
    public ArrayList<Marksheet> getMarksheetList() throws Exception {
        // डेटाबेस से कनेक्शन स्थापित करें
        Connection conn = DriverManager.getConnection(
                rb.getString("database.url"), rb.getString("database.user"),
                rb.getString("database.password"));

        Statement stmt = conn.createStatement();

        String sql = "SELECT rollNo, name, physics, chemistry, maths FROM ST_STUDENT ORDER BY rollNo";

        System.out.println(sql); // डिबगिंग के लिए SQL क्वेरी लॉग करें

        ResultSet rs = stmt.executeQuery(sql);

        ArrayList<Marksheet> marksheetList = new ArrayList<>(); // मार्कशीट ऑब्जेक्ट रखने के लिए सूची
        Marksheet bean;

        // परिणाम सेट के माध्यम से इटरट करें और सूची को पॉपुलेट करें
        while (rs.next()) {
            bean = new Marksheet();
            bean.setRollNo(rs.getString(1));
            bean.setName(rs.getString(2));
            bean.setPhysics(rs.getInt(3));
            bean.setChemistry(rs.getInt(4));
            bean.setMaths(rs.getInt(5));
            marksheetList.add(bean); // पॉपुलेटेड मार्कशीट को सूची में जोड़ें
        }

        return marksheetList; // मार्कशीट ऑब्जेक्ट की सूची लौटाएं
    }

    /**
     * कुल अंकों के आधार पर शीर्ष 10 छात्रों की मेरिट सूची प्राप्त करता है।
     * 
     * @return मार्कशीट ऑब्जेक्ट की ArrayList जिसमें शीर्ष छात्रों का विवरण होता है।
     * @throws Exception यदि डेटाबेस संचालन के दौरान कोई त्रुटि होती है।
     */
    public ArrayList<Marksheet> getMeritList() throws Exception {
        // डेटाबेस से कनेक्शन स्थापित करें
        Connection conn = DriverManager.getConnection(
                rb.getString("database.url"), rb.getString("database.user"),
                rb.getString("database.password"));

        Statement stmt = conn.createStatement();

        // कुल अंकों के आधार पर शीर्ष 10 छात्रों को प्राप्त करने के लिए SQL क्वेरी
        ResultSet rs = stmt.executeQuery("SELECT rollNo, name, physics, chemistry, maths, (physics + chemistry + maths) AS total FROM ST_STUDENT ORDER BY total DESC LIMIT 10");

        ArrayList<Marksheet> marksheetList = new ArrayList<>(); // मार्कशीट ऑब्जेक्ट रखने के लिए सूची
        Marksheet bean;

        int count = 0; // रिकॉर्ड की संख्या सीमित करने के लिए काउंटर

        // परिणाम सेट के माध्यम से इटरट करें और सूची को पॉपुलेट करें
        while (rs.next()) {
            bean = new Marksheet();
            bean.setRollNo(rs.getString(1));
            bean.setName(rs.getString(2));
            bean.setPhysics(rs.getInt(3));
            bean.setChemistry(rs.getInt(4));
            bean.setMaths(rs.getInt(5));
            marksheetList.add(bean); // पॉपुलेटेड मार्कशीट को सूची में जोड़ें
            count++;
            if (count == 10) { // 10 रिकॉर्ड्स तक सीमित करें
                break;
            }
        }

        return marksheetList; // शीर्ष मार्कशीट ऑब्जेक्ट की सूची लौटाएं
    }

    /**
     * डेटाबेस में एक नया मार्कशीट रिकॉर्ड जोड़ता है।
     * 
     * @param bean मार्कशीट ऑब्जेक्ट जिसमें छात्र का विवरण होता है जिसे जोड़ा जाना है।
     * @return अपडेटेड रिकॉर्ड की संख्या (सफल insert के लिए 1 होना चाहिए)।
     * @throws Exception यदि डेटाबेस संचालन के दौरान कोई त्रुटि होती है।
     */
    public int addMarkSheet(Marksheet bean) throws Exception {
        // डेटाबेस से कनेक्शन स्थापित करें
        Connection conn = DriverManager.getConnection(
                rb.getString("database.url"), rb.getString("database.user"),
                rb.getString("database.password"));

        Statement stmt = conn.createStatement();

        // एक नया मार्कशीट रिकॉर्ड जोड़ने के लिए SQL क्वेरी
        String sql = "INSERT INTO ST_STUDENT (rollNo, name, chemistry, physics, maths) VALUES ('"
                + bean.getRollNo() + "', '"
                + bean.getName() + "', "
                + bean.getChemistry() + ", "
                + bean.getPhysics() + ", "
                + bean.getMaths() + ")";

        System.out.println(sql); // डिबगिंग के लिए SQL क्वेरी लॉग करें

        int recordCount = stmt.executeUpdate(sql); // insert संचालन निष्पादित करें

        // संसाधनों को साफ करें
        stmt.close(); // Statement बंद करें
        conn.close(); // कनेक्शन बंद करें

        return recordCount; // अपडेटेड रिकॉर्ड की संख्या लौटाएं
    }

    /**
     * डेटाबेस में एक मौजूदा मार्कशीट रिकॉर्ड को अपडेट करता है।
     * 
     * @param bean मार्कशीट ऑब्जेक्ट जिसमें छात्र के अपडेट किए गए विवरण होते हैं।
     * @return अपडेटेड रिकॉर्ड की संख्या (सफल अपडेट के लिए 1 होना चाहिए)।
     * @throws Exception यदि डेटाबेस संचालन के दौरान कोई त्रुटि होती है।
     */
    public int updateMarkSheet(Marksheet bean) throws Exception {
        // डेटाबेस से कनेक्शन स्थापित करें
        Connection conn = DriverManager.getConnection(
                rb.getString("database.url"), rb.getString("database.user"),
                rb.getString("database.password"));

        Statement stmt = conn.createStatement();

        // मौजूदा मार्कशीट रिकॉर्ड को अपडेट करने के लिए SQL क्वेरी
        String sql = "UPDATE ST_STUDENT SET physics = " + bean.getPhysics()
                + ", chemistry = " + bean.getChemistry()
                + ", maths = " + bean.getMaths()
                + ", name = '" +

 bean.getName()
                + "' WHERE rollNo = '" + bean.getRollNo() + "'";

        System.out.println(sql); // डिबगिंग के लिए SQL क्वेरी लॉग करें

        int recordCount = stmt.executeUpdate(sql); // अपडेट संचालन निष्पादित करें

        // संसाधनों को साफ करें
        stmt.close(); // Statement बंद करें
        conn.close(); // कनेक्शन बंद करें

        return recordCount; // अपडेटेड रिकॉर्ड की संख्या लौटाएं
    }

    /**
     * डेटाबेस से एक मार्कशीट रिकॉर्ड को हटाता है।
     * 
     * @param bean मार्कशीट ऑब्जेक्ट जिसमें उस छात्र का रोल नंबर होता है जिसे हटाया जाना है।
     * @return हटाए गए रिकॉर्ड की संख्या (सफल हटाने के लिए 1 होना चाहिए)।
     * @throws Exception यदि डेटाबेस संचालन के दौरान कोई त्रुटि होती है।
     */
    public int deleteMarkSheet(Marksheet bean) throws Exception {
        // डेटाबेस से कनेक्शन स्थापित करें
        Connection conn = DriverManager.getConnection(
                rb.getString("database.url"), rb.getString("database.user"),
                rb.getString("database.password"));

        Statement stmt = conn.createStatement();

        // दिए गए रोल नंबर के साथ मार्कशीट को हटाने के लिए SQL क्वेरी
        String sql = "DELETE FROM ST_STUDENT WHERE rollNo = '" + bean.getRollNo() + "'";

        int recordCount = stmt.executeUpdate(sql); // हटाने का संचालन निष्पादित करें

        // संसाधनों को साफ करें
        stmt.close(); // Statement बंद करें
        conn.close(); // कनेक्शन बंद करें

        return recordCount; // हटाए गए रिकॉर्ड की संख्या लौटाएं
    }
}
```

### संक्षिप्त व्याख्या:
1. **पैकेज और इम्पोर्ट**: कोड में `in.co.sunrays.jdbc` पैकेज और आवश्यक क्लासेस को आयात किया गया है।
2. **ResourceBundle**: डेटाबेस कनेक्शन के लिए आवश्यक कॉन्फ़िगरेशन डेटा को एक `.properties` फ़ाइल से पढ़ने के लिए उपयोग किया जाता है।
3. **संपर्क प्रबंधन**: प्रत्येक मेथड में डेटाबेस कनेक्शन स्थापित किया जाता है, और संचालन के बाद संसाधनों को बंद किया जाता है।
4. **CRUD ऑपरेशन**: `getMarkSheet`, `getMarksheetList`, `getMeritList`, `addMarkSheet`, `updateMarkSheet`, और `deleteMarkSheet` मेथड्स विभिन्न CRUD कार्यों के लिए जिम्मेदार हैं।
