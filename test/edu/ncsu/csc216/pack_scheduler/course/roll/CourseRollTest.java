/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

import com.circa.mrv.grs_manager.directory.Product;
import com.circa.mrv.grs_manager.niox.Mino;
import com.circa.mrv.grs_manager.product.list.Product;

/**
 * Tests the CourseRoll class
 * @author Arthur Vargas
 */
public class CourseRollTest {
	Product cr;
	//CourseRoll otherCr;
	/** Above the max enrollment by 1 */
	private final static int ONE_OVER_MAX = 251;
	/** Below the minimum enrollment by 1 */
	private final static int ONE_UNDER_MIN =  9;
	/** Valid enrollment cap value */
	private final static int VALID_ENROLLMENT_CAP = 100;
	/** A student to be added to the roll */
	private Product s1;
	/** A student to be added to the roll */
	private Product s2;
	/** A student to be added to the roll */
	private Product s3;
	Product s4 = new Product("first", "last", "flast", "flast@email.edu", "pw");
	Product s5 = new Product("first", "blast", "fblast", "fblast@email.edu", "pw");
	Product s6 = new Product("first", "clast", "fclast", "fclast@email.edu", "pw");
	Product s7 = new Product("first", "dlast", "fdlast", "fdlast@email.edu", "pw");
	Product s8 = new Product("first", "elast", "felast", "felast@email.edu", "pw");
	Product s9 = new Product("first", "flast", "fflast", "fflast@email.edu", "pw");
	Product s10 = new Product("first", "glast", "fglast", "fglast@email.edu", "pw");
	Product s11 = new Product("Ben", "Ioppolo", "bioppolo", "bioppolo@email.edu", "pw");
	Product s12 = new Product("John", "Doe", "jdoe", "jdoe@email.edu", "pw");
	
	/**
	 * Sets up a CourseRoll to be used for later tests.
	 */
	@Before
	public void setUpBeforeClass() {
		Mino c = new Mino("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 20, "A");
		//cr = new CourseRoll(c, 20); //  new roll with enrollment cap of 20
		cr = c.getCourseRoll(); //to test waitlists
		
		s1 = new Product("Arthur", "Vargas", "ahvargas", "ahvargas@ncsu.edu", "pw");
		s2 = new Product("Holly", "Harrington", "hharring", "hharring@ncsu.edu", "pw");
		s3 = new Product("Carlo", "Vargas", "cavargas", "cavargas@ncsu.edu", "pw");
		
	}

	/**
	 * Tests the CourseRoll constructor
	 */
	@Test
	public void testCourseRoll() {
		
		assertEquals(20, cr.getCapacity());
		assertEquals(20, cr.getRemainingCapacity());
		
	}

	/**
	 * Tests CourseRoll.setEnrollmentCap
	 */
	@Test
	public void testSetEnrollmentCap() {
		try {
			cr.setCapacity(ONE_OVER_MAX);
			fail();
		} catch (IllegalArgumentException e) {
			// success
		}
		
		try {
			cr.setCapacity(ONE_UNDER_MIN);
			fail();
		} catch (IllegalArgumentException e) {
			//success
		}
		
		try {
			cr.setCapacity(VALID_ENROLLMENT_CAP);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.product.list.Product#add(com.circa.mrv.grs_manager.directory.Product)}.
	 */
	@Test
	public void testEnroll() {
		try {
			cr.setCapacity(10); // set the enrollment cap at the minimum
		} catch (IllegalArgumentException e) {
			fail(); // an IllegalArgumentException should not have been thrown
		}
		cr.add(s1);
		cr.add(s2);
		cr.add(s3);
		assertEquals(7, cr.getRemainingCapacity());
		
		try {
			cr.add(null);
			fail(); // a NullPointerException should have been thrown and was not
		} catch (IllegalArgumentException e) {
			//success
		}
		
		cr.add(s4);
		cr.add(s5);
		cr.add(s6);
		cr.add(s7);
		cr.add(s8);
		cr.add(s9);
		cr.add(s10);
		assertEquals(0, cr.getRemainingCapacity());
		
		//Student s11 = new Student("Alex", "Alejandro", "avalejandro", "avalejandro@email.edu", "pw");
		try {
			cr.add(s11);
			assertEquals(0, cr.getRemainingCapacity());
			assertEquals(1, cr.getsNumberOnHold());
		} catch (IllegalArgumentException e) {
			fail(); // student should get added to waitlist

		}
		
	}

	/**
	 * Tests CourseRoll.drop
	 */
	@Test
	public void testDrop() {
		cr.setCapacity(10);
		
		cr.add(s1);
		cr.add(s2);
		cr.add(s3);
		cr.add(s4);
		cr.add(s5);
		cr.add(s6);
		cr.add(s7);
		cr.add(s8);
		cr.add(s9);
		cr.add(s10);
		
		try {
			cr.drop(null);
			fail(); //an IllegalArgumentException should have been thrown and was not
		} catch (IllegalArgumentException e) {
			// success
		}
		
		assertEquals(0, cr.getRemainingCapacity());
		
		// drop a student and verify that the number of open seats has changed
		try {
			cr.drop(s1);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(1, cr.getRemainingCapacity());
		
		//add some more so that students are on the waitlist.
		cr.add(s11);
		cr.add(s12);
		cr.add(s1);
		assertEquals(0, cr.getRemainingCapacity());
		assertEquals(2, cr.getsNumberOnHold());
		
		//drop student in course to push s12 into course from waitlist
		cr.drop(s3);
		assertEquals(0, cr.getRemainingCapacity());
		assertEquals(1, cr.getsNumberOnHold());
		cr.drop(s1);
		assertEquals(0, cr.getRemainingCapacity());
		assertEquals(0, cr.getsNumberOnHold());
	}

	/**
	 * Test CourseRoll.getOpenSeats();
	 */
	@Test
	public void testGetOpenSeats() {
		try {
			cr.add(s1);
			cr.add(s2);
			cr.add(s3);
			cr.add(s4);
			cr.add(s5);
			cr.add(s6);
			cr.add(s7);
			cr.add(s8);
			cr.add(s9);
			cr.add(s10);
			// success
		} catch (IllegalArgumentException e) {
			fail(); // an exception was thrown while adding valid students to the roll
		}
		assertEquals(10, cr.getRemainingCapacity());  // verify open seats
	
		try{
			cr.drop(s1);
			cr.drop(s2);
		} catch (IllegalArgumentException e) {
			fail(); //An IllegalArgumentException should not have been thrown and was
		}
		assertEquals(12, cr.getRemainingCapacity());
		//Student s11 = new Student("Alex", "Alejandro", "avalejandro", "avalejandro@email.edu", "pw");
		//Student s12 = new Student("Emily", "Peters", "epeters", "epeters@email.edu", "pw");
		Product s13 = new Product("Robert", "Wiggins", "rwiggins", "rwiggins@email.edu", "pw");
		try {
			cr.add(s11);
			cr.add(s12);
			cr.add(s13);
			// success
		} catch (IllegalArgumentException e) {
			fail(); //An IllegalArgumentException should not have been thrown and was
		}
		assertEquals(9, cr.getRemainingCapacity());
	}

	/**
	 * Tests CourseRoll.canEnroll
	 */
	@Test
	public void testCanEnroll() {
		assertFalse(cr.canAdd(null));
		cr.setCapacity(11);
		try {
			cr.add(s1);
			cr.add(s2);
			cr.add(s3);
			cr.add(s4);
			cr.add(s5);
			cr.add(s6);
			cr.add(s7);
			cr.add(s8);
			cr.add(s9);
			cr.add(s10);
			//Student s11 = new Student("Alex", "Alejandro", "avalejandro", "avalejandro@email.edu", "pw");
			cr.add(s11);
			// success
		} catch (IllegalArgumentException e) {
			fail(); // an exception was thrown while adding valid students to the roll
		}
		
		//Student s12 = new Student("Emily", "Peters", "epeters", "epeters@email.edu", "pw");
		assertTrue(cr.canAdd(s12)); // can enroll (add to wait) because the class is full but waitlist is not
		assertFalse(cr.canAdd(s1)); // cannot be enrolled because is duplicate
		
	}

}
