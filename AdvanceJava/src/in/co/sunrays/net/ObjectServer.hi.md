```java
package in.co.sunrays.net;

import in.co.sunrays.bean.Employee;

import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ऑब्जेक्ट TCP सर्वर, नेटवर्क के माध्यम से धारित ऑब्जेक्ट का आदान-प्रदान करता है।
 * यह सर्वर 4444 पोर्ट पर आने वाले कनेक्शनों के लिए सुनता है।
 * एक बार कनेक्शन प्राप्त होने पर, यह धारित Employee ऑब्जेक्ट को प्रोसेस करता है।
 * 
 * @Copyright (c) SunilOS. सभी अधिकार सुरक्षित।
 * @URL www.SunilOS.com
 */
public class ObjectServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = null;

        // 4444 पोर्ट पर सुनने के लिए ServerSocket बनाएँ
        serverSocket = new ServerSocket(4444);

        System.out.println("ऑब्जेक्ट सर्वर शुरू हुआ");

        Socket clientSocket = null;

        // लगातार क्लाइंट कनेक्शन के लिए सुनें
        boolean flag = true;
        while (flag) {
            // क्लाइंट कनेक्शन स्वीकार करें
            clientSocket = serverSocket.accept();

            // क्लाइंट के साथ बात करें
            talk(clientSocket);
        }

        System.out.println("ऑब्जेक्ट सर्वर बंद हुआ");
        serverSocket.close(); // सर्वर सॉकेट बंद करें
    }

    /**
     * क्लाइंट के साथ बात करता है और प्राप्त Employee ऑब्जेक्ट को प्रोसेस करता है।
     *
     * @param client कनेक्टेड क्लाइंट के लिए सॉकेट
     * @throws Exception यदि संचार के दौरान कोई I/O त्रुटि होती है
     */
    public static void talk(Socket client) throws Exception {
        // क्लाइंट को प्रतिक्रिया भेजने के लिए PrintWriter बनाएँ
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        
        // धारित ऑब्जेक्ट प्राप्त करने के लिए ObjectInputStream बनाएँ
        ObjectInputStream in = new ObjectInputStream(client.getInputStream());

        // क्लाइंट द्वारा भेजे गए Employee ऑब्जेक्ट को पढ़ें
        Employee emp = (Employee) in.readObject();

        // प्राप्त Employee ऑब्जेक्ट का विवरण प्रिंट करें
        System.out.println("ID : " + emp.getId());
        System.out.println("पहला नाम : " + emp.getFirstName());
        System.out.println("आखिरी नाम : " + emp.getLastName());
        System.out.println("अस्थायी मान: " + emp.getTempValue());

        // क्लाइंट को पुष्टि संदेश भेजें
        out.println("ऑब्जेक्ट प्राप्त हुआ");

        // स्ट्रीम और सॉकेट बंद करें
        out.close();
        in.close();
        client.close();
    }
}
```

### कोड की व्याख्या:

1. **पैकेज घोषणा**:
   - यह क्लास `in.co.sunrays.net` पैकेज का हिस्सा है, जो संबंधित क्लासेस को समूहित करता है।

2. **आयात**:
   - `in.co.sunrays.bean` से `Employee` क्लास को आयात करता है ताकि कर्मचारी डेटा को हैंडल किया जा सके।
   - इनपुट/आउटपुट संचालन और नेटवर्क संचार के लिए `java.io` और `java.net` से आवश्यक क्लासेस का आयात करता है।

3. **JavaDoc टिप्पणियां**:
   - क्लास और विधियों को JavaDoc टिप्पणियों का उपयोग करके दस्तावेजीकृत किया गया है, जो उनके उद्देश्य, कार्यक्षमता और कॉपीराइट के बारे में जानकारी प्रदान करता है।

4. **मुख्य विधि**:
   - यह प्रोग्राम का प्रवेश बिंदु है। यह सर्वर सॉकेट को प्रारंभ करता है और आने वाले कनेक्शनों के लिए सुनता है।
   - यह एक संदेश प्रिंट करता है जो बताता है कि सर्वर शुरू हो गया है।

5. **ServerSocket प्रारंभिककरण**:
   - `ServerSocket serverSocket = new ServerSocket(4444);`: यह लाइन एक सर्वर सॉकेट बनाती है जो 4444 पोर्ट पर आने वाले कनेक्शनों के लिए सुनती है।

6. **क्लाइंट कनेक्शनों को स्वीकार करना**:
   - एक लूप (`while (flag)`) सर्वर को चालू रखता है, जो `serverSocket.accept()` का उपयोग करके क्लाइंट कनेक्शनों को स्वीकार करता है।
   - प्रत्येक स्वीकार किए गए क्लाइंट के लिए, `talk()` विधि को संचार को हैंडल करने के लिए कॉल किया जाता है।

7. **बातचीत विधि**:
   - यह विधि जुड़े हुए क्लाइंट के साथ बातचीत को संभालती है।
   - क्लाइंट को प्रतिक्रियाएँ भेजने के लिए **PrintWriter** बनाया जाता है।
   - धारित `Employee` ऑब्जेक्ट को पढ़ने के लिए **ObjectInputStream** का उपयोग किया जाता है।

8. **प्राप्त Employee ऑब्जेक्ट को प्रोसेस करना**:
   - सर्वर ऑब्जेक्ट को `in.readObject()` का उपयोग करके पढ़ता है और इसे `Employee` में कास्ट करता है।
   - यह `Employee` ऑब्जेक्ट की विशेषताओं को प्रिंट करता है, जैसे ID, पहला नाम, अंतिम नाम और अस्थायी मान।

9. **प्रतिक्रिया भेजना**:
   - ऑब्जेक्ट को प्रोसेस करने के बाद, क्लाइंट को एक पुष्टि संदेश ("ऑब्जेक्ट प्राप्त हुआ") भेजा जाता है।

10. **संसाधनों को बंद करना**:
    - आउटपुट स्ट्रीम, इनपुट स्ट्रीम, और क्लाइंट सॉकेट को संसाधनों को मुक्त करने के लिए बंद किया जाता है।

### सारांश:
`ObjectServer` क्लास यह प्रदर्शित करती है कि कैसे Java में एक TCP सर्वर बनाया जाता है जो आने वाले क्लाइंट कनेक्शनों के लिए सुनता है, एक धारित ऑब्जेक्ट प्राप्त करता है, उसे प्रोसेस करता है, और प्रतिक्रिया वापस भेजता है। यह उदाहरण नेटवर्क प्रोग्रामिंग के सिद्धांतों, जैसे सॉकेट, स्ट्रीम, और Java में ऑब्जेक्ट धारणा को प्रदर्शित करता है।
