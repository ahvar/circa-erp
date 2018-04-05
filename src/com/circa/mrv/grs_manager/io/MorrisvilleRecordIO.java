package com.circa.mrv.grs_manager.io;


import java.util.Scanner;

import com.circa.mrv.grs_manager.directory.Product;
import com.circa.mrv.grs_manager.user.User;

import java.io.*;

import edu.ncsu.csc216.collections.list.SortedList;
/**
 * This class is passed a file with student records. it reads the file line by line. 
 * each line is checked for the appropriate number of items and item types. a non null student is created
 * if the line is not invalid. 
 * @author Ben
 *
 */
public class MorrisvilleRecordIO {

	/**
	 * reads in a file with student records. passes the file line by line to processStudent. it recieves
	 * a student object from processStudent which could be null. it checks for null and also for duplicate
	 * students. it creates an array list of students from the non null students. if all records were invalid,
	 * the array list will be null.
	 * @param fileName name and possibly path of file to read
	 * @return students an array list of student objects that are not null. 
	 * @throws FileNotFoundException if the file is not found at given location
	 */
	public static SortedList<Product> readStudentRecords(String fileName) throws FileNotFoundException {
		
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		//set new scanner to read file
		SortedList<Product> students = new SortedList<Product>();
		//set new array list
		//System.out.println("Test 0 " + students.size());	
	    while (fileReader.hasNextLine()) {
	        try {
	            Product student = processStudent(fileReader.nextLine());
	            //set a new student object to returned student
	            //System.out.println("Test 1 " + students.size());
                if (student == null){
                	//checks if returned student was null (record was invalid)
                	//line was invalid
                  	throw new IllegalArgumentException("Invalid line");
                }	
	            boolean duplicate = false;
	            for (int i = 0; i < students.size(); i++) {
	            	//System.out.println("test 2 " + students.size());
	            	//loop through array to check for duplicates
	                User s = students.get(i);
	                if (student == null){
	                	//checks if returned student was null (record was invalid)
	                	//line was invalid
	                	duplicate = true;
	                	throw new IllegalArgumentException("Invalid line");
	                }	
	                else if (student.getId().equals(s.getId())) {
	                	//check for duplicates
	                    //it's a duplicate
	                    duplicate = true;
	                	throw new IllegalArgumentException("Student already in system");
	                }
	            }
	            if (!duplicate) {
	                students.add(student);
	            }
	        } catch (IllegalArgumentException e) {
	            //skip the line
	        }
	    }
		
		fileReader.close();
		return students;
	}
	/**
	 * recieves a single student record and processes it. it counts the items in the record to determine
	 * if the record is invalid. it checks the type of the 5th record for an int. it limits valid records to 5 or 6
	 * items. if the record is deemed valid, it passes the items to the student constructor. 
	 * @param line the passed line. one student record from the file.
	 * @return student created student object or null student object if the line was invalid. 
	 */
	private static Product processStudent(String line){
			Product student = null;
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
						//returns null student
						lineCounter.close();
						lineScanner.close();
						return student;

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
					return student;
					//null
				}
				
				else if(itemCount == 5){
					//if 5 strings then 5 string constructor can be used
					
					student = new Product(lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next());
				}
				else{
					//item count is 6
					//if 5 strings and int then 6 construtor can be used
					student = new Product(lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.nextInt());
					}	
			} catch (Exception e){
				//skip
			}
			
		lineScanner.close();
		lineCounter.close();
		
		return student;
		//returns student null or otherwise for verification
	}
	
	/**
	 * writes out student records to a file from an array list of students.
	 * @param fileName file name to write to
	 * @param studentDirectory array list of students
	 * @throws IOException if the file is not available for writing
	 */
	public static void writeStudentRecords(String fileName, SortedList<Product> studentDirectory) throws IOException {	

		PrintStream fileWriter = new PrintStream(new File(fileName));
		//creates new output printstream object for new file
		
		for(int i = 0 ; i < studentDirectory.size() ; i++){//loops through the student directory array list
			
			fileWriter.println(studentDirectory.get(i).toString());
			//writes out each item in the list
		}
		
		fileWriter.close();
	}
}
