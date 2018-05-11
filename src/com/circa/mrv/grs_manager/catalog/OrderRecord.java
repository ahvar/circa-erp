/**
 * 
 */
package com.circa.mrv.grs_manager.catalog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

import com.circa.mrv.grs_manager.io.OrderRecordIO;
import com.circa.mrv.grs_manager.io.ProductTitle;
import com.circa.mrv.grs_manager.niox.Product;
import com.circa.mrv.grs_manager.util.LinkedListRecursive;
import com.circa.mrv.grs_manager.document.Order;

/**
 * Stores and manages all order data
 * 
 * @author Arthur Vargas
 */
public class OrderRecord {
	/** The order record array */
	private String [][] unformattedRecords;
	/** An order record array with no product title columns */
	private String [][] noPTColumns;
	/** A list of order record titles which are products */
	private LinkedListRecursive<ProductTitle> productTitlesList = new LinkedListRecursive<ProductTitle>();
	/** The last column containing order data*/
	private int lastCol;
	/** The first column that is a product title */
	private int first;
	/** The last column that is a product title */
	private int last;
	/** PrintStream to output data to file */
	private PrintStream fileWriter;
	/** The order records filename */
	private static final String RECORDS_NO_PRODUCT_TITLES = "test-files/records_no_product_titles.txt";
	/** The order records title filename */
	private static final String ORDER_RECORD_TITLES = "test-files/order_record_titles.txt";
	/** Name for the output file of the product titles */
	private static final String PRODUCT_TITLES = "test-files/product_titles.txt";
	/** Name of the output file for all order record data; includes product titles */
	private static final String RECORDS_PRODUCT_TITLES = "test-files/records_product_titles.txt";
	

	/**
	 * Constructs the OrderRecord with a default number of row and columns, 500 and 70, respectively.
	 * Sets the last column to 0.
	 */
	public OrderRecord() {
		unformattedRecords = new String [500][70];
		lastCol = 0;
		first = 0;
		last = 0;
	}
	
	/**
	 * Creates an empty order record.
	 */
	public void newOrderRecord() {
		new OrderRecord();
	}
	
	/**
	 * Loads an input file of orders records. If the file is unable to be found an IllegalArgumentException is thrown.
	 * 
	 * @param filename the name of the file
	 * @throws IllegalArgumentException if file cannot be found
	 */
	public void loadOrdersFromFile(String filename) throws IllegalArgumentException {
		try {
			OrderRecordIO.readOrderRecord(filename,unformattedRecords,productTitlesList,lastCol);
			writeUnFormattedToFile(RECORDS_NO_PRODUCT_TITLES);
			printAllUnformattedToFile(RECORDS_PRODUCT_TITLES);
			//removePTColumns();
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to read file " + filename);
		}
	
	}
	
	/**
	 * Load the file containing order record titles.
	 * 
	 * @param filename the filename
	 * @throws IllegalArgumentException if there is a problem reading the file
	 */
	public void loadTitlesFromFile(String filename) throws IllegalArgumentException {
		
		try {
			lastCol = OrderRecordIO.readOrderTitles(filename, unformattedRecords, productTitlesList);
			setProductTitleRange();
			writeTitlesToFile(ORDER_RECORD_TITLES);
			writeProductTitlesToFile(PRODUCT_TITLES);
		} catch(IOException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * Returns 2D String array representation of order records
	 * @return the record
	 */
	public String [][] getRecord() {
		String [][] orderRecords = new String[500][70];
		for(int row = 1; row < unformattedRecords.length; row++) {
			for(int col = 0; col < lastCol; col++) {
				if(!isProductTitle(col) && unformattedRecords[row][col] != null) {
					orderRecords[row][col] = unformattedRecords[row][col];
				}
			}
		}
		return orderRecords;
	}

	/**
	 * Sets the 
	 * @param record the record to set
	 */
	public void setRecord(String [][] record) {
		this.unformattedRecords = record;
	}
	
	/**
	 * Returns the Order whose ID matches the parameter id
	 * @param id the order id
	 * @return the Order to return
	 */
	public Order getOrderById(String id) {
		Order o = new Order();
		return o;
		
	}
	
	/**
	 * Returns a 2D array containing the following order information: Study, Site, City, PO Number, Contact, Date.
	 * @return array the abbreviated order information
	 * @throws NullPointerException if noPTColumns array is null
	 * @throws IOException if there is a problem reading the file
	 */
	public String[][] getShortOrderInfo() throws NullPointerException, IOException {
		System.out.println("ENTER getShortOrderInfo" + '\n');
		//BufferedReader br = new BufferedReader(new FileReader(RECORDS_NO_PRODUCT_TITLES));
		//Scanner scan;
		//String line = br.readLine(); //blank
		//line = br.readLine(); //titles
		//String record = "";
		String[][] shortOrderRecord = new String[unformattedRecords.length][11];
		for(int row = 1; row < unformattedRecords.length; row++) {
			shortOrderRecord[row][0] = unformattedRecords[row][0];
			shortOrderRecord[row][1] = unformattedRecords[row][1];
			shortOrderRecord[row][2] = unformattedRecords[row][33];
			shortOrderRecord[row][3] = unformattedRecords[row][34];
			shortOrderRecord[row][4] = unformattedRecords[row][48];
			shortOrderRecord[row][5] = unformattedRecords[row][47];
			shortOrderRecord[row][6] = unformattedRecords[row][39];
			shortOrderRecord[row][7] = unformattedRecords[row][41];
			shortOrderRecord[row][8] = unformattedRecords[row][35];
			shortOrderRecord[row][9] = unformattedRecords[row][36];
			shortOrderRecord[row][10] = unformattedRecords[row][37];
			
		}
		//br.close();
		return shortOrderRecord;
	}
	
	/**
	 * Reads data from the unformatted order record 2D array into another 2D array, eliminating columns that were 
	 * identified as containing product information.
	 * 
	 * @return array the abbreviated order information
	 */
	private void removePTColumns() {
		try {
			int noPTRow = 0;
			int noPTCol = 0;
			noPTColumns = new String[unformattedRecords.length][lastCol - productTitlesList.size()];
			for(int row = 0; row < unformattedRecords.length; row++) {
				for(int col = 0; col < lastCol; col++) {
					if( isProductTitle(col) ) continue;
					if ( unformattedRecords[row][col] == null ) {
						noPTColumns[noPTRow][noPTCol] = "empty";
					} else { 
						noPTColumns[noPTRow][noPTCol] = unformattedRecords[row][col];
						noPTCol++;
						//System.out.println("UFR Column: " + col + '\t' + unformattedRecords[noPTRow][noPTCol] + '\n' + 
								//"no PT Column: " + noPTCol + '\t' + noPTColumns[noPTRow][noPTCol]);
					}
				}
				noPTRow++;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("removePTColumns() attempted to step outside the array");
		}
		
	}
	
	/**
	 * Returns the order whose purchase order and study match the parameters
	 * for purchase order and study.
	 * @param po the purchase order
	 * @param study the study
	 * @return the order to return
	 */
	public Order getOrderByPOAndStudy(String po, String study) {
		Order o = new Order();
		return o;
	}
	
	/**
	 * Determines which columns are product titles and by finding the max and min columns to establish and product 
	 * title range. Iterates through the list of product titles and evaluates the index. 
	 * Sets the minProductTitle and maxProductTitle instance variables.
	 */
	private void setProductTitleRange() {
		for(int i = 0; i < productTitlesList.size(); i++) {
			int titleIdx = productTitlesList.get(i).getIndex();
			if( first == last ) {
				first = titleIdx;
				last = titleIdx + 1;
			} else if (titleIdx < first) { 
				first = titleIdx;
			} else if (last < titleIdx)
				last = titleIdx;
		}
		
	}
	
	/**
	 * Returns first order record title which is a product title.
	 */
	private int getFirst() {
		return first;
	}
	
	/**
	 * Returns last order record title which is a product title.
	 */
	private int getLast() {
		return last;
	}
	
	/**
	 * Evaluates column parameter to see if it falls within the range defined by first and last columns
	 * @param column the column of the title 
	 * @return true if the column is within the range of product titles
	 */
	private boolean isProductTitle(int column) {
		if( first <= column && column <= last) 
			return true;
		return false;
	}
	
	/**
	 * Initializes the PrintWriter instance variable with a file to write output to
	 * @param output the filename 
	 * @throws IOException if there is a problem writing to the output file
	 */
	private void writeUnFormattedToFile(String output) throws IOException {
		fileWriter = new PrintStream(new File(output));
		for(int row = 0; row < unformattedRecords.length; row++) {
			fileWriter.println();
			for(int col = 0; col < lastCol; col++) {
				if(isProductTitle(col)) continue;
				if(unformattedRecords[row][col] == null)
					fileWriter.print("no record" + ",");
				else 
					fileWriter.print(unformattedRecords[row][col] + ",");
			}
		}
	}

	/**
	 * Initializes the PrintWriter instance variable with a file to write order record titles.
	 * Writes the order record titles to the file specified by the filename
	 * @param output the filename 
	 * @throws IOException if there is a problem writing to the output file
	 */
	private void writeTitlesToFile(String output) throws IOException {
		fileWriter = new PrintStream(output);
		for(int col = 0; col < lastCol; col++) {
			if(!isProductTitle(col)) {
				fileWriter.print(unformattedRecords[0][col] + " ");
			}
		}
		
	}
	
	/**
	 * Writes the column titles that have been identified to be products along with their index in the array 
	 * title row. throws an IOException if there is a problem writing to the file.
	 * @throws IOException
	 */
	private void writeProductTitlesToFile(String productTitles) throws IOException {
		fileWriter = new PrintStream(productTitles);
		fileWriter.println("Product Title Min: " + first + '\t' + "Product Title Max: " + last);
		for(int i = 0; i < this.productTitlesList.size(); i++) {
			fileWriter.println(this.productTitlesList.get(i).getFam() + " " + this.productTitlesList.get(i).getGen() + 
					" " + this.productTitlesList.get(i).getDescription() + " index: " + this.productTitlesList.get(i).getIndex());
		}
	}
	
	/** 
	 * Prints all data in the unformatted records array to the output file.
	 */
	private void printAllUnformattedToFile(String output) throws IOException {
		fileWriter = new PrintStream(output);
		for(int row = 0; row < unformattedRecords.length; row++) {
			for(int col = 0; col < lastCol; col++) 
				fileWriter.print(unformattedRecords[row][col] + ",");
			fileWriter.print('\n');
		}
	}

}
