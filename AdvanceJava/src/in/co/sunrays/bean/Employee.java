package in.co.sunrays.bean;

import java.io.Serializable;

/*
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */

public class Employee implements Serializable {

	private int id;

	private String firstName;

	private String lastName;

	private transient String tempValue;

	private static int RETIREMENT_AGE = 60;

	public Employee() {

	}

	public Employee(int id, String firstName, String lastName) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the tempValue
	 */
	public String getTempValue() {
		return tempValue;
	}

	/**
	 * @param tempValue
	 *            the tempValue to set
	 */
	public void setTempValue(String tempValue) {
		this.tempValue = tempValue;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("");
		sb.append("ID : " + getId());
		sb.append("\nName : " + getFirstName());
		sb.append("\nLast Name : " + getLastName());

		return sb.toString();
	}

}
