package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;


/**
 * Provides a suite of tests for assessing the functionality of the Schedule Class. 
 * @author Ben W Ioppolo
 */
public class ScheduleTest {

	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	
	/** Course name */
	private static final String NAME216 = "CSC216";
	private static final String NAME116 = "CSC116";
	private static final String NAME226 = "CSC226";
	/** Course section */
	private static final String SECTION = "001";
	
	/** Course catalog object to help load in courses */
	private CourseCatalog catalog;
	/** Schedule */
	private Schedule schedule;
	
	/**
	 * Creates new schedule for subsequent tests.
	 */
	@Before
	public void setup(){
		//catalog.loadCoursesFromFile(validTestFile);
		schedule = new Schedule();
		catalog = new CourseCatalog();
		catalog.loadCoursesFromFile(validTestFile);
		
	}
	
	/**
	 * Tests that the Schedule class constructor creates a blank schedule and that the title is "My Schedule"
	 */
	@Test
	public void testSchedule(){
		assertTrue(schedule.getScheduledCourses().length == 0);
		assertEquals("My Schedule" , schedule.getTitle());
	}
	
	/**
	 * Tests for Adding courses to the schedule
	 */
	@Test
	public void testAddCourseToSchedule(){
		catalog.getCourseFromCatalog(NAME216, SECTION);

		try {
			assertFalse(schedule.addCourseToSchedule(catalog.getCourseFromCatalog("CSC777", SECTION)));
		} catch (ConflictException e2) {
			assertTrue(schedule.getScheduledCourses().length == 0);
		}

		try {
			assertTrue(schedule.addCourseToSchedule(catalog.getCourseFromCatalog(NAME216, SECTION)));
		} catch (ConflictException e1) {
			fail();
		}
		try {
			assertTrue(schedule.addCourseToSchedule(catalog.getCourseFromCatalog(NAME116, SECTION)));
		} catch (ConflictException e1) {
			fail();
		}
		String[][] courses = schedule.getScheduledCourses();
		assertTrue(courses[0][0].equals(NAME216));
		assertTrue(courses[1][0].equals(NAME116));		
		
		try{
			schedule.addCourseToSchedule(catalog.getCourseFromCatalog(NAME216, SECTION));
			fail();
		} catch (Exception e){
			assertEquals(e.getMessage(), "You are already enrolled in " + NAME216);
		}
		try{
			schedule.addCourseToSchedule(catalog.getCourseFromCatalog(NAME226, SECTION));
			fail();
		} catch (Exception e){
			assertEquals(e.getMessage(), "The course cannot be added due to a conflict.");
		}
	}
	
	/**
	 * Tests that removing a course on the schedule returns true and that removing one not on the schedule returns false.
	 */
	@Test
	public void testRemoveCourseFromSchedule(){
		catalog.getCourseFromCatalog(NAME216, SECTION);
		
		try {
			assertTrue(schedule.addCourseToSchedule(catalog.getCourseFromCatalog(NAME216, SECTION)));
		} catch (ConflictException e1) {
			fail();
		}
		assertTrue(schedule.removeCourseFromSchedule(catalog.getCourseFromCatalog(NAME216, SECTION)));
		assertFalse(schedule.removeCourseFromSchedule(catalog.getCourseFromCatalog(NAME216, SECTION)));
	}
	
	/**
	 * Tests that the resetSchedule method clears the schedule which implies that items in the 2d array would be null.
	 */
	@Test
	public void testResetSchedule(){
		schedule.resetSchedule();
		assertTrue(schedule.getScheduledCourses().length == 0);	
	}
	
	/**
	 * Tests that Each row should be a Course and the columns are name, section, title, 
	 * and the meeting string (i.e., Course.getShortDisplayArray()).
	 */
	@Test
	public void testGetScheduledCourses(){
		try {
			assertTrue(schedule.addCourseToSchedule(catalog.getCourseFromCatalog(NAME216, SECTION)));
		} catch (ConflictException e) {
			fail();
		}
		try {
			assertTrue(schedule.addCourseToSchedule(catalog.getCourseFromCatalog(NAME116, SECTION)));
		} catch (ConflictException e) {
			fail();
		}
		assertTrue(schedule.getScheduledCourses()[1][0].equals(NAME116));	
		assertTrue(schedule.getScheduledCourses()[0][1].equals(SECTION));
		//assertTrue(schedule.getScheduledCourses()[1][3] == catalog.getCourseFromCatalog(NAME116, SECTION).getShortDisplayArray());
	}
	
	/**
	 * Tests that setting the title to a string sets the title correctly. If the title is set to null then an error should
	 * be thrown.
	 */
	@Test
	public void testSetTitle(){
		assertEquals("My Schedule" , schedule.getTitle());
		schedule.setTitle("Awesome Title");
		assertEquals("Awesome Title" , schedule.getTitle());
		
		try{
			schedule.setTitle(null);
			fail();
		} catch (IllegalArgumentException e){
			assertEquals(e.getMessage(), "Title cannot be null.");
		}
	}
	
	/**
	 * Tests to verify that the number of credits a student is registered for is accurately summed over the schedule.
	 */
	@Test
	public void testGetScheduleCredits(){
		schedule.addCourseToSchedule(catalog.getCourseFromCatalog(NAME116, SECTION));
		schedule.addCourseToSchedule(catalog.getCourseFromCatalog(NAME216, SECTION));
		assertEquals(7, schedule.getScheduleCredits());
	}
	
	/**
	 * Tests to verify that courses can or can not be added to the schedule.
	 */
	@Test
	public void testCanAdd(){
		//test adding a null course
		assertFalse(schedule.canAdd(null));
		//test adding a duplicate course
		schedule.addCourseToSchedule(catalog.getCourseFromCatalog(NAME116, SECTION));
		assertFalse(schedule.canAdd(catalog.getCourseFromCatalog(NAME116, SECTION)));
		//test adding a conflicting course
		Course c  = new Course("CSC226", "computer stuff", "002", 4, "joe", 30, "M", 910, 1100);
		assertFalse(schedule.canAdd(c));
		//test adding a good course
		Course d = new Course("CSC226", "computer stuff", "002", 4, "joe", 30, "M", 1200, 1300);
		assertTrue(schedule.canAdd(d));
	}
	
}
