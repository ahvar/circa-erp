package com.circa.mrv.grs_manager.util;


/**
 * custom implementation of a recursive linked list that doesn't allow for null elements or duplicate 
 * elements as defined by the equals() method.
 * @author Arthur Vargas
 * @param <E> generic type parameter
 */
public class LinkedListRecursive<E> {
	
	/* node pointing to the front of the list */
	private ListNode front;
	/* size of list */ 
	private int size;
	
	/**
	 * The constructor of LinkedListRecursive initializes the state to represent an empty list.
	 */
	public LinkedListRecursive(){
		front = null;
		size = 0;
	}
	
	/**
	 * Checks to see if the list is empty by checking if size is 0. 
	 * @return true if the list is empty false otherwise.
	 */
	public boolean isEmpty(){
		if (size == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the number of items stored in the list.
	 * @return The number of items stored in the list.
	 */
	public int size(){
		return size;
	}
	
	/**
	 * Attempts to add the passed element to the end of the list. If the list already contains the item, 
	 * an IllegalArgumentException is thrown. If the list is empty, this method adds the item. Otherwise, 
	 * this method calls the ListNode.Add E method which recursively finds the end of the list and adds the 
	 * passed element there. 
	 * @param element The element to try adding to the list.
	 * @return true if the element is added to the list. false otherwise. 
	 * @throws IllegalArgumentException if the passed element already exists in the list. 
	 */
	public boolean add(E element){
		System.out.println("enter add");
		if (element == null){
			System.out.println("element false");
			return false;
		}
		if(contains(element))
			throw new IllegalArgumentException("Element already exists");
		if(size == 0){
			System.out.println("enter add condition");
			front = new ListNode(element, front);
			size++;
			System.out.println("size increment");
			return true;
		}
		front.add(element);
		return true;
	}
	
	/**
	 * Adds the passed element data to the specified index location in the list. If the element is null, the element already exists
	 * in the list, or if the index is out of bounds, exceptions are thrown. Additionally, if the size of the list is 0 this method
	 * adds the element to the list. Otherwise, this methods calls the ListNode.Add(int, E) method which traverses the list recursively
	 * and adds the element at the appropriate location. 
	 * @param index The location in the list to add the item.
	 * @param element The data for the new item to add to the list. 
	 * @throws NullPointerException if the passed element is null
	 * @throws IllegalArgumentException if the element already exists in the list
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than the size of the list. 
	 */
	public void add(int index, E element){
		if(element == null)
			throw new NullPointerException("Element is null");
		if(contains(element))
			throw new IllegalArgumentException("Element already exists");
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException("Invalid index");
		if(size == 0){
			front = new ListNode(element, front);
			size++;
		} else {
			front.add(index, element);
		}
	}
	
	/**
	 * Gets the item in the list located at the specifed index. If the index is out of bounds, an exception is thrown. 
	 * This methods calls the ListNode.get(int) method which traverses the list recursively and adds the element at the 
	 * appropriate location. 
	 * @param index The index of the item to retrieve from the list.
	 * @return The data that is stored at the index location in the list. 
	 * @throws IndexOutOfBoundsException if the passed index is less than 0 or greater than the size of the list. 
	 */
	public E get(int index){
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Invalid index");
		
		return front.get(index);
	}
	
	/**
	 * Removes the specified element from the list if it exists in the list and returns true if it is removed and false otherwise. 
	 * This method can remove the first item from the list if it matches the passed element otherwise it calls the ListNode.remove(E)
	 * method to recursively traverse the list and remove the item if a match is found. 
	 * @param element The element to remove if it matches an item in the list
	 * @return true if the item is removed and false otherwise. 
	 */
	public boolean remove(E element){
		if (element == null || size == 0)
			return false;
		if(front.data.equals(element)){
			front = front.next;
			size--;
			return true;
		}
		return front.remove(element);
	}
	
	/**
	 * Removes the item from the list that is located at the specified index. If the index is out of bounds an exception is thrown. 
	 * If there is only 1 item in the list, this method removes the item and returns the item that was removed. Otherwise, this
	 * method calls the ListNode.remove(int) method which recursively traverses the list to find the item to remove at the index. 
	 * @param index The index of the item to remove from the list
	 * @return The item that was removed from the list
	 * @throws IndexOutOfBoundsException if the passed index is less than 0 or greater than the size of the list. 
	 */
	public E remove(int index){
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Invalid index");
		if(index == 0){
			E temp = front.data;
			front = front.next;
			size--;
			return temp;
		}
		return front.remove(index);
	}
	
	/**
	 * Writes the passed element data into the list item located at the passed index location. If the index is out of bounds, an
	 * exception is thrown. The actual set process is done by ListNode.set method which recursively traverses the list to find the 
	 * index location.
	 * @param index The location in the list to write the passed data into.
	 * @param element The data to write into the passed location in the list. 
	 * @return The item that was in the list location prior to being overwritten. 
	 * @throws IndexOutOfBoundsException if the passed index is less than 0 or greater than the list size. 
	 */
	public E set(int index, E element){
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Invalid index");
		if(element == null)
			throw new NullPointerException("Element is null");
		if(contains(element))
			throw new IllegalArgumentException("Element already exists");
		
		return front.set(index, element);
	}
	
	/**
	 * Checks if the list contains the passed element. If the list is null or empty then false is returned. 
	 * Otherwise, the ListNode.contains method is called which recursively checks the list for a match.
	 * @param element The element to check for existence in the list. 
	 * @return true if the element is in the list and false otherwise.
	 */
	public boolean contains(E element){
		if (front == null || size == 0 || element == null){
			return false;
		}
		
		return front.contains(element);
	}
	
	/**
	 * Tests if this list is equal to the parameter object
	 * @param obj the object to test for equality
	 * @return true if they are equal
	 */
	@Override
	public boolean equals(Object obj) {
		for(int i = this.size(); this.size() != 0; i--) {
			if(!this.get(i).equals(obj)) return false;
		}
		return true;
	}
	
	/**
	 *  Class which provides the methods and data for creating new nodes in a linked list 
	 * @author Ben W Ioppolo
	 */
	private class ListNode {
		
		public E data;
		public ListNode next;
		
		/**
		 * Constructs nodes that have data and a reference to the next list item. 
		 * @param data The information stored at a list item. 
		 * @param next The reference to the next list item which is stored at the prior list item. 
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
		
		/**
		 * Adds the passed element to the end of the list by recursively going through the list until reaching
		 * the end at which point the element is added. 
		 * 
		 * @param element The new item to add to the list. 
		 */
		public void add(E element){
			if(next == null){
				next = new ListNode(element, next);
				size++;
			} else
				next.add(element);
		}
		
		/**
		 * Adds the passed element to the list at the passed index location by recursively going through the list until reaching the
		 * the item prior to the index location where the item should be added. 
		 * @param index The location to add the new item
		 * @param element The data item to store in the list at the specified location.
		 */
		public void add(int index, E element){
			if (index == 0){
				front = new ListNode(element, front);
				size++;
			} else if (index - 1 == 0){
				next = new ListNode(element, next);
				size++;
			} else{
				next.add(index - 1, element);
			}
		}
		
		/**
		 * Gets the item located at the passed index location and returns it. 
		 * @param index The location in the list to get the item from.
		 * @return The item location at the passed index location in the list.
		 */
		public E get(int index){
			if(index == 0)
				return data;
			return next.get(index - 1);
		}
		
		/**
		 * Removes the passed element from the list if it matches an item in the list. The list is traversed recusively while
		 * checking for matches to the passed element. If an item is removed true is returned. 
		 * @param element The element to remove from the list if a match is found.
		 * @return true if an item is removed and false otherwise. 
		 */
		public boolean remove(E element){
		if (next != null) {
			if (next.data.equals(element)) {
				next = next.next;
				size--;
				return true;
			} else {
				return next.remove(element);
			}
		}
		    return false;
		}
		
		
		/**
		 * Removes the item from the list that is located at the specified index. This method recursively traverses the list 
		 * to find the item to remove at the index. 
		 * @param index The index of the item to remove from the list
		 * @return The item that was removed from the list
		 */
		public E remove(int index){
			if (index - 1 == 0){
				E temp = next.data;
				next = next.next;
				size--;
				return temp;
			}
			return next.remove(index - 1);
		}
		
		/**
		 * Overwrites the data for the passed element to the item located at the passed index. 
		 * @param index The index of the item in the list to overwrite data for. 
		 * @param element The data to write into the list at the passed index location. 
		 * @return
		 */
		public E set(int index, E element){
			if(index == 0){
				E temp = data;
				data = element;
				return temp;
			}
			return next.set(index - 1, element);
		}
		
		/**
		 * Recursively checks the list for a match. If a match to the passed element is found at the current list
		 * item, then true is returned. Otherwise, the method recurisvely calls itself with the next list item. 
		 * @param element The element to check for existence in the list. 
		 * @return true if the element is in the list and false otherwise.
		 */
		public boolean contains(E element){
			if(data == null || next == null)
				return false;
			if(data.equals(element)){
				return true;
			}
			
			return next.contains(element);
		}
	}
}

