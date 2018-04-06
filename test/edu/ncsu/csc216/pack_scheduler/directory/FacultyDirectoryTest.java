package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.*;

//import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.Before;
import org.junit.Test;

import com.circa.mrv.grs_manager.directory.CustomerDirectory;

/**
 * Tests StudentDirectory. 
 * @author Sarah Heckman
 */
public class FacultyDirectoryTest {
	
	/** Valid Faculty records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Stu";
	/** Test last name */
	private static final String LAST_NAME = "Dent";
	/** Test id */
	private static final String ID = "sdent";
	/** Test email */
	private static final String EMAIL = "sdent@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_COURSES = 3;
	
	/**
	 * Resets faculty_records.txt for use in other tests.
	 * @throws Exception if something fails during setup. 
	 */
	@Before
	public void setUp() throws Exception {		
		//Reset Faculty_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_faculty_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "faculty_record.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests facultyDirectory().
	 */
	@Test
	public void testFacultyDirectory() {
		//Test that the FacultyDirectory is initialized to an empty list
		CustomerDirectory sd = new CustomerDirectory();
		assertFalse(sd.removeFaculty("sesmith5"));
		assertEquals(0, sd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.testNewFacultyDirectory().
	 */
	@Test
	public void testNewFacultyDirectory() {
		//Test that if there are Faculty in the directory, they 
		//are removed after calling newFacultyDirectory().
		CustomerDirectory sd = new CustomerDirectory();
		
		sd.loadEmployeeFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);
		
		sd.newFacultyDirectory();
		assertEquals(0, sd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.loadFacultyFromFile().
	 */
	@Test
	public void testLoadFacultyFromFile() {
		CustomerDirectory sd = new CustomerDirectory();
				
		//Test valid file
		sd.loadEmployeeFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);
		//Test invalid file
		try {
			sd = new CustomerDirectory();
			sd.loadEmployeeFromFile("invalidFile.txt");
		} catch (IllegalArgumentException e) {
			assertEquals(0, sd.getFacultyDirectory().length);	
		}
	}

	/**
	 * Tests FacultyDirectory.addFaculty().
	 */
	@Test
	public void testAddFaculty() {
		CustomerDirectory sd = new CustomerDirectory();
		
		//Test valid Student
		sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		String [][] facultyDirectory = sd.getFacultyDirectory();
		assertEquals(1, facultyDirectory.length);
		assertEquals(FIRST_NAME, facultyDirectory[0][0]);
		assertEquals(LAST_NAME, facultyDirectory[0][1]);
		assertEquals(ID, facultyDirectory[0][2]);
		//Test invalid Faculty
		try{
		sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, null, PASSWORD, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e){
			facultyDirectory = sd.getFacultyDirectory();
			assertEquals(1, facultyDirectory.length);			
		}
		//Test invalid Faculty
		try{
		sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "", PASSWORD, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e){
			facultyDirectory = sd.getFacultyDirectory();
			assertEquals(1, facultyDirectory.length);			
		}
		//Test invalid Faculty
		try{
		sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, null, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e){
			facultyDirectory = sd.getFacultyDirectory();
			assertEquals(1, facultyDirectory.length);			
		}
		//Test invalid Faculty
		try{
		sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "", MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e){
			facultyDirectory = sd.getFacultyDirectory();
			assertEquals(1, facultyDirectory.length);			
		}
		//Test invalid Faculty
		try{
		sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "pass", MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e){
			facultyDirectory = sd.getFacultyDirectory();
			assertEquals(1, facultyDirectory.length);			
		}
		//Test duplicate Faculty
		assertFalse(sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES));
		//Test additional Faculty
		sd.addFaculty(FIRST_NAME, LAST_NAME, "1234", EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		facultyDirectory = sd.getFacultyDirectory();
		assertEquals(2, facultyDirectory.length);	
	}
	

	/**
	 * Tests FacultyDirectory.removeFaculty().
	 */
	@Test
	public void testRemoveFaculty() {
		CustomerDirectory sd = new CustomerDirectory();
				
		//Add students and remove
		sd.loadEmployeeFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);
		assertTrue(sd.removeFaculty("fmeadow"));
		String [][] facultyDirectory = sd.getFacultyDirectory();
		assertEquals(7, facultyDirectory.length);
		assertEquals("Brent", facultyDirectory[1][0]);
		assertEquals("Brewer", facultyDirectory[1][1]);
		assertEquals("bbrewer", facultyDirectory[1][2]);
	}

	/**
	 * Tests FacultyDirectory.saveFacultyDirectory().
	 */
	@Test
	public void testSaveFacultyDirectory() {
		CustomerDirectory sd = new CustomerDirectory();
		
		//Add a Faculty
		sd.addFaculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 3);
		assertEquals(1, sd.getFacultyDirectory().length);
//		sd.saveFacultyDirectory("test-files/actual_student_records.txt");
//		checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
	}
	
//	/**
//	 * Helper method to compare two files for the same contents
//	 * @param expFile expected output
//	 * @param actFile actual output
//	 */
//	private void checkFiles(String expFile, String actFile) {
//		try {
//			Scanner expScanner = new Scanner(new FileInputStream(expFile));
//			Scanner actScanner = new Scanner(new FileInputStream(actFile));
//			
//			while (expScanner.hasNextLine()) {
//				assertEquals(expScanner.nextLine(), actScanner.nextLine());
//			}
//			
//			expScanner.close();
//			actScanner.close();
//		} catch (IOException e) {
//			fail("Error reading files.");
//		}
//	}
	
	/**
	 * Tests the getFacultyById method and ensures that the correct Faculty is returned if it exists in the directory. If the student doesnt exist, 
	 * null is returned.
	 */
	@Test
	public void testgetFacultyById(){
		
		CustomerDirectory sd = new CustomerDirectory();
		
		sd.addFaculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 3);
		assertEquals("Zahir", sd.getEmployeeById("zking").getFirstName());
		assertEquals(null, sd.getEmployeeById("invalid"));
	}

}
