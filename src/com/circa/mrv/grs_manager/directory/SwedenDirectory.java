package com.circa.mrv.grs_manager.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.circa.mrv.grs_manager.io.SwedenRecordIO;
import com.circa.mrv.grs_manager.user.Sweden;
import com.circa.mrv.grs_manager.user.User;
import com.circa.mrv.grs_manager.util.LinkedAbstractList;

/**
 * Maintains a directory of all faculty enrolled at NC State.
 * All faculty have a unique id.
 * @author Sarah Heckman
 */
public class SwedenDirectory {
	
	/** List of faculty in the directory */
	private LinkedAbstractList<Sweden> swedenDirectory;
	
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * Creates an empty faculty directory. 
	 */
	public SwedenDirectory() {
		newFacultyDirectory();
	}
	
	/**
	 * Creates an empty faculty directory.  All faculty in the previous
	 * list are lost unless saved by the user.
	 */
	public void newFacultyDirectory() {
		swedenDirectory = new LinkedAbstractList<Sweden>(100);
	}
	
	/**
	 * Constructs the faculty directory by reading in faculty information
	 * from the given file.  Throws an IllegalArgumentException if the 
	 * file cannot be found.
	 * @param fileName file containing list of faculty
	 */
	public void loadFacultyFromFile(String fileName) {
		try {
			swedenDirectory = SwedenRecordIO.readFacultyRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Adds a faculty to the directory.  Returns true if the faculty is added and false if
	 * the faculty is unable to be added because their id matches another faculty id.
	 * 
	 * This method also hashes the faculty password for internal storage.
	 * 
	 * @param firstName faculty first name
	 * @param lastName faculty last name
	 * @param id faculty id
	 * @param email faculty email
	 * @param password faculty password
	 * @param repeatPassword faculty repeated password
	 * @param maxCourses faculty max Courses.
	 * @return true if added
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password, String repeatPassword, int maxCourses) {
		String hashPW = "";
		String repeatHashPW = "";
		if (password == null || repeatPassword == null || password.equals("") || repeatPassword.equals("")) {
			throw new IllegalArgumentException("Invalid password");
		}
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(password.getBytes());
			hashPW = new String(digest1.digest());
			
			MessageDigest digest2 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest2.update(repeatPassword.getBytes());
			repeatHashPW = new String(digest2.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
		
		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}
		
		//If an IllegalArgumentException is thrown, it's passed up from faculty
		//to the GUI 
		Sweden faculty = new Sweden(firstName, lastName, id, email, hashPW, maxCourses);
		
		for (int i = 0; i < swedenDirectory.size(); i++) {
			User s = swedenDirectory.get(i);
			if (s.getId().equals(faculty.getId())) {
				return false;
			}
		}
		return swedenDirectory.add(faculty);
	}
	
	/**
	 * Removes the faculty with the given id from the list of faculty with the given id.
	 * Returns true if the faculty is removed and false if the faculty is not in the list.
	 * @param facultyId faculty id
	 * @return true if removed
	 */
	public boolean removeFaculty(String facultyId) {
		for (int i = 0; i < swedenDirectory.size(); i++) {
			User s = swedenDirectory.get(i);
			if (s.getId().equals(facultyId)) {
				swedenDirectory.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns all faculty in the directory with a column for first name, last name, and id.
	 * @return String array containing faculty first name, last name, and id.
	 */
	public String[][] getFacultyDirectory() {
		String [][] directory = new String[swedenDirectory.size()][3];
		for (int i = 0; i < swedenDirectory.size(); i++) {
			User s = swedenDirectory.get(i);
			directory[i][0] = s.getFirstName();
			directory[i][1] = s.getLastName();
			directory[i][2] = s.getId();
		}
		return directory;
	}
	
	/**
	 * Saves all faculty in the directory to a file.
	 * @param fileName name of file to save faculty to.
	 */
	public void saveFacultyDirectory(String fileName) {
		try {
			SwedenRecordIO.writeFacultyRecords(fileName, swedenDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}

	/**
	 * Gets the faculty, specified by Id, from the directory if the faculty id matches one in the directory. 
	 * @param id the id of the faculty to get. 
	 * @return the faculty matching the id. If no match is found, null is returned. 
	 */
	public Sweden getFacultyById(String id){
		Sweden s;
		for (int i = 0 ; i < swedenDirectory.size() ; i++){
			s = swedenDirectory.get(i);
			if (s.getId().equals(id))
				return s;
		}
		return null;
	}
}
