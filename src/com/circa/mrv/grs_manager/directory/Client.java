/**
 * 
 */
package com.circa.mrv.grs_manager.directory;
import com.circa.mrv.grs_manager.location.BillTo;
import com.circa.mrv.grs_manager.location.Location;
import com.circa.mrv.grs_manager.util.LinkedListRecursive;

/**
 * The Client class contains a list of locations, a business name, the name of a primary physician, and certain
 * number of devices.
 * @author ArthurVargas
 */
public abstract class Client {
	/** A list of locations for the customer */
    private LinkedListRecursive<Location> locations;
    /** Name of the business */
    private String name;

    
    /**
     * Initializes the field for the Client.
     * 
	 * @param locations a list of locations
	 * @param name business name of the client
	 * @param doc name of the primary physician
	 * @param devices number of devices the practice has
	 */
	public Client(LinkedListRecursive<Location> locations, String name) {
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
