/**
 * 
 */
package com.circa.mrv.grs_manager.niox;

/**
 * Interface class that checks for conflicting Activities via the checkConflict method. checks for conflict of a passed
 * activity against activities on the schedule.
 * @author Arthur Vargas
 *
 */
public interface Conflict {

	/**
	 * checks for conflicting activities
	 * @param possibleConflictingActivity activity to check for conflict with another on the schedule
	 * @throws ConflictException if a conflict is determined to exist
	 */
	void checkConflict(Product possibleConflictingActivity) throws ConflictException;
	//because part of interface, automatically public and abstract but can be left out in header. because abstract, can end in ;
	
	
	
	
	
}
