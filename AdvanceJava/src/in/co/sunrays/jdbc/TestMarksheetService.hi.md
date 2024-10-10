```java
package in.co.sunrays.jdbc;

import in.co.sunrays.bean.Marksheet;

/**
 * MarksheetService क्लास का टेस्ट केस।
 * यह क्लास MarksheetService की कार्यक्षमता को परीक्षण करने के लिए
 * विधियों को शामिल करती है, विशेष रूप से जोड़ने और प्राप्त करने के संचालन को।
 * 
 * @Copyright (c) SunilOS. सभी अधिकार सुरक्षित।
 * @URL www.SunilOS.com
 */
public class TestMarksheetService {

    /**
     * मुख्य विधि जो परीक्षण मामलों को निष्पादित करती है।
     * 
     * @param args कमांड लाइन तर्क (अप्रयुक्त)।
     * @throws Exception यदि परीक्षणों के निष्पादन के दौरान कोई त्रुटि होती है।
     */
    public static void main(String[] args) throws Exception {
        testAdd(); // Marksheet जोड़ने के परीक्षण के लिए testAdd विधि को कॉल करें
        // testGet(); // Marksheet को प्राप्त करने के परीक्षण के लिए अनकमेंट करें
    }

    /**
     * MarksheetService क्लास की get विधि का परीक्षण करता है।
     * यह विधि रोल नंबर द्वारा Marksheet को प्राप्त करती है और इसके विवरण को प्रिंट करती है।
     * 
     * @throws Exception यदि प्राप्त करने के दौरान कोई त्रुटि होती है।
     */
    public static void testGet() throws Exception {
        // MarksheetService की एक इंस्टेंस बनाएँ ताकि इसकी विधियों का उपयोग किया जा सके
        MarksheetService service = new MarksheetService();

        // "B1" रोल नंबर वाली Marksheet को प्राप्त करें
        Marksheet bean = service.getMarkSheet("B1");

        // प्राप्त Marksheet के विवरण को प्रिंट करें
        System.out.println("Roll No: " + bean.getRollNo());
        System.out.println("Name: " + bean.getName());
        System.out.println("Maths: " + bean.getMaths());
        System.out.println("Chemistry: " + bean.getChemistry());
        System.out.println("Physics: " + bean.getPhysics());
    }

    /**
     * MarksheetService क्लास की add विधि का परीक्षण करता है।
     * यह विधि एक नया Marksheet इंस्टेंस बनाती है और इसे सेवा में जोड़ती है।
     * 
     * @throws Exception यदि जोड़ने के दौरान कोई त्रुटि होती है।
     */
    public static void testAdd() throws Exception {
        // एक नया Marksheet ऑब्जेक्ट बनाएँ
        Marksheet bean = new Marksheet();

        // Marksheet के विवरण सेट करें
        bean.setRollNo("A1");
        bean.setName("Yuvraj");
        bean.setMaths(99);
        bean.setChemistry(99);
        bean.setPhysics(99);

        // MarksheetService की एक इंस्टेंस बनाएँ
        MarksheetService service = new MarksheetService();

        // सेवा का उपयोग करके Marksheet को जोड़ें
        service.addMarkSheet(bean);
    }
}
```

### व्याख्या:
1. **पैकेज घोषणा**: यह क्लास `in.co.sunrays.jdbc` पैकेज का हिस्सा है, जो यह संकेत करता है कि यह एक JDBC एप्लिकेशन का हिस्सा है।

2. **आयात**: `in.co.sunrays.bean` से `Marksheet` क्लास को आयात किया गया है ताकि Marksheet ऑब्जेक्ट बनाने और उन्हें प्रबंधित किया जा सके।

3. **क्लास दस्तावेज़ीकरण**: क्लास स्तर की जावाडॉक टिप्पणी क्लास के उद्देश्य और कॉपीराइट और यूआरएल के विवरण प्रदान करती है।

4. **मुख्य विधि**: 
   - `main` विधि प्रोग्राम का प्रवेश बिंदु है। 
   - यह Marksheet जोड़ने की कार्यक्षमता का परीक्षण करने के लिए `testAdd` विधि को कॉल करता है।
   - भविष्य में Marksheet को प्राप्त करने के परीक्षण के लिए `testGet()` के लिए एक टिप्पणी की गई पंक्ति है।

5. **testGet विधि**:
   - यह `MarksheetService` की `getMarkSheet` विधि को कॉल करके रोल नंबर ("B1") द्वारा Marksheet को प्राप्त करता है।
   - प्राप्त Marksheet के विवरण को प्रिंट करता है, जिसमें रोल नंबर, नाम और विषयों के स्कोर (गणित, रसायन, भौतिकी) शामिल हैं।

6. **testAdd विधि**:
   - एक नया `Marksheet` ऑब्जेक्ट बनाया जाता है, और इसकी विशेषताओं (रोल नंबर, नाम और विषयों के स्कोर) को सेट किया जाता है।
   - `MarksheetService` की एक इंस्टेंस बनाई जाती है ताकि `addMarkSheet` विधि को कॉल किया जा सके।
   - नए बनाए गए Marksheet को सेवा में जोड़ने के लिए `addMarkSheet` विधि को कॉल किया जाता है।
