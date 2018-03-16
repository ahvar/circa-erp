/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Creates a Course object that contains the name, title, section, credit hours,
 * instructor id, meeting days start time and end time. It also has various
 * methods to get and set the Course's information.. 
 * 
 * @author Ben Ioppolo
 */
public class Course extends Activity implements Comparable<Course> {

	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Length of section String */
	private static final int SECTION_LENGTH = 3;
	/** max length of name string */
	private static final int MAX_NAME_LENGTH = 8;
	/** min length of name string */
	private static final int MIN_NAME_LENGTH = 4;
	/** max number of credits for a course */
	private static final int MAX_CREDITS = 5;
	/** min number of credits for a course */
	private static final int MIN_CREDITS = 1;
	/** The course roll list for the course */
	private CourseRoll roll;
	
	/**
	 * Constructs a Course object with values for all fields. title, meetingDays, startTime,
	 * and endTime are all sent to the Activity class for construction.
	 * 
	 * @param name
	 *            name of course
	 * @param title
	 *            title of course
	 * @param section
	 *            section of course
	 * @param credits
	 *            credit hours of course
	 * @param instructorId
	 *            instructors unity id
	 * @param meetingDays
	 *            meeting days for course as series of chars
	 * @param startTime
	 *            start time for course
	 * @param endTime
	 *            end time for course
	 * @param enrollmentCap the number of students that can register for the course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
			int startTime, int endTime) { // primary constructor
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		roll = new CourseRoll(this, enrollmentCap);

	}

	/**
	 * Creates a Course object with the given name, title, section, credits,
	 * instructorId, and meetingDays for courses that are arranged. This is done
	 * by calling the primary constructor with the given parameters plus default
	 * parameters for the missing parameters (endTime and startTime).
	 * 
	 * @param name
	 *            name of course
	 * @param title
	 *            title of course
	 * @param section
	 *            section of course
	 * @param credits
	 *            credit hours of course
	 * @param instructorId
	 *            instructors unity id
	 * @param meetingDays
	 *            meeting days for course as series of chars
	 * @param enrollmentCap the number of students that can register for the course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
		// calls primary constructor with default values for missing parameters
	}

	/**
	 * Returns the Course's name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null or has a length less than
	 * MIN_NAME_LENGTH or greater than MAX_NAME_LENGTH an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param name
	 *            the name to set
	 * @throws IllegalArgumentException
	 *             if name is null or has length < MIN_NAME_LENGTH or >
	 *             MAX_NAME_LENGTH.
	 */
	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Invalid Name");
		}

		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid Name");
		}
		CourseNameValidator validator = new CourseNameValidator();
		
		try {
			if (!(validator.isValid(name)))
				throw new IllegalArgumentException("Invalid Name");
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid Name");			
		}
		
		this.name = name;
	}

	/**
	 * Returns the Course's section
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * sets the Course's section. if the section string's length is not equal to
	 * SECTION_LENGTH, if null, or if the characters are not digits, an
	 * IllegalArgumentException is thrown
	 * 
	 * @param section
	 *            the section to set
	 * @throws IllegalArgumentException
	 *             if section's length is not equal to SECTION_LENGTH, if null,
	 *             or if the characters are not digits
	 */
	public void setSection(String section) {
		char c;
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid Section");
		}

		for (int i = 0; i < section.length(); i++) {

			c = section.charAt(i);

			if (!(Character.isDigit(c))) {
				throw new IllegalArgumentException("Invalid Section");
			}
		}
		this.section = section;

	}

	/**
	 * returns the Course's credits
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * sets the Course's credits. if the credits are less than MIN_CREDITS or credits are greater than
	 * MAX_CREDITS an IllegalArgumentException is thrown.
	 * 
	 * @param credits the credits to set
	 *           
	 * @throws IllegalArgumentException if MIN_CREDITS is greater than credits or credits is greater than MAX_CREDITS
	 *             
	 */
	public void setCredits(int credits) {

		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException();
		}
		this.credits = credits;
	}

	/**
	 * returns the Course's instructorId
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * sets the Course's instructorId. if instructorId is null or empty an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param instructorId
	 *            the instructorId to set
	 * @throws IllegalArgumentException
	 *             if instructorId is null or empty
	 */
	public void setInstructorId(String instructorId) {

//		if (instructorId.isEmpty()) {
//			throw new IllegalArgumentException("Invalid instructor id");
//		}
		this.instructorId = instructorId;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if (getMeetingDays().equals("A")) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + getMeetingDays() + ","
				+ getStartTime() + "," + getEndTime();
	}

	/**
	 * Generates a hashCode for Course using all fields
	 * 
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields.
	 * 
	 * @param obj
	 *            the object to compare
	 * @return true if the objects are the same on all fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}
	
	/**
	 * gets a string array with the name, section, title, meeting information, and open seats for the course
	 * @return string array with the course information
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortCourseInfo = {name, section, getTitle(), getMeetingString(), "" + roll.getOpenSeats()}; 
		return shortCourseInfo;
	}

	/**
	 * gets a string array with the name, section, title, credits, instructorId, and meeting information
	 * as well as an empty string
	 * @return string array with the course information
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longCourseInfo = {name, section, getTitle(), "" + credits, instructorId, 
				getMeetingString(), ""};
		return longCourseInfo;
	}
	
	/**
	 * sets the Course's meetingDays. If meetingDays is null, empty, if the only
	 * meetingDay is not M, T, W, H, F, or A, if multiple meetingDays are
	 * provided and A is included, or if multiple meetingDays are provided and 1
	 * or more do not match M, T, W, H, or F, an IllegalArgumentException is
	 * thrown.
	 * 
	 * @param meetingDays
	 *            the meetingDays to set
	 * @throws IllegalArgumentException
	 *             if meetingDays is null, empty, if the only meetingDay is not
	 *             M, T, W, H, F, or A, if multiple meetingDays are provided and
	 *             A is included, or if multiple meetingDays are provided and 1
	 *             or more do not match M, T, W, H, or F.
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		char c;
	
		if (meetingDays == null || meetingDays.isEmpty()) {
			throw new IllegalArgumentException("Invalid meeting days");
		}
	
		if (meetingDays.length() == 1) {
			c = meetingDays.charAt(0);
	
			if (c != 'M' && c != 'T' && c != 'W' && c != 'H' && c != 'F' && c != 'A') {
				throw new IllegalArgumentException("Invalid meeting days");
			}
		}
	
		if (meetingDays.length() > 1) {
	
			for (int i = 0; i < meetingDays.length(); i++) {
	
				c = meetingDays.charAt(i);
	
				if (c != 'M' && c != 'T' && c != 'W' && c != 'H' && c != 'F') {
					throw new IllegalArgumentException("Invalid meeting days");
				}
	
			}
		}
	
		super.setMeetingDays(meetingDays);
	}
	
	/**
	 * Checks if the passed activity has the same name as this course
	 * @return true if it is a duplicate otherwise false.
	 */
	@Override
	public boolean isDuplicate(Activity activity){

		if (activity instanceof Course){
			Course tempCourse = (Course) activity;
			if(tempCourse.name.equals(name))
				return true;
		}
		
		return false;
	}
	
	/**
	 * compares the passed course to this course on the name and then section parameters. This is done by creating a string
	 * from those parameters for each course object and then comparing the strings via the compareTo method in comparable. 
	 * @return compareResult the result of the comparison which will be an integer 0 (equal), greater than 0 (this object is 
	 * greater than the passed object), or less than 0 (object less than passed object). 
	 */
	@Override
	public int compareTo(Course c) {

		int compareResult;
		String thisCourse = new String(this.name + this.section);
		String courseC = new String(c.getName() + c.getSection());	
		
		compareResult = thisCourse.compareTo(courseC);
		
		return compareResult;
	}
	
	/**
	 * Gets the course roll which shows which students are registered for the course
	 * @return roll The linked list that shows which students are registered for the course.
	 */
	public CourseRoll getCourseRoll(){
		return roll;
	}
}
