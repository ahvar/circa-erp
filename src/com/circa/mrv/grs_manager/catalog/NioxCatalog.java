/**
 * 
 */
package com.circa.mrv.grs_manager.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.circa.mrv.grs_manager.io.ProductRecordIO;
import com.circa.mrv.grs_manager.niox.Mino;
import com.circa.mrv.grs_manager.niox.Product;
import com.circa.mrv.grs_manager.util.LinkedListRecursive;

import edu.ncsu.csc216.collections.list.SortedList;

/**
 * The NioxCatalog is a list of Niox products.
 *    
 * @author Arthur Vargas 
 */
public class NioxCatalog {
/** The catalog as a Sorted list of courses */
	private LinkedListRecursive<Product> catalog;
	
	/**
	 * Null Constructor which calls the newCourseCatalog method
	 */
	public NioxCatalog() {
		newCourseCatalog();
	}
	/**
	 * Creates an empty course catalog
	 */
	public void newCourseCatalog() {
		catalog = new LinkedListRecursive<Product>();
	}
	/**
	 * Loads an input file of courses into the catalog. If the file is unable to be found an IllegalArgumentexception is thrown.
	 * @throws IllegalArgumentException if the file is unable to be found.
	 * @param fileName the filename for the file of courses to be read in to create the catalog. 
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = ProductRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	/**
	 * Adds a course to the catalog if the name and section of the course to be added does not match one of the 
	 * courses in the catalog. If the course exists on the catalog, the new course is not added. 
	 * @param name the name of the course to add
	 * @param title the title of the course to add
	 * @param section the section of the course to add
	 * @param credits the credit hours of the course to add
	 * @param instructorId the instructors Id for the course to add
	 * @param meetingDays the days that the course meets
	 * @param startTime the start time of the course in military time. 
	 * @param endTime the end time of the course in military time
	 * @return True if the course can be added to the catalog. False otherwise. 
	 * @param enrollmentCap The number of students that can register for the course
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, 
			int enrollmentCap, String meetingDays, int startTime, int endTime) {
			int isDuplicate;
			
			Product c = new Product(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
			
			for (int i = 0 ; i < catalog.size() ; i++){
				isDuplicate = catalog.get(i).compareTo(c);
				if (isDuplicate == 0)
					return false;
	
			}
				catalog.add(c);
				return true;
	}
	/**
	 * Removes a course from the catalog if the course's name and section match the passed values.
	 * @param name the name of the course to remove
	 * @param section the section of the course to remove
	 * @return True if the course can be removed. I.E. the course is on the catalog. False if the course is not on the catalog.
	 */
	public boolean removeCourseFromCatalog(String name, String section){
		
		for (int i = 0; i < catalog.size(); i++) {
			Product c = catalog.get(i);
			if (c.getName().equals(name) && c.getSection().equals(section)) {
				catalog.remove(i);
				return true;
			}
		}
		return false;
	}
	/**
	 * Gets a course from the catalog that is specified by the passed name and section. If the passed values match a course
	 * on the catalog, the course is returned. 
	 * @param name the name of the course to get
	 * @param section the section of the course to get. 
	 * @return c the course that matches the passed name and section. Returns null if no match existed.
	 */
	public Product getCourseFromCatalog(String name, String section){	
		for (int i = 0; i < catalog.size(); i++) {
			Product c = catalog.get(i);
			if (c.getName().equals(name) && c.getSection().equals(section))
				return c;
		}
		return null;
	}
	/**
	 * Gets the full course catalog which is stored as a 2D array. The rows of the array are individual courses and the 
	 * columns of the array are the name, section, title, and meeting information (days and times), respectively. 
	 * @return courseCatalog a 2D String array representing the course catalog. 
	 */
	public String[][] getCourseCatalog() {
		String [][] courseCatalog = new String[catalog.size()][5];
		for (int i = 0; i < catalog.size(); i++) {
			Product c = catalog.get(i);
			courseCatalog[i][0] = c.getName();
			courseCatalog[i][1] = c.getSection();
			courseCatalog[i][2] = c.getDescription();
			courseCatalog[i][3] = c.getMeetingString();
			courseCatalog[i][4] = "" + c.getCourseRoll().getOpenSeats();	
		}
		return courseCatalog;
	}
	/**
	 * Saves the course catalog to the passed file name. Passes the filename and the catalog to the writeCourseRecords method
	 * in the CourseRecordIO class.
	 * @param fileName the name of the file to save the course records to. 
	 * @throws IllegalArgumentException if the writeCourseRecords method is unable to write to the file.
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			ProductRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}
}
