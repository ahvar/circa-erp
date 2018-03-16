package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;


/**
 * The CourseRoll class provides functionality for maintaining a list of students who are registered for a particular
 * course. The list is a LinkedAbstractList and students can enroll or drop the course. Also, the registrar can set the
 * enrollment cap for the course before or during registration. The system can also see the number of open seats remaining
 * for the course based on the current registration and enrollment cap.
 * @author Ben W Ioppolo
 */
public class CourseRoll {
	
	private LinkedAbstractList<Student> roll;
	private int enrollmentCap;
	private static final int MIN_ENROLLMENT = 10;
	private static final int MAX_ENROLLMENT = 250;
	private static final int WAITLIST_SIZE = 10;
	private LinkedQueue<Student> waitlist;
	private Course course;
	
	/**
	 * Creates a new LinkedAbstractList of Students to store the students who are registered for a particular course. 
	 * The capacity of the list is passed by the user and set using the setEnrollmentCap method which checks that the 
	 * capacity falls within the minimum and max values. 
	 * @param enrollmentCap The requested enrollment cap.
	 * @param c The course to enroll student from waitlist into.
	 */
	public CourseRoll(Course c, int enrollmentCap){
		if (c == null)
			throw new IllegalArgumentException();
		course = c;
		setEnrollmentCap(enrollmentCap);
		roll = new LinkedAbstractList<Student>(this.enrollmentCap);	
		waitlist = new LinkedQueue<Student>(WAITLIST_SIZE);
		waitlist.setCapacity(WAITLIST_SIZE);
	}
	
	/**
	 * Sets the cap on the number of students allowed to enroll in a particular course to the passed value. If the passed
	 * value is less than 10 or greater than 250 an IllegalArgumentException is thrown. Also, if the current course 
	 * enrollment is greater than the passed enrollment Cap than the IAE is thrown.
	 * @param enrollmentCap the requested enrollment cap. 
	 * @throws IllegalArgumentException if the passed enrollment cap is less than 10, greater than 250, or less than the
	 * current enrollment for the course.
	 */
	public void setEnrollmentCap(int enrollmentCap){
		if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT)
			throw new IllegalArgumentException("Enrollment Cap must be between 10 – 250");
		else if (roll != null && enrollmentCap < roll.size())
			throw new IllegalArgumentException();
		else{
			this.enrollmentCap = enrollmentCap;
			if (roll != null && enrollmentCap >= roll.size())
				roll.setCapacity(enrollmentCap);
		}
	}
	
	/**
	 * Gets the number of students that are allowed to enroll in a particular course.
	 * @return enrollmentCap The cap on the number of students allowed to enroll in a particular course.
	 */
	public int getEnrollmentCap(){
		return enrollmentCap;
	}
	
	/**
	 * Enrolls the passed student if they are not null, if there is more room in the class, if the student is not already
	 * enrolled in the class, and if no other exceptions are thrown from the LinkedAbstractList class when attempting to 
	 * add the student to the end of the list.
	 * @param s The student to enroll in the course roll list.
	 * @throws IllegalArgumentException if the canEnroll method returns false or if an exception is thrown from the 
	 * LinkedAbstractList class when attempting to add the student to the end of the course roll list.
	 */
	public void enroll(Student s){
		if (!canEnroll(s))
			throw new IllegalArgumentException();
		if(roll.size() >= enrollmentCap){ //it got here so there must be room on the waitlist
			waitlist.enqueue(s);
		} else{
			try{
				roll.add(roll.size(), s);
				s.getSchedule().addCourseToSchedule(course);
			} catch (Exception e){
				throw new IllegalArgumentException(e);
			}	
		}
	}
	
	/**
	 * Removes an enrolled student from the course roll list if they are present on the list and if the student is not null. 
	 * @throws IllegalArgumentException if the student is null or if the LinkedAbstractList class throws an exception 
	 * during the removal process.
	 * @param s The student to remove from the course roll list. 
	 */
	public void drop(Student s){
		if (s == null)
			throw new IllegalArgumentException();
		try{
			for (int i = 0 ; i < roll.size() ; i++){
				if (roll.get(i).equals(s)){
					roll.remove(i); //removes from course roll
					if (!waitlist.isEmpty()){ //if it is not empty then put the person on the waitlist into the class
						Student waitingStudent = waitlist.dequeue();
						roll.add(roll.size(), waitingStudent);
						waitingStudent.getSchedule().addCourseToSchedule(course);
					}
				}
			}
			if (!waitlist.isEmpty()){ //this is checking if the student that dropped is on the waitlist.
				Student waitingStudent;
				int initialWaitlistSize = waitlist.size();
				for (int i = 0 ; i < initialWaitlistSize ; i++){
					waitingStudent = waitlist.dequeue();
					if (!waitingStudent.equals(s)){ //if not equal put student back into queue at end.
						waitlist.enqueue(waitingStudent);
					}
					//otherwise, the student is removed and the queue is simply "requeued"
				}
			}
		} catch (Exception e){
			throw new IllegalArgumentException();
		}	
	}
	
	/**
	 * Gets the number of available seats for students to enroll into the course.
	 * @return The difference between the enrollment cap and the current number of students enrolled in the course.
	 */
	public int getOpenSeats(){
		return enrollmentCap - roll.size();
	}
	
	/**
	 * Checks whether a student is allowed to enroll in a particular course. A student would be allowed if the passed student
	 * is not null, if the current enrollment is less than the enrollment cap, or if the student is not already enrolled
	 * in the course.
	 * @param s The student to check whether they are allowed to enroll in the course.
	 * @return True if student passes the above criteria. False otherwise. 
	 */
	public boolean canEnroll(Student s){
		//if null or if both the waitlist and the roll are full then cant enroll. roll is full if wait is full.
		if (s == null || waitlist.size() >= WAITLIST_SIZE) 
			return false;
		//waitlist not full but course is. check if student is on either list.
		if (waitlist.size() < WAITLIST_SIZE && roll.size() >= enrollmentCap){
			try{ //this is checking to see if a student is already on the course roll.
				for (int i = 0; i < roll.size() ; i++){
					if (roll.get(i).equals(s))
						return false;
				}
				
				if(waitlist.isEmpty()){ //wasnt on roll so we can add to empty waitlist
					return true;
				} else{
					Student stu;
					int matchCount = 0;
					for (int i = 0 ; i < waitlist.size() ; i++){
						stu = waitlist.dequeue();
						if (stu.equals(s)){ //then they are already on the waitlist
							matchCount++;
						}
						waitlist.enqueue(stu);
					}
					if (matchCount > 0)
						return false; //the student is already on wait list
				}	
			} catch (IllegalArgumentException e){
				return false;
			}				
		} else { //the course is not full so check that student is not on roll
			for (int i = 0; i < roll.size() ; i++){
				if (roll.get(i).equals(s))
					return false;
			}
		}
		return true;
	}
	
	/**
	 * Gets the number of students on the waitlist for the course.
	 * @return The number of students on the waitlist.
	 */
	public int getNumberOnWaitlist(){
		return waitlist.size();
	}
}
