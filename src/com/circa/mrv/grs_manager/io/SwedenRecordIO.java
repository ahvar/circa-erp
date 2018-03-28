package com.circa.mrv.grs_manager.io;


import java.util.Scanner;

import com.circa.mrv.grs_manager.user.Sweden;
import com.circa.mrv.grs_manager.user.User;
import com.circa.mrv.grs_manager.util.LinkedAbstractList;

import java.io.*;

/**
 * This class is passed a file with Faculty records. it reads the file line by line. 
 * each line is checked for the appropriate number of items and item types. a non null faculty is created
 * if the line is not invalid. 
 * @author Ben Ioppolo
 *
 */
public class SwedenRecordIO {

	/**
	 * reads in a file with faculty records. passes the file line by line to processStudent. it recieves
	 * a faculty object from processFaculty which could be null. it checks for null and also for duplicate
	 * faculty. it creates an linkedList of Faculty from the non null faculty. if all records were invalid,
	 * the linked list will be null.
	 * @param fileName name and possibly path of file to read
	 * @return faculty a linked list of faculty objects that are not null. 
	 * @throws FileNotFoundException if the file is not found at given location
	 */
	public static LinkedAbstractList<Sweden> readFacultyRecords(String fileName) throws FileNotFoundException {
		
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		//set new scanner to read file
		LinkedAbstractList<Sweden> facultyList = new LinkedAbstractList<Sweden>(100);
		//set new linked list	
	    while (fileReader.hasNextLine()) {
	        try {
	            Sweden faculty = processFaculty(fileReader.nextLine());
	            //set a new faculty object to returned faculty
                if (faculty == null){
                	//checks if returned faculty was null (record was invalid)
                	//line was invalid
                  	throw new IllegalArgumentException("Invalid line");
                }	
	            boolean duplicate = false;
	            for (int i = 0; i < facultyList.size(); i++) {
	            	//loop through list to check for duplicates
	                User f = facultyList.get(i);
	                if (faculty == null){
	                	//checks if returned faculty was null (record was invalid)
	                	//line was invalid
	                	duplicate = true;
	                	throw new IllegalArgumentException("Invalid line");
	                }	
	                else if (faculty.getId().equals(f.getId())) {
	                	//check for duplicates
	                    //it's a duplicate
	                    duplicate = true;
	                	throw new IllegalArgumentException("Faculty already in system");
	                }
	            }
	            if (!duplicate) {
	                facultyList.add(facultyList.size(), faculty);
	            }
	        } catch (IllegalArgumentException e) {
	            //skip the line
	        }
	    }
		
		fileReader.close();
		return facultyList;
	}
	/**
	 * recieves a single faculty record and processes it. it counts the items in the record to determine
	 * if the record is invalid. it checks the type of the 5th record for an int. it limits valid records to 5 or 6
	 * items. if the record is deemed valid, it passes the items to the faculty constructor. 
	 * @param line the passed line. one faculty record from the file.
	 * @return faculty created faculty object or null faculty object if the line was invalid. 
	 */
	private static Sweden processFaculty(String line){
			Sweden faculty = null;
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
						return faculty;

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
					return faculty;
					//null
				}
				
				else if(itemCount == 5){
					//if 5 strings then 5 string constructor can be used
					
					faculty = new Sweden(lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next());
				}
				else{
					//item count is 6
					//if 5 strings and int then 6 construtor can be used
					faculty = new Sweden(lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.nextInt());
					}	
			} catch (Exception e){
				//skip
			}
			
		lineScanner.close();
		lineCounter.close();
		
		return faculty;
		//returns faculty null or otherwise for verification
	}
	
	/**
	 * writes out faculty records to a file from an linked list of faculty.
	 * @param fileName file name to write to
	 * @param facultyDirectory linked list of faculty
	 * @throws IOException if the file is not available for writing
	 */
	public static void writeFacultyRecords(String fileName, LinkedAbstractList<Sweden> facultyDirectory) throws IOException {	

		PrintStream fileWriter = new PrintStream(new File(fileName));
		//creates new output printstream object for new file
		
		for(int i = 0 ; i < facultyDirectory.size() ; i++){//loops through the faculty directory linked list
			
			fileWriter.println(facultyDirectory.get(i).toString());
			//writes out each item in the list
		}
		
		fileWriter.close();
	}
}
