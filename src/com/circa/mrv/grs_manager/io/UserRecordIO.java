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
		//set new scanner to read file
		LinkedAbstractList<User> employeeList = new LinkedAbstractList<User>(100);
		//set new linked list	
	    while (fileReader.hasNextLine()) {
	        try {
	            User employee = processUser(fileReader.nextLine());
	            //set a new faculty object to returned faculty
                if (employee == null){
                	//checks if returned faculty was null (record was invalid)
                	//line was invalid
                  	throw new IllegalArgumentException("Invalid line");
                }	
	            boolean duplicate = false;
	            for (int i = 0; i < employeeList.size(); i++) {
	            	//loop through list to check for duplicates
	                User e = employeeList.get(i);
	                if (employee == null){
	                	//checks if returned faculty was null (record was invalid)
	                	//line was invalid
	                	duplicate = true;
	                	throw new IllegalArgumentException("Invalid line");
	                }	
	                else if (employee.getId().equals(e.getId())) {
	                	//check for duplicates
	                    //it's a duplicate
	                    duplicate = true;
	                	throw new IllegalArgumentException("Employee already in system");
	                }
	            }
	            if (!duplicate) {
	                employeeList.add(employeeList.size(), employee);
	            }
	        } catch (IllegalArgumentException e) {
	            //skip the line
	        }
	    }
		
		fileReader.close();
		return employeeList;
	}
	/**
	 * recieves a single employee record and processes it. it counts the items in the record to determine
	 * if the record is invalid. it limits valid records to 5 or 6
	 * items. if the record is deemed valid, it passes the items to the employee constructor. 
	 * @param line the passed line. one employee record from the file.
	 * @return employee created employee object or null employee object if the line was invalid. 
	 */
	private static User processUser(String line){
			User user = null;
			int itemCount = 0;
			//scanner to use for counting items on line
			Scanner lineCounter = new Scanner(line);
			//set delimiter for ease of scanning
			lineCounter.useDelimiter(",");
			
			//scanner to use for assigning items after verification
			Scanner lineScanner = new Scanner(line);
			lineScanner.useDelimiter(",");	
			
			try{
				while (lineCounter.hasNext()){
					
					if (itemCount == 4 && lineCounter.hasNextInt()){
						//if the 5th item is an int the record is invalid
						//defective line
						//returns null faculty
						lineCounter.close();
						lineScanner.close();
						return user;

					}
					else{
					lineCounter.next();
					//count of items
					itemCount++;

					}
				}
				
				if(itemCount < 5 || itemCount > 6){
					//if too short or too long
					//defective line
					lineScanner.close();
					lineCounter.close();
					return user;
					//null
				}
				
				else if(itemCount == 5){
					//if 5 strings then 5 string constructor can be used
					
					user = new Employee(lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next());
				}
				else{
					//item count is 6
					//if 5 strings and int then 6 construtor can be used
					user = new Employee(lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), new OrderSchedule() );
					}	
			} catch (Exception e){
				//skip
			}
			
		lineScanner.close();
		lineCounter.close();
		
		return user;
		//returns faculty null or otherwise for verification
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
