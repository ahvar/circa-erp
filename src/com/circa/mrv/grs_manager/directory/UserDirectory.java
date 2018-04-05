/**
 * 
 */
package com.circa.mrv.grs_manager.directory;

import java.io.FileNotFoundException;

import com.circa.mrv.grs_manager.io.UserRecordIO;
import com.circa.mrv.grs_manager.util.LinkedAbstractList;
import com.circa.mrv.grs_manager.user.User;

/**
 * The userDirectory is a list of all users.
 * @author Arthur Vargas
 */
public class UserDirectory {
    /** A list of users */
	LinkedAbstractList<User> users;
	
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * Constructs an empty userDirectory
	
	public UserDirectory() {
		
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

}
