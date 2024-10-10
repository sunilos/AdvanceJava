```java
package in.co.sunrays.scan;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RegExpExample क्लास एक निर्दिष्ट निर्देशिका में फाइलों को स्कैन करता है 
 * और उन पंक्तियों की खोज करता है जो दिए गए नियमित अभिव्यक्तियों से मेल खाती हैं। 
 * यह एक ResourceBundle का उपयोग करता है जो अभिव्यक्तियों, संदेशों, 
 * बाहर किए गए फाइल प्रकारों और टिप्पणी टोकनों को एक गुणांकित फ़ाइल से लोड करता है। 
 * यह क्लास किसी भी फाइल और पंक्तियों को नजरअंदाज करने की कार्यक्षमता प्रदान करती है 
 * जो विन्यासित पैटर्न और टिप्पणियों के आधार पर हैं।
 *
 * @Copyright (c) SunilOS. सभी अधिकार सुरक्षित हैं।
 * @URL www.SunilOS.com
 */
public class RegExpExample {

    // फाइलों के स्कैनिंग के लिए रूट निर्देशिका
    protected String rootDirectory = null;

    // फाइलों में मेल खाने के लिए नियमित अभिव्यक्तियों की सूची
    protected ArrayList<String> expressions = null;

    // प्रत्येक अभिव्यक्ति के लिए संदेशों की सूची
    protected ArrayList<String> messages = null;

    // प्रभावी मिलान के लिए संकलित regex पैटर्न
    protected ArrayList<Pattern> patterns = null;

    // बाहर किए गए फाइल प्रकारों की सूची
    protected String[] excludeFiles = null;

    // टिप्पणी टोकन जो यह बताते हैं कि किन पंक्तियों को नजरअंदाज किया जाना चाहिए
    protected String[] comments = null;

    // गुणांकित फ़ाइल में अभिव्यक्तियों को विभाजित करने के लिए उपयोग किया जाने वाला विभाजक
    public static String separater = "&";

    // स्कैन की गई पंक्तियों की संख्या का काउंटर
    protected int scannedFileCount = 0;

    /**
     * डिफ़ॉल्ट कंस्ट्रक्टर जो वर्तमान निर्देशिका को रूट निर्देशिका के रूप में 
     * उपयोग करके स्कैनिंग ऑब्जेक्ट को प्रारंभ करता है।
     */
    public RegExpExample() {
        this(".");
    }

    /**
     * कंस्ट्रक्टर जो एक निर्दिष्ट रूट निर्देशिका के साथ स्कैनिंग ऑब्जेक्ट को प्रारंभ करता है।
     *
     * @param dir स्कैन किए जाने वाले निर्देशिका का नाम।
     */
    public RegExpExample(String dir) {
        rootDirectory = dir;
        init();
    }

    /**
     * अभिव्यक्तियों, संदेशों, बाहर किए गए फाइल प्रकारों और टिप्पणियों को 
     * ResourceBundle से लोड करके प्रारंभ करता है। 
     * यह मिलान के लिए regex पैटर्न भी संकलित करता है।
     */
    public void init() {
        expressions = new ArrayList<>();
        messages = new ArrayList<>();
        patterns = new ArrayList<>();

        // ResourceBundle से गुणांकित फ़ाइल को लोड करें
        ResourceBundle bundle = ResourceBundle.getBundle("com.resources.scon");

        // अभिव्यक्तियों, संदेशों, बाहर किए गए फाइलों और टिप्पणियों को पुनः प्राप्त करें
        String exps = bundle.getString("expressions");
        String msgs = bundle.getString("messages");
        String exFiles = bundle.getString("excludeFiles");
        String comm = bundle.getString("comments");

        // अभिव्यक्तियों को टोकनाइज़ करें और पैटर्न में संकलित करें
        StringTokenizer stringTokenizer = new StringTokenizer(exps, separater);
        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            expressions.add(token);
            patterns.add(Pattern.compile(token)); // मिलान के लिए regex संकलित करें
        }

        // प्रत्येक अभिव्यक्ति के साथ संबद्ध संदेशों को टोकनाइज़ करें
        stringTokenizer = new StringTokenizer(msgs, separater);
        while (stringTokenizer.hasMoreTokens()) {
            messages.add(stringTokenizer.nextToken());
        }

        // बाहर किए गए फाइलों के एक्सटेंशन को टोकनाइज़ करें
        stringTokenizer = new StringTokenizer(exFiles, separater);
        excludeFiles = new String[stringTokenizer.countTokens()];
        for (int i = 0; stringTokenizer.hasMoreTokens(); i++) {
            excludeFiles[i] = stringTokenizer.nextToken();
        }

        // टिप्पणी टोकनों को टोकनाइज़ करें
        stringTokenizer = new StringTokenizer(comm, separater);
        comments = new String[stringTokenizer.countTokens()];
        for (int i = 0; stringTokenizer.hasMoreTokens(); i++) {
            comments[i] = stringTokenizer.nextToken();
        }

        // लोड की गई अभिव्यक्तियों और संदेशों को सत्यापन के लिए प्रिंट करें
        for (int i = 0; i < expressions.size(); i++) {
            System.out.println(expressions.get(i) + "=" + messages.get(i));
        }
    }

    /**
     * यह जांचता है कि क्या एक दी गई पंक्ति को टिप्पणी टोकनों के आधार पर स्कैन किया जा सकता है।
     *
     * @param line जांची जाने वाली टेक्स्ट की पंक्ति।
     * @return यदि पंक्ति को स्कैन किया जा सकता है तो true, यदि यह टिप्पणी टोकन से शुरू होती है तो false।
     */
    public boolean canScanLine(String line) {
        for (String comment : comments) {
            if (line.trim().startsWith(comment)) {
                return false; // पंक्ति टिप्पणी के साथ शुरू होती है, स्कैनिंग छोड़ें
            }
        }
        return true; // पंक्ति को स्कैन किया जा सकता है
    }

    /**
     * यह जांचता है कि क्या एक फाइल को उसके एक्सटेंशन के आधार पर स्कैन किया जा सकता है।
     *
     * @param fileName जांची जाने वाली फाइल का नाम।
     * @return यदि फाइल को स्कैन किया जा सकता है तो true, यदि यह बाहर किए गए पैटर्न से मेल खाती है तो false।
     */
    public boolean canScan(String fileName) {
        for (String excludeFile : excludeFiles) {
            if (fileName.toLowerCase().endsWith(excludeFile)) {
                System.out.println("Excluded File " + fileName);
                return false; // फाइल बाहर किए गए पैटर्न से मेल खाती है, स्कैन न करें
            }
        }
        return true; // फाइल स्कैनिंग के लिए योग्य है
    }

    /**
     * एक निर्देशिका को फाइलों और उपनिर्देशिकाओं के लिए पुनरावृत्त रूप से स्कैन करता है।
     *
     * @param dirname स्कैन की जा रही निर्देशिका का नाम।
     * @param dir फाइल का File ऑब्जेक्ट जो निर्देशिका का प्रतिनिधित्व करता है।
     * @throws IOException यदि फाइलों तक पहुँचने में I/O त्रुटि होती है।
     */
    public void scanDirectory(String dirname, File dir) throws IOException {
        String[] files = dir.list();
        if (files != null) {
            for (String file : files) {
                File newFile = new File(dir, file);
                if (newFile.isDirectory()) {
                    scanDirectory(file, newFile); // उपनिर्देशिकाओं में पुनरावृत्त रूप से जाएं
                } else if (canScan(file)) {
                    scanFile(dirname, file, newFile); // फाइल को स्कैन करें
                }
            }
        }
    }

    /**
     * स्कैनिंग के दौरान तुलना परिणामों के आधार पर एक संदेश को लॉग करता है।
     *
     * @param message लॉग किया जाने वाला संदेश।
     * @param dirName स्कैन की जा रही फाइल का निर्देशिका।
     * @param name स्कैन की जा रही फाइल का नाम।
     * @param lineNumber मेल मिलने पर पंक्ति संख्या।
     * @param line मेल खाई गई टेक्स्ट की पंक्ति।
     * @throws IOException यदि लॉगिंग के दौरान I/O त्रुटि होती है।
     */
    public void log(String message, String dirName, String name,
                    int lineNumber, String line) throws IOException {
        // मिलान को एक फ़ाइल या रिपोर्टिंग तंत्र में लॉग करें (इसे लागू करना बाकी है)
    }

    /**
     * एक फाइल की प्रत्येक पंक्ति की तुलना लोड की गई नियमित अभिव्यक्तियों के साथ करता है 
     * और मिले हुए मैचों को लॉग करता है।
     *
     * @param dirName स्कैन की जा रही फाइल का निर्देशिका नाम।
     * @param name स्कैन की जा रही फाइल का नाम।
     * @param lineNumber वर्तमान पंक्ति संख्या जो प्रोसेस की जा रही है।
     * @param line वर्तमान टेक्स्ट की पंक्ति जो की तुलना की जा रही है।
     * @throws IOException यदि लॉगिंग के दौरान I/O त्रुटि होती है।
     */
    public void compareAndLog

File(String dirName, String name, int lineNumber,
                                   String line) throws IOException {
        for (int i = 0; i < messages.size(); i++) {
            String message = messages.get(i);
            Pattern pattern = patterns.get(i);
            if (compare(line.toLowerCase(), pattern)) {
                log(message, dirName, name, lineNumber, line); // मेल खाता हुआ संदेश लॉग करें
                scannedFileCount++; // स्कैन की गई फाइलों की संख्या बढ़ाएँ
            }
        }
    }

    /**
     * एक स्ट्रिंग को एक पैटर्न के साथ मिलान करता है।
     *
     * @param str मिलान के लिए स्ट्रिंग।
     * @param p पैटर्न जो मिलान के लिए उपयोग किया जा रहा है।
     * @return यदि स्ट्रिंग पैटर्न से मेल खाता है तो true, अन्यथा false।
     */
    public boolean compare(String str, Pattern p) {
        Matcher m = p.matcher(str);
        return m.find(); // यदि मेल है तो true लौटाएँ
    }

    /**
     * एक निर्दिष्ट फाइल को स्कैन करता है और प्रत्येक पंक्ति की जांच करता है 
     * यह देखने के लिए कि क्या इसे स्कैन किया जा सकता है।
     *
     * @param dirName स्कैन की जा रही फाइल का निर्देशिका नाम।
     * @param name स्कैन की जा रही फाइल का नाम।
     * @param file स्कैन की जा रही फाइल का File ऑब्जेक्ट।
     * @throws IOException यदि फाइल पढ़ने में I/O त्रुटि होती है।
     */
    public void scanFile(String dirName, String name, File file)
            throws IOException {
        scannedFileCount = 0; // स्कैन की गई फाइलों की संख्या को शून्य करें

        LineNumberReader br = new LineNumberReader(new FileReader(file));
        String line;
        int lineNumber;

        while ((line = br.readLine()) != null) {
            lineNumber = br.getLineNumber();
            if (canScanLine(line)) {
                compareAndLogFile(file.getParent(), name, lineNumber, line); // फाइल की पंक्ति की तुलना करें
            }
        }

        br.close(); // BufferedReader बंद करें
    }

    /**
     * स्कैनिंग प्रक्रिया शुरू करता है। 
     * वर्तमान में, यह लॉगिंग कार्यक्षमता को समाहित करता है।
     *
     * @throws IOException यदि स्कैनिंग के दौरान I/O त्रुटि होती है।
     */
    public void startScan() throws IOException {
        // स्कैनिंग प्रक्रिया को लागू करने का कार्यवाही की जानी चाहिए (इसे लागू करना बाकी है)
    }

    /**
     * मुख्य विधि, जो प्रोग्राम की प्रारंभिक बिंदु है। 
     * यह निर्दिष्ट निर्देशिका का स्कैनिंग शुरू करती है।
     *
     * @param args कमांड-लाइन तर्क जो स्कैन करने के लिए निर्देशिका को निर्दिष्ट करते हैं।
     * @throws Exception यदि स्कैनिंग के दौरान कोई त्रुटि होती है।
     */
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("कृपया स्कैन करने के लिए एक निर्देशिका निर्दिष्ट करें।");
            return; // यदि कोई निर्देशिका नहीं दी गई है तो लौटें
        }

        RegExpExample codeScan = new RegExpExample(args[0]); // निर्दिष्ट निर्देशिका के साथ स्कैनिंग ऑब्जेक्ट बनाएं
        System.out.println("स्कैनिंग शुरू हुई");
        codeScan.startScan(); // निर्दिष्ट निर्देशिका की स्कैनिंग शुरू करें
        System.out.println("स्कैनिंग समाप्त हो गई, रिपोर्ट यहाँ है: " + args[0] + "\\scon.xls");
    }
}
```
### कोड की कार्यक्षमता का स्पष्टीकरण:
- **आरंभिककरण**: `RegExpExample` वर्ग को रूट निर्देशिका के साथ आरंभ किया जाता है, जिसमें यह गुणांकित फ़ाइल से अभिव्यक्तियों, संदेशों, बाहर किए गए फ़ाइल प्रकारों और टिप्पणियों को लोड करता है।
- **स्कैनिंग**: यह वर्ग निर्देशिका संरचना के माध्यम से पुनरावृत्त रूप से स्कैन करता है, प्रत्येक फ़ाइल को निर्दिष्ट पैटर्न के खिलाफ चेक करता है और उन फ़ाइलों को नजरअंदाज करता है जो बाहर किए गए प्रकारों या टिप्पणी टोकनों से मेल खाती हैं।
- **लॉगिंग**: जब एक मेल पाया जाता है, तो संबंधित संदेश और फ़ाइल जानकारी लॉग करने के लिए विचारित होती है (लॉगिंग कार्यान्वयन को जोड़ा जाना आवश्यक है)।
- **Regex तुलना**: फ़ाइल की प्रत्येक पंक्ति को संकलित regex पैटर्न के खिलाफ तुलना की जाती है, और मेल आने पर उसे लॉग किया जाता है।
- **मुख्य विधि**: प्रोग्राम के प्रवेश बिंदु के रूप में निर्दिष्ट निर्देशिका के आधार पर स्कैनिंग को प्रारंभ करता है, विभिन्न निर्देशिका इनपुट के लिए लचीलापन प्रदान करता है।

यह व्यापक दस्तावेज़ीकरण और स्पष्टीकरण वर्तमान और भविष्य के विकासकर्ताओं को कोड के उद्देश्य और कार्यक्षमता को बेहतर ढंग से समझने में मदद करेगा।
