/**
 * 
 */
package com.circa.mrv.grs_manager.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.circa.mrv.grs_manager.io.OrderRecordIO;
import com.circa.mrv.grs_manager.io.ProductTitle;
import com.circa.mrv.grs_manager.niox.Product;
import com.circa.mrv.grs_manager.util.LinkedListRecursive;
import com.circa.mrv.grs_manager.document.Order;

/**
 * @author ahvar
 *
 */
public class OrderRecord {
	/** The order record array */
	private String [][] records;
	private LinkedListRecursive<ProductTitle> productTitles = new LinkedListRecursive<ProductTitle>();
	private int lastCol;

	/**
	 * 
	 */
	public OrderRecord() {
		records = new String [500][60];
		lastCol = 0;
	}
	
	/**
	 * Loads an input file of orders records. If the file is unable to be found an IllegalArgumentException is thrown.
	 * 
	 * @param filename the name of the file
	 * @throws IllegalArgumentException if file cannot be found
	 */
	public void loadOrdersFromFile(String filename) throws IllegalArgumentException {
		try {
			OrderRecordIO.readOrderRecord(filename,records,productTitles,lastCol);
		
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
			OrderRecordIO.readOrderTitles(filename, this.records, productTitles, lastCol);
		} catch(IOException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * @return the record
	 */
	public String [][] getRecord() {
		return records;
	}

	/**
	 * @param record the record to set
	 */
	public void setRecord(String [][] record) {
		this.records = record;
	}


	

}
