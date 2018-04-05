package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import com.circa.mrv.grs_manager.io.UserRecordIO;
import com.circa.mrv.grs_manager.user.Employee;
import com.circa.mrv.grs_manager.util.LinkedAbstractList;

/**
 * implements tests that check the functionality of FacultyRecordIO class.
 * @author Ben
 *
 */
public class FacultyRecordIOTest {

	/** actual Faculty file that was created by writeFacultyRecords */
	private final String actualOutputFile = "test-files/actual_faculty_records.txt";
	/** valid Faculty file*/
	private final String validTestFile = "test-files/faculty_records.txt";
	/** count of valid records in valid faculty file */
	private final int validTestFileFacultyCount = 8;
	/** invalid faculty file */
	private final String invalidTestFile = "test-files/invalid_faculty_records.txt";
	/** count of valid records in invalid faculty file */
	private final int invalidTestFileFacultyCount = 0;
	/** expected results for valid faculty */
	private final String validFaculty1 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,pw,2";
	private final String validFaculty2 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,pw,3";
	private final String validFaculty3 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,pw,1";
	private final String validFaculty4 = "Halla,Aguirre,haguirr,Fusce.dolor.quam@amalesuadaid.net,pw,3";
	private final String validFaculty5 = "Kevyn,Patel,kpatel,risus@pellentesque.ca,pw,1";
	private final String validFaculty6 = "Elton,Briggs,ebriggs,arcu.ac@ipsumsodalespurus.edu,pw,3";
	private final String validFaculty7 = "Norman,Brady,nbrady,pede.nonummy@elitfermentum.co.uk,pw,1";
	private final String validFaculty8 = "Lacey,Walls,lwalls,nascetur.ridiculus.mus@fermentum.net,pw,2";
	/** Array to hold the valid Faculty and that gets processed for pw hashing */
	private final String[] validFaculty = {validFaculty1, validFaculty2, validFaculty3, validFaculty4, validFaculty5, 
			validFaculty6, validFaculty7, validFaculty8};
	
	private String hashPW;
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * changes the password strings in the expected results for valid faculty to be hashed values
	 */
	@Before
	public void setUp() {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = new String(digest.digest());
	        
	        for (int i = 0; i < validFaculty.length; i++) {
	            validFaculty[i] = validFaculty[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}

	/**
	 * Tests the readFacultyRecords method of FacultyRecordIO. tests that the correct number of records
	 * are read and that the records are in the correct order and have the correct information. Also 
	 * tests an invalid file read to ensure that the no records are read in.  
	 */
	@Test
	public void testReadFacultyRecords() {
		//Test for correct number of read records and for validity of records and their order
		try {
			LinkedAbstractList<Employee> faculty = UserRecordIO.readEmployeeRecords(validTestFile);
			assertEquals(validTestFileFacultyCount, faculty.size());
			
			for (int i = 0 ; i < faculty.size(); i++)
				assertEquals(faculty.get(i).toString(), validFaculty[i]);
			
		} catch (FileNotFoundException e) {
			fail();
		}
		
		//Test for handling invalid records file
		try {
			LinkedAbstractList<Employee> faculty = UserRecordIO.readEmployeeRecords(invalidTestFile);
			assertEquals(invalidTestFileFacultyCount, faculty.size());
			
//			for (int i = 0 ; i < students.size(); i++)
//				assertEquals(students.get(i).toString(), validStudents[i]);
			
		} catch (FileNotFoundException e) {
			fail();
		}
		
//		try{
//		LinkedList<Faculty> faculty = FacultyRecordIO.readFacultyRecords("test-files/test_StudentRecordIO.txt");
//		assertEquals(1, faculty.size());
//		} catch(FileNotFoundException e) {
//			fail();
//		}
	}
	
	/**
	 * tests the writeStudentRecords method. reads in a file and then verifies that the written out file
	 * matches the one that was read. the check is made line by line. 
	 */
	@Test
	public void testWriteFacultyRecordsNoPermissions() {
	    
		try {
			UserRecordIO.writeUserRecords(actualOutputFile, UserRecordIO.readEmployeeRecords(validTestFile));
			//checkFiles(validTestFile, actualOutputFile);
			
		} catch (IOException e1) {
			fail();
		}
		
		
		
		LinkedAbstractList<Employee> faculty = new LinkedAbstractList<Employee>(100);
	    faculty.add(new Employee("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 2));
	    //Assumption that you are using a hash of "pw" stored in hashPW 
	    
	    try {
	    	UserRecordIO.writeUserRecords("/home/sesmith5/actual_faculty_records.txt", faculty);
	        fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
	    } catch (IOException e) {
	        assertEquals("/home/sesmith5/actual_faculty_records.txt (Permission denied)", e.getMessage());
	        //The actual error message on Jenkins!
	    }
	    
	    checkFiles("ts-test-files/expected_faculty_records.txt", "ts-test-files/actual_faculty_records.txt");
	}

	private void checkFiles(String expFile, String actFile) {
	    try {
	        Scanner expScanner = new Scanner(new FileInputStream(expFile));
	        Scanner actScanner = new Scanner(new FileInputStream(actFile));
	        
	        while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
	            String exp = expScanner.nextLine();
	            String act = actScanner.nextLine();
	            assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
	        }
	        if (expScanner.hasNextLine()) {
	            fail("The expected results expect another line " + expScanner.nextLine());
	        }
	        if (actScanner.hasNextLine()) {
	            fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
	        }
	        
	        expScanner.close();
	        actScanner.close();
	    } catch (IOException e) {
	        fail("Error reading files.");
	    }
	}
}
