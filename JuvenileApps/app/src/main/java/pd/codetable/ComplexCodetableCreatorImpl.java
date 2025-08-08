//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\codetable\\transactions\\GetCodeTableRecordCommand.java

package pd.codetable;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.codetable.GetCodetableRecordEvent;
import messaging.codetable.UpdateCodetableDataEvent;
import messaging.codetable.reply.CodetableAttributeResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.ReturnException;
import naming.PDCodeTableConstants;
import naming.ResponseLocatorConstants;
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

public class ComplexCodetableCreatorImpl extends CodetableCreator implements ICodetableCreator
{
   
	  /**
	   * @roseuid 45B9521B01BF
	   */
	  public ComplexCodetableCreatorImpl() 
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
		   	   	    post(resp);
		   	    }
		    }
		    try {
		    	Object entityObj = Class.forName(reg.getCodetableEntityName()).newInstance();
			    Iterator dataIter = (Iterator) getResults(entityObj, PDCodeTableConstants.FINDALL);
				postData(entityObj, reg.getCodetableEntityName(), hearderMap, dataIter);
				
     		}catch (NoSuchMethodException ex) {
				postError(ex.getMessage());
				ex.printStackTrace();
			}
		}
    }

	/* (non-Javadoc)
	 * @see pd.codetable.transactions.ICodetable#save(mojo.km.messaging.IEvent)
	 */
	public void save(IEvent event) throws SecurityException, IllegalArgumentException, InstantiationException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, CodetableException {
		UpdateCodetableDataEvent uEvent = (UpdateCodetableDataEvent) event;
  	    Object entityObj = Class.forName(uEvent.getEntityName()).newInstance();
   	   
   	   // update scenerio
   	   if(uEvent.getCodetableDataId() != null && !uEvent.getCodetableDataId().equals("")){
   	       try{
   	           entityObj = getResults(PDCodeTableConstants.STRING,PDCodeTableConstants.FIND,uEvent.getCodetableDataId(),entityObj);
   	       }catch(NoSuchMethodException e){
   	           entityObj = getResults(PDCodeTableConstants.INTEGER,PDCodeTableConstants.FIND,uEvent.getCodetableDataId(),entityObj);
   	       }
   	       if(entityObj!=null)
   	    	   this.saveCodetable(uEvent,entityObj, true);
   	   }else{
   	       // set create date
	   	   getResults(PDCodeTableConstants.TIMESTAMP, PDCodeTableConstants.SETCREATEDATE, new Timestamp(System.currentTimeMillis()), entityObj);
	   	   // set create user
	   	   getResults(PDCodeTableConstants.STRING, PDCodeTableConstants.SETCREATEUSERID, PDSecurityHelper.getLogonId(), entityObj);
		   this.saveCodetable(uEvent,entityObj, false);
   	   }
	}
	
	/**
	  * @param event
	  * @param entityObj
	  * @param isUpdateAction
	  * @throws NoSuchMethodException
	  * @throws SecurityException
	  * @throws InvocationTargetException
	  * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	  */
	  private void saveCodetable(UpdateCodetableDataEvent event, Object entityObj, boolean isUpdateAction) throws SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException{
	  	  String codetableIdentifier = event.getEntityName();
		  CodetableEntityMappingLoader loader = CodetableEntityMappingLoader.getInstance(codetableIdentifier);
		  HashMap entityMapping = loader.getEntityMapping(codetableIdentifier);
	   	  Map valueMap = event.getValueMap();
	   	  Iterator valueMapIter = valueMap.keySet().iterator();
	   	  while(valueMapIter.hasNext()){
	   	      String key = (String) valueMapIter.next();
	   	      String value = (String) valueMap.get(key);
	   	      CodetableRegAttribute attr = CodetableRegAttribute.find(key);
	   	      if(attr != null
	   		      && !"pd.contact.agency.Agency".equals( attr.getCompEntityName() ) ){
	   	   	   	  String propertyName = (String) entityMapping.get(attr.getDbcolumn());
	   	   	   	  
	   	   	      if(!isValidData(entityObj, attr, propertyName, value, isUpdateAction)){
	   	   	      	   return;
	   	   	      }
	   	   	      	
	   	   	      if(PDCodeTableConstants.OID.equalsIgnoreCase(propertyName)){
		   	   	       getResults(PDCodeTableConstants.OBJECT, getMethodNameFromAttribute(propertyName, PDCodeTableConstants.SET), value, entityObj);
	   	   	      }else if(propertyName != null && !propertyName.equals("")){    
	   	   	      	   getResults(attr.getType(), getMethodNameFromAttribute(propertyName, PDCodeTableConstants.SET), value, entityObj);
	   	   	   	  }
	   	      }
	   	  }
	  }
	  
	 /**
	 * @param entityObj
	 * @param attr
	 * @param propertyName
	 * @param propertyValue
	 * @param isUpdateAction
	 * @return true/false
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 */
	 private boolean isValidData(Object entityObj, CodetableRegAttribute attr, String propertyName, String propertyValue, boolean isUpdateAction) throws SecurityException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
   	     if(attr.isValidCheckRequired()){
   	         String validationEntityName = attr.getCompEntityName();
   	         CodetableEntityMappingLoader loader = CodetableEntityMappingLoader.getInstance(validationEntityName);
	         HashMap entityMapping = loader.getEntityMapping(validationEntityName);
	         String propertyName1 = (String) entityMapping.get(attr.getCompoundAttributeId());
	         Object obj = Class.forName(validationEntityName).newInstance();
	 	     obj =  getResults(obj, PDCodeTableConstants.FINDALL, propertyName1, propertyValue.toUpperCase());
	 	     Iterator iter = (Iterator) obj;
	 	     if(!iter.hasNext()){
	 	         sendNotification(propertyValue, "error.codetable.invalid.parameterValue", attr.getDisplayName());
	 	      	 getResults(PDCodeTableConstants.BOOLEAN.toLowerCase(), PDCodeTableConstants.SETMODIFIED, new Boolean(false), entityObj);
	 	      	 return false;
	 	     }
   	     }
   	  
   	     if(attr.isUnique()){
   	         Object obj = entityObj;
   	         if(!(isUpdateAction && propertyValue.equalsIgnoreCase("" + getResults(obj, getMethodNameFromAttribute(propertyName,PDCodeTableConstants.GET))))){
   	             if(!attr.getType().equalsIgnoreCase(PDCodeTableConstants.DATE) && !attr.getType().equalsIgnoreCase(PDCodeTableConstants.TIMESTAMP)){
 	   	             obj =  getResults(obj, PDCodeTableConstants.FINDALL, propertyName, propertyValue.toUpperCase());
 	   	          	 Iterator iter = (Iterator) obj;
	 	             if(iter.hasNext()){
	 	      	         sendNotification(propertyValue, "error.codetable.duplicate.parameterValue", attr.getDisplayName());
			 	         getResults(PDCodeTableConstants.BOOLEAN.toLowerCase(), PDCodeTableConstants.SETMODIFIED, new Boolean(false), entityObj);
			 	      	 return false;
	 	             }
   	             }
   	         }else{
   	             // TODO for date, timestamp, we need to come up with a generic method from framework side
   	         	return true;
   	         }
         }
	   	 return true;
	}
}
