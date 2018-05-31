/**
 * 
 */
package com.circa.mrv.grs_manager.niox;

import java.text.ParseException;
import java.util.Calendar;

/**
 * The Component class extends the Product class and implements Consumable interface. 
 * It has a date the component was packaged, from which the expiration can be calculated.
 * Component also has a serial number and product generation.
 *  
 * @author Arthur Vargas
 */
public class Component extends Product implements Consumable {
	/** Miscellaneous ID number */
	private long miscIDNumber;
	/** Date the component was packaged */
	private Calendar packageDate;
	/** Serial Number */
	private long serial;
	/** Product generation */
	private String generation;
	/** Notes about this specific component */
	private String note;
	
	/**
	 * Constructs a component with a miscID, part-number, product family, product generation, product description,
	 * price, and notes.
	 * @param miscID the miscellaneous ID number
	 * @param family the product family
	 * @param desc the description of the component
	 * @param pn the part number
	 * @param p the price
	 * @param packageDate the package date
	 * @param serial the serial number
	 * @param generation the product generation
	 */
	public Component(String miscID, String pn, String family, String gen, String desc, String price, String note) {
		this(family, desc, pn, price);
		
		setGeneration(gen);
		setMiscIDNumber(miscID);
		setNote(note);
	}
	
	/**
	 * Constructs a component with a family, description, part number, price, package date, serial number, and product
	 * generation.
	 * @param miscID the miscellaneous ID number
	 * @param family the product family
	 * @param desc the description of the component
	 * @param pn the part number
	 * @param p the price
	 * @param packageDate the package date
	 * @param serial the serial number
	 * @param generation the product generation
	 */
	public Component(String family, String desc, String pn, double price, String gen, long serial, Calendar packageDate) {
		this(family, gen, desc, price);
		setPartNumber(pn);
		setMiscIDNumber(0);
		setSerial(serial);
		setPackageDate(packageDate);
	}
	

	
	/**
	 * Constructs a component with a description, part number, price, package date, serial number, and product
	 * generation.
	 * @param family the family of the product
	 * @param desc the description of the component
	 * @param pn the part number
	 * @param p the price
	 */
	public Component(String family, String desc, String pn, String p) {
		super(family, desc, pn);
	
		setPrice(p);
		setPackageDate(null);
		setSerial(0);
		setGeneration(null);
	}
	
	/**
	 * Constructs a component with a name, generation, description
	 * @param family the product family
	 * @param desc the description of the component
	 * @param gen the product generation
	 */
	public Component(String fam, String gen, String desc) {
		this(fam, desc);
		setGeneration(gen);
		setPackageDate(null);
		setSerial(0);
	}
	
	/**
	 * Constructs a component with a name, generation, description
	 * @param family the product family
	 * @param desc the description of the component
	 * @param gen the product generation
	 * @param price the price of this component
	 */
	public Component(String fam, String gen, String desc, double price) {
		this(fam, desc);
		setGeneration(gen);
		setPackageDate(null);
		setSerial(0);
		setPrice(price);
	}
	
	/**
	 * Constructs a component with a name, description, and part number
	 */
	public Component(String fam, String desc) {
		super(fam,desc);
		setMiscIDNumber(0);
		
	}
	/**
	 * Default constructor for Component 
	 */
	public Component(){super("","","");}
	

	/**
	 * Returns the package date for this component
	 * @return the packageDate
	 */
	public Calendar getPackageDate() {
		return packageDate;
	}

	/**
	 * Sets the package date to the parameter packageDate
	 * @param packageDate the packageDate to set
	 */
	public void setPackageDate(Calendar packageDate) {
		this.packageDate = packageDate;
	}

	/**
	 * Returns the serial number for this component.
	 * @return the serial
	 */
	public long getSerial() {
		return serial;
	}

	/**
	 * Sets the serial number to the long named serial.
	 * @param serial the serial to set
	 */
	public void setSerial(long serial) {
		this.serial = serial;
	}

	/**
	 * Returns the product generation as a String.
	 * @return the generation
	 */
	public String getGeneration() {
		return generation;
	}

	/**
	 * Sets the product generation to the String in the parameter
	 * @param generation the generation to set
	 */
	public void setGeneration(String generation) {
		this.generation = generation;
	}
	
	/**
	 * Calculates the expiration date based on the package date.
	 * @return date the expiration date based on the package date.
	 */
	public Calendar calculateExpiration() {
	    Calendar exp = packageDate;
	    exp.add(Calendar.YEAR, 2);
	    return exp;
	}
	
	/**
	 * Checks the expiration date by calculating the time elapsed from package date until today.
	 * 
	 * @param packageDate the date the item was packaged
	 * @return date the expiration date based on package date
	 * @throws NioxExpirationException if the item is past expiration
	 */
	@Override
	public Boolean checkExpiration( Calendar packageDate ) {
		return calculateExpiration().compareTo(Calendar.getInstance()) < 0; 
			
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	
	public long getMiscIDNumber() {
		return miscIDNumber;
	}
	
	/**
	 * Sets the miscellaneous ID number data field to number parameter. If number parameter is <= 0 an 
	 * IllegalArgumentException is thrown.
	 * @param number the miscellaneous ID number
	 * @throws IllegalArgumentException if the number is < 0
	 */
	public void setMiscIDNumber(long number) {
		//if( number < 0 )
			//throw new IllegalArgumentException(MISC_ID_ERROR);
		miscIDNumber = number;
	}
	
	/**
	 * Sets the miscellaneous ID number data field to number parameter. If number parameter is < 0 or 
	 * there is a problem parsing the long value, an IllegalArgumentException is thrown.
	 * @param number the miscellaneous ID number
	 * @throws IllegalArgumentException if the number is < 0
	 */
	public void setMiscIDNumber(String number) {
		if(number.equals("") || number == null || number.equals("null")) {
			miscIDNumber = 0;
		} else {
			if(0 < Long.parseLong(number)) miscIDNumber = Long.parseLong(number);
			else miscIDNumber = 0;
		}
		/*
		try {
			Long l = Long.parseLong(number);
			if( l < 0 ) {
				miscIDNumber = 0;
				System.out.println("miscID less than 0" + " " + miscIDNumber);
				throw new IllegalArgumentException(Product.LESS_THAN_ZERO);
			}
			miscIDNumber = l;
		} catch (Exception pe) {
			miscIDNumber = 0;
			System.out.println("problem parsing miscID" + " " + miscIDNumber);
			throw new IllegalArgumentException(pe.getMessage());
		}*/
	}

	/**
	 * Generates the hashcode for an instance of component.
	 * @return the hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((generation == null) ? 0 : generation.hashCode());
		result = prime * result + ((packageDate == null) ? 0 : packageDate.hashCode());
		result = prime * result + (int) (serial ^ (serial >>> 32));
		return result;
	}


	/**
	 * Tests this Component for equality with the parameter obj
	 * @param obj the Object to compare with
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Component))
			return false;
		Component other = (Component) obj;
		if (generation == null) {
			if (other.generation != null)
				return false;
		} else if (!generation.equals(other.generation))
			return false;
		if (packageDate == null) {
			if (other.packageDate != null)
				return false;
		} else if (!packageDate.equals(other.packageDate))
			return false;
		if (serial != other.serial)
			return false;
		return true;
	}


	
}
