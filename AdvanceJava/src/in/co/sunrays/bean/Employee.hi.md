```java
package in.co.sunrays.bean;

import java.io.Serializable;

/**
 * Employee class एक entity का प्रतिनिधित्व करती है जो एक employee के बारे में जानकारी संग्रहीत करती है।
 * यह class Serializable interface को implement करती है, जिससे Employee objects को
 * स्टोरेज या नेटवर्क पर ट्रांसमिशन के लिए serialized और deserialized किया जा सकता है।
 * 
 * @author SunilOS
 * @version 1.0
 * @since 2023
 */
public class Employee implements Serializable {

    // Employee के लिए एक unique पहचानकर्ता
    private int id;

    // Employee का पहला नाम
    private String firstName;

    // Employee का अंतिम नाम
    private String lastName;

    /**
     * एक transient field जिसे object को stream में लिखते समय serialized नहीं किया जाएगा।
     * इसका उपयोग अस्थायी मान संग्रहीत करने के लिए किया जाता है जिन्हें बनाए रखने की आवश्यकता नहीं है।
     */
    private transient String tempValue;

    /**
     * एक static field जो retirement age को संग्रहीत करता है। इसे Employee class की सभी instances के बीच साझा किया जाता है।
     */
    private static int RETIREMENT_AGE = 60;

    /**
     * Default constructor, एक खाली Employee object को initialize करता है।
     */
    public Employee() {
    }

    /**
     * Parameterized constructor जो दिए गए id, firstName, और lastName के साथ एक Employee object बनाता है।
     * 
     * @param id        employee का unique ID
     * @param firstName employee का पहला नाम
     * @param lastName  employee का अंतिम नाम
     */
    public Employee(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Employee के पहले नाम को प्राप्त करता है।
     * 
     * @return employee का पहला नाम
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Employee के पहले नाम को सेट करता है।
     * 
     * @param firstName employee के लिए सेट करने के लिए पहला नाम
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Employee का ID प्राप्त करता है।
     * 
     * @return employee का ID
     */
    public int getId() {
        return id;
    }

    /**
     * Employee का ID सेट करता है।
     * 
     * @param id employee के लिए सेट करने के लिए unique ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Employee का अंतिम नाम प्राप्त करता है।
     * 
     * @return employee का अंतिम नाम
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Employee का अंतिम नाम सेट करता है।
     * 
     * @param lastName employee के लिए सेट करने के लिए अंतिम नाम
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * अस्थायी मान प्राप्त करता है (जो संग्रहीत नहीं किया जाएगा)।
     * 
     * @return employee का अस्थायी मान
     */
    public String getTempValue() {
        return tempValue;
    }

    /**
     * Employee के लिए एक अस्थायी मान सेट करता है। इस मान को serialized नहीं किया जाएगा।
     * 
     * @param tempValue employee के लिए सेट करने के लिए अस्थायी मान
     */
    public void setTempValue(String tempValue) {
        this.tempValue = tempValue;
    }

    /**
     * Default toString method को override करता है ताकि Employee object का एक सार्थक string representation प्रदान किया जा सके।
     * 
     * @return employee के ID, first name, और last name का एक string representation
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("");
        sb.append("ID : " + getId());
        sb.append("\nपहला नाम : " + getFirstName());
        sb.append("\nअंतिम नाम : " + getLastName());
        return sb.toString();
    }
}
```

### कोड व्याख्या:

1. **Class घोषणा**:
   - `Employee` class `Serializable` interface को implement करती है, जिससे Employee objects को byte stream में परिवर्तित किया जा सकता है और बाद में (deserialization) पुनर्स्थापित किया जा सकता है।

2. **Fields**:
   - `id`: प्रत्येक employee के लिए unique पहचानकर्ता।
   - `firstName`: Employee का पहला नाम संग्रहीत करता है।
   - `lastName`: Employee का अंतिम नाम संग्रहीत करता है।
   - `tempValue`: एक `transient` field, जिसका serialization के दौरान समावेश नहीं किया जाएगा।
   - `RETIREMENT_AGE`: सभी employees के बीच साझा की जाने वाली retirement age को संग्रहीत करने के लिए एक static field।

3. **Constructors**:
   - Class में एक default no-arg constructor है जो एक खाली `Employee` object बनाने के लिए है।
   - एक parameterized constructor आपको `id`, `firstName`, और `lastName` प्रदान करके employee बनाने की अनुमति देता है।

4. **Getter और Setter मेथड्स**:
   - प्रत्येक field (सिवाय `RETIREMENT_AGE`) के लिए संबंधित getter और setter मेथड्स हैं, जो आपको class के बाहर से उनके मानों तक पहुंचने और उन्हें संशोधित करने की अनुमति देते हैं।

5. **Transient Field**:
   - `transient` keyword `tempValue` के लिए इंगित करता है कि यह field serialized नहीं की जाएगी। इसका उपयोग अस्थायी data के लिए किया जाता है जिसे संग्रहीत करने की आवश्यकता नहीं होती है।

6. **`toString` Method**:
   - `toString()` method `Employee` object का एक string representation प्रदान करता है, जो इसके `id`, `firstName`, और `lastName` को print करता है।
