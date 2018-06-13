/**
 * 
 */
package com.circa.mrv.grs_manager.manager;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests GRSManager
 * @author Arthur Vargas
 */
public class GRSManagerTest {
	/** filename for order record titles */
	private final String titles = "test-files/grs-manager-test/titles";
	/** filename for order records */
	private final String orders = "test-files/grs-manager-test/all-records";
	/** filename for product records */
	private final String products = "test-files/grs-manager-test/products";
	/**
	 * Tests login()
	 */
	@Test
	public void testLogin() {
		GRSManager gm = GRSManager.getInstance();
		assertEquals(gm.getNioxCatalog().allProducts(),0);
		assertEquals(gm.getOrderRecord().getOrderRecordList().size(),0);
		assertEquals(gm.getCurrentUser(),null);
		assertEquals(gm.getCompanyDirectory().getCompanyList().size(),0);
		
		gm.getOrderRecord().loadTitlesFromFile(titles);
		assertEquals(gm.getOrderRecord().getProductTitles().size(),22);
		gm.getOrderRecord().loadOrdersFromFile(orders);
		assertEquals(gm.getOrderRecord().getOrderRecordList().size(),541);
		gm.getNioxCatalog().loadProductsFromFile(products);
		assertEquals(gm.getNioxCatalog().getProductPartNumbers().length,22);
		
		
		
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.manager.GRSManager#logout()}.
	 */
	@Test
	public void testLogout() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.manager.GRSManager#getCurrentUser()}.
	 */
	@Test
	public void testGetCurrentUser() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.manager.GRSManager#getCompany()}.
	 */
	@Test
	public void testGetCompany() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.manager.GRSManager#clearData()}.
	 */
	@Test
	public void testClearData() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.manager.GRSManager#addOrderToSchedule(com.circa.mrv.grs_manager.document.Order)}.
	 */
	@Test
	public void testAddOrderToSchedule() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.manager.GRSManager#dropOrderFromSchedule(com.circa.mrv.grs_manager.document.Order)}.
	 */
	@Test
	public void testDropOrderFromSchedule() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.manager.GRSManager#resetSchedule()}.
	 */
	@Test
	public void testResetSchedule() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.manager.GRSManager#addFacultyToCourse(com.circa.mrv.grs_manager.document.Order, com.circa.mrv.grs_manager.user.Employee)}.
	 */
	@Test
	public void testAddFacultyToCourse() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.manager.GRSManager#addEmployeeToCompany(com.circa.mrv.grs_manager.directory.Company, com.circa.mrv.grs_manager.user.Employee, int)}.
	 */
	@Test
	public void testAddEmployeeToCompany() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.manager.GRSManager#removeOrderFromSchedule(com.circa.mrv.grs_manager.document.Order, com.circa.mrv.grs_manager.user.Employee)}.
	 */
	@Test
	public void testRemoveOrderFromSchedule() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.manager.GRSManager#removeEmployeeFromCompany(com.circa.mrv.grs_manager.directory.Company, com.circa.mrv.grs_manager.user.Employee)}.
	 */
	@Test
	public void testRemoveEmployeeFromCompany() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.manager.GRSManager#resetFacultySchedule(com.circa.mrv.grs_manager.user.Employee)}.
	 */
	@Test
	public void testResetFacultySchedule() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.manager.GRSManager#getCompanyDirectory()}.
	 */
	@Test
	public void testGetCompanyDirectory() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.manager.GRSManager#getUserDirectory()}.
	 */
	@Test
	public void testGetUserDirectory() {
		fail("Not yet implemented");
	}

}
