package com.circa.mrv.grs_manager.util;

import java.util.AbstractList;


/**
 * The ArrayList class provides functionality for the creation of generic array list collections. It offers methods to 
 * add and remove elements at various indices, for getting an element at a specific location, and for getting the number
 * of elements stored in the list. 
 * @author Arthur Vargas
 * @param <E> Generic type parameter which allows the class to be used to store any object type.
 */
public class ArrayList<E> extends AbstractList<E> {

	private static final int INIT_SIZE = 10;
	private E[] list;
	private int size;
	
	/**
	 * Constructor which sets the list of elements to the default length of 10. Also intializes the size of the list (the
	 * number of items it contains) to 0. 
	 */
	@SuppressWarnings("unchecked") //Removes the warning about an unchecked cast
	public ArrayList() {
		list = (E[]) new Object[INIT_SIZE]; //because we can not construct an array of type E we need to cast array of obj as E.
		size = 0; // the number of elements in the array and also the correct index for adding to end of list.
	}
	
	/**
	 * Inserts the specified element at the specified position in this list (optional operation). Checks if the 
	 * specified index is out of bounds, if the passed element is null, and if the element is already in the list. If 
	 * any of these conditions are true, and exception is thrown. Additionally, if the size of the list is equal to the 
	 * length of the list, the list is made larger to accommodate the item to add. 
	 * @param index - index at which the specified element is to be inserted
	 * @param element - element to be inserted
	 * @throws IndexOutOfBoundsException If the passed index is less than 0 or larger than the size of the list.
	 * @throws NullPointerException If the passed element is null.
	 * @throws IllegalArgumentException If the passed element already exists on the list.
	 */
	@Override
	public void add(int index, E element) {

		 if (size == list.length){
			 growArray();
		 }
		 
		 if (index < 0 || index > size()) {
			 throw new IndexOutOfBoundsException();
		 }
		 if (element == null){
			 throw new NullPointerException();
		 }
		 for (int k = 0 ; k < size ; k++){
			 if (list[k].equals(element)){
				 throw new IllegalArgumentException();
			 }
		 }
			 
		 if (index == size){ //add to end of list
			 list[index] = element;
			 size++;
		 } else { // add to index between 0 and size
			for (int i = size ; i > index ; i--) {
				list[i] = list[i - 1];
			}
			size++;
			list[index] = element;
		 }  	 
	}
	
	/**
	 * Increases the length of the existing list by first creating a new list that is twice as long as the existing list. 
	 * Then it writes each element from the existing list into the new list. finally it sets the new list to the existing list.
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		int currentArrayLength = list.length;
		int newArrayLength = currentArrayLength * 2; //grow list by 2x
		E[] newList = (E[]) new Object[newArrayLength];
		
		for (int i = 0 ; i < list.length ; i++) {
			newList[i] = list[i];
		}
		list = newList;
	}
	
	/**
	 * Removes the element which is located at the passed index location in the list. If the index does not correspond to an element 
	 * location in the list, and IndexOutOfBoundsException is thrown.
	 * @param index The index of the element to return.
	 * @return removedItem The element which was removed at the passed index location.
	 */
	@Override
	public E remove(int index) {
		E removedItem;
		
		if (index < 0 || index >= size()) {
			 throw new IndexOutOfBoundsException();
		 }
		 removedItem = list[index];
		 if (index == size - 1){ //remove from end of list
			 list[index] = null;
			 size--;
		 } else { // remove from index between 0 and size - 1 
			for (int i = index ; i < size - 1 ; i++) {
				list[i] = list[i + 1];
			}
			list[size - 1] = null;
			size--;
		 }  
		return removedItem;
	}
	
	/**
	 * This method obtains the element which is stored at the passed index. If the index does not correspond to an element 
	 * location in the list, and IndexOutOfBoundsException is thrown.
	 * @param index The index of the element to return.
	 * @return element The element which is stored at the passed index.
	 */
	@Override
	public E get(int index) {

		 if (index < 0 || index >= size()) {
			 throw new IndexOutOfBoundsException();
		 }
		return list[index];
	}

	/**
	 * Overwrites the passed element to the specified index in the list. If the passed index  is less than 0 or greater
	 * than the size of the list, if the passed element is null, or if the passed element already exists on the list,
	 * exceptions are thrown. 
	 * @param index The location of the element to overwrite with the passed element.
	 * @param element The element to write into the list at the specified index.
	 * @return replacedItem the item that was replaced by the element. 
	 * @throws IndexOutOfBoundsException If the passed index is less than 0 or larger than the size of the list.
	 * @throws NullPointerException If the passed element is null.
	 * @throws IllegalArgumentException If the passed element already exists on the list.
	 */
	@Override
	public E set(int index, E element) {
		E replacedItem;
		 if (index < 0 || (index == 0 && size == 0) || index >= size()) {
			 throw new IndexOutOfBoundsException();
		 }
		 if (element == null){
			 throw new NullPointerException();
		 }
		 for (int k = 0 ; k < size ; k++){
			 if (list[k].equals(element)){
				 throw new IllegalArgumentException();
			 }
		 }
		 replacedItem = list[index];
		 list[index] = element;
		return replacedItem;
	}
	
	/**
	 * Returns the number of elements in this list. 
	 */
	@Override
	public int size() {
		return size;
	}
}
