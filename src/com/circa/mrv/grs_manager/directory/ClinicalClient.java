/**
 * 
 */
package com.circa.mrv.grs_manager.directory;

import com.circa.mrv.grs_manager.location.Location;
import com.circa.mrv.grs_manager.util.LinkedListRecursive;

/**
 * The ClinicalClient class extends abstract class Client and has an owner or primary physician associated with
 * the medical practice.
 * 
 * @author ArthurVargas
 */
public class ClinicalClient extends Client {
    /** Primary physician */
    private String doc;

	/**
	 * Constructs a ClinicalClient with a list of locations, the name of the business, and 
	 * the name of the primary physician.
	 * 
	 * @param locations the different locations for the ClinicalClient
	 * @param name the name of the business
	 * @param doc the name of the physician 
	 */
	public ClinicalClient(LinkedListRecursive<Location> locations, String name, String doc) {
		super(locations, name);
		this.doc = doc;
	}

	/**
	 * Returns the name of the physician.
	 * 
	 * @return the doc the name of the doctor
	 */
	public String getDoc() {
		return doc;
	}

	/**
	 * Sets the instance variable 'doc' to the parameter string.
	 * 
	 * @param doc the doc to set
	 */
	public void setDoc(String doc) {
		this.doc = doc;
	}
	

}
