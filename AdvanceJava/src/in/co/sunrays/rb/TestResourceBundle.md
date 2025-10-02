```java
package in.co.sunrays.rb;

import java.util.ResourceBundle;

/**
 * This class demonstrates the use of ResourceBundle to read values 
 * from a .properties file using keys. 
 * 
 * It retrieves and prints the value associated with the key "organization.name".
 * 
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */
public class TestResourceBundle {

    /**
     * The main method serves as the entry point of the program.
     * It loads a ResourceBundle and retrieves a value using a specified key.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {

        // Load the ResourceBundle from the specified package
        ResourceBundle rb = ResourceBundle.getBundle("in.co.sunrays.rb.app");

        // Retrieve the value associated with the key "organization.name"
        String value = rb.getString("organization.name");

        // Print the retrieved value to the console
        System.out.println(value);
    }
}
```

### Explanation of the Code:

1. **Package and Import Statements:**
   - The code is part of the `in.co.sunrays.rb` package.
   - It imports the `ResourceBundle` class from the `java.util` package, which is used for internationalization and localization of applications.

2. **Class Description (`TestResourceBundle`):**
   - This class is designed to demonstrate how to use the `ResourceBundle` class to read values from a properties file.
   - It specifically retrieves the value associated with the key `organization.name`.

3. **Main Method:**
   - The `main` method is the entry point of the program. It is where the execution begins.
   - It is responsible for loading the `ResourceBundle` and retrieving the desired value.

4. **Loading the ResourceBundle:**
   - The `ResourceBundle` is loaded using the `getBundle` method, which takes the name of the properties file (without the `.properties` extension) as an argument.
   - In this case, it is looking for `in/co/sunrays/rb/app.properties`.

5. **Retrieving a Value:**
   - The `getString` method is called on the `ResourceBundle` object to retrieve the value associated with the key `organization.name`.
   - This key should exist in the properties file; otherwise, a `MissingResourceException` will be thrown.

6. **Output:**
   - The retrieved value is printed to the console using `System.out.println`.

### Key Points:
- **ResourceBundle**: It is a powerful tool for managing locale-specific objects, which allows applications to easily handle internationalization by using properties files.
- **Keys and Values**: The properties file contains key-value pairs, where keys are used to retrieve the corresponding values. For example, if the properties file contains `organization.name=SunilOS`, the output will be `SunilOS`.
- **Exception Handling**: In production code, it's advisable to handle potential exceptions (like `MissingResourceException`) that may occur while loading the ResourceBundle or retrieving values to ensure robustness.
