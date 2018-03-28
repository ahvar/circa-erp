package com.circa.mrv.grs_manager.user;

import com.circa.mrv.grs_manager.niox.Mino;
import com.circa.mrv.grs_manager.user.schedule.MorrisvilleSchedule;

/**
 * Creates a record object for a student when provided with their pertinent information.
 * @author Ben Ioppolo

 */
public class Morrisville extends User implements Comparable<Morrisville> {

	private int maxCredits;
/**
 * static final variable MAX_CREDITS. the max credits allowed. 
 */
	public static final int MAX_CREDITS = 18;

/**
 * static final variable MIN_CREDITS. min credits allowed	
 */
	public static final int MIN_CREDITS = 3;
	
	private MorrisvilleSchedule schedule;
	
	/**
	 * Given information except the max credits field. then calls the primary constructor with given variables 
	 * and the max credits Class final variable.
	 * @param firstName students first name
	 * @param lastName students last name
	 * @param id student id
	 * @param email students email
	 * @param hashPW students password
	 */
	public Morrisville(String firstName, String lastName, String id, String email, String hashPW){
		
		this(firstName, lastName, id, email, hashPW, MAX_CREDITS); //"this" is used to call the other constructor by providing it the MAX_CREDITS variable
	}
	
	/**
	 * Primary Constructor that Calls setters for each parameter and does not handle IllegalArgumentExceptions. Creates a 
	 * new empty schedule for the user as well.
	 * @param firstName students first name
	 * @param lastName students last name
	 * @param id student id
	 * @param email student email
	 * @param hashPW student password
	 * @param maxCredits the credits the student is taking
	 */
	public Morrisville(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {

		super(firstName, lastName, id, email, hashPW);
		setMaxCredits(maxCredits);
		schedule = new MorrisvilleSchedule();
	}

	/**
	 * gets the students maxcredits
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * sets the students max credits
	 * @param maxCredits the maxCredits to set
	 *  @throws IllegalArgumentException if maxCredits is less than MIN_CREDITS or greater than MAX_CREDITS
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < MIN_CREDITS || maxCredits > MAX_CREDITS){
			String errorDescription;
			errorDescription = "Invalid max credits";
			throw new IllegalArgumentException(errorDescription);
		}
			this.maxCredits = maxCredits;
	}

	/**
	 * Returns a comma separated value string of all Student fields
	 * @return String representation of Student
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + maxCredits;
	}
	
	/**
	 * Compares this student to the passed student by comparing a combined string of lastname firstname and id. the 
	 * compareTo method in comparable is called to compare the combined string.
	 */
	@Override
	public int compareTo(Morrisville s) {
		int compareResult;
		String thisStudent = new String(this.getLastName() + this.getFirstName() + this.getId());
		String studentS = new String(s.getLastName() + s.getFirstName() + s.getId());	
		
		compareResult = thisStudent.compareTo(studentS);
		
		return compareResult;
	}

	/**
	 * Generates a hashcode for student using the maxCredits field. 
	 * @return result the hashcode for student
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	/**
	 * compares a given object to this object for equality on all fields. 
	 * @param obj the object to compare
	 * @return true if the objects are the same on all fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Morrisville other = (Morrisville) obj;
		if (maxCredits != other.maxCredits)
			return false;
		return true;
	}

	/**
	 * Gets the student's schedule which contains the courses they have selected.
	 * @return schedule. The schedule of the student's courses.
	 */
	public MorrisvilleSchedule getSchedule() {
		return schedule;
	}
	
	/**
	 * Checks whether the student can add a course to their schedule. Uses schedule.canAdd method to perform some of the checks.
	 * The check not performed by schedule.canAdd that is performed is whether the total credits the student is already
	 * registered for is greater than their maxCredits that they are allowed to register for. 
	 * @param c The course that is being considered for adding to the schedule.
	 * @return True if the student can add the course. False otherwise. 
	 */
	public boolean canAdd(Mino c){
		if (!schedule.canAdd(c) || schedule.getScheduleCredits() + c.getCredits() > maxCredits)
			return false;

		return true;
	}
}
