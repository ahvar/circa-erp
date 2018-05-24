/**
 * 
 */
package com.circa.mrv.grs_manager.location;

/**
 * A physical location of a participant in a research study.
 *  
 * @author ArthurVargas
 */
public class ResearchSite extends ShipTo {
	/** The site ID number */
	private long num;
	/** Primary contact HCP (health care provider) at this research site */
	private String name;
	/** Number of devices at this site */
	private int devices;
	
	/**
	 * Constructs a research site for a particular study.
	 * @param add1 the street address
	 * @param add2 the building, suite or unit number
	 * @param city the city
	 * @param state the state
	 * @param zip the zip code
	 * @param country the country
	 * @param num the unique ID for this site
	 * @param name the name of a health care professional associated with the study at this site
	 * @param devices the number of devices at this site
	 */
	public ResearchSite(String add1, String add2, String city, String state, String zip, String country, long num, String name, int devices) {
		this(add1,add2,city,state,zip,country,num,devices);
		this.name = name;
	}

	/**
	 * Constructs a research site for a particular study.
	 * @param add1 the street address
	 * @param add2 the suite or building number
	 * @param city the city
	 * @param state the state
	 * @param zip the zip code
	 * @param country the country
	 * @param num the unique ID for this site
	 * @param devices the number of devices at this site
	 */
	public ResearchSite(String add1, String add2, String city, String state, String zip, String country, long num, int devices) {
		super(add1,city,state,zip,country);
		setAddress2(add2);
		setNum(num);
		setDevices(devices);
	}
	
	
	/**
	 * Returns the unique id number.
	 * @return the num the unique id number for this research site
	 */
	public long getNum() {
		return num;
	}

	/**
	 * Sets the unique id number for this research site
	 * @param num the num to set
	 */
	public void setNum(long num) {
		this.num = num;
	}

	/**
	 * Returns the name of a contact HCP.
	 * @return the name of the contact HCP
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the contact HCP
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the number of devices for this research site
	 * @return the devices 
	 */
	public int getDevices() {
		return devices;
	}

	/**
	 * Sets the number of devices that are used at this research site
	 * @param devices the devices to set
	 */
	public void setDevices(int devices) {
		this.devices = devices;
	}

	/**
	 * Generates the hashcode for ResearchSite
	 * @param prime the hashcode for ResearchSite
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + devices;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (num ^ (num >>> 32));
		return result;
	}

	/**
	 * Tests for equality between this ResearchSite and obj parameter
	 * @param obj the object to test equality with this ResearchSite
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResearchSite other = (ResearchSite) obj;
		if (devices != other.devices)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (num != other.num)
			return false;
		return true;
	}
	

}
