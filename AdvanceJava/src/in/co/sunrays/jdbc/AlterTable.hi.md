```java
package in.co.sunrays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * इस प्रोग्राम में JDBC (Java Database Connectivity) का उपयोग करके टेबल में बदलाव (Alter) करना दिखाया गया है।
 * विशेष रूप से, यह एक मौजूदा टेबल में 'DOB' नाम का एक नया कॉलम जोड़ता है, जिसका डेटा टाइप 'date' है।
 * 
 * चरण:
 * - MySQL JDBC ड्राइवर को लोड करें।
 * - MySQL डेटाबेस से कनेक्शन स्थापित करें।
 * - SQL क्वेरी को निष्पादित करके टेबल को बदलें।
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */
public class AlterTable {
    public static void main(String[] args) {

        try {
            // चरण 1: MySQL JDBC ड्राइवर को लोड करें
            // यह एप्लिकेशन को MySQL डेटाबेस से इंटरैक्ट करने में सक्षम बनाता है
            Class.forName("com.mysql.jdbc.Driver");

            // चरण 2: MySQL डेटाबेस से कनेक्शन स्थापित करें
            // यह 'st_test' नामक डेटाबेस से लोकल सर्वर (localhost) पर कनेक्ट होता है
            // यूज़रनेम 'root' है और पासवर्ड खाली है
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/st_test", "root", "");

            // चरण 3: SQL क्वेरी भेजने के लिए एक स्टेटमेंट ऑब्जेक्ट बनाएं
            Statement stmt = conn.createStatement();

            // चरण 4: SQL क्वेरी लिखें जिससे 'ST_TESTTABLE' में बदलाव किया जाएगा
            // यह क्वेरी एक नया कॉलम 'DOB' जोड़ता है, जिसका प्रकार 'date' है
            String sql = "ALTER TABLE ST_TESTTABLE ADD DOB date";

            // उपयोगकर्ता को सूचित करें कि टेबल में बदलाव किया जा रहा है
            System.out.println("टेबल में बदलाव किया जा रहा है...");

            // चरण 5: SQL अपडेट निष्पादित करें
            // यह SQL क्वेरी को निष्पादित करेगा और टेबल में नया कॉलम जोड़ेगा
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            // चरण 6: SQL से संबंधित एरर को हैंडल करें
            // SQL या कनेक्शन में कोई समस्या होने पर एरर मैसेज प्रिंट करें
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            // चरण 7: JDBC ड्राइवर से संबंधित एरर को हैंडल करें
            // यदि JDBC ड्राइवर नहीं मिला तो एरर मैसेज प्रिंट करें
            System.out.println(e.getMessage());
        }

        // चरण 8: टेबल में सफलतापूर्वक बदलाव होने पर संदेश दें
        // उपयोगकर्ता को सूचित करें कि टेबल को सफलतापूर्वक बदला गया है
        System.out.println("टेबल को सफलतापूर्वक बदला गया है");
    }
}
```

### हिंदी में कोड की व्याख्या:

1. **क्लास घोषणा**:
   - `AlterTable` क्लास JDBC का उपयोग करके एक मौजूदा टेबल में बदलाव करने का प्रदर्शन करता है।

2. **ड्राइवर लोडिंग**:
   - `Class.forName("com.mysql.jdbc.Driver");` MySQL JDBC ड्राइवर को डायनामिक रूप से लोड करता है। यह लाइन सुनिश्चित करती है कि एप्लिकेशन MySQL डेटाबेस से कनेक्ट कर सके।

3. **कनेक्शन स्थापना**:
   - `DriverManager.getConnection("jdbc:mysql://localhost:3306/st_test", "root", "");` MySQL डेटाबेस से कनेक्शन स्थापित करता है। यहां डेटाबेस का नाम `st_test` है, और यूज़रनेम `root` है जिसका पासवर्ड खाली है।

4. **स्टेटमेंट ऑब्जेक्ट**:
   - `Statement stmt = conn.createStatement();` एक स्टेटमेंट ऑब्जेक्ट बनाता है, जिसका उपयोग SQL क्वेरी को डेटाबेस में भेजने के लिए किया जाता है।

5. **SQL क्वेरी**:
   - `String sql = "ALTER TABLE ST_TESTTABLE ADD DOB date";` SQL कमांड है जो 'ST_TESTTABLE' में एक नया कॉलम `DOB` (Date of Birth) जोड़ता है, जिसका प्रकार `date` है।

6. **SQL क्वेरी निष्पादन**:
   - `stmt.executeUpdate(sql);` SQL कमांड को डेटाबेस में भेजता है और टेबल की संरचना में नया कॉलम जोड़ता है।

7. **त्रुटि हैंडलिंग**:
   - दो `catch` ब्लॉक का उपयोग करके संभावित त्रुटियों को हैंडल किया जाता है:
     - `SQLException`: SQL या कनेक्शन से संबंधित त्रुटियों को हैंडल करता है।
     - `ClassNotFoundException`: JDBC ड्राइवर से संबंधित त्रुटियों को हैंडल करता है।

8. **सफलता संदेश**:
   - सफलतापूर्वक क्वेरी निष्पादन के बाद `"टेबल को सफलतापूर्वक बदला गया है"` संदेश प्रिंट किया जाता है।
