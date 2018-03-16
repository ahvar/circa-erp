/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Interface class that checks for conflicting Activities via the checkConflict method. checks for conflict of a passed
 * activity against activities on the schedule
 * @author Ben Ioppolo
 *
 */
public interface Conflict {

	/**
	 * checks for conflicting activities
	 * @param possibleConflictingActivity activity to check for conflict with another on the schedule
	 * @throws ConflictException if a conflict is determined to exist
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
	//because part of interface, automatically public and abstract but can be left out in header. because abstract, can end in ;
	
	
	
	
	
}
