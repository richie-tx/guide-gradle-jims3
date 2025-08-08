package pd.codetable;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;

import mojo.km.config.EntityMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.FieldMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.km.persistence.PersistentObject;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * 
 * @roseuid 45BA30DA00B9
 */
public class CodetableEntityMappingLoader extends PersistentObject
{
	static private CodetableEntityMappingLoader instance = null;
	static private HashMap map = null;
    	
	/**
	 * <p>name: getInstance description: Other classes call this method to get the CodetableEntityMappingLoader
	 * @param entityName
	 * @return Instance
	 */
	public synchronized static CodetableEntityMappingLoader getInstance(String entityName) throws InvocationTargetException{
	   if (instance == null) {
		   instance = new CodetableEntityMappingLoader(entityName, true);
	   }else if(!map.containsKey(entityName)){
	   	   instance = new CodetableEntityMappingLoader(entityName, false);
	   }
	   return instance;
	}


	/** <p>name: CodetableEntityMappingLoader description: constructor 
	 * @param entityName
	 * @param firstTime
	 */
	private CodetableEntityMappingLoader(String entityName, boolean firstTime) throws InvocationTargetException{
	   this.load(entityName, firstTime);
	}

	/** 
	 * @description: loads the mapping
	 * @param entityName
	 * @param firstTime
	 */
	private void load(String entityName, boolean firstTime) throws InvocationTargetException{
	   if(firstTime){
	       map = new HashMap();
	   }
   	   EntityMappingProperties entityMap = MojoProperties.getInstance().getEntityMap(entityName);
   	   if(entityMap == null){
   	   	   throw new InvocationTargetException(null, "Right Configuration does not exist for " + entityName);
   	   }
	   Iterator queryIter = entityMap.getQueryCallbacks();
	   Iterator saveIter = entityMap.getSaveCallbacks();
	   if(!queryIter.hasNext()){
	       throw new InvocationTargetException(null, "QueryCallBack Mappings do not exist for " + entityName);
	   }
	   
	   if(!saveIter.hasNext()){
	       throw new InvocationTargetException(null, "SaveCallBack Mappings do not exist for " + entityName);
	   }	   
	   
	   HashMap mapping = new HashMap();
	   while(queryIter.hasNext()){
	   	    EventQueryProperties   e = (EventQueryProperties) queryIter.next();
	   	    Iterator callbackIter = e.getFieldsIterator();
	   	    while(callbackIter.hasNext()){
	   	    	FieldMappingProperties f = (FieldMappingProperties) callbackIter.next();
	   	    	if(!mapping.containsKey(f.getDataItemName())){
	   	    		mapping.put(f.getDataItemName(),f.getPropertyName());
	   	    	}
	   	    }
	   }
	   map.put(entityName,mapping);
	}
	
	/** 
	 * @param entityName
	 * @return  firstTime
	 */
	public HashMap getEntityMapping(String entityName){
	   return (HashMap) map.get(entityName);
	}
}
