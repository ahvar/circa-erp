/**
 * 
 */
package com.circa.mrv.grs_manager.document;

import java.util.Calendar;

import com.circa.mrv.grs_manager.manager.GRSManager;

/**
 * The Document class is an abstract class that defines instance variables and methods for a document maintained by
 * a company. 
 * 
 * @author Arthur Vargas
 */
public abstract class Document {
	/** Document number */
	private long number;
	/** The date the document is created */
	private Calendar creation;
	/** The id of the user who created the document */
	private String userID;

	/**
	 * Constructs an instance of Document by assigning its number parameter to the number instance variable and
	 * setting today's date as the creation instance variable if creation date is null.
	 * @param number the unique ID number for this document
	 * @param userID the id of the user creating this document
	 */
	public Document(long number, String userID, Calendar creationDate) {
		setNumber(number);
		if(creationDate == null)
			setCreation(Calendar.getInstance());
		else 
			setCreation(creationDate);
		setUserId(userID);
	}
	
	/** Default constructor for document */
	public Document(){}

	/**
	 * Returns the unique number for this document
	 * @return the number
	 */
	public long getNumber() {
		return number;
	}

	/**
	 * Sets the unique id number for this document to the parameter number
	 * @param number the number to set
	 */
	public void setNumber(long number) {
		this.number = number;
	}

	/**
	 * Returns the date the document was created
	 * @return the creation
	 */
	public Calendar getCreation() {
		return creation;
	}

	/**
	 * Sets the creation date to the parameter date
	 * @param creation the creation to set
	 */
	public void setCreation(Calendar creation) {
		this.creation = creation;
	}

	/**
	 * Returns the unique id for the user who created this document
	 * @return the id
	 */
	public String getUserId() {
		return userID;
	}

	/**
	 * Sets the unique id for the user who created this document
	 * @param id the id to set
	 */
	public void setUserId(String id) {
		this.userID = id;
	}

	/**
	 * Generates the hashcode for Document
	 * @result the hashcode for Document
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creation == null) ? 0 : creation.hashCode());
		result = prime * result + (int) (number ^ (number >>> 32));
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		return result;
	}

	/**
	 * Compares this Document for equality with the parameter object
	 * @param obj the object for comparison with this Document
	 * @return true if this Document is equal to the parameter object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Document))
			return false;
		Document other = (Document) obj;
		if (creation == null) {
			if (other.creation != null)
				return false;
		} else if (!creation.equals(other.creation))
			return false;
		if (number != other.number)
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}

}
