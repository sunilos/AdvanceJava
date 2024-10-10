```java
package in.co.sunrays.scan;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The RegExpExample class scans files in a specified directory for lines 
 * that match given regular expressions. It uses a ResourceBundle to load 
 * expressions, messages, excluded file types, and comment tokens from a 
 * properties file. The class provides functionality to ignore certain 
 * files and lines based on the configured patterns and comments.
 *
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */
public class RegExpExample {

    // Root directory for scanning files
    protected String rootDirectory = null;

    // List of regular expressions to match in the files
    protected ArrayList<String> expressions = null;

    // List of messages corresponding to each expression
    protected ArrayList<String> messages = null;

    // Compiled regex patterns for efficient matching
    protected ArrayList<Pattern> patterns = null;

    // File extensions to exclude from scanning
    protected String[] excludeFiles = null;

    // Comment tokens that indicate lines to be excluded
    protected String[] comments = null;

    // Separator used for splitting expressions in the properties file
    public static String separater = "&";

    // Counter for the number of scanned lines
    protected int scannedFileCount = 0;

    /**
     * Default constructor that initializes the scanning object
     * with the current directory as the root directory.
     */
    public RegExpExample() {
        this(".");
    }

    /**
     * Constructor that initializes the scanning object with a specified root directory.
     *
     * @param dir The directory to be scanned.
     */
    public RegExpExample(String dir) {
        rootDirectory = dir;
        init();
    }

    /**
     * Initializes the expressions, messages, excluded file types, and comments
     * by loading them from a ResourceBundle. It compiles regex patterns for matching.
     */
    public void init() {
        expressions = new ArrayList<>();
        messages = new ArrayList<>();
        patterns = new ArrayList<>();

        // Load properties from ResourceBundle
        ResourceBundle bundle = ResourceBundle.getBundle("com.resources.scon");

        // Retrieve expressions, messages, excluded files, and comments
        String exps = bundle.getString("expressions");
        String msgs = bundle.getString("messages");
        String exFiles = bundle.getString("excludeFiles");
        String comm = bundle.getString("comments");

        // Tokenize and compile expressions into patterns
        StringTokenizer stringTokenizer = new StringTokenizer(exps, separater);
        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            expressions.add(token);
            patterns.add(Pattern.compile(token)); // Compile regex for matching
        }

        // Tokenize messages associated with each expression
        stringTokenizer = new StringTokenizer(msgs, separater);
        while (stringTokenizer.hasMoreTokens()) {
            messages.add(stringTokenizer.nextToken());
        }

        // Tokenize file extensions to exclude
        stringTokenizer = new StringTokenizer(exFiles, separater);
        excludeFiles = new String[stringTokenizer.countTokens()];
        for (int i = 0; stringTokenizer.hasMoreTokens(); i++) {
            excludeFiles[i] = stringTokenizer.nextToken();
        }

        // Tokenize comment tokens
        stringTokenizer = new StringTokenizer(comm, separater);
        comments = new String[stringTokenizer.countTokens()];
        for (int i = 0; stringTokenizer.hasMoreTokens(); i++) {
            comments[i] = stringTokenizer.nextToken();
        }

        // Print loaded expressions and messages for verification
        for (int i = 0; i < expressions.size(); i++) {
            System.out.println(expressions.get(i) + "=" + messages.get(i));
        }
    }

    /**
     * Checks if a given line can be scanned based on comment tokens.
     *
     * @param line The line of text to evaluate.
     * @return true if the line can be scanned, false if it starts with a comment token.
     */
    public boolean canScanLine(String line) {
        for (String comment : comments) {
            if (line.trim().startsWith(comment)) {
                return false; // Line starts with a comment, skip scanning
            }
        }
        return true; // Line can be scanned
    }

    /**
     * Checks if a file can be scanned based on its extension.
     *
     * @param fileName The name of the file to check.
     * @return true if the file can be scanned, false if it matches excluded patterns.
     */
    public boolean canScan(String fileName) {
        for (String excludeFile : excludeFiles) {
            if (fileName.toLowerCase().endsWith(excludeFile)) {
                System.out.println("Excluded File " + fileName);
                return false; // File matches an excluded pattern, do not scan
            }
        }
        return true; // File is eligible for scanning
    }

    /**
     * Recursively scans a directory for files and subdirectories.
     *
     * @param dirname The name of the directory being scanned.
     * @param dir The File object representing the directory.
     * @throws IOException If an I/O error occurs while accessing files.
     */
    public void scanDirectory(String dirname, File dir) throws IOException {
        String[] files = dir.list();
        if (files != null) {
            for (String file : files) {
                File newFile = new File(dir, file);
                if (newFile.isDirectory()) {
                    scanDirectory(file, newFile); // Recurse into subdirectories
                } else if (canScan(file)) {
                    scanFile(dirname, file, newFile); // Scan the file
                }
            }
        }
    }

    /**
     * Logs a message based on the comparison results during scanning.
     *
     * @param message The message to log.
     * @param dirName The directory of the file being scanned.
     * @param name The name of the file being scanned.
     * @param lineNumber The line number where the match was found.
     * @param line The line of text that matched.
     * @throws IOException If an I/O error occurs while logging.
     */
    public void log(String message, String dirName, String name,
                    int lineNumber, String line) throws IOException {
        // Log the match to a file or reporting mechanism (to be implemented)
    }

    /**
     * Compares each line of a file against loaded regular expressions and logs matches found.
     *
     * @param dirName The directory name of the file being scanned.
     * @param name The name of the file being scanned.
     * @param lineNumber The current line number being processed.
     * @param line The current line of text being compared.
     * @throws IOException If an I/O error occurs while logging matches.
     */
    public void compareAndLogFile(String dirName, String name, int lineNumber,
                                   String line) throws IOException {
        for (int i = 0; i < messages.size(); i++) {
            String message = messages.get(i);
            Pattern pattern = patterns.get(i);
            if (compare(line.toLowerCase(), pattern)) {
                log(message, dirName, name, lineNumber, line); // Log the match
                scannedFileCount++; // Increment the count of scanned lines
            }
        }
    }

    /**
     * Compares a string against a compiled regular expression pattern.
     *
     * @param str The string to compare.
     * @param p The compiled Pattern object representing the regex.
     * @return true if a match is found, false otherwise.
     */
    public boolean compare(String str, Pattern p) {
        Matcher m = p.matcher(str);
        return m.find(); // Returns true if a match is found
    }

    /**
     * Scans a single file for lines that match any of the loaded regular expressions.
     *
     * @param dirName The directory name of the file being scanned.
     * @param name The name of the file being scanned.
     * @param file The File object representing the file to scan.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public void scanFile(String dirName, String name, File file)
            throws IOException {
        scannedFileCount = 0; // Reset count for the new file

        try (LineNumberReader br = new LineNumberReader(new FileReader(file))) {
            String line;
            int lineNumber;

            // Read each line of the file
            while ((line = br.readLine()) != null) {
                lineNumber = br.getLineNumber(); // Get the current line number
                if (canScanLine(line)) {
                    compareAndLogFile(file.getParent(), name, lineNumber, line); // Compare and log matches
                }
            }
        } // Automatically closes the reader here
    }

    /**
     * Starts the scanning process for the root directory specified.
     *
     * @throws IOException If an I/O error occurs while scanning.
     */
    public void startScan() throws IOException {
        // Create a File object for the root directory and initiate scanning
        File rootDir = new File(rootDirectory);
        scanDirectory(rootDir.getName(), rootDir); // Start scanning the root directory
    }

    /**
     * The main method initializes the scanning process.
     * It expects

 the directory to scan as a command-line argument.
     *
     * @param args Command-line arguments specifying the directory to scan.
     * @throws Exception If an error occurs during scanning.
     */
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Please specify a directory to scan.");
            return;
        }

        RegExpExample codeScan = new RegExpExample(args[0]);
        System.out.println("Scanning Started");
        codeScan.startScan(); // Start scanning the specified directory
        System.out.println("Scanning completed, report is at " + args[0]
                + "\\scon.xls");
    }
}
```
### Explanation of Code Functionality:
- **Initialization**: The `RegExpExample` class is initialized with a root directory, from which it loads expressions, messages, excluded file types, and comments from a properties file using `ResourceBundle`.
- **Scanning**: The class recursively scans through the directory structure, checking each file against the specified patterns and ignoring those that match excluded types or comment tokens.
- **Logging**: When a match is found, the relevant message and file information are intended to be logged for reporting (the logging implementation needs to be added).
- **Regex Comparison**: Each line of the file is compared against the compiled regex patterns, and matches are logged accordingly.
- **Main Method**: The program entry point initializes scanning based on command-line arguments, providing flexibility for different directory inputs.

This comprehensive documentation and explanation should help both current and future developers understand the code's purpose and functionality better.
