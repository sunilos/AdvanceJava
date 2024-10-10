```java
package in.co.sunrays.net;

import in.co.sunrays.bean.Employee;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * ऑब्जेक्ट TCP क्लाइंट जो नेटवर्क पर सीरियलाइज़्ड ऑब्जेक्ट्स का आदान-प्रदान करता है।
 * यह कक्षा एक TCP सर्वर से कनेक्शन स्थापित करती है, एक सीरियलाइज़्ड Employee ऑब्जेक्ट भेजती है,
 * और सर्वर से प्रतिक्रिया प्राप्त करती है।
 * 
 * @Copyright (c) SunilOS. सभी अधिकार सुरक्षित।
 * @URL www.SunilOS.com
 */
public class ObjectClient {
    public static void main(String[] args) throws Exception {
        // सर्वर से कनेक्ट करने के लिए सॉकेट बनाना जो लोकलहोस्ट पर 4444 पोर्ट पर चल रहा है
        Socket echoSocket = new Socket("127.0.0.1", 4444);

        // सर्वर को सीरियलाइज़्ड ऑब्जेक्ट्स भेजने के लिए ObjectOutputStream बनाना
        ObjectOutputStream out = new ObjectOutputStream(echoSocket.getOutputStream());

        // सर्वर की प्रतिक्रिया पढ़ने के लिए BufferedReader बनाना
        BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

        // एक Employee ऑब्जेक्ट बनाना जिसमें ID, नाम, और कंपनी है
        Employee emp = new Employee(1, "Sunrays", "Technologies");
        // Employee के लिए अस्थायी पता मान सेट करना
        emp.setTempValue("8 A Shalimar Corporate");

        // Employee ऑब्जेक्ट को सर्वर पर भेजना
        out.writeObject(emp);

        // सर्वर से एक पंक्ति की प्रतिक्रिया पढ़ना
        String line = in.readLine();

        // सर्वर से प्राप्त जानकारी को प्रिंट करना
        System.out.println("Received From Server: " + line);

        // आउटपुट स्ट्रीम, इनपुट स्ट्रीम, और सॉकेट कनेक्शन को बंद करना
        out.close();
        in.close();
        echoSocket.close();
    }
}
```

### कोड का स्पष्टीकरण:

1. **पैकेज घोषणा**:
   - कक्षा को `in.co.sunrays.net` पैकेज के अंतर्गत घोषित किया गया है, जो संबंधित कक्षाओं को समूहित करता है।

2. **आयात**:
   - `Employee` कक्षा को `in.co.sunrays.bean` से आयात किया गया है, जो उस कस्टम ऑब्जेक्ट को संभालने के लिए है जो नेटवर्क पर भेजा जाएगा।
   - `java.io` और `java.net` से आवश्यक कक्षाएँ आयात की गई हैं।

3. **JavaDoc टिप्पणियाँ**:
   - कक्षा को JavaDoc टिप्पणियों के साथ दस्तावेजीकृत किया गया है जो इसके उद्देश्य, कार्यक्षमता, और कॉपीराइट जानकारी का वर्णन करती हैं।

4. **मुख्य विधि**:
   - मुख्य विधि कार्यक्रम का प्रवेश बिंदु है। यह अपवाद फेंक सकती है, जो त्रुटियों के प्रबंधन की अनुमति देती है।

5. **सॉकेट निर्माण**:
   - `Socket echoSocket = new Socket("127.0.0.1", 4444);` यह लाइन स्थानीय सर्वर पर `4444` पोर्ट पर एक TCP सॉकेट स्थापित करती है।

6. **आउटपुट स्ट्रीम**:
   - `ObjectOutputStream out = new ObjectOutputStream(echoSocket.getOutputStream());` यह आउटपुट स्ट्रीम बनाने के लिए `ObjectOutputStream` का उपयोग करती है, जो ऑब्जेक्ट्स को सीरियलाइज़ करने में सक्षम बनाती है।

7. **इनपुट स्ट्रीम**:
   - `BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));` यह लाइन इनपुट स्ट्रीम बनाने के लिए `BufferedReader` का उपयोग करती है, जो सर्वर से डेटा पढ़ने की अनुमति देती है।

8. **ऑब्जेक्ट निर्माण**:
   - एक `Employee` ऑब्जेक्ट `ID`, नाम, और कंपनी के साथ बनाया गया है। `emp.setTempValue("8 A Shalimar Corporate");` यह अस्थायी मान को सेट करता है।

9. **ऑब्जेक्ट भेजना**:
   - `out.writeObject(emp);` यह लाइन `Employee` ऑब्जेक्ट को सर्वर पर भेजती है।

10. **सर्वर से प्रतिक्रिया प्राप्त करना**:
    - `String line = in.readLine();` यह लाइन सर्वर से एक पंक्ति पढ़ती है, जो यह मानती है कि सर्वर कुछ प्रतिक्रिया भेजता है।

11. **प्रतिक्रिया प्रिंट करना**:
    - `System.out.println("Received From Server: " + line);` यह लाइन सर्वर से प्राप्त जानकारी को कंसोल पर प्रिंट करती है।

12. **स्ट्रीम और सॉकेट को बंद करना**:
    - अंत में, आउटपुट स्ट्रीम, इनपुट स्ट्रीम, और सॉकेट कनेक्शन को बंद किया जाता है `out.close();`, `in.close();`, और `echoSocket.close();`, यह सुनिश्चित करते हुए कि संसाधनों को सही ढंग से प्रबंधित किया गया है।

### सारांश:
यह कोड स्निपेट Java में एक TCP क्लाइंट बनाने का एक सरल उदाहरण है जो सर्वर से कनेक्ट करता है, एक सीरियलाइज़्ड ऑब्जेक्ट भेजता है, और प्रतिक्रिया प्राप्त करता है। यह नेटवर्क संचार के लिए सॉकेट के उपयोग और Java ऑब्जेक्ट्स के सिरीयलाइज़ेशन के सिद्धांतों को प्रदर्शित करता है।
