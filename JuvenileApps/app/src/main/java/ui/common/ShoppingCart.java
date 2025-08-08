package ui.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author  mchowdhury
 * description This class has four basic methods. 
 * When items are added to shopping cart, then first addToShoppingCart methods needs to be called and then 
 * removeFromAvailableShoppingItemList should be called. When items are removed from shoppingCart, then
 * addToAvailableShoppingItemList method should be called first and then removeFromShoppingCart method 
 * should be called. All those methods rerurns collection of items. Two of the methods returns current list 
 * in shopping cart and two other methods returns available list for shopping cart.
 * This is a utility class, therefore, it does not have a public or default constructor.
 */

public class ShoppingCart extends ReflectionHelper
{
	/**
	* @param itemName String (name of the item class such as FeatureResponseEvent)
	* @param itemIdName String (name of the item id such as getFeatureId)
	* @param objectsId String[]
	* @param currentShoppingItemList Collection
	* @param availableShoppingItemList Collection
	* @return collection add to shopping cart and returns the current items in the shopping cart
	*/
	public static Collection addToShoppingCart(String itemName, String itemIdName, String[] objectsId, Collection currentShoppingItemList, Collection availableShoppingItemList) throws InstantiationException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
	   Collection currentList = currentShoppingItemList;
	   Object item = Class.forName(itemName).newInstance();
              
	   //add current list to the shoppingCartMap
	   HashMap shoppingCart = addItemsIntoCartMap(item,itemIdName,currentList);
 
	   //add selected items to the shoppingCart
	   Collection availableList = availableShoppingItemList;
	   Iterator availableListIter = availableList.iterator();
	   String objectId = null;
	   while(availableListIter.hasNext()){
		  item = (Object) availableListIter.next();
		  objectId = getObjectId(item,itemIdName);
		  for(int i=0;i<objectsId.length;i++){
			 if(objectId.equals(objectsId[i])){
				if(!(shoppingCart.keySet().contains(objectId))){
				   shoppingCart.put(objectId,item); 
				   break;
				}
			 }
		  }
	   } 
	   return shoppingCart.values();
	}//end of the method

   /**
	* @param itemName String (name of the item class such as FeatureResponseEvent)
	* @param itemIdName String (name of the item id such as getFeatureId)
	* @param objectsId String[]
	* @param currentShoppingItemList Collection
	* @param availableShoppingItemList Collection
	* @return collection Remove from shopping cart and returns the current items in the shopping cart
	*/
	public static Collection removeFromShoppingCart(String itemName, String itemIdName, String[] objectsId, Collection currentShoppingItemList, Collection availableShoppingItemList) throws InstantiationException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
	   Collection currentList = currentShoppingItemList;
	   Object item = Class.forName(itemName).newInstance();
	   
	   //add current list to the shoppingCartMap
	   HashMap shoppingCart = addItemsIntoCartMap(item,itemIdName,currentList);

	   //remove selected items to the shoppingCart
	   Collection availableList = availableShoppingItemList;
	   Iterator availableListIter = availableList.iterator();
	   String objectId = null;
	   while(availableListIter.hasNext()){
		  item = (Object) availableListIter.next();
		  objectId = getObjectId(item,itemIdName);
		  for(int i=0;i<objectsId.length;i++){
			 if(objectId.equals(objectsId[i])){
				if(shoppingCart.keySet().contains(objectId)){
				   shoppingCart.remove(objectId); 
				   break;
				}
    		 }
		  }
	   } 
	   return shoppingCart.values();
	}//end of the method

	/**
	* @param itemName String (name of the item class such as FeatureResponseEvent)
	* @param itemIdName String (name of the item id such as getFeatureId)
	* @param currentShoppingItemList Collection
	* @param availableShoppingItemList Collection
	* @return collection Remove from available shopping cart which exists in current list
	*/
	public static Collection removeFromAvailableShoppingItemList(String itemName, String itemIdName, Collection currentShoppingItemList, Collection availableShoppingItemList) throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException{
	   if(currentShoppingItemList == null || currentShoppingItemList.isEmpty())
	   {
		  return availableShoppingItemList;
	   }
	   
	   Collection currentShoppingList = currentShoppingItemList;
	   Collection availableShoppingList = availableShoppingItemList;
	   Object availableItem = Class.forName(itemName).newInstance();
	   Object currentItem = Class.forName(itemName).newInstance();
	   
	   //add availableShoppingList to availableShoppingCart
	   HashMap availableShoppingCart = addItemsIntoCartMap(availableItem,itemIdName,availableShoppingList);

	   //remove current items from the available list
	   Iterator availableShoppingListIter = availableShoppingList.iterator();
	   String availableObjectId = null;
	   String currentObjectId = null; 
	   while(availableShoppingListIter.hasNext()){
		 availableItem = (Object) availableShoppingListIter.next();
		 availableObjectId = getObjectId(availableItem,itemIdName);
		 Iterator currentShoppingListIter = currentShoppingList.iterator();
		 while(currentShoppingListIter.hasNext()){
			currentItem = (Object) currentShoppingListIter.next();
			currentObjectId = getObjectId(currentItem,itemIdName);
			if(availableObjectId.equalsIgnoreCase(currentObjectId)){
				availableShoppingCart.remove(availableObjectId);
				break;
			}
		 }
	   }
	   return availableShoppingCart.values();
	}//end of the method
	
	/**
	* @param itemName String (name of the item class such as FeatureResponseEvent)
	* @param itemIdName String (name of the item id such as getFeatureId)
	* @param selectedFeaturesId String[] 	
	* @param currentShoppingItemList Collection
	* @param availableShoppingItemList Collection
	* @return collection add items to available shopping cart
	*/
	public static Collection addToAvailableShoppingItemList(String itemName, String itemIdName, String[] selectedFeaturesId, Collection currentShoppingItemList, Collection availableShoppingItemList) throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException{
		Collection currentShoppingList = currentShoppingItemList;
		Collection availableShoppingList = availableShoppingItemList;
		Object availableItem = Class.forName(itemName).newInstance();
		Object currentItem = Class.forName(itemName).newInstance();
	   
	   //add availableShoppingList to availableShoppingCart
	   HashMap availableShoppingCart = addItemsIntoCartMap(availableItem,itemIdName,availableShoppingList);

	   Iterator iterator = currentShoppingList.iterator();
	   String availableObjectId = null;
	   String currentObjectId = null;
	   while(iterator.hasNext()){
		  currentItem = (Object) iterator.next();
		  for(int i=0;i<selectedFeaturesId.length;i++){
			 currentObjectId = getObjectId(currentItem,itemIdName);
			 if(currentObjectId.equalsIgnoreCase(selectedFeaturesId[i])){
				if(!(availableShoppingCart.keySet().contains(selectedFeaturesId[i]))){
				   availableShoppingCart.put(currentObjectId,currentItem);
				   break;	
				}
			   }
			}
		 }
		 return availableShoppingCart.values();
	}//end of the method

	/**
	* @param item Object
	* @param itemIdName String
	* @param items Collection 
	* @return cartMap HashMap of items
	*/
	private static HashMap addItemsIntoCartMap(Object item, String itemIdName, Collection items) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
	   HashMap cartMap = new HashMap();
	   
	   if(items != null && !(items.isEmpty())){
		  Iterator itemsIter = items.iterator();
		  String objectId = null;
		  while(itemsIter.hasNext()){
			 item = (Object) itemsIter.next();
			 objectId = getObjectId(item,itemIdName);
			 cartMap.put(objectId,item); 
		  }
	   }
	   return cartMap;
	}
}