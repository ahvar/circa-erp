package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Creates a record object for a faculty when provided with their pertinent information.
 * @author Arthur Vargas
 */
public class Faculty extends User implements Comparable<Faculty> {
	private int maxCourses;
	/**
	 * static final variable MAX_CREDITS. the max courses allowed. 
	 */
	public static final int MAX_COURSES = 3;

	/**
	 * static final variable MIN_CREDITS. min courses allowed	
	 */
	public static final int MIN_COURSES = 1;
		
	private Schedule schedule;
	private FacultySchedule fSchedule;
		
	/**
	 * Given information except the max credits field. then calls the primary constructor with given variables 
	 * and the max credits Class final variable.
	 * @param firstName faculty first name
	 * @param lastName faculty last name
	 * @param id faculty id
	 * @param email faculty email
	 * @param hashPW faculty password
	 */
	public Faculty(String firstName, String lastName, String id, String email, String hashPW){
			
		this(firstName, lastName, id, email, hashPW, MAX_COURSES); //"this" is used to call the other constructor by providing it the MAX_CREDITS variable
	}
		
		/**
		 * Primary Constructor that Calls setters for each parameter and does not handle IllegalArgumentExceptions. Creates a 
		 * new empty schedule for the user as well.
		 * @param firstName faculty first name
		 * @param lastName faculty last name
		 * @param id faculty id
		 * @param email faculty email
		 * @param hashPW faculty password
		 * @param maxCourses the courses the faculty is teaching
		 */
		public Faculty(String firstName, String lastName, String id, String email, String hashPW, int maxCourses) {

			super(firstName, lastName, id, email, hashPW);
			setMaxCourses(maxCourses);
			schedule = new Schedule();
			fSchedule = new FacultySchedule(id);
		}

		/**
		 * gets the faculty maxCourses
		 * @return the maxCourses
		 */
		public int getMaxCourses() {
			return maxCourses;
		}

		/**
		 * sets the faculty max courses
		 * @param maxCourses the maxCourses to set
		 *  @throws IllegalArgumentException if maxCourses is less than MIN_CREDITS or greater than MAX_CREDITS
		 */
		public void setMaxCourses(int maxCourses) {
			if (maxCourses < MIN_COURSES || maxCourses > MAX_COURSES){
				String errorDescription;
				errorDescription = "Invalid max courses";
				throw new IllegalArgumentException(errorDescription);
			}
				this.maxCourses = maxCourses;
		}

		/**
		 * Returns a comma separated value string of all faculty fields
		 * @return String representation of faculty
		 */
		@Override
		public String toString() {
			return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + maxCourses;
		}
		
		/**
		 * Compares this faculty to the passed faculty by comparing a combined string of lastname firstname and id. the 
		 * compareTo method in comparable is called to compare the combined string.
		 */
		@Override
		public int compareTo(Faculty f) {
			int compareResult;
			String thisFaculty = new String(this.getLastName() + this.getFirstName() + this.getId());
			String facultyF = new String(f.getLastName() + f.getFirstName() + f.getId());	
			
			compareResult = thisFaculty.compareTo(facultyF);
			
			return compareResult;
		}

		/**
		 * Generates a hashcode for faculty using the maxCourses field. 
		 * @return result the hashcode for faculty
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result + maxCourses;
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
			Faculty other = (Faculty) obj;
			if (maxCourses != other.maxCourses)
				return false;
			return true;
		}

		/**
		 * Gets the faculty schedule which contains the courses they have selected.
		 * @return schedule. The schedule of the faculty courses.
		 */
		public FacultySchedule getSchedule() {
			return fSchedule;
		}
		
		/**
		 * Checks whether the faculty can add a course to their schedule. Uses schedule.canAdd method to perform some of the checks.
		 * The check not performed by schedule.canAdd that is performed is whether the total courses the faculty is already
		 * registered for is greater than their maxCourses that they are allowed to register for. 
		 * @param c The course that is being considered for adding to the schedule.
		 * @return True if the faculty can add the course. False otherwise. 
		 */
		public boolean canAdd(Course c){
			if (!schedule.canAdd(c) || fSchedule.getScheduledCourses().length + 1 > maxCourses)
				return false;

			return true;
		}
		
		/**
		 * returns true if the number of scheduled courses is greater than the Faculty's maxCourses
		 * @return true if the faculty is registered for too many courses, false otherwise.
		 */
		public boolean isOverloaded(){
			if (fSchedule.getNumScheduledCourses() > maxCourses)
				return true;
			return false;
		}
}
