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
	 * Tests getNioxCatalog()
	 */
	@Test
	public void testGetNioxCatalog() {
		NioxCatalog nc = new NioxCatalog();
		nc.loadProductsFromFile(allProducts);
		String[][] catalog = nc.getNioxCatalog();
		assertEquals(catalog[21][0],"09-1005");
		assertEquals(catalog[21][2],"MINO");
		assertEquals(catalog[21][3],"Power Supply NIOX MINO 2009");
		assertEquals(catalog[21][4],"95.0");
		assertEquals(catalog[20][0],"09-1015");
		assertEquals(catalog[20][2],"MINO");
		assertEquals(catalog[20][3],"QC Plug NIOX Mino Unit");
		assertEquals(catalog[20][4],"10.0");
		assertEquals(catalog[19][0],"no value");
		assertEquals(catalog[19][2],"MINO");
		assertEquals(catalog[19][3],"NO scrubber expiration date");
		assertEquals(catalog[19][4],"0.0");
		assertEquals(catalog[18][0],"09-1300");
		assertEquals(catalog[18][2],"MINO");
		assertEquals(catalog[18][3],"NO scrubber 2009");
		assertEquals(catalog[18][4],"70.0");
		assertEquals(catalog[17][0],"no value");
		assertEquals(catalog[17][2],"MINO");
		assertEquals(catalog[17][3],"test kit 100 expiration date");
		assertEquals(catalog[17][4],"0.0");
		
		
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
		
		NioxCatalog nc1 = new NioxCatalog();
		nc1.loadProductsFromFile(allProducts);
		
		assertEquals(nc1.getProductPartNumbers()[0],"12-1200");
		assertEquals(nc1.getProductPartNumbers()[2],"12-1810-US");
		assertEquals(nc1.getProductPartNumbers()[4],"12-1220");
		assertEquals(nc1.getProductPartNumbers()[6],"12-1008");
		assertEquals(nc1.getProductPartNumbers()[21],"09-1005");
		assertEquals(nc1.getProductPartNumbers()[20],"09-1015");
		assertEquals(nc1.getProductPartNumbers()[19],"no value");
		assertEquals(nc1.getProductPartNumbers()[18],"09-1300");
		
		assertEquals(nc1.getProductNames()[0],"NIOX VERO device");
		assertEquals(nc1.getProductNames()[2],"NIOX VERO test kit 100");
		assertEquals(nc1.getProductNames()[4],"NIOX VERO Power Adapter");
		assertEquals(nc1.getProductNames()[6],"NIOX VERO Battery Lid");
		assertEquals(nc1.getProductNames()[21],"NIOX MINO Power Supply NIOX MINO 2009");
		assertEquals(nc1.getProductNames()[20],"NIOX MINO QC Plug NIOX Mino Unit");
		assertEquals(nc1.getProductNames()[19],"NIOX MINO NO scrubber expiration date");
		assertEquals(nc1.getProductNames()[18],"NIOX MINO NO scrubber 2009");
		
		
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
