package com.circa.mrv.grs_manager.document;

import java.util.Calendar;

import com.circa.mrv.grs_manager.niox.Product;
import com.circa.mrv.grs_manager.util.LinkedStack;
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
	/** The list of products for this order */
	private LinkedStack<Product> product;
	/** Default length for product stack */
	private final int DEFAULT_SIZE = 50;
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
	
	/**
	 * Describes the "Processed" status for an order. An order is "processed" if it has been entered into the vendor's
	 * ERP system.
	 */
	private static final String PROCESSED = "PROCESSED";
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
	 * @param number the unique id number for this order
	 * @param userID the user id of the user who created this order
	 * @param deliveryDate the requested date for delivery of this product
	 * @param creationDate the date this order was created 
	 * @param product the products for this order
	 */
	public Order(long number, String userID, Calendar creationDate, Calendar deliveryDate, LinkedStack<Product> products) {
		this(number);
		setUserId(userID);
		if(creationDate == null)
			setCreation(Calendar.getInstance());
		else
			setCreation(creationDate);
		setDelieryDate(deliveryDate);
		setProduct(product);
	}
	
	/**
	 * Constructs an Order with order number, user id, study, site, purchase order, creation date, delivery date,
	 * products.
	 * @param number the unique id assigned to this order
	 * @param study the study id
	 * @param site the site id
	 * @param purchase order the purchase order number
	 * @param createdOn the creation date
	 * @param deliveryDate the delivery date for the order
	 * @param products the list of products being ordered
	 */
	public Order(long number, String userID, String study, String site, String po, Calendar createdOn, Calendar deliveryDate, LinkedStack<Product> products ) {
		this(number,userID,createdOn,deliveryDate,products);
		setPo(po);
		setStudy(study);
		setSite(site);
	}
	
	/**
	 * Constructs an Order with this order number.
	 * @param ordNum the order number
	 */
	public Order(long ordNum){
		super(ordNum);
		product = new LinkedStack<Product>(DEFAULT_SIZE);
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
	 * Returns the list of products in this order
	 * @return the product
	 */
	public LinkedStack<Product> getProduct() {
		return product;
	}


	/**
	 * Sets the instance variable products to the list of products passed to the parameter
	 * @param product the product to set
	 */
	public void setProduct(LinkedStack<Product> product) {
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
	 * 
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
	 * Returns the "open" status
	 * @return the open
	 */
	public static String getOpen() {
		return OPEN;
	}


	/**
	 * Returns the "in process" status
	 * @return the inProcess
	 */
	public static String getInProcess() {
		return IN_PROCESS;
	}


	/**
	 * Returns the "scheduled" status.
	 * @return the scheduled
	 */
	public static String getScheduled() {
		return SCHEDULED;
	}


	/**
	 * Returns the "shipped" status.
	 * @return the shipped
	 */
	public static String getShipped() {
		return SHIPPED;
	}


	/**
	 * Returns the "processed" status.
	 * @return the processed
	 */
	public static String getProcessed() {
		return PROCESSED;
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
	 * The hashcode for Order
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((delivery == null) ? 0 : delivery.hashCode());
		result = prime * result + ((po == null) ? 0 : po.hashCode());
		result = prime * result + ((site == null) ? 0 : site.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((statusDate == null) ? 0 : statusDate.hashCode());
		result = prime * result + ((study == null) ? 0 : study.hashCode());
		result = prime * result + (int) (trackingNum ^ (trackingNum >>> 32));
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
		return result;
	}

	/**
	 * The equals method for Order
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
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (delivery == null) {
			if (other.delivery != null)
				return false;
		} else if (!delivery.equals(other.delivery))
			return false;
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
		if (study == null) {
			if (other.study != null)
				return false;
		} else if (!study.equals(other.study))
			return false;
		if (trackingNum != other.trackingNum)
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}


	
}
