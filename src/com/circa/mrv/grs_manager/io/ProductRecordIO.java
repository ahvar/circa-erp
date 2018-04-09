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

import edu.ncsu.csc216.collections.list.SortedList;

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
		Scanner fileReader = new Scanner(new File(fileName));
		LinkedListRecursive<Product> products = new LinkedListRecursive<Product>();
		while (fileReader.hasNextLine()) {
			try {
				if( ( !products.add( ProductRecordIO.readLine( fileReader.nextLine() ) ) ) ) 
					throw new IllegalArgumentException("Duplicate"); 
			    
					// add to records

					//boolean duplicate = false;
					//for (int i = 0; i < courses.size(); i++) {
						//Product c = courses.get(i);
						//if (course.getName().equals(c.getName()) && course.getSection().equals(c.getSection())) {
							// it's a duplicate
							//duplicate = true;
						//}
					//}
					//if (!duplicate) {
						//courses.add(course);
					//}
			
			} catch (IllegalArgumentException e) {
				// skip the line
			}
		}
		fileReader.close();
		System.out.println(products.size());
		return products;
	}

	/**
	 * Reads the value fields from the string and constructs an instance of Product class.
	 * @param nextLine the line that is to be processed
	 * @return p a product constructed from the line
	 * @throws IllegalArgumentException if the parameters are not in the correct order or if there
	 * is an incorrect number of parameters.
	 */
	private static Product readLine(String nextLine) {
		Scanner word = new Scanner(nextLine);
		Component c = new Component(word.next(),word.next(),word.next());
		word.close();
		return c;
		
	}

	/**
	 * counts the number of tokens that exist in the passed line. uses a comma delimiter.
	 * @param nextLine line to count tokens on.
	 * @return tokenCount the number of tokens found
	 */
	private static int countTokens(String nextLine) {
		int tokenCount = 0;
		Scanner lineScanner = new Scanner(nextLine);
		lineScanner.useDelimiter(",");

		while (lineScanner.hasNext()) {
			try {
				lineScanner.next();
				tokenCount++;
			} catch (IllegalArgumentException e) {
				//Something odd would have to happen to trigger this.
			}
		}
		lineScanner.close();
		return tokenCount;
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
