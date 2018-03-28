/**
 * 
 */
package com.circa.mrv.grs_manager.niox;

/**
 * Creates a new checked exception. Can be passed an exception message string or can use default.
 * @author Arthur  Vargas
 */
public class ExpirationException extends RuntimeException{

	/**
	 * creates an exception with message. Calls the exception class.
	 * @param exceptionMessage the message to use for the exception 
	 */
	public ExpirationException(String exceptionMessage){
		super(exceptionMessage);
	}
	
	/**
	 * creates an exception by calling the primary constructor and passing it the default exception message 
	 * "Component Expiration."
	 */
	public ExpirationException(){
		this("Component Expiration.");

	}
	
	/**	ID used for serialization */
	private static final long serialVersionUID = 1L;

}
