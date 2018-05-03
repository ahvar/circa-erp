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

import com.circa.mrv.grs_manager.document.Order;
import com.circa.mrv.grs_manager.util.LinkedAbstractList;
import com.circa.mrv.grs_manager.util.LinkedListRecursive;

/**
 * StudyRecordIO handles IO of GRS data, including customers, research studies, sites, location information, etc.
 * This data is parsed from the purchase history of GRS customers. 
 * cx1DGB08f
 * 
 * @author Arthur  Vargas
 */
public class OrderRecordIO {
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
	public static LinkedListRecursive<Order> readOrderRecord(String filename) throws FileNotFoundException, IOException {
		
		ArrayList<Character> delim = new ArrayList<Character>();
    	StringBuilder segment = new StringBuilder();
    
    	LinkedListRecursive<Order> orders = new LinkedListRecursive<Order>();
    	FileReader fr = new FileReader(filename);
    	BufferedReader reader = new BufferedReader(fr);
    	
    	PrintStream pw = new PrintStream(FORMATTED_TITLES);
    	int current = 0;
    	int x;
    	char c;
    	while( (x = reader.read()) != -1 ) {
    		c = (char)x;
    		
    	    if(Character.isWhitespace(c)) {
    	    	
    	    	segment.append(' ');
    	    	continue;
    	    }
    	    	
    	    /** ending comma for a segment. print line, clear builder, clear delimiter */
        	if( x == ',' && delim.get(0) == ',' && 0 < segment.length() ) { // end of a segment, maybe it is an extended segment 
        			
        		pw.println(segment.toString());
        		segment = OrderRecordIO.clearStringBuilder(segment);
        		delim.clear(); 
        		current++;
    			
        	/** quoted information within a comma separate segment. */
    		} else if ( x == '"' && delim.get(0) == ',' ) { // end of a segment, reader is entering a quoted segment that may contain delimiters
    			delim.clear();
    			delim.add(c);
    			
    			/** all characters between quotation marks is part of the segment */
    		} else if ( delim.get(0) == '"' && x != '"' ) { // reader is in long segment and not on an ending quotation
    		    
    			segment.append(c);
    			
    			/** the end of a segment between quotation marks. print line, clear builder, clear delim list. 
    		    won't increment current until reading ending comma */
    		} else if ( delim.get(0) == '"' && x == '"') { // reader is at the end of a long segment
    			
    			pw.println(segment.toString());
    			segment = OrderRecordIO.clearStringBuilder(segment);
    			delim.clear();
    			
    			/** double commas describe empty column condition */
    		} else if ( x != ',' && delim.get(0) == ',' ) {
    			delim.clear();
    			current++;
    			
    			/** describes condition where a comma follows an ending quotation. marks beginning of new segment. */	
    		} else if ( x == ',' && delim.size() == 0 && segment.length() == 0 ) {
    			current++;
    			delim.add(c);
    		}

    	}
    	reader.close();
    	
    	return orders;
    	
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
