```java
package in.co.sunrays.bean;

/**
 * Marksheet Javabean class represents a student's marksheet with attributes like roll number, 
 * name, and marks in subjects such as physics, chemistry, and maths. 
 * It includes getter and setter methods to access and modify these attributes.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class Marksheet {

    // Student's roll number, serves as a unique identifier
    private String rollNo;

    // Name of the student
    private String name;

    // Marks obtained in Physics
    private int physics;

    // Marks obtained in Chemistry
    private int chemistry;

    // Marks obtained in Maths
    private int maths;

    /**
     * Gets the marks obtained in Chemistry.
     * 
     * @return the marks in chemistry
     */
    public int getChemistry() {
        return chemistry;
    }

    /**
     * Sets the marks for Chemistry.
     * 
     * @param chemistry the marks to set for chemistry
     */
    public void setChemistry(int chemistry) {
        this.chemistry = chemistry;
    }

    /**
     * Gets the marks obtained in Maths.
     * 
     * @return the marks in maths
     */
    public int getMaths() {
        return maths;
    }

    /**
     * Sets the marks for Maths.
     * 
     * @param maths the marks to set for maths
     */
    public void setMaths(int maths) {
        this.maths = maths;
    }

    /**
     * Gets the name of the student.
     * 
     * @return the name of the student
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the student.
     * 
     * @param name the name to set for the student
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the marks obtained in Physics.
     * 
     * @return the marks in physics
     */
    public int getPhysics() {
        return physics;
    }

    /**
     * Sets the marks for Physics.
     * 
     * @param physics the marks to set for physics
     */
    public void setPhysics(int physics) {
        this.physics = physics;
    }

    /**
     * Gets the roll number of the student.
     * 
     * @return the roll number of the student
     */
    public String getRollNo() {
        return rollNo;
    }

    /**
     * Sets the roll number of the student.
     * 
     * @param rollNo the roll number to set for the student
     */
    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }
}
```

### Code Explanation:

1. **Class Declaration**:
   - The `Marksheet` class represents a student's marksheet with fields for roll number, name, and subject marks.
   - It's structured as a JavaBean, with private fields and public getter/setter methods for encapsulation.

2. **Fields**:
   - `rollNo`: Represents the unique roll number for each student.
   - `name`: Stores the name of the student.
   - `physics`, `chemistry`, `maths`: Hold the marks scored by the student in these respective subjects.

3. **Getter Methods**:
   - `getRollNo()`, `getName()`, `getPhysics()`, `getChemistry()`, and `getMaths()` return the values of their respective fields. These methods allow external classes to access these values.

4. **Setter Methods**:
   - `setRollNo()`, `setName()`, `setPhysics()`, `setChemistry()`, and `setMaths()` allow setting the values of the corresponding fields. These methods enable modification of object attributes.

5. **Encapsulation**:
   - The fields are private, meaning they can't be accessed directly from outside the class. The public getter and setter methods provide controlled access, ensuring data encapsulation.

6. **Javadoc Comments**:
   - Javadoc comments are added to explain each method and field's purpose, making the code easily understandable and providing context for developers reading or using the class.
