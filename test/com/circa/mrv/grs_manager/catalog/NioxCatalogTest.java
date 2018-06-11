/**
 * 
 */
package com.circa.mrv.grs_manager.catalog;

import static org.junit.Assert.*;

import org.junit.Test;

import com.circa.mrv.grs_manager.niox.Component;

/**
 * Tests NioxCatalog
 * @author Arthur Vargas
 */
public class NioxCatalogTest {
	/** the text file with all product records */
	private final String allProducts = "test-files/product-record-test/all-products";
	/** the number of products in the file */
	private final int productCount = 22;
	/**
	 * Tests NioxCatalog constructor
	 */
	@Test
	public void testNioxCatalog() {
		NioxCatalog nc = new NioxCatalog();
		if(nc.allProducts() != 0) fail();
	}

	/**
	 * Tests loadProductsFromFile()
	 */
	@Test
	public void testLoadProductsFromFile() {
		NioxCatalog nc = new NioxCatalog();
		nc.loadProductsFromFile(allProducts);
		if(nc.allProducts() != productCount) fail();
		nc.newNioxCatalog();
		if(nc.allProducts() != 0) fail();
	}

	/**
	 * Test methods addProductToCatalog.
	 */
	@Test
	public void testAddProductToCatalog() {
		Component c = new Component("992040","12-1200","NIOX","VERO","device","1600.00","(Device handle handle cap power adapter power cord battery manual)");
		Component c2 = new Component("841630","xxxxx","NIOX","VERO","Boveda Bag for Hardcase","20.00","xxxx");
		NioxCatalog nc = new NioxCatalog();
		nc.addProductToCatalog(c);
		nc.addProductToCatalog(c2);
		if(nc.allProducts() != 2) fail();
		assertEquals(nc.getProductFromCatalog("NIOX", "device", "12-1200").getPartNumber(),"12-1200");
		assertEquals(nc.getProductFromCatalog("NIOX", "Boveda Bag for Hardcase", "xxxxx").getPartNumber(),"xxxxx");
		assertEquals(nc.getProductFromCatalog("NIOX", "12-1200").getFamily(),"NIOX");
		assertEquals(nc.getProductFromCatalog("NIOX", "xxxxx").getDescription(),"Boveda Bag for Hardcase");
		Component c3 = new Component("992048","12-1250","NIOX","VERO","Battery","45.00","xxxx");
		Component c4 = new Component("707171","12-1810-US","NIOX","VERO","test kit 100","1350.00","(1 sensor for 100 tests 140 filters)");
		nc.addProductToCatalog(c3);
		nc.addProductToCatalog(c4);
		if(nc.allProducts() != 4) fail();
		assertEquals(nc.getProductFromCatalog("NIOX", "12-1250").getPartNumber(),"12-1250");
		assertEquals(nc.getProductFromCatalog("NIOX", "test kit 100","12-1810-US").getDescription(),"test kit 100");
	}


	/**
	 * Tests removeProductFromCatalog
	 */
	@Test
	public void testRemoveProductFromCatalog() {
		Component c = new Component("992040","12-1200","NIOX","VERO","device","1600.00","(Device handle handle cap power adapter power cord battery manual)");
		Component c2 = new Component("841630","xxxxx","NIOX","VERO","Boveda Bag for Hardcase","20.00","xxxx");
		Component c3 = new Component("992048","12-1250","NIOX","VERO","Battery","45.00","xxxx");
		Component c4 = new Component("707171","12-1810-US","NIOX","VERO","test kit 100","1350.00","(1 sensor for 100 tests 140 filters)");
		NioxCatalog nc = new NioxCatalog();
		nc.addProductToCatalog(c);
		nc.addProductToCatalog(c2);
		nc.addProductToCatalog(c3);
		nc.addProductToCatalog(c4);
		nc.removeProductFromCatalog("NIOX", "device", "12-1200");
		if(nc.allProducts() != 3) fail();
		
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.NioxCatalog#catalogContainsProduct(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testCatalogContainsProduct() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.NioxCatalog#getNioxCatalog()}.
	 */
	@Test
	public void testGetNioxCatalog() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.NioxCatalog#saveProductCatalog(java.lang.String)}.
	 */
	@Test
	public void testSaveProductCatalog() {
		//fail("Not yet implemented");
	}

	/**
	 * Test for getProductPartNumbers
	 */
	@Test
	public void testGetProductPartNumbers() {
		Component c = new Component("992040","12-1200","NIOX","VERO","device","1600.00","(Device handle handle cap power adapter power cord battery manual)");
		Component c2 = new Component("841630","xxxxx","NIOX","VERO","Boveda Bag for Hardcase","20.00","xxxx");
		Component c3 = new Component("992048","12-1250","NIOX","VERO","Battery","45.00","xxxx");
		Component c4 = new Component("707171","12-1810-US","NIOX","VERO","test kit 100","1350.00","(1 sensor for 100 tests 140 filters)");
		NioxCatalog nc = new NioxCatalog();
		nc.addProductToCatalog(c);
		nc.addProductToCatalog(c2);
		nc.addProductToCatalog(c3);
		nc.addProductToCatalog(c4);
		String [] array = nc.getProductPartNumbers();
		if(array.length != 4) fail();
		if(!array[0].equals("12-1200")) fail();
		if(!array[3].equals("12-1810-US")) fail();
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.NioxCatalog#getProductNames()}.
	 */
	@Test
	public void testGetProductNames() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.NioxCatalog#getDefaultProductPartNumbers()}.
	 */
	@Test
	public void testGetDefaultProductPartNumbers() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.NioxCatalog#getDefaultProductNames()}.
	 */
	@Test
	public void testGetDefaultProductNames() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.catalog.NioxCatalog#getDescriptions()}.
	 */
	@Test
	public void testGetDescriptions() {
		//fail("Not yet implemented");
	}

}
