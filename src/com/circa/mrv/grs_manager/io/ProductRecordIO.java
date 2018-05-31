/**
 * 
 */
package com.circa.mrv.grs_manager.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import com.circa.mrv.grs_manager.directory.CompanyDirectory;
import com.circa.mrv.grs_manager.manager.GRSManager;
import com.circa.mrv.grs_manager.niox.Component;
import com.circa.mrv.grs_manager.niox.Product;
import com.circa.mrv.grs_manager.util.LinkedListRecursive;


/**
 * ProductRecordIO class handles I/O for NIOX product records. 
 * 
 * @author Arthur Vargas
 *
 */
public class ProductRecordIO {
	
	/**
	 * Reads product records from a file and generates a list of valid Niox products.
	 * Any invalid products are ignored. If the file to read cannot be found or
	 * the permissions are incorrect a FileNotFoundException is thrown.
	 * 
	 * @param fileName file to read product records from
	 * @return product a list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 *             
	 */
	public static LinkedListRecursive<Product> readProductRecords(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		Scanner fileReader = new Scanner(file);
		//while(fileReader.hasNextLine())
			//System.out.println(fileReader.nextLine());
		LinkedListRecursive<Product> products = new LinkedListRecursive<Product>();
		String line = null;
		while (fileReader.hasNextLine()) {
			
			line = fileReader.nextLine();
			
			try {
				
				products.add( ProductRecordIO.readLine( line ) ); 
				
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
			
		}
		fileReader.close();
		return products;
	}

	/**
	 * Reads the value fields from the string and constructs an instance of Product class.
	 * 
	 * @param nextLine the line that is to be processed
	 * @return p a product constructed from the line
	 * @throws IllegalArgumentException if the parameters are not in the correct order or if there
	 * is an incorrect number of parameters.
	 */
	public static Product readLine(String nextLine) throws IllegalArgumentException {
		Scanner scan = new Scanner(nextLine);
		String[] data = new String[7];
		String word = null;
		Component c = null;
		scan.useDelimiter(",");
		try {
		for(int i = 0;scan.hasNext();i++) {
			
			word = scan.next();
			if(word != null && !word.equals("")) {
				data[i] = word;
			} else {
				data[i] = "no value";
			}
			System.out.println(data[i] + " " + i);
		}
		c = new Component(data[0],data[1],data[2],data[3],data[4],data[5],data[6]);
		scan.close();
		} catch (Exception e) {
			System.out.println("An Exception was caught: " + e.getMessage());
		}
		
		return c;
	}
	
	

	/**
	 * If the line contains
	 * @param nextLine line to count tokens on.
	 * @return true if every character in the string is a digit
	 */
	private static boolean verifyCharacters(String line) {
		for(int i = 0; i < line.length(); i++) {
			if(!Character.isDigit(line.charAt(i)))
				return false;
		}
		return true;
	}
	
	/**
	 * 
	 */
	private static boolean attemptPriceParse(String line) {
		try {
			Double.parseDouble(line);
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}

	/**
	 * Writes the given list of products to a text file.
	 * 
	 * @param fileName
	 *            name of file to write products to
	 * @param products
	 *            list of products to write
	 * @throws IOException
	 *             if file can not be written to
	 */
	public static void writeProductRecords(String fileName, LinkedListRecursive<Product> products) throws IOException {

		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < products.size(); i++) {
			fileWriter.println(products.get(i).toString());
		}

		fileWriter.close();
	}

}
