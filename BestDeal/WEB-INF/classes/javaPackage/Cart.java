package javaPackage;

import java.util.*;


public class Cart {
	public Map<String,Double> map;
	public Cart()
	{
		 map = new HashMap<String,Double>();
	}
	public void addToCart(String productName, double ProductPrice)
	{
		map.put(productName, ProductPrice);
		
	}
	public void removeFromCart(String productName)
	{
		map.remove(productName);
		
	}
	public Map<String,Double> getItems()
	{
		return map;
	}

	// public void deleteFromCart(String itemId){
 //        cartItems.remove(itemId);
 //    }
	
}