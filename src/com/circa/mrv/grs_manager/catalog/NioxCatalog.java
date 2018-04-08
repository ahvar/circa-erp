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

import edu.ncsu.csc216.collections.list.SortedList;

/**
 * The NioxCatalog is a list of Products. All NIOX products extend the abstract class Product.
 *    
 * @author Arthur Vargas 
 */
public class NioxCatalog {
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
	public void loadProductsFromFile(String fileName) {
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
	public boolean removeProductFromCatalog(String name, String desc, String partNumber){
		
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getDescription().equals(desc) && 
					catalog.get(i).getPartNumber().equals(partNumber)) {
				catalog.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Removes a product from the catalog if the product's name and part number match the passed values.
	 * @param name the name of the name of the product to remove
	 * @param part number the part number of the product to remove
	 * @return True if the course was removed 
	 */
	public boolean removeProductFromCatalog(String name, String partNumber){
		
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getDescription().equals(partNumber) && 
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
	public Product getProductFromCatalog(String name, String desc, String pn){	
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getDescription().equals(desc) &&
					catalog.get(i).getPartNumber().equals(pn))
				return new Component(catalog.get(i).getName(), catalog.get(i).getDescription(), catalog.get(i).getPartNumber());
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
	public Product getProductFromCatalog(String name, String pn){	
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getPartNumber().equals(pn) &&
					catalog.get(i).getPartNumber().equals(pn))
				return new Component(catalog.get(i).getName(), catalog.get(i).getDescription(), catalog.get(i).getPartNumber());
		}
		return null;
	}
	
	
	
	/**
	 * Gets the full product catalog which is stored as a 2D array. The rows of the array are individual products and the 
	 * columns of the array are the description and part number 
	 * @return nioxCatalog a 2D String array representing the niox catalog. 
	 */
	public String[][] getNioxCatalog() {
		String [][] nioxCatalog = new String[catalog.size()][5];
		for (int i = 0; i < catalog.size(); i++) {
			Product c = catalog.get(i);
			nioxCatalog[i][0] = c.getPartNumber();
			nioxCatalog[i][2] = c.getName();
			nioxCatalog[i][3] = c.getDescription();	
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
