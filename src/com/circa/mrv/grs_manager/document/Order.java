package com.circa.mrv.grs_manager.document;

import java.lang.reflect.Array;
import java.util.Calendar;

import com.circa.mrv.grs_manager.niox.Product;
import com.circa.mrv.grs_manager.util.LinkedListRecursive;
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
	private Product[] product;
	/** Product count */
	private int pdcount;
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
	/** Site name */
	private String siteName;
	/** Site street address */
	private String streetAdd;
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
	public Order(long number, String userID, Calendar creationDate, Calendar deliveryDate, Product[] products) {
		this(number);
		setUserId(userID);
		if(creationDate == null)
			setCreation(Calendar.getInstance());
		else
			setCreation(creationDate);
		setDelieryDate(deliveryDate);
		setProduct(products);
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
	public Order(long number, String userID, String study, String site, String po, Calendar createdOn, Calendar deliveryDate, Product[] products ) {
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
		product = new Product[DEFAULT_SIZE];
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
	public Product[] getProduct() {
		return product;
	}


	/**
	 * Sets the instance variable products to the list of products passed to the parameter
	 * @param product the product to set
	 */
	public void setProduct(Product [] product) {
		this.product = product;
		pdcount = getProductCount();
		
	}
	
	/**
	 * Adds a product to the products for this order
	 * @param product the product to add to the Order's stack
	 * @param qty the number of products being ordered
	 */
	public void addProduct(Product product, int qty) {
		if(pdcount == this.product.length || this.product.length < (pdcount + qty)) getProductCapacity(); 
		for(int i = 0; i < qty; i++) {
			System.out.println(pdcount);
			this.product[pdcount] = product; 
			pdcount++;
		}
	}
	
	
	/**
	 * Doubles the length of the product capacity
	 */
	public void getProductCapacity() {
		Product[] bigger = new Product[this.product.length *2];
		for(int i = 0; i < this.product.length; i++) {
			bigger[i] = this.product[i];
		}
		this.product = bigger;
	}
	
	/**
	 * Returns a 2D array of data for the current 
	 * product stack
	 * @return array a 2D array of string data
	 */
	public String[][] getProductDisplay() {
		if(pdcount == 0) return null;
		String [][] array = new String[pdcount][4];
		for(int i = 0; i < pdcount; i++) {
			//System.out.println(product[i].getPartNumber() + " " + product[i].getFamily() + " " + product[i].getDescription());
			array[i][0] = product[i].getPartNumber();
			array[i][1] = product[i].getFamily();
			array[i][2] = product[i].getDescription();
			array[i][3] = Double.toString(product[i].getPrice());
		}
		return array;
	}
	
	/**
	 * Returns the number of products in this order
	 * @return count the product count on this order
	 */
	public int getProductCount() {
		return pdcount;
	}
	
	/**
	 * Counts the products in the order and sets the product count instance variable.
	 */
	public void setProductCount() {
		for(int i = 0; i < product.length; i++) {
			if(product[i] != null) pdcount++;
		}
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
		if(site == null || site.equals("") )
			this.site = "xxxx";
		this.site = site;
	}

	/**
	 * Returns the name of the healthcare facility where the research is being conducted
	 * @return the siteName
	 */
	public String getSiteName() {
		return siteName;
	}

	/**
	 * The site name is the name of the healthcare facility where the research is conducted.
	 * Sets the site name to the siteName parameter. Throws IllegalArgumentException if 
	 * siteName parameter is null or empty.
	 * 
	 * @param siteName the siteName to set
	 * @throws IllegalArgumentException if siteName is null or empty
	 */
	public void setSiteName(String siteName) {
		if(siteName == null || siteName.equals(""))
			throw new IllegalArgumentException();
		this.siteName = siteName;
	}

	/**
	 * Returns the street address of the healthcare facility where the research is conducted.
	 * @return the streetAdd
	 */
	public String getStreetAdd() {
		return streetAdd;
	}

	/**
	 * Sets the street address of the healthcare facility where the research is conducted.
	 * Throws IllegalArgumentException if streetAdd parameter is null or empty.
	 * @param streetAdd the streetAdd to set
	 */
	public void setStreetAdd(String streetAdd) {
		if(streetAdd == null || streetAdd.equals(""))
			throw new IllegalArgumentException();
		this.streetAdd = streetAdd;
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
	 * Generates the hash code for Order
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((po == null) ? 0 : po.hashCode());
		result = prime * result + ((site == null) ? 0 : site.hashCode());
		result = prime * result + ((study == null) ? 0 : study.hashCode());
		return result;
	}

	/**
	 * Tests this order for equality. Two orders are equal if they have the same PO, study id, and site id.
	 * @param obj the object to test for equality
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
		if (study == null) {
			if (other.study != null)
				return false;
		} else if (!study.equals(other.study))
			return false;
		return true;
	}


	
}
