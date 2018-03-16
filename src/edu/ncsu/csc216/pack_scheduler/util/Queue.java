package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Generic interface for implementing a Queue list. States the methods needed to define for a Queue.
 * @author Ben W Ioppolo
 * @param <E> Generic type parameter. 
 */
public interface Queue<E> {
	
	/**
	 * Adds the element to the back of the Queue. If there is no room (capacity has been reached), 
	 * an IllegalArgumentException is thrown
	 * @param element The element to add to the queue
	 * @throws IllegalArgumentException if the capacity of the queue has been reached.
	 */
	public void enqueue (E element);
	
	/**
	 * Removes and returns the element at the front of the Queue. If the Queue is empty, 
	 * an NoSuchElementException is thrown.
	 * @return The element at the front of the queue.
	 * @throws NoSuchElementException if the queue is empty.
	 */
	public E dequeue();
	
	/**
	 * Returns true if the Queue is empty
	 * @return True if the queue is empty and false otherwise.
	 */
	public boolean isEmpty();
	
	/**
	 * Returns the number of elements in the Queue
	 * @return The number of elements in the Queue
	 */
	public int size();
	
	/**
	 * Sets the Queue's capacity. If the actual parameter is negative or if it is less than the number of 
	 * elements in the Queue, an 1IllegalArgumentException is thrown
	 * @param capacity The value to set as the queue's capacity
	 * @throws IllegalArgumentException if the passed capacity is less than the queue's size
	 */
	public void setCapacity(int capacity);
}
