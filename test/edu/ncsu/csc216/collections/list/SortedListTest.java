package edu.ncsu.csc216.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Provides a number of tests to assess the functionality of the SortedList class
 * @author Ben
 *
 */
public class SortedListTest {

	/**
	 * Tests the constructor for the SortedList Class. verifies that a list is created properly, and that it changes length
	 * correctly when items are added or removed.
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		assertFalse(list.contains("apple"));
		
		//Tests that the list grows when more items than the default 10 are added.
		for (int i = 0 ; i < 11 ; i++){//add 11 elements to the list
			list.add("Element " + i);
		}
		assertEquals(11, list.size());		
	}

	/**
	 * Tests that adding valid items to various places in the list are added correctly and that adding invalid items to the
	 * list are also handled properly. Also tests that the list ordering is correct after an incorrect or correct add call.
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		//add item to front
		list.add("alpha");
		assertEquals(2, list.size());
		assertEquals("alpha", list.get(0));
		assertEquals("banana", list.get(1));
		//add item to middle
		list.add("ball");
		assertEquals(3, list.size());
		assertEquals("alpha", list.get(0));
		assertEquals("ball", list.get(1));
		assertEquals("banana", list.get(2));
		//add item to end
		list.add("zebra");
		assertEquals(4, list.size());
		assertEquals("alpha", list.get(0));
		assertEquals("ball", list.get(1));
		assertEquals("banana", list.get(2));
		assertEquals("zebra", list.get(3));
		//add duplicate element
		try{
			list.add("ball");
			fail();
		} catch (IllegalArgumentException e){
			assertEquals(4, list.size());
			assertEquals("alpha", list.get(0));
			assertEquals("ball", list.get(1));
			assertEquals("banana", list.get(2));
			assertEquals("zebra", list.get(3));		
		}
		//add null element
		try{
			list.add(null);
			fail();
		} catch (NullPointerException e){
			assertEquals(4, list.size());
			assertEquals("alpha", list.get(0));
			assertEquals("ball", list.get(1));
			assertEquals("banana", list.get(2));
			assertEquals("zebra", list.get(3));
		}
		//add empty element
		list.add("");
		assertEquals(5, list.size());
		assertEquals("", list.get(0));		
		assertEquals("alpha", list.get(1));
		assertEquals("ball", list.get(2));
		assertEquals("banana", list.get(3));
		assertEquals("zebra", list.get(4));
	}
	
	/**
	 * Tests the error and boundary cases for the get method since the other tests provided in this Class will test
	 * the get method on valid calls. Ensures that valid list items and positions are maintained.
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//test get element from empty list
		try{
			list.get(0);
			fail();
		} catch (IndexOutOfBoundsException e){
			assertEquals(0, list.size());
		}
		//add elements
		list.add("banana");
		list.add("zebra");
		list.add("alpha");
		//get element -1
		try{
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e){
			assertEquals(3, list.size());
			assertEquals("alpha", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("zebra", list.get(2));
		}
		//get element at index = size
		try{
			list.get(list.size());
			fail();
		} catch (IndexOutOfBoundsException e){
			assertEquals(3, list.size());
			assertEquals("alpha", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("zebra", list.get(2));
		}
	}
	
	/**
	 * Tests removing items from various positions within a valid list and also tests removing them from empty lists 
	 * or using invalid indices. also verifies that the list items are in the proper order and at correct indices
	 * after the remove process has been completed.
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//remove item from empty list
		try{
			list.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e){
			assertEquals(0, list.size());
		}
		//add 1 item to the list
		list.add("banana");
		//remove only item from list
		list.remove(0);
		assertEquals(0, list.size());
		//add multiple items to list
		list.add("banana");
		list.add("zebra");
		list.add("alpha");
		list.add("ball");
		list.add("");
		//remove item from index less than 0
		try{
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e){
			assertEquals(5, list.size());
			assertEquals("", list.get(0));		
			assertEquals("alpha", list.get(1));
			assertEquals("ball", list.get(2));
			assertEquals("banana", list.get(3));
			assertEquals("zebra", list.get(4));
		}
		//remove last item from list
		list.remove(list.size() - 1);
		assertEquals(4, list.size());
		assertEquals("", list.get(0));		
		assertEquals("alpha", list.get(1));
		assertEquals("ball", list.get(2));
		assertEquals("banana", list.get(3));
		//remove first item from list
		list.remove(0);
		assertEquals(3, list.size());		
		assertEquals("alpha", list.get(0));
		assertEquals("ball", list.get(1));
		assertEquals("banana", list.get(2));	
		//remove middle item from list
		list.remove(1);
		assertEquals(2, list.size());		
		assertEquals("alpha", list.get(0));
		assertEquals("banana", list.get(1));	
		//remove item at index = size
		try{
			list.remove(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, list.size());		
			assertEquals("alpha", list.get(0));
			assertEquals("banana", list.get(1));
		}
	}
	
	/**
	 * Tests that the indexOf method returns the correct indices for valid list elements and also tests items not present
	 * on the list and on empty or null lists. 
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//Test indexOf on an empty list
		assertEquals(-1, list.indexOf("alpha"));
		//add multiple items to list
		list.add("banana");
		list.add("zebra");
		list.add("alpha");
		list.add("ball");
		list.add("");
		//Test various calls to indexOf for elements in the list
		assertEquals(0, list.indexOf(""));
		assertEquals(1, list.indexOf("alpha"));
		assertEquals(2, list.indexOf("ball"));
		assertEquals(3, list.indexOf("banana"));
		assertEquals(4, list.indexOf("zebra"));
		//and not in the list
		assertEquals(-1, list.indexOf("not on list"));
		//Test checking the index of null
		try{
			list.indexOf(null);
			fail();
		} catch (NullPointerException e){
			assertEquals(0, list.indexOf(""));
			assertEquals(1, list.indexOf("alpha"));
			assertEquals(2, list.indexOf("ball"));
			assertEquals(3, list.indexOf("banana"));
			assertEquals(4, list.indexOf("zebra"));
		}
	}
	
	/**
	 * Tests that a previously empty or populated list is empty after being cleared. 
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();
		
		//Test that the list is empty
		assertTrue(list.isEmpty());
		//add multiple items to list
		list.add("banana");
		list.add("zebra");
		list.add("alpha");
		list.add("ball");
		list.add("");
		//Clear the list
		list.clear();
		//Test that the list is empty
		assertTrue(list.isEmpty());
	}

	/**
	 * Tests that an empty list is empty and that a non empty list is not empty.
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//Test that the list is empty
		assertTrue(list.isEmpty());
		//add multiple items to list
		list.add("banana");
		list.add("zebra");
		list.add("alpha");
		list.add("ball");
		list.add("");
		//Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}

	/**
	 * Tests that a list contains various elements that it doesnt contain various elements and how the list responds when
	 * it is empty.
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//Test the empty list case
		assertFalse(list.contains("string"));
		//add multiple items to list
		list.add("banana");
		list.add("zebra");
		list.add("alpha");
		list.add("ball");
		list.add("");
		//Test some true and false cases
		assertTrue(list.contains("banana"));
		assertTrue(list.contains("alpha"));
		assertTrue(list.contains(""));
		assertTrue(list.contains("zebra"));
		assertTrue(list.contains("ball"));
		assertFalse(list.contains("not in list"));
	}
	
	/**
	 * Tests that two lists are equal to eachother or not equal to eachother based on the items they contain.
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//Make two lists the same and one list different
		list1.add("banana");
		list1.add("zebra");
		list1.add("alpha");
		list1.add("ball");
		list1.add("");
		list2.add("banana");
		list2.add("zebra");
		list2.add("alpha");
		list2.add("ball");
		list2.add("");
		list3.add("ban");
		list3.add("zeb");
		list3.add("alp");
		list3.add("bal");
		list3.add("sdf");
		//Test for equality in both directions
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));
		//and non-equality in both directions
		assertFalse(list1.equals(list3));
		assertFalse(list2.equals(list3));
		assertFalse(list3.equals(list2));
		assertFalse(list3.equals(list2));
		//test equal to self
		assertTrue(list1.equals(list1));
	}
	
	/**
	 * Tests that two list's hashcodes are equal to eachother or not equal. 
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//Make two lists the same and one list different
		list1.add("banana");
		list1.add("zebra");
		list1.add("alpha");
		list1.add("ball");
		list1.add("");
		list2.add("banana");
		list2.add("zebra");
		list2.add("alpha");
		list2.add("ball");
		list2.add("");
		list3.add("ban");
		list3.add("zeb");
		list3.add("alp");
		list3.add("bal");
		list3.add("sdf");
		//Test for equality in both directions
		assertEquals(list1.hashCode(), list2.hashCode());
		assertEquals(list2.hashCode(), list1.hashCode());
		//and non-equality in both directions
		assertNotEquals(list1.hashCode(), list3.hashCode());
		assertNotEquals(list2.hashCode(), list3.hashCode());
		assertNotEquals(list3.hashCode(), list2.hashCode());
		assertNotEquals(list3.hashCode(), list2.hashCode());
		//test equal to self
		assertEquals(list1.hashCode(), list1.hashCode());
	}

}
 