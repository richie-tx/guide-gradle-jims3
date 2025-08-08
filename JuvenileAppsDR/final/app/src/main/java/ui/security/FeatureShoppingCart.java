//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\FeatureShoppingCart.java

package ui.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import messaging.security.reply.FeaturesResponseEvent;

/**
 * @author mchowdhury
 *
 * description This class has four basic methods. 
 * When items are added to shopping cart, then first addToShoppingCart methods needs to be called and then 
 * removeFromAvailableShoppingItemList should be called. When items are removed from shoppingCart, then
 * addToAvailableShoppingItemList method should be called first and then removeFromShoppingCart method 
 * should be called. All those methods rerurns collection of items. Two of the methods returns current list 
 * in shopping cart and two other methods returns available list for shopping cart.
 * This is a utility class, therefore, it does not have a public or default constructor.
 * */

public class FeatureShoppingCart
{

	/**
	* @param itemName String (name of the item class such as FeatureResponseEvent)
	* @param itemIdName String (name of the item id such as getFeatureId)
	* @param objectsId String[]
	* @param currentShoppingItemList Collection
	* @param availableShoppingItemList Collection
	* @return collection add to shopping cart and returns the current items in the shopping cart
	*/
	public static Collection addToShoppingCart(String itemName, String itemIdName, String[] objectsId, Collection currentShoppingItemList, Collection availableShoppingItemList){
	   Collection currentList = currentShoppingItemList;
	          
	   //add current list to the shoppingCartMap
	   HashMap shoppingCart = addItemsIntoCartMap(currentList);
 
	   //add selected items to the shoppingCart
	   Collection availableList = availableShoppingItemList;
	   Iterator availableListIter = availableList.iterator();
	   while(availableListIter.hasNext()){
    	  FeaturesResponseEvent event = (FeaturesResponseEvent) availableListIter.next();
		  Collection childFeatures = event.getChildFeatures();
  		  for(int i=0;i<objectsId.length;i++){
			 if(event.getFeatureId().equals(objectsId[i])){
				if(!shoppingCart.containsKey(event.getFeatureId()) && (event.getParentId() != null || (event.getParentId() == null && event.getChildFeatures() == null))){
				   shoppingCart.put(event.getFeatureId(),event); 
				}
			 }else if(childFeatures != null && childFeatures.size() > 0){
				Iterator iter = childFeatures.iterator();
				while(iter.hasNext()){
				   FeaturesResponseEvent childEvent = (FeaturesResponseEvent) iter.next();
				   if(childEvent.getFeatureId().equals(objectsId[i])){
					  if(!shoppingCart.keySet().contains(childEvent.getFeatureId())){
						 shoppingCart.put(childEvent.getFeatureId(),childEvent); 
						 break;
					  }
				   }
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
	public static Collection removeFromShoppingCart(String itemName, String itemIdName, String[] objectsId, Collection currentShoppingItemList, Collection availableShoppingItemList){
	   Collection currentList = currentShoppingItemList;
	   
	   //add current list to the shoppingCartMap
	   HashMap shoppingCart = addItemsIntoCartMap(currentList);
      
	   for(int i=0;i<objectsId.length;i++){
		  if(shoppingCart.containsKey(objectsId[i])){    
			 shoppingCart.remove(objectsId[i]); 
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
	public static Collection removeFromAvailableShoppingItemList(String itemName, String itemIdName, Collection currentShoppingItemList, Collection availableShoppingItemList){
	   if(currentShoppingItemList == null || currentShoppingItemList.isEmpty())
		  return availableShoppingItemList;

	   Collection currentShoppingList = currentShoppingItemList;
	   Collection availableShoppingList = availableShoppingItemList;
	   
	   //add availableShoppingList to availableShoppingCart
	   HashMap availableShoppingCart = addItemsIntoCartMap(availableShoppingList);

	   Iterator currentShoppingListIter = currentShoppingList.iterator();
	   while(currentShoppingListIter.hasNext()){
		  FeaturesResponseEvent currentItem = (FeaturesResponseEvent) currentShoppingListIter.next();
		  Iterator availableListIter = availableShoppingCart.values().iterator();
		  while(availableListIter.hasNext()){
			 FeaturesResponseEvent availableEvent = (FeaturesResponseEvent) availableListIter.next();
			 Collection availableChildFeatures = availableEvent.getChildFeatures();
			 boolean isMatched = false;
 	     
			 if(availableShoppingCart.containsKey(currentItem.getFeatureId())){
				availableShoppingCart.remove(currentItem.getFeatureId());
				break;
			 }else if(availableChildFeatures != null){
				Iterator iter = availableChildFeatures.iterator();
				while(iter.hasNext()){
				   FeaturesResponseEvent availableChildEvent = (FeaturesResponseEvent) iter.next();
				   if(availableChildEvent.getFeatureId().equals(currentItem.getFeatureId())){
					  availableShoppingCart.remove(availableEvent.getFeatureId());
					  availableChildFeatures.remove(availableChildEvent);
					  availableEvent.setChildFeatures(availableChildFeatures);
					  availableShoppingCart.put(availableEvent.getFeatureId(),availableEvent);
					  isMatched = true;
					  break;
				   }
				}
				if(isMatched){
				   break;
				}
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
	public static Collection addToAvailableShoppingItemList(String itemName, String itemIdName, String[] selectedFeaturesId, Collection currentShoppingItemList, Collection availableShoppingItemList){
		Collection currentShoppingList = currentShoppingItemList;
		Collection availableShoppingList = availableShoppingItemList;
 	   
	    //add availableShoppingList to availableShoppingCart
	    HashMap availableShoppingCart = addItemsIntoCartMap(availableShoppingList);

	    Iterator iter = currentShoppingList.iterator();
	    while(iter.hasNext()){
		   FeaturesResponseEvent currentItem = (FeaturesResponseEvent) iter.next();
		   for(int i=0;i<selectedFeaturesId.length;i++){
			  if(currentItem.getFeatureId().equalsIgnoreCase(selectedFeaturesId[i])){
				 if(availableShoppingCart.containsKey(currentItem.getParentId())){
				     FeaturesResponseEvent event = (FeaturesResponseEvent) availableShoppingCart.get(currentItem.getParentId());
				     availableShoppingCart.remove(event.getFeatureId());
				     event.getChildFeatures().add(currentItem);
				     event.setChildFeatures(event.getChildFeatures());
				     availableShoppingCart.put(event.getFeatureId(),event); 
				     break;  	
				 }else if(!(availableShoppingCart.containsKey(selectedFeaturesId[i]))){
				     availableShoppingCart.put(currentItem.getFeatureId(),currentItem);
				     break;	
				 }   
			  }
		   }
	    }
	    return availableShoppingCart.values();
	}//end of the method

	/**
	* @param items Collection 
	* @return cartMap HashMap of items
	*/
	private static HashMap addItemsIntoCartMap(Collection items){
	   HashMap cartMap = new HashMap();
	   
	   if(items != null && !(items.isEmpty())){
		  Iterator itemsIter = items.iterator();
		  while(itemsIter.hasNext()){
			 FeaturesResponseEvent item = (FeaturesResponseEvent) itemsIter.next();
			 cartMap.put(item.getFeatureId(),item); 
		  }
	   }
	   return cartMap;
	}
}
