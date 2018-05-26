package com.circa.mrv.grs_manager.directory;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.circa.mrv.grs_manager.io.CompanyRecordIO;
import com.circa.mrv.grs_manager.location.Location;
import com.circa.mrv.grs_manager.location.BillTo;
import com.circa.mrv.grs_manager.util.LinkedListRecursive;
import com.circa.mrv.grs_manager.user.Employee;

/**
 * Maintains a directory of all companies.
 * 
 * @author Arthur Vargas
 */
public class CompanyDirectory {
	
	/** List of companies in the directory */
	private LinkedListRecursive<Company> companyDirectory;
	
	/** Hashing algorithm */
	//private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * Creates an empty company directory. 
	 */
	public CompanyDirectory() {
		companyDirectory = new LinkedListRecursive<Company>();
	}
	
	/**
	 * Creates a new company directory with a single bill to location in the company directory's list.
	 * @param name the name of the company
	 * @param add1 the street address of the company
	 * @param add2 the suite or bldg number for the company
	 * @param city the city the company is in
	 * @param state the state the company is in
	 * @param zip the zip code for the compnay
	 * @param country the country where the company is located
	 */
	public CompanyDirectory(String name,String add1,String add2,String city,String state,String zip,String country) {
		addCompany(name,add1,add2,city,state,zip,country);
	}
	
	/**
	 * Creates an empty company directory.  All companies in the previous
	 * list are lost unless saved by the user.
	 */
	public void newCompanyDirectory() {
		 new CompanyDirectory();
	}
	
	/**
	 * Returns the company at index specified by index parameter
	 * @param index the index in the directory
	 * @return company the company 
	 */
	public Company getCompanyAt(int index) {
		return companyDirectory.get(index); 
			
	}
	
	/**
	 * Constructs the Company directory by reading in Company information
	 * from the given file.  Throws an IllegalArgumentException if the 
	 * file cannot be found.
	 * @param fileName file containing list of Companies
	 */
	public void loadCompanyFromFile(String fileName) {
		try {
			companyDirectory = CompanyRecordIO.readCompanyRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Adds a company with the name specified in the name parameter and a billing/office location defined
	 * by the local  parameter.  Returns true if the company is added and false if
	 * the company is unable to be added because their name and address match another company. Throws an IAE if 
	 * the company is not a current GRS customer.
	 * 
	 * @param name the name of the company
	 * @param local a bill-to location
	 * @return true if added
	 * @throws IllegalArgumentException if the company is not eRT or Circassia.
	 */
	public boolean addCompany(String name, Location local) {
		
		if(!name.equals(Company.cir) || !name.equals(Company.ert)) {
			throw new IllegalArgumentException("This company already exists. Contact administrator to setup new company.");
		} else if(name.contains(Company.cir)) {
	
			return companyDirectory.add(new VendorCompany(local, name));
		} else if(name.contains(Company.ert)) { 
			return companyDirectory.add(new ResearchCompany(local,name));
		}
		return false;

	}
	
	/**
	 * Adds a company with the name specified in the name parameter and a billing/office location defined
	 * by the address information passed into the corresponding parameters.  Returns true if the company is 
	 * added and false if the company is unable to be added because their name and address match another company. 
	 * Throws an IAE if the company is not a current GRS customer.
	 * 
	 * @param name 
	 * @param add1
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 * @return true if added
	 * @throws IllegalArgumentException if the company is not eRT or Circassia.
	 */
	public boolean addCompany(String name, String add1, String city, String state, String zip, String country) {
		
		if(!name.equals(Company.cir) || !name.equals(Company.ert)) {
			throw new IllegalArgumentException("This company already exists. Contact administrator to setup new company.");
		} else if(name.contains(Company.cir)) {
			
			return companyDirectory.add(new VendorCompany(new BillTo(add1,city,state,zip,country,"first last"), name));
		} else if(name.contains(Company.ert)) {
			return companyDirectory.add(new ResearchCompany(new BillTo(add1,city,state,zip,country,"first last"),name));
		}
		return false;

	}
	
	/**
	 * Adds a company with the name specified in the name parameter and a billing/office location defined
	 * by the address information passed into the corresponding parameters.  Returns true if the company is 
	 * added and false if the company is unable to be added because their name and address match another company. 
	 * Throws an IAE if the company is not a current GRS customer.
	 * 
	 * @param name 
	 * @param add1
	 * @param add2
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 * @return true if added
	 * @throws IllegalArgumentException if the company is not eRT or Circassia.
	 */
	public boolean addCompany(String name, String add1, String add2, String city, String state, String zip, String country) {	
		
		if(!name.equals(Company.cir) && !name.equals(Company.ert)) { 
			throw new IllegalArgumentException("This company already exists. Contact administrator to setup new company.");
		} else if(name.equals(Company.cir)) {
			companyDirectory.add(new VendorCompany(new BillTo(add1,add2,city,state,zip,country), name));
			System.out.println("companies in CompanyDirectory: " + getCompanyList().size());
			return true;
		} else if(name.equals(Company.ert)) {
			return companyDirectory.add(new ResearchCompany(name,add1,add2,city,state,country,zip));
		}
		return false;
	}
	
	/**
	 * Adds the ResearchCompany parameter to the list of companies instance variable.
	 * @param researchCompany the research company to add
	 * @throws IllegalArgumentException if this company already exists in the list
	 */
	public void addResearchCompany(ResearchCompany rc) {
		try{
			companyDirectory.add(rc);
		} catch(IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		
	}
	
	/**
	 * Adds the VendorCompany parameter to the list of companies.
	 * @param vendorCompany the vendor company to add
	 * @throws IllegalArgumentException if this company already exists in the list
	 */
	public void addVendorCompany(VendorCompany vc) {
		try {
			companyDirectory.add(vc);
		} catch (IllegalArgumentException iae) {
			throw new IllegalArgumentException(iae.getMessage());
		}
	}
	
	/**
	 * Removes the company with the given name from the list of companies.
	 * Returns true if the company is removed and false if the employee is not in the list.
	 * @param name the name of the company
	 * @return true if removed
	 */
	public boolean removeCompany(String name) {
		for (int i = 0; i < companyDirectory.size(); i++) {
			if (companyDirectory.get(i).getName().equals(name)) {
				companyDirectory.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns a list with all companies in the GRS Manager 
	 * @return companyDirectory the list of companies in GRS
	 */
	public LinkedListRecursive<Company> getCompanyList() {
		return companyDirectory;
	}
	/**
	 * Returns all companies in the directory with a columns for name and address information.
	 * @return String array containing company's name and address info.
	 */
	public String[][] getCompanyDirectory() {
		String [][] directory = new String[companyDirectory.size()][6];
		for (int i = 0; i < companyDirectory.size(); i++) {
			for(int j = 0; j < companyDirectory.get(i).getLocations().size(); j++) {
				if(companyDirectory.get(i).getLocations().get(j) instanceof BillTo) 
					directory[i][0] = companyDirectory.get(i).getName();
				    directory[i][1] = companyDirectory.get(i).getLocations().get(j).getAddress1();
				    directory[i][2] = companyDirectory.get(i).getLocations().get(j).getAddress2();
				    directory[i][3] = companyDirectory.get(i).getLocations().get(j).getCity();
				    directory[i][4] = companyDirectory.get(i).getLocations().get(j).getState();
				    directory[i][5] = companyDirectory.get(i).getLocations().get(j).getZip();
				    directory[i][6] = companyDirectory.get(i).getLocations().get(j).getCountry();
			}
		}
		return directory;
	}
	
	/**
	 * Saves all companies in the directory to a file.
	 * @param fileName name of file to save company to.
	 */
	public void saveCompanyDirectory(String fileName) {
		try {
			CompanyRecordIO.writeCompanyRecords(fileName, companyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}

	/**
	 * Gets the company, name, from the directory if the company name matches one in the directory. 
	 * @param name the  name of the company 
	 * @return the company. If no match is found, null is returned. 
	 */
	public Company getCompanyByName(String name) {
		for (int i = 0 ; i < companyDirectory.size() ; i++){
			if (companyDirectory.get(i).getName().equals(name))
				return companyDirectory.get(i);
		}
		return null;
	}
	
	/**
	 * Gets the company, name, from the directory if the company name matches one in the directory. 
	 * @param name the  name of the company 
	 * @param add1 the street address of company bill to
	 * @return the company. If no match is found, null is returned. 
	 */
	public Company getCompanyByNameAndStreet(String name, String add1) {
		for (int i = 0 ; i < companyDirectory.size() ; i++) {
			for(int j = 0; j < companyDirectory.get(i).getLocations().size(); j++)
			    if ( companyDirectory.get(i).getLocations().get(j) instanceof BillTo )
			    	if(companyDirectory.get(i).getLocations().get(j).getAddress1().equals(add1) &&
			    			companyDirectory.get(i).getName().equals(name))
			    		return companyDirectory.get(i);
		}
		return null;
	}
	
	/**
	 * Searches all of the locations in all of the companies in the directory and returns the employee
	 * whose id matches the string passed into getEmployeeById() method. If no employee is found matching this id
	 * getEmployeeById() returns null.
	 * 
	 * @param id the unique id for this employee
	 * @return the employee whose id matches the id parameter
	 */
	public Employee getEmployeeById(String id) {
      for(int i = 0; i < companyDirectory.size(); i++) {
	    for(int j = 0; j < companyDirectory.get(i).getLocations().size(); j++) {
		  for(int k = 0; k < companyDirectory.get(i).getLocations().get(j).getEmployees().size(); k++) {		
		    if(companyDirectory.get(i).getLocations().get(j).getEmployees().get(k).getId().equals(id)) 
		    	return companyDirectory.get(i).getLocations().get(j).getEmployees().get(k);
		  }
		}
	  }
      return null;
	}
	
	/**
	 * Searches the employee list of the company whose name matches parameter name and whose bill to street address
	 * matches the street parameter. 
	 * @param emp the employee to add to the bill to
	 * @param name the name of the company
	 * @param street the street address of the bil to
	 * @return true if the employee is successfully added.
	 */
	public boolean addEmployeeToBillToLocation(Employee emp, String name, String street) {
		for(int i = 0; i < companyDirectory.size(); i++) {
			for(int j = 0; j < companyDirectory.get(i).getLocations().size(); j++) {
				if(companyDirectory.get(i).getLocations().get(j) instanceof BillTo &&
				companyDirectory.get(i).getName().equals(name) && 
				companyDirectory.get(i).getLocations().get(j).getAddress1().equals(street) ) {
					return companyDirectory.get(i).getLocations().get(j).getEmployees().add(emp);
				}
			}
		}
		return false;
	}
	
	/**
	 * Returns string array with research company employee data.
	 * @return employees the research employees
	 */
	public String[][] getResearchEmployeeData() {
		int p = 0;
		String [][] employees = new String[companyDirectory.size()][4];
		for(int i = 0; i < companyDirectory.size(); i++) {
			if(companyDirectory.get(i) instanceof ResearchCompany) {
			  for(int j = 0; j < companyDirectory.get(i).getLocations().size();j++) {
				  for(int k = 0; k < companyDirectory.get(i).getLocations().get(j).getEmployees().size(); k++ ) {
					  employees[p][0] = companyDirectory.get(i).getLocations().get(j).getEmployees().get(k).getFirstName();
					  employees[p][1] = companyDirectory.get(i).getLocations().get(j).getEmployees().get(k).getLastName();
					  employees[p][2] = companyDirectory.get(i).getLocations().get(j).getEmployees().get(k).getId();
					  employees[p][3] = companyDirectory.get(i).getLocations().get(j).getEmployees().get(k).getEmail();
					  p++;
				  }
			  }
			}
		}
		return employees;
	}
	
	/**
	 * Returns string array with vendor company employee data.
	 * @return employees the research employees
	 */
	public String[][] getVendorEmployeeData() {
		int p = 0;
		String [][] employees = new String[companyDirectory.size()][4];
		for(int i = 0; i < companyDirectory.size(); i++) {
			if(companyDirectory.get(i) instanceof VendorCompany) {
			  for(int j = 0; j < companyDirectory.get(i).getLocations().size();j++) {
				  for(int k = 0; k < companyDirectory.get(i).getLocations().get(j).getEmployees().size(); k++ ) {
					  employees[p][0] = companyDirectory.get(i).getLocations().get(j).getEmployees().get(k).getFirstName();
					  employees[p][1] = companyDirectory.get(i).getLocations().get(j).getEmployees().get(k).getLastName();
					  employees[p][2] = companyDirectory.get(i).getLocations().get(j).getEmployees().get(k).getId();
					  employees[p][3] = companyDirectory.get(i).getLocations().get(j).getEmployees().get(k).getEmail();
					  p++;
				  }
			  }
			}
		}
		return employees;
	}

}
