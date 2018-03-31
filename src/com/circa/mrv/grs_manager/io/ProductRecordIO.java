/**
 * 
 */
package com.circa.mrv.grs_manager.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import com.circa.mrv.grs_manager.directory.SwedenDirectory;
import com.circa.mrv.grs_manager.manager.GRSManager;
import com.circa.mrv.grs_manager.niox.Mino;
import com.circa.mrv.grs_manager.util.LinkedListRecursive;

import edu.ncsu.csc216.collections.list.SortedList;

/**
 * ProductRecordIO class handles I/O for NIOX product records. 
 * 
 * @author Arthur Vargas
 *
 */
public class ProductRecordIO {
	String[][] data;
	
	/**
	 * Reads course records from a file and generates a list of valid Courses.
	 * Any invalid Courses are ignored. If the file to read cannot be found or
	 * the permissions are incorrect a FileNotFoundException is thrown.
	 * 
	 * @param fileName file to read Course records from
	 * @return courses a sorted list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 *             
	 */
	public static LinkedListRecursive<Product> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new File(fileName));
		while (fileReader.hasNextLine()) {
			try {
				String line = readLine(fileReader.nextLine());
				if (line != null) {
					// add to records

					//boolean duplicate = false;
					//for (int i = 0; i < courses.size(); i++) {
						//Product c = courses.get(i);
						//if (course.getName().equals(c.getName()) && course.getSection().equals(c.getSection())) {
							// it's a duplicate
							//duplicate = true;
						//}
					//}
					//if (!duplicate) {
						//courses.add(course);
					//}
				}
			} catch (IllegalArgumentException e) {
				// skip the line
			}
		}
		fileReader.close();
		return courses;
	}

	/**
	 * Reads the passed line and calls countTokens method to determine the number of parameters
	 * that the line contains. it then checks that number vs what the two constructors in Course
	 * can use (6 or 8 parameters). If one of these is true, then the appropriate constructor is 
	 * called. Course's methods then check the validity of each parameter. If neither 6 or 8 
	 * parameters are contained in the line, it is assumed to be invalid and a null Course is 
	 * returned. 
	 * @param nextLine the line that is to be processed
	 * @return c a course generated from the line
	 * @throws IllegalArgumentException if the parameters are not in appropriate order or if there
	 * is an incorrect number of parameters.
	 */
	private static Product readLine(String nextLine) {
		SwedenDirectory dir = GRSManager.getInstance().getFacultyDirectory();
		//FacultyDirectory dir = new FacultyDirectory();
		//dir.loadFacultyFromFile("test-files/faculty_records.txt");
		
		//FacultySchedule sch;
		
		String course; 
		String title;
		String section;
		int credits;
		String instructor;
		int cap;
		String meetingDays;
		int start;
		int end;

		int tokenCount = countTokens(nextLine);

		if (tokenCount == 7) {
			Scanner lineScanner = new Scanner(nextLine);
			lineScanner.useDelimiter(",");
			try {
				course = lineScanner.next();
				title = lineScanner.next();
				section = lineScanner.next();
				credits = lineScanner.nextInt();
				instructor = lineScanner.next();
				cap = lineScanner.nextInt();
				meetingDays = lineScanner.next();
				lineScanner.close();
				
				Product c = new Product(course, title, section, credits, null, cap, meetingDays);
				
				if (dir.getFacultyById(instructor) != null) {
					dir.getFacultyById(instructor).getSchedule().addCourseToSchedule(c);
				}
				
//				c.setInstructorId(instructor);
				return c;
			} catch (Exception e) {
				//Invalid parameter order
			}

		}
		if (tokenCount == 9) {
			Scanner lineScanner = new Scanner(nextLine);
			lineScanner.useDelimiter(",");
			try {
				course = lineScanner.next();
				title = lineScanner.next();
				section = lineScanner.next();
				credits = lineScanner.nextInt();
				instructor = lineScanner.next();
				cap = lineScanner.nextInt();
				meetingDays = lineScanner.next();
				start = lineScanner.nextInt();
				end = lineScanner.nextInt();
				lineScanner.close();
				
				Product c = new Product(course, title, section, credits, null, cap, meetingDays, start, end);
				
				if (dir.getFacultyById(instructor) != null) {
					dir.getFacultyById(instructor).getSchedule().addCourseToSchedule(c);
				}
				
//				c.setInstructorId(instructor);
				return c;
			} catch (Exception e) {
				//invalid parameter order
			}
		} else {
			throw new IllegalArgumentException();
		}
		// string, string, string, int, string, string
		// string, string, string, int, string, string, int, int
		// returns a course which means i need to call the course constructor
		// with the given data
		Product c = null;
		return c;
	}

	/**
	 * counts the number of tokens that exist in the passed line. uses a comma delimiter.
	 * @param nextLine line to count tokens on.
	 * @return tokenCount the number of tokens found
	 */
	private static int countTokens(String nextLine) {
		int tokenCount = 0;
		Scanner lineScanner = new Scanner(nextLine);
		lineScanner.useDelimiter(",");

		while (lineScanner.hasNext()) {
			try {
				lineScanner.next();
				tokenCount++;
			} catch (IllegalArgumentException e) {
				//Something odd would have to happen to trigger this.
			}
		}
		lineScanner.close();
		return tokenCount;
	}

	/**
	 * Writes the given list of Courses to a text file.
	 * 
	 * @param fileName
	 *            name of file to write courses to
	 * @param courses
	 *            list of courses to write
	 * @throws IOException
	 *             if file can not be written to
	 */
	public static void writeCourseRecords(String fileName, LinkedListRecursive<Product> courses) throws IOException {

		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < courses.size(); i++) {
			fileWriter.println(courses.get(i).toString());
		}

		fileWriter.close();
	}

}
