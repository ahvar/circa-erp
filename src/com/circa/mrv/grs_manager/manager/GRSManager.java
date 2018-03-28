package com.circa.mrv.grs_manager.manager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.circa.mrv.grs_manager.catalog.NioxCatalog;
import com.circa.mrv.grs_manager.directory.SwedenDirectory;
import com.circa.mrv.grs_manager.niox.Mino;
import com.circa.mrv.grs_manager.directory.MorrisvilleDirectory;
import com.circa.mrv.grs_manager.product.list.ProductList;
import com.circa.mrv.grs_manager.user.Sweden;
import com.circa.mrv.grs_manager.user.Morrisville;
import com.circa.mrv.grs_manager.user.User;
import com.circa.mrv.grs_manager.user.schedule.MorrisvilleSchedule;

/**
 * The registration manager class handles users of the registration system. It allows users to log in and out by verifying their credentials, it contains 
 * the registrar user information and handles registrar creation, and it allows the course catalog and student directory to be obtained. 
 * @author Ben
 */
public class GRSManager {
	
	private static GRSManager instance;
	  private NioxCatalog courseCatalog;
	private MorrisvilleDirectory studentDirectory;
	private SwedenDirectory facultyDirectory;
	  private User registrar;
	   private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	   private static final String PW = "Regi5tr@r";
	  private static String hashPW;
	
	//Static code block for hashing the registrar user's password 
	{
		try {
			  MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			  digest1.update(PW.getBytes());
			 hashPW = new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException("Cannot hash password");
		}
	}
	
	private GRSManager() {
		courseCatalog = new NioxCatalog();
		studentDirectory = new MorrisvilleDirectory();
		registrar = new Registrar();
		facultyDirectory = new SwedenDirectory();
	}
	/**
	 * Obtains or creates an instance of the registration manager. A new instance is created when first called and subsequent calls return the instance. 
	 * Ensures that only one instance is able to be created. 
	 * @return the instance of the registration manager.
	 */
	public static GRSManager getInstance() {
		  if (instance == null) {
			instance = new GRSManager();
		}
		return instance;
	}
	
	/**
	 * Gets the course catalog that was created
	 * @return the course catalog
	 */
	public NioxCatalog getCourseCatalog() {
		return courseCatalog;
	}
	
	/**
	 * gets the student directory that was created
	 * @return the student directory
	 */
	public MorrisvilleDirectory getStudentDirectory() {
		return studentDirectory;
	}
	
	/**
	 * Logs a user into the registration manager after checking the passed id and password. If the Id matches a student in the student directory,
	 * then that student's password is checked against the passed password. If there is a match, then the student is logged in. If the passed Id and password 
	 * match the registrar's Id and password then the user is logged in as the registrar. If the id and/or password does not match for any students
	 * in the directory or for the registrar, then the user is not logged in. If there is already a user logged into the system, the user attempting
	 * log in is not allowed. An error is thrown if the user does not exist in the directory.
	 * @param id the user's id
	 * @param password the user's password
	 * @return true if the user is able to be logged in. False otherwise.
	 */
	public boolean login(String id, String password) {
		if (currentUser != null)
			   return false;
		Sweden f = facultyDirectory.getFacultyById(id);
		Morrisville s = studentDirectory.getStudentById(id);
		if (f == null && s == null && !(id.equals("registrar")))
			throw new IllegalArgumentException ("User doesn't exist.");

		try {
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			String localHashPW = new String(digest.digest());
			if (s != null && s.getPassword().equals(localHashPW)) {
			    currentUser = s;
			    return true;
			} else if(f != null && f.getPassword().equals(localHashPW)){
			    currentUser = f;
			    return true;
			} else if (registrar.getId().equals(id) && registrar.getPassword().equals(localHashPW)) {
			    currentUser = registrar;
			    return true;
			}
			} catch (NoSuchAlgorithmException e) {
			   throw new IllegalArgumentException();
			}   
			  return false;
	}
	
	/**
	 * Logs the current user out of the system by setting the current user to null.
	 */
	public void logout() {
		currentUser = null; 
	}
	
	/**
	 * Gets the current user in the system.
	 * @return the current user in the system
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * Clears the student directory and course catalog by creating new empty versions.
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
		facultyDirectory.newFacultyDirectory();
	}
	
	/**
	 * The registrar class is a child class of user and contains information about the registrar user. It also creates the registrar user.
	 * @author Ben
	 */
	private static class Registrar extends User {
		
		  private static final String FIRST_NAME = "Wolf";
		  private static final String LAST_NAME = "Scheduler";
		 private static final String ID = "registrar";
		 	private static final String EMAIL = "registrar@ncsu.edu";
		
		/**
		 * Create a registrar user with the user id of registrar and
		 * password of Regi5tr@r.  Note that hard coding passwords in a 
		 * project is HORRIBLY INSECURE, but it simplifies testing here.
		 * This should NEVER be done in practice!
		 */
		public Registrar() {
			super(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPW);
		}
	}
	
	   /**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Mino c) {
	    if (currentUser == null || !(currentUser instanceof Morrisville)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Morrisville s = (Morrisville)currentUser;
	        //Schedule schedule = s.getSchedule();
	        ProductList roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            //schedule.addCourseToSchedule(c);
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Mino c) {
	    if (currentUser == null || !(currentUser instanceof Morrisville)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Morrisville s = (Morrisville)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (currentUser == null || !(currentUser instanceof Morrisville)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Morrisville s = (Morrisville)currentUser;
	        MorrisvilleSchedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Mino c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}
	
	/**
	 * Adds the passed faculty to the passed course if the user is the registrar and if the faculty
	 * is able to add the course based on their current course load and schedule. 
	 * @param course The course to add the faculty to
	 * @param faculty The faculty to add to the course
	 * @return true if the faculty is added to the course, false otherwise.
	 * @throws IllegalArgumentException if a user other than the registrar tries to add the faculty.
	 */
	public boolean addFacultyToCourse(Mino course, Sweden faculty){
		if (currentUser == null || !currentUser.equals(registrar))
			throw new IllegalArgumentException("Illegal Action");
		if (currentUser != null && currentUser.equals(registrar) && faculty.canAdd(course)){
				faculty.getSchedule().addCourseToSchedule(course);
				return true;
		}
		return false;
	}
	
	/**
	 * Removes the passed faculty from the passed course if the user is the registrar.
	 * @param course The course to remove the faculty from
	 * @param faculty The faculty to remove the course from
	 * @return true if the faculty is removed from the course, false otherwise.
	 * @throws IllegalArgumentException if a user other than the registrar tries to remove the faculty.
	 */
	public boolean removeFacultyFromCourse(Mino course, Sweden faculty){
		if (currentUser == null || !currentUser.equals(registrar))
			throw new IllegalArgumentException("Illegal Action");
		if (currentUser != null && currentUser.equals(registrar)){
			faculty.getSchedule().removeCourseFromSchedule(course);
			return true;
		}
		return false;
	}
	
	/**
	 * Resets the passed faculty's schedule if the user is the registrar. 
	 * @param faculty The faculty whose schedule is to be reset. 
	 * @throws IllegalArgumentException if a user other than the registrar tries to reset the schedule.
	 */
	public void resetFacultySchedule(Sweden faculty){
		if (currentUser != null && currentUser.equals(registrar)){
			faculty.getSchedule().resetSchedule();
		} else
			throw new IllegalArgumentException("Illegal Action");
	}
	
	/**
	 * Gets the faculty Directory
	 * @return facultyDirectory The directory of the faculty. 
	 */
	public SwedenDirectory getFacultyDirectory(){
		return facultyDirectory;
	}
}