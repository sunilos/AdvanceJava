```java
package in.co.sunrays.rb;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class demonstrates the use of ResourceBundle to read localized strings 
 * from .properties files based on the specified locale.
 * 
 * It retrieves greeting messages in different languages (default, Spanish, Hindi).
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */
public class TestMultiLanguage {

    /**
     * The main method serves as the entry point of the program.
     * It loads and prints greeting messages based on different locales.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {

        // Load the default ResourceBundle for the application
        ResourceBundle rb = ResourceBundle.getBundle("in.co.sunrays.rb.app");

        // Retrieve and print the greeting message for the default locale
        System.out.println(rb.getString("greeting"));

        // Create a Locale object for Spanish and load the corresponding ResourceBundle
        rb = ResourceBundle.getBundle("in.co.sunrays.rb.app", new Locale("es"));

        // Retrieve and print the greeting message for the Spanish locale
        System.out.println(rb.getString("greeting"));

        // Create a Locale object for Hindi and load the corresponding ResourceBundle
        rb = ResourceBundle.getBundle("in.co.sunrays.rb.app", new Locale("hi"));

        // Retrieve and print the greeting message for the Hindi locale
        System.out.println(rb.getString("greeting"));

        // Create a Locale object for Hindi specific to India and load the corresponding ResourceBundle
        rb = ResourceBundle.getBundle("in.co.sunrays.rb.app", new Locale("hi", "IN"));

        // Retrieve and print the greeting message for the Hindi (India) locale
        System.out.println(rb.getString("greeting"));

        // Create a Locale object for Hindi specific to Uttar Pradesh and load the corresponding ResourceBundle
        rb = ResourceBundle.getBundle("in.co.sunrays.rb.app", new Locale("hi", "IN", "UP"));

        // Retrieve and print the greeting message for the Hindi (India, Uttar Pradesh) locale
        System.out.println(rb.getString("greeting"));
    }
}
```

### Explanation of the Code:

1. **Package and Imports:**
   - The code is part of the `in.co.sunrays.rb` package.
   - It imports `Locale` and `ResourceBundle` classes from the `java.util` package, which are used for handling internationalization.

2. **Class Description (`TestMultiLanguage`):**
   - This class demonstrates how to use the `ResourceBundle` class to retrieve localized strings from `.properties` files.
   - It retrieves greeting messages in different languages based on the specified locale.

3. **Main Method:**
   - The `main` method is the entry point of the program.
   - It demonstrates loading and printing greeting messages for various locales.

4. **Default Locale:**
   - The `ResourceBundle` is initialized to load the default properties file `app.properties` from the package `in.co.sunrays.rb`.
   - The greeting message for the default locale is retrieved and printed using `rb.getString("greeting")`.

5. **Spanish Locale:**
   - A new `Locale` object is created for Spanish (`new Locale("es")`).
   - The corresponding `ResourceBundle` is loaded, and the greeting message for the Spanish locale is printed.

6. **Hindi Locale:**
   - A `Locale` object for Hindi (`new Locale("hi")`) is created.
   - The corresponding `ResourceBundle` is loaded, and the greeting message for the Hindi locale is printed.

7. **Hindi Locale for India:**
   - A `Locale` object for Hindi specific to India is created (`new Locale("hi", "IN")`).
   - The corresponding `ResourceBundle` is loaded, and the greeting message for the Hindi (India) locale is printed.

8. **Hindi Locale for Uttar Pradesh:**
   - A `Locale` object for Hindi specific to Uttar Pradesh is created (`new Locale("hi", "IN", "UP")`).
   - The corresponding `ResourceBundle` is loaded, and the greeting message for the Hindi (India, Uttar Pradesh) locale is printed.

### Key Points:
- The `ResourceBundle` class is used to manage locale-specific objects, allowing for easy internationalization of applications.
- Locales can specify language, country, and variant, enabling precise control over the localized content displayed to users.
- To fully utilize this class, you need corresponding `.properties` files (`app.properties`, `app_es.properties`, `app_hi.properties`, etc.) in the specified package to provide the localized strings.
