package ui.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;

/**
 *
 * @author  mchowdhury
 * description This class sorts the collection based on the objectName property. 
 * Item name or response event name, the name of getter for the item id, the name of the getter for item 
 * name and Collection of items to be sorted are required here.
 * This is a utility class, therefore, it does not have a public or default constructor.
 */

public class SortItemList extends ReflectionHelper{

	/**
	* @param responseEventName String example: messaging.reply.RoleResponseEvent
	* @param itemIdNameMethod String example: getRoleId
	* @param itemNameMethod String example: getRoleName
	* @param optionalItemNameMethodName String example: agencyName
	* @param items Collection example: roles
	* @return Collection of items example: roles
	*/	
	public static Collection getAscendingSortedData(String responseEventName,String itemIdNameMethod, String itemNameMethod, String optionalItemNameMethodName, Collection items) throws InstantiationException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
	   Object item = Class.forName(responseEventName).newInstance();
	   String objectId = null;
	   String primarySortBy = null;
	   String secondarySortBy = null;		
	   Iterator iter = items.iterator();
	   SortedMap map = new TreeMap();
	   while(iter.hasNext()){
		  item = (Object) iter.next();
		  objectId = getObjectId(item,itemIdNameMethod);
		  primarySortBy = getObjectId(item,itemNameMethod);
		  if(primarySortBy == null || primarySortBy.equals("")){
			primarySortBy = "ZZZZZ";	
		  }
		  if(optionalItemNameMethodName != null && !(optionalItemNameMethodName.equals(""))){
			 secondarySortBy = getObjectId(item,optionalItemNameMethodName);	 
		  }else{
			 secondarySortBy = "";
		  }
		  map.put(primarySortBy+secondarySortBy+objectId,item);
	   }
	   return map.values();
	}
	
	/**
	* @param responseEventName String example: messaging.reply.RoleResponseEvent
	* @param itemIdNameMethod String example: getRoleId
	* @param itemNameMethod String example: getRoleName
	* @param optionalItemNameMethodName String example: agencyName
	* @param items Collection example: roles
	* @param String dataType
	* @return Collection of items example: roles
	*/	
	public static Collection getDescendingSortedData(String responseEventName,String itemIdNameMethod, String itemNameMethod, String optionalItemNameMethodName, String dataType, Collection items) throws InstantiationException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		LinkedList stack = new LinkedList();
		Object item = Class.forName(responseEventName).newInstance();
		if(dataType != null && dataType.equalsIgnoreCase("String")){
			items = getAscendingSortedData(responseEventName,itemIdNameMethod,itemNameMethod,optionalItemNameMethodName,items);
		}else if(dataType != null && dataType.equalsIgnoreCase("Integer")){
			items = getAscendingSortedDataByInteger(responseEventName,itemIdNameMethod,itemNameMethod,optionalItemNameMethodName,items);
		}
		else if(dataType != null && dataType.equalsIgnoreCase("Date")){
			items = getAscendingSortedDataByDate(responseEventName,itemIdNameMethod,itemNameMethod,optionalItemNameMethodName,items);
		}
		Iterator it = items.iterator();
		while(it.hasNext()){
		   item = (Object) it.next();
		   stack.addFirst(item);
		}
		Collection list = new ArrayList();
		Object[] obj = stack.toArray();
		for(int i=0;i<obj.length;i++){
		   list.add(obj[i]);
		}
		return list;
	}
	
	/**
	* @param item Object
	* @param itemName String
	* @return String objectId
	*/	
	public static Collection getItemsList(Object item, String itemName) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
	   Method method = item.getClass().getMethod(itemName, new Class[]{});
	   Object obj = method.invoke(item, new Class[]{}); 
	   return (Collection) obj; 
	}
	
	/**
	* @param responseEventName String example: messaging.reply.RoleResponseEvent
	* @param itemIdNameMethod String example: getRoleId
	* @param itemNameMethod String example: getRoleName
	* @param optionalItemNameMethodName String example: agencyName
	* @param items Collection example: roles
	* @param inputHashCode int this is the hashcode of the user selected character
	* @return Collection of items example: roles
	*/ 
	public static Collection getSortedData(String responseEventName,String itemIdNameMethod, String itemNameMethod, String optionalItemNameMethodName, Collection items, int inputHashValue) throws InstantiationException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
	   Collection desiredList = new ArrayList();
	   Collection lessDesiredList = new ArrayList();
	   Object item = Class.forName(responseEventName).newInstance();
	   items = getAscendingSortedData(responseEventName,itemIdNameMethod,itemNameMethod,optionalItemNameMethodName,items);
	   Iterator it = items.iterator();
	   while(it.hasNext()){
		 item = (Object) it.next();
		 String objectName = getObjectId(item,itemNameMethod);
		 int hashVal = -1;
		 if(objectName != null && !(objectName.equals(""))){
			hashVal = ("" + objectName.charAt(0)).toUpperCase().hashCode();
		 }
		 if(hashVal >= inputHashValue){
			desiredList.add(item); 
		 }else{
			lessDesiredList.add(item); 
		 }
	   }
	   desiredList.addAll(lessDesiredList);
	   return desiredList;
	}
	
	/**
		* @param responseEventName String example: messaging.reply.RoleResponseEvent
		* @param itemIdNameMethod String example: getRoleId
		* @param itemNameMethod String example: getRoleName
		* @param optionalItemNameMethodName String example: agencyName
		* @param items Collection example: roles
		* @return Collection of items example: roles
		*/
	
		public static Collection getAscendingSortedDataByDate(String responseEventName,String itemIdNameMethod, String itemNameMethod, String optionalItemNameMethodName, Collection items) throws InstantiationException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		   Object item = Class.forName(responseEventName).newInstance();
		   Object primarySortBy = null;
		   Iterator iter = items.iterator();
		   SortedMap map = new TreeMap();
		   while(iter.hasNext()){
			  item = (Object) iter.next();
			  primarySortBy = getObjectData(item,itemNameMethod);
			  map.put(primarySortBy,item);
		   }
		   return map.values();
		}
		
	/**
		* @param responseEventName String example: messaging.reply.RoleResponseEvent
		* @param itemIdNameMethod String example: getRoleId
		* @param itemNameMethod String example: getRoleName
		* @param optionalItemNameMethodName String example: agencyName
		* @param items Collection example: roles
		* @return Collection of items example: roles
		*/

		public static Collection getAscendingSortedDataByInteger(String responseEventName,String itemIdNameMethod, String itemNameMethod, String optionalItemNameMethodName, Collection items) throws InstantiationException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		   Collection finalList = new ArrayList();
		   if(items == null || items.isEmpty()){
			   return finalList;
		   }
		   Object item = Class.forName(responseEventName).newInstance();
		   Object primarySortBy = null;
		   Vector vec = new Vector();
		   Iterator iter = items.iterator();
		   SortedMap map = new TreeMap();
		   while(iter.hasNext()){
			  item = (Object) iter.next();
			  primarySortBy = getObjectData(item,itemNameMethod);
			  vec.addElement(new Integer(primarySortBy.toString()));
			  map.put(primarySortBy,item);
		   }
		   Collections.sort(vec);
		   for (int i = 0; i < vec.size(); i++) {
			  String key =(vec.elementAt(i)).toString();
			  item = map.get(key);
			  finalList.add(item);
		  }
		  return finalList;
		}
}