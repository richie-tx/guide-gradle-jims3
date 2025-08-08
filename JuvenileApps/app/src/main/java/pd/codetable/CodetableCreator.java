//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\codetable\\transactions\\GetCodeTableRecordCommand.java

package pd.codetable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

import messaging.codetable.reply.CodetableDataResponseEvent;
import messaging.error.reply.CodetableErrorResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import pd.exception.CodetableException;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class CodetableCreator 
{
   
	  /**
	   * @roseuid 45B9521B01BF
	   */
	  public CodetableCreator() 
	  {
	  }
   
  	/**
	 * @param entityObj
	 * @param headerMap
	 * @param objectRelationalMap
	 * @param dataIter
	 * @param GetCodetableRecordEvent gEvent
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
 	 * @throws CodetableException
	 */
	protected void postData(Object entityObj, String mappingIdentifier, HashMap headerMap, Iterator dataIter) throws InstantiationException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, CodetableException {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	   
	    CodetableEntityMappingLoader loader = CodetableEntityMappingLoader.getInstance(mappingIdentifier);
    	HashMap entityMapping = loader.getEntityMapping(mappingIdentifier);

        while(dataIter.hasNext()){
	   	    entityObj = dataIter.next();
 	   	    Iterator mapIter = headerMap.keySet().iterator();
	   	    SortedMap valueMap = new TreeMap();
	   	    CodetableDataResponseEvent cResp = new CodetableDataResponseEvent();
	   	    while(mapIter.hasNext()){
	   	   	    String displayOrder = (String) mapIter.next();
	   	   	    String dbcolumnName = (String) headerMap.get(displayOrder);
	   	   	    String attributeName = (String) entityMapping.get(dbcolumnName);
	   	   	    if(attributeName != null && !attributeName.equals("")){
	   	   	        Object objectValue = this.getResults(attributeName,entityObj);
	   	   	        if(objectValue instanceof Timestamp){
	                    valueMap.put(new Integer(displayOrder), DateUtil.dateToString((Date) objectValue,"MM/dd/yyyy hh:mm:ss"));
	                }else if(objectValue instanceof Date){
	   	   	            valueMap.put(new Integer(displayOrder), DateUtil.dateToString((Date) objectValue,"MM/dd/yyyy"));
	   	   	        }
	   	   	        else{
	   	   	            valueMap.put(new Integer(displayOrder), (objectValue == null)?"":objectValue.toString());
	   	   	        }

	   	   	        if("1".equals(displayOrder)){
		   	   	    	cResp.setSortedValue(objectValue);
		   	   	    }
	   	   	    }
	   	    }
	   	    
	   	    cResp.setValueMap(valueMap);
	   	    cResp.setCodetableDataId("" + this.getResults(entityObj,PDCodeTableConstants.GETOID));
	   	    dispatch.postEvent(cResp);
	    }
	}

	/**
	 * @param attributeName
	 * @param entityObj
	 * @return
	 * @throws CodetableException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	protected Object getResults(String attributeName, Object entityObj) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
   	   try{
            return getResults(entityObj, getMethodNameFromAttribute(attributeName,PDCodeTableConstants.GET));
   	   }catch(NoSuchMethodException e){
   	        return getResults(entityObj, getMethodNameFromAttribute(attributeName,PDCodeTableConstants.IS));
   	   }
	}
	
	/**
	 * @param obj
	 * @param methodName
	 * @return
	 */
     protected Object getResults(Object obj, String methodName) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		try{
			Method method = obj.getClass().getMethod(methodName, new Class[]{});
			return method.invoke(obj, new Object[]{});
		}catch(NoSuchMethodException e){
			throw new NoSuchMethodException(new StringBuffer("Method ").append(methodName).append(" not found").toString());
		}
	}
     
     /**
 	 * @param obj
 	 * @param methodName
 	 * @param attributeName
 	 * @param attributeValue
     * @return obj
 	 */
     protected Object getResults(Object obj, String methodName, String attributeName, String attributeValue) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
     	try{
			Method method = obj.getClass().getMethod(methodName, new Class[]{String.class, String.class});
 	        return method.invoke(obj, new Object[]{attributeName,attributeValue});
     	}catch(NoSuchMethodException e){
     		throw new NoSuchMethodException(new StringBuffer("Method ").append(methodName).append("(String attributeName, String attributeValue) not found").toString());
	    }
 	}
     
	 /**
 	 * @param argumentType
 	 * @param methodName
 	 * @param data
 	 * @param obj
 	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException 
 	 */
 	protected Object getResults(String argumentType, String methodName, Object data, Object obj) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
 	      Method method = null;
 	      if(PDCodeTableConstants.STRING.equalsIgnoreCase(argumentType)){
 	      	  method = obj.getClass().getMethod(methodName, new Class[]{String.class});
 	      }else if(PDCodeTableConstants.DOUBLE.equalsIgnoreCase(argumentType)){
 	  	  	  method = obj.getClass().getMethod(methodName, new Class[]{Double.class});
 	  	  	  if(data instanceof String){
	  	  	      data = new Double((String) data);
	  	  	  }
 	  	  }else if(PDCodeTableConstants.FLOAT.equals(argumentType)){
 	  	  	  method = obj.getClass().getMethod(methodName, new Class[]{Float.class});
 	  	  	  if(data instanceof String){
	  	  	      data = new Float((String) data);
	  	  	  }
 	  	  }else if(PDCodeTableConstants.FLOAT.toLowerCase().equals(argumentType)){
	  	  	  method = obj.getClass().getMethod(methodName, new Class[]{Float.TYPE});
 	  	  	  if(data instanceof String){
	  	  	      data = new Float((String) data);
	  	  	  }
 	  	  }else if(PDCodeTableConstants.INTEGER.equals(argumentType)){
 	  	  	  method = obj.getClass().getMethod(methodName, new Class[]{Integer.class});
 	  	  	  if(data instanceof String){
	  	  	      data = new Integer((String) data);
	  	  	  }
 	  	  }else if(PDCodeTableConstants.INT.equals(argumentType)){
	  	  	  method = obj.getClass().getMethod(methodName, new Class[]{Integer.TYPE});
 	  	  	  if(data instanceof String){
	  	  	      data = new Integer((String) data);
	  	  	  }
 	  	  }else if(PDCodeTableConstants.LONG.equals(argumentType)){
	  	  	  method = obj.getClass().getMethod(methodName, new Class[]{Long.class});
	  	  	  if(data instanceof String){
	  	  	      data = new Long((String) data);
	  	  	  }
 	  	  }else if(PDCodeTableConstants.LONG.toLowerCase().equals(argumentType)){
	  	  	  method = obj.getClass().getMethod(methodName, new Class[]{Long.TYPE});
	  	  	  if(data instanceof String){
	  	  	      data = new Long((String) data);
	  	  	  }
 	  	  }else if(PDCodeTableConstants.BOOLEAN.equals(argumentType)){
	  	  	  method = obj.getClass().getMethod(methodName, new Class[]{Boolean.class});
	  	  	  if(data instanceof String){
	  	  	      data = new Boolean((String) data);
	  	  	  }
 	  	  }else if(PDCodeTableConstants.BOOLEAN.toLowerCase().equals(argumentType)){
	  	  	  method = obj.getClass().getMethod(methodName, new Class[]{Boolean.TYPE});
	  	  	  if(data instanceof String){
	  	  	      data = new Boolean((String) data);
	  	  	  }
 	  	  }else if(PDCodeTableConstants.DATE.equalsIgnoreCase(argumentType)){
 	  	  	  method = obj.getClass().getMethod(methodName, new Class[]{Date.class});
 	  	  	  if(data instanceof String){
 	  	  	  	   data = DateUtil.stringToDate((String) data, "MM/dd/yyyy");
 	  	  	  }
 	  	  }else if(PDCodeTableConstants.TIMESTAMP.equals(argumentType)){
	  	  	  method = obj.getClass().getMethod(methodName, new Class[]{Timestamp.class});
 	  	  	  if(data instanceof String){
	  	  	  	   data = DateUtil.stringToDate((String) data, "MM/dd/yyyy hh:mm:ss");
	  	  	  }
 	  	  }else if(PDCodeTableConstants.OBJECT.equals(argumentType)){
	  	  	  try{
	  	  		  method = obj.getClass().getMethod(methodName, new Class[]{Object.class});
	  	  	  }catch(Exception e){
	  	  		  method = obj.getClass().getMethod(methodName, new Class[]{String.class});
	  	  	  }
 	  	  }else{
 	  	      method = obj.getClass().getMethod(methodName, new Class[]{String.class});
 	  	  }
  	      return  method.invoke(obj, new Object[]{data}); 
	}
 	
 	/**
 	 * @param attributeName
 	 * @param methodPrefix
 	 * @return String methodName
 	 */
 	protected String getMethodNameFromAttribute(String attributeName, String methodPrefix){
 		StringBuffer methodName = new StringBuffer(methodPrefix);
 		methodName.append(attributeName.substring(0,1).toUpperCase());
 		methodName.append(attributeName.substring(1,attributeName.length()));	
 		return methodName.toString();
 	}
 	
 	/**
 	 * @param attributeName
 	 * @param methodPrefix
 	 * @return String methodName
 	 */
 	protected void post(IEvent event){
 		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
 		dispatch.postEvent(event);
 	}
 	
 	/**
 	 * @param attributeName
 	 * @param methodPrefix
 	 * @return String methodName
 	 */
 	protected void post(String message){
 		ReturnException re = new ReturnException(message);
 		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
 		dispatch.postEvent(re);
 	}
 	
 	protected void postError(String message){
 		ErrorResponseEvent errorResp = new ErrorResponseEvent();
		errorResp.setMessage(message);			
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(errorResp);
 	}
 	
	/*
	 * @param validationData
     * @param errorKey 
     * @param propertyName
	 */
	  protected void sendNotification(String validationData, String errorKey, String propertyName){
	      CodetableErrorResponseEvent resp = new CodetableErrorResponseEvent();
    	  resp.setErrorKey(errorKey);
    	  StringBuffer buff = new StringBuffer("'");
    	  buff.append(propertyName);
    	  buff.append("=");
    	  buff.append(validationData);
    	  buff.append("'");
    	  resp.setParameterValue(buff.toString());
    	  post(resp);
	  }
}
