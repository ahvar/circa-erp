package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * the StudentTest class provides test coverage for the Student class.
 * @author Ben Ioppolo
 *
 */
public class StudentTest {
	
	/**
	 * Tests the main constructor in student for valid and invalid students.
	 */
	@Test
	public void testStudentStringStringStringStringStringInt() {
		//test valid student creation
		User s = null;
		try{
		Student sValid = new Student("first", "last", "id", "email@ncsu.edu", "hashpassword", 6);
		assertEquals("first", sValid.getFirstName());
		assertEquals("last", sValid.getLastName());
		assertEquals("id", sValid.getId());
		assertEquals("email@ncsu.edu", sValid.getEmail());
		assertEquals("hashpassword", sValid.getPassword());
		assertEquals(6, sValid.getMaxCredits());		
		} catch (IllegalArgumentException e){
			fail();
		}
		//test invalid first name
		try {
		    s = new Student(null, "last", "id", "email@ncsu.edu", "hashedpassword", 6);
		    //Note that for testing purposes, the password doesn't need to be hashedpassword
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}
		
		//test invalid last name
		try{
			s = new Student("first", null, "id", "email@ncsu.edu", "hashedpassword", 6);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		//test invalid last name
		try{
			s = new Student("first", null, "id", "email@ncsu.edu", "hashedpassword", 6);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}	
		
		//test invalid id
		try{
			s = new Student("first", "last", null, "email@ncsu.edu", "hashedpassword", 6);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//test invalid id
		try{
			s = new Student("first", "last", "", "email@ncsu.edu", "hashedpassword", 6);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		//test invalid email
		try{
			s = new Student("first", "last", "id", null, "hashedpassword", 6);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		//test invalid password
		try{
			s = new Student("first", "last", "id", "email@ncsu.edu", null, 6);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		//test invalid maxcredits
		try{
			s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}	
	}

	/**
	 * Tests the all string constructor for student for valid and invalid students.
	 */
	@Test
	public void testStudentStringStringStringStringString() {
		//test valid student creation
		User s = null;
		try{
		Student sValid = new Student("first", "last", "id", "email@ncsu.edu", "hashpassword");
		assertEquals("first", sValid.getFirstName());
		assertEquals("last", sValid.getLastName());
		assertEquals("id", sValid.getId());
		assertEquals("email@ncsu.edu", sValid.getEmail());
		assertEquals("hashpassword", sValid.getPassword());
		assertEquals(18, sValid.getMaxCredits());		
		} catch (IllegalArgumentException e){
			fail();
		}
		//test invalid first name
		try {
		    s = new Student(null, "last", "id", "email@ncsu.edu", "hashedpassword");
		    //Note that for testing purposes, the password doesn't need to be hashedpassword
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}
		
		//test invalid last name
		try{
			s = new Student("first", null, "id", "email@ncsu.edu", "hashedpassword");
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		//test invalid last name
		try{
			s = new Student("first", null, "id", "email@ncsu.edu", "hashedpassword");
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}	
		
		//test invalid id
		try{
			s = new Student("first", "last", null, "email@ncsu.edu", "hashedpassword");
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		//test invalid email
		try{
			s = new Student("first", "last", "id", null, "hashedpassword");
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		//test invalid password
		try{
			s = new Student("first", "last", "id", "email@ncsu.edu", null);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

	}

	/**
	 * tests the set first name method of student for valid empty and null errors
	 */
	@Test
	public void testSetFirstName() {
		//Construct a valid Student
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 6);
		
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
		    assertEquals(6, s.getMaxCredits());
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
		    assertEquals(6, s.getMaxCredits());
		}	
	}

	/**
	 * tests the set last name method of student for empty and null set errors
	 */
	@Test
	public void testSetLastName() {
		//Construct a valid Student
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 6);
		
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
		    assertEquals(6, s.getMaxCredits());
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
		    assertEquals(6, s.getMaxCredits());
		}	
	}

	/**
	 * tests the set email method of student for empty and null set errors as well as missing ".", 
	 * missing "@", and out of order . and "@"
	 */
	@Test
	public void testSetEmail() {
		//Construct a valid Student
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 6);
		
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
		    assertEquals(6, s.getMaxCredits());
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
		    assertEquals(6, s.getMaxCredits());
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
		    assertEquals(6, s.getMaxCredits());
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
		    assertEquals(6, s.getMaxCredits());
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
		    assertEquals(6, s.getMaxCredits());
		}		
	}

	/**
	 * tests the set password method of student for empty and null set errors
	 */
	@Test
	public void testSetPassword() {
		//Construct a valid Student
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 6);
		
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
		    assertEquals(6, s.getMaxCredits());
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
		    assertEquals(6, s.getMaxCredits());
		}
	}

	/**
	 * tests the set max credits method of student for below 3 and above 18 errors
	 */
	@Test
	public void testSetMaxCredits() {
		//Construct a valid Student
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 6);
		
		//test invalid set to below 3
		try {
		    s.setMaxCredits(2);
		    fail(); //We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
		    //We've caught the exception, now we need to make sure that the field didn't change
		    assertEquals("first", s.getFirstName());
		    assertEquals("last", s.getLastName());
		    assertEquals("id", s.getId());
		    assertEquals("email@ncsu.edu", s.getEmail());
		    assertEquals("hashedpassword", s.getPassword());
		    assertEquals(6, s.getMaxCredits());
		}
		
		//test invalid set to above 18 
		try {
		    s.setMaxCredits(19);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
		    assertEquals("last", s.getLastName());
		    assertEquals("id", s.getId());
		    assertEquals("email@ncsu.edu", s.getEmail());
		    assertEquals("hashedpassword", s.getPassword());
		    assertEquals(6, s.getMaxCredits());
		}
	}
	
	/**
	 * tests whether two students are equal, less than, or greater than one another when comparing their last name
	 * then their first name, then their Id.
	 */
	@Test
	public void testCompareTo() {
		//Construct valid Students 
		Student s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 6);
		Student s2 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 6);
		Student s3 = new Student("a", "last", "id", "email@ncsu.edu", "hashedpassword", 6);		
		
		assertEquals(0, s1.compareTo(s2));
		assertEquals(0, s2.compareTo(s1));
		assertTrue(0 < s1.compareTo(s3));
		assertTrue(0 > s3.compareTo(s1));
	}

	/**
	 * tests the equals method of student for symmetry, fields, object equals itself, class equivalence,
	 * and null object
	 */
	@Test
	public void testEqualsObject() {
		//Construct valid Students
		User s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 6);
		User s2 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 6);
		User s3 = new Student("a", "last", "id", "email@ncsu.edu", "hashedpassword", 6);
		User s4 = new Student("first", "b", "id", "email@ncsu.edu", "hashedpassword", 6);
		User s5 = new Student("first", "last", "c", "email@ncsu.edu", "hashedpassword", 6);
		User s6 = new Student("first", "last", "id", "d@ncsu.edu", "hashedpassword", 6);
		User s7 = new Student("first", "last", "id", "email@ncsu.edu", "e", 6);
		User s8 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 7);
		
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
	 * tests hash code method of student for symmetry, and field equivalence
	 */
	@Test
	public void testHashCode() {
		//Construct valid Students
		User s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 6);
		User s2 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 6);
		User s3 = new Student("a", "last", "id", "email@ncsu.edu", "hashedpassword", 6);
		User s4 = new Student("first", "b", "id", "email@ncsu.edu", "hashedpassword", 6);
		User s5 = new Student("first", "last", "c", "email@ncsu.edu", "hashedpassword", 6);
		User s6 = new Student("first", "last", "id", "d@ncsu.edu", "hashedpassword", 6);
		User s7 = new Student("first", "last", "id", "email@ncsu.edu", "e", 6);
		User s8 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 7);
		
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
		User s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 6);
		User s2 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");		
		//Expected strings
		String str1 = "first,last,id,email@ncsu.edu,hashedpassword,6";
		String str2 = "first,last,id,email@ncsu.edu,hashedpassword,18";	
		//Test toString
		assertEquals(str1, s1.toString());
		assertEquals(str2, s2.toString());		
	}

}
