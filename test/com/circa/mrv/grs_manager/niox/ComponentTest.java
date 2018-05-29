/**
 * 
 */
package com.circa.mrv.grs_manager.niox;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Calendar;

import org.junit.Test;

/**
 * Tests Component
 * @author Arthur Vargas
 */
public class ComponentTest {
	/** misc id number */
	private String miscId = "992040";
	/** part number */
	private String partNumber = "12-1200";
	/** serial number */
	private long serial = 10293845;
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
	 * Test method for {@link com.circa.mrv.grs_manager.niox.Component#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.niox.Component#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		//fail("Not yet implemented");
	}

	/**
	 * Tests Component constructor.
	 */
	@Test
	public void testComponent() {
		Component c = new Component(miscId,partNumber,family,generationVero,descriptionDevice,Double.toString(price),notes);

		if(c.getMiscIDNumber() != Long.parseLong(miscId) || !c.getPartNumber().equals(partNumber) ||
				!c.getFamily().equals(family) || !c.getGeneration().equals(generationVero) ||
				!c.getDescription().equals(descriptionDevice) || c.getPrice() != price ||
				!c.getNote().equals(notes)) {
			fail();
		}
		
		Calendar cal = Calendar.getInstance();
		Component c1 = new Component(family,descriptionDevice,partNumber,price,generationVero,serial,cal);
		if(!c1.getFamily().equals(family) || !c1.getDescription().equals(descriptionDevice) ||
				!c1.getPartNumber().equals(partNumber) || c1.getPrice() != price ||
				!c1.getGeneration().equals(generationVero) || c1.getSerial() != serial ||
				!c1.getPackageDate().equals(cal) || c1.getMiscIDNumber() != 0  ) {
			fail();
		}
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.niox.Component#calculateExpiration()}.
	 */
	@Test
	public void testCalculateExpiration() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.niox.Component#checkExpiration(java.util.Calendar)}.
	 */
	@Test
	public void testCheckExpiration() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.niox.Product#Product(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testProductStringStringString() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.niox.Product#Product(java.lang.String, java.lang.String, java.lang.String, double)}.
	 */
	@Test
	public void testProductStringStringStringDouble() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.niox.Product#Product(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testProductStringString() {
		//fail("Not yet implemented");
	}

	/**
	 * Tests Product.getShortDisplayArray
	 */
	@Test
	public void testGetShortDisplayArray() {
		Component c = new Component(miscId,partNumber,family,generationVero,descriptionDevice,Double.toString(price),notes);
		String [] shortDisplay = {partNumber,descriptionDevice};
		if (!Arrays.equals(c.getShortDisplayArray(), shortDisplay)) fail();
		
		Component c1 = new Component(family,descriptionDevice,partNumber,price,generationVero,serial,Calendar.getInstance());
		if (!Arrays.equals(c.getShortDisplayArray(), shortDisplay)) fail();
		
	}

	/**
	 * Tests Product.getLongDisplayArray()
	 */
	@Test
	public void testGetLongDisplayArray() {
		Component c = new Component(miscId,partNumber,family,generationVero,descriptionDevice,Double.toString(price),notes);
		String [] longDisplay = {partNumber,descriptionDevice,Double.toString(price)};
		if (!Arrays.equals(c.getLongDisplayArray(), longDisplay)) fail();
		
		Component c1 = new Component(family,descriptionDevice,partNumber,price,generationVero,serial,Calendar.getInstance());
		if(!Arrays.equals(c1.getLongDisplayArray(), longDisplay)) fail();
	}

}
