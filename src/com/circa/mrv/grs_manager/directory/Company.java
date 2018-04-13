/**
 * 
 */
package com.circa.mrv.grs_manager.directory;
import com.circa.mrv.grs_manager.location.BillTo;
import com.circa.mrv.grs_manager.location.Location;
import com.circa.mrv.grs_manager.util.LinkedListRecursive;

/**
 * The Company has a list of locations and business name. 
 * 
 * @author ArthurVargas
 */
public abstract class Company {
	/** eRT is a research company */
	public static final String ert = "ERT";
	/** Circassia is a vendor company */
	public static final String cir = "Circassia Pharmaceuticals";
	/** A list of locations for the customer */
    private LinkedListRecursive<Location> locations;
    /** Name of the business */
    private String name;

    /**
     * Initializes the field locations instance variable to contain the location information passed into the 
     * constructor. A BillTo location is created and added to the list of locations for a company constructed
     * with the name passed into the parameter. An empty string is passed to the name of the billing contact 
     * parameter.
     * 
     * Sets the name of the business to parameter name.
     * @param name the name of the company
     * @param add1 street address
     * @param city the city
     * @param state the state
     * @param zip the zip code
     * @param country the country
     */
    public Company(String name, String add1, String city, String state, String zip, String country) {
    	locations = new LinkedListRecursive<Location>();
    	locations.add(new BillTo(add1,city,state,zip,country,""));
    	setName(name);
    }
    
    /**
     * Initializes the field locations instance variable to contain the location information passed into the 
     * constructor. A BillTo location is created and added to the list of locations for a company constructed
     * with the name passed into the parameter.
     * 
     * Sets the name of the business to parameter name.
     * @param location a location for the company
     * @param name the name of the company
     */
    public Company(String name, String add1, String add2, String city, String state, String country, String zip) {
    	locations = new LinkedListRecursive<Location>();
    	locations.add(new BillTo(add1,add2,city,state,country,zip));
    	setName(name);
    }
     
    /**
     * Initializes the field locations instance variable for Company and adds the parameter local.
     * Sets the name of the business to parameter name.
     * @param location a location for the company
     * @param name the name of the company
     */
    public Company(Location local, String name) {
    	System.out.println("enter company constructor");
    	locations = new LinkedListRecursive<Location>();
    	locations.add(local);
    	setName(name);
    	System.out.println("vendor locations" + locations.size());
    }
    
    /**
     * Initializes the field for the Client.
     * 
	 * @param locations a list of locations
	 * @param name business name of the client
	 */
	public Company(LinkedListRecursive<Location> locations, String name) {
		setLocations(locations);
		setName(name);
	}
	
	/**
	 * @return the locations
	 */
	public LinkedListRecursive<Location> getLocations() {
		return locations;
	}
	
	/**
	 * @param locations the locations to set
	 */
	public void setLocations(LinkedListRecursive<Location> locations) {
		this.locations = locations;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	

}
