package com.circa.mrv.grs_manager.user.schedule;

import com.circa.mrv.grs_manager.document.Order;
import com.circa.mrv.grs_manager.niox.ConflictException;
import com.circa.mrv.grs_manager.niox.Mino;
import com.circa.mrv.grs_manager.util.LinkedListRecursive;

/**
 * The ROW (rest of world) order schedule.
 * @author Arthur Vargas
 */
public class OrderSchedule {

	/** Schedule of orders with no cap */
	private LinkedListRecursive<Order> schedule;
	/** Employee id for updating orders */
	private String employeeId;
	
	/**
	 * Creates an empty schedule.
	 * @param employeeId employee's id for updating order
	 */
	public OrderSchedule(String employeeId) {
		schedule = new LinkedListRecursive<Order>();
		this.employeeId = employeeId;
	}
	
	/**
	 * Adds an order to the schedule.
	 * @param order Order to add to schedule
	 * @return true if added
	 */
	public boolean addOrderToSchedule(Order ord) {
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(ord)) {
				throw new IllegalArgumentException("Already assigned " + ord.getNumber());
			}
			try {
				//schedule.get(i).checkConflict(ord);
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The order cannot be assigned due to a conflict.");
			}
		}
		if (ord.getUserId() != null) {
			throw new IllegalArgumentException("The order already has another creator.");
		}
		if (schedule.add(ord)) {
			ord.setUserId(employeeId);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes a order from the schedule.
	 * @param order Order to remove from the schedule
	 * @return true if added
	 */
	public boolean removeOrderFromSchedule(Order order) {
		if (schedule.remove(order)) {
			order.setUserId(null);
			return true;
		}
		return false;
	}
	
	/**
	 * Resets the schedule to an empty schedule
	 */
	public void resetSchedule() {
		int startingSize = schedule.size();
		for (int i = 0; i < startingSize; i++) {
			removeOrderFromSchedule(schedule.get(i)); 
		}
	}
	
	/**
	 * Returns the list of scheduled Orders.
	 * @return list of scheduled Orders
	 */
	public String[][] getScheduledOrders() {
		String [][] scheduleArray = new String[schedule.size()][5];
		for (int i = 0; i < schedule.size(); i++) {
			scheduleArray[i] = schedule.get(i).getShortDisplay();
		}
		return scheduleArray;
	}
	
	/**
	 * Returns the number of orders the employee has in his/her schedule.
	 * @return the size of the schedule
	 */
	public int getNumScheduledOrders() {
		return schedule.size();
	}
	
	/**
	 * Returns a list of orders contained in this OrderSchedule
	 * @return schedule the list of orders
	 */
	public LinkedListRecursive<Order> getOrderSchedule() {
		return schedule;
	}

	/**
	 * Generates the hashcode for order schedule
	 * @return the hashcode for OrderSchedule
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((schedule == null) ? 0 : schedule.hashCode());
		return result;
	}

	/**
	 * Evaluates for equality between this OrderSchedule and the parameter object
	 * @param obj the object to test for equality
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof OrderSchedule))
			return false;
		OrderSchedule other = (OrderSchedule) obj;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (schedule == null) {
			if (other.schedule != null)
				return false;
		} //else if (!schedule.equals(other.schedule))  THIS NEEDS TO CHECK EACH ORDER IN THE SCHEDULE
			//return false;								
		return true;
	}
	
	
	
	
}