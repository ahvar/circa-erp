/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Defines the state and behavior for a list of nodes. Includes add, remove, get, and set operations.
 * @author Arthur Vargas
 * @param <E> Generic type argument for the list.
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	/** A ListNode of type E referencing the front of the list */
	private ListNode<E> front;
	/** A ListNode of type E referencing the back of the list */
	private ListNode<E> back;
	/** The size of the list */
	private int size;
	/** The capacity of the list */
	private int capacity;
	
	/**
	 * Constructs LinkedAbstractList by initializing the front to null, the size to 0, and capacity per the 
	 * parameter value if the value is greater than 1. If the parameter value is less than 0, an IllegalArgumentException
	 * is thrown.
	 * @param c the capacity of the list
	 */
	public LinkedAbstractList(int c) {
		if (c < 0) {
			throw new IllegalArgumentException();
		}
		
		this.front = null;
		this.size = 0;
		this.capacity = c;
	}
	
	/**
	 * Adds an element to the list. The list must have capacity for additional elements.
	 * @param index the location of the element
	 * @param element the element to be added
	 * @throws IllegalArgumentException if the list is full
	 * @throws NullPointerException if element is null
	 * @throws IndexOutOfBoundsException if index is out of range
	 */
	@Override
	public void add(int index, E element) {
		
		if (size == capacity) {
			throw new IllegalArgumentException("size equals capacity");
		}
		
		if (element == null) {
			throw new NullPointerException("element is null");
		}
		
		 if (index < 0 || index > size()) {
			 throw new IndexOutOfBoundsException("index out of bounds");
		 }
		//duplicate checker
		ListNode<E> current = front;
		while (current != null) {
			if (current.data.equals(element)) {
				throw new IllegalArgumentException("Duplicate element.");
			}
			current = current.next;
		}
		 ListNode<E> previous  = null;
		//if adding to front i.e. index == 0
		if (index == 0) {
			front = new ListNode<E>(element, front); //insert new node at front and switch head reference to it
			size++;
			if(size == 1){
				back = front;
			}
		}
			//adding to back.
		else if (index == size){
			previous = back;	
			back = new ListNode<E>(element, back.next); //insert new node at back and switch back reference to it
			size++;
			previous.next = back;
		}
		 else {
			 //searching for index and moving pointers
			 current = front;

			 while (current != null && index > 0) {
				 previous = current;
				 current = current.next;
				 index--;
			 } 
			 previous.next = new ListNode<E>(element, current);
			 size++;
		}
	}
	
//	/**
//	 * Insert new data into the front of the list
//	 * @param data the data to insert into the list
//	 */
//	private void insertAtFront(E data) {
//		ListNode<E> newHead = new ListNode<E>(data, front);
//		front = newHead;
//		size++;
//	}
//	
//	/**
//	 * Insert new data at the end of the list
//	 * @param data the data to insert into the list
//	 */
//	private void insertAtEnd(E data) {
//		ListNode<E> current = front;
//		while (current.next != null) {
//			current = current.next;
//			
//		}
//		current.next = new ListNode<E>(data);
//		size++;
//	}
	
	/**
	 * Removes the element located at the parameter specified index. If the index does not correspond to an element 
	 * location in the list, and IndexOutOfBoundsException is thrown.
	 * @param index The index of the element to return.
	 * @return removedItem The element which was removed at the passed index location.
	 */
	@Override
	public E remove(int index) {
		E e;
		if (index < 0 || index >= size()) {
			 throw new IndexOutOfBoundsException();
		 }
		
		ListNode<E> current = front;
		ListNode<E> previous = null;
		while (current != null && index > 0) {
			previous = current;
			current = current.next;
			index--;
		}
		if (current != null) {
			if (current == front) { // current points to front, located at index = 0
				e = current.data; // put the element in e
				front = front.next; // remove
				size--; // decrease size
				if (size == 0)
					back = front;
				
			 } else {
				 e = current.data; // current points to ListNode to be removed
				 if (current.next == null)
					 back = previous;
				 previous.next = current.next; // removes ListNode 
				 size--; // decrease size
			 }
			return e;
		 }
		return null;
		
	}
	
	/**
	 * The element at the specified index is replaced with the given element. If the passed index  is less than 0 or greater
	 * than the size of the list, if the passed element is null, or if the passed element already exists on the list,
	 * exceptions are thrown. 
	 * @param index The location of the element to overwrite with the parameter element.
	 * @param element The element to write into the list at the specified index.
	 * @return replacedItem the item that was replaced by the element. 
	 * @throws IndexOutOfBoundsException If the passed index is less than 0 or larger than the size of the list.
	 * @throws NullPointerException If the passed element is null.
	 * @throws IllegalArgumentException If the passed element already exists on the list.
	 */
	@Override
	public E set(int index, E element) {
		E e = null;
		 if (index < 0 || index > size() ) {
			 throw new IndexOutOfBoundsException();
		 }
		 if (element == null){
			 throw new NullPointerException();
		 }
		 ListNode<E> current = front;
		 while (current != null) {
			if (current.data.equals(element)) {
				throw new IllegalArgumentException("Duplicate element.");
			}
			current = current.next;
		}
		
		if (index == 0) {
			if (front != null) {
				e = front.data;
				front.data = element;
			} else {
				 throw new IndexOutOfBoundsException();
			}
		}	
		if (front != null && index > 0) {
			ListNode<E> traveler = front;
			//ListNode<E> previous = null;
			while (traveler != null && index > 1) {
				//previous = traveler;
				traveler = traveler.next;
				index--;
			}
			if (traveler != null) {
				if (index == 1) {
					traveler = traveler.next; // traveler points to item at 1
					e = traveler.data; // e is assigned the data to be removed
					traveler.data = element; // the parameter element replaces the data at this position
				} else {  // not removing item at 1
					e = traveler.data;
					traveler.data = element;
				}
			} 
//			else {
//				traveler = new ListNode<E>(element);
//			}
		}
		
		return e;
	}
	
	/**
	 * Returns the current number of elements in the list
	 * @return size the number representing the current number of elements in the list
	 */
	public int size() {
//		int count = 0;
//		ListNode<E> current = front;
//		while (current != null) {
//			current = current.next;
//			count++;
//		}
		return size;
	}
	
	/**
	 * Returns the element of the ListNode at the given index in the list
	 * @return element the element of the ListNode at the index parameter
	 */
	@Override
	public E get(int index) {
		
		if (index < 0 || index >= size()) {
			 throw new IndexOutOfBoundsException();
		 }
		
		ListNode<E> current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.data;
	}
	
	/**
	 * Sets the capacity to the integer parameter. Throws an IllegalArgumentException if parameter c is less than 0 or less than
	 * the current size.
	 * @param c the capacity to set
	 * @throws IllegalArgumentException the exception to throw if c parameter is invalid
	 */
	public void setCapacity(int c) {
		if (c < 0 || c < size) {
			throw new IllegalArgumentException();
		}
		
		this.capacity = c;
	}
	
	/**
	 * Defines the state and behavior for an individual ListNode. ListNode contains a data attribute and a 
	 * reference to the next node in the list.
	 * @param <T> generic type parameter
	 */
	private class ListNode<T> {
		/** The data object */
		private E data;
		/** The next node in the list */
		private ListNode<T> next;
		
//		/**
//		 * Constructs a ListNode with parameter specified T
//		 * @param data the data element of this node
//		 */
//		public ListNode(E data) {
//			this.data = data;
//			this.next = null;
//		}
		
		/**
		 * Constructs a ListNode with parameter specified T and next
		 * @param data the data element of this node
		 * @param next a reference to the next node in the list
		 */
		public ListNode (E data, ListNode<T> next) {
			this.data = data;
			this.next = next;
		}
	}

}
