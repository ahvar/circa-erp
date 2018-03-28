package com.circa.mrv.grs_manager.niox;

import java.util.Calendar;

import com.circa.mrv.grs_manager.util.LinkedListRecursive;

/**
 * The Kit class has a collection of components. A kit' price is the sum of the prices of its components.
 *  
 * @author Arthur Vargas
 */
public class Kit extends Product {
	/** A list of components */
	private LinkedListRecursive<Component> cmps;

	/**
	 * 
	 */
	public Kit(String description, String partNumber, LinkedListRecursive<Component> cmps) {
		super(description,partNumber);
		setCmps(cmps);
	}


	/**
	 * Returns the list of components in the kit
	 * @return cmps the list of components.
	 */
	public LinkedListRecursive<Component> getCmps() {
		return cmps;
	}


	/**
	 * Sets the list of components to the parameter cmps
	 * @param cmps the cmps to set
	 */
	public void setCmps(LinkedListRecursive<Component> cmps) {
		this.cmps = cmps;
	}
	
	
	/**
	 * Generates the hashcode for an instance of Kit
	 * @return result the hashcode 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cmps == null) ? 0 : cmps.hashCode());
		return result;
	}


	/**
	 * Tests this Kit with equality to the parameter obj
	 * @param obj the Object to test for equality with this Kit
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Kit))
			return false;
		Kit other = (Kit) obj;
		if (cmps == null) {
			if (other.cmps != null)
				return false;
		} else if (!cmps.equals(other.cmps))
			return false;
		return true;
	}

	

}
