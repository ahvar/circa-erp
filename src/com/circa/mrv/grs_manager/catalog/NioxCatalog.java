/**
 * 
 */
package com.circa.mrv.grs_manager.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.circa.mrv.grs_manager.io.ProductRecordIO;
import com.circa.mrv.grs_manager.niox.Component;

import com.circa.mrv.grs_manager.niox.Product;
import com.circa.mrv.grs_manager.util.LinkedListRecursive;

/**
 * The NioxCatalog is a list of Products. 
 *    
 * @author Arthur Vargas 
 */
public class NioxCatalog {
	/** Product part numbers */
	public static final String [] NUMBERS = {"12-1200","12-1806-US","12-1810-US","12-1010","12-1220","12-1009","12-1008","12-1230","12-1250","03-4002-US","03-4000-US","09-1300","09-1015","09-1005","Part-Number"};
	/** Product names */
	public static final String [] NAMES = {"NIOX VERO device","NIOX VERO test kit 60","NIOX VERO test kit 100","NIOX VERO breathing handle","NIOX VERO Power Adapter","NIOX VERO Handle Cap","NIOX VERO Battery Lid","NIOX VERO Power Cord","NIOX VERO Battery","NIOX VERO hardcase","NIOX VERO Boveda Bag for Hardcase","NIOX VERO Training device",
			"NIOX MINO Unit Model 2009 US Aerocrine eNO System","NIOX MINO Test Kit 50","NIOX MINO Test Kit 100","NIOX MINO NO Scrubber 2009","NIOX MINO QC Plug Niox Mino Unit","NIOX MINO Power Supply NIOX MINO 2009"};
	public static final String [] DESCRIPTIONS = {"device","test kit 60","test kit 100","breathing handle","power adapter","handle cap","batter lid","power cord","battery","hardcase","boveda bag","training device",
			"unit model 2009","test kit 50","no scrubber","qc plug","power supply","no scrubber 2009"};
	/** The catalog for products */
	private LinkedListRecursive<Product> catalog;
	
	/**
	 * Null Constructor which calls the newNioxCatalog method
	 */
	public NioxCatalog() {
		newNioxCatalog();
	}
	/**
	 * Creates an empty product catalog
	 */
	public void newNioxCatalog() {
		catalog = new LinkedListRecursive<Product>();
	}
	/**
	 * Loads an input file of products into the catalog. If the file is unable to be found a IllegalArgumentException is thrown.
	 * @throws IllegalArgumentException if the file is unable to be found.
	 * @param fileName the filename for the file of products to be read in to create the catalog. 
	 */
	public void loadProductsFromFile(String fileName) throws IllegalArgumentException {
		try {
			catalog = ProductRecordIO.readProductRecords(fileName);
		
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Adds a product to the catalog if the name and part number of the product to be added does not match one of the 
	 * products in the catalog. If the product exists on the catalog, the new course is not added.
	 *  
	 * @param name the name of the product to add
	 * @param description the description of the product to add
	 * @param partNumber the partNumber of the product to add
	 * @param price the price of the product to add
	 * @return true if the product is added to the catalog
	 */
	public boolean addProductToCatalog(String name, String description, String partNumber, double price) {
			
		Product c = new Component(name, description, partNumber, price);
			
		for (int i = 0 ; i < catalog.size(); i++) {
			if( catalog.get(i).equals(c) )
				return false;
	
		}
		catalog.add(c);
		return true;
	}
	
	/**
	 * Adds a product to the catalog if it is not already listed.
	 * If the product exists on the catalog, the new course is not added and the method returns false.
	 *  
	 * @param product the product to add to the catalog
	 * @return true if the product is added to the catalog
	 */
	public boolean addProductToCatalog(Product product) {
			
		for (int i = 0 ; i < catalog.size(); i++) {
			if( catalog.get(i).equals(product) )
				return false;
	
		}
		catalog.add(product);
		return true;
	}
	
	
	/**
	 * Removes a product from the catalog if the product's name, description, and part number match the passed values.
	 * @param name the name of the name of the product to remove
	 * @param desc the description of the product to remove
	 * @return True if the course was removed 
	 */
	public boolean removeProductFromCatalog(String family, String desc, String partNumber){
		
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getFamily().equals(family) && catalog.get(i).getDescription().equals(desc) && 
					catalog.get(i).getPartNumber().equals(partNumber)) {
				catalog.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the number of products contained in the catalog
	 * @return int the number of products in the catalog
	 */
	public int allProducts() {
		return catalog.size();
	}
	
	/**
	 * Removes a product from the catalog if the product's name and part number match the passed values.
	 * @param name the name of the name of the product to remove
	 * @param part number the part number of the product to remove
	 * @return True if the course was removed 
	 */
	public boolean removeProductFromCatalog(String family, String partNumber){
		
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getFamily().equals(family) && catalog.get(i).getDescription().equals(partNumber) && 
					catalog.get(i).getPartNumber().equals(partNumber)) {
				catalog.remove(i);
				return true;
			}
		}
		return false;
	}
	/**
	 * Gets a product from the catalog that is specified by the passed name, description, and part number. 
	 * If the passed values match a product on the catalog, the product is returned. 
	 * 
	 * @param name the name of the product to get
	 * @param desc the section of the product to get
	 * @param pn the part number of the product to get
	 * @return c the product that matches the passed name and section. Returns null if no match existed.
	 */
	public Product getProductFromCatalog(String family, String desc, String pn){	
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getFamily().equals(family) && catalog.get(i).getDescription().equals(desc) &&
					catalog.get(i).getPartNumber().equals(pn))
				return new Component(catalog.get(i).getFamily(), catalog.get(i).getDescription(), catalog.get(i).getPartNumber());
		}
		return null;
	}
	
	/**
	 * Gets a product from the catalog that is specified by the passed name and part number. 
	 * If the passed values match a product on the catalog, the product is returned. 
	 * 
	 * @param name the name of the product to get
	 * @param pn the part number of the product to get
	 * @return c the product that matches the passed name and section. Returns null if no match existed.
	 */
	public Product getProductFromCatalog(String family, String pn){	
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getFamily().equals(family) && catalog.get(i).getPartNumber().equals(pn) )
				return new Component(catalog.get(i).getFamily(), catalog.get(i).getDescription(), catalog.get(i).getPartNumber());
		}
		return null;
	}
	
	/**
	 * Searches the catalog for the product whose famiyl, generation, and description match the parameters.
	 * @param family the product famiy
	 * @param generation the product generation
	 * @param description the product description
	 * @return true if the catalog contains the product
	 */
	public boolean catalogContainsProduct(String family, String generation, String description) {
		
		return true;
	}
	
	/**
	 * Gets the full product catalog which is stored as a 2D array. The rows of the array are individual products and the 
	 * columns of the array are the description and part number 
	 * @return nioxCatalog a 2D String array representing the niox catalog. 
	 */
	public String[][] getNioxCatalog() {
		String [][] nioxCatalog = new String[catalog.size()][6];
		for (int i = 0; i < catalog.size(); i++) {
			Component c = (Component) catalog.get(i);
			nioxCatalog[i][0] = c.getPartNumber();
			nioxCatalog[i][1] = c.getFamily();
			nioxCatalog[i][2] = c.getGeneration();
			nioxCatalog[i][3] = c.getDescription();
			nioxCatalog[i][4] = Double.toString(c.getPrice());
		}
		
		return nioxCatalog;
	}
	
	/**
	 * Saves the product catalog to the passed file name. Passes the filename and the catalog to the writeProducteRecords method
	 * in the ProductRecordIO class.
	 * @param fileName the name of the file to save the product records to. 
	 * @throws IllegalArgumentException if the writeProductRecords method is unable to write to the file.
	 */
	public void saveProductCatalog(String fileName) {
		try {
			ProductRecordIO.writeProductRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}
}
