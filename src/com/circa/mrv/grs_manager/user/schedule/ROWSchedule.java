package com.circa.mrv.grs_manager.user.schedule;

import com.circa.mrv.grs_manager.niox.ConflictException;
import com.circa.mrv.grs_manager.niox.Mino;
import com.circa.mrv.grs_manager.util.LinkedListRecursive;

/**
 * The ROW (rest of world) order schedule.
 * @author Sarah Heckman
 */
public class ROWSchedule {

	/** Schedule of courses with no cap */
	private LinkedListRecursive<Mino> schedule;
	/** Instructor id for updating courses */
	private String instructorId;
	
	/**
	 * Creates an empty schedule.
	 * @param instructorId faculty's id for updating Course
	 */
	public ROWSchedule(String instructorId) {
		schedule = new LinkedListRecursive<Mino>();
		this.instructorId = instructorId;
	}
	
	/**
	 * Adds a course to the schedule.
	 * @param course Course to add to schedule
	 * @return true if added
	 */
	public boolean addCourseToSchedule(Mino course) {
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(course)) {
				throw new IllegalArgumentException("Already assigned " + course.getName());
			}
			try {
				schedule.get(i).checkConflict(course);
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be assigned due to a conflict.");
			}
		}
		if (course.getInstructorId() != null) {
			throw new IllegalArgumentException("The course already has an instructor.");
		}
		if (schedule.add(course)) {
			course.setInstructorId(instructorId);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes a course from the schedule.
	 * @param course Course to remove from the schedule
	 * @return true if added
	 */
	public boolean removeCourseFromSchedule(Mino course) {
		if (schedule.remove(course)) {
			course.setInstructorId(null);
			return true;
		}
		return false;
	}
	
	/**
	 * Resets the schedule to an empty schedule
	 */
	public void resetSchedule() {
		int startingSize = schedule.size();
		for (int i = 0; i < startingSize; i++) {
			removeCourseFromSchedule(schedule.get(0)); //also removes from Course
		}
	}
	
	/**
	 * Returns the list of scheduled Courses.
	 * @return list of scheduled Courses
	 */
	public String[][] getScheduledCourses() {
		String [][] scheduleArray = new String[schedule.size()][5];
		for (int i = 0; i < schedule.size(); i++) {
			scheduleArray[i] = schedule.get(i).getShortDisplayArray();
		}
		return scheduleArray;
	}
	
	/**
	 * Returns the number of courses the faculty is scheduled to teach.
	 * @return num courses
	 */
	public int getNumScheduledCourses() {
		return schedule.size();
	}
	
}