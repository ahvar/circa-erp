/**
 * 
 */
package com.circa.mrv.grs_manager.location;

/**
 * The Location class is an abstract class with data fields for address information, city, state, and country.
 * 
 * @author ArthurVargas
 */
public abstract class Location {
	/** A street address */
	private String address1;
	/** Additional address information such as suite or building number */
	private String address2;
	/** The city */
	private String city;
	/** The state or province */
	private String state;
	/** The location's country */
	private String country;
	
	/**
	 * Assigns the parameters to Location's appropriate instance variables.
	 * 
	 * @param address1 street address
	 * @param city the city
	 * @param state the state
	 * @param country the country
	 */
	public Location(String address1, String city, String state, String country) {
		setAddress1(address1);
		setCity(city);
		setState(state);
		setCountry(country);
	}
	
	/**
	 * Assigns the parameters to Location's appropriate instance variables.
	 * 
	 * @param address1 street address
	 * @param address2 additional information
	 * @param city the city
	 * @param state the state
	 * @param country the country
	 */
	public Location(String address1, String address2, String city, String state, String country) {
		this(address1,city,state,country);
		setAddress2(address2);
	}

	/**
	 * Returns the street address for the location
	 * @return the address1 the street address
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * Sets the instance variable named address1 to the parameter string
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * Returns additional information, if any exists.
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * Sets additional address information for the location.
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * Returns the city.
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the instance variable named 'city' to the parameter string.
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Returns the state
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state or province containing the location
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Returns the country containing the location
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country containing the location
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Generates the hashcode for Location.
	 * @return int the hashcode for Location
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address1 == null) ? 0 : address1.hashCode());
		result = prime * result + ((address2 == null) ? 0 : address2.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	/** 
	 * Compares a Location object to the parameter objecct
	 * @param obj the comparison object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (address1 == null) {
			if (other.address1 != null)
				return false;
		} else if (!address1.equals(other.address1))
			return false;
		if (address2 == null) {
			if (other.address2 != null)
				return false;
		} else if (!address2.equals(other.address2))
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
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	
}
