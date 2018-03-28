package edu.ncsu.csc216.pack_scheduler.course.validator;
import static org.junit.Assert.*;

import org.junit.Test;

import com.circa.mrv.grs_manager.product.validator.InvalidTransitionException;
import com.circa.mrv.grs_manager.product.validator.ProductNameValidatorFSM;

/**
 * Provides a number of tests to asses the integrity and accuracy of the CourseNameValidatorFSM class.
 * @author Ben W Ioppolo
 */
public class CourseNameValidatorFSMTest {

	/**
	 * Provides a number of tests to asses the integrity and accuracy of the CourseNameValidatorFSM class.
	 */
	@Test
	public void testIsValid(){
		ProductNameValidatorFSM validator = new ProductNameValidatorFSM();
		
		//Test invalid Initial state
		try {
			validator.isValid("!");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
	
		try {
			validator.isValid("9");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
		}
		
		//Test invalid L state
		try {
			assertFalse(validator.isValid("C"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		//Test invalid LD state
		try {
			assertFalse(validator.isValid("C1"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		//Test invalid LL state
		try {
			assertFalse(validator.isValid("CS"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		//Test invalid LDD state
		try {
			assertFalse(validator.isValid("C11"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		//Test invalid LLL state
		try {
			assertFalse(validator.isValid("CSC"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		//Test valid LDDD state
		try {
			assertTrue(validator.isValid("C111"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		//Test invalid LLLL state
		try {
			assertFalse(validator.isValid("CSCA"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		//Test valid LDDDL state
		try {
			assertTrue(validator.isValid("C111A"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		//Test valid LLDDDL state
		try {
			assertTrue(validator.isValid("CS111A"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		//Test valid LLLDDDL state
		try {
			assertTrue(validator.isValid("CSC111A"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		//Test valid LLLLDDDL state
		try {
			assertTrue(validator.isValid("CSCA111A"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		//Test invalid LLLLL state
		try {
			validator.isValid("CSCAA");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		}
		
		//Test invalid LDL state
		try {
			validator.isValid("A1A");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		//Test invalid LDDL state
		try {
			validator.isValid("A11A");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		//Test invalid LDDDD state
		try {
			validator.isValid("A1111");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		
		//Test invalid LDDDLL state
		try {
			validator.isValid("A111LL");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}
		
		//Test invalid LDDDLD state
		try {
			validator.isValid("A111L9");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}
	}
}
