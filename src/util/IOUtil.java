package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class IOUtil {
	
	private IOUtil() {}
	
	/**
	 * checks if a string is an integer
	 * @param in string to test
	 * @return true if the string can be parsed to an integer
	 */
	public static boolean isNumber(String in) {
		try{
			Integer.parseInt(in);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * writes to a document at the path specified
	 * @param outPath path of the document to write to
	 * @param strings the strings to write
	 * @throws IOException If there's a problem writing to the file
	 */
	public static void writeFile(String outPath, String... strings) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(outPath));
		for (int x = 0; x < strings.length; x++) {
			writer.write(strings[x]);
			writer.newLine();
		}
		writer.close();
	}
	
	/**
	 * Reads the file that is given and creates a list from it
	 * @param input name of file to be read
	 * @throws IOException Throws an exception if there's a problem reading the file
	 */
	public static ArrayList<String> readFile(String input) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(input));
		ArrayList<String> output = new ArrayList<String>();
		
		String inputString = reader.readLine();
		while(inputString != null) {
			output.add(inputString);
			inputString = reader.readLine();
		}
		reader.close();
		return output;
	}
	
	/**
	 * Reads the lines of a document and returns the entire document as a String Array
	 * @param document name of document, or path
	 * @return text in document as a string array, each element is one line.
	 * @throws IOException if something goes wrong with inputs
	 */
	public static String[] readLines(String document) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(document));
		ArrayList<String> output = new ArrayList<String>();
		
		String inputString = reader.readLine();
		while(inputString != null) {
			output.add(inputString);
			inputString = reader.readLine();
		}
		reader.close();
		return output.toArray(new String[output.size()]);
	}
	
}
