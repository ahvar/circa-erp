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
     * Initializes the field locations instance variable for Company and adds the parameter local.
     * Sets the name of the business to parameter name.
     * @param location a location for the company
     * @param name the name of the company
     */
    public Company(Location local, String name) {
    	locations = new LinkedListRecursive<Location>();
    	locations.add(local);
    	setName(name);
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
