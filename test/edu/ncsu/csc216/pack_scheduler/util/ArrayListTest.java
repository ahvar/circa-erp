/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

import com.circa.mrv.grs_manager.util.ArrayList;

/**
 * Tests functionality of ArrayList class
 * @author Arthur Vargas
 */
public class ArrayListTest {
	
	/** An ArrayList */
	private ArrayList<String> al;
	/** Test string 1 */
	private String s1;
	/** Test String 2 */
	private String s2;
	/** Test string 3 */
	private String s3;
	
	/**
	 * Sets up an ArrayList for tests to use.
	 */
	@Before
	public void setUpBefore() {
		
		al = new ArrayList<String>();  // initialize the new ArrayList
		s1 = "This the first test string"; // initialize the first test string
		s2 = "This is the second test string"; // initialize the second test string
		s3 = "This is the third test string"; // initialize the third test string
	}
	
	/**
	 * Tests ArrayList constructor
	 */
	@Test
	public void testArrayList() {
		
		// check that a new ArrayList has been created and it is empty
		assertEquals(0, al.size());

	}
	
	/**
	 * Tests Arraylist.size()
	 */
	@Test
	public void testSize() {
		assertEquals(0, al.size()); // empty ArrayList
		// add some strings
		al.add(s1);
		al.add(s2);
		al.add(s3);
		al.add("Arthur");
		al.add("Vargas");
		al.add("CSC216");
		al.add("JavaJavaJava");
		assertEquals(7, al.size()); // check new size
	}

	/**
	 * Tests ArrayList.growArray()
	 */
	@Test
	public void testRemove() {
		assertEquals(0, al.size());  // empty array
		al.add(s1); // add s1
		al.add(s2); // add s2
		al.add(s3); // add s3
		assertEquals(3, al.size()); // new size
		String returned;
		
		// try to remove an element from an index < 0
		try {
			al.remove(-1);
			fail(); // IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			// success
		}
		
		assertEquals(3, al.size()); // no size change
		
		// try to remove an element from an index > size
		try {
			al.remove(5);
			fail(); // IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			// success
		}
		
		assertEquals(3, al.size()); // no size change
		
		// try to remove an element from an index = size
		try {
			al.remove(al.size());
			fail(); // IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			// success
		}
		
		assertEquals(3, al.size()); // no size change
		
		// try to remove an element from a valid index
		returned = (String) al.remove(0);
		assertEquals(returned, s1); // the returned string equals the string at position 0 (s1)
		assertEquals(2, al.size()); // size decrease by 1
		assertEquals(s2, al.get(0)); // the position of other elements has shifted
		assertEquals(s3, al.get(1));
		
		al.remove(1); //remove from end
		assertEquals(1, al.size()); // size decrease by 1
	}

	/**
	 * Tests ArrayList.get()
	 */
	@Test
	public void testGet() {
		assertEquals(0, al.size());  // empty array
		al.add(s1); // add s1
		al.add(s2); // add s2
		al.add(s3); // add s3
		assertEquals(3, al.size()); // new size
		String get;
		
		// try to remove an element from an index < 0
		try {
			al.get(-1);
			fail(); // IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			// success
		}
				
		assertEquals(3, al.size()); // no size change
		
		// try to get an element from an index > size
		try {
			al.get(5);
			fail(); // IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			// success
		}
				
		assertEquals(3, al.size()); // no size change
				
		// try to get an element from an index = size
		try {
			al.get(al.size());
			fail(); // IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			// success
		}
				
		assertEquals(3, al.size()); // no size change
		
		// try to remove an element from a valid index
		get = (String) al.get(1);
		assertEquals(get, s2); // the returned string equals the string at position 0 (s1)
		assertEquals(3, al.size()); // no size change
		assertEquals(s1, al.get(0)); // the position of other elements has shifted
		assertEquals(s2, al.get(1));
		assertEquals(s3, al.get(2));
		
	}

	/**
	 * Tests ArrayList.addE()
	 */
	@Test
	public void testAdd() {
		
		assertEquals(0, al.size()); // ArrayList is empty
		
		al.add(0, s1); // add s1
		al.add(1, s2); // add s2
		al.add(2, s3); // add s3
		
		assertEquals(3, al.size());
		
		// try to pass in an object at an index < 0
		try {
			al.add(-1, "Add this string.");
			fail(); // An IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			// success
		}
		// try to pass in an object at an index > size
		try {
			al.add(15, "Add this string.");
			fail(); // An IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			// success
		}
		assertEquals(3, al.size()); // no size change
		
		// try to add null
		try {
			al.add(null);
			fail(); //NullPointerException should have been thrown and was not
		} catch (NullPointerException e) {
			// success
		}
		
		assertEquals(3, al.size()); // no size change
		
		// try to add a duplicate
		try {
			al.add(s2); // An IllegalArgumentException should have been thrown and was not
		} catch (IllegalArgumentException e) {
			// success
		}
	
		
		assertEquals(3, al.size()); // no size change
		
		// add some valid strings to the list 
		al.add(al.size(), "Add this to the list");
		assertEquals(4, al.size());
		assertEquals("Add this to the list", al.get(al.size() - 1));
		al.add(1, "Add another one to the list");
		assertEquals(5, al.size());
		assertEquals("Add another one to the list", al.get(1));
		
		//add a bunch of items to the list
		al = new ArrayList<String>();
		for (int i = 0 ; i < 12 ; i++){
			al.add("test string " + i);
		}
		assertEquals(12, al.size());
	}

	/**
	 * Tests ArrayList.indexOf()
	 */
	@Test
	public void testSet() {
		assertEquals(0, al.size()); // ArrayList is empty

		al.add(s1); // add s1
		al.add(s2); // add s2
		al.add(s3); // add s3
		
		assertEquals(s2, al.set(1, "test"));
		assertEquals(3, al.size());
		
		// try to pass in an object at an index < 0
		try {
			al.set(-1, "Set this string right here.");
			fail(); // An IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			// success
		}
				
		assertEquals(3, al.size()); // no size change
				
		// try to set null
//		try {
//			al.set(0, null);
//			fail(); //NullPointerException should have been thrown and was not
//		} catch (NullPointerException e) {
//			// success
//		}
				
		assertEquals(3, al.size()); // no size change
				
		// try to set a duplicate
		try {
			al.set(1, s3); 
			fail(); // An IllegalArgumentException should have been thrown and was not
		} catch (IllegalArgumentException e) {
			// success
		}
		
		assertEquals(3, al.size());
		
		// try to set at an index > size
		try {
			al.set(10, "You shouldn't be seeing this."); 
			fail();  // IndexOutOfBoundsException should have been thrown but was not
		} catch (IndexOutOfBoundsException e) {
			// success
		}
		
		assertEquals(3, al.size());
		
		// try to set at an index = size
		try {
			al.set(al.size() + 1, "Can you see this."); 
			fail();	
		} catch (IndexOutOfBoundsException e) {
			// success
		}		
	}
}
