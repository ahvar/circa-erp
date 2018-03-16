/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Creates an ArrayList based Stack and provides functions for pushing to the top of the stack, popping the 
 * top item off the stack, checking if the stack is empty, getting the number of items on the stack, and
 * setting the stack's capacity.
 * @author Arthur Varags
 * @author Ben Ioppolo
 * @param <E> Generic type parameter
 */
public class ArrayStack<E> implements Stack<E> {
	
	/** ArrayList of objects */
	private ArrayList<E> stack;
	private int stackCapacity = 10;
	
	/**
	 * Constructor that creates an empty stack
	 * @param capacity the capacity of the stack
	 */
	public ArrayStack(int capacity){
		stack = new ArrayList<E>();
		setCapacity(capacity);
	}
	
	/**
	 * Adds the element to the "top" (the front of arrayList) by delegating to arraylist.add
	 * @param element the element to push
	 */
	@Override
	public void push(E element) {
		if (stackCapacity == stack.size())
			throw new IllegalArgumentException("Capacity has been reached");
		stack.add(0, element);
	}

	/**
	 * Pops the top item off the stack. 
	 * @return The top item or null if the stack is empty
	 */
	@Override
	public E pop() {
		if (stack.isEmpty())
			throw new EmptyStackException();
		return stack.remove(0);
	}

	/**
	 * Checks to see if there are no items stored in the stack.
	 */
	@Override
	public boolean isEmpty() {
		if (stack.size() == 0)
			return true;
		return false;
	}

	/**
	 * Gets the number of items currently stored in the stack.
	 */
	@Override
	public int size() {
		return stack.size();
	}

	/**
	 * Sets the maximum capacity of the stack.
	 */
	@Override
	public void setCapacity(int c) {
		if (c < stack.size())
			throw new IllegalArgumentException();
		stackCapacity = c;
	}

}
