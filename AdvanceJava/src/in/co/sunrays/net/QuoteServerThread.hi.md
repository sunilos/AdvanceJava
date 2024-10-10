```java
package in.co.sunrays.net;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * मल्टी-थ्रेडेड Quote UDP सर्वर।
 * यह सर्वर क्लाइंट अनुरोधों को सुनता है और एक यादृच्छिक (random) उद्धरण या वर्तमान तिथि के साथ जवाब देता है।
 * 
 * @Copyright (c) SunilOS. सभी अधिकार सुरक्षित।
 * @URL www.SunilOS.com
 */

public class QuoteServerThread extends Thread {

    // DatagramSocket का उपयोग UDP पैकेट्स को भेजने और प्राप्त करने के लिए किया जाता है
    protected DatagramSocket socket = null;

    // BufferedReader का उपयोग फ़ाइल से उद्धरण पढ़ने के लिए किया जाता है (यदि आवश्यक हो)
    protected BufferedReader in = null;

    // Boolean फ़्लैग जो सर्वर को चालू रखने या बंद करने के लिए उपयोग किया जाता है
    protected boolean moreQuotes = true;

    // पहले से परिभाषित उद्धरणों का एक समूह जिसे क्लाइंट को भेजा जा सकता है
    protected String[] quotes = { "बुरा मत देखो", "बुरा मत कहो", "बुरा मत सुनो" };

    /**
     * QuoteServerThread क्लास का डिफ़ॉल्ट कन्स्ट्रक्टर।
     * यह थ्रेड और सॉकेट को इनिशियलाइज़ करता है।
     * 
     * @throws IOException अगर इनपुट या आउटपुट में कोई त्रुटि होती है।
     */
    public QuoteServerThread() throws IOException {
        this("QuoteServerThread");
    }

    /**
     * एक नाम के पैरामीटर के साथ कन्स्ट्रक्टर, जो एक विशिष्ट नाम के साथ थ्रेड को इनिशियलाइज़ करता है।
     * यह क्लाइंट अनुरोधों को सुनने के लिए पोर्ट 4445 पर DatagramSocket खोलता है।
     * 
     * @param name थ्रेड का नाम।
     * @throws IOException अगर इनपुट या आउटपुट में कोई त्रुटि होती है।
     */
    public QuoteServerThread(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(4445); // पोर्ट 4445 पर सॉकेट को इनिशियलाइज़ करें
    }

    /**
     * run() मेथड क्लाइंट अनुरोधों को संभालने के लिए लॉजिक को शामिल करता है।
     * यह लगातार इनकमिंग पैकेट्स के लिए सुनता है और क्लाइंट को एक प्रतिक्रिया भेजता है।
     */
    public void run() {

        // जब तक moreQuotes सत्य (true) है, तब तक अनुरोधों को सुनते रहें
        while (moreQuotes) {
            try {
                // क्लाइंट से आने वाले डेटा को स्टोर करने के लिए बफ़र
                byte[] buf = new byte[256];

                // क्लाइंट से डेटा प्राप्त करने के लिए DatagramPacket बनाएँ
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet); // क्लाइंट के अनुरोध को प्राप्त करें

                // क्लाइंट को भेजने के लिए प्रतिक्रिया का निर्धारण करें
                String dString = null;
                if (in == null) {
                    dString = new Date().toString(); // यदि कोई उद्धरण नहीं पढ़ा जाता है, तो वर्तमान तारीख और समय भेजें
                } else {
                    dString = getNextQuote(); // भेजने के लिए अगला उद्धरण प्राप्त करें
                }
                buf = dString.getBytes(); // प्रतिक्रिया को बाइट्स में बदलें

                // प्राप्त पैकेट से क्लाइंट का पता और पोर्ट प्राप्त करें
                InetAddress address = packet.getAddress();
                int port = packet.getPort();

                // क्लाइंट को प्रतिक्रिया भेजने के लिए एक नया पैकेट बनाएँ
                packet = new DatagramPacket(buf, buf.length, address, port);

                // क्लाइंट को प्रतिक्रिया पैकेट भेजें
                socket.send(packet);

            } catch (IOException e) {
                e.printStackTrace();
                moreQuotes = false; // किसी त्रुटि के मामले में सर्वर को बंद कर दें
            }
        }
        socket.close(); // जब सर्वर बंद हो जाए तो सॉकेट को बंद कर दें
    }

    /**
     * BufferedReader से अगला उद्धरण प्राप्त करता है।
     * अगर और उद्धरण उपलब्ध नहीं हैं, तो यह रीडर को बंद कर देता है और सर्वर को बंद कर देता है।
     * 
     * @return अगला उद्धरण या एक संदेश अगर और कोई उद्धरण उपलब्ध नहीं है।
     */
    protected String getNextQuote() {
        String returnValue = null;
        try {
            // BufferedReader (फाइल) से अगली लाइन पढ़ें
            if ((returnValue = in.readLine()) == null) {
                in.close();
                moreQuotes = false; // यदि और उद्धरण नहीं हैं, तो सर्वर को बंद कर दें
                returnValue = "कोई और उद्धरण नहीं है। अलविदा।"; // अगर कोई उद्धरण नहीं बचा है तो क्लाइंट को संदेश भेजें
            }
        } catch (IOException e) {
            returnValue = "सर्वर में IOException त्रुटि हुई।"; // पढ़ने के दौरान त्रुटि को संभालें
        }
        return returnValue;
    }

    /**
     * मुख्य मेथड जो QuoteServerThread को प्रारंभ करता है।
     * 
     * @param args कमांड-लाइन तर्क (प्रयुक्त नहीं)।
     * @throws IOException अगर इनपुट या आउटपुट में कोई त्रुटि होती है।
     */
    public static void main(String[] args) throws IOException {
        new QuoteServerThread().start(); // एक नया सर्वर थ्रेड बनाएं और प्रारंभ करें
    }
}
```

### कोड का हिंदी में व्याख्यान:
1. **पैकेज और इम्पोर्ट्स:**
   - `in.co.sunrays.net` पैकेज क्लास के स्थान को परिभाषित करता है।
   - कोड में नेटवर्किंग (`DatagramSocket`, `DatagramPacket`, `InetAddress`) और इनपुट/आउटपुट संचालन (`BufferedReader`, `IOException`) के लिए आवश्यक क्लासेज़ को इम्पोर्ट किया गया है।

2. **क्लास और फ़ील्ड्स:**
   - `QuoteServerThread` क्लास, `Thread` क्लास को एक्सटेंड करता है जिससे यह मल्टी-थ्रेडेड सर्वर बनता है।
   - फ़ील्ड्स जैसे कि `DatagramSocket`, `BufferedReader`, और `boolean moreQuotes` सर्वर संचालन और क्लाइंट संचार को प्रबंधित करते हैं।

3. **कन्स्ट्रक्टर्स:**
   - डिफ़ॉल्ट और पैरामीटराइज्ड कन्स्ट्रक्टर थ्रेड को इनिशियलाइज़ करते हैं और क्लाइंट अनुरोधों के लिए UDP सॉकेट को पोर्ट `4445` पर खोलते हैं।

4. **run() मेथड:**
   - सर्वर का मुख्य लॉजिक `run` मेथड में होता है।
   - यह इनकमिंग क्लाइंट अनुरोधों की प्रतीक्षा करता है, उन्हें प्रोसेस करता है, और क्लाइंट को एक उद्धरण या वर्तमान तारीख और समय के साथ प्रतिक्रिया भेजता है।

5. **getNextQuote() मेथड:**
   - यह मेथड फ़ाइल से उद्धरण पढ़ता है और उन्हें क्लाइंट को वापस भेजता है।
   - अगर कोई उद्धरण शेष नहीं हैं, तो यह संदेश भेजता है कि कोई और उद्धरण उपलब्ध नहीं है।

6. **मुख्य मेथड (main method):**
   - `main` मेथड सर्वर थ्रेड को प्रारंभ करता है, एक नया `QuoteServerThread` बनाता है, और उसके `start()` मेथड को कॉल करता है।

### मुख्य विशेषताएं:
- **मल्टी-थ्रेडिंग:** सर्वर एक साथ कई क्लाइंट अनुरोधों को कुशलतापूर्वक संभाल सकता है।
- **UDP संचार:** यह DatagramSocket का उपयोग करके पैकेट्स भेजने और प्राप्त करने के लिए सरल, तेज़ संदेश विनिमय के लिए उपयुक्त है।
- **त्रुटि हैंडलिंग:** यह त्रुटियों को अच्छी तरह से संभालता है और किसी त्रुटि के होने पर सर्वर को बंद कर देता है।
