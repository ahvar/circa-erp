/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Provides suite of tests for the LinkedQueue Class
 * @author Ben W Ioppolo
 */
public class LinkedQueueTest {

	private LinkedQueue<String> stringQueue;
	
	/**
	 * Set up for each test
	 */
	@Before
	public void setUp() {
		stringQueue = new LinkedQueue<String>(10);
	}

//	/**
//	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedQueue#LinkedQueue()}.
//	 */
//	@Test
//	public void testLinkedQueue() {
//		assertTrue(stringQueue.size() == 0);
//	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedQueue#enqueue(java.lang.Object)}.
	 */
	@Test
	public void testEnqueue() {
		stringQueue.setCapacity(10);
		//add one item
		stringQueue.enqueue("test 0");
		assertTrue(stringQueue.size() == 1);
		assertEquals("test 0", stringQueue.dequeue());
		assertTrue(stringQueue.size() == 0);
		
		//add some items to the queue
		for (int i = 0 ; i < 10 ; i++){
			stringQueue.enqueue("test " + i);
		}
		assertTrue(stringQueue.size() == 10);
		assertEquals("test 0", stringQueue.dequeue());
		assertTrue(stringQueue.size() == 9);
		stringQueue.enqueue("test 0");
		
		//exceed capacity
		try{
			stringQueue.enqueue("one too many");
			fail();
		} catch(IllegalArgumentException e){
			assertTrue(stringQueue.size() == 10);
		}
		
		//interleaved
		assertEquals("test 1", stringQueue.dequeue());
		assertEquals("test 2", stringQueue.dequeue());
		assertTrue(stringQueue.size() == 8);
		int s = stringQueue.size();
		stringQueue.setCapacity(20);
		LinkedQueue<String> temp = new LinkedQueue<String>(10);
		for (int j = 0; j < s / 2; j++){
			temp.enqueue(stringQueue.dequeue()); //pulls 3, 4, 5, 6 off of queue
			stringQueue.enqueue("test " + (j + 1) * 100); // adds 100, 200, 300, 400
			stringQueue.enqueue(temp.dequeue()); // adds 3, 4, 5, 6
			//resulting queue should be 7, 8, 9, 0, 100, 3, 200, 4, 300, 5, 400, 6
		}
		assertTrue(stringQueue.size() == 12);
		assertTrue(temp.size() == 0);
		String str = "7,8,9,0,100,3,200,4,300,5,400,6";
		String[] sA = str.split(",");
		for (String st : sA){
			assertEquals("test " + st, stringQueue.dequeue());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedQueue#dequeue()}.
	 */
	@Test
	public void testDequeue() {
		stringQueue.setCapacity(10);
		//add one item
		stringQueue.enqueue("test 0");
		assertTrue(stringQueue.size() == 1);
		assertEquals("test 0", stringQueue.dequeue());
		assertTrue(stringQueue.size() == 0);
		
		//add some items to the queue
		for (int i = 0 ; i < 10 ; i++){
			stringQueue.enqueue("test " + i);
		}
		assertTrue(stringQueue.size() == 10);
		assertEquals("test 0", stringQueue.dequeue());
		assertTrue(stringQueue.size() == 9);
		stringQueue.enqueue("test 0");
		
		//exceed capacity
		try{
			stringQueue.enqueue("one too many");
			fail();
		} catch(IllegalArgumentException e){
			assertTrue(stringQueue.size() == 10);
		}
		
		//interleaved
		assertEquals("test 1", stringQueue.dequeue());
		assertEquals("test 2", stringQueue.dequeue());
		assertTrue(stringQueue.size() == 8);
		int s = stringQueue.size();
		stringQueue.setCapacity(20);
		LinkedQueue<String> temp = new LinkedQueue<String>(10);
		for (int j = 0; j < s / 2; j++){
			temp.enqueue(stringQueue.dequeue()); //pulls 3, 4, 5, 6 off of queue
			stringQueue.enqueue("test " + (j + 1) * 100); // adds 100, 200, 300, 400
			stringQueue.enqueue(temp.dequeue()); // adds 3, 4, 5, 6
			//resulting queue should be 7, 8, 9, 0, 100, 3, 200, 4, 300, 5, 400, 6
		}
		assertTrue(stringQueue.size() == 12);
		assertTrue(temp.size() == 0);
		String str = "7,8,9,0,100,3,200,4,300,5,400,6";
		String[] sA = str.split(",");
		for (String st : sA){
			assertEquals("test " + st, stringQueue.dequeue());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedQueue#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(stringQueue.isEmpty());
		stringQueue.enqueue("test");
		assertFalse(stringQueue.isEmpty());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedQueue#size()}.
	 */
	@Test
	public void testSize() {
		assertTrue(stringQueue.size() == 0);
		stringQueue.enqueue("test");
		assertTrue(stringQueue.size() == 1);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedQueue#setCapacity(int)}.
	 */
	@Test
	public void testSetCapacity() {
		stringQueue.setCapacity(1);
		stringQueue.enqueue("test");
		try{
			stringQueue.enqueue("test1");
			fail();
		} catch(IllegalArgumentException e){
			assertTrue(stringQueue.size() == 1);
		}
		
		stringQueue.setCapacity(2);
		stringQueue.enqueue("test1");
		assertTrue(stringQueue.size() == 2);	
	}

}
