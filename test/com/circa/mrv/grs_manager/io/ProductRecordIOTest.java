/**
 * 
 */
package com.circa.mrv.grs_manager.io;

import static org.junit.Assert.*;
import java.io.IOException;

import com.circa.mrv.grs_manager.util.*;
import com.circa.mrv.grs_manager.niox.*;

import org.junit.Test;

/**
 * Tests ProductRecordIO
 * 
 * @author Arthur Vargas
 */
public class ProductRecordIOTest {
	/** Test file for all niox products */
	private String allProducts = "test-files/product-record-test/all-products.txt";
	/** Test file for four niox products */
	private String fourProducts = "test-files/product-record-test/four-products.txt";
	/** List of Products */
	LinkedListRecursive<Product> list;
	/** misc id number */
	private String miscId = "992040";
	/** part number */
	private String partNumber = "12-1200";
	/** product family */
	private String family = "NIOX";
	/** product generation mino */
	private String generationVero = "VERO";
	/** product generation vero */
	private String generationMino = "MINO";
	/** product description */
	private String descriptionDevice = "device";
	/** product description breathing handle */
	private String descriptionBreathingHandle = "breathing handle";
	/** product price */
	private double price = 25.00;
	/** product notes */
	private String notes = "these are the notes";

	/**
	 * Tests ProductRecordIO.readProductRecords().
	 */
	@Test
	public void testReadProductRecords() {
		try {
			list = ProductRecordIO.readProductRecords(fourProducts);
		} catch (IOException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		
		if (list.isEmpty()) fail();

	}
	
	/**
	 * Tests ProductRecordIO.readLine()
	 */
	@Test
	public void testReadLine() {
		String line = miscId + "," + partNumber + "," + family + "," + generationVero + "," + descriptionDevice + "," + price + "," + notes;
		try { 
			Component c = (Component) ProductRecordIO.readLine(line);
			if(c.getMiscIDNumber() != Long.parseLong(miscId) || !c.getPartNumber().equals(partNumber) ||
					!c.getFamily().equals(family) || !c.getGeneration().equals(generationVero) ||
					!c.getDescription().equals(descriptionDevice) || c.getPrice() != price ||
					!c.getNote().equals(notes)) 
				fail();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (NullPointerException npe) {
			throw new NullPointerException(npe.getMessage());
		}

	}

	/**
	 * Tests ProductRecordIO.writeProductRecords().
	 */
	@Test
	public void testWriteProductRecords() {
		//fail("Not yet implemented");
	}

}
