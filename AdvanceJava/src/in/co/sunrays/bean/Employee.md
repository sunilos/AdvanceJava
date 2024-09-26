```java
package in.co.sunrays.bean;

import java.io.Serializable;

/**
 * Employee class represents an entity that stores information about an employee.
 * This class implements Serializable interface, allowing Employee objects to be
 * serialized and deserialized for storage or transmission over a network.
 * 
 * @author SunilOS
 * @version 1.0
 * @since 2023
 */
public class Employee implements Serializable {

    // A unique identifier for the employee
    private int id;

    // First name of the employee
    private String firstName;

    // Last name of the employee
    private String lastName;

    /**
     * A transient field which will not be serialized when the object is written
     * to a stream. It's used to store temporary values that don't need to be
     * persisted.
     */
    private transient String tempValue;

    /**
     * A static field to hold the retirement age. It is shared among all instances
     * of the Employee class.
     */
    private static int RETIREMENT_AGE = 60;

    /**
     * Default constructor, initializes an empty Employee object.
     */
    public Employee() {
    }

    /**
     * Parameterized constructor to create an Employee object with given id,
     * firstName, and lastName.
     * 
     * @param id        the unique ID of the employee
     * @param firstName the first name of the employee
     * @param lastName  the last name of the employee
     */
    public Employee(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Gets the first name of the employee.
     * 
     * @return the first name of the employee
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the employee.
     * 
     * @param firstName the first name to set for the employee
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the employee ID.
     * 
     * @return the employee's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the employee ID.
     * 
     * @param id the unique ID to set for the employee
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the last name of the employee.
     * 
     * @return the last name of the employee
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the employee.
     * 
     * @param lastName the last name to set for the employee
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the temporary value (not persisted).
     * 
     * @return the temporary value of the employee
     */
    public String getTempValue() {
        return tempValue;
    }

    /**
     * Sets a temporary value for the employee. This value will not be serialized.
     * 
     * @param tempValue the temporary value to set for the employee
     */
    public void setTempValue(String tempValue) {
        this.tempValue = tempValue;
    }

    /**
     * Overrides the default toString method to provide a meaningful string
     * representation of the Employee object.
     * 
     * @return a string representation of the employee's ID, first name, and last
     *         name
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("");
        sb.append("ID : " + getId());
        sb.append("\nFirst Name : " + getFirstName());
        sb.append("\nLast Name : " + getLastName());
        return sb.toString();
    }
}
```

### Code Explanation:

1. **Class Declaration**: 
   - `Employee` class implements the `Serializable` interface. This allows the instances of `Employee` to be converted into a byte stream and be restored later (deserialization).
   
2. **Fields**:
   - `id`: Unique identifier for each employee.
   - `firstName`: Stores the first name of the employee.
   - `lastName`: Stores the last name of the employee.
   - `tempValue`: A `transient` field, meaning it won’t be included during serialization.
   - `RETIREMENT_AGE`: A static field for holding the retirement age, shared among all employees.

3. **Constructors**:
   - The class has a default no-arg constructor for creating an empty `Employee` object.
   - A parameterized constructor allows you to create an employee by providing the `id`, `firstName`, and `lastName`.

4. **Getter and Setter Methods**:
   - Each field (except `RETIREMENT_AGE`) has corresponding getter and setter methods, allowing you to access and modify their values from outside the class.

5. **Transient Field**:
   - The `transient` keyword for `tempValue` indicates that this field will not be serialized. It’s used for temporary data that does not need to be stored.

6. **`toString` Method**:
   - The `toString()` method provides a string representation of the `Employee` object, printing out its `id`, `firstName`, and `lastName`.
