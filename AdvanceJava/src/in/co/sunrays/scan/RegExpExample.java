/*
 * Created on Sep 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
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
 * @Copyright (c) SunilOS. All rights reserved.
 * @URL www.SunilOS.com
 */
public class RegExpExample {

	protected String rootDirectory = null;

	protected ArrayList expressions = null;

	protected ArrayList messages = null;

	protected ArrayList patterns = null;

	protected String[] excludeFiles = null;

	protected String[] comments = null;

	public static String separater = "&";

	// FileManager fileManager = null;

	protected int scannedFileCount = 0;

	public RegExpExample() {
		this(".");
	}

	public RegExpExample(String dir) {
		rootDirectory = dir;
		init();
	}

	public void init() {

		expressions = new ArrayList();
		messages = new ArrayList();
		patterns = new ArrayList();

		ResourceBundle bundle = ResourceBundle.getBundle("com.resources.scon");

		String exps = bundle.getString("expressions");
		String msgs = bundle.getString("messages");
		String exFiles = bundle.getString("excludeFiles");
		String comm = bundle.getString("comments");

		StringTokenizer stringTokenizer = new StringTokenizer(exps, separater);
		String token = null;

		while (stringTokenizer.hasMoreTokens()) {
			token = stringTokenizer.nextToken();
			expressions.add(token);
			patterns.add(Pattern.compile(token));
		}

		stringTokenizer = new StringTokenizer(msgs, separater);

		while (stringTokenizer.hasMoreTokens()) {
			token = stringTokenizer.nextToken();
			messages.add(token);
		}

		stringTokenizer = new StringTokenizer(exFiles, separater);
		excludeFiles = new String[stringTokenizer.countTokens()];
		// System.out.println("exFiles size" + excludeFiles.length);
		for (int i = 0; stringTokenizer.hasMoreTokens(); i++) {
			token = stringTokenizer.nextToken();
			excludeFiles[i] = token;
		}

		stringTokenizer = new StringTokenizer(comm, separater);
		comments = new String[stringTokenizer.countTokens()];

		for (int i = 0; stringTokenizer.hasMoreTokens(); i++) {
			token = stringTokenizer.nextToken();
			comments[i] = token;
		}

		int size = expressions.size();

		for (int i = 0; i < size; i++) {
			System.out.println(expressions.get(i) + "=" + messages.get(i));
		}

	}

	public boolean canScanLine(String line) {
		boolean flag = true;

		int size = comments.length;

		for (int i = 0; i < size; i++) {

			if (line.trim().startsWith(comments[i])) {
				flag = false;
				// System.out.println("Excluded Line " + line);
				break;
			}
		}

		return flag;
	}

	public boolean canScan(String fileName) {
		boolean flag = true;

		int size = excludeFiles.length;

		String extention = null;

		for (int i = 0; i < size; i++) {

			if (fileName.toLowerCase().endsWith(excludeFiles[i])) {
				flag = false;
				System.out.println("Excluded File " + fileName);
				break;
			}
		}

		return flag;
	}

	public void scanDirectory(String dirname, File dir) throws IOException {
		String[] files = dir.list();
		int size = files.length;

		// System.out.println(" size -- " + size);
		File newFile = null;

		for (int i = 0; i < size; i++) {
			newFile = new File(dir, files[i]);

			// System.out.println(files[i] + " is Directory $$ " +
			// newFile.isDirectory());

			if (newFile.isDirectory()) {
				scanDirectory(files[i], newFile);
			} else if (canScan(files[i])) {
				scanFile(dirname, files[i], newFile);
			}
		}

	}

	public void log(String message, String dirName, String name,
			int lineNumber, String line) throws IOException {

		/*
		 * if ("Drive Mapping".equals(message)) {
		 * fileManager.appendRow(FileManager.DRIVE_MAPPING_SHEET, dirName, name,
		 * lineNumber, line); } else if ("IP Address".equals(message)) {
		 * fileManager.appendRow(FileManager.IP_ADDRESS_SHEET, dirName, name,
		 * lineNumber, line); } else if ("URLS".equals(message)) {
		 * fileManager.appendRow(FileManager.URL_SHEET, dirName, name,
		 * lineNumber, line); } else if ("Others".equals(message)) {
		 * fileManager.appendRow(FileManager.OTHER_SHEET, dirName, name,
		 * lineNumber, line); }
		 */

	}

	public void compareAndLogFile(String dirName, String name, int lineNumber,
			String line) throws IOException {

		int size = messages.size();

		String message = null;
		Pattern pattern = null;

		for (int i = 0; i < size; i++) {
			message = (String) messages.get(i);
			pattern = (Pattern) patterns.get(i);
			if (compare(line.toLowerCase(), pattern)) {
				log(message, dirName, name, lineNumber, line);
				scannedFileCount++;
			}
		}

	}

	public boolean compare(String str, Pattern p) {
		Matcher m = p.matcher(str);
		return m.find();
	}

	public void scanFile(String dirName, String name, File file)
			throws IOException {

		scannedFileCount = 0;

		LineNumberReader br = null;

		br = new LineNumberReader(new FileReader(file));

		String line = null;
		int lineNumber = 0;

		line = br.readLine();
		lineNumber = br.getLineNumber();

		while (line != null) {
			if (canScanLine(line)) {
				compareAndLogFile(file.getParent(), name, lineNumber, line);
			}
			line = br.readLine();
			lineNumber = br.getLineNumber();
		}

		/*
		 * fileManager.appendRow(FileManager.SCANNED_FILES_SHEET,
		 * file.getParent(), name, scannedFileCount, null);
		 */

		br.close();

	}

	public void startScan() throws IOException {

		/*
		 * File root = new File(rootDirectory); fileManager = new
		 * FileManager(rootDirectory, "scon.xls", messages); fileManager.open();
		 * scanDirectory(rootDirectory, root); fileManager.flush();
		 */

	}

	public static void main(String[] args) throws Exception {

		RegExpExample codeScan = new RegExpExample(args[0]);
		System.out.println("Scanning Started");
		codeScan.startScan();
		System.out.println("Scanning completed, report is at " + args[0]
				+ "\\scon.xls");
		// codeScan.compare();
	}
}
