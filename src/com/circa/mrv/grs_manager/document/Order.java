/**
 * 
 */
package com.circa.mrv.grs_manager.document;

import java.util.Calendar;

import com.circa.mrv.grs_manager.manager.GRSManager;
import com.circa.mrv.grs_manager.niox.Product;
import com.circa.mrv.grs_manager.document.Order;

/**
 * An Order class specifies the data and methods for an order that a company will place with a vendor.
 * It has a creation date, delivery date for requested goods, the id of the user who created the order, and the 
 * product being ordered. The Order class extends the Document class.
 * 
 * @author Arthur Vargas
 */
public class Order extends Document {
	/** Requested delivery date */
	private Calendar delivery;
	/** Date of last status update */
	private Calendar statusDate;
	/** The Product being ordered */
	private Product product;
	/** 
	 * Describes the "open" status for an order. An Order is "open" if it has not been acknowledged by the vendor.
	 */
	private static final String OPEN = "OPEN";
	/** 
	 * Describes the "in process" status for an order. An order is "in process" if it has been acknowledged and
	 * has not been scheduled for shipment.
	 */
	private static final String IN_PROCESS = "IN PROCESS";
	/** 
	 * Describes the "scheduled" status for an order. An order is "scheduled" if it has been processed and a 
	 * delivery date has been assigned.
	 */
	private static final String SCHEDULED = "SCHEDULED";
	/**
	 * Describes the "shipped" status for an order. An order is "shipped" if it has been processed and a tracking
	 * number data field is not null
	 */
	private static final String SHIPPED = "SHIPPED";
	/** The FEDEX tracking number */
	private long trackingNum;
	/** Order status */
	private String status;

	/**
	 * Constructs an Order object with today's date, the delivery date, id of the employee who created the order,
	 * and the product being ordered.
	 * @param deliveryDate the requested date for delivery of this product 
	 * @param product the product for this order
	 */
	public Order(long number, String userID, Calendar deliveryDate, Product product) {
		super(number, userID);
		setDelieryDate(deliveryDate);
		setProduct(product);
		//get the creator id from the grsmgr
		//get the requested product from the grsmgr
		
	}

	/**
	 * @return the delieryDate
	 */
	public Calendar getDelieryDate() {
		return delivery;
	}


	/**
	 * @param delieryDate the delieryDate to set
	 */
	public void setDelieryDate(Calendar delieryDate) {
		this.delivery = delieryDate;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}


	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public boolean isDuplicate(Order number) {
	    if(this.getNumber() == number.getNumber())
	    	return true;
	    return false;
	}


	/**
	 * @return the statusDate
	 */
	public Calendar getStatusDate() {
		return statusDate;
	}


	/**
	 * @param statusDate the statusDate to set
	 */
	public void setStatusDate(Calendar statusDate) {
		this.statusDate = statusDate;
	}

	/**
	 * @return the trackingNum
	 */
	public long getTrackingNum() {
		return trackingNum;
	}

	/**
	 * @param trackingNum the trackingNum to set
	 */
	public void setTrackingNum(long trackingNum) {
		this.trackingNum = trackingNum;
	}
	
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	

	
}
