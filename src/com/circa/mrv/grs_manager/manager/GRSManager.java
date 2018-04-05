package com.circa.mrv.grs_manager.manager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.circa.mrv.grs_manager.catalog.NioxCatalog;
import com.circa.mrv.grs_manager.directory.CustomerDirectory;
import com.circa.mrv.grs_manager.directory.ResearchCompany;
import com.circa.mrv.grs_manager.directory.Company;
import com.circa.mrv.grs_manager.directory.UserDirectory;
import com.circa.mrv.grs_manager.directory.VendorDirectory;
import com.circa.mrv.grs_manager.niox.Mino;
import com.circa.mrv.grs_manager.product.list.ProductList;
import com.circa.mrv.grs_manager.user.User;
import com.circa.mrv.grs_manager.user.Employee;
import com.circa.mrv.grs_manager.user.schedule.VendorSchedule;

 /**
 * The grs manager class handles users of the grs manager. It allows users to log in and out by verifying their credentials, it contains 
 * the administrator user information and handles administrator creation, and it allows the niox catalog and employee directory to be obtained. 
 * 
 * @author Arthur Vargas
 */
public class GRSManager {
	/** GRS Manager */
	private static GRSManager instance;
	/** Catalog of NIOX Products */
	private NioxCatalog catalog;
	/** Directory of employees for a vendor */
	private VendorDirectory vendorDirectory;
	/** Directory of employees for a customer */
	private CustomerDirectory customerDirectory;
	/** A company */
	private Company company;
	/** A directory of employees */
	private UserDirectory employeeDirectory;
	/** The administrator user */
	private User administrator;
	/** The user currently logged into GRS Manager */
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Administrator Password */
	private static final String PW = "03CI27RCA17";

	private static String hashPW;
	
	//Static code block for hashing the administrator's user's password 
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
		catalog = new NioxCatalog();
		vendorDirectory = new VendorDirectory();
		administrator = new Administrator();
		employeeDirectory = new UserDirectory();
		customerDirectory = new CustomerDirectory();
	}
	
	/**
	 * Obtains or creates an instance of the grs manager. A new instance is created when first called and subsequent calls return the instance. 
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
	public NioxCatalog getNioxCatalog() {
		return catalog;
	}
	
	/**
	 * gets the student directory that was created
	 * @return the student directory
	 */
	public VendorDirectory getVendorDirectory() {
		return vendorDirectory;
	}
	
	/**
	 * Logs a user into the grs manager after checking the passed id and password. If the employee is located in Sweden,
	 * then the employee's password is checked against the passed password. If there is a match, then the Sweden employee is logged in. 
	 * If the passed Id and password match the administrator's Id and password then the user is logged in as the administrator. 
	 * If the id and/or password matches a Morrisville employee, then the Morrisville employee's password is checked against the passed password. 
	 * If the employee is located at a research company location, then the employee's password is checked against the passed password. If it matches
	 * the employee is logged in as a research company employee. If the login and password does not match for Sweden, Morrisville, or the 
	 * customer, then the user is not logged in. If there is already a user logged into the system, the user attempting log in is not 
	 * allowed. An error is thrown if the user does not exist in the directory.
	 * 
	 * @param id the user's id
	 * @param password the user's password
	 * @return true if the user is able to be logged in. False otherwise.
	 */
	public boolean login(String id, String password) {
		if (currentUser != null)
			   return false;
		User u;
		for(int i = 0; i < company.getLocations().size(); ++i )
			if( ( u = company.getLocations().get(i).findEmployee(id, password) ) != null ) break;
		if( this.company instanceof ResearchCompany )	
			// open eRT panel
		else if( this.company instanceof )
		Employee s = customerDirectory.getEmployeeById(id);
		Vendor m = vendorDirectory.getEmployeeById(id);
		if (s == null && m == null && !(id.equals("administrator")))
			throw new IllegalArgumentException ("User doesn't exist.");

		try {
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			String localHashPW = new String(digest.digest());
			if ( this.company instanceof ResearchCompany && u != null && u.getPassword().equals(localHashPW) ||
					this.company instanceof ProductList && u != null && u.getPassword().equals(localHashPW) ) {
				currentUser = company.getEmployeeDirectory().getUserById(id);
				return true;
		    } else if (m != null && m.getPassword().equals(localHashPW)) {
			    currentUser = m;
			    return true;
			} else if(s != null && s.getPassword().equals(localHashPW)){
			    currentUser = s;
			    return true;
			} else if (administrator.getId().equals(id) && administrator.getPassword().equals(localHashPW)) {
			    currentUser = administrator;
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
		catalog.newNioxCatalog();
		vendorDirectory.newStudentDirectory();
		customerDirectory.newFacultyDirectory();
	}
	
	/**
	 * The Administrator class is a child class of user and contains information about the registrar user. It also creates the registrar user.
	 * @author Arthur Vargas
	 */
	private static class Administrator extends User {
		
		  private static final String FIRST_NAME = "Arthur";
		  private static final String LAST_NAME = "Vargas";
		 private static final String ID = "administrator";
		 	private static final String EMAIL = "arthur.vargas@circassia.com";
		
		/**
		 * Create an adminisrator user with the user id of administrator and
		 * password of 03ci27rca17.  Note that hard coding passwords in a 
		 * project is HORRIBLY INSECURE, but it simplifies testing here.
		 */
		public Administrator() {
			super(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPW);
		}
	}
	
	 /**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Mino c) {
	    if (currentUser == null || !(currentUser instanceof Vendor)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Vendor s = (Vendor)currentUser;
	        //Schedule schedule = s.getSchedule();
	        ProductList roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canAdd(s)) {
	            //schedule.addCourseToSchedule(c);
	            roll.add(s);
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
	    if (currentUser == null || !(currentUser instanceof Vendor)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Vendor s = (Vendor)currentUser;
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
	    if (currentUser == null || !(currentUser instanceof Vendor)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Vendor s = (Vendor)currentUser;
	        VendorSchedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Mino c = catalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
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
	public boolean addFacultyToCourse(Mino course, Employee faculty){
		if (currentUser == null || !currentUser.equals(administrator))
			throw new IllegalArgumentException("Illegal Action");
		if (currentUser != null && currentUser.equals(administrator) && faculty.canAdd(course)){
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
	public boolean removeFacultyFromCourse(Mino course, Employee faculty){
		if (currentUser == null || !currentUser.equals(administrator))
			throw new IllegalArgumentException("Illegal Action");
		if (currentUser != null && currentUser.equals(administrator)){
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
	public void resetFacultySchedule(Employee faculty){
		if (currentUser != null && currentUser.equals(administrator)){
			faculty.getSchedule().resetSchedule();
		} else
			throw new IllegalArgumentException("Illegal Action");
	}
	
	/**
	 * Gets the faculty Directory
	 * @return facultyDirectory The directory of the faculty. 
	 */
	public CustomerDirectory getFacultyDirectory(){
		return customerDirectory;
	}
}