/**
 * 
 */
package com.circa.mrv.grs_manager.io;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import com.circa.mrv.grs_manager.util.LinkedListRecursive;

/**
 * Tests OrderRecordIO
 * @author Arthur Vargas
 */
public class OrderRecordIOTest {
	/** Test order records */
	private static final String orderRecordFileName = "test-files/order-record-test/order-records";
	/** Study id 006155 */
	private static final String study006155 = "006155";
	/** Study id 145986 */
	private static final String study145986 = "145986";
	/** Research ship-to on line 5 / column 30 of order-records */
	private final String siteName = "Clinical Research of Gastonia";
	/** fax number on line 5 / column 37 of order-records */
	private final String faxNumber = "0017046712322";
	/** PO number on line 5 / column 48 of order records */
	private String po = "15003907 OD";
	
	/** Research ship-to on line 9 / column 30 of order-records */
	private final String siteNameLine9 = "Clinical Research of Charlotte";
	/** fax number on line 9 / column 37 of order-records */
	private final String faxNumberLine9 = "0017043419996";
	/** PO number on line 9 / column 48 of order records */
	private String poLine9 = "15003912 OD";
	
	
	/** Number of product titles */
	private static final int productTitleCount = 22;
	/** Number of order records */
	private static final int orderRecordCount = 7;
	/** Total number of order record columns */
	private static final int orderRecordColumns = 54;
	/** Test order record titles */
	private static final String orderRecordTitleFileName = "test-files/order-record-test/titles";

	/**
	 * Tests readOrderRecord()
	 */
	@Test
	public void testReadOrderRecord() throws FileNotFoundException,IllegalArgumentException {
		String [][] orders = new String[10][55]; 
		LinkedListRecursive<ProductTitle> ptList = new LinkedListRecursive<ProductTitle>();
		try {
			OrderRecordIO.readOrderRecord(orderRecordFileName,orders,ptList,orderRecordColumns);
		} catch (IOException ioe) {
			fail(ioe.getMessage());
			if(ioe instanceof FileNotFoundException) throw new FileNotFoundException(ioe.getMessage());
			else throw new IllegalArgumentException(ioe.getMessage());
			
		}
		for(int i = 0; i < orders.length; i++) {
			for(int j = 0; j < 55; j++) {
				System.out.println("row: " + i + "column: " + j + " " + orders[i][j]);
			}
		}
		if(!orders[1][0].equals(study006155) || !orders[1][30].equals(siteName) || !orders[1][48].equals(po) )
			fail();
		if(!orders[3][0].equals(study145986) || !orders[3][30].equals(siteNameLine9) || !orders[3][48].equals(poLine9) )
			fail();
		
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.io.OrderRecordIO#clearStringBuilder(java.lang.StringBuilder)}.
	 */
	@Test
	public void testClearStringBuilder() {
		StringBuilder sb = new StringBuilder();
		sb.append("a");
		sb.append("b");
		sb.append("c");
		OrderRecordIO.clearStringBuilder(sb);
		assertEquals(sb.length(),0);
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.io.OrderRecordIO#readOrderTitles(java.lang.String, java.lang.String[][], com.circa.mrv.grs_manager.util.LinkedListRecursive)}.
	 */
	@Test
	public void testReadOrderTitles() {
		/** These tests must use a full set of product titles */
		String [][] titles = new String[1][orderRecordColumns];
		LinkedListRecursive<ProductTitle> ptList = new LinkedListRecursive<ProductTitle>();
		try {
			OrderRecordIO.readOrderTitles(orderRecordTitleFileName, titles, ptList);
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

}
