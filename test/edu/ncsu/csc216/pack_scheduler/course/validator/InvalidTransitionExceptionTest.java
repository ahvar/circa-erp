package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.circa.mrv.grs_manager.product.validator.InvalidTransitionException;

/**
 * Tests for verifying that InvalidTransitionException is working properly.
 * @author Ben Ioppolo
 */
public class InvalidTransitionExceptionTest {

		/**
		 * Test method for InvalidTransitionException
		 */
		@Test
		public void testInvalidTransitionExceptionString() {
			InvalidTransitionException ce = new InvalidTransitionException("Custom exception message");
		    assertEquals("Custom exception message", ce.getMessage());
		}

		/**
		 * Test method for InvalidTransitionException
		 */
		@Test
		public void testInvalidTransitionException() {
			InvalidTransitionException ce = new InvalidTransitionException();
			assertEquals("Invalid FSM Transition.", ce.getMessage());
		}
}
