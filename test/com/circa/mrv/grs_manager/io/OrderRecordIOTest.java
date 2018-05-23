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
	private static final String orderRecordFileName = "test-files/order-record-test/order-records.txt";
	/** Study id 006155 */
	private static final String study0011655 = "0011655";
	/** Study id 145986 */
	private static final String study145986 = "145986";
	/** Number of product titles */
	private static final int productTitleCount = 22;
	/** Number of order records */
	private static final int orderRecordCount = 7;
	/** Total number of order record columns */
	private static final int orderRecordColumns = 53;
	/** Test order record titles */
	private static final String orderRecordTitleFileName = "test-files/order-record-test/titles.txt";

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.io.OrderRecordIO#readOrderRecord(java.lang.String, java.lang.String[][], com.circa.mrv.grs_manager.util.LinkedListRecursive, int)}.
	 */
	@Test
	public void testReadOrderRecord() throws FileNotFoundException,IllegalArgumentException {
		String [][] orders = new String[7][53]; 
		LinkedListRecursive<ProductTitle> ptList = new LinkedListRecursive<ProductTitle>();
		try {
			OrderRecordIO.readOrderRecord(orderRecordFileName,orders,ptList,orderRecordColumns);
		} catch (IOException ioe) {
			fail();
			if(ioe instanceof FileNotFoundException) throw new FileNotFoundException(ioe.getMessage());
			else throw new IllegalArgumentException(ioe.getMessage());
			
		}
		assertEquals(ptList.size(),productTitleCount);
		assertEquals(orders.length,orderRecordCount);
		
		for(int i = 0; i < orders.length;i++) {
			if(orders[i][0] != study0011655 && orders[i][0] != study145986) fail();
			if(orders[i][1] == null || orders[i][2] == null) fail();
		}
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
			fail();
		}
	}

}
