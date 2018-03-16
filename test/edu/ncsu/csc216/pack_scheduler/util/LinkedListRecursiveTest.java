/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the LinkedListRecursive class
 * @author Arthur Vargas
 */
public class LinkedListRecursiveTest {
	/** A LinkedListRecursive */
	private LinkedListRecursive<String> stringList;
	/** Test string 1 */
	private String s1;
	/** Test String 2 */
	private String s2;
	/** Test string 3 */
	private String s3;
	
	/**
	 * Sets up a LinkedListRecursive object and test strings for indivdual method testing.
	 */
	@Before
	public void setUp() {
		stringList = new LinkedListRecursive<String>();  // construct new LLR
		s1 = "This the first test string"; // initialize the first test string
		s2 = "This is the second test string"; // initialize the second test string
		s3 = "This is the third test string"; // initialize the third test string
	}

	/**
	 * Test constructor
	 */
	@Test
	public void testLinkedListRecursive() {
		assertEquals(0, stringList.size()); // the size of the new LLR is 0
		
	}

	/**
	 * Test LinkedListRecursive.isEmpty()
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(stringList.isEmpty()); // true if the list is empty
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#size()}.
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
	 * Test LinkedListRecursive.add(element)
	 */
	@Test
	public void testAddE() {
		assertEquals(0, stringList.size()); // LLR is empty
		
		// try to add null
		try {
			stringList.add(0, null);
			fail(); //NullPointerException should have been thrown and was not
		} catch (NullPointerException e) {
			assertEquals(0, stringList.size()); // LLR is empty
		}
		
	
		stringList.add(s1); // add s1
		assertEquals(1, stringList.size()); // LLR has 1 element
		stringList.add(s2); // add s2
		assertEquals(2, stringList.size()); // LLR has 2 elements
		assertEquals(s1, stringList.get(0));
		assertEquals(s2, stringList.get(1));
	
		stringList.add(s3); // add s3
		assertEquals(3, stringList.size()); // LLR has 3 elements
		assertEquals(s1, stringList.get(0));
		assertEquals(s2, stringList.get(1));
		assertEquals(s3, stringList.get(2));
		
		stringList.add("New String"); 
		assertEquals(4, stringList.size()); // LinkedAbstractList has 4 elements
		assertEquals("New String", stringList.get(3));
		assertEquals(s1, stringList.get(0));
		assertEquals(s2, stringList.get(1));
		assertEquals(s3, stringList.get(2));
		
		// try to add a duplicate
		try {
			stringList.add(s2); 
			fail(); // An IllegalArgumentException should have been thrown and was not
		} catch (IllegalArgumentException e) {
			assertEquals(4, stringList.size()); // LLR has 4 elements
		}
	}

	/**
	 * Test LinkedListRecursive.add(index, element)
	 */
	@Test
	public void testAddIntE() {
		assertEquals(0, stringList.size()); // LinkedListRecursive is empty
		
		// add at an index < 0
		try {
			stringList.add(-1, "Add this string.");
			fail(); // An IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, stringList.size()); // LLR is empty
		}
		
		// try to pass in an object at an index > size
		try {
			stringList.add(15, "Add this string.");
			fail(); // An IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, stringList.size()); // LLR is empty
		}
		
		// try to add null
		try {
			stringList.add(0, null);
			fail(); //NullPointerException should have been thrown and was not
		} catch (NullPointerException e) {
			assertEquals(0, stringList.size()); // LLR is empty
		}
		
		//add to front
		stringList.add(0, s1); // add s1
		assertEquals(1, stringList.size()); // LLR has 1 element
		
		//add to front again
		stringList.add(0, s2); // add s2
		assertEquals(2, stringList.size()); // LLR has 2 elements
		assertEquals(s1, stringList.get(1));
		assertEquals(s2, stringList.get(0));
		
		//add to end
		stringList.add(2, s3); // add s3
		assertEquals(3, stringList.size()); // LLR has 3 elements
		assertEquals(s1, stringList.get(1));
		assertEquals(s2, stringList.get(0));
		assertEquals(s3, stringList.get(2));
		
		//add to middle
		stringList.add(2, "New String"); 
		assertEquals(4, stringList.size()); // LLR has 4 elements
		assertEquals(s1, stringList.get(1));
		assertEquals(s2, stringList.get(0));
		assertEquals("New String", stringList.get(2));
		assertEquals(s3, stringList.get(3));
		
		// try to add a duplicate
		try {
			stringList.add(1, s2); 
			fail(); // An IllegalArgumentException should have been thrown and was not
		} catch (IllegalArgumentException e) {
			assertEquals(4, stringList.size()); // LLR has 4 elements
		}
	
	}

	/**
	 * Test LinkedListRecursive.get()
	 */
	@Test
	public void testGet() {
		assertEquals(0, stringList.size());  // empty list
		stringList.add(0, s1); // add s1
		stringList.add(1, s2); // add s2
		stringList.add(2, s3); // add s3
		assertEquals(3, stringList.size()); // new size
		assertEquals(s1, stringList.get(0));
		assertEquals(s2, stringList.get(1));
		assertEquals(s3, stringList.get(2));
		
		//try to get an element from an empty list
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		try {
			list.get(1);
			fail(); // IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size()); // no size change
		}
		
		list.add(s1);
		list.add(s2);
		list.add(s3);
		assertEquals(3, list.size());
		//try to get an element from index < 0
		try {
			list.get(-1);
			fail(); // IndexOutOfBoundsException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, list.size()); // no size change
		}
		
		
	}

	/**
	 * Tests LinkedListRecursive.remove
	 */
	@Test
	public void testRemoveE() {
		
		assertEquals(0, stringList.size());  // empty list
		
		//try to remove an element from an empty list
			assertFalse(stringList.remove(s1));
			assertEquals(0, stringList.size()); // no size change
		
		stringList.add(0, s1); // add s1
		stringList.add(1, s2); // add s2
		stringList.add(2, s3); // add s3
		assertEquals(3, stringList.size()); // new size
		//String returned;
		
		// try to remove an element that is not contained in the list
		try {
			assertFalse(stringList.remove("not in the list")); 
			assertEquals(3, stringList.size()); // no change in size
		} catch (Exception e) {
			fail(); // Unexpected exception thrown by LLR.remove(element)
		}
		
		// try to remove an existing element
		try {
			assertTrue(stringList.remove(s1));
			assertEquals(2, stringList.size()); // LLR size decremented by 1
		} catch (Exception e) {
			fail(); // Unexpected exception thrown by LLR.remove(element)
		}
		
	
	}

	/**
	 * Test LinkedListRecursive.remove(int)
	 */
	@Test
	public void testRemoveInt() {
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
		//String returned;
		
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
		stringList.remove(0);
		assertEquals(2, stringList.size()); // size is decremented by 1
		assertEquals(s2, stringList.get(0)); // s2 is now at the front
		assertEquals(s3, stringList.get(1));
		
		//remove from end
		stringList.remove(1); 
		assertEquals(1, stringList.size()); // size decrease by 1
		assertEquals(s2, stringList.get(0));
		
		//remove last item
		stringList.remove(0); 
		assertEquals(0, stringList.size()); // size decrease by 1
		
		//add more items.
		stringList.add(0, s1); // add s1
		stringList.add(1, s2); // add s2
		stringList.add(2, s3); // add s3
		stringList.add("this is test string 4");
		stringList.add("this is test string 5");
		stringList.add("this is test string 6");
		assertEquals(6, stringList.size());
		
		//remove from middle
		stringList.remove(4); 
		assertEquals(5, stringList.size()); // size decrease by 1
		assertEquals(s1, stringList.get(0));
		assertEquals(s2, stringList.get(1));
		assertEquals(s3, stringList.get(2));
		assertEquals("this is test string 4", stringList.get(3));
		assertEquals("this is test string 6", stringList.get(4));
	}

	/**
	 * Tests LinkedListRecursive
	 */
	@Test
	public void testSet() {
		assertEquals(0, stringList.size()); 
		
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
			assertEquals(0, stringList.size()); // LinkedListRecursive is empty
		}
		
		//set a new element to the first element of an empty list
		try{
			stringList.set(0, s1); // set s1 to the first element
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, stringList.size()); // LinkedListRecursive is empty
		}

		
		// try to set null
		try {
			stringList.set(0, null);
			fail(); //NullPointerException should have been thrown and was not
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, stringList.size()); // LinkedListRecursive is empty
		}
		
		//add to front 
		stringList.add(0, s2); // add s2
		assertEquals(1, stringList.size()); // LinkedListRecursive has 1 elements
		assertEquals(s2, stringList.get(0));
		
		//set to end
		stringList.add(1, s3); // add s3
		stringList.set(1, s1);
		assertEquals(2, stringList.size()); // LinkedListRecursive has 2 elements
		assertEquals(s2, stringList.get(0));
		assertEquals(s1, stringList.get(1));
		
		//set to middle
		stringList.add(1, "New String"); 
		stringList.set(1, s3);
		assertEquals(3, stringList.size()); // LinkedListRecursive has 3 elements
		assertEquals(s1, stringList.get(2));
		assertEquals(s2, stringList.get(0));
		assertEquals(s3, stringList.get(1));
		
		// try to set a duplicate
		try {
			stringList.set(1, s2); 
			fail(); // An IllegalArgumentException should have been thrown and was not
		} catch (IllegalArgumentException e) {
			assertEquals(3, stringList.size()); // LinkedListRecursive has 3 elements
		}
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#contains(java.lang.Object)}.
	 */
	@Test
	public void testContains() {
		assertEquals(0, stringList.size()); // LLR is empty
		stringList.add(s2); // add s2
		assertEquals(1, stringList.size()); // LinkedListRecursive has 1 elements
		assertEquals(s2, stringList.get(0));
		stringList.add("this is test string 4");
		stringList.add("this is test string 5");
		stringList.add("this is test string 6");
		
		assertTrue(stringList.contains("this is test string 4"));
		assertFalse(stringList.contains("this isn't in the list"));
		
	}

}
