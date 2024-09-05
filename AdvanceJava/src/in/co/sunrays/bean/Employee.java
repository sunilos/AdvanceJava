package in.co.sunrays.bean;

import java.io.Serializable;

/**
 * Represents an Employee with basic details like ID, first name, last name, and a transient temporary value.
 * This class implements {@link Serializable} to allow the Employee objects to be serialized.
 * <p>
 * The class also contains a static constant {@link #RETIREMENT_AGE} indicating the standard retirement age.
 * </p>
 * <p>
 * Note: The {@code tempValue} field is marked as {@code transient}, meaning it will not be serialized.
 * </p>
 * <p>
 * @see java.io.Serializable
 * @see #RETIREMENT_AGE
 * </p>
 * 
 * @author SunilOS
 * @version 1.0
 * @since 1.0
 */
public class Employee implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private transient String tempValue;
    private static int RETIREMENT_AGE = 60;

    /**
     * Default constructor for Employee.
     */
    public Employee() {
        // Default constructor
    }

    /**
     * Parameterized constructor for Employee.
     * 
     * @param id the unique identifier for the employee
     * @param firstName the first name of the employee
     * @param lastName the last name of the employee
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
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the unique identifier of the employee.
     * 
     * @return the ID of the employee
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the employee.
     * 
     * @param id the ID to set
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
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the temporary value of the employee. This field is transient and will not be serialized.
     * 
     * @return the temporary value
     */
    public String getTempValue() {
        return tempValue;
    }

    /**
     * Sets the temporary value of the employee.
     * 
     * @param tempValue the temporary value to set
     */
    public void setTempValue(String tempValue) {
        this.tempValue = tempValue;
    }

    /**
     * Provides a string representation of the employee.
     * 
     * @return a string representation of the employee, including ID, first name, and last name
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("");
        sb.append("ID : " + getId());
        sb.append("\nName : " + getFirstName());
        sb.append("\nLast Name : " + getLastName());

        return sb.toString();
    }
}
