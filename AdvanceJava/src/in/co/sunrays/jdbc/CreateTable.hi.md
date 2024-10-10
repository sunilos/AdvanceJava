```java
package in.co.sunrays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * CreateTable क्लास JDBC (Java Database Connectivity) का उपयोग करके एक तालिका बनाने का उदाहरण है।
 * यह MySQL डेटाबेस से कनेक्ट होता है और SQL स्टेटमेंट का उपयोग करके एक तालिका बनाता है।
 * 
 * बनाई गई तालिका का नाम ST_TESTTABLE है जिसमें दो कॉलम हैं:
 * - id: एक integer कॉलम (प्राइमरी की, not null)
 * - name: 100 वर्णों की अधिकतम लंबाई वाला varchar कॉलम
 * 
 * @लेखक SunilOS
 * @संस्करण 1.0
 * @तारीख 2023-09-26
 * @see java.sql.Connection
 * @see java.sql.Statement
 */
public class CreateTable {

    /**
     * main विधि प्रोग्राम का प्रवेश बिंदु है।
     * यह MySQL डेटाबेस से कनेक्ट होती है, तालिका बनाती है, और किसी भी अपवाद को संभालती है।
     * 
     * @param args अप्रयुक्त।
     */
    public static void main(String[] args) {

        try {

            // MySQL डेटाबेस के लिए JDBC ड्राइवर लोड और पंजीकृत करें।
            Class.forName("com.mysql.jdbc.Driver");

            // MySQL डेटाबेस से कनेक्शन स्थापित करें।
            // URL डेटाबेस का स्थान निर्दिष्ट करता है, उपयोगकर्ता नाम "root" है और पासवर्ड रिक्त है।
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/st_test", "root", "");

            // SQL क्वेरी निष्पादित करने के लिए एक स्टेटमेंट ऑब्जेक्ट बनाएँ।
            Statement stmt = conn.createStatement();

            // SQL क्वेरी जो ST_TESTTABLE तालिका बनाती है जिसमें दो कॉलम हैं: id और name।
            String sql = "CREATE TABLE ST_TESTTABLE "
                    + "(id INTEGER not NULL, name VARCHAR(100), "
                    + " PRIMARY KEY ( id ))";

            // उपयोगकर्ता को सूचित करें कि तालिका बनाने की प्रक्रिया शुरू हो गई है।
            System.out.println("तालिका बनाई जा रही है...");

            // तालिका बनाने के लिए SQL क्वेरी निष्पादित करें।
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            // अगर कुछ गलत हो जाता है तो SQL-संबंधी त्रुटि संदेश प्रिंट करें।
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            // JDBC ड्राइवर क्लास न मिलने पर अपवाद को संभालें।
            System.out.println(e.getMessage());
        }

        // उपयोगकर्ता को पुष्टि करें कि तालिका सफलतापूर्वक बनाई गई है।
        System.out.println("तालिका सफलतापूर्वक बनाई गई है।");
    }
}
```

### प्रोग्राम का विवरण:
1. **JDBC ड्राइवर लोड करना (`Class.forName()`)**:
   - यह लाइन MySQL के लिए JDBC ड्राइवर को लोड करती है। यह प्रोग्राम को MySQL डेटाबेस के साथ इंटरैक्ट करने के लिए आवश्यक है।

2. **डेटाबेस कनेक्शन (`DriverManager.getConnection()`)**:
   - यह MySQL डेटाबेस से कनेक्शन स्थापित करता है जो `localhost:3306` पर स्थित है और डेटाबेस का नाम `st_test` है। उपयोगकर्ता नाम `root` है और पासवर्ड नहीं है।

3. **SQL क्वेरी बनाना**:
   - SQL स्टेटमेंट `ST_TESTTABLE` नामक तालिका बनाता है जिसमें दो कॉलम हैं: `id` (integer, not null, primary key) और `name` (100 वर्णों की अधिकतम लंबाई वाला varchar)।

4. **स्टेटमेंट निष्पादन (`stmt.executeUpdate()`)**:
   - यह विधि SQL क्वेरी को निष्पादित करके डेटाबेस में तालिका बनाती है।

5. **अपवाद संभालना**:
   - प्रोग्राम SQL अपवादों (`SQLException`) और JDBC ड्राइवर क्लास न मिलने की स्थिति (`ClassNotFoundException`) में त्रुटियों को संभालता है।

6. **प्रोग्राम आउटपुट**:
   - प्रोग्राम यह सूचित करता है कि तालिका बन रही है और इसे सफलतापूर्वक बनाया गया है।
