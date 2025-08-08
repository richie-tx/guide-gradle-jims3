package ui.security;

import java.util.Collection;

import ui.common.IShoppingCart;

/**
 * @author mchowdhury
 * Created on Jun 23, 2005
 * description This class implements the four basic methods declared by the interface. 
 * When items are added to shopping cart, then first addToShoppingCart methods needs to be called and then 
 * removeFromAvailableShoppingItemList should be called. When items are removed from shoppingCart, then
 * addToAvailableShoppingItemList method should be called first and then removeFromShoppingCart method 
 * should be called. All those methods rerurns collection of items. Two of the methods returns current list 
 * in shopping cart and two other methods returns available list for shopping cart.
  */

public class FeatureShoppingCartImpl implements IShoppingCart {
   
   /* public constructor
    */
	public FeatureShoppingCartImpl(){};
   /**
	* @param itemName String (name of the item class such as FeatureResponseEvent)
	* @param itemIdName String (name of the item id such as getFeatureId)
	* @param objectsId String[]
	* @param currentShoppingItemList Collection
	* @param availableShoppingItemList Collection
	* @return collection add to shopping cart and returns the current items in the shopping cart
	*/
	public Collection addToShoppingCart(String itemName, String itemIdName, String[] objectsId, Collection currentShoppingItemList, Collection availableShoppingItemList){
	   return FeatureShoppingCart.addToShoppingCart(itemName,itemIdName,objectsId,currentShoppingItemList,availableShoppingItemList);
	}
	
   /**
	* @param itemName String (name of the item class such as FeatureResponseEvent)
	* @param itemIdName String (name of the item id such as getFeatureId)
	* @param objectsId String[]
	* @param currentShoppingItemList Collection
	* @param availableShoppingItemList Collection
	* @return collection Remove from shopping cart and returns the current items in the shopping cart
	*/
	public Collection removeFromShoppingCart(String itemName, String itemIdName, String[] objectsId, Collection currentShoppingItemList, Collection availableShoppingItemList){
		return FeatureShoppingCart.removeFromShoppingCart(itemName,itemIdName,objectsId,currentShoppingItemList,availableShoppingItemList);
	}
	
	/**
	* @param itemName String (name of the item class such as FeatureResponseEvent)
	* @param itemIdName String (name of the item id such as getFeatureId)
	* @param currentShoppingItemList Collection
	* @param availableShoppingItemList Collection
	* @return collection Remove from available shopping cart which exists in current list
	*/
	public Collection removeFromAvailableShoppingItemList(String itemName, String itemIdName, Collection currentShoppingItemList, Collection availableShoppingItemList){
		return FeatureShoppingCart.removeFromAvailableShoppingItemList(itemName,itemIdName,currentShoppingItemList,availableShoppingItemList);
	}
	
	/**
	* @param itemName String (name of the item class such as FeatureResponseEvent)
	* @param itemIdName String (name of the item id such as getFeatureId)
	* @param selectedFeaturesId String[] 	
	* @param currentShoppingItemList Collection
	* @param availableShoppingItemList Collection
	* @return collection add items to available shopping cart
	*/
	public Collection addToAvailableShoppingItemList(String itemName, String itemIdName, String[] selectedFeaturesId, Collection currentShoppingItemList, Collection availableShoppingItemList){
		return FeatureShoppingCart.addToAvailableShoppingItemList(itemName,itemIdName,selectedFeaturesId,currentShoppingItemList,availableShoppingItemList);
	}
}
