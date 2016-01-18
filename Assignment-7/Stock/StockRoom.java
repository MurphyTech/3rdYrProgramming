/**
 * David Murphy
 * d.murphy53@nuigalway.ie
 * 12493252
 */
package stock;

import java.util.HashMap;
//import java.util.ArrayList;

public class StockRoom{
	//ref to stock array list
	private HashMap<String, Integer> stock;
	
	public StockRoom(){
		stock = new HashMap<String, Integer>();
	}
	/**
	 * Add item to stock
	 */
	public synchronized void addToStock(String item){
		//if item name not in list, add it and make its stock value 1
		if (!stock.containsKey(item)) stock.put(item,  1);
		//if item name is on the list, increment it value by 1
		else stock.put(item, stock.get(item) + 1);
	}
	/**
	 * Remove item from list
	 */
	public synchronized void remove(String item){
		//Decrement value by 1
		Integer value = stock.get(item) - 1;
		//If value is now zero, remove stock entry
		if (value == 0) stock.remove(item);
		//otherwise, update with new value
		else stock.put(item, value);
	}
	/**
	 * Check Stock
	 */
	public synchronized int checkStock(String item){
		//get the value of stock
		Integer value = stock.get(item);
		//return appropriate value
		return value == null ? 0 : value;
	}
	/**
	 * isEmpty method would be useful
	 */
	public synchronized boolean isEmpty(){
		return this.stock.isEmpty();
	}
}


