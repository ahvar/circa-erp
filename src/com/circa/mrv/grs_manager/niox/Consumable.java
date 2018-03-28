/**
 * 
 */
package com.circa.mrv.grs_manager.niox;

import java.util.Calendar;

/**
 * The Consumable interface declares the methods that an implementing component must define to calculate
 * the expiration.
 * 
 * @author Arthur Vargas
 */
public interface Consumable {
	
	/**
	 * This method calculates the expiration date for the accessory to be 1 year from the purchase date and 
	 * returns the expiration date.
	 * 
	 * @param purchaseDate the date the device was purchased on
	 * @return the date the accessory expires
	 */
	public Boolean checkExpiration( Calendar purchaseDate ) throws ExpirationException ;

}
