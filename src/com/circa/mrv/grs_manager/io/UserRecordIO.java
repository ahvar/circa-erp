package com.circa.mrv.grs_manager.io;


import java.util.Scanner;

import com.circa.mrv.grs_manager.user.Employee;
import com.circa.mrv.grs_manager.user.User;
import com.circa.mrv.grs_manager.util.LinkedAbstractList;
import com.circa.mrv.grs_manager.user.schedule.OrderSchedule;

import java.io.*;

/**
 * This class is passed a file with user records. it reads the file line by line. 
 * each line is checked for the appropriate number of items and item types. a non null user is created
 * if the line is not invalid. 
 * @author Arthur Vargas
 *
 */
public class UserRecordIO {

	/**
	 * reads in a file with user records. passes the file line by line to processUser. it recives
	 * an User object from processUser which could be null. it checks for null and also for duplicate
	 * Users. it creates an linkedList of Users from the non null User. if all records were invalid,
	 * the linked list will be null.
	 * @param fileName name and possibly path of file to read
	 * @return User a linked list of faculty objects that are not null. 
	 * @throws FileNotFoundException if the file is not found at given location
	 */
	public static LinkedAbstractList<User> readUserRecords(String fileName) throws FileNotFoundException {
		
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		
		LinkedAbstractList<User> employeeList = new LinkedAbstractList<User>(10);
		
	    while (fileReader.hasNextLine()) {
	        try {
	            User employee = processUser(fileReader.nextLine());
                if (employee == null){
                  	throw new IllegalArgumentException("Invalid line");
                }	
	            
	            for (int i = 0; i < employeeList.size(); i++) {
	                if (employeeList.get(i).getId().equals(employee.getId())) {
	                	System.err.println("Employee " + employee.getId() + " already in system.");
	                    continue;
	                }
	            }
	            employeeList.add(employee);
	        } catch (IllegalArgumentException e) {
	            //skip the line
	        }
	    }
		
		fileReader.close();
		return employeeList;
	}
	/**
	 * recieves a single employee record and processes it. 
	 * if the record is deemed valid, it passes the items to the employee constructor. 
	 * @param line the passed line. one employee record from the file.
	 * @return employee created employee object or null employee object if the line was invalid. 
	 */
	private static User processUser(String line){
		if(line == null || line.equals(""))
			return null;
		Scanner s = new Scanner(line);
        Employee e = new Employee(s.next(),s.next(),s.next(),s.next(),s.next());
        s.close();
		return e;
	}
	
	/**
	 * writes out employee records to a file from an linked list of employee.
	 * @param fileName file name to write to
	 * @param employees linked list of employee
	 * @throws IOException if the file is not available for writing
	 */
	public static void writeUserRecords(String fileName, LinkedAbstractList<User> employee) throws IOException {	

		PrintStream fileWriter = new PrintStream(new File(fileName));
		//creates new output printstream object for new file
		
		for(int i = 0 ; i < employee.size() ; i++){//loops through the faculty directory linked list
			
			fileWriter.println(employee.get(i).toString());
			//writes out each item in the list
		}
		
		fileWriter.close();
	}
}
