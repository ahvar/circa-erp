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
	/** Customer purchase order number */
	private String po;
	/** Study number associated with this order */
	private String study;
	/** Site number associated with this order */
	private String site;
	/** City of the research site */
	private String city;
	/** State of the research site */
	private String state;
	/** Zip code for the research site */
	private String zip;
	/** Country of the research site */
	private String country;
	/** Total amount (in USD) on this order */
	private double amount;

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
	
	
	public Order(){
		super();
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
	
	/**
	 * 
	 * @param number
	 * @return
	 */
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
	 * @return the po
	 */
	public String getPo() {
		return po;
	}


	/**
	 * @param po the po to set
	 */
	public void setPo(String po) {
		this.po = po;
	}


	/**
	 * @return the study
	 */
	public String getStudy() {
		return study;
	}


	/**
	 * @param study the study to set
	 */
	public void setStudy(String study) {
		this.study = study;
	}


	/**
	 * @return the site
	 */
	public String getSite() {
		return site;
	}


	/**
	 * @param site the site to set
	 */
	public void setSite(String site) {
		this.site = site;
	}


	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}


	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}


	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}


	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}


	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}


	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}


	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}


	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}


	/**
	 * Return the total amount for this order
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}


	/**
	 * Sets the amount for this order
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}


	/**
	 * Returns a string array of the order number and creator id
	 * @return numbers an array of the order numbers 
	 */
	public String[] getShortDisplay() {
		String[] orderString = { String.valueOf(getNumber()), getUserId() };
		return orderString;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((po == null) ? 0 : po.hashCode());
		result = prime * result + ((site == null) ? 0 : site.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((study == null) ? 0 : study.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
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
		if (po == null) {
			if (other.po != null)
				return false;
		} else if (!po.equals(other.po))
			return false;
		if (site == null) {
			if (other.site != null)
				return false;
		} else if (!site.equals(other.site))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (study == null) {
			if (other.study != null)
				return false;
		} else if (!study.equals(other.study))
			return false;
		return true;
	}


	
}
