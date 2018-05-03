package com.circa.mrv.grs_manager.io;


import java.util.Scanner;

import com.circa.mrv.grs_manager.directory.Company;
import com.circa.mrv.grs_manager.directory.ResearchCompany;
import com.circa.mrv.grs_manager.directory.VendorCompany;
import com.circa.mrv.grs_manager.location.BillTo;
import com.circa.mrv.grs_manager.user.User;
import com.circa.mrv.grs_manager.util.LinkedListRecursive;

import java.io.*;


/**
 * This class is passed a file with company records. it reads the file line by line. 
 * each line is checked for the appropriate number of items and item types. a non null company is created
 * if the line is not invalid. 
 * 
 * @author Arthur Vargas
 *
 */
public class CompanyRecordIO {

	/**
	 * reads in a file with company records. passes the file line by line to processCompany. it receives
	 * a Company object from processCompany which could be null. it checks for null and also for duplicate
	 * companies. it creates an array list of companies from the non null companies. if all records were invalid,
	 * the array list will be null.
	 * @param fileName name and possibly path of file to read
	 * @return companies an array list of company objects that are not null. 
	 * @throws FileNotFoundException if the file is not found at given location
	 */
	public static LinkedListRecursive<Company> readCompanyRecords(String fileName) throws FileNotFoundException {
		
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		//set new scanner to read file
		LinkedListRecursive<Company> companies = new LinkedListRecursive<Company>();
		boolean duplicate = false;
	    while (fileReader.hasNextLine()) {
	        try {
	            Company c = processCompany(fileReader.nextLine());
	            
                if (c == null){
                  	throw new IllegalArgumentException("Invalid line");
                }	
	           
	            for (int i = 0; i < companies.size(); i++) {
	            	if(c.equals(companies.get(i)))
	            		throw new IllegalArgumentException("Company is already in the directory.");
	            }
	            companies.add(c);

	        } catch (IllegalArgumentException e) {
	            //skip the line
	        }
	    }
		
		fileReader.close();
		return companies;
	}
	
	/**
	 * Receives a single company record and processes it. it counts the items in the record to determine
	 * if the record is invalid. it limits valid records to 5 or 6
	 * items. if the record is deemed valid, it passes the items to the company constructor. 
	 * @param line the passed line. one company record from the file.
	 * @return company created company object or null company object if the line was invalid. 
	 */
	private static Company processCompany(String line){
			Company c = null;
			String name = "";
			Scanner lineScanner = new Scanner(line);
			lineScanner.useDelimiter(",");	
			try{
				name = lineScanner.next();
				BillTo bt = new BillTo(lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(),lineScanner.next());
				if (name.contains(Company.cir)) {	
	              c = new VendorCompany(bt,name);			
				} else if (name.contains(Company.ert)) {
				  c = new ResearchCompany(bt,name);
				}
			} catch (Exception e){
				//skip
			}
			
		lineScanner.close();
		
		return c;
	}
	
	/**
	 * writes out company records to a file from an array list of companies.
	 * @param fileName file name to write to
	 * @param companyDirectory array list of companies
	 * @throws IOException if the file is not available for writing
	 */
	public static void writeCompanyRecords(String fileName, LinkedListRecursive<Company> companyDirectory) throws IOException {	

		PrintStream fileWriter = new PrintStream(new File(fileName));
		
		for(int i = 0 ; i < companyDirectory.size() ; i++){//loops through the student directory array list
			
			fileWriter.println(companyDirectory.get(i).toString());
			//writes out each item in the list
		}
		
		fileWriter.close();
	}
}
