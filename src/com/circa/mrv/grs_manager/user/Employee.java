package com.circa.mrv.grs_manager.user;
import com.circa.mrv.grs_manager.user.schedule.OrderSchedule;
import com.circa.mrv.grs_manager.document.Order;

/**
 * Creates an employee object for a company directory when provided with their pertinent information.
 * 
 * @author Arthur Vargas
 */
public class Employee extends User implements Comparable<Employee> {
    /** Employee order schedule */
	private OrderSchedule schedule;
	
	private int maxOrders;
	
	public static final int MAX_ORDERS = 3;


	public static final int MIN_ORDERS = 1;


		
	/**
	 * Constructs an instance of Employee with first name, last name, id, email, and password. 
	 * Initializes the employee's order schedule capacity to 10.
	 * 
	 * @param firstName employee first name
	 * @param lastName employee last name
	 * @param id employee id
	 * @param email employee email
	 * @param hashPW employee password
	 */
	public Employee(String firstName, String lastName, String id, String email, String hashPW){
			
		super(firstName, lastName, id, email, hashPW); 
		schedule = new OrderSchedule(getId());
	}
		
		/**
		 * Constructs an instance of Employee with first name, last name, id, email, and password. 
	     * Initializes the employee's order schedule capacity to 10.
	     * 
		 * @param firstName employee first name
		 * @param lastName employee last name
		 * @param id employee id
		 * @param email employee email
		 * @param hashPW employee password
		 * @param schedule the orders schedule for that employee
		 */
		public Employee(String firstName, String lastName, String id, String email, String hashPW, OrderSchedule schedule) {
			this(firstName, lastName, id, email, hashPW);
			this.schedule = schedule;
		}

		/**
		 * gets the employee maxOrders
		 * @return the maxOrders
		 */
		public int getMaxOrders() {
			return maxOrders;
		}

		/**
		 * sets the employee max courses
		 * @param maxOrders the maxCourses to set
		 *  @throws IllegalArgumentException if maxOrders is less than MIN_ORDERs or greater than MAX_ORDERS
		 */
		public void setMaxOrders(int maxOrders) {
			if (maxOrders < MIN_ORDERS || maxOrders > MAX_ORDERS){
				String errorDescription;
				errorDescription = "Invalid max orders";
				throw new IllegalArgumentException(errorDescription);
			}
				this.maxOrders = maxOrders;
		}

		/**
		 * Returns a comma separated value string of all employee fields
		 * @return String representation of employee
		 */
		@Override
		public String toString() {
			return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + "Total Orders: " + 
		    schedule.getNumScheduledOrders();
			
		}
		
		/**
		 * Compares this employee to the passed employee by comparing a combined string of lastname firstname and id. the 
		 * compareTo method in comparable is called to compare the combined string.
		 */
		@Override
		public int compareTo(Employee f) {
			int compareResult;
			String thisEmployee = new String(this.getLastName() + this.getFirstName() + this.getId());
			String employeeE = new String(f.getLastName() + f.getFirstName() + f.getId());	
			
			compareResult = thisEmployee.compareTo(employeeE);
			
			return compareResult;
		}

		/**
		 * Generate hashcode for Employee
		 * @return the hashcode
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result + ((schedule == null) ? 0 : schedule.hashCode());
			return result;
		}

		/**
		 * Compares this employee for equality with the object parameter
		 * @param the object to test with this employee for equality
		 * @return true if this employee is equal to the parameter object
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!super.equals(obj))
				return false;
			if (!(obj instanceof Employee))
				return false;
			Employee other = (Employee) obj;
			if (schedule == null) {
				if (other.schedule != null)
					return false;
			} else if (!schedule.equals(other.schedule))
				return false;
			return true;
		}

		/**
		 * Gets the employee's schedule which contains the orders they have entered or have not processed.
		 * @return schedule the order schedule for this employee.
		 */
		public OrderSchedule getSchedule() {
			return schedule;
		}
		
		/**
		 * 
		 * THIS CHECK SHOULD BE DONE SOMEWHERE ELSE
		 * Checks whether the employee can add an order to their schedule. Uses LinkedAbstractList's canAdd method to perform some of the checks.
		 * The check not performed by schedule.canAdd that is performed is whether the total courses the employee is already
		 *  
		 * @param c the order that is being considered for adding to the schedule.
		 * @return true if the employee can add the course. False otherwise. 
		 */
		public boolean canAdd(Order c){
			//if (!schedule.canAdd(c) || fSchedule.getScheduledCourses().length + 1 > maxCourses)
				//return false;

			return true;
		}
		
		/**
		 * returns true if the number of orders is greater than the employee's maximum
		 * @return true if the employee exceeded the maximum number of orders
		 */
		public boolean isOverloaded(){
			if (schedule.getNumScheduledOrders() > maxOrders)
				return true;
			return false;
		}
}
