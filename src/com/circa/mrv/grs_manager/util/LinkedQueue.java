package com.circa.mrv.grs_manager.util;

import java.util.NoSuchElementException;

/**
 * Implements a Queue on an LinkedAbstractlist and provides functions for adding items to the back of the queue, 
 * removing items from the front of the queue, checking if the queue is empty, getting the number of items in 
 * the queue, and setting the queue's capacity.
 * @author Ben W Ioppolo
 * @param <E> Generic type parameter. 
 */
public class LinkedQueue<E> implements Queue<E> {

	/** LinkedList of objects */
	private LinkedAbstractList<E> queue;
	private int queueCapacity = 10;
	
	/**
	 * Creates a new Queue based on a LinkedAbstractList
	 * @param capacity the capacity of the queue
	 */
	public LinkedQueue(int capacity){
		//setCapacity(capacity);
		queue = new LinkedAbstractList<E>(capacity);
		
	}
	
	/**
	 * Adds the element to the back of the Queue. If there is no room (capacity has been reached), 
	 * an IllegalArgumentException is thrown
	 * @param element The element to add to the queue
	 * @throws IllegalArgumentException if the capacity of the queue has been reached.
	 */
	@Override
	public void enqueue(E element) {
		if (queue.size() == queueCapacity)
			throw new IllegalArgumentException("Capacity has been reached");
		queue.add(queue.size(), element);
	}

	/**
	 * Removes and returns the element at the front of the Queue. If the Queue is empty, 
	 * an NoSuchElementException is thrown.
	 * @return The element at the front of the queue.
	 * @throws NoSuchElementException if the queue is empty.
	 */
	@Override
	public E dequeue() {
		if (queue.size() == 0)
			throw new NoSuchElementException("Queue is empty");
		return queue.remove(0);
	}

	/**
	 * Returns true if the Queue is empty
	 * @return True if the queue is empty and false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		if (queue.size() == 0)
			return true;
		return false;
	}

	/**
	 * Returns the number of elements in the Queue
	 * @return The number of elements in the Queue
	 */
	@Override
	public int size() {
		return queue.size();
	}

	/**
	 * Sets the Queue's capacity. If the actual parameter is negative or if it is less than the number of 
	 * elements in the Queue, an 1IllegalArgumentException is thrown
	 * @param capacity The value to set as the queue's capacity
	 * @throws IllegalArgumentException if the passed capacity is less than the queue's size
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < queue.size())
			throw new IllegalArgumentException();
		queueCapacity = capacity;
		queue.setCapacity(queueCapacity);
	}
}
