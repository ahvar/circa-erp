/**
 * 
 */
package com.circa.mrv.grs_manager.catalog;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test for OrderRecord
 * @author Arthur Vargas
 */
public class OrderRecordTest {
	/** The filename for order records */
	private static final String orderRecordFile = "test-files/order-record-test/order-records";
	
	
	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.OrderRecord#OrderRecord()}.
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
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.OrderRecord#newOrderRecord()}.
	 */
	@Test
	public void testNewOrderRecord() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.OrderRecord#loadOrdersFromFile(java.lang.String)}.
	 */
	@Test
	public void testLoadOrdersFromFile() {
		
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.OrderRecord#loadTitlesFromFile(java.lang.String)}.
	 */
	@Test
	public void testLoadTitlesFromFile() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.OrderRecord#getRecord()}.
	 */
	@Test
	public void testGetRecord() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.OrderRecord#setRecord(java.lang.String[][])}.
	 */
	@Test
	public void testSetRecord() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.OrderRecord#getOrderById(long)}.
	 */
	@Test
	public void testGetOrderById() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.OrderRecord#getShortOrderInfo()}.
	 */
	@Test
	public void testGetShortOrderInfo() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.OrderRecord#getOrderByPOAndStudy(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetOrderByPOAndStudy() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.OrderRecord#updateOrderList()}.
	 */
	@Test
	public void testUpdateOrderList() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.OrderRecord#getLastOrder()}.
	 */
	@Test
	public void testGetLastOrder() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.OrderRecord#getCalendarFromString(java.lang.String)}.
	 */
	@Test
	public void testGetCalendarFromString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.OrderRecord#getStudyNumbers()}.
	 */
	@Test
	public void testGetStudyNumbers() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.OrderRecord#getDefaultStudyNumbers()}.
	 */
	@Test
	public void testGetDefaultStudyNumbers() {
		fail("Not yet implemented");
	}

}
