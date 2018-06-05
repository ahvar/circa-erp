/**
 * 
 */
package com.circa.mrv.grs_manager.catalog;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

/**
 * Test for OrderRecord
 * @author Arthur Vargas
 */
public class OrderRecordTest {
	/** The filename for order records */
	private static final String orderRecordFile = "test-files/order-record-test/order-records";
	/** The filename for order record titles */
	private static final String orderRecordTitles = "test-files/order-record-test/titles";
	/** Order count */
	private int orderCount = 7;
	/** ProductTitle count */
	private int ptCount = 22;
	/** Column count */
	private int columnCount = 52;
	
	
	/**
	 * Tests OrderRecord constructor
	 */
	@Test
	public void testOrderRecord() {
		// init empty OrderRecord
		OrderRecord or = new OrderRecord();
		assertEquals(or.getOrderRecordList().size(),0);
		assertEquals(or.getFirst(),0);
		assertEquals(or.getLast(),0);
		assertEquals(or.getOpenOrderCount(),0);
	}

	/**
	 * Tests loadOrdersFromFile() method. Loads
	 */
	@Test
	public void testLoadOrdersFromFile() {
		
		OrderRecord or = new OrderRecord();
		or.loadTitlesFromFile(orderRecordTitles);
		or.loadOrdersFromFile(orderRecordFile);
		assertEquals(or.getFirst(),3); // the first column that is a product title
		assertEquals(or.getLast(),24); //  the last column that is a product title
		for(int i = 3; i <= 24; i++) { // if a column title is within this range, it is a produc title
			if(!or.isProductTitle(i))
				fail();
		}
		assertEquals(or.getProductTitles().size(), ptCount);
		try {
		assertEquals(or.getShortOrderInfo().length, 800);
		}catch(IOException ioe ) {
			throw new IllegalArgumentException(ioe.getMessage());
		}catch(NullPointerException npe ) {
			throw new NullPointerException(npe.getMessage());
		}
		assertEquals(or.getOrderRecordList().size(),0);
		or.updateOrderList();
		assertEquals(or.getOrderRecordList().size(),7);
		
	}

	/**
	 * Tests loadTitlesFromFile
	 */
	@Test
	public void testLoadTitlesFromFile() {
		OrderRecord or = new OrderRecord();
		or.loadTitlesFromFile(orderRecordTitles);
		//String product = null;
		//for(int i = 0; i < or.getProductTitles().size(); i++) {
			//product = or.getProductTitles().get(i).getFam() + " " + or.getProductTitles().get(i).getGen() + " " + 
				//	or.getProductTitles().get(i).getDescription();
			//System.out.println(product);
		//}
		assertEquals(or.getProductTitles().size(),ptCount);
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.OrderRecord#getOrderById(long)}.
	 */
	@Test
	public void testGetOrderById() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.OrderRecord#getShortOrderInfo()}.
	 */
	@Test
	public void testGetShortOrderInfo() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.OrderRecord#getOrderByPOAndStudy(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetOrderByPOAndStudy() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.OrderRecord#updateOrderList()}.
	 */
	@Test
	public void testUpdateOrderList() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.OrderRecord#getLastOrder()}.
	 */
	@Test
	public void testGetLastOrder() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.OrderRecord#getCalendarFromString(java.lang.String)}.
	 */
	@Test
	public void testGetCalendarFromString() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.OrderRecord#getStudyNumbers()}.
	 */
	@Test
	public void testGetStudyNumbers() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.OrderRecord#getDefaultStudyNumbers()}.
	 */
	@Test
	public void testGetDefaultStudyNumbers() {
		//fail("Not yet implemented");
	}

}
