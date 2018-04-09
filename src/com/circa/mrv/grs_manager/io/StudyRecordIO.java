/**
 * 
 */
package com.circa.mrv.grs_manager.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.circa.mrv.grs_manager.util.LinkedAbstractList;

/**
 * StudyRecordIO handles IO of GRS data, including customers, research studies, sites, location information, etc.
 * This data is parsed from the purchase history of GRS customers. 
 * cx1DGB08f
 * 
 * @author Arthur  Vargas
 */
public class StudyRecordIO {
	private static final String UNFORMATTED_TITLES = "test-files/unformatted_titles.txt";
	private static final String FORMATTED_TITLES = "test-files/formatted_titles.txt";
	
	/**
	 * Reads in a master file containing all data for a particular GRS customer. 
	 * students.
	 * 
	 * @param fileName file name
	 * @return students a list of 
	 * @throws FileNotFoundException if the file is not found at given location
	 */
	public static LinkedAbstractList<String> readStudyRecords(String filename) throws FileNotFoundException, IOException {
		
		ArrayList<Character> delim = new ArrayList<Character>();
    	StringBuilder segment = new StringBuilder();
    
    	LinkedAbstractList<String> titles = new LinkedAbstractList<String>(100);
    	FileReader fr = new FileReader(filename);
    	BufferedReader reader = new BufferedReader(fr);
    	PrintStream pw = new PrintStream(FORMATTED_TITLES);
    	
    	int x;
    	char c;
    	while( (x = reader.read()) != -1 ) {
    		c = (char)x;
    		
    	    if(Character.isWhitespace(c)) {
    	    	
    	    	segment.append(' ');
    	    	continue;
    	    	
    	    } else if( x == ',' && delim.size() == 0 && segment.length() != 0 ) { // end of a segment, maybe it is an extended segment 
    			
    			pw.println(segment.toString());
    			segment = clearStringBuilder(segment);
    			c = (char)x;
    			delim.add(c); // add the delimiter
    			
    		} else if ( x == '"' && delim.size() == 1 ) { // end of a segment, reader is entering a long segment
    		    c = (char)x;
    			delim.add(c);
    			
    		} else if ( delim.size() == 2 && x != '"' ) { // reader is in long segment and not on an ending quotation
    		    	
    			c = (char)x;
    			segment.append(c);
    			
    		} else if ( delim.size() == 2 && x == '"') { // reader is at the end of a long segment
    			
    			pw.println(segment.toString());
    			segment = clearStringBuilder(segment);
    			reader.skip(1); // skips the comma following the quotation mark
    			delim.clear();
    			
    		} else if ( x != ',' && x != '"' && delim.size() == 0 ) {
    			
    			c = (char)x;
    			segment.append(c);
    			
    		} else if ( x != ',' && x != '"' && delim.size() == 1 ) {
    			
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
