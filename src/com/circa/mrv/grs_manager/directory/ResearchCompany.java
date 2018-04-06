/**
 * 
 */
package com.circa.mrv.grs_manager.directory;
import com.circa.mrv.grs_manager.location.Location;
import com.circa.mrv.grs_manager.location.ResearchSite;
import com.circa.mrv.grs_manager.util.LinkedListRecursive;

/**
 * The ResearchClient class extends the Client class and contains a list of research studies.
 * 
 * @author ArthurVargas
 */
public class ResearchCompany extends Company {
	/** A list of research studies */
	private LinkedListRecursive<Study> studies;
	/** The total number of devices this research client has */
	private int devices;
	
	public ResearchCompany( LinkedListRecursive<Location> locations, String name, LinkedListRecursive<Study> studies ){
		super(locations, name);
		setStudies(studies);
		setDevices();
	}
	
	public ResearchCompany( Location local, String name ){
		super(local, name);
		setStudies(null);
		setDevices();
	}
	
	
	/**
	 * Sets the list of studies to the instance variable named 'studies'.
	 * @param studies a list of studies
	 */
	public void setStudies(LinkedListRecursive<Study> studies) {
		this.studies = studies;
	}
	
	/**
	 * Returns the instance variable 'studies' which is a list of the studies for this research client.
	 * @return studies a list of studies
	 */
	public LinkedListRecursive<Study> getStudies() {
		return studies;
	}
	
	/**
	 * Counts the devices in all the studies and assigns it to the instance variable named 'devices'
	 */
	public void setDevices() {
		if(studies.isEmpty()) { 
		  this.devices = 0; 
		} else {
		    for(int i = 0; i < studies.size(); i++ ) {
			  for(int j = 0; j < studies.get(i).getSites().size(); j++) {
			    this.devices += studies.get(i).getSites().get(j).getDevices();
			  }
			}   
		}
		
	}
	
	/**
	 * Returns the number of devices contained in all the studies for this research client.
	 * @return devices the number of devices
	 */
	public int getDevices() {
		return this.devices;
	}
	
}
