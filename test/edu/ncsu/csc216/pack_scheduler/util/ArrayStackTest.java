/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the ArrayStack class
 * @author Arthur Vargas
 */
public class ArrayStackTest {
	/** An ArrayStack */
	private ArrayStack<String> stringStack;
	
	/**
	 * Sets up ArrayStack objects to be used in testing.
	 */
	@Before
	public void setUp() {
		stringStack = new ArrayStack<String>(10);
		assertEquals(0, stringStack.size());
		assertTrue(stringStack.isEmpty());
			
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#push(java.lang.Object)}.
	 */
	@Test
	public void testPush() {
		stringStack.setCapacity(20);
		// Add single element to the front of the stack
		stringStack.push("Element " + Integer.toString(20));
		assertFalse(stringStack.isEmpty());
		assertEquals(1, stringStack.size());
		
		// try to push 10 new strings onto the stack 
		for (int i = 0 ; i < 10 ; i++) {
			stringStack.push("Element " + Integer.toString(i));
		}
		assertEquals(11, stringStack.size()); // the total size should be 11
		
		// add one more string so the size is an even number
		stringStack.push("Element " + Integer.toString(21));
		assertEquals(12, stringStack.size());
		
		// Insert some Strings in the middle of stringStack by
		// popping elements off stringStack and pushing them onto
		// stringStack2
		int s = stringStack.size();
		ArrayStack<String> stringStack2 = new ArrayStack<String>(10);
		for (int i = 0; i < s / 2; i++) {
			stringStack2.push(stringStack.pop());
		}
		
		// this string should be on the top of stringStack before pushing
		// the other elements back on
		stringStack.push("This string should end up in the middle.");
		assertEquals(7, stringStack.size());
		int s2 = stringStack2.size();
		for (int i = 0; i < s2 ; i++) {
			stringStack.push(stringStack2.pop());
		}
		assertEquals(13, stringStack.size());
		assertEquals(0, stringStack2.size());
	}

	/**
	 * Tests ArrayStack.pop
	 */
	@Test
	public void testPop() {
				
		// try to push 10 new strings onto the stack 
		for (int i = 0 ; i < 10 ; i++){//add 10 elements to the list
			stringStack.push("Element " + Integer.toString(i));
		}
		assertEquals(10, stringStack.size());	
		
		// last in first out
		assertEquals("Element 9", stringStack.pop());
		assertEquals("Element 8", stringStack.pop());
		assertEquals(8, stringStack.size());
				
		// Insert some Strings in the middle of stringStack
		ArrayStack<String> stringStack2 = new ArrayStack<String>(10);
		int s = stringStack.size();
		for (int i = 0; i < (s / 2); i++) {
			stringStack2.push(stringStack.pop());
		}
				
		stringStack.push("This string should end up in the middle.");
		assertEquals(5, stringStack.size());
		int s2 = stringStack2.size();	
		for (int i = 0; i < s2; i++) {
			stringStack.push(stringStack2.pop());
		}
		assertEquals(9, stringStack.size());
		assertEquals(0, stringStack2.size());
	}

	/**
	 * Test ArrayStack.isEmpty
	 */
	@Test
	public void testIsEmpty() {
		// Add single element to the front of the stack
		stringStack.push("Element " + Integer.toString(1));
		assertFalse(stringStack.isEmpty());
		assertEquals(1, stringStack.size());
		
		// Remove the element and assert that it is empty
		stringStack.pop();
		assertTrue(stringStack.isEmpty());
	}

	/**
	 * Tests ArrayStack.size()
	 */
	@Test
	public void testSize() {
		// try to push 10 new strings onto the stack 
		for (int i = 0 ; i < 10 ; i++){//add 10 elements to the list
			stringStack.push("Element " + Integer.toString(i));
		}
		assertEquals(10, stringStack.size());	
		
		// remove 5 strings
		for (int i = 0 ; i < 5 ; i++){//add 10 elemnts to the list
			stringStack.pop();
		}
		assertEquals(5, stringStack.size());	
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#setCapacity(int)}.
	 */
	@Test
	public void testSetCapacity() {
		stringStack.setCapacity(5);
		// try to push 5 new strings onto the stack 
		for (int i = 0; i < 5; i++){//add 5 elements to the list
			stringStack.push("Element " + Integer.toString(i));
		}
		assertEquals(5, stringStack.size());
		
		// try to set capacity less than size
		try {
			stringStack.setCapacity(3);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(5, stringStack.size());
		}
		
		// try to set capacity to -1
		try {
			stringStack.setCapacity(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(5, stringStack.size());
		}
	}

}
