package in.co.sunrays.utility;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

/**
 * Common validation methods All methods in this class are static.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */
public class ValidatorUtility {

	/**
	 * Constructor Only static methods are allowed in this class.
	 */
	private ValidatorUtility() {

	}

	/**
	 * Checks whether the string in the parameter contains non alphabetic
	 * character.
	 * 
	 * @param String
	 *            sText
	 * @return boolean
	 */
	public static boolean isOnlyAlphabets(String sText) {
		char cCharAtLocation = '\0';
		for (int i = 0; sText != null && i < sText.length(); i++) {
			cCharAtLocation = sText.charAt(i);
			if (!Character.isLetter(cCharAtLocation)
					&& !Character.isSpaceChar(cCharAtLocation)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Method getNormalDate. For a given Date in String format in the format of
	 * mm/dd/yyyy, it returns the corresponding date object. If the string
	 * contains invalid date or characters then it returns null
	 * 
	 * @param sInputDate
	 * @return Date
	 */
	public static java.util.Date getNormalDate(String sInputDate) {
		java.util.Date oNormalDate;
		try {
			SimpleDateFormat sdfFormat;
			sdfFormat = new SimpleDateFormat("M/d/yyyy");
			oNormalDate = sdfFormat.parse(sInputDate, new ParsePosition(0));
			// System.out.println( "dNormalDate: " + dNormalDate);

			int dt_l = sInputDate.length();
			Calendar oC = new GregorianCalendar();
			oC.setTime(oNormalDate);
			int iCalDate = oC.get(Calendar.DAY_OF_MONTH);
			int iCalMonth = oC.get(Calendar.MONTH) + 1;
			int iCalYear = oC.get(Calendar.YEAR);

			int iSeparatorA = sInputDate.indexOf("/");
			int iSeparatorB = sInputDate.lastIndexOf("/");
			int iMonth = Integer.parseInt(sInputDate.substring(0, iSeparatorA));
			int iDate = Integer.parseInt(sInputDate.substring(iSeparatorA + 1,
					iSeparatorB));
			int iYear = Integer.parseInt(sInputDate.substring(iSeparatorB + 1,
					dt_l));

			if (iCalDate != iDate || iCalMonth != iMonth || iCalYear != iYear
					|| iYear < 1753 || iYear > 9999) {
				oNormalDate = null;
			}
		} catch (Exception e) {
			oNormalDate = null;
		}
		return oNormalDate;
	}

	/**
	 * @method isEmail() Checks whether the email is Valid email or not
	 * @param String
	 *            sText
	 * @return boolean
	 */

	public static boolean isValidEmail(String sText) {
		String str = new String();
		String sAt = "@";
		int iStrLn = str.length();
		int iAtIndx = sText.indexOf(sAt);
		try {
			str = sText.toLowerCase().trim();
			if ((str.indexOf(' ') != -1) || (str.indexOf(',') != -1)
					|| (iAtIndx == 0) || (iAtIndx == -1) || (iAtIndx == iStrLn)
					|| (str.indexOf('@', iAtIndx + 1) != -1)
					|| (str.indexOf('.') == -1) || (str.indexOf('.') == 0)
					|| (str.indexOf('.') == iStrLn)) {
				return false;

			}
		} catch (StringIndexOutOfBoundsException se) {
			return false;
		}
		return true;

	}

	/**
	 * Validates whether object is empty
	 * 
	 * @param Object
	 * @return boolean
	 */

	public static boolean isEmpty(Object aObject) {
		System.out.println("** Object " + aObject);
		if (aObject == null) {
			return true;
		} else if (aObject instanceof String) {
			if (((String) aObject).length() == 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Validates whether string is an number
	 * 
	 * @param String
	 *            strNumber
	 * @param int length
	 * @return boolean
	 */

	public static boolean isNumber(String strNumber) {
		NumberFormat numberFormat = NumberFormat.getInstance();

		try {
			if (strNumber != null) {
				numberFormat.parse(strNumber);
				return true;
			}
		} catch (ParseException pEx) {
			return false;
		}
		return false;
	}

	/**
	 * Validates whether string size does not exceeds from length
	 * 
	 * @param String
	 *            string
	 * @param int length
	 * @return boolean
	 */

	public static boolean isValidLength(String inString, int length) {

		if (inString != null) {
			if (inString.length() == length) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Validates whether number size does not exceeds from length
	 * 
	 * @param long longNumber
	 * @param int length
	 * @return boolean
	 */

	public static boolean isValidLength(long longNumber, int length) {

		String longStr = String.valueOf(longNumber);
		return isValidLength(longStr, length);
	}

	/**
	 * Validates whether number size does not exceeds from length
	 * 
	 * @param double doubleNumber
	 * @param int length
	 * @return boolean
	 */

	public static boolean isValidLength(double doubleNumber, int length) {
		String doubleStr = String.valueOf(doubleNumber);
		return isValidLength(doubleStr, length);
	}

	/**
	 * Validates the date
	 * 
	 * @param Date
	 *            vDate
	 * @return boolean
	 */

	public static boolean isValidDate(String strDate) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		try {
			dateFormat.parse(strDate);
		} catch (Exception pEx) {
			System.out.println(pEx);
			return false;
		}
		return true;
	}

	/**
	 * Validates whether string is in a certain format like "0001","9999.99"
	 * etc.
	 * 
	 * @param String
	 * @param Format
	 * @return boolean
	 */

	public static boolean isValidFormat(String inString, String format) {
		// TO BE IMPLEMENTED LATER
		return (true);
	}

	/**
	 * Validates whether number falls in a range
	 * 
	 * @param double number - no to be checked for
	 * @param double startLimit
	 * @param double endLimit
	 * @return boolean
	 */

	public static boolean isNumberBetween(double number, double startLimit,
			double endLimit) {
		if ((number >= startLimit) && (number <= endLimit)) {
			return true;
		}
		return false;
	}

	/**
	 * Validates whether date falls in the given range
	 * 
	 * @param Date
	 *            date - Date to be checked for
	 * @param Date
	 *            startDate - Start Date
	 * @param Date
	 *            endDate - End Date
	 * @return boolean
	 */

	public static boolean isDateBetween(Date date, Date startDate, Date endDate) {

		if (date != null && startDate != null && endDate != null) {

			if ((date.after(startDate) || date.equals(startDate))
					&& (date.before(endDate) || date.equals(endDate))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Validates whether string is a alphabetic string like PERSON NAME
	 * 
	 * @param String
	 *            inString
	 * @return boolean
	 */

	public static boolean isAlphabeticString(String inString) {

		return isOnlyAlphabets(inString);
	}

	/**
	 * Validates whether two string are equals
	 * 
	 * @param String
	 *            string1
	 * @param String
	 *            string2
	 * @return boolean
	 */

	public static boolean isEquals(String inString1, String inString2) {
		if (inString1 != null && inString2 != null) {
			return inString1.equals(inString2);
		}
		return false;
	}

	/**
	 * Validates whether date1 is greater than date2
	 * 
	 * @param Date
	 *            date1
	 * @param Date
	 *            date2
	 * @return boolean
	 */

	public static boolean isMax(Date date1, Date date2) {

		if (date1.compareTo(date2) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Validates whether date1 is lesser than date2
	 * 
	 * @param Date
	 *            date1
	 * @param Date
	 *            date2
	 * @return boolean
	 */

	public static boolean isMin(Date date1, Date date2) {

		if (date1.compareTo(date2) < 0) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if string is *Yes* or *Y* or *true* then return true otherwise
	 * false
	 * 
	 * @param Object
	 *            inObject
	 * @return boolean
	 */

	public static boolean isTrue(Object inObject) {

		if (inObject != null) {
			if (inObject instanceof String) {
				String tmpString = (String) inObject;
				if (tmpString.equals("Yes") || tmpString.equals("Y")
						|| tmpString.equals("true")) {
					return true;
				}
			}
		}
		return false;
	}
}
