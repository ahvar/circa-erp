/**
 * 
 */
package com.circa.mrv.grs_manager.location;

/**
 * A non-clinical, office location for a business that houses AR/AP departments.
 * @author ArthurVargas
 */
public class BillTo extends Location {
	/** The name of someone in the billing dept. */
	private String billingContact;

	/**
	 * Constructs this BillTo with address information and contact name.
	 * 
	 * @param address1 the street address
	 * @param city the city
	 * @param state the state
	 * @param zip the zip code
	 * @param country the country
	 * @param billingContact the name of the billing contact
	 */
	public BillTo(String address1, String city, String state, String zip, String country, String billingContact) {
		super(address1, city, state, zip, country);
		setBillingContact(billingContact);
	}
	
	/**
	 * Constructs this BillTo with address information and contact name.
	 * 
	 * @param address1 the street address
	 * @param city the city
	 * @param state the state
	 * @param country the country
	 * @param billingContact the name of the billing contact
	 */
	public BillTo(String address1, String address2, String city, String state, String zip, String country, String billingContact) {
		this(address1, city, state, zip, country, billingContact);
		setAddress2(address2);
	}

	/**
	 * Returns the name of a person in the billing department.
	 * @return billingContact the name of the contact
	 */
	public String getBillingContact() {
		return billingContact;
	}

	/**
	 * Sets the name of someone in the billing department.
	 * @param billingContact the name to be set
	 */
	public void setBillingContact(String billingContact) {
		this.billingContact = billingContact;
	}

	/** 
	 * Generates hash code for BillTo
	 * @return hashcode for the BillTo
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((billingContact == null) ? 0 : billingContact.hashCode());
		return result;
	}

	/**
	 * Test for equality between two ship-to's
	 * 
	 * @param obj the object tested for equality with this BillTo
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BillTo other = (BillTo) obj;
		if (billingContact == null) {
			if (other.billingContact != null)
				return false;
		} else if (!billingContact.equals(other.billingContact))
			return false;
		return true;
	}

}
