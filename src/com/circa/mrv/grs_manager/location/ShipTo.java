package com.circa.mrv.grs_manager.location;

import com.circa.mrv.grs_manager.directory.UserDirectory;

/**
 * The ShipTo class extends abstract class Location and contains the name of a shipping contact.
 * The ShipTo is a location where goods are shipped.
 * 
 * @author ArthurVargas 
 */
public class ShipTo extends Location {
	/** The name of a contact in shipping department */
	private String shippingContact;
	/** Ship-to Description */
	private static final String SHIP_TO = "Ship-To";
	
	/**
	 * Constructs the ShipTo location.
	 * 
	 * @param add1 street address
	 * @param city the city
	 * @param state the state
	 * @param zip the zip code
	 * @param country the country
	 */
	public ShipTo(String add1, String city, String state, String zip, String country) {
        super(add1,city,state,zip,country);
	}

	/**
	 * Constructs the ShipTo location
	 * @param add1 street address
	 * @param add2 additional information for the street address
	 * @param city the city
	 * @param state the state
	 * @param zip the zip code
	 * @param country the country
	 */
	public ShipTo(String add1, String add2, String city, String state, String zip, String country, String name) {
		this(add1,city,state,zip,country);
		setAddress2(add2);
		setShippingContact(name);
	}

	public String getShippingContact() {
		return shippingContact;
	}

	public void setShippingContact(String shippingContact) {
		this.shippingContact = shippingContact;
	}
	
	/**
	 * Returns the description for a ship-to
	 * @return String the description for ship-to
	 */
	public static String getShipTo() {
		return SHIP_TO;
	}

	/**
	 * Generates hashcode for ShipTo
	 * @param prime the hashcode for ShipTo
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((shippingContact == null) ? 0 : shippingContact.hashCode());
		return result;
	}

	/**
	 * Tests for equality between this ShipTo and the parameter objet
	 * @param obj the object to test for equality with this ShipTo
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShipTo other = (ShipTo) obj;
		if (shippingContact == null) {
			if (other.shippingContact != null)
				return false;
		} else if (!shippingContact.equals(other.shippingContact))
			return false;
		return true;
	}
	
}
