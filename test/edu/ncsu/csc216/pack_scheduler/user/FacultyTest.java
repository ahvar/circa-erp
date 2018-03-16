/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Provides tests for Faculty class.
 * @author Arthur Vargas
 */
public class FacultyTest {

	/**
	 * Tests the main constructor in faculty for valid and invalid faculty.
	 */
	@Test
	public void testFacultyStringStringStringStringStringInt() {
		//test valid Faculty creation
		User s = null;
		try{
		Faculty fValid = new Faculty("first", "last", "id", "email@ncsu.edu", "hashpassword", 2);
		assertEquals("first", fValid.getFirstName());
		assertEquals("last", fValid.getLastName());
		assertEquals("id", fValid.getId());
		assertEquals("email@ncsu.edu", fValid.getEmail());
		assertEquals("hashpassword", fValid.getPassword());
		assertEquals(2, fValid.getMaxCourses());		
		} catch (IllegalArgumentException e){
			fail();
		}
		//test invalid first name
		try {
		    s = new Faculty(null, "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		    //Note that for testing purposes, the password doesn't need to be hashedpassword
		    fail(); //If we reach this point a Faculty was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}
		
		//test invalid last name
		try{
			s = new Faculty("first", null, "id", "email@ncsu.edu", "hashedpassword", 2);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		//test invalid last name
		try{
			s = new Faculty("first", null, "id", "email@ncsu.edu", "hashedpassword", 2);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}	
		
		//test invalid id
		try{
			s = new Faculty("first", "last", null, "email@ncsu.edu", "hashedpassword", 2);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//test invalid id
		try{
			s = new Faculty("first", "last", "", "email@ncsu.edu", "hashedpassword", 2);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		//test invalid email
		try{
			s = new Faculty("first", "last", "id", null, "hashedpassword", 2);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		//test invalid password
		try{
			s = new Faculty("first", "last", "id", "email@ncsu.edu", null, 2);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		//test invalid maxCourses
		try{
			s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 5);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}	
	}

	/**
	 * Tests the all string constructor for Faculty for valid and invalid Faculty.
	 */
	@Test
	public void testFacultyStringStringStringStringString() {
		//test valid Faculty creation
		User s = null;
		try{
		Faculty fValid = new Faculty("first", "last", "id", "email@ncsu.edu", "hashpassword");
		assertEquals("first", fValid.getFirstName());
		assertEquals("last", fValid.getLastName());
		assertEquals("id", fValid.getId());
		assertEquals("email@ncsu.edu", fValid.getEmail());
		assertEquals("hashpassword", fValid.getPassword());
		assertEquals(3, fValid.getMaxCourses());		
		} catch (IllegalArgumentException e){
			fail();
		}
		//test invalid first name
		try {
		    s = new Faculty(null, "last", "id", "email@ncsu.edu", "hashedpassword");
		    //Note that for testing purposes, the password doesn't need to be hashedpassword
		    fail(); //If we reach this point a Faculty was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}
		
		//test invalid last name
		try{
			s = new Faculty("first", null, "id", "email@ncsu.edu", "hashedpassword");
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		//test invalid last name
		try{
			s = new Faculty("first", null, "id", "email@ncsu.edu", "hashedpassword");
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}	
		
		//test invalid id
		try{
			s = new Faculty("first", "last", null, "email@ncsu.edu", "hashedpassword");
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		//test invalid email
		try{
			s = new Faculty("first", "last", "id", null, "hashedpassword");
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		//test invalid password
		try{
			s = new Faculty("first", "last", "id", "email@ncsu.edu", null);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

	}

	/**
	 * tests the set first name method of Faculty for valid empty and null errors
	 */
	@Test
	public void testSetFirstName() {
		//Construct a valid Faculty
		Faculty s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		
		//test invalid set to null
		try {
		    s.setFirstName(null);
		    fail(); //We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
		    //We've caught the exception, now we need to make sure that the field didn't change
		    assertEquals("first", s.getFirstName());
		    assertEquals("last", s.getLastName());
		    assertEquals("id", s.getId());
		    assertEquals("email@ncsu.edu", s.getEmail());
		    assertEquals("hashedpassword", s.getPassword());
		    assertEquals(2, s.getMaxCourses());
		}
		
		//test invalid set to empty
		try {
		    s.setFirstName("");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
		    assertEquals("last", s.getLastName());
		    assertEquals("id", s.getId());
		    assertEquals("email@ncsu.edu", s.getEmail());
		    assertEquals("hashedpassword", s.getPassword());
		    assertEquals(2, s.getMaxCourses());
		}	
	}

	/**
	 * tests the set last name method of Faculty for empty and null set errors
	 */
	@Test
	public void testSetLastName() {
		//Construct a valid Faculty
		Faculty s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		
		//test invalid set to null
		try {
		    s.setLastName(null);
		    fail(); //We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
		    //We've caught the exception, now we need to make sure that the field didn't change
		    assertEquals("first", s.getFirstName());
		    assertEquals("last", s.getLastName());
		    assertEquals("id", s.getId());
		    assertEquals("email@ncsu.edu", s.getEmail());
		    assertEquals("hashedpassword", s.getPassword());
		    assertEquals(2, s.getMaxCourses());
		}
		
		//test invalid set to empty
		try {
		    s.setLastName("");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
		    assertEquals("last", s.getLastName());
		    assertEquals("id", s.getId());
		    assertEquals("email@ncsu.edu", s.getEmail());
		    assertEquals("hashedpassword", s.getPassword());
		    assertEquals(2, s.getMaxCourses());
		}	
	}

	/**
	 * tests the set email method of Faculty for empty and null set errors as well as missing ".", 
	 * missing "@", and out of order . and "@"
	 */
	@Test
	public void testSetEmail() {
		//Construct a valid Faculty
		Faculty s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		
		//test invalid set to null
		try {
		    s.setEmail(null);
		    fail(); //We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
		    //We've caught the exception, now we need to make sure that the field didn't change
		    assertEquals("first", s.getFirstName());
		    assertEquals("last", s.getLastName());
		    assertEquals("id", s.getId());
		    assertEquals("email@ncsu.edu", s.getEmail());
		    assertEquals("hashedpassword", s.getPassword());
		    assertEquals(2, s.getMaxCourses());
		}
		
		//test invalid set to empty
		try {
		    s.setEmail("");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
		    assertEquals("last", s.getLastName());
		    assertEquals("id", s.getId());
		    assertEquals("email@ncsu.edu", s.getEmail());
		    assertEquals("hashedpassword", s.getPassword());
		    assertEquals(2, s.getMaxCourses());
		}
		
		//test no @ in email
		try {
		    s.setEmail("email.com");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
		    assertEquals("last", s.getLastName());
		    assertEquals("id", s.getId());
		    assertEquals("email@ncsu.edu", s.getEmail());
		    assertEquals("hashedpassword", s.getPassword());
		    assertEquals(2, s.getMaxCourses());
		}		
		
		//test no . in email
		try {
		    s.setEmail("email@com");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
		    assertEquals("last", s.getLastName());
		    assertEquals("id", s.getId());
		    assertEquals("email@ncsu.edu", s.getEmail());
		    assertEquals("hashedpassword", s.getPassword());
		    assertEquals(2, s.getMaxCourses());
		}	
		
		//test out of order . and @
		try {
		    s.setEmail("email.@com");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
		    assertEquals("last", s.getLastName());
		    assertEquals("id", s.getId());
		    assertEquals("email@ncsu.edu", s.getEmail());
		    assertEquals("hashedpassword", s.getPassword());
		    assertEquals(2, s.getMaxCourses());
		}		
	}

	/**
	 * tests the set password method of Faculty for empty and null set errors
	 */
	@Test
	public void testSetPassword() {
		//Construct a valid Faculty
		Faculty s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		
		//test invalid set to null
		try {
		    s.setPassword(null);
		    fail(); //We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
		    //We've caught the exception, now we need to make sure that the field didn't change
		    assertEquals("first", s.getFirstName());
		    assertEquals("last", s.getLastName());
		    assertEquals("id", s.getId());
		    assertEquals("email@ncsu.edu", s.getEmail());
		    assertEquals("hashedpassword", s.getPassword());
		    assertEquals(2, s.getMaxCourses());
		}
		
		//test invalid set to empty
		try {
		    s.setPassword("");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
		    assertEquals("last", s.getLastName());
		    assertEquals("id", s.getId());
		    assertEquals("email@ncsu.edu", s.getEmail());
		    assertEquals("hashedpassword", s.getPassword());
		    assertEquals(2, s.getMaxCourses());
		}
	}

	/**
	 * tests the set max credits method of Faculty for below 1 and above 3 errors
	 */
	@Test
	public void testSetMaxCourses() {
		//Construct a valid Faculty
		Faculty s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		
		//test invalid set to below 1
		try {
		    s.setMaxCourses(0);
		    fail(); //We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
		    //We've caught the exception, now we need to make sure that the field didn't change
		    assertEquals("first", s.getFirstName());
		    assertEquals("last", s.getLastName());
		    assertEquals("id", s.getId());
		    assertEquals("email@ncsu.edu", s.getEmail());
		    assertEquals("hashedpassword", s.getPassword());
		    assertEquals(2, s.getMaxCourses());
		}
		
		//test invalid set to above 3 
		try {
		    s.setMaxCourses(4);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
		    assertEquals("last", s.getLastName());
		    assertEquals("id", s.getId());
		    assertEquals("email@ncsu.edu", s.getEmail());
		    assertEquals("hashedpassword", s.getPassword());
		    assertEquals(2, s.getMaxCourses());
		}
	}
	
	/**
	 * tests whether two Faculty are equal, less than, or greater than one another when comparing their last name
	 * then their first name, then their Id.
	 */
	@Test
	public void testCompareTo() {
		//Construct valid Faculty 
		Faculty f1 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		Faculty f2 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		Faculty f3 = new Faculty("a", "last", "id", "email@ncsu.edu", "hashedpassword", 2);		
		
		assertEquals(0, f1.compareTo(f2));
		assertEquals(0, f2.compareTo(f1));
		assertTrue(0 < f1.compareTo(f3));
		assertTrue(0 > f3.compareTo(f1));
	}

	/**
	 * tests the equals method of Faculty for symmetry, fields, object equals itself, class equivalence,
	 * and null object
	 */
	@Test
	public void testEqualsObject() {
		//Construct valid Students
		User s1 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		User s2 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		User s3 = new Faculty("a", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		User s4 = new Faculty("first", "b", "id", "email@ncsu.edu", "hashedpassword", 2);
		User s5 = new Faculty("first", "last", "c", "email@ncsu.edu", "hashedpassword", 2);
		User s6 = new Faculty("first", "last", "id", "d@ncsu.edu", "hashedpassword", 2);
		User s7 = new Faculty("first", "last", "id", "email@ncsu.edu", "e", 2);
		User s8 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 3);
		
		//Test s1 is equal to s2
		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));
		//Test for each of the fields
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));	
	
		//Test for each field null
		//Student sNull = null;
		assertTrue(s1.equals(s1));
		//assertFalse(s1.equals(null));
		assertFalse(s1.equals(""));
		//assertFalse(s1.getEmail().equals(null));
	}
	 
	/**
	 * tests hash code method of Faculty for symmetry, and field equivalence
	 */
	@Test
	public void testHashCode() {
		//Construct valid Students
		User s1 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		User s2 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		User s3 = new Faculty("a", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		User s4 = new Faculty("first", "b", "id", "email@ncsu.edu", "hashedpassword", 2);
		User s5 = new Faculty("first", "last", "c", "email@ncsu.edu", "hashedpassword", 2);
		User s6 = new Faculty("first", "last", "id", "d@ncsu.edu", "hashedpassword", 2);
		User s7 = new Faculty("first", "last", "id", "email@ncsu.edu", "e", 2);
		User s8 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 3);
		
		//Test s1 is equal to s2
		assertEquals(s1.hashCode(), s2.hashCode());
		assertEquals(s2.hashCode(), s1.hashCode());		
		//Test for each of the fields
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());
	}

	/**
	 * tests the to string method of student to ensure that strings are created as expected
	 */
	@Test
	public void testToString() {
		//Construct a valid Students
		User s1 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		User s2 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword");		
		//Expected strings
		String str1 = "first,last,id,email@ncsu.edu,hashedpassword,2";
		String str2 = "first,last,id,email@ncsu.edu,hashedpassword,3";	
		//Test toString
		assertEquals(str1, s1.toString());
		assertEquals(str2, s2.toString());		
	}

}
