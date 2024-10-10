```java
package in.co.sunrays.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * एक ईको TCP सर्वर जो कई क्लाइंट को समवर्ती रूप से संभालता है।
 * यह सर्वर क्लाइंट से प्राप्त संदेशों को ईको करता है
 * जब तक कि क्लाइंट "Bye." संदेश नहीं भेजता है, जिससे कनेक्शन समाप्त हो जाता है।
 * 
 * @लेखक SunilOS
 * @संस्करण 1.0
 * @तारीख 2024
 * 
 * @Copyright (c) SunilOS. सभी अधिकार सुरक्षित।
 * @URL www.SunilOS.com
 */
public class MultiThreadedEchoServer extends Thread {

    private Socket client = null;

    /**
     * क्लाइंट सॉकेट के साथ MultiThreadedEchoServer को प्रारंभ करने के लिए कंस्ट्रक्टर।
     * 
     * @param clientSocket वह सॉकेट है जिसके माध्यम से सर्वर क्लाइंट के साथ संवाद करता है
     */
    public MultiThreadedEchoServer(Socket clientSocket) {
        this.client = clientSocket;
    }

    /**
     * रन विधि तब निष्पादित होती है जब थ्रेड शुरू होता है।
     * यह क्लाइंट के साथ संचार को संभालती है, इनपुट पढ़ती है
     * और ईको के रूप में वही इनपुट भेजती है जब तक "Bye." प्राप्त नहीं होता।
     */
    public void run() {
        try {
            // क्लाइंट को आउटपुट भेजने के लिए PrintWriter बनाएँ
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            // क्लाइंट से इनपुट पढ़ने के लिए BufferedReader बनाएँ
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String inputLine;

            // क्लाइंट से इनपुट पंक्ति पढ़ें
            inputLine = in.readLine();
            while (inputLine != null) {
                // सर्वर कंसोल पर प्राप्त इनपुट प्रिंट करें
                System.out.println("Server Received: " + inputLine);
                // क्लाइंट को प्राप्त इनपुट का ईको भेजें
                out.println(inputLine + " .. " + inputLine);
                // यदि इनपुट "Bye." है तो लूप से बाहर निकलें
                if (inputLine.equals("Bye.")) break;
                // क्लाइंट से अगले इनपुट की पंक्ति पढ़ें
                inputLine = in.readLine();
            }
            // संसाधनों को बंद करें
            out.close();
            in.close();
            client.close();

        } catch (IOException e) {
            // किसी भी IOException को संभालें जो हो सकती है
            e.printStackTrace();
        }
    }

    /**
     * ईको सर्वर शुरू करने के लिए मुख्य विधि।
     * यह इनकमिंग क्लाइंट कनेक्शनों के लिए सुनती है और प्रत्येक क्लाइंट के लिए एक नया थ्रेड शुरू करती है
     * ताकि संचार को संभाला जा सके।
     * 
     * @param args कमांड लाइन आर्ग्युमेंट्स (इस कार्यान्वयन में उपयोग नहीं किया गया)
     * @throws IOException यदि सर्वर सॉकेट खोलने में I/O त्रुटि होती है
     */
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;

        // पोर्ट 4444 पर सुनने के लिए सर्वर सॉकेट प्रारंभ करें
        serverSocket = new ServerSocket(4444);

        System.out.println("Echo Server Started");

        Socket clientSocket = null;

        // इनफिनिट लूप जो इनकमिंग क्लाइंट कनेक्शन को स्वीकार करता है
        while (true) {
            // एक क्लाइंट कनेक्शन स्वीकार करें
            clientSocket = serverSocket.accept();
            // क्लाइंट के लिए MultiThreadedEchoServer का एक नया उदाहरण बनाएँ
            MultiThreadedEchoServer multiThreadedEchoServer = new MultiThreadedEchoServer(clientSocket);
            // क्लाइंट को संभालने के लिए थ्रेड शुरू करें
            multiThreadedEchoServer.start();
        }

        // यह कोड का हिस्सा उपरोक्त अनंत लूप के कारण नहीं पहुँचा जाएगा
        // System.out.println("Echo Server Stopped");
        // serverSocket.close();
    }
}
```

### कोड का स्पष्टीकरण

1. **पैकेज घोषणा**:
   - `package in.co.sunrays.net;` स्टेटमेंट कक्षा को एक विशेष नामस्थान में व्यवस्थित करता है।

2. **आयात**:
   - आवश्यक कक्षाएँ `java.io` और `java.net` से आयात की गई हैं ताकि इनपुट/आउटपुट संचालन और नेटवर्क संचार को संभाला जा सके।

3. **JavaDoc टिप्पणियाँ**:
   - कक्षा और विधियों को JavaDoc टिप्पणियों के साथ प्रलेखित किया गया है, जो उनके उद्देश्य, पैरामीटर और व्यवहार के बारे में जानकारी प्रदान करती हैं। यह अन्य डेवलपर्स को कक्षा की कार्यक्षमता और संदर्भ को समझने में मदद करता है।

4. **कक्षा घोषणा**:
   - `MultiThreadedEchoServer` कक्षा `Thread` कक्षा का विस्तार करती है, जिससे यह अन्य थ्रेड के साथ समवर्ती रूप से चल सकती है।

5. **इंस्टेंस वेरिएबल**:
   - `private Socket client`: यह वेरिएबल क्लाइंट सॉकेट को धारण करता है, जिसका उपयोग जुड़े हुए क्लाइंट के साथ संचार करने के लिए किया जाता है।

6. **कंस्ट्रक्टर**:
   - कंस्ट्रक्टर दी गई `clientSocket` के साथ इंस्टेंस को प्रारंभ करता है, जिससे प्रत्येक सर्वर इंस्टेंस को एक अलग क्लाइंट कनेक्शन का प्रबंधन करने की अनुमति मिलती है।

7. **रन विधि**:
   - `run()` विधि में क्लाइंट संचार को संभालने की लॉजिक होती है:
     - यह आउटपुट और इनपुट के लिए `PrintWriter` और `BufferedReader` ऑब्जेक्ट्स बनाती है।
     - यह क्लाइंट से पंक्तियों को पढ़ती है और "Bye." संदेश प्राप्त होने तक उन्हें वापस भेजती है।
     - सर्वर कंसोल पर प्राप्त इनपुट को मॉनिटरिंग के लिए प्रिंट करता है।
     - जब कार्य समाप्त हो जाता है, तो संसाधनों को बंद कर दिया जाता है।

8. **मुख्य विधि**:
   - `main` विधि ईको सर्वर शुरू करती है:
     - यह `4444` पोर्ट पर एक `ServerSocket` प्रारंभ करती है।
     - यह इनकमिंग क्लाइंट कनेक्शनों को स्वीकार करने के लिए अनंत लूप में प्रवेश करती है।
     - प्रत्येक स्वीकार किए गए कनेक्शन के लिए, यह एक नया `MultiThreadedEchoServer` इंस्टेंस बनाती है और इसे शुरू करती है, जिससे कई क्लाइंट का समवर्ती रूप से प्रबंधन होता है।

9. **त्रुटि प्रबंधन**:
   - `run()` विधि में `catch` ब्लॉक किसी भी `IOException` को संभालता है जो संचार के दौरान हो सकती है, यह सुनिश्चित करते हुए कि अपवादों को लॉग किया जाए ताकि समस्या का समाधान किया जा सके।

### नोट
- `System.out.println("Echo Server Stopped");` और `serverSocket.close();` पंक्तियाँ `main` विधि में टिप्पणी की गई हैं क्योंकि अनंत लूप सर्वर को इन पंक्तियों तक पहुँचने से रोकता है। यदि आवश्यक हो, तो सर्वर को उचित तरीके से बंद करने के लिए लूप से बाहर निकलने के लिए अतिरिक्त लॉजिक की आवश्यकता होगी।
