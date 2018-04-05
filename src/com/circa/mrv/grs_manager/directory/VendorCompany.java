/**
 * 
 */
package com.circa.mrv.grs_manager.directory;

import com.circa.mrv.grs_manager.catalog.NioxCatalog;
import com.circa.mrv.grs_manager.location.Location;
import com.circa.mrv.grs_manager.util.LinkedListRecursive;

/**
 * The Vendor class extends Company.
 * 
 * @author ArthurVargas
 */
public class VendorCompany extends Company {
	/** Catalog of NIOX products */
	private NioxCatalog products;

	/**
	 * Constructs a VendorCompany with a list of locations, the name of the business, and 
	 * the NIOX product catalog.
	 * 
	 * @param locations the different locations for the ClinicalClient
	 * @param name the name of the business 
	 */
	public VendorCompany(LinkedListRecursive<Location> locations, String name) {
		super(locations, name);
		
	}


	

}
