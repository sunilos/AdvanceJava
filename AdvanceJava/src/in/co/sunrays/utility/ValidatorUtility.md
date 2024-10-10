```java
package in.co.sunrays.utility;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

/**
 * Utility class providing common validation methods.
 * All methods in this class are static and can be accessed without creating an instance.
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */
public class ValidatorUtility {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private ValidatorUtility() {
    }

    /**
     * Checks whether the provided string contains only alphabetic characters and spaces.
     *
     * @param sText the string to check
     * @return true if the string contains only alphabetic characters and spaces; false otherwise
     */
    public static boolean isOnlyAlphabets(String sText) {
        char cCharAtLocation;
        // Iterate through each character in the string
        for (int i = 0; sText != null && i < sText.length(); i++) {
            cCharAtLocation = sText.charAt(i);
            // Check if character is neither a letter nor a space
            if (!Character.isLetter(cCharAtLocation) && !Character.isSpaceChar(cCharAtLocation)) {
                return false; // Non-alphabetic character found
            }
        }
        return true; // All characters are valid
    }

    /**
     * Converts a date string in the format "MM/DD/YYYY" to a Date object.
     * If the input string is invalid, it returns null.
     *
     * @param sInputDate the date string to parse
     * @return the corresponding Date object, or null if invalid
     */
    public static java.util.Date getNormalDate(String sInputDate) {
        java.util.Date oNormalDate;
        try {
            SimpleDateFormat sdfFormat = new SimpleDateFormat("M/d/yyyy");
            oNormalDate = sdfFormat.parse(sInputDate, new ParsePosition(0));

            // Validate the parsed date against the original string
            Calendar oC = new GregorianCalendar();
            oC.setTime(oNormalDate);
            int iCalDate = oC.get(Calendar.DAY_OF_MONTH);
            int iCalMonth = oC.get(Calendar.MONTH) + 1; // Months are 0-based
            int iCalYear = oC.get(Calendar.YEAR);

            // Extract month, day, and year from the input string
            int iSeparatorA = sInputDate.indexOf("/");
            int iSeparatorB = sInputDate.lastIndexOf("/");
            int iMonth = Integer.parseInt(sInputDate.substring(0, iSeparatorA));
            int iDate = Integer.parseInt(sInputDate.substring(iSeparatorA + 1, iSeparatorB));
            int iYear = Integer.parseInt(sInputDate.substring(iSeparatorB + 1));

            // Check for validity of the date components
            if (iCalDate != iDate || iCalMonth != iMonth || iCalYear != iYear || iYear < 1753 || iYear > 9999) {
                oNormalDate = null; // Invalid date
            }
        } catch (Exception e) {
            oNormalDate = null; // Exception indicates an invalid date
        }
        return oNormalDate; // Return the parsed date or null
    }

    /**
     * Validates whether the provided string is a valid email address.
     *
     * @param sText the email string to validate
     * @return true if valid; false otherwise
     */
    public static boolean isValidEmail(String sText) {
        String str = sText.toLowerCase().trim();
        String sAt = "@";
        int iAtIndx = str.indexOf(sAt);

        // Check various conditions for a valid email
        if ((str.indexOf(' ') != -1) || (str.indexOf(',') != -1) || (iAtIndx <= 0) || (iAtIndx == str.length())
                || (str.indexOf('@', iAtIndx + 1) != -1) || (str.indexOf('.') == -1)
                || (str.indexOf('.') == 0) || (str.indexOf('.') == str.length())) {
            return false; // Invalid email conditions
        }
        return true; // Valid email
    }

    /**
     * Checks if the provided object is empty.
     * An object is considered empty if it is null or, if it's a string, its length is 0.
     *
     * @param aObject the object to check
     * @return true if empty; false otherwise
     */
    public static boolean isEmpty(Object aObject) {
        if (aObject == null) {
            return true; // Null object
        } else if (aObject instanceof String) {
            return ((String) aObject).length() == 0; // Empty string
        }
        return false; // Non-empty object
    }

    /**
     * Validates whether the provided string can be parsed as a number.
     *
     * @param strNumber the string to validate
     * @return true if the string is a valid number; false otherwise
     */
    public static boolean isNumber(String strNumber) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        try {
            if (strNumber != null) {
                numberFormat.parse(strNumber); // Attempt to parse as a number
                return true; // Successfully parsed
            }
        } catch (ParseException pEx) {
            return false; // Parsing failed
        }
        return false; // Not a number
    }

    /**
     * Validates whether the length of the provided string matches the specified length.
     *
     * @param inString the string to validate
     * @param length   the required length
     * @return true if the string length matches; false otherwise
     */
    public static boolean isValidLength(String inString, int length) {
        return inString != null && inString.length() == length; // Check for null and length match
    }

    /**
     * Validates whether the length of the provided long number matches the specified length.
     *
     * @param longNumber the long number to validate
     * @param length     the required length
     * @return true if the number length matches; false otherwise
     */
    public static boolean isValidLength(long longNumber, int length) {
        return isValidLength(String.valueOf(longNumber), length); // Convert to string and validate length
    }

    /**
     * Validates whether the length of the provided double number matches the specified length.
     *
     * @param doubleNumber the double number to validate
     * @param length       the required length
     * @return true if the number length matches; false otherwise
     */
    public static boolean isValidLength(double doubleNumber, int length) {
        return isValidLength(String.valueOf(doubleNumber), length); // Convert to string and validate length
    }

    /**
     * Validates whether the provided date string is a valid date in the format "MM/DD/YYYY".
     *
     * @param strDate the date string to validate
     * @return true if valid; false otherwise
     */
    public static boolean isValidDate(String strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            dateFormat.parse(strDate); // Attempt to parse
        } catch (Exception pEx) {
            return false; // Parsing failed
        }
        return true; // Successfully parsed
    }

    /**
     * Validates whether the provided string matches a specific format (implementation to be added).
     *
     * @param inString the string to validate
     * @param format   the format to match
     * @return true if matches the format; false otherwise
     */
    public static boolean isValidFormat(String inString, String format) {
        // TO BE IMPLEMENTED LATER
        return true; // Placeholder implementation
    }

    /**
     * Checks if the provided number is within the specified range (inclusive).
     *
     * @param number      the number to check
     * @param startLimit  the lower bound
     * @param endLimit    the upper bound
     * @return true if the number is in range; false otherwise
     */
    public static boolean isNumberBetween(double number, double startLimit, double endLimit) {
        return (number >= startLimit && number <= endLimit); // Check range
    }

    /**
     * Checks if the provided date falls within the specified range (inclusive).
     *
     * @param date      the date to check
     * @param startDate the start of the range
     * @param endDate   the end of the range
     * @return true if the date is in range; false otherwise
     */
    public static boolean isDateBetween(Date date, Date startDate, Date endDate) {
        if (date != null && startDate != null && endDate != null) {
            return (date.after(startDate) || date.equals(startDate)) &&
                   (date.before(endDate) || date.equals(endDate)); // Check range
        }
        return false; // One or more dates are null
    }

    /**
     * Validates whether the provided string contains only alphabetic characters.
     *
     * @param inString the string to validate
     * @return true if valid

; false otherwise
     */
    public static boolean isAlphabeticString(String inString) {
        return isOnlyAlphabets(inString); // Reuse the method for alphabet validation
    }

    /**
     * Checks whether two strings are equal.
     *
     * @param inString1 the first string
     * @param inString2 the second string
     * @return true if equal; false otherwise
     */
    public static boolean isEquals(String inString1, String inString2) {
        if (inString1 != null && inString2 != null) {
            return inString1.equals(inString2); // Use String's equals method
        }
        return false; // One or both strings are null
    }

    /**
     * Checks whether the first date is greater than the second date.
     *
     * @param date1 the first date
     * @param date2 the second date
     * @return true if date1 is greater; false otherwise
     */
    public static boolean isMax(Date date1, Date date2) {
        return date1.compareTo(date2) > 0; // Compare dates
    }

    /**
     * Checks whether the first date is less than the second date.
     *
     * @param date1 the first date
     * @param date2 the second date
     * @return true if date1 is less; false otherwise
     */
    public static boolean isMin(Date date1, Date date2) {
        return date1.compareTo(date2) < 0; // Compare dates
    }

    /**
     * Checks if the provided object represents a "true" value.
     * Accepted values are "Yes", "Y", or "true" (case insensitive).
     *
     * @param inObject the object to check
     * @return true if it represents a "true" value; false otherwise
     */
    public static boolean isTrue(Object inObject) {
        if (inObject != null) {
            if (inObject instanceof String) {
                String tmpString = (String) inObject;
                return tmpString.equalsIgnoreCase("Yes") ||
                       tmpString.equalsIgnoreCase("Y") ||
                       tmpString.equalsIgnoreCase("true"); // Check for true values
            }
        }
        return false; // Not a true value
    }
}
```

### Explanation of Key Components

1. **Class Declaration**: The class is declared as a utility class, meaning it only contains static methods and cannot be instantiated.

2. **Validation Methods**: Each method performs a specific validation task:
   - **`isOnlyAlphabets`**: Checks if a string contains only alphabetic characters and spaces.
   - **`getNormalDate`**: Parses a date string and checks if it’s valid.
   - **`isValidEmail`**: Validates the format of an email address.
   - **`isEmpty`**: Determines if an object or string is empty or null.
   - **`isNumber`**: Checks if a string can be parsed as a number.
   - **`isValidLength`**: Validates string length against a specified requirement.

3. **Error Handling**: The methods include try-catch blocks to handle exceptions that may arise during parsing, returning `false` or `null` as appropriate.

4. **Use of Java API**: The class utilizes Java's `SimpleDateFormat`, `NumberFormat`, and calendar classes for date and number validation.

5. **Modularity**: Each method is designed to be reusable and focused on a single task, adhering to the Single Responsibility Principle.
