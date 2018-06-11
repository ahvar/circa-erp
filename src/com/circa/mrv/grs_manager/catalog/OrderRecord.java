/**
 * 
 */
package com.circa.mrv.grs_manager.catalog;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


import com.circa.mrv.grs_manager.io.OrderRecordIO;
import com.circa.mrv.grs_manager.io.ProductTitle;
import com.circa.mrv.grs_manager.niox.Component;

import com.circa.mrv.grs_manager.util.LinkedListRecursive;
import com.circa.mrv.grs_manager.util.LinkedStack;
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
	private LinkedListRecursive<ProductTitle> productTitlesList;
	/** A list of all Orders */
	private LinkedListRecursive<Order> orderRecordList;
	/** A list of studies */
	private ArrayList<String> studyList;
	/** A list of research sites */
	private ArrayList<String> siteList;
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
	/** Default study numbers */
	private static final String[] DEFAULT_STUDY_NUMBERS = {"006155","006156","006186","107061"};
	/** Default site numbers */
	private static final String[] DEFAULT_SITE_NUMBERS = {"2344","3456","3456","2345"};
	/** Default study stack size */
	private static final int STUDY_LIST_SIZE = 50;
	/** Default research site size */
	private static final int RESEARCH_SITE_SIZE = 600;
	/** Number of ongoing studies */
	private static final int STUDY_COUNT = 7;
	/** Open order count */
	private int open;
	

	/**
	 * Constructs the OrderRecord with a default number of row and columns, 500 and 70, respectively.
	 * Sets the last column to 0.
	 */
	public OrderRecord() {
		productTitlesList = new LinkedListRecursive<ProductTitle>();
		orderRecordList = new LinkedListRecursive<Order>();
		studyList = new ArrayList<String>(STUDY_LIST_SIZE);
		siteList = new ArrayList<String>(RESEARCH_SITE_SIZE);
		unformattedRecords = new String [800][54];
		lastCol = 0;
		first = 0;
		last = 0;
		open = 0;
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
			countOpenOrders();
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
	 * If no order is found with the correct id, null is returned.
	 * @param id the order id
	 * @return the Order to return or null if the order is not found
	 */
	public Order getOrderById(long id) {
		for(int i = 0; i < orderRecordList.size(); i++) {
			if(orderRecordList.get(i).getNumber() == id)
				return orderRecordList.get(i);
		}
		return null;
		
	}
	
	/**
	 * Returns a 2D array containing the following order information: Study, Site, City, PO Number, Contact, Date.
	 * @return array the abbreviated order information
	 * @throws NullPointerException if noPTColumns array is null
	 * @throws IOException if there is a problem reading the file
	 */
	public String[][] getShortOrderInfo() throws NullPointerException, IOException {
		//System.out.println("ENTER getShortOrderInfo" + '\n');
		//BufferedReader br = new BufferedReader(new FileReader(RECORDS_NO_PRODUCT_TITLES));
		//Scanner scan;
		//String line = br.readLine(); //blank
		//line = br.readLine(); //titles
		//String record = "";
		String[][] shortOrderRecord = new String[unformattedRecords.length][11];
		int shortOrderRow = 0;
		for(int row = 1; row < unformattedRecords.length; row++) {
			shortOrderRecord[shortOrderRow][0] = unformattedRecords[row][0]; //study
			shortOrderRecord[shortOrderRow][1] = unformattedRecords[row][1]; //site
			shortOrderRecord[shortOrderRow][2] = unformattedRecords[row][33]; //city
			shortOrderRecord[shortOrderRow][3] = unformattedRecords[row][34]; //country
			shortOrderRecord[shortOrderRow][4] = unformattedRecords[row][48]; //PO Number
			shortOrderRecord[shortOrderRow][5] = unformattedRecords[row][47]; //Note
			shortOrderRecord[shortOrderRow][6] = unformattedRecords[row][39]; //Email
			shortOrderRecord[shortOrderRow][7] = unformattedRecords[row][41]; //Date
			shortOrderRecord[shortOrderRow][8] = unformattedRecords[row][35]; //State
			shortOrderRecord[shortOrderRow][9] = unformattedRecords[row][36]; //Phone
			shortOrderRecord[shortOrderRow][10] = unformattedRecords[row][37];//Fax
			shortOrderRow++;
		}
		//br.close();
		return shortOrderRecord;
	}
	
	/**
	 * Steps through the order record list and adding each new study number to the study stack.
	 */
	public void updateStudyList() {
		Scanner scan;
		String study = null;
		if( !this.orderRecordList.isEmpty() ) {
			for(int i = 0; i < this.orderRecordList.size(); i++) {
				scan = new Scanner(this.orderRecordList.get(i).getStudy());
				if(!scan.hasNext()) break;
				study = scan.next();
				scan.close();
				if(this.studyList.isEmpty()) this.studyList.add(study);
				else {
					String s = null;
					for(int j = 0; j < studyList.size(); j++) {
						if(studyList.get(j).equals(study)) s = study;
					}
					try {
					if(s == null) studyList.add(study);
					}catch(IndexOutOfBoundsException ibe) {
						throw new  IllegalArgumentException(ibe.getMessage());
					}
						
				}
			}
		}
	}
	
	/**
	 * Steps through order record list and adds each research site number to the list.
	 */
	public void updateSiteList() {
		Scanner scan;
		String site = null;
		if( !this.orderRecordList.isEmpty() ) {
			for(int i = 0; i < this.orderRecordList.size(); i++) {
				//System.out.println(this.orderRecordList.get(i).getSite());
				scan = new Scanner(this.orderRecordList.get(i).getSite());
				if(!scan.hasNext()) break;
				site = scan.next();
				scan.close();
				if(this.siteList.isEmpty()) this.siteList.add(site);
				else {
					String s = null;
					for(int j = 0; j < siteList.size(); j++) {
						if(siteList.get(j).equals(site)) s = site;
					}
					try {
					if(s == null) siteList.add(site);
					}catch(IndexOutOfBoundsException ibe) {
						throw new  IllegalArgumentException(ibe.getMessage());
					}
						
				}
			}
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
		Order o = null;
		for(int i = 0;i < orderRecordList.size(); i++) {
			if(orderRecordList.get(i).getPo().equals(po) && orderRecordList.get(i).getStudy().equals(study))
				o = orderRecordList.get(i);
		}
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
	public int getFirst() {
		return first;
	}
	
	/**
	 * Returns last order record title which is a product title.
	 */
	public int getLast() {
		return last;
	}
	
	/**
	 * Evaluates column parameter to see if it falls within the range defined by first and last columns
	 * @param column the column of the title 
	 * @return true if the column is within the range of product titles
	 */
	public boolean isProductTitle(int column) {
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
	
	/**
	 * Beginning with the second row, this method iterates over the order record array and constructs an Order 
	 * from the data in each column of the row. The column titles are listed in the first row and within this 
	 * list exist product titles, which are a consecutive set of titles that correspond to a product that the 
	 * customer can order. In each of the following rows, integers in the product title columns indicate which 
	 * and how many products were selected. Using OrderRecord’s “first” and “last” instance variables, which 
	 * were set when the order record titles were read into the program, updateOrderList() can determine the 
	 * beginning and ending titles on the upper and lower bounds of the product title range.
	 * 
	 * @throws IlleglArgumentException if date strings cannot be parsed
	 */
	public void updateOrderList() {
		Order o = null;
		// loop for rows in the record array
		for(int row = 1; row < unformattedRecords.length; row++) {
			// rows without study numbers, site numbers, and location are skipped
			if(unformattedRecords[row][0] == null && unformattedRecords[row][1] == null && unformattedRecords[row][2] == null)
				continue;
			
			if(row == 1) {
				o = new Order(00001);
			} else {
				o = new Order(getLastOrder().getNumber() + 1 ); 
			}
			
			// loop for the product title range to see which products were ordered
			for(int col = first; col <= last; col++) {
				
				if(unformattedRecords[row][col] != null) {
					int prodTitle = 0;
					String family = null;
					String gen = null;
					String desc = null;
					while(prodTitle < productTitlesList.size()) {
						
						if(productTitlesList.get(prodTitle).getIndex() == col) {
							family = productTitlesList.get(prodTitle).getFam();
							gen = productTitlesList.get(prodTitle).getGen();
							desc = productTitlesList.get(prodTitle).getDescription();
							o.getProduct().push(new Component(family,gen,desc));
						}
						prodTitle++;
					}
					
				}
					
			}
			
			o.setStudy(unformattedRecords[row][0]);
			o.setSite(unformattedRecords[row][1]);
			o.setPo(unformattedRecords[row][48]);
			try {
				if(unformattedRecords[row][41] != null)
					o.setCreation(getCalendarFromString(unformattedRecords[row][41]));
			} catch (ParseException e) {
				throw new IllegalArgumentException(e.getMessage() + " OrderRecord.updateOrderList()");
			}
			orderRecordList.add(o);
		}
		
	}
	
	/**
	 * Returns the most recent order created from the list
	 * @return order the most recently created order
	 */
	public Order getLastOrder() {
		long id = 0;
		for(int i = 0; i < orderRecordList.size(); i++) {
			if(id < orderRecordList.get(i).getNumber())
				id = orderRecordList.get(i).getNumber();
		}
		return getOrderById(id);
		
	}
	
	/**
	 * Parses the different String formats in the order record array into Calendar objects
	 * Returns the Calendar object for the parameter
	 * @param date the date as a String
	 * @return calendar the Calendar object representing the date
	 * @throws ParseException if there is a problem parsing Date from String
	 */
	public Calendar getCalendarFromString(String date) throws ParseException {
		SimpleDateFormat sdfSlash = new SimpleDateFormat("mm/dd/yyyy");
		SimpleDateFormat sdfDash = new SimpleDateFormat("dd-MMM-yy");
		Calendar calendar = Calendar.getInstance();
		Date dat = null;
		if(date.contains("/")) {
			dat = sdfSlash.parse(date);
			calendar.setTime(dat);
		} else if (date.contains("-")) {
			dat = sdfDash.parse(date);
			calendar.setTime(dat);
		}
		return calendar;
	}
	
	/**
	 * Returns an array of current study numbers.
	 * @return the current study numbers
	 * @throws IllegalArgumentException if records are null or empty or if the study number array is null or empty.
	 */
	public String[] getStudyNumbers() {
		String[] studies = null;
		try {
		  ArrayList<String> studyList = new ArrayList<String>();
		  studyList.add(unformattedRecords[1][0]);
		  String study = unformattedRecords[1][0];
		  for(int row = 2; row < unformattedRecords.length; row++) {
			  if(!study.equals(unformattedRecords[row][0])) {
				  studyList.add(unformattedRecords[row][0]);
				  study = unformattedRecords[row][0];
			  }
		  }
		  studies = new String[studyList.size()];
		  for(int i = 0; i < studies.length; i++) {
			  studies[i] = studyList.get(i);
		  }
		} catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
		if( studies.length == 0 || studies == null ) throw new IllegalArgumentException();
		return studies;
 	}

	/**
	 * @return the defaultStudyNumbers
	 */
	public static String[] getDefaultStudyNumbers() {
		return DEFAULT_STUDY_NUMBERS;
	}
	
	/**
	 * @return the defaultStudyNumbers
	 */
	public static String[] getDefaultStiteNumbers() {
		return DEFAULT_SITE_NUMBERS;
	}

	/**
	 * Returns the list of order records.
	 * @return the orderRecordList
	 */
	public LinkedListRecursive<Order> getOrderRecordList() {
		return orderRecordList;
	}

	/**
	 * Sets the order record list
	 * @param orderRecordList the orderRecordList to set
	 */
	public void setOrderRecordList(LinkedListRecursive<Order> orderRecordList) {
		this.orderRecordList = orderRecordList;
	}
	
	/**
	 * Returns an array of the open orders
	 */
	public String[][] getOpenOrderArray() {
		String [][] open = new String[this.open][7];
		for(int i = 0; i < orderRecordList.size(); i++) {
			if(orderRecordList.get(i).getStatus().equals(Order.getOpen())) {
				open[i][0] = orderRecordList.get(i).getPo();
				open[i][1] = orderRecordList.get(i).getStudy();
				open[i][2] = orderRecordList.get(i).getSite();
				open[i][3] = orderRecordList.get(i).getCity();
				open[i][4] = orderRecordList.get(i).getState();
				open[i][5] = orderRecordList.get(i).getZip();
				open[i][6] = orderRecordList.get(i).getStatus();
			}
		}
		return open;
	}
	
	/**
	 * Counts open orders in the order record list.
	 */
	public void countOpenOrders() {
		for(int i = 0; i < orderRecordList.size(); i++) {
			if(orderRecordList.get(i).getStatus().equals(Order.getOpen())) open++;
		}
	}
	
	/**
	 * Returns the current number of open orders.
	 * @return open the current number of open orders
	 */
	public int getOpenOrderCount() {
		return open;
	}
	
	/**
	 * Return list of product titles
	 * @return list list of product titles
	 */
	public LinkedListRecursive<ProductTitle> getProductTitles() {
		return productTitlesList;
	}
	
	/**
	 * Returns an array-based list with study numbers
	 * @return studyList the list of studies
	 */
	public ArrayList<String> getStudyList() {
		return studyList;
	}
	
	/**
	 * Returns an array-based list with site numbers
	 * @return studyList the list of sites
	 */
	public ArrayList<String> getSiteList() {
		return siteList;
	}
	
			
}
