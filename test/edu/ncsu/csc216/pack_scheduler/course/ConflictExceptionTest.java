/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

import com.circa.mrv.grs_manager.niox.ConflictException;

/**
 * Tests for verifying that ConflictException is working properly.
 * @author Ben Ioppolo
 *
 */
public class ConflictExceptionTest {

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.niox.ConflictException#ConflictException(java.lang.String)}.
	 */
	@Test
	public void testConflictExceptionString() {
	    ConflictException ce = new ConflictException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.niox.ConflictException#ConflictException()}.
	 */
	@Test
	public void testConflictException() {
		ConflictException ce = new ConflictException();
		assertEquals("Schedule conflict.", ce.getMessage());
	}

}
