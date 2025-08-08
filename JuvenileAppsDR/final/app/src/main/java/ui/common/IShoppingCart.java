package ui.common;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

/**
 * @author mchowdhury
 * Created on Jun 6, 2005
 * description This interface declares four basic methods to call for ShoppingCart. 
 * When items are added to shopping cart, then first addToShoppingCart methods needs to be called and then 
 * removeFromAvailableShoppingItemList should be called. When items are removed from shoppingCart, then
 * addToAvailableShoppingItemList method should be called first and then removeFromShoppingCart method 
 * should be called. All those methods rerurns collection of items. Two of the methods returns current list 
 * in shopping cart and two other methods returns available list for shopping cart.
  */

public interface IShoppingCart {
   /**
	* @param itemName String (name of the item class such as FeatureResponseEvent)
	* @param itemIdName String (name of the item id such as getFeatureId)
	* @param objectsId String[]
	* @param currentShoppingItemList Collection
	* @param availableShoppingItemList Collection
	* @return collection add to shopping cart and returns the current items in the shopping cart
	*/
	Collection addToShoppingCart(String itemName, String itemIdName, String[] objectsId, Collection currentShoppingItemList, Collection availableShoppingItemList) throws InstantiationException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException;
	
   /**
	* @param itemName String (name of the item class such as FeatureResponseEvent)
	* @param itemIdName String (name of the item id such as getFeatureId)
	* @param objectsId String[]
	* @param currentShoppingItemList Collection
	* @param availableShoppingItemList Collection
	* @return collection Remove from shopping cart and returns the current items in the shopping cart
	*/
	Collection removeFromShoppingCart(String itemName, String itemIdName, String[] objectsId, Collection currentShoppingItemList, Collection availableShoppingItemList) throws InstantiationException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	
	/**
	* @param itemName String (name of the item class such as FeatureResponseEvent)
	* @param itemIdName String (name of the item id such as getFeatureId)
	* @param currentShoppingItemList Collection
	* @param availableShoppingItemList Collection
	* @return collection Remove from available shopping cart which exists in current list
	*/
	Collection removeFromAvailableShoppingItemList(String itemName, String itemIdName, Collection currentShoppingItemList, Collection availableShoppingItemList) throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException;
	
	
	/**
	* @param itemName String (name of the item class such as FeatureResponseEvent)
	* @param itemIdName String (name of the item id such as getFeatureId)
	* @param selectedFeaturesId String[] 	
	* @param currentShoppingItemList Collection
	* @param availableShoppingItemList Collection
	* @return collection add items to available shopping cart
	*/
	Collection addToAvailableShoppingItemList(String itemName, String itemIdName, String[] selectedFeaturesId, Collection currentShoppingItemList, Collection availableShoppingItemList) throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException;
}
