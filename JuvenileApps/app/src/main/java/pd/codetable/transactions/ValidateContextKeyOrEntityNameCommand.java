/*
 * Created on Sep 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.codetable.transactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.codetable.GetCodetableRegistrationAttributesEvent;
import messaging.codetable.ValidateContextKeyOrEntityNameEvent;
import messaging.codetable.reply.ContextKeyOrEntityNameValidationResponseEvent;
import mojo.km.config.EntityMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import naming.PDCodeTableConstants;
import pd.codetable.CodetableReg;

/**
 * @author Nagalla
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ValidateContextKeyOrEntityNameCommand  implements ICommand {

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent anEvent) throws Exception {
    	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
    	ValidateContextKeyOrEntityNameEvent gEvent = (ValidateContextKeyOrEntityNameEvent) anEvent;	    
//	    try {			
			ContextKeyOrEntityNameValidationResponseEvent responseEvent = getCodeContextsOrEntities(gEvent);			
			dispatch.postEvent(responseEvent);
//	    } catch (ReflectionException e) {
//			e.printStackTrace();
//	    } 	    
	    
	    
    }
    
    private static ContextKeyOrEntityNameValidationResponseEvent getCodeContextsOrEntities(ValidateContextKeyOrEntityNameEvent gEvent) { 
    	ContextKeyOrEntityNameValidationResponseEvent responseEvent = new ContextKeyOrEntityNameValidationResponseEvent();
    	List codeContextOrEntityList = new ArrayList();    	
    	MojoProperties props = MojoProperties.getInstance();		
		Iterator i = props.getEntityMaps();				
    	if(gEvent.getContextkey()!=null){    		
    		while(i.hasNext())	{			
    			EntityMappingProperties eProps = (EntityMappingProperties) i.next();
    			if(eProps.getEntity().equalsIgnoreCase(PDCodeTableConstants.CONTEXTS_CLASS)) {
    				String contextKey = eProps.getContextKey().toLowerCase().trim();
    				String searchKey = gEvent.getContextkey().toLowerCase().trim();
    				if(contextKey.indexOf(searchKey) > -1) {
    					if(contextKey.equalsIgnoreCase(searchKey)) {
    						codeContextOrEntityList.clear();
    						codeContextOrEntityList.add(eProps.getContextKey());
    						break;
    					}
    					codeContextOrEntityList.add(eProps.getContextKey());    					
    				}
    			} 
    		}     		
	    } else if(gEvent.getEntityName()!=null){	       
	        while(i.hasNext())	{			
				EntityMappingProperties eProps = (EntityMappingProperties) i.next();
//				if(eProps.getEntity().startsWith(PDCodeTableConstants.ENTITIES_PACKAGE)&& !(eProps.getEntity().equalsIgnoreCase(PDCodeTableConstants.CONTEXTS_CLASS))) {
				if(!(eProps.getEntity().equalsIgnoreCase(PDCodeTableConstants.CONTEXTS_CLASS))) {	
					if(eProps.getEntity().equalsIgnoreCase(eProps.getContextKey())) {
						String contextKey = eProps.getContextKey().toLowerCase().trim();
	    				String searchName = gEvent.getEntityName().toLowerCase().trim();
	    				if(contextKey.indexOf(searchName) > -1) {
	    					if(contextKey.equalsIgnoreCase(searchName)) {
	    						codeContextOrEntityList.clear();
	    						codeContextOrEntityList.add(eProps.getContextKey());
	    						break;
	    					}
	    					codeContextOrEntityList.add(eProps.getContextKey());
	    				}
					}
				}
			}	        	       	    
	    } 
	    if(!(codeContextOrEntityList.isEmpty()) && (codeContextOrEntityList.size() > 1)) {
	    	try {
	    		Iterator entityIte = codeContextOrEntityList.iterator();
	    		List removeList = new ArrayList();
	    		while(entityIte.hasNext()) {
	    			String entityOrKeyExists  = (String)entityIte.next();
	    			if(gEvent.getContextkey()!=null){
	    	    		try {
	    	    			
	    	    			Iterator ite = CodetableReg.findAll("codetableContextKey", entityOrKeyExists);
	    	    			if(ite!=null && ite.hasNext()) {
	    	    				removeList.add(entityOrKeyExists);
	    	    			}
	    	    		} catch (Exception ex) { }	    		
	    	    	} else if(gEvent.getEntityName()!=null){
	    	    		try {
	    	    			GetCodetableRegistrationAttributesEvent regEnityEvent = new GetCodetableRegistrationAttributesEvent();
	    	    			regEnityEvent.setType(entityOrKeyExists);
	    	    			IHome home = new Home();
	    	    			Iterator ite = home.findAll(regEnityEvent, CodetableReg.class);	    	    			
	    	    			if(ite!=null && ite.hasNext()) {
	    	    				removeList.add(entityOrKeyExists);
	    	    			}
	    	    		} catch (Exception ex) { }
	    	    	}
	    		}
	    		codeContextOrEntityList.removeAll(removeList);
    		} catch (Exception ex) { } 
    		if(codeContextOrEntityList.isEmpty()) {
    	    	if(gEvent.getContextkey()!=null){
    	    		responseEvent.setErrorMessage("No_Context_Key");
    	    	} else if(gEvent.getEntityName()!=null){
    	    		responseEvent.setErrorMessage("No_Entity_Name");
    	    	}
    	    } else {
    	    	Collections.sort(codeContextOrEntityList);	 
    	    	responseEvent.setContextKeysOrEntityNames(codeContextOrEntityList);
    	    }
	    } else if(!(codeContextOrEntityList.isEmpty()) && (codeContextOrEntityList.size() == 1)) {	    	
	    	if(gEvent.getContextkey()!=null){
	    		try {
//	    			Iterator ite = CodetableReg.findAll("contextKey", gEvent.getContextkey());
	    			Iterator ite = CodetableReg.findAll("codetableContextKey", (String)codeContextOrEntityList.iterator().next());
	    			if(ite!=null && ite.hasNext()) {
	    				responseEvent.setErrorMessage("Context_Key_Exists");
	    			}
	    		} catch (Exception ex) { }	    		
	    	} else if(gEvent.getEntityName()!=null){
	    		try {
//	    			Iterator ite = CodetableReg.findAll("entityName", gEvent.getEntityName());
//	    			Iterator ite = CodetableReg.findAll("codetableEntityName", (String)codeContextOrEntityList.iterator().next());
	    			GetCodetableRegistrationAttributesEvent regEnityEvent = new GetCodetableRegistrationAttributesEvent();
	    			regEnityEvent.setType((String)codeContextOrEntityList.iterator().next());
	    			IHome home = new Home();
	    			Iterator ite = home.findAll(regEnityEvent, CodetableReg.class);
	    			if(ite!=null && ite.hasNext()) {
	    				responseEvent.setErrorMessage("Entity_Name_Exists");
	    			}
	    		} catch (Exception ex) { }
	    	}
	    	responseEvent.setContextKeysOrEntityNames(codeContextOrEntityList);
	    } else if(codeContextOrEntityList.isEmpty()) {
	    	if(gEvent.getContextkey()!=null){
	    		responseEvent.setErrorMessage("No_Context_Key");
	    	} else if(gEvent.getEntityName()!=null){
	    		responseEvent.setErrorMessage("No_Entity_Name");
	    	}
	    }
		if(gEvent.getName()!=null){
    		try {
    			
    			Iterator ite = CodetableReg.findAll("codetableName", gEvent.getName());
    			if(ite!=null && ite.hasNext()) {
    				responseEvent.setNameError("Name_Exists");
    				responseEvent.setErrorMessage("Name_Exists");
    			}
    		} catch (Exception ex) { }	    		
    	}
	    
	    return responseEvent;

    }

    
    private static Map getCodeContextsOrEntitiesOrig(String type) {
    	HashMap codeContextsAndEntitiesMap = new HashMap();
    	List codeContextsList = new ArrayList();
    	List codeEntitiesList = new ArrayList();
    	MojoProperties props = MojoProperties.getInstance();		
		Iterator i = props.getEntityMaps();				
    	if(PDCodeTableConstants.CODETABLE_CONTEXTS.equalsIgnoreCase(type)){    		
    		while(i.hasNext())	{			
    			EntityMappingProperties eProps = (EntityMappingProperties) i.next();
    			if(eProps.getEntity().equalsIgnoreCase(PDCodeTableConstants.CONTEXTS_CLASS)) {
    				codeContextsList.add(eProps.getContextKey());
    			} 
    		}    		
	    } else if(PDCodeTableConstants.CODETABLE_ENTITIES.equalsIgnoreCase(type)){	       
	        while(i.hasNext())	{			
				EntityMappingProperties eProps = (EntityMappingProperties) i.next();
				if(eProps.getEntity().startsWith(PDCodeTableConstants.ENTITIES_PACKAGE)&& !(eProps.getEntity().startsWith(PDCodeTableConstants.CONTEXTS_CLASS))) {
					if(eProps.getEntity().equalsIgnoreCase(eProps.getContextKey())) {
						codeEntitiesList.add(eProps.getContextKey());
					}
				}
			}
	       	    
	    } else if(PDCodeTableConstants.CODETABLE_CONTEXTS_AND_ENTITIES.equalsIgnoreCase(type)){	       	        
	        while(i.hasNext())	{			
				EntityMappingProperties eProps = (EntityMappingProperties) i.next();
				if(eProps.getEntity().equalsIgnoreCase(PDCodeTableConstants.CONTEXTS_CLASS)) {
					codeContextsList.add(eProps.getContextKey());
				} else if(eProps.getEntity().startsWith(PDCodeTableConstants.ENTITIES_PACKAGE)&& !(eProps.getEntity().startsWith(PDCodeTableConstants.CONTEXTS_CLASS))) {
					if(eProps.getEntity().equalsIgnoreCase(eProps.getContextKey())) {
						codeEntitiesList.add(eProps.getContextKey());
					}
				}
			}
	       	    
	    }  
	    
	    Collections.sort(codeContextsList);
	    Collections.sort(codeEntitiesList);	    
	    codeContextsAndEntitiesMap.put(new Integer(1), codeContextsList);
	    codeContextsAndEntitiesMap.put(new Integer(2), codeEntitiesList);
	    
	    return codeContextsAndEntitiesMap;

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
     */
    public void onRegister(IEvent event) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject) {
        // TODO Auto-generated method stub

    }

}

