package ui.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author  mchowdhury
 * description This class provide reflection helper methods. 
 */

public class ReflectionHelper
{
	
	   /**
	* @param item Object
	* @param itemIdName String 
	* @return Method
	*/
	public static Method getObjectIdMethod(Object item, String itemIdName) throws NoSuchMethodException{
       return item.getClass().getMethod(itemIdName, new Class[]{});
	}
	
	/**
	* @param item Object
	* @param itemIdName String
	* @return String objectId
	*/	
	public static String getObjectId(Object item, String itemIdName) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
	   Method method = getObjectIdMethod(item,itemIdName);
	   Object objectId = method.invoke(item, new Class[]{}); 
	   if(objectId == null){
	   	  return "";
	   }else{
	      return objectId.toString().trim(); 
	   }
	}
	
   /**
	* @param item Object
	* @param itemIdName String
	* @return String objectId
	*/	
	public static Object getObjectData(Object item, String itemIdName) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
	   Method method = getObjectIdMethod(item,itemIdName);
	   return (Object) method.invoke(item, new Class[]{}); 
	}
}