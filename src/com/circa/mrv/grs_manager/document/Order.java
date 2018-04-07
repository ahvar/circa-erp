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
	
	/**
	 * Returns a string array of the order number and creator id
	 * @return numbers an array of the order numbers 
	 */
	public String[] getShortDisplay() {
		String[] orderString = { String.valueOf(getNumber()), getUserId() };
		return orderString;
	}

	/**
	 * Generate the hash code for an order.
	 * @return result the hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((delivery == null) ? 0 : delivery.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((statusDate == null) ? 0 : statusDate.hashCode());
		result = prime * result + (int) (trackingNum ^ (trackingNum >>> 32));
		return result;
	}

	/**
	 * Evalutes for equality between this Order and the object parameter
	 * @param obj the object to test for equality
	 * @return true if the object parameter is equal to this Order
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Order))
			return false;
		Order other = (Order) obj;
		if (delivery == null) {
			if (other.delivery != null)
				return false;
		} else if (!delivery.equals(other.delivery))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (statusDate == null) {
			if (other.statusDate != null)
				return false;
		} else if (!statusDate.equals(other.statusDate))
			return false;
		if (trackingNum != other.trackingNum)
			return false;
		return true;
	}


	
}
