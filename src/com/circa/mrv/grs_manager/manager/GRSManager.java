package com.circa.mrv.grs_manager.manager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.circa.mrv.grs_manager.catalog.NioxCatalog;
import com.circa.mrv.grs_manager.directory.ResearchCompany;
import com.circa.mrv.grs_manager.directory.VendorCompany;
import com.circa.mrv.grs_manager.directory.Company;
import com.circa.mrv.grs_manager.directory.UserDirectory;
import com.circa.mrv.grs_manager.directory.CompanyDirectory;
import com.circa.mrv.grs_manager.catalog.OrderRecord;
import com.circa.mrv.grs_manager.product.list.ProductList;
import com.circa.mrv.grs_manager.user.User;
import com.circa.mrv.grs_manager.user.Employee;
import com.circa.mrv.grs_manager.user.schedule.OrderSchedule;
import com.circa.mrv.grs_manager.document.Order;

/**
 * The grs manager class handles users of the grs manager. It allows users to
 * log in and out by verifying their credentials, it contains the administrator
 * user information and handles administrator creation, and it allows the niox
 * catalog and employee directory to be obtained.
 * 
 * @author Arthur Vargas
 */
public class GRSManager {
	/** GRS Manager */
	private static GRSManager instance;
	/** Catalog of NIOX Products */
	private NioxCatalog catalog;
	/** Directory of employees for a vendor */
	private CompanyDirectory vendorDirectory;
	/** A company */
	private Company company;
	/** A company directory */
	private CompanyDirectory companyDirectory;
	/** A directory of employees */
	private UserDirectory userDirectory;
	/** A record of customer orders */
	private OrderRecord orderRecord;
	/** The administrator user */
	private User administrator;
	/** The user currently logged into GRS Manager */
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Administrator Password */
	private static final String ADMIN_PW = "admin";

	private static String hashPW;

	// Static code block for hashing the administrator's user's password
	{
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(ADMIN_PW.getBytes());
			hashPW = new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	private GRSManager() {
		catalog = new NioxCatalog();
		companyDirectory = new CompanyDirectory();
		orderRecord = new OrderRecord();
		administrator = new Administrator();
		userDirectory = new UserDirectory();
	}

	/**
	 * Obtains or creates an instance of the grs manager. A new instance is created
	 * when first called and subsequent calls return the instance. Ensures that only
	 * one instance is able to be created.
	 * 
	 * @return the instance of the registration manager.
	 */
	public static GRSManager getInstance() {
		if (instance == null) {
			// System.out.println("GRS is null");
			instance = new GRSManager();
		}
		// System.out.println("GRS is not null");
		return instance;
	}

	/**
	 * Gets the course catalog that was created
	 * 
	 * @return the course catalog
	 */
	public NioxCatalog getNioxCatalog() {
		return catalog;
	}

	/**
	 * Gets the order record that was created
	 * 
	 * @return the order record
	 */
	public OrderRecord getOrderRecord() {
		// System.out.println(orderRecord.getOrderRecordList().size());
		return orderRecord;
	}

	/**
	 * Logs a user into the grs manager after checking the passed id and password.
	 * If the employee is located in Sweden, then the employee's password is checked
	 * against the passed password. If there is a match, then the Sweden employee is
	 * logged in. If the passed Id and password match the administrator's Id and
	 * password then the user is logged in as the administrator. If the id and/or
	 * password matches a Morrisville employee, then the Morrisville employee's
	 * password is checked against the passed password. If the employee is located
	 * at a research company location, then the employee's password is checked
	 * against the passed password. If it matches the employee is logged in as a
	 * research company employee. If the login and password does not match for
	 * Sweden, Morrisville, or the customer, then the user is not logged in. If
	 * there is already a user logged into the system, the user attempting log in is
	 * not allowed. An error is thrown if the user does not exist in the directory.
	 * 
	 * @param id       the user's id
	 * @param password the user's password
	 * @return true if the user is able to be logged in. False otherwise.
	 */
	public boolean login(String id, String password) {

		if (currentUser != null)
			return false;
		User u = null;

		try {

			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			String localHashPW = new String(digest.digest());

			for (int i = 0; i < companyDirectory.getCompanyList().size(); i++) {

				for (int j = 0; j < companyDirectory.getCompanyList().get(i).getLocations().size(); j++) {

					if ((u = companyDirectory.getCompanyAt(i).getLocations().get(j).findEmployee(id,
							localHashPW)) != null) {
						if (companyDirectory.getCompanyAt(i) instanceof VendorCompany) {
							company = companyDirectory.getCompanyAt(i);
						} else if (companyDirectory.getCompanyAt(i) instanceof ResearchCompany) {
							company = companyDirectory.getCompanyAt(i);
						} else {
							throw new IllegalArgumentException("User must be a vendor or a research company.");
						}

						if (u.getPassword().equals(localHashPW)) {
							currentUser = u;
							return true;
						}

					}
				}

			}

			if (u == null && id.equals("administrator") && administrator.getPassword().equals(localHashPW)) {
				currentUser = administrator;
				return true;
			} else {
				throw new IllegalArgumentException("Could not log this user in.");
			}

		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("User does not exist.");
		}

	}

	/**
	 * Logs the current user out of the system by setting the current user to null.
	 */
	public void logout() {
		currentUser = null;
	}

	/**
	 * Gets the current user in the system.
	 * 
	 * @return the current user in the system
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Gets the current company in the system
	 * 
	 * @return the company in the system
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * Clears the user directory and niox catalog by creating new empty versions.
	 */
	public void clearData() {
		catalog.newNioxCatalog();
		userDirectory.newUserDirectory();

	}

	/**
	 * The Administrator class is a child class of user and contains information
	 * about the administrator user. It creates the administrator as the user.
	 * 
	 * @author Arthur Vargas
	 */
	private static class Administrator extends User {

		private static final String FIRST_NAME = "Arthur";

		private static final String LAST_NAME = "Vargas";

		private static final String ID = "administrator";

		private static final String EMAIL = "ahvargas92@gmail.com";

		/**
		 * Create an administrator user with the user id of administrator and password
		 * of admin. Note that hard coding passwords in a project is BAD AND THIS MUST
		 * BE CHANGED AFTER TESTING IS COMPLETE.
		 */
		public Administrator() {
			super(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPW);
		}

	}

	/**
	 * Returns true if the order is added to the employee's order schedule. Only an
	 * employee of a research company or the GRS manager administrator can drop an
	 * order from the schedule.
	 * 
	 * @param o order the order to be added to the employee's (current user's)
	 *          schedule
	 * @return true if enrolled
	 * @throws IllegalArgumentException if current user is null or company is not a
	 *                                  research company
	 */
	public boolean addOrderToSchedule(Order o) {
		if (currentUser == null || !(company instanceof ResearchCompany)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Employee s = (Employee) currentUser;

			return s.getSchedule().getOrderSchedule().add(o);

		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	/**
	 * Returns true if the order is dropped from the employee's schedule. Only an
	 * employee of a research company or the GRS Manager administrator can drop an
	 * order from the schedule.
	 * 
	 * @param o order to drop
	 * @return true if dropped
	 */
	public boolean dropOrderFromSchedule(Order o) {
		if (currentUser == null || !(company instanceof ResearchCompany)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Employee e = (Employee) currentUser;
			return e.getSchedule().removeOrderFromSchedule(o);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	/**
	 * Resets the order schedule by dropping every order it contains. Only an
	 * employee or the GRS Manager administrator can reset an order schedule
	 */
	public void resetSchedule() {
		if (currentUser == null || !(company instanceof ResearchCompany)) {
			throw new IllegalArgumentException("Illegal Action");
		}

		Employee e = (Employee) currentUser;
		e.getSchedule().resetSchedule();

	}

	/**
	 * Adds the passed faculty to the passed course if the user is the registrar and
	 * if the faculty is able to add the course based on their current course load
	 * and schedule.
	 * 
	 * @param course  The course to add the faculty to
	 * @param faculty The faculty to add to the course
	 * @return true if the faculty is added to the course, false otherwise.
	 * @throws IllegalArgumentException if a user other than the registrar tries to
	 *                                  add the faculty.
	 */
	public boolean addFacultyToCourse(Order course, Employee faculty) {
		if (currentUser == null || !currentUser.equals(administrator))
			throw new IllegalArgumentException("Illegal Action");
		if (currentUser != null && currentUser.equals(administrator) && faculty.canAdd(course)) {
			faculty.getSchedule().addOrderToSchedule(course);
			return true;
		}
		return false;
	}

	/**
	 * Adds the passed employee to the location at position 'i' of the company's
	 * location list if the currentUser is the administrator and position 'i' is
	 * valid.
	 * 
	 * @param c The company to add the employee to
	 * @param e The employee to add
	 * @param i the position of the location in company's list to which employee
	 *          will be added
	 * @return true if the employee is added to the company location, false
	 *         otherwise.
	 * @throws IllegalArgumentException if a user other than the administrator tries
	 *                                  to add the employee.
	 */
	public boolean addEmployeeToCompany(Company c, Employee e, int i) {
		if (currentUser == null || !currentUser.equals(administrator))
			throw new IllegalArgumentException("Illegal Action");
		if (currentUser != null && currentUser.equals(administrator) && i < c.getLocations().size()) {
			c.getLocations().get(i).getEmployees().add(e);
			return true;
		}
		return false;
	}

	/**
	 * Removes the order from the passed employee's schedule if the user is the GRS
	 * Manager administrator.
	 * 
	 * @param order    the order to remove the employee's schedule
	 * @param employee the employee whose schedule order will be removed from
	 * @return true if the order is removed from the employee schedule, false
	 *         otherwise.
	 * @throws IllegalArgumentException if a user other than GRS Manager
	 *                                  administrator tries to remove the order.
	 */
	public boolean removeOrderFromSchedule(Order order, Employee employee) {
		if (currentUser == null || !currentUser.equals(administrator))
			throw new IllegalArgumentException("Illegal Action");
		if (currentUser != null && currentUser.equals(administrator)) {
			employee.getSchedule().removeOrderFromSchedule(order);
			return true;
		}
		return false;
	}

	/**
	 * Removes the employee from the passed company's location where this employee
	 * is located. This action can only be completed by the GRS administrator. If
	 * the user is not the GRS Manager administrator, an IllegalArgumentException is
	 * thrown.
	 * 
	 * @param e the employee to remove
	 * @param c the company whose schedule order will be removed from
	 * @return true if the employee is removed from the company location, false
	 *         otherwise.
	 * @throws IllegalArgumentException if a user other than GRS Manager
	 *                                  administrator tries to remove the order.
	 */
	public boolean removeEmployeeFromCompany(Company c, Employee employee) {
		if (currentUser == null || !currentUser.equals(administrator))
			throw new IllegalArgumentException("Illegal Action");
		if (currentUser != null && currentUser.equals(administrator)) {

			for (int i = 0; i < c.getLocations().size(); i++) {

				if (c.getLocations().get(i).getEmployees() == null)
					continue;

				for (int j = 0; j < c.getLocations().get(i).getEmployees().size(); j++) {

					if (c.getLocations().get(i).getEmployees().get(j).getId().equals(employee.getId())) {
						c.getLocations().get(i).getEmployees().remove(j);
						return true;
					}
				}

			}
		}
		return false;
	}

	/**
	 * Resets the passed employee's schedule if the user is GRS Manager
	 * administrator.
	 * 
	 * @param employee the employee whose schedule is to be reset.
	 * @throws IllegalArgumentException if a user other than GRS Manager
	 *                                  administrator tries to reset the schedule.
	 */
	public void resetFacultySchedule(Employee employee) {
		if (currentUser != null && currentUser.equals(administrator)) {
			employee.getSchedule().resetSchedule();
		} else
			throw new IllegalArgumentException("Illegal Action");
	}

	/**
	 * Gets the company Directory. If company directory is null, returns a new
	 * company directory.
	 * 
	 * @return companyDirectory The directory of the company.
	 */
	public CompanyDirectory getCompanyDirectory() {
		if (companyDirectory == null)
			return new CompanyDirectory();
		return companyDirectory;
	}

	/**
	 * Retursn the user directory
	 * 
	 * @return userDirectory the directory of GRS Manager users
	 */
	public UserDirectory getUserDirectory() {
		return userDirectory;
	}

	/**
	 * Adds the Order to the order record
	 * 
	 * @param order the order to add
	 */
	public void addOrderToRecord(Order order) {

		orderRecord.getOrderRecordList().add(order);
	}
}