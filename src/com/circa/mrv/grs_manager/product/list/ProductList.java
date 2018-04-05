package com.circa.mrv.grs_manager.product.list;

import com.circa.mrv.grs_manager.util.LinkedAbstractList;
import com.circa.mrv.grs_manager.util.LinkedQueue;
import com.circa.mrv.grs_manager.niox.Product;


/**
 * The ProductList class provides functionality for maintaining a list of products that have been ordered for a particular
 * company. The list is a LinkedAbstractList and company's employees can add or remove products. Also, the administrator can 
 * set the list cap for total products at any time. The system can also see the number of spaces remaining
 * for the product based on the current list size and maximum capacity.
 * @author Arthur Vargas
 */
public class ProductList {
	/** A list of products */
	private LinkedAbstractList<Product> list;
	/** The capacity of the list set by the customer */
	private int listCapacity;
	/** The minimum capacity of the list */
	private static final int MIN_CAPACITY = 1;
	/** The maximum capacity of the list */
	private static final int MAX_CAPACITY = 30;
	/** The maximum holding capacity */
	private static final int MAX_HOLDING = 5;
	/** The holding list permits extra capacity for additional products */
	private LinkedQueue<Product> holding;
	/** A product in the list */
	private Product product;
	
	/**
	 * Creates a new LinkedAbstractList of products. The capacity of the list is passed by the user and set using 
	 * the setCapacity method which checks that the capacity falls within the minimum and max values. 
	 * @param cap The requested enrollment cap.
	 * @param p the product to add to the list or holding
	 */
	public ProductList(Product p, int cap){
		if (p == null)
			throw new IllegalArgumentException();
		product = p;
		setCapacity(cap);
		list = new LinkedAbstractList<Product>(this.listCapacity);	
		holding = new LinkedQueue<Product>(MAX_HOLDING);
		holding.setCapacity(MAX_HOLDING);
	}
	
	/**
	 * Sets the cap on the number of products allowed to be added to the list. If the passed
	 * value is less than 1 or greater than 30 an IllegalArgumentException is thrown. Also, if the current product 
	 * list size is greater than the passed capacity than the IAE is thrown.
	 * @param capacity the requested capacity. 
	 * @throws IllegalArgumentException if the passed capacity is less than 1, greater than 30, or less than the
	 * current size of the list.
	 */
	public void setCapacity(int capacity) {
		if (capacity < MIN_CAPACITY || capacity > MAX_CAPACITY)
			throw new IllegalArgumentException("Enrollment Cap must be between 10 – 250");
		else if (list != null && capacity < list.size())
			throw new IllegalArgumentException();
		else{
			this.listCapacity = capacity;
			if (list != null && capacity >= list.size())
				list.setCapacity(capacity);
		}
	}
	
	/**
	 * Gets the number of products that are allowed to be added in a particular list.
	 * @return listCapacity the cap on the number of products that can be added to the list.
	 */
	public int getCapacity(){
		return listCapacity;
	}
	
	/**
	 * Adds the product to the list if there is room in the list and if no other exceptions are thrown 
	 * from the LinkedAbstractList class when attempting to add the product to the end of the list.
	 * @param p the product to add to the list.
	 * @throws IllegalArgumentException if the add() method returns false or if an exception is thrown from the 
	 * LinkedAbstractList class when attempting to add the product to the end of the list.
	 */
	public void add(Product p){
		if (!canAdd(p))
			throw new IllegalArgumentException();
		if(list.size() >= listCapacity){ 
			holding.enqueue(p);
		} else{
			try{
				list.add(list.size(), p);
				//p.getSchedule().addCourseToSchedule(product);
			} catch (Exception e){
				throw new IllegalArgumentException(e);
			}	
		}
	}
	
	/**
	 * Removes a product from the list if it are present on the list and if product is not null. 
	 * @throws IllegalArgumentException if the product is null or if the LinkedAbstractList class 
	 * throws an exception during the removal process.
	 * @param p The product to remove from the list. 
	 */
	public void drop(Product p) {
		if (p == null)
			throw new IllegalArgumentException();
		try{
			for (int i = 0 ; i < list.size() ; i++){
				if (list.get(i).equals(p)){
					list.remove(i); 
					if (!holding.isEmpty()){ 
						Product onHold = holding.dequeue();
						list.add(list.size(), onHold);
						//onHold.getSchedule().addCourseToSchedule(product);
					}
				}
			}
			if (!holding.isEmpty()){ 
				Product productOnHold;
				int initialHoldingSize = holding.size();
				for (int i = 0 ; i < initialHoldingSize ; i++){
					productOnHold = holding.dequeue();
					if (!productOnHold.equals(p)){ //if not equal put student back into queue at end.
						holding.enqueue(productOnHold);
					}
					//otherwise, the student is removed and the queue is simply "requeued"
				}
			}
		} catch (Exception e){
			throw new IllegalArgumentException();
		}	
	}
	
	/**
	 * Gets the spaces in the list for additional products.
	 * @return The difference between the capacity and the current number of products in the list.
	 */
	public int getRemainingCapacity(){
		return listCapacity - list.size();
	}
	
	/**
	 * Checks whether a product can be added to the list. A product would be allowed if it is not null and 
	 * if the current enrollment is less than the enrollment cap.
	 * @param s the product to check whether it can be added to the list.
	 * @return True if product can be added. False otherwise. 
	 */
	public boolean canAdd(Product s){
		if (s == null || holding.size() >= MAX_HOLDING) 
			return false;
		
		if (holding.size() < MAX_HOLDING && list.size() >= listCapacity){
			try { 
				holding.enqueue(s);
				//for (int i = 0; i < list.size() ; i++){
					//if (list.get(i).equals(s))
						//return false;
				//}
				
				
			
				//Product stu;
				//int matchCount = 0;
				//for (int i = 0 ; i < holding.size() ; i++) {
				  //  stu = holding.dequeue();
					//if (stu.equals(s)) { 
						//matchCount++;
					//}
						//holding.enqueue(stu);
					//}
					//if (matchCount > 0)
						//return false; //the student is already on wait list
				//}	
			} catch (IllegalArgumentException e){
				return false;
			}				
		} //else { //the course is not full so check that student is not on roll
			//for (int i = 0; i < list.size() ; i++){
				//if (list.get(i).equals(s))
					//return false;
			//}
		//}
		return true;
	}
	
	/**
	 * Gets the number of products on hold for the product list.
	 * @return The number of products on the holding list.
	 */
	public int getsNumberOnHold(){
		return holding.size();
	}
}
