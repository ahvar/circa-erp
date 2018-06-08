/**
 * 
 */
package com.circa.mrv.grs_manager.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import com.circa.mrv.grs_manager.catalog.NioxCatalog;

import com.circa.mrv.grs_manager.manager.GRSManager;

import com.circa.mrv.grs_manager.util.LinkedListRecursive;

/**
 * StudyRecordIO handles IO of order records containing data on customers, research studies, sites, addresses,
 * contact names, notes, products, and other information related to an order. 
 * 
 * cx1DGB08f
 * 
 * @author Arthur  Vargas
 */
public class OrderRecordIO {
	
	/**
	 * Reads in a master file containing all data for a particular GRS customer. 
	 * students.
	 * 
	 * @param fileName file name
	 * @return students a list of 
	 * @throws FileNotFoundException if the file is not found at given location
	 */
	public static void readOrderRecord(String filename, String [][] orders,LinkedListRecursive<ProductTitle> pt, int lastCol) throws FileNotFoundException, IOException {
		
		char delim = ',';
    	StringBuilder segment = new StringBuilder();
    	FileReader fr = new FileReader(filename);
    	BufferedReader reader = new BufferedReader(fr);
    	
    	int col = 0; // current column 
    	int row = 1; // current row
    	int x; 
    	char c; // current character
    	while( (x = reader.read()) != -1 ) {
    		c = (char)x;
   
    		if(col == lastCol) {
    			while( !Character.isWhitespace(c)) {
    				x = reader.read(); 
    				c = (char)x;
        			segment.append(c);
    			}
    			if(0 < segment.length()) {
    				Scanner scan = new Scanner(segment.toString());
    				while(scan.hasNext()) {
    					if(orders[row][col] == null) orders[row][col] = scan.next();
    					else orders[row][col] = orders[row][col] + " " + scan.next();
    				}
    				scan.close();
        		} else orders[row][col] = null;
    	    	col = 0;
    	    	row++;
    	    	continue;
    		} 
    		
    	    if(Character.isWhitespace(c)) {
    	    	segment.append(' ');
    	    	continue;
    	    }
    	    	
    	    /** ending comma for a segment. print line, clear builder, clear delimiter */
        	if( c == ',' && delim == ',' && 0 < segment.length()) {  
        		Scanner scan = new Scanner(segment.toString());
        		while(scan.hasNext()) {
        			if(orders[row][col] == null) orders[row][col] = scan.next();
        			else orders[row][col] = orders[row][col] + " " + scan.next();
        		}
        		segment = OrderRecordIO.clearStringBuilder(segment);
        		col++;
        		scan.close();
    			
        	/** quoted information within a comma separate segment. */
    		} else if ( c == '"' && delim == ',' ) { 
    			delim = c;
    			
    		/** all characters between quotation marks or commas are part of the segment */
    		} else if ( (delim == '"' && c != '"') || (delim == ',' && c != '"' && c != ',') ) { 
    		    
    			segment.append(c);
    			
    		/** the end of a segment between quotation marks. print line, clear builder, clear delim list. 
    		won't increment current until reading ending comma */
    		} else if ( delim == '"' && c == '"') { 
    			Scanner scan = new Scanner(segment.toString());
    			while(scan.hasNext()) {
        			if(orders[row][col] == null) orders[row][col] = scan.next();
        			else orders[row][col] = orders[row][col] + " " + scan.next();
        		}
    			segment = OrderRecordIO.clearStringBuilder(segment);
    			delim = ','; // a comma always follows ending quotation
    			reader.read();
    			col++;
    			scan.close();
    			
    			/** double commas describe empty column condition */
    		} else if ( c == ',' && delim == ',' ) {
    			orders[row][col] = null;
    			col++;
    		} 
    	}

    	reader.close();
    	
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
	
	/**
	 * Reads the order record titles contained in the file passed into the filename parameter and cross 
	 * references each title with the product names to identify product titles. The title must contain a 
	 * valid product family, generation, and description to be considered a product title. If the title is 
	 * identified as a product title, then it is added to the list of product titles passed into the productTitles parameter. 
	 * All titles are assigned to the corresponding column in row 0 of the string array named records.
	 *  
	 * @param filename the filename
	 * @param records the order records
	 * @param productTitles the list of product titles
	 * @throws IOException if there is a problem reading the file
	 */
	public static int readOrderTitles(String filename, String [][] records, LinkedListRecursive<ProductTitle> productTitles) throws IOException {
		char delim = ',';
    	StringBuilder segment = new StringBuilder();
		ProductTitle pt = null;
		FileReader fr = new FileReader(filename);
    	BufferedReader reader = new BufferedReader(fr);
    	int lastCol = 0;
    	int x;
    	char c;
    	while( (x = reader.read()) != -1 ) {
    		c = (char)x;
    		
    		if(lastCol == 53) {
    			if(Character.isWhitespace(c)) segment.append(' ');
    			else segment.append(c);
    			while( (x = reader.read()) != -1) {
    				c = (char)x;
    				if(Character.isWhitespace(c)) segment.append(' ');
        			else segment.append(c);
    			}
    			Scanner scan = new Scanner(segment.toString());
    	    	while(scan.hasNext()) {
        			if(records[0][lastCol] == null) records[0][lastCol] = scan.next();
        			else records[0][lastCol] = records[0][lastCol] + " " + scan.next();
        		}
    	    	
    	    	scan.close();
    	    	break;
    		} 
    		
    		if(Character.isWhitespace(c) ) {
    	    	segment.append(' ');
    	    	continue;
    	    } 

    	    /** ending comma for a segment. add a ProductTitle if matched, assign to title array, clear builder, clear delimiter */
        	if( c == ',' && delim == ',' && 0 < segment.length() ) {  
        		Scanner scan = new Scanner(segment.toString());
        		while(scan.hasNext()) {
        			if(records[0][lastCol] == null) records[0][lastCol] = scan.next();
        			else records[0][lastCol] = records[0][lastCol] + " " + scan.next();
        		}
        		//System.out.println(records[0][lastCol]);
        		pt = OrderRecordIO.matchProductTitle(records[0][lastCol]);
        		if( pt != null) {
        			//System.out.println("product title: " + pt.getDescription() + " " + lastCol);
        			pt.setIndex(lastCol);
        			productTitles.add(pt);
        		}
        		segment = OrderRecordIO.clearStringBuilder(segment);
        		delim = ','; 
        		lastCol++;
    			scan.close();
        	/** quoted information within a comma separate segment. clear delim list and assign starting quotation character */
    		} else if ( c == '"' && delim == ',' ) { 
    			
    			delim = c;
    			
    		/** all characters between quotation marks are valid characters to be added to the segment */
    		} else if ( (delim == '"' && c != '"') || (delim == ',' && c != '"' && c != ',') ) { 
    		    
    			segment.append(c);
    			
    		/** the end of a segment between quotation marks. look for product matches, assign to title array, clear builder, 
    			clear delim list. skip the next character which is always a comma */
    		} else if ( delim == '"' && x == '"') { 
    			Scanner scan = new Scanner(segment.toString());
        		while(scan.hasNext()) {
        			if(records[0][lastCol] == null) records[0][lastCol] = scan.next();
        			else records[0][lastCol] = records[0][lastCol] + " " + scan.next();
        		}
        		//System.out.println(records[0][lastCol]);
    			pt = OrderRecordIO.matchProductTitle(records[0][lastCol]);
    			if(  pt != null ) {
    				//System.out.println("product title: " + pt.getDescription() + " " + lastCol);
    				pt.setIndex(lastCol);
    				productTitles.add(pt);
    			}
    			segment = OrderRecordIO.clearStringBuilder(segment);
    			delim = ',';
    			reader.read();
    			lastCol++;
    			scan.close();
    		}
        	
        	
    	}
    	reader.close();
    	return lastCol;
	}
	
	/**
	 * Searches the product catalog for a match. To be a match the title must contain the product family, generation,
	 * and the description. Returns an unordered ProductTitle, meaning the index of the returned ProductTitle within the
	 * list of order record titles has not been set, so its position within the list of order record titles
	 * is unknown. The index is set when matchProductTitle() returns control to readOrderTitles(). 
	 * 
	 * @param title the order record title
	 * @return pt the ProductTitle
	 * @throws IOException 
	 */
	private static ProductTitle matchProductTitle(String title) throws IOException {
		String gen = null; String desc = null; String fam = null;
		String noWSTitle = OrderRecordIO.removeWhiteSpace(title);
		noWSTitle = noWSTitle.toLowerCase();
		//System.out.println(noWSTitle);
		if(noWSTitle.contains(NioxCatalog.NIOX)) fam = NioxCatalog.NIOX;
		if(noWSTitle.contains(NioxCatalog.VERO)) gen = NioxCatalog.VERO;
		else if(noWSTitle.contains(NioxCatalog.MINO)) gen = NioxCatalog.MINO;
		
		for(int i = 0;i < NioxCatalog.DESCRIPTIONS.length;i++) {
	
			if(noWSTitle.contains(NioxCatalog.DESCRIPTIONS[i])) {
				if(desc == null) 
					desc = NioxCatalog.DESCRIPTIONS[i] + " ";
				else 
					desc = desc + NioxCatalog.DESCRIPTIONS[i] + " "; 
			}
	
		}
		ProductTitle pt = null;
		//System.out.println(fam + " " + gen + " " + desc);
		if(desc != null)
			pt = new ProductTitle(gen,fam,desc);
		return pt;
		
	}
	
	
	private static String removeWhiteSpace(String title) throws IOException {
		//System.out.println("NEW TITLE" + '\n');
		Scanner scan = new Scanner(title);
		String newTitle = "";
		while(scan.hasNext())
			if(newTitle.isEmpty()) newTitle = scan.next() + " ";
			else newTitle = newTitle + scan.next() + " ";
		//System.out.println(newTitle);
		//System.out.println(newTitle.length() + '\n');
		scan.close();
		return newTitle;
	}

}
