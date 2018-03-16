/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;
import java.util.EmptyStackException;

/**
 * The Stack interface defines the behaviors to be implemented by various list classes in the PackScheduler.
 * @param <E> the generic element
 * @author Arthur Vargas
 */
public interface Stack<E> {
	
	/**
	 * Adds the element parameter to the top of the stack. If there is no available capacity, an IllegalArgumentException is thrown.
	 * @param element the element to add to the stack
	 * @throws IllegalArgumentException the exception thrown if there is no capacity
	 */
	public void push(E element);
	
	/**
	 * Removes and returns the element at the top of the stack. If the stack is empty, an EmptyStackException is thrown
	 * @return E the element to return
	 * @throws EmptyStackException the exception thrown if the stack is empty
	 */
	public E pop();

	/**
	 * Returns true if the stack is empty.
	 * @return boolean true if the stack is empty, false otherwise.
	 */
	public boolean isEmpty();
	
	/**
	 * Returns the number of elements in the stack
	 * @return int the number of elements in the stack
	 */
	public int size();
	
	/**
	 * Sets the capacity of the stack to the c parameter. If the parameter is less than 0 or if it is less than the number of
	 * elements in the stack, then an IllegalArgumentException is thrown.
	 * @param c the capacity to set
	 * @throws IllegalArgumentException the exception to throw if c is invalid
	 */
	public void setCapacity(int c);
}
