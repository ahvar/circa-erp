/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import com.circa.mrv.grs_manager.catalog.NioxCatalog;
import com.circa.mrv.grs_manager.niox.Mino;;

/**
 * Provides multiple tests to assess the various methods of the CourseCatalog class.
 * @author Ben Ioppolo
 *
 */
public class CourseCatalogTest {

	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_course_records.txt";
	/** file does not exist */
	private final String doesNotExistFile = "test-files/abcdefg.txt";
	
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Programming Concepts - Java";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 4;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = null;
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	/** enrollment cap */
	private static final int ENROLLMENT_CAP = 10;
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if files don't exist
	 */
	@Before
	public void setUp() throws Exception {
		//Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}
	
	/**
	 * Tests CourseCatalog().
	 */
	@Test
	public void testCourseCatalog() {
		//Test that the CourseCatalog is initialized to an empty list
		NioxCatalog cc = new NioxCatalog();
		assertFalse(cc.removeProductFromCatalog(NAME, SECTION));
		assertEquals(0, cc.getNioxCatalog().length);
	}	
	
	/**
	 * Tests how the loadCoursesFromFile method handles being passed invalid, non existent, or valid files. 
	 */
	@Test
	public void testLoadCoursesFromFile() {
		
		//test with file that doesn't exist
		NioxCatalog cc = new NioxCatalog();
		try{
			cc.loadProductsFromFile(doesNotExistFile);
			fail();
		} catch(IllegalArgumentException e){
			assertEquals(0, cc.getNioxCatalog().length);
		}

		//Test with invalid file.  Should have an empty catalog and schedule. 

			cc.loadProductsFromFile(invalidTestFile);
			assertEquals(0, cc.getNioxCatalog().length);			

		
		//Test with valid file containing 8 courses.  Will test other methods in other tests.
		try{
			cc.loadProductsFromFile(validTestFile);
			assertEquals(8, cc.getNioxCatalog().length);			
		} catch (IllegalArgumentException e) {
			fail();
		}
		
	}
	
	/**
	 * Test CourseCatalog.getCourseFromCatalog().
	 */
	@Test
	public void testGetCourseFromCatalog() {
		NioxCatalog cc = new NioxCatalog();
		cc.loadProductsFromFile(validTestFile);
				
		//Attempt to get a course that doesn't exist
		assertNull(cc.getProductFromCatalog("CSC492", "001"));
		
		//Attempt to get a course that does exist
		Mino c = new Mino(NAME, TITLE, SECTION, CREDITS, null, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(c, cc.getProductFromCatalog("CSC216", "001"));
	}
	
	/**
	 * Test CourseCatalog.addCourseToCatalog().
	 */
	@Test
	public void testaddCourseToCatalog() {
		NioxCatalog cc = new NioxCatalog();
		cc.loadProductsFromFile(validTestFile);
		
		//Attempt to add a course that does exist on catalog
		assertFalse(cc.addProductToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals(8, cc.getNioxCatalog().length);

		
		//Attempt to add a course that is not on catalog 
		try{
		assertTrue(cc.addProductToCatalog("newcrs", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		fail();
		} catch(IllegalArgumentException e){
			assertEquals("Invalid Name", e.getMessage());
		}
	}
	
	
	/**
	 * Test CourseCatalog.removeCourseFromCatalog().
	 */
	@Test
	public void testremoveCourseFromCatalog() {
		NioxCatalog cc = new NioxCatalog();
		cc.loadProductsFromFile(validTestFile);
		
		//Attempt to remove incorrect name and schedule from empty schedule
		assertFalse(cc.removeProductFromCatalog("not on list", SECTION));
		assertFalse(cc.removeProductFromCatalog(NAME, "not on list"));
		
		//Add some courses and remove them
		try{
		assertTrue(cc.addProductToCatalog("newCrs", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		fail();
		} catch (IllegalArgumentException e){
			assertEquals("Invalid Name", e.getMessage());
		}
		assertTrue(cc.addProductToCatalog(NAME, TITLE, "111", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));

		assertEquals(9, cc.getNioxCatalog().length);

		//Remove CSC226
		assertTrue(cc.removeProductFromCatalog(NAME, SECTION));
		assertEquals(8, cc.getNioxCatalog().length);

	}
	
	/**
	 * Test CourseCatalog.getCourseCatalog().
	 */
	@Test
	public void testGetCourseCatalog() {
		NioxCatalog cc = new NioxCatalog();
		cc.loadProductsFromFile(validTestFile);
				
		//Get the catalog and make sure contents are correct 
		//Name, section, title
		String [][] catalog = cc.getNioxCatalog();
		//Row 0
		assertEquals("CSC116", catalog[0][0]);
		assertEquals("001", catalog[0][1]);
		assertEquals("Intro to Programming - Java", catalog[0][2]);
		assertEquals("MW 9:10AM-11:00AM", catalog[0][3]);
		//Row 1
		assertEquals("CSC116", catalog[1][0]);
		assertEquals("002", catalog[1][1]);
		assertEquals("Intro to Programming - Java", catalog[1][2]);
		assertEquals("MW 11:20AM-1:10PM", catalog[1][3]);
		//Row 2
		assertEquals("CSC116", catalog[2][0]);
		assertEquals("003", catalog[2][1]);
		assertEquals("Intro to Programming - Java", catalog[2][2]);
		assertEquals("TH 11:20AM-1:10PM", catalog[2][3]);
		//Row 3
		assertEquals("CSC216", catalog[3][0]);
		assertEquals("001", catalog[3][1]);
		assertEquals("Programming Concepts - Java", catalog[3][2]);
		assertEquals("TH 1:30PM-2:45PM", catalog[3][3]);
		//Row 4
		assertEquals("CSC216", catalog[4][0]);
		assertEquals("002", catalog[4][1]);
		assertEquals("Programming Concepts - Java", catalog[4][2]);
		assertEquals("MW 1:30PM-2:45PM", catalog[4][3]);
		//Row 5
		assertEquals("CSC216", catalog[5][0]);
		assertEquals("601", catalog[5][1]);
		assertEquals("Programming Concepts - Java", catalog[5][2]);
		assertEquals("Arranged", catalog[5][3]);
		//Row 6
		assertEquals("CSC226", catalog[6][0]);
		assertEquals("001", catalog[6][1]);
		assertEquals("Discrete Mathematics for Computer Scientists", catalog[6][2]);
		assertEquals("MWF 9:35AM-10:25AM", catalog[6][3]);
		//Row 7
		assertEquals("CSC230", catalog[7][0]);
		assertEquals("001", catalog[7][1]);
		assertEquals("C and Software Tools", catalog[7][2]);
		assertEquals("MW 11:45AM-1:00PM", catalog[7][3]);
	}
	
	/**
	 * Test CourseCatalog.getCourseFromCatalog().
	 */
	@Test
	public void testgetCourseFromCatalog() {
		NioxCatalog cc = new NioxCatalog();
		cc.loadProductsFromFile(validTestFile);
		Mino course = new Mino(NAME, TITLE, SECTION, CREDITS, null, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);	
		Mino c = cc.getProductFromCatalog(NAME, SECTION);
		assertTrue(c.equals(course));
	}
	
	/**
	 * Test CourseCatalog.exportSchedule().
	 */
	@Test
	public void testSaveCourseCatalog() {
		//Test that empty schedule exports correctly 
		NioxCatalog cc = new NioxCatalog();

		
		cc.saveCourseCatalog("test-files/actual_empty_export.txt");
		checkFiles("test-files/expected_empty_export.txt", "test-files/actual_empty_export.txt");
		
		cc.loadProductsFromFile(validTestFile);		
		//Add courses and test that exports correctly
		assertEquals(8, cc.getNioxCatalog().length);
		cc.saveCourseCatalog("test-files/actual_catalog_export.txt");
		checkFiles("test-files/expected_catalog_export.txt", "test-files/actual_catalog_export.txt");
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new File (expFile));
			Scanner actScanner = new Scanner(new File(actFile));
			
			while (actScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			if (expScanner.hasNextLine()) {
				fail();
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
