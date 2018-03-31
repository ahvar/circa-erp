/**
 * 
 */
package com.circa.mrv.grs_manager.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.circa.mrv.grs_manager.util.LinkedAbstractList;

/**
 * StudyRecordIO handles IO of GRS data, including customers, research studies, sites, location information, etc.
 * This data is parsed from the purchase history of GRS customers. 
 * 
 * @author Arthur  Vargas
 */
public class StudyRecordIO {
	
	/**
	 * Reads in a master file containing all data for a particular GRS customer. 
	 * students. it creates an array list of students from the non null students. if all records were invalid,
	 * the array list will be null.
	 * @param fileName name and possibly path of file to read
	 * @return students an array list of student objects that are not null. 
	 * @throws FileNotFoundException if the file is not found at given location
	 */
	public static LinkedAbstractList<String> readStudyRecords(String filename) throws FileNotFoundException, IOException {
		
		ArrayList<Character> delim = new ArrayList<Character>();
    	StringBuilder segment = new StringBuilder();
    
    	LinkedAbstractList<String> titles = new LinkedAbstractList<String>(10);
    	FileReader fr = new FileReader(filename);
    	BufferedReader reader = new BufferedReader(fr);
    	
    	int x;
    	char c;
    	while( (x = reader.read()) != -1 ) {
    		c = (char)x;
    		
    	    if(Character.isWhitespace(c)) {
    	    	
    	    	segment.append(' ');
    	    	continue;
    	    	
    	    } else if( x == ',' && delim.size() == 0 && segment.length() != 0 ) { // end of a segment, maybe it is an extended segment 
    			
    			titles.add(segment.toString());
    			segment = clearStringBuilder(segment);
    			c = (char)x;
    			delim.add(c); // add the delimiter
    			
    		} else if ( x == '"' && delim.size() == 1 ) { // end of a segment, reader is entering a long segment
    		    c = (char)x;
    			delim.add(c);
    			
    		} else if ( delim.size() == 2 && x != '"' && x != '\n' && x != '\t' ) { // reader is in long segment and not on an ending quotation
    		    
    			c = (char)x;
    			segment.append(c);
    			
    		} else if ( delim.size() == 2 && x == '"') { // reader is at the end of a long segment
    			
    			titles.add(segment.toString());
    			segment = clearStringBuilder(segment);
    			reader.skip(1); // skips the comma following the quotation mark
    			delim.clear();
    			
    		} else if ( x != ',' && x != '"' && x != '\n' && x != '\t' && delim.size() == 0 ) {
    			
    			c = (char)x;
    			segment.append(c);
    			
    		} else if ( x != ',' && x != '"' && x != '\n' && x != '\t' && delim.size() == 1 ) {
    			
    			delim.clear();
    			c = (char)x;
    			segment.append(c);
    			
    		}

    	}
    	reader.close();
    	return titles;
    	
    }
	
	/**
	 * Empties the StringBuilder.
	 * 
	 * @param sb the StringBuilder
	 * @return an empty StringBuilder
	 */
	public static StringBuilder clearStringBuilder( StringBuilder sb ) {
    	while(sb.length() != 0)
    		sb.deleteCharAt(sb.length() - 1);
    	return sb;
    }

}
