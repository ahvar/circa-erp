/**
 * 
 */
package com.circa.mrv.grs_manager.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.circa.mrv.grs_manager.io.UserRecordIO;
import com.circa.mrv.grs_manager.util.LinkedAbstractList;
import com.circa.mrv.grs_manager.user.Employee;
import com.circa.mrv.grs_manager.user.User;

/**
 * The userDirectory is a list of all users.
 * 
 * @author Arthur Vargas
 */
public class UserDirectory {
    /** A list of users */
	LinkedAbstractList<User> users;
	
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * Constructs an empty userDirectory
	 */ 
	
	public UserDirectory() {
		users = new LinkedAbstractList<User>(100);
	}
	
	/**
	 * Creates an empty user directory.  All users in the previous
	 * list are lost unless saved by the user.
	 */
	public void newUserDirectory() {
		users = new LinkedAbstractList<User>(100);
	}
	
	/**
	 * Constructs the user directory by reading in user information
	 * from the given file.  Throws an IllegalArgumentException if the 
	 * file cannot be found.
	 * @param fileName file containing list of users
	 */
	public void loadUsersFromFile(String fileName) {
		try {
			users = UserRecordIO.readUserRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	
	/**
	 * Gets the user, specified by Id, from the directory if the user id matches one in the directory. 
	 * @param id the id of the user to get. 
	 * @return the user matching the id. If no match is found, null is returned. 
	 */
	public User getUserById(String id){
		User e;
		for (int i = 0 ; i < users.size() ; i++){
			e = users.get(i);
			if (e.getId().equals(id))
				return e;
		}
		return null;
	}
	
	/**
	 * Adds a user to the directory.  Returns true if the user is added and false if
	 * the user is unable to be added because their id matches another user id.
	 * 
	 * This method also hashes the employee password for internal storage.
	 * 
	 * @param firstName employee first name
	 * @param lastName employee last name
	 * @param id employee id
	 * @param email employee email
	 * @param password employee password
	 * @param repeatPassword employee repeated password
	 * @return true if added
	 */
	public boolean addUser(String firstName, String lastName, String id, String email, String hashPW) {

		return this.users.add(new Employee(firstName,lastName,id,email,hashPW));
	}
	
	/**
	 * Removes the users with the given id from the list of user with the given id.
	 * Returns true if the user is removed and false if the user is not in the list.
	 * @param userId user id
	 * @return true if removed
	 */
	public boolean removeUser(String userId) {
		for (int i = 0; i < users.size(); i++) {
			User s = users.get(i);
			if (s.getId().equals(userId)) {
				users.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns all users in the directory with a column for first name, last name, and id.
	 * @return String array containing user first name, last name, and id.
	 */
	public String[][] getEmployeeDirectory() {
		String [][] directory = new String[users.size()][3];
		for (int i = 0; i < users.size(); i++) {
			User s = users.get(i);
			directory[i][0] = s.getFirstName();
			directory[i][1] = s.getLastName();
			directory[i][2] = s.getId();
		}
		return directory;
	}
	
	/**
	 * Saves all users in the directory to a file.
	 * @param fileName name of file to save user to.
	 */
	public void saveUserDirectory(String fileName) {
		try {
			UserRecordIO.writeUserRecords(fileName, users);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}
	
	/**
	 * Returns the LinkedAbstractList of users contained in this user directory.
	 * @return users the directory of users
	 */
	public LinkedAbstractList<User> getUsers() {
		return users;
	}

}
