/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

import com.circa.mrv.grs_manager.util.LinkedAbstractList;

/**
 * Tests functionality of LinkedAbstractList class
 * @author Ben Ioppolo and Arthur Vargas
 */
public class LinkedAbstractListTest {
	
	/** A LinkedAbstractList */
	private LinkedAbstractList<String> stringList;
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
		
		stringList = new LinkedAbstractList<String>(10);  // initialize the new LinkedAbstractList to capacity 10
		s1 = "This the first test string"; // initialize the first test string
		s2 = "This is the second test string"; // initialize the second test string
		s3 = "This is the third test string"; // initialize the third test string
	}
	
	/**
	 * Tests ArrayList constructor
	 */
	@Test
	public void testLinkedAbstractList() {
		
		// check that a new LinkedAbstractList has been created and it is empty
		assertEquals(0, stringList.size());
	}
	
	/**
	 * Tests LinkedAbstractList.size()
	 */
	@Test
	public void testSize() {
		assertEquals(0, stringList.size()); // empty LinkedAbstractList
		// add some strings
		stringList.add(0, s1);
		stringList.add(1, s2);
		stringList.add(2, s3);
		assertEquals(3, stringList.size());
		stringList.add(3, "Arthur");
		stringList.add(4, "Vargas");
		stringList.add(5, "CSC216");
		stringList.add(6, "JavaJavaJava");
		assertEquals(7, stringList.size()); // check new size
	}

	/**
	 * Tests LinkedAbstractList.remove()
	 */
	@Test
	public void testRemove() {
		assertEquals(0, stringList.size());  // empty list
		
		//try to remove an element from an empty list
		try {
			stringList.remove(0);
			fail(); // IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, stringList.size()); // no size change
		}
		
		stringList.add(0, s1); // add s1
		stringList.add(1, s2); // add s2
		stringList.add(2, s3); // add s3
		assertEquals(3, stringList.size()); // new size
		String returned;
		
		// try to remove an element from an index < 0
		try {
			stringList.remove(-1);
			fail(); // IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, stringList.size()); // no size change
		}
		
		// try to remove an element from an index > size
		try {
			stringList.remove(5);
			fail(); // IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, stringList.size()); // no size change
		}
		
		// try to remove an element from an index = size
		try {
			stringList.remove(stringList.size());
			fail(); // IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, stringList.size()); // no size change
		}
		
		// try to remove an element from the front
		returned = stringList.remove(0);
		assertEquals(returned, s1); // the returned string equals the string at position 0 (s1)
		assertEquals(2, stringList.size()); // size decrease by 1
		assertEquals(s2, stringList.get(0)); // the position of other elements has shifted
		assertEquals(s3, stringList.get(1));
		
		//remove from end
		assertEquals(s3, stringList.remove(1)); 
		assertEquals(1, stringList.size()); // size decrease by 1
		
		//remove last item
		assertEquals(s2, stringList.remove(0)); 
		assertEquals(0, stringList.size()); // size decrease by 1
		
		//add more items.
		stringList.add(0, s1); // add s1
		stringList.add(1, s2); // add s2
		stringList.add(2, s3); // add s3
		
		//remove from middle
		assertEquals(s2, stringList.remove(1)); 
		assertEquals(2, stringList.size()); // size decrease by 1
		assertEquals(s1, stringList.get(0));
		assertEquals(s3, stringList.get(1));
	}

	/**
	 * Tests LinkedAbstractList.get()
	 */
	@Test
	public void testGet() {
		assertEquals(0, stringList.size());  // empty list
		
		//test getting from an empty list
		try {
			stringList.get(0);
			fail(); // IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, stringList.size());  // empty list
		}
		
		stringList.add(0, s1); // add s1
		stringList.add(1, s2); // add s2
		stringList.add(2, s3); // add s3
		assertEquals(3, stringList.size()); // new size
		String get;
		
		// try to get an element from an index < 0
		try {
			stringList.get(-1);
			fail(); // IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, stringList.size()); // no size change
		}
		
		// try to get an element from an index > size
		try {
			stringList.get(5);
			fail(); // IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, stringList.size()); // no size change
		}
				
		// try to get an element from an index = size
		try {
			stringList.get(stringList.size());
			fail(); // IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, stringList.size()); // no size change
		}

		// try to get an element from the front
		get = stringList.get(0);
		assertEquals(get, s1); // the returned string equals the string at position 0 (s1)

		//get from end
		assertEquals(s3, stringList.get(2)); 
		
		//get from middle
		assertEquals(s2, stringList.get(1)); 
	}

	/**
	 * Tests LinkedAbstractList.add()
	 */
	@Test
	public void testAdd() {
		
		assertEquals(0, stringList.size()); // LinkedAbstractList is empty
				
		// add at an index < 0
		try {
			stringList.add(-1, "Add this string.");
			fail(); // An IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, stringList.size()); // LinkedAbstractList is empty
		}
		
		// try to pass in an object at an index > size
		try {
			stringList.add(15, "Add this string.");
			fail(); // An IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, stringList.size()); // LinkedAbstractList is empty
		}
		
		// try to add null
		try {
			stringList.add(0, null);
			fail(); //NullPointerException should have been thrown and was not
		} catch (NullPointerException e) {
			assertEquals(0, stringList.size()); // LinkedAbstractList is empty
		}
		
		//add to front
		stringList.add(0, s1); // add s1
		assertEquals(1, stringList.size()); // LinkedAbstractList has 1 element
		
		//add to front again
		stringList.add(0, s2); // add s2
		assertEquals(2, stringList.size()); // LinkedAbstractList has 2 elements
		assertEquals(s1, stringList.get(1));
		assertEquals(s2, stringList.get(0));
		
		//add to end
		stringList.add(2, s3); // add s3
		assertEquals(3, stringList.size()); // LinkedAbstractList has 3 elements
		assertEquals(s1, stringList.get(1));
		assertEquals(s2, stringList.get(0));
		assertEquals(s3, stringList.get(2));
		
		//add to middle
		stringList.add(2, "New String"); 
		assertEquals(4, stringList.size()); // LinkedAbstractList has 4 elements
		assertEquals(s1, stringList.get(1));
		assertEquals(s2, stringList.get(0));
		assertEquals("New String", stringList.get(2));
		assertEquals(s3, stringList.get(3));
		
		// try to add a duplicate
		try {
			stringList.add(1, s2); 
			fail(); // An IllegalArgumentException should have been thrown and was not
		} catch (IllegalArgumentException e) {
			assertEquals(4, stringList.size()); // LinkedAbstractList has 4 elements
		}
		
		//add a bunch of items to end of the list to test capacity violation
		for (int i = 4 ; i < 10 ; i++){
			try{
			stringList.add(i, "test string " + i);
			} catch (IllegalArgumentException e){
				assertEquals(10, stringList.size());
			}
		}
	}

	
	/**
	 * Tests LinkedAbstractList.set 
	 */
	@Test
	public void testSet(){
		assertEquals(0, stringList.size()); // LinkedAbstractList is empty
		
		// set at an index < 0
		try {
			stringList.set(-1, "Add this string.");
			fail(); // An IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, stringList.size()); // LinkedAbstractList is empty
		}
		
		// try to set an object at an index > size
		try {
			stringList.set(15, "Add this string.");
			fail(); // An IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, stringList.size()); // LinkedAbstractList is empty
		}
		
		//set a new element to the first element of an empty list
		try{
			stringList.set(0, s1); // set s1 to the first element
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, stringList.size()); // LinkedAbstractList is empty
		}

		
		// try to set null
		try {
			stringList.set(0, null);
			fail(); //NullPointerException should have been thrown and was not
		} catch (NullPointerException e) {
			assertEquals(0, stringList.size()); // LinkedAbstractList is empty
		}
		
		//add to front 
		stringList.add(0, s2); // add s2
		assertEquals(1, stringList.size()); // LinkedAbstractList has 1 elements
		assertEquals(s2, stringList.get(0));
		
		//set to end
		stringList.add(1, s3); // add s3
		stringList.set(1, s1);
		assertEquals(2, stringList.size()); // LinkedAbstractList has 2 elements
		assertEquals(s2, stringList.get(0));
		assertEquals(s1, stringList.get(1));
		
		//set to middle
		stringList.add(1, "New String"); 
		stringList.set(1, s3);
		assertEquals(3, stringList.size()); // LinkedAbstractList has 3 elements
		assertEquals(s1, stringList.get(2));
		assertEquals(s2, stringList.get(0));
		assertEquals(s3, stringList.get(1));
		
		// try to set a duplicate
		try {
			stringList.set(1, s2); 
			fail(); // An IllegalArgumentException should have been thrown and was not
		} catch (IllegalArgumentException e) {
			assertEquals(3, stringList.size()); // LinkedAbstractList has 3 elements
		}
		
		//test linked node single param constructor
		
	}
	
	/**
	 * Tests LinkedAbstractList.setCapacity
	 */
	@Test
	public void testSetCapacity() {
		assertEquals(0, stringList.size()); // LinkedAbstractList is empty
		
		// set at an index < 0
		try {
			stringList.setCapacity(-1);
			fail(); // An IllegalArgumentException should have been thrown and was not
		} catch (IllegalArgumentException e) {
			assertEquals(0, stringList.size()); // LinkedAbstractList is empty
		}
		
		//add to front 
		stringList.add(0, s2); // add s2
		assertEquals(1, stringList.size()); // LinkedAbstractList has 1 elements
		assertEquals(s2, stringList.get(0));
				
		//set to end
		stringList.add(1, s3); // add s3
		stringList.set(1, s1);
		assertEquals(2, stringList.size()); // LinkedAbstractList has 2 elements
		assertEquals(s2, stringList.get(0));
		assertEquals(s1, stringList.get(1));
				
		//set to middle
		stringList.add(1, "New String"); 
		stringList.set(1, s3);
		assertEquals(3, stringList.size()); // LinkedAbstractList has 3 elements
		assertEquals(s1, stringList.get(2));
		assertEquals(s2, stringList.get(0));
		
		// try to set capacity less than size
		try {
			stringList.setCapacity(2);
			fail(); // an IllegalArgumentException should have been thrown and was not
		} catch (IllegalArgumentException e) {
			assertEquals(3, stringList.size());
		}
		
		// try to increase capacity to 5
		try {
			stringList.setCapacity(5);
			stringList.add(1, "Add this new string.");
			stringList.add(2, "Add this new string also.");
			assertEquals(5, stringList.size());
		} catch (IllegalArgumentException e) {
			fail(); // an IllegalArgumentException should not have been thrown
		}
		
		
	}
}
