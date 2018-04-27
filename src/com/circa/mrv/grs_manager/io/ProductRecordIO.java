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
				products.add( ProductRecordIO.readLine( fileReader.nextLine() ) ); 
			
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		}
		fileReader.close();
		System.out.println(products.size());
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
	private static Product readLine(String nextLine) throws IllegalArgumentException {
		Scanner scan = new Scanner(nextLine);
		String token = "";
		Component c = new Component(Product.DEFAULT_PRODUCT_FAMILY,Product.DEFAULT_PRODUCT_DESCRIPTION,Product.DEFAULT_PRODUCT_PART_NUMBER);
		scan.useDelimiter(",");
		while(scan.hasNext()) {
			token = scan.next();
			
			try {
				
				if( c.getMiscIDNumber() == 0  && ProductRecordIO.verifyCharacters(token) && c.getMiscIDNumber() != 1) {
					
					c.setMiscIDNumber(token);
				} else if( token.contains("-") && Character.isDigit(token.charAt(0)) && Character.isDigit(token.charAt(1)) &&
						c.getPartNumber().equals(Product.DEFAULT_PRODUCT_PART_NUMBER) )  {
			
					c.setPartNumber(token);
				} else if(token.equals("NIOX") && c.getFamily().equals(Product.DEFAULT_PRODUCT_FAMILY)) { 
				
					c.setFamily(token);
				} else if (token.equals("MINO") || token.equals("VERO")) { 
					
					c.setGeneration(token);
				} else if( ProductRecordIO.attemptPriceParse(token) && c.getPrice() == 0 ) { 
					
					c.setPrice(Double.parseDouble(token));
				} else if( c.getDescription().equals(Product.DEFAULT_PRODUCT_DESCRIPTION)  ) {
					
					c.setDescription(token);
				} else if ( !c.getDescription().equals(Product.DEFAULT_PRODUCT_DESCRIPTION) && (c.getNote() == null || c.getDescription().equals("") ) ) {
					c.setNote(token);
				} else throw new IllegalArgumentException("error");
				

			} catch (IllegalArgumentException e) {
				if(e.getMessage().equals(Component.MISC_ID_ERROR)) 
					c.setMiscIDNumber(1);
				else  if (e.getMessage().equals(Component.PART_NUMBER_ERROR)) 
					c.setPartNumber(" ");
				else if (e.getMessage().equals(Component.PRODUCT_FAMILY_ERROR)) 
					c.setFamily("NIOX");
				 else throw new IllegalArgumentException(e.getMessage());
			} 
		}
		scan.close();
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
