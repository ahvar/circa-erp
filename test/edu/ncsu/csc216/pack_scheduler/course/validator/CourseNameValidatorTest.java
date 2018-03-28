package edu.ncsu.csc216.pack_scheduler.course.validator;
import static org.junit.Assert.*;

import org.junit.Test;

import com.circa.mrv.grs_manager.product.validator.InvalidTransitionException;
import com.circa.mrv.grs_manager.product.validator.ProductNameValidator;

/**
 * Provides a number of tests to asses the integrity and accuracy of the CourseNameValidator class.
 * @author Ben W Ioppolo
 */
public class CourseNameValidatorTest {

	/**
	 * Provides a number of tests to asses the integrity and accuracy of the CourseNameValidator class.
	 */
	@Test
	public void testIsValid(){
		
		ProductNameValidator validator = new ProductNameValidator();
		
		//Test invalid Initial state
		try {
			validator.isValid("!");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
	
		validator = new ProductNameValidator();
		try {
			validator.isValid("9");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
		}
		
		validator = new ProductNameValidator();
		//Test invalid L state
		try {
			assertFalse(validator.isValid("C"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		validator = new ProductNameValidator();
		//Test invalid LD state
		try {
			assertFalse(validator.isValid("C1"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		validator = new ProductNameValidator();
		//Test invalid LL state
		try {
			assertFalse(validator.isValid("CS"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		validator = new ProductNameValidator();
		//Test invalid LDD state
		try {
			assertFalse(validator.isValid("C11"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		validator = new ProductNameValidator();
		//Test invalid LLL state
		try {
			assertFalse(validator.isValid("CSC"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		validator = new ProductNameValidator();
		//Test valid LDDD state
		try {
			assertTrue(validator.isValid("C111"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		validator = new ProductNameValidator();
		//Test invalid LLLL state
		try {
			assertFalse(validator.isValid("CSCA"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		validator = new ProductNameValidator();
		//Test valid LDDDL state
		try {
			assertTrue(validator.isValid("C111A"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		validator = new ProductNameValidator();
		//Test valid LLDDDL state
		try {
			assertTrue(validator.isValid("CS111A"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		validator = new ProductNameValidator();
		//Test valid LLLDDDL state
		try {
			assertTrue(validator.isValid("CSC111A"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		validator = new ProductNameValidator();
		//Test valid LLLLDDDL state
		try {
			assertTrue(validator.isValid("CSCA111A"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		validator = new ProductNameValidator();
		//Test invalid LLLLL state
		try {
			validator.isValid("CSCAA");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		}
		
		validator = new ProductNameValidator();
		//Test invalid LDL state
		try {
			validator.isValid("A1A");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		validator = new ProductNameValidator();
		//Test invalid LDDL state
		try {
			validator.isValid("A11A");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		validator = new ProductNameValidator();
		//Test invalid LDDDD state
		try {
			validator.isValid("A1111");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		
		validator = new ProductNameValidator();
		//Test invalid LDDDLL state
		try {
			validator.isValid("A111LL");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}
		
		validator = new ProductNameValidator();
		//Test invalid LDDDLD state
		try {
			validator.isValid("A111L9");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}
	}
}
