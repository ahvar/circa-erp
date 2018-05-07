/**
 * 
 */
package com.circa.mrv.grs_manager.io;

/**
 * Represents a order record title that is a product name and locates that product name within the list of 
 * order record titles.
 * @author Arthur Vargas
 */
public class ProductTitle {
	/** Product Generation */
	private String gen;
	/** Product Family */
	private String fam;
	/** Product description */
	private String description;
	/** Title index */
	private int index;
	
	/**
	 * Constructs ProductTitle with the product's generation, family, and description.
	 * 
	 * @param generation the product generation
	 * @param family the product family
	 * @param description the product description
	 */
	public ProductTitle(String generation, String family, String description) {
		setGen(generation);
		setFam(family);
		setDescription(description);
	}
	
	/**
	 * Constructs ProductTitle with the product's generation, family, description, and its index within a list of column
	 * titles.
	 * 
	 * @param generation the product generation
	 * @param family the product family
	 * @param description the product description
	 * @param i the index of the title in a list of order record titles
	 */
	public ProductTitle(String generation, String family, String description, int i) {
		this(generation,family,description);
		setIndex(i);
	}


	/**
	 * @return the gen
	 */
	public String getGen() {
		return gen;
	}

	/**
	 * @param gen the gen to set
	 */
	public void setGen(String gen) {
		this.gen = gen;
	}

	/**
	 * @return the fam
	 */
	public String getFam() {
		return fam;
	}

	/**
	 * @param fam the fam to set
	 */
	public void setFam(String fam) {
		this.fam = fam;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
}
