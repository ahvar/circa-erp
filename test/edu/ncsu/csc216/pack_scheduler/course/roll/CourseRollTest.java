/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

import com.circa.mrv.grs_manager.niox.Mino;
import com.circa.mrv.grs_manager.product.list.ProductList;
import com.circa.mrv.grs_manager.user.Morrisville;

/**
 * Tests the CourseRoll class
 * @author Arthur Vargas
 */
public class CourseRollTest {
	ProductList cr;
	//CourseRoll otherCr;
	/** Above the max enrollment by 1 */
	private final static int ONE_OVER_MAX = 251;
	/** Below the minimum enrollment by 1 */
	private final static int ONE_UNDER_MIN =  9;
	/** Valid enrollment cap value */
	private final static int VALID_ENROLLMENT_CAP = 100;
	/** A student to be added to the roll */
	private Morrisville s1;
	/** A student to be added to the roll */
	private Morrisville s2;
	/** A student to be added to the roll */
	private Morrisville s3;
	Morrisville s4 = new Morrisville("first", "last", "flast", "flast@email.edu", "pw");
	Morrisville s5 = new Morrisville("first", "blast", "fblast", "fblast@email.edu", "pw");
	Morrisville s6 = new Morrisville("first", "clast", "fclast", "fclast@email.edu", "pw");
	Morrisville s7 = new Morrisville("first", "dlast", "fdlast", "fdlast@email.edu", "pw");
	Morrisville s8 = new Morrisville("first", "elast", "felast", "felast@email.edu", "pw");
	Morrisville s9 = new Morrisville("first", "flast", "fflast", "fflast@email.edu", "pw");
	Morrisville s10 = new Morrisville("first", "glast", "fglast", "fglast@email.edu", "pw");
	Morrisville s11 = new Morrisville("Ben", "Ioppolo", "bioppolo", "bioppolo@email.edu", "pw");
	Morrisville s12 = new Morrisville("John", "Doe", "jdoe", "jdoe@email.edu", "pw");
	
	/**
	 * Sets up a CourseRoll to be used for later tests.
	 */
	@Before
	public void setUpBeforeClass() {
		Mino c = new Mino("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 20, "A");
		//cr = new CourseRoll(c, 20); //  new roll with enrollment cap of 20
		cr = c.getCourseRoll(); //to test waitlists
		
		s1 = new Morrisville("Arthur", "Vargas", "ahvargas", "ahvargas@ncsu.edu", "pw");
		s2 = new Morrisville("Holly", "Harrington", "hharring", "hharring@ncsu.edu", "pw");
		s3 = new Morrisville("Carlo", "Vargas", "cavargas", "cavargas@ncsu.edu", "pw");
		
	}

	/**
	 * Tests the CourseRoll constructor
	 */
	@Test
	public void testCourseRoll() {
		
		assertEquals(20, cr.getEnrollmentCap());
		assertEquals(20, cr.getOpenSeats());
		
	}

	/**
	 * Tests CourseRoll.setEnrollmentCap
	 */
	@Test
	public void testSetEnrollmentCap() {
		try {
			cr.setEnrollmentCap(ONE_OVER_MAX);
			fail();
		} catch (IllegalArgumentException e) {
			// success
		}
		
		try {
			cr.setEnrollmentCap(ONE_UNDER_MIN);
			fail();
		} catch (IllegalArgumentException e) {
			//success
		}
		
		try {
			cr.setEnrollmentCap(VALID_ENROLLMENT_CAP);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.product.list.ProductList#enroll(com.circa.mrv.grs_manager.user.Morrisville)}.
	 */
	@Test
	public void testEnroll() {
		try {
			cr.setEnrollmentCap(10); // set the enrollment cap at the minimum
		} catch (IllegalArgumentException e) {
			fail(); // an IllegalArgumentException should not have been thrown
		}
		cr.enroll(s1);
		cr.enroll(s2);
		cr.enroll(s3);
		assertEquals(7, cr.getOpenSeats());
		
		try {
			cr.enroll(null);
			fail(); // a NullPointerException should have been thrown and was not
		} catch (IllegalArgumentException e) {
			//success
		}
		
		cr.enroll(s4);
		cr.enroll(s5);
		cr.enroll(s6);
		cr.enroll(s7);
		cr.enroll(s8);
		cr.enroll(s9);
		cr.enroll(s10);
		assertEquals(0, cr.getOpenSeats());
		
		//Student s11 = new Student("Alex", "Alejandro", "avalejandro", "avalejandro@email.edu", "pw");
		try {
			cr.enroll(s11);
			assertEquals(0, cr.getOpenSeats());
			assertEquals(1, cr.getNumberOnWaitlist());
		} catch (IllegalArgumentException e) {
			fail(); // student should get added to waitlist

		}
		
	}

	/**
	 * Tests CourseRoll.drop
	 */
	@Test
	public void testDrop() {
		cr.setEnrollmentCap(10);
		
		cr.enroll(s1);
		cr.enroll(s2);
		cr.enroll(s3);
		cr.enroll(s4);
		cr.enroll(s5);
		cr.enroll(s6);
		cr.enroll(s7);
		cr.enroll(s8);
		cr.enroll(s9);
		cr.enroll(s10);
		
		try {
			cr.drop(null);
			fail(); //an IllegalArgumentException should have been thrown and was not
		} catch (IllegalArgumentException e) {
			// success
		}
		
		assertEquals(0, cr.getOpenSeats());
		
		// drop a student and verify that the number of open seats has changed
		try {
			cr.drop(s1);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(1, cr.getOpenSeats());
		
		//add some more so that students are on the waitlist.
		cr.enroll(s11);
		cr.enroll(s12);
		cr.enroll(s1);
		assertEquals(0, cr.getOpenSeats());
		assertEquals(2, cr.getNumberOnWaitlist());
		
		//drop student in course to push s12 into course from waitlist
		cr.drop(s3);
		assertEquals(0, cr.getOpenSeats());
		assertEquals(1, cr.getNumberOnWaitlist());
		cr.drop(s1);
		assertEquals(0, cr.getOpenSeats());
		assertEquals(0, cr.getNumberOnWaitlist());
	}

	/**
	 * Test CourseRoll.getOpenSeats();
	 */
	@Test
	public void testGetOpenSeats() {
		try {
			cr.enroll(s1);
			cr.enroll(s2);
			cr.enroll(s3);
			cr.enroll(s4);
			cr.enroll(s5);
			cr.enroll(s6);
			cr.enroll(s7);
			cr.enroll(s8);
			cr.enroll(s9);
			cr.enroll(s10);
			// success
		} catch (IllegalArgumentException e) {
			fail(); // an exception was thrown while adding valid students to the roll
		}
		assertEquals(10, cr.getOpenSeats());  // verify open seats
	
		try{
			cr.drop(s1);
			cr.drop(s2);
		} catch (IllegalArgumentException e) {
			fail(); //An IllegalArgumentException should not have been thrown and was
		}
		assertEquals(12, cr.getOpenSeats());
		//Student s11 = new Student("Alex", "Alejandro", "avalejandro", "avalejandro@email.edu", "pw");
		//Student s12 = new Student("Emily", "Peters", "epeters", "epeters@email.edu", "pw");
		Morrisville s13 = new Morrisville("Robert", "Wiggins", "rwiggins", "rwiggins@email.edu", "pw");
		try {
			cr.enroll(s11);
			cr.enroll(s12);
			cr.enroll(s13);
			// success
		} catch (IllegalArgumentException e) {
			fail(); //An IllegalArgumentException should not have been thrown and was
		}
		assertEquals(9, cr.getOpenSeats());
	}

	/**
	 * Tests CourseRoll.canEnroll
	 */
	@Test
	public void testCanEnroll() {
		assertFalse(cr.canEnroll(null));
		cr.setEnrollmentCap(11);
		try {
			cr.enroll(s1);
			cr.enroll(s2);
			cr.enroll(s3);
			cr.enroll(s4);
			cr.enroll(s5);
			cr.enroll(s6);
			cr.enroll(s7);
			cr.enroll(s8);
			cr.enroll(s9);
			cr.enroll(s10);
			//Student s11 = new Student("Alex", "Alejandro", "avalejandro", "avalejandro@email.edu", "pw");
			cr.enroll(s11);
			// success
		} catch (IllegalArgumentException e) {
			fail(); // an exception was thrown while adding valid students to the roll
		}
		
		//Student s12 = new Student("Emily", "Peters", "epeters", "epeters@email.edu", "pw");
		assertTrue(cr.canEnroll(s12)); // can enroll (add to wait) because the class is full but waitlist is not
		assertFalse(cr.canEnroll(s1)); // cannot be enrolled because is duplicate
		
	}

}
