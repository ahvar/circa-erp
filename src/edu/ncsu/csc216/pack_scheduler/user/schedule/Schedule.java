package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;


/**
 * Defines behavior and state for the Student's schedule by managing the addition and removal of courses.
 * Initializes the title to "My Schedule" and allows the user to reset both title and schedule as necessary.
 * @author Arthur Vargas
 */
public class Schedule {
	
	/** An ArrayList of courses */
	private ArrayList<Course> schedule;
	/** The Course title */
	private String title;
	
	/**
	 * Constructs an empty Arraylist of Courses to hold all courses in the Student's schedule. Also initializes the
	 * title to "My schedule."
	 */
	public Schedule() {
		schedule = new ArrayList<Course>();
		title = "My Schedule";
	}
	
	/**
	 * Adds a Course to the end of the schedule and confirm addition by returning true if the Course was added, false
	 * otherwise. If the Course is a duplicate then an IllegalArgumentException is thrown. If there is a conflict with
	 * an existing Course in the schedule, a ConflictException is thrown with the following message: "The Course cannot
	 * be added due to conflict."
	 * @param course the Course to be added to the schedule
	 * @return boolean true if the Course could be added to the schedule, false otherwise
	 * @throws ConflictException if there is another Course scheduled at this time
	 */
	public boolean addCourseToSchedule(Course course) throws ConflictException {
		
		// if the schedule is empty, add course
		if (schedule.isEmpty()) {
			try{
			schedule.add(schedule.size(), course);
			} catch(Exception e){
				return false;
			}
			return true;
		}
			
		// loop through courses and make sure the Course to add doesn't already exist in the schedule
		// and that the Course we are adding doesn't conflict with existing Courses
		for (Course currentCourse : schedule) {
			
			if(course.isDuplicate(currentCourse)) {
				throw new IllegalArgumentException("You are already enrolled in " + course.getName());
			} else {
				
				try {
					course.checkConflict(currentCourse);
				} catch (ConflictException e) {
					throw new IllegalArgumentException("The course cannot be added due to a conflict.");
				}
			}
		}
		try{
			schedule.add(schedule.size(), course);
		} catch(Exception e){
			return false;
		}
		return true;
	}
	
	/**
	 * Removes the parameter specified Course from the schedule. Returns true if the Course was successfully removed, 
	 * false otherwise. 
	 * @param course the Course to be removed from the schedule
	 * @return boolean true if this Course was successfully removed, false otherwise
	 */
	public boolean removeCourseFromSchedule(Course course) {
		
		if (schedule.size() == 0) {
			return false;
		} 
		
		if (schedule.size() > 0) {
			
			for (Course currentCourse : schedule) {			
				if (currentCourse.isDuplicate(course)) {
					schedule.remove(schedule.indexOf(currentCourse));
					return true;
				}
			}
		} 
		return false;
	}
	
	/**
	 * Resets the schedule by creating a new ArrayList
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
	}
	
	/**
	 * Returns a 2D array of Course information. Each row should be a Course and the columns are name,
	 * section, title, and the meeting string.
	 * @return scheduleArray the 2D array of Course information as 
	 */
	public String[][] getScheduledCourses() {
		String[][] scheduleArray = new String[this.schedule.size()][5];
		if (this.schedule.size() == 0) {
			return scheduleArray;
		} else {
			
			for (int i = 0; i <  this.schedule.size(); i++) {
				scheduleArray[i][0] = schedule.get(i).getShortDisplayArray()[0];
				scheduleArray[i][1] = schedule.get(i).getShortDisplayArray()[1];
				scheduleArray[i][2] = schedule.get(i).getShortDisplayArray()[2];
				scheduleArray[i][3] = schedule.get(i).getShortDisplayArray()[3];
				scheduleArray[i][4] = schedule.get(i).getShortDisplayArray()[4];
			}
		}
		//System.out.println(scheduleArray[0][0]);
		return scheduleArray;
	}
	
	/**
	 * Sets the title parameter value. If the title is null, an IllegalArgumentException is thrown with the message
	 * "Title cannot be null."
	 * @param title the String representation of the title
	 * @throws IllegalArgumentException if the title is null or blank
	 */
	public void setTitle(String title) {
		if (title == null || title.equals(" ")) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}
	
	/**
	 * Returns the title of the schedule as a String
	 * @return title the title of this schedule
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Gets the total number of credits for all courses on the schedule and returns it. 
	 * @return scheduledCredits The total number of credits that a student is registered for.
	 */
	public int getScheduleCredits(){
		int scheduledCredits = 0;
		for(Course c : schedule){
			scheduledCredits += c.getCredits();
		}
		return scheduledCredits;
	}
	
	/**
	 * checks to see if a course can be added to the schedule. A course can be added to the schedule if it is not null,
	 * if it is not duplicate, and if it is not conflicting with something else on the schedule. 
	 * @param c The course to check if it is possible to add to the schedule.
	 * @return true if the course can be added and false otherwise.
	 */
	public boolean canAdd(Course c){
		if (c == null)
			return false;
		for (Course course : schedule){
			if (course.isDuplicate(c))
				return false;
			try{
				course.checkConflict(c);
			} catch (ConflictException e){
				return false;
			}
		}
		return true;
	}
}
