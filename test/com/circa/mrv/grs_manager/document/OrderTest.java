package com.circa.mrv.grs_manager.document;

import static org.junit.Assert.*;

import org.junit.Test;

import com.circa.mrv.grs_manager.niox.Component;

public class OrderTest {
	/** Product family */
	private final String family = "NIOX";
	/** MINO generation */
	private final String mino = "MINO";
	/** VERO generation */
	private final String vero = "VERO";
	/** part number */
	private final String part = "12-1200";
	/** product description */
	private final String description = "this is the product description";
	/** product unit price */
	private final String price = "20.00";

	/**
	 * Tests Order constructor
	 */
	@Test
	public void testOrder() {
		Order order = new Order(1);
		long one = 1;
		assertEquals(order.getNumber(),one);
		assertEquals(order.getProductCount(),0);
	}

	/**
	 * Tests addProduct()
	 */
	@Test
	public void testAddProduct() {
		Order order = new Order(1);
		assertEquals(order.getProductCount(),0);
		Component c = new Component(family,description,part,Double.parseDouble(price));
		order.addProduct(c, 1);
		//System.out.println(order.getProductCount());
		assertEquals(order.getProductCount(),1);
		order.addProduct(c, 1);
		assertEquals(order.getProductCount(),2);
		order.addProduct(c, 5);
		//System.out.println(order.getProductCount());
		assertEquals(order.getProductCount(),7);
	}

	/**
	 * Tests getProductDisplay()
	 */
	@Test
	public void testGetProductDisplay() {
		Order order = new Order(1);
		Component c = new Component(family,description,part,Double.parseDouble(price));
		order.addProduct(c, 4);
		String [][] array = order.getProductDisplay();
		assertEquals(array.length,4);
	}

}
