/**
 * 
 */
package com.circa.mrv.grs_manager.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.circa.mrv.grs_manager.catalog.OrderRecord;
import com.circa.mrv.grs_manager.document.Order;

/**
 * @author Arthur Vargas
 */
public class LinkedListRecursiveTest {
	/** file name for order records */
	private String orderRecordFile = "test-files/order-record-test/order-record-all";
	/** filename for order record titles */
	private String orderRecordTitle = "test-file/order-record-test/titles";
	/** study 107061 */
	private final String study = "107061";
	/** study 106016 */
	private final String study1 = "106016";
	/** study 12345 */
	private final String study2 = "12345";
	/** site 2345 */
	private final String site = "2345";
	/** site 3456 */
	private final String site1 = "3456";
	/** site 5678 */
	private final String site2 = "5678";
 	/** po number */
	private final String po = "1800456";
	/** po number */
	private final String po1 = "1800567";
	/** po number */
	private final String po2 = "1800789";

	/**
	 * Tests LinkedListRecursive constructor
	 */
	@Test
	public void testLinkedListRecursive() {
		LinkedListRecursive<String> sList = new LinkedListRecursive<String>();
		try {
			sList.add(study);
			sList.add(study1);
			sList.add(study2);
			assertEquals(sList.get(0),study);
			assertEquals(sList.get(1),study1);
			assertEquals(sList.get(2),study2);
			assertEquals(sList.size(),3);
			// try to add duplicate
			sList.add(study); 
			fail();
		} catch (IllegalArgumentException iae) {
			// success
		}
		
		LinkedListRecursive<Order> list = new LinkedListRecursive<Order>();
		Order o = new Order(00001);
		o.setStudy(study);
		o.setSite(site);
		o.setPo(po);
		Order o1 = new Order(00002);
		o1.setStudy(study1);
		o1.setSite(site1);
		o1.setPo(po1);
		Order o2 = new Order(00003);
		o1.setStudy(study2);
		o1.setSite(site2);
		o1.setPo(po2);
	
		
		try {
			list.add(o);
			list.add(o1);
			list.add(o2);
			assertEquals(list.get(0),o);
			assertEquals(list.get(1),o1);
			assertEquals(list.get(2),o2);
			list.add(o);
			fail();
		} catch (IllegalArgumentException iae) {
			// success
		}
		
		// order has same study, site, and po. It has a different order number, so expect addition to complete
		// without exception
		Order o3 = new Order(00004);
		o3.setStudy(study);
		o3.setSite(site);
		o3.setPo(po);
		try{
			list.add(o3);
		} catch (IllegalArgumentException iae) {
			fail();
		}
		
	}

	/**
	 * Tests isEmpty()
	 */
	@Test
	public void testIsEmpty() {
		Order o = new Order(00001);
		o.setStudy(study);
		o.setSite(site);
		o.setPo(po);
		LinkedListRecursive<Order> list = new LinkedListRecursive<Order>();
		if(!list.isEmpty()) fail();
		list.add(o);
		assertFalse(list.isEmpty());
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.util.LinkedListRecursive#size()}.
	 */
	@Test
	public void testSize() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.util.LinkedListRecursive#add(java.lang.Object)}.
	 */
	@Test
	public void testAddE() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.util.LinkedListRecursive#add(int, java.lang.Object)}.
	 */
	@Test
	public void testAddIntE() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.util.LinkedListRecursive#get(int)}.
	 */
	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.util.LinkedListRecursive#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveE() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.util.LinkedListRecursive#remove(int)}.
	 */
	@Test
	public void testRemoveInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.util.LinkedListRecursive#set(int, java.lang.Object)}.
	 */
	@Test
	public void testSet() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.util.LinkedListRecursive#contains(java.lang.Object)}.
	 */
	@Test
	public void testContains() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.util.LinkedListRecursive#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

}
