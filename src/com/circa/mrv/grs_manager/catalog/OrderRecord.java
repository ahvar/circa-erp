/**
 * 
 */
package com.circa.mrv.grs_manager.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.circa.mrv.grs_manager.io.OrderRecordIO;
import com.circa.mrv.grs_manager.niox.Product;
import com.circa.mrv.grs_manager.util.LinkedListRecursive;
import com.circa.mrv.grs_manager.document.Order;

/**
 * @author ahvar
 *
 */
public class OrderRecord {
	/** The record for orders */
	private LinkedListRecursive<Order> record;
	/** The titles for a set of orders */
	

	/**
	 * 
	 */
	public OrderRecord() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Loads an input file of orders records. If the file is unable to be found an IllegalArgumentException is thrown.
	 * 
	 * @param filename the name of the file
	 * @throws IllegalArgumentException if file cannot be found
	 */
	public void loadOrdersFromFile(String filename) throws IllegalArgumentException {
		try {
			record = OrderRecordIO.readOrderRecord(filename);
		
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to read file " + filename);
		}
	}
	
	public void loadTitlesFromFile(String filename) {
		
	}

}
