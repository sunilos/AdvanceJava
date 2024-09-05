package in.co.sunrays.bean;

/**
 * Represents a Marksheet for a student containing attributes related to their marks in different subjects.
 * Provides accessor methods to get and set the marks for physics, chemistry, and maths, as well as the student's name and roll number.
 * <p>
 * Note: This class does not include any methods for calculating total marks or grades.
 * </p>
 * 
 * @see #getRollNo()
 * @see #setRollNo(String)
 * @see #getName()
 * @see #setName(String)
 * @see #getPhysics()
 * @see #setPhysics(int)
 * @see #getChemistry()
 * @see #setChemistry(int)
 * @see #getMaths()
 * @see #setMaths(int)
 * 
 * @version 1.0
 * @since 1.0
 * 
 * @author SunilOS
 */
public class Marksheet {

    private String rollNo;
    private String name;
    private int physics;
    private int chemistry;
    private int maths;

    /**
     * Gets the roll number of the student.
     * 
     * @return the roll number
     */
    public String getRollNo() {
        return rollNo;
    }

    /**
     * Sets the roll number of the student.
     * 
     * @param rollNo the roll number to set
     */
    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
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
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the marks obtained in physics.
     * 
     * @return the marks in physics
     */
    public int getPhysics() {
        return physics;
    }

    /**
     * Sets the marks obtained in physics.
     * 
     * @param physics the marks in physics to set
     */
    public void setPhysics(int physics) {
        this.physics = physics;
    }

    /**
     * Gets the marks obtained in chemistry.
     * 
     * @return the marks in chemistry
     */
    public int getChemistry() {
        return chemistry;
    }

    /**
     * Sets the marks obtained in chemistry.
     * 
     * @param chemistry the marks in chemistry to set
     */
    public void setChemistry(int chemistry) {
        this.chemistry = chemistry;
    }

    /**
     * Gets the marks obtained in maths.
     * 
     * @return the marks in maths
     */
    public int getMaths() {
        return maths;
    }

    /**
     * Sets the marks obtained in maths.
     * 
     * @param maths the marks in maths to set
     */
    public void setMaths(int maths) {
        this.maths = maths;
    }
}
