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
	/** Niox product family */
	public static final String NIOX = "niox";
	/** Mino product generation */
	public static final String MINO = "mino";
	/** Vero product generation */
	public static final String VERO = "vero";
	/** Product part numbers */
	public static final String [] NUMBERS = {"12-1200","12-1806-US","12-1810-US","12-1010","12-1220","12-1009","12-1008","12-1230","12-1250","03-4002-US","03-4000-US","09-1300","09-1015","09-1005","Part-Number"};
	/** Product names */
	public static final String [] NAMES = {"NIOX VERO device","NIOX VERO test kit 60","NIOX VERO test kit 100","NIOX VERO breathing handle","NIOX VERO Power Adapter","NIOX VERO Handle Cap","NIOX VERO Battery Lid","NIOX VERO Power Cord","NIOX VERO Battery","NIOX VERO hardcase","NIOX VERO Boveda Bag for Hardcase","NIOX VERO Training device",
			"NIOX MINO Unit Model 2009 US Aerocrine eNO System","NIOX MINO Test Kit 50","NIOX MINO Test Kit 100","NIOX MINO NO Scrubber 2009","NIOX MINO QC Plug Niox Mino Unit","NIOX MINO Power Supply NIOX MINO 2009"};
	public static final String [] DESCRIPTIONS = {"device","test kit 60","test kit 100","breathing handle","power adapter","handle cap","battery lid","power cord","battery","hardcase","boveda bag","training device",
			"unit model 2009","test kit 50","no scrubber","qc plug","power supply","no scrubber 2009","feno","expiration date","boveda bags"};
	public static final String DEFAULT_GEN = "generation";
	public static final String DEFAULT_FAM = "family";
	public static final String TRAINING_DEVICE = "training device";
	public static final String BREATHING_HANDLE = "breathing handle";
	public static final String NO_SCRUBBER = "no scrubber";
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
	 * Adds a product to the catalog if it does not already exist. 
	 * If the product exists on the catalog, the product is not added and the method
	 * returns false.
	 *  
	 * @param family the product family
	 * @param description the description of the product to add
	 * @param partNumber the partNumber of the product to add
	 * @param price the price of the product to add
	 * @return true if the product is added to the catalog
	 */
	public boolean addProductToCatalog(String fam, String description, String partNumber, double price) {
			
		Product c = new Component(fam, description, partNumber, price);
			
		for (int i = 0 ; i < catalog.size(); i++) {
			if( catalog.get(i).equals(c) )
				return false;
	
		}
		catalog.add(c);
		return true;
	}
	
	/**
	 * Adds a product to the catalog if it does not already exist. 
	 * If the product exists on the catalog, product is not added at the method returns false.
	 *  
	 * @param family the product family
	 * @param description the description of the product to add
	 * @param partNumber the partNumber of the product to add
	 * @param price the price of the product to add
	 * @return true if the product is added to the catalog
	 */
	public boolean addProductToCatalog(String fam, String description, String partNumber, String price) {
			
		Product c = new Component(fam, description, partNumber, price);
			
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
	
	/**
	 * Returns an array of product part-numbers.
	 * @return array the array of product part-numbers
	 * @throws IllegalArgumentException if product pn array is empty or if catalog is null 
	 */
	public String[] getProductPartNumbers() {
		String [] pn = null;
		try {
		  pn = new String[catalog.size()];
		  for(int i = 0; i < catalog.size(); i++ ) {
			  pn[i] = catalog.get(i).getPartNumber();
		  }
		} catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
		if(pn.length == 0 || pn == null) throw new IllegalArgumentException();
		return pn;
	}
	
	/**
	 * Returns an array of product names. A product name is constructed by concatenating the product
	 * family, generation, and description. For example, "Niox Mino test kit 100"
	 * 
	 * @return array the array of product names
	 * @throws IllegalArgumentException if the product name array is empty or if catalog is null
	 */
	public String[] getProductNames() {
		String[] names = null;
		Component part = null;
		try {
			names = new String[catalog.size()];
			for(int i = 0; i < catalog.size(); i++) {
				part = (Component) catalog.get(i);
				names[i] = part.getFamily() + " " + part.getGeneration() + " " + part.getDescription(); 
			}
		} catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
		if (names.length == 0 || names == null) throw new IllegalArgumentException();
		return names;
	}
	
	/**
	 * Returns the array of default product numbers
	 * @return the numbers
	 */
	public static String[] getDefaultProductPartNumbers() {
		return NUMBERS;
	}
	
	/**
	 * Returns the array of default product names.
	 * @return the names
	 */
	public static String[] getDefaultProductNames() {
		return NAMES;
	}
	
	/**
	 * Returns the array of default product descriptions
	 * @return the descriptions
	 */
	public static String[] getDescriptions() {
		return DESCRIPTIONS;
	}
	
	
}
