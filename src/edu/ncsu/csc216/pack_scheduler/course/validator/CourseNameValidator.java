package edu.ncsu.csc216.pack_scheduler.course.validator;

import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * The CourseNameValidator class implments the State design pattern for FSM's in order to verify that a particular course 
 * name starts with 1 to 4 alphabetic characters, which are immediately followed by 3 digits, and that ends with either 
 * the last digit or a single optional alphabetic character. The class contains several inner classes which define 
 * the States of the FSM. 
 * @author Ben W Ioppolo
 */
public class CourseNameValidator {
	
	private boolean validEndState;
	private int letterCount;
	private int digitCount;
	private int suffixLetterCount;
	/** The initial state of the FSM when no characters have been checked*/
	private final State initialState = new InitialState();
	/** The letters state of the FSM when it is checking whether at least the first and at most the 4th character is a letter */
	private final State letterState = new LetterState();
	/** The numbers state of the FSM when it is checking whether the 3 digits are present */
	private final State numberState = new NumberState();
	/** The suffix state of the FSM when it is checking whether there is 1 or no optional alphabetical characters at the end */
	private final State suffixState = new SuffixState();
	
	private State currentState = initialState;	
	
	/**
	 * The isValid is the implementing method for the FSM. It is passed a course name and loops through each character in
	 * the name and calls the appropriate method (if the character is a number, letter, or neither) of the current FSM state class.
	 * The state classes handle verification of correct letter counts and throw InvalidTransitionExceptions if the course
	 * name rules are not properly followed. Finally, if no exceptions are thrown, the letter counts are checked and the
	 * course name is determined to be valid or invalid.  
	 * @param courseName The name of the course to check for validity.
	 * @return True if the name is valid and false otherwise. 
	 * @throws InvalidTransitionException If the course naming rules are violated. Violations include 0 letters at start, more
	 * than 4 letters at start, non alphabetic or digit characters, does not have 3 digits, has more than one letter after digits.
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException{
		letterCount = 0;
		digitCount = 0;
		suffixLetterCount = 0;
		validEndState = false;
		char c;
		
		for (int i = 0 ; i < courseName.length() ; i++){
			c = courseName.charAt(i);
			if (Character.isAlphabetic(c))
				currentState.onLetter();
			else if (Character.isDigit(c))
				currentState.onDigit();
			else
				currentState.onOther();
		}
		
		if (letterCount > 0 && digitCount == 3 && suffixLetterCount <= 1)
			validEndState = true;
		
		return validEndState;
	}
	
	/**
	 * The State class is an abstract class which defines the two methods which each FSM state class must implement. These 
	 * two methods handle when a letter character is found and when a digit character is found. Additionally, the State class
	 * handles all non letter and digit characters that are contained in the course name by throwing InvalidTransitionExceptions.
	 * @author Ben W Ioppolo
	 */
	public abstract class State {
	
		/** Constructs a new State */
		public State() {
			//Intentionally left empty.
		}
		
		/**
		 * Determines what happens when an alphabetic character is found in the course name.
		 * @throws InvalidTransitionException If the course name violates the criteria described in the isValid method.
		 */
		public abstract void onLetter() throws InvalidTransitionException;
		
		/**
		 * Determines what happens when an digit character is found in the course name.
		 * @throws InvalidTransitionException If the course name violates the criteria described in the isValid method.
		 */
		public abstract void onDigit() throws InvalidTransitionException;
		
		/**
		 * Throws an InvalidTransitionException when a non digit or alphabetic character is found in the course name.
		 * @throws InvalidTransitionException If a non digit or alphabetic character is found in the course name.
		 */
		public void onOther() throws InvalidTransitionException{
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
	
	/**
	 * The LetterState class extends the State class and is one of the States of the FSM. It is operational once a letter
	 * is found at the beginning of the course name and runs until a digit is found or until there are 4 letters make up
	 * the beginning of the course name. Once a 5th letter is added, an InvalidTransitionException is thrown.
	 * @author Ben W Ioppolo
	 */
	public class LetterState extends State {

		/** The maximum number of letters that can be used to start the course name */
		private static final int MAX_PREFIX_LETTERS = 4;

		/** Constructs a new LetterState for the FSM */
		private LetterState() {
		}
		
		/**
		 * Determines if the number of letters that start the course name falls within the range of 1-4. If there are
		 * too many or none, an InvalidTransitionException is thrown. If a digit is passed any time after the 1st letter,
		 * then the number state of the FSM is activated. 
		 * @throws InvalidTransitionException If the course name violates the criteria described.
		 */
		@Override
		public void onLetter() throws InvalidTransitionException{
			if (letterCount < MAX_PREFIX_LETTERS ){
				//currentState = numberState;
				letterCount++;
			} else
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");		
		}

		/**
		 * When the first digit is found in the course name (as long as it isnt the first character in the name) the FSM state
		 * is set to the number state and the digit count is increased. 
		 */
		@Override
		public void onDigit() throws InvalidTransitionException{
				currentState = numberState;
				digitCount++;
		}
	}	
		
	/**
	 * The SuffixState class extends the State class and is one of the States of the FSM. It is operational only if a letter
	 * is after the digits (at the end of course name) If a 2nd letter is added or if a digit is added after the first letter, an
	 * InvalidTransitionException is thrown.
	 * @author Ben W Ioppolo
	 */
	public class SuffixState extends State {
		
		/** The maximum number of letters that can be present at the end of the course name */
		private static final int MAX_SUFFIX_LETTERS = 1;
		
		/** Constructs a new SuffixState for use in the FSM */
		private SuffixState() {
		}
		
		/**
		 * If there is more than 1 letter at the end of the course name an InvalidTransitionException is thrown.
		 * @throws InvalidTransitionException If there is more than 1 letter at the end of the course name.
		 */
		@Override
		public void onLetter() throws InvalidTransitionException{
			if (suffixLetterCount == MAX_SUFFIX_LETTERS)
				throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");		
		}

		/**
		 * If a number is added after the first letter of the suffix and InvalidTransitionException is thrown.
		 * @throws InvalidTransitionException If a number is added after the first letter of the suffix.
		 */
		@Override
		public void onDigit() throws InvalidTransitionException{
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}		
	}
	
	/**
	 * The InitialState class extends the State class and is one of the States of the FSM. It is operational when the program
	 * starts and checks the first passed character. If that character is a letter, the letter State is started and the letter
	 * count is incremented. Otherwise, an InvalidTransitionException is thrown.
	 * @author Ben W Ioppolo
	 */
	public class InitialState extends State {
		
		/** constructs a new Initial state for use in the FSM */
		private InitialState() {
		}

		/**
		 *checks the first passed character. If that character is a letter, the letter State is started and the letter
		 * count is incremented.
		 */
		@Override
		public void onLetter() {
			currentState = letterState;
			letterCount++;
		}

		/**
		 * If the first character that is found is a digit, a InvalidTransitionException is thrown.
		 * @throws InvalidTransitionException If the first character that is found is a digit.
		 */
		@Override
		public void onDigit() throws InvalidTransitionException{
			throw new InvalidTransitionException("Course name must start with a letter.");
		}		
	}
	
	/**
	 * The NumberState class extends the State class and is one of the States of the FSM. It is operational once a digit
	 * is found after the first 1-4 letters are found at the beginning of the course name and runs until a 3rd digit is found 
	 * or until an invalid character is found. If a 4th digit is added, an InvalidTransitionException is thrown.
	 * @author Ben W Ioppolo
	 */
	public class NumberState extends State {
		
		/** The maximum number of digits that can be present in the course name */
		private static final int COURSE_NUMBER_LENGTH = 3;
		
		/** creates a new number state for use in the FSM */
		private NumberState() {
		}

		/**
		 * If a letter is found prior to there being 3 digits, an InvalidTransitionException is thrown. Once 3 digits are found,
		 * if a letter is found, then the suffix state is started and the suffix count incremented. 
		 * @throws InvalidTransitionException if a letter is found prior to there being 3 digits. 
		 */
		@Override
		public void onLetter() throws InvalidTransitionException{
			if (digitCount < COURSE_NUMBER_LENGTH)
				throw new InvalidTransitionException("Course name must have 3 digits.");
			else {
				currentState = suffixState;
				suffixLetterCount++;
			}
		}

		/**
		 * If a digit is found the digit count is incremented as long as there has not been 3 digits found. If a 4th digit is
		 * found then a InvalidTransitionException is thrown. 
		 * @throws InvalidTransitionException If a 4th digit is found.
		 */
		@Override
		public void onDigit() throws InvalidTransitionException{
			if (digitCount < COURSE_NUMBER_LENGTH)
				digitCount++;
			else
				throw new InvalidTransitionException("Course name can only have 3 digits.");
		}		
	}	
}
