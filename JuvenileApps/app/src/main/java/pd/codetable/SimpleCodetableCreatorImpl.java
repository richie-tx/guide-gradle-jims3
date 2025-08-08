//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\codetable\\transactions\\GetCodeTableRecordCommand.java

package pd.codetable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.codetable.GetCodetableRecordEvent;
import messaging.codetable.UpdateCodetableDataEvent;
import messaging.codetable.reply.CodetableAttributeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.ResponseLocatorConstants;
import pd.codetable.CodeTable;
import pd.codetable.CodetableEntityMappingLoader;
import pd.codetable.CodetableReg;
import pd.codetable.CodetableRegAttribute;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.exception.CodetableException;
import pd.exception.ReflectionException;
import pd.security.PDSecurityHelper;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class SimpleCodetableCreatorImpl extends CodetableCreator implements ICodetableCreator
{
   
	  /**
	   * @roseuid 45B9521B01BF
	   */
	  public SimpleCodetableCreatorImpl() 
	  {
	  }
   
	 /**
	 * @param event
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws CodetableException
	 * @roseuid 45B94F5000C2
    */
    public void retrieve(IEvent event) throws SecurityException, IllegalArgumentException, InstantiationException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, CodetableException 
    {
    	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	    GetCodetableRecordEvent gEvent = (GetCodetableRecordEvent) event;
	    ResponseContextFactory respFac = new ResponseContextFactory();
	    ResponseCreator aCreator =  null;
	    try {
			aCreator = (ResponseCreator) respFac.lookup(ResponseLocatorConstants.CODETABLEATTRIBUTE_RESPONSE_LOCATOR);
	    } catch (ReflectionException e) {
			e.printStackTrace();
	    }
	    
	    CodetableReg reg = CodetableReg.find(gEvent.getCodetableRegId());

	    if(reg != null){
	   	    Iterator regAttrIter = reg.getCodetableRegAttributes().iterator();
	   	    HashMap hearderMap = new HashMap();
		    while(regAttrIter.hasNext()){
		        CodetableRegAttribute regAttr = (CodetableRegAttribute) regAttrIter.next();
		   	    if(regAttr != null){
		   	   	    CodetableAttributeResponseEvent resp = (CodetableAttributeResponseEvent) aCreator.create(regAttr);
		   	   	    hearderMap.put("" + regAttr.getDisplayOrder(),regAttr.getDbcolumn());
	   	            dispatch.postEvent(resp);
		   	    }
		    }
		    try {
		    	Object entityObj = Class.forName(reg.getCodetableEntityName()).newInstance();
		    	Collection data = (Collection) getResults(PDCodeTableConstants.STRING, PDCodeTableConstants.FINDALL, reg.getCodetableContextKey(), entityObj);
		    	postData(entityObj, reg.getCodetableContextKey(), hearderMap, data.iterator());
		    }catch (NoSuchMethodException ex) {
				postError(ex.getMessage());
				ex.printStackTrace();
			}
	    }
    }

	/* (non-Javadoc)
	 * @see pd.codetable.transactions.ICodetableCreator#save(mojo.km.messaging.IEvent)
	 */
	public void save(IEvent event) throws SecurityException, IllegalArgumentException, InstantiationException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, CodetableException {
	    UpdateCodetableDataEvent uEvent = (UpdateCodetableDataEvent) event;
        Object entityObj = Class.forName(uEvent.getEntityName()).newInstance();
		CodeTable codeTable = CodeTable.find(uEvent.getContextKey());
		CodetableEntityMappingLoader loader = CodetableEntityMappingLoader.getInstance(uEvent.getContextKey());
		HashMap entityMapping = loader.getEntityMapping(uEvent.getContextKey());
	   	
	   	if (uEvent.getCodetableDataId() == null)
		{
	   		// create scenerio
	   		entityObj = this.setEntity(entityObj, uEvent, entityMapping, false);
	   		if(entityObj == null){
	   			return;
	   		}
		   	// set the remaining required attributes --- setSTATUS
	   		getResults(PDCodeTableConstants.STRING, PDCodeTableConstants.SETSTATUS, PDCodeTableConstants.ACTIVE, entityObj);
	   		// set codetable name
	   		getResults(PDCodeTableConstants.STRING, PDCodeTableConstants.SETCODETABLENAME, uEvent.getContextKey(), entityObj);
	   		// set active date
	   		getResults(PDCodeTableConstants.DATE, PDCodeTableConstants.SETACTIVEDATE, DateUtil.getCurrentDate(), entityObj);
	   		// set create date
	   		getResults(PDCodeTableConstants.TIMESTAMP, PDCodeTableConstants.SETCREATEDATE, new Timestamp(System.currentTimeMillis()), entityObj);
	   		// set create user
	   		getResults(PDCodeTableConstants.STRING, PDCodeTableConstants.SETCREATEUSERID, PDSecurityHelper.getLogonId(), entityObj);
	   		codeTable.insertCode(entityObj);
		}
		else
		{
			// update scenerio
			Method method = entityObj.getClass().getMethod(PDCodeTableConstants.FIND, new Class[]{String.class, String.class});
			entityObj = method.invoke(entityObj, new Object[]{uEvent.getContextKey(),uEvent.getCodetableDataId()});
			if(entityObj != null){
				entityObj = this.setEntity(entityObj, uEvent, entityMapping, true);
		   		if(entityObj == null){
		   			return;
		   		}
				//this is temporary, framework should do it by themselves, but since framework is not fixed, we will have it
			   	getResults(PDCodeTableConstants.STRING, PDCodeTableConstants.SETCONTEXT, uEvent.getContextKey(), entityObj);
			}
		}
   }

	/**
	 * @param entityObj
	 * @param event
	 * @param entityMapping
	 * @param isUpdateAction
	 * @return entityObj
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 */
	private Object setEntity(Object entityObj, UpdateCodetableDataEvent event, HashMap entityMapping, boolean isUpdateAction) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
	   	Iterator valueMapIter = event.getValueMap().keySet().iterator();

	   	while(valueMapIter.hasNext()){
	   	    String key = (String) valueMapIter.next();
	   	    String value = (String) event.getValueMap().get(key);
	   	    CodetableRegAttribute attr = CodetableRegAttribute.find(key);
	   	    if(attr != null){
	   	        String propertyName = (String) entityMapping.get(attr.getDbcolumn());
	   	   	    
	 	   	    if(attr.isUnique()){
	 	   	        Object obj = entityObj;
	                if(!(isUpdateAction && value.equalsIgnoreCase("" + getResults(obj, getMethodNameFromAttribute(propertyName,PDCodeTableConstants.GET))))){
	                    if(!attr.getType().equalsIgnoreCase(PDCodeTableConstants.DATE) && !attr.getType().equalsIgnoreCase(PDCodeTableConstants.TIMESTAMP)){
	   	                    obj =  getResults(obj, PDCodeTableConstants.FIND, event.getContextKey(), value.toUpperCase());
	                        if(obj != null){
	      	                    sendNotification(value, "error.codetable.duplicate.parameterValue", attr.getDisplayName());
		 	                    getResults(PDCodeTableConstants.BOOLEAN.toLowerCase(), PDCodeTableConstants.SETMODIFIED, new Boolean(false), entityObj);
		 	      	            return null;
	                        }
	                    }else{
	                        // TODO for date, timestamp, we need to come up with a generic method from framework side
        	            }
	                }
	 	   	    }

	   	        if(PDCodeTableConstants.OID.equalsIgnoreCase(propertyName)){
	   	   	        getResults(PDCodeTableConstants.OBJECT, getMethodNameFromAttribute(propertyName, PDCodeTableConstants.SET), value, entityObj);
	   	        }else if(propertyName != null && !propertyName.equals("")){
	   	   	        getResults(attr.getType(), this.getMethodNameFromAttribute(propertyName, PDCodeTableConstants.SET), value, entityObj);
	   	   	   	}
	   	   	}
	   	}
	   	return entityObj;
	}
	
	/**
	 * @param event
	 * @param entityMapping
	 * @param entityObj
	 * @return true/false
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 */
	 private boolean isValidData(UpdateCodetableDataEvent event, HashMap entityMapping, Object entityObj) throws SecurityException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
	  	  Map valueMap = event.getValueMap();
	   	  Iterator valueMapIter = valueMap.keySet().iterator();
	   	  while(valueMapIter.hasNext()){
	   	      String key = (String) valueMapIter.next();
	   	      String value = (String) valueMap.get(key);
	   	      CodetableRegAttribute attr = CodetableRegAttribute.find(key);
	   	      if(attr != null){
	   	   	   	  String propertyName = (String) entityMapping.get(attr.getDbcolumn());
	   	   	   	  
	 	   	      if(attr.isUnique()){
	 	   	          entityObj =  getResults(entityObj, PDCodeTableConstants.FINDALL, propertyName, value.toUpperCase());
		 	          Iterator iter = (Iterator) entityObj;
		 	          if(iter.hasNext()){
		 	      	       sendNotification(value, "error.codetable.duplicate.parameterValue", attr.getDisplayName());
		 	      	       return false;
		 	          }
		   	      }
	   	      }
	   	  }
	   	  return true;
	}
}
