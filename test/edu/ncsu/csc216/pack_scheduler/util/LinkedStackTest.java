/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Provides suite of tests for the linkedStack
 * @author Arthur Vargas
 */
public class LinkedStackTest {
	/** An ArrayStack */
	private LinkedStack<String> linkedStack;
	/**
	 * Sets up ArrayStack objects to be used in testing.
	 */
	@Before
	public void setUp() {
		linkedStack = new LinkedStack<String>(10);
		assertEquals(0, linkedStack.size());
		assertTrue(linkedStack.isEmpty());
			
	}

	/**
	 * Tests LinkedStack.push
	 */
	@Test
	public void testPush() {
		// Add single element to the front of the stack
		linkedStack.push("Element " + Integer.toString(20));
		assertFalse(linkedStack.isEmpty());
		assertEquals(1, linkedStack.size());
			
		linkedStack.setCapacity(20);
		// try to push 10 new strings onto the stack 
		for (int i = 0 ; i < 10 ; i++) {
			linkedStack.push("Element " + Integer.toString(i));
		}
		assertEquals(11, linkedStack.size()); // the total size should be 11
				
		// add one more string so the size is an even number
		linkedStack.push("Element " + Integer.toString(21));
		assertEquals(12, linkedStack.size());
		
		// Insert some Strings in the middle of stringStack by
		// popping elements off stringStack and pushing them onto
		// stringStack2
		ArrayStack<String> stringStack2 = new ArrayStack<String>(10);
		int s = linkedStack.size();
		for (int i = 0; i < s / 2; i++) {
			stringStack2.push(linkedStack.pop());
		}
				
		// this string should be on the top of stringStack before pushing
		// the other elements back on
		linkedStack.push("This string should end up in the middle.");
		assertEquals(7, linkedStack.size());
		
		int s2 = stringStack2.size();
		for (int i = 0; i < s2; i++) {
			linkedStack.push(stringStack2.pop());
		}
		assertEquals(13, linkedStack.size());
		assertEquals(0, stringStack2.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#pop()}.
	 */
	@Test
	public void testPop() {
		// try to push 10 new strings onto the stack 
		for (int i = 0 ; i < 10 ; i++){//add 10 elements to the list
			linkedStack.push("Element " + Integer.toString(i));
		}
		assertEquals(10, linkedStack.size());	
		
		// last in first out
		assertEquals("Element 9", linkedStack.pop());
		assertEquals("Element 8", linkedStack.pop());
		assertEquals(8, linkedStack.size());
				
		// Insert some Strings in the middle of stringStack
		ArrayStack<String> stringStack2 = new ArrayStack<String>(10);
		int s = linkedStack.size();
		for (int i = 0; i < (s / 2); i++) {
			stringStack2.push(linkedStack.pop());
		}
				
		linkedStack.push("This string should end up in the middle.");
		assertEquals(5, linkedStack.size());
		int s2 = stringStack2.size();	
		for (int i = 0; i < s2; i++) {
			linkedStack.push(stringStack2.pop());
		}
		assertEquals(9, linkedStack.size());
		assertEquals(0, stringStack2.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		// Add single element to the front of the stack
		linkedStack.push("Element " + Integer.toString(1));
		assertFalse(linkedStack.isEmpty());
		assertEquals(1, linkedStack.size());
		
		// Remove the element and assert that it is empty
		linkedStack.pop();
		assertTrue(linkedStack.isEmpty());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#size()}.
	 */
	@Test
	public void testSize() {
		// try to push 10 new strings onto the stack 
		for (int i = 0 ; i < 10 ; i++){//add 10 elements to the list
			linkedStack.push("Element " + Integer.toString(i));
		}
		assertEquals(10, linkedStack.size());	
		
		// remove 5 strings
		for (int i = 0 ; i < 5 ; i++){//add 10 elemnts to the list
			linkedStack.pop();
		}
		assertEquals(5, linkedStack.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#setCapacity(int)}.
	 */
	@Test
	public void testSetCapacity() {
		linkedStack.setCapacity(5);
		// try to push 5 new strings onto the stack 
		for (int i = 0; i < 5; i++){//add 5 elements to the list
			linkedStack.push("Element " + Integer.toString(i));
		}
		assertEquals(5, linkedStack.size());
		
		// try to set capacity less than size
		try {
			linkedStack.setCapacity(3);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(5, linkedStack.size());
		}
		
		// try to set capacity to -1
		try {
			linkedStack.setCapacity(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(5, linkedStack.size());
		}
	}

}
