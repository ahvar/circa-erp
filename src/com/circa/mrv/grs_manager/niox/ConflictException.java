/**
 * 
 */
package com.circa.mrv.grs_manager.niox;

/**
 * Creates a new checked exception. Can be passed a exception message string or can use the default. 
 * @author Ben IOppolo
 *
 */
public class ConflictException extends RuntimeException {

	/**
	 * creates an exception with message. Calls the exception class.
	 * @param exceptionMessage the message to use for the exception 
	 */
	public ConflictException(String exceptionMessage){
		super(exceptionMessage);
	}
	
	/**
	 * creates an exception by calling the primary constructor and passing it the default exception message "Schedule Conflict."
	 */
	public ConflictException(){
		this("Schedule conflict.");

	}
	
	/**	ID used for serialization */
	private static final long serialVersionUID = 1L;

}
