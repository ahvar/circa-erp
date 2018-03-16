package edu.ncsu.csc216.pack_scheduler.user;

/**
 * The user class represents a user of the system. The user has a first name, last name, id, email, and password. 
 * @author Ben Ioppolo
 */
public abstract class User {

	private String firstName;
	private String lastName;
	private String id;
	private String email;
	private String hashPW;

	/**
	 * Constructor for User. 
	 * @param firstName the users first name
	 * @param lastName the users last name
	 * @param id the users id
	 * @param email the users email
	 * @param hashPW the users hashed password
	 */
	public User(String firstName, String lastName, String id, String email, String hashPW) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(hashPW);
	}

	/**
	 * gets the students first name
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * sets the students first name
	 * @param firstName the firstName to set
	 * @throws IllegalArgumentException if firstName is null or empty
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || firstName.isEmpty()){
			//System.out.println("Invalid first name");
			throw new IllegalArgumentException("Invalid first name");
	
		}
			this.firstName = firstName;
	}

	/**
	 * gets the students last name
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * sets the students last name
	 * @param lastName the lastName to set
	 * @throws IllegalArgumentException if lastName is null or empty	 
	 */
	public void setLastName(String lastName) {
		if(lastName == null || lastName.isEmpty()){
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * gets student id
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * sets the students id
	 * @param id the id to set
	 * @throws IllegalArgumentException if id is null or empty
	 */
	protected void setId(String id) {
		if(id == null || id.isEmpty()){
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * gets student email
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * sets the student email
	 * @param email the email to set
	 * @throws IllegalArgumentException if email is null or empty or if it is missing "." or "@" or if "." and "@" are out of order
	 */
	public void setEmail(String email) {
		if(email == null || email.isEmpty() || email.indexOf("@") == -1 || email.indexOf(".") == -1 
				|| email.lastIndexOf(".") < email.indexOf("@")){
			throw new IllegalArgumentException("Invalid email");
		}
		
		this.email = email;
	}

	/**
	 * gets student password
	 * @return the hashPW
	 */
	public String getPassword() {
		return hashPW;
	}

	/**
	 * sets student password
	 * @param hashPW the hashPW to set
	 * @throws IllegalArgumentException if hashPW is null or empty
	 */
	public void setPassword(String hashPW) {
		if(hashPW == null || hashPW.isEmpty()){
			throw new IllegalArgumentException("Invalid password");
		}
		this.hashPW = hashPW;
	}

	/**
	 * Generates a hashcode for student using the maxCredits field. 
	 * @return result the hashcode for student
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((hashPW == null) ? 0 : hashPW.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	/**
	 * compares a given object to this object for equality on all fields. 
	 * @param obj the object to compare
	 * @return true if the objects are the same on all fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (hashPW == null) {
			if (other.hashPW != null)
				return false;
		} else if (!hashPW.equals(other.hashPW))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
}