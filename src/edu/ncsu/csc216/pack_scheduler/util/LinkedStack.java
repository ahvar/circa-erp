/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Creates a stack based on a LinkedList and provides functions for pushing to the top of the stack, popping the 
 * top item off the stack, checking if the stack is empty, getting the number of items on the stack, and
 * setting the stack's capacity.
 * @author Arthur Vargas
 * @author Ben Ioppolo
 * @param <E> Generic type parameter
 */
public class LinkedStack<E> implements Stack<E> {

	/** LinkedList of objects */
	private LinkedAbstractList<E> stack;
	private int stackCapacity = 10;
	
	/**
	 * Constructor that creates an empty stack
	 * @param capacity the capacity of the stack
	 */
	public LinkedStack(int capacity){
		//setCapacity(capacity);
		stack = new LinkedAbstractList<>(capacity);
	}
	
	/**
	 * Adds the element to the "top" (the front of stack) by delegating to LinkedAbstractList.add
	 * @param element the element to push
	 */
	@Override
	public void push(E element) {
		if (stack.size() == stackCapacity){
			throw new IllegalArgumentException("Capacity has been reached");
		}
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
		stack.setCapacity(c);
		stackCapacity = c;
	}
}
