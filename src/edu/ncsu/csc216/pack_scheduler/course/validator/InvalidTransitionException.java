package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Creates a new checked exception. Can be passed a exception message string or can use the default. 
 * @author Ben Ioppolo
 */
public class InvalidTransitionException extends Exception {

		/**
		 * creates an exception with message. Calls the exception class.
		 * @param exceptionMessage the message to use for the exception 
		 */
		public InvalidTransitionException(String exceptionMessage){
			super(exceptionMessage);
		}
		
		/**
		 * creates an exception by calling the primary constructor and passing it the default exception message "Invalid FSM Transition."
		 */
		public InvalidTransitionException(){
			this("Invalid FSM Transition.");

		}
		
		/**	ID used for serialization */
		private static final long serialVersionUID = 1L;

	}

