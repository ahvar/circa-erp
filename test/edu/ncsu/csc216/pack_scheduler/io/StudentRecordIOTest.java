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

import com.circa.mrv.grs_manager.io.MorrisvilleRecordIO;
import com.circa.mrv.grs_manager.user.Morrisville;

import edu.ncsu.csc216.collections.list.SortedList;

/**
 * implements tests that check the functionality of StudentRecordIO class.
 * @author Ben
 *
 */
public class StudentRecordIOTest {
	/** actual students file that was created by writeStudentRecords */
	private final String actualOutputFile = "test-files/actual_student_records.txt";
	/** valid students file*/
	private final String validTestFile = "test-files/student_records.txt";
	/** count of valid records in valid students file */
	private final int validTestFileStudentCount = 10;
	/** invalid students file */
	private final String invalidTestFile = "test-files/invalid_student_records.txt";
	/** count of valid records in invalid students file */
	private final int invalidTestFileStudentCount = 0;
	/** expected results for valid students */
	private final String validStudent1 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	private final String validStudent2 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	private final String validStudent3 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	private final String validStudent4 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	private final String validStudent5 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	private final String validStudent6 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	private final String validStudent7 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	private final String validStudent8 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	private final String validStudent9 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	private final String validStudent10 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";
	/** Array to hold the valid students and that gets processed for pw hashing */
	private final String[] validStudents = {validStudent4, validStudent7, validStudent5, validStudent6, validStudent3, 
			validStudent9, validStudent1, validStudent10, validStudent2, validStudent8};
	
	
	private String hashPW;
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * changes the password strings in the expected results for valid students to be hashed values
	 */
	@Before
	public void setUp() {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = new String(digest.digest());
	        
	        for (int i = 0; i < validStudents.length; i++) {
	            validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}

	/**
	 * Tests the readStudentRecords method of StudentRecordIO. tests that the correct number of records
	 * are read and that the records are in the correct order and have the correct information. Also 
	 * tests an invalid file read to ensure that the no records are read in.  
	 */
	@Test
	public void testReadStudentRecords() {
		//Test for correct number of read records and for validity of records and their order
		try {
			SortedList<Morrisville> students = MorrisvilleRecordIO.readStudentRecords(validTestFile);
			assertEquals(validTestFileStudentCount, students.size());
			
			for (int i = 0 ; i < students.size(); i++)
				assertEquals(students.get(i).toString(), validStudents[i]);
			
		} catch (FileNotFoundException e) {
			fail();
		}
		
		//Test for handling invalid records file
		try {
			SortedList<Morrisville> students = MorrisvilleRecordIO.readStudentRecords(invalidTestFile);
			assertEquals(invalidTestFileStudentCount, students.size());
			
//			for (int i = 0 ; i < students.size(); i++)
//				assertEquals(students.get(i).toString(), validStudents[i]);
			
		} catch (FileNotFoundException e) {
			fail();
		}
		
		try{
		SortedList<Morrisville> students = MorrisvilleRecordIO.readStudentRecords("test-files/test_StudentRecordIO.txt");
		assertEquals(1, students.size());
		} catch(FileNotFoundException e) {
			fail();
		}
	}
	
	/**
	 * tests the writeStudentRecords method. reads in a file and then verifies that the written out file
	 * matches the one that was read. the check is made line by line. 
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
	    
		try {
			MorrisvilleRecordIO.writeStudentRecords(actualOutputFile, MorrisvilleRecordIO.readStudentRecords(validTestFile));
			//checkFiles(validTestFile, actualOutputFile);
			
		} catch (IOException e1) {
			fail();
		}
		
		
		
		SortedList<Morrisville> students = new SortedList<Morrisville>();
	    students.add(new Morrisville("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
	    //Assumption that you are using a hash of "pw" stored in hashPW 
	    
	    try {
	        MorrisvilleRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students);
	        fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
	    } catch (IOException e) {
	        assertEquals("/home/sesmith5/actual_student_records.txt (Permission denied)", e.getMessage());
	        //The actual error message on Jenkins!
	    }
	    
	    checkFiles("ts-test-files/expected_student_records.txt", "ts-test-files/actual_student_records.txt");
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
