/**
 * 
 */
package com.circa.mrv.grs_manager.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

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
	private String allProducts = "test-files/product-record-test/all-products";
	/** Test file for four niox products */
	private String fourProducts = "test-files/product-record-test/four-products";
	/** Test file for one product */
	private String oneProduct = "test-files/product-record-test/one-product";
	/** Test file for one product, missing miscID */
	private String oneProduct1 = "test-files/product-record-test/one-product-1";
	/** Test file for all niox products */
	private String nioxProducts = "test-files/niox_products";
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
			fail(e.getMessage());
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
		
		String line2 = "null" + "," + partNumber + "," + family + "," + generationVero + "," + descriptionDevice + "," + price + "," + notes;
		try { 
			Component c1 = (Component) ProductRecordIO.readLine(line2);
			if(c1 == null) fail();
			if(c1.getMiscIDNumber() != 0 || !c1.getPartNumber().equals(partNumber) ||
					!c1.getFamily().equals(family) || !c1.getGeneration().equals(generationVero) ||
					!c1.getDescription().equals(descriptionDevice) || c1.getPrice() != price ||
					!c1.getNote().equals(notes)) 
				fail();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (NullPointerException npe) {
			throw new NullPointerException(npe.getMessage());
		}
		
		String line3 = miscId + "," + partNumber + "," + family + "," + generationVero + "," + descriptionDevice + "," + "null" + "," + notes;
		try { 
			Component c2 = (Component) ProductRecordIO.readLine(line3);
			if(c2.getMiscIDNumber() != Long.parseLong(miscId) || !c2.getPartNumber().equals(partNumber) ||
					!c2.getFamily().equals(family) || !c2.getGeneration().equals(generationVero) ||
					!c2.getDescription().equals(descriptionDevice) || c2.getPrice() != 0 ||
					!c2.getNote().equals(notes)) 
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
