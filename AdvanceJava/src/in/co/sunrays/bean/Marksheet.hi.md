```java
package in.co.sunrays.bean;

/**
 * Marksheet Javabean class एक छात्र की मार्कशीट का प्रतिनिधित्व करती है जिसमें रोल नंबर, 
 * नाम, और फिजिक्स, केमिस्ट्री और मैथ्स जैसे विषयों में अंक जैसे attributes होते हैं।
 * इसमें इन attributes को एक्सेस और मॉडिफाई करने के लिए getter और setter methods शामिल हैं।
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class Marksheet {

    // छात्र का रोल नंबर, जो एक unique पहचानकर्ता के रूप में कार्य करता है
    private String rollNo;

    // छात्र का नाम
    private String name;

    // फिजिक्स में प्राप्त अंक
    private int physics;

    // केमिस्ट्री में प्राप्त अंक
    private int chemistry;

    // मैथ्स में प्राप्त अंक
    private int maths;

    /**
     * केमिस्ट्री में प्राप्त अंकों को प्राप्त करता है।
     * 
     * @return केमिस्ट्री में प्राप्त अंक
     */
    public int getChemistry() {
        return chemistry;
    }

    /**
     * केमिस्ट्री के अंक सेट करता है।
     * 
     * @param chemistry केमिस्ट्री के लिए सेट करने के लिए अंक
     */
    public void setChemistry(int chemistry) {
        this.chemistry = chemistry;
    }

    /**
     * मैथ्स में प्राप्त अंकों को प्राप्त करता है।
     * 
     * @return मैथ्स में प्राप्त अंक
     */
    public int getMaths() {
        return maths;
    }

    /**
     * मैथ्स के अंक सेट करता है।
     * 
     * @param maths मैथ्स के लिए सेट करने के लिए अंक
     */
    public void setMaths(int maths) {
        this.maths = maths;
    }

    /**
     * छात्र के नाम को प्राप्त करता है।
     * 
     * @return छात्र का नाम
     */
    public String getName() {
        return name;
    }

    /**
     * छात्र का नाम सेट करता है।
     * 
     * @param name छात्र के लिए सेट करने के लिए नाम
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * फिजिक्स में प्राप्त अंकों को प्राप्त करता है।
     * 
     * @return फिजिक्स में प्राप्त अंक
     */
    public int getPhysics() {
        return physics;
    }

    /**
     * फिजिक्स के अंक सेट करता है।
     * 
     * @param physics फिजिक्स के लिए सेट करने के लिए अंक
     */
    public void setPhysics(int physics) {
        this.physics = physics;
    }

    /**
     * छात्र का रोल नंबर प्राप्त करता है।
     * 
     * @return छात्र का रोल नंबर
     */
    public String getRollNo() {
        return rollNo;
    }

    /**
     * छात्र का रोल नंबर सेट करता है।
     * 
     * @param rollNo छात्र के लिए सेट करने के लिए रोल नंबर
     */
    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }
}
```

### कोड व्याख्या:

1. **क्लास घोषणा**:
   - `Marksheet` क्लास एक छात्र की मार्कशीट का प्रतिनिधित्व करती है, जिसमें रोल नंबर, नाम और विषयों के अंक जैसे फील्ड्स होते हैं।
   - यह एक JavaBean के रूप में संरचित है, जिसमें private फील्ड्स और उन्हें एक्सेस और मॉडिफाई करने के लिए public getter और setter methods होते हैं।

2. **फील्ड्स**:
   - `rollNo`: प्रत्येक छात्र के लिए एक unique पहचानकर्ता के रूप में रोल नंबर का प्रतिनिधित्व करता है।
   - `name`: छात्र का नाम संग्रहीत करता है।
   - `physics`, `chemistry`, `maths`: इन संबंधित विषयों में छात्र द्वारा प्राप्त किए गए अंक संग्रहीत करते हैं।

3. **Getter Methods**:
   - `getRollNo()`, `getName()`, `getPhysics()`, `getChemistry()`, और `getMaths()` अपने-अपने फील्ड्स के मानों को प्राप्त करते हैं। ये methods बाहरी क्लासेस को इन मानों तक पहुंचने की अनुमति देते हैं।

4. **Setter Methods**:
   - `setRollNo()`, `setName()`, `setPhysics()`, `setChemistry()`, और `setMaths()` संबंधित फील्ड्स के लिए मान सेट करने की अनुमति देते हैं। ये methods object के attributes को संशोधित करने में सहायक होते हैं।

5. **Encapsulation**:
   - फील्ड्स private होते हैं, जिसका अर्थ है कि इन्हें क्लास के बाहर सीधे एक्सेस नहीं किया जा सकता। public getter और setter methods नियंत्रित एक्सेस प्रदान करते हैं, जिससे डेटा encapsulation सुनिश्चित होता है।

6. **Javadoc Comments**:
   - प्रत्येक method और फील्ड के उद्देश्य को स्पष्ट करने के लिए Javadoc टिप्पणियाँ जोड़ी गई हैं, जिससे कोड आसानी से समझा जा सकता है और इसका उपयोग करने वाले डेवलपर्स के लिए संदर्भ प्रदान होता है।
