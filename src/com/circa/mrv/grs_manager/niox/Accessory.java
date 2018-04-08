package com.circa.mrv.grs_manager.niox;

import java.util.Calendar;

/**
 * The accessory class extends the Component class and implements the Expiration interface. A accessory's expiration
 * can be calculated in two ways: 
 * 
 * 1.) based on the package date 
 * 2.) based on the date the accessory is installed into a Niox monitor. 
 * 
 * The date of installation is defined by the instance variable 'install'
 * A accessory contains a certain number of tests which is defined by the instance variable 'size'.
 * 
 * @author Arthur Vargas
 *
 */
public class Accessory extends Component implements Consumable {
    /** The number of FeNO tests on the accessory */
	private int size;
	/** Date of installation */
	private Calendar install;
    
	/**
	 * Constructs an instance of accessory with a description (e.g. Test Kit 100), a part number, a price, and the 
	 * number of tests the accessory contains.
	 * 
	 * @param name the name of the accessory
	 * @param description the accessory description
	 * @param partNumber the accessory part number
	 * @param price the price of the accessory
	 * @param serial the serial number of the accessory
	 * @param packageDate the package date
	 * @param installDate the date the accessory was installed
	 * @param generation the product generation
	 * @param size the number of tests on the accessory
	 */
	public Accessory(String name, String description, String partNumber, double price, long serial, Calendar packageDate, 
			Calendar installDate, String generation, int size) {
		super(name, description,partNumber, price, packageDate,serial,generation);
		setSize(size);
		setInstall(install);
	}

	/**
	 * Returns the size of the accessory
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Sets the size to the parameter 
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Returns the installation date
	 * @return the install date
	 */
	public Calendar getInstall() {
		return install;
	}

	/**
	 * Sets the installation date to the parameter install
	 * @param install the install to set
	 */
	public void setInstall(Calendar install) {
		this.install = install;
	}

	/**
	 * Generates the hashcode for the accessory
	 * @param result the hashcode for accessory
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((install == null) ? 0 : install.hashCode());
		result = prime * result + size;
		return result;
	}

	/**
	 * Tests this accessory for equality with the object parameter
	 * @param obj the object to test for equality with this accessory
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Accessory))
			return false;
		Accessory other = (Accessory) obj;
		if (install == null) {
			if (other.install != null)
				return false;
		} else if (!install.equals(other.install))
			return false;
		if (size != other.size)
			return false;
		return true;
	}
	
	/**
	 * Calculates the expiration date based on the installation date.
	 * @return date the expiration date based on the installation date.
	 */
	public Calendar calculateInstallationExpiration() {
	    Calendar exp = this.getInstall();
	    exp.add(Calendar.YEAR, 1);
	    return exp;
	}
	
	
	
	/**
	 * Calculates the expiration date based on the package date.
	 * @return date the expiration date based on the package date.
	 */
	public Calendar calculatePackageExpiration() {
	    Calendar exp = this.getPackageDate();
	    exp.add(Calendar.YEAR, 2);
	    return exp;
	}
	
	/**
	 * This method calculates the expiration date for the accessory to be 2 years from the package date and 
	 * 1 year from the date of installation.
	 * 
	 * @param purchaseDate the date the device was purchased on
	 * @return the date the accessory expires
	 * @throws ExpirationException
	 */
	public Boolean checkExpiration( Calendar purchaseDate ) throws ExpirationException {
		if( calculatePackageExpiration().compareTo(Calendar.getInstance()) < 0 ||
				calculateInstallationExpiration().compareTo(Calendar.getInstance()) < 0 )
			return false;
		return true;
		
	}

}
