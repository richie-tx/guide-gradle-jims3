/*
 * Created on Dec 4, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.codetable.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import messaging.codetable.ValidateCompoundEntityEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.config.EntityMappingProperties;
import mojo.km.config.FieldMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDCodeTableConstants;

/**
 * @author Nagalla
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ValidateCompoundEntityCommand implements ICommand {

	    /*
	     * (non-Javadoc)
	     * 
	     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	     */
	    public void execute(IEvent anEvent) throws Exception {	    	
	    	ValidateCompoundEntityEvent gEvent = (ValidateCompoundEntityEvent) anEvent;	    
	    	validateCompoundEntity(gEvent);
	    }
	    
    
	    private static void validateCompoundEntity(ValidateCompoundEntityEvent gEvent) { 
	    	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	    	EntityMappingProperties matchedEntity = null;    	
	    	MojoProperties props = MojoProperties.getInstance();		
			Iterator i = props.getEntityMaps();				
	    	if(gEvent.getContextkey()!=null){    		
	    		while(i.hasNext())	{			
	    			EntityMappingProperties eProps = (EntityMappingProperties) i.next();
	    			if(eProps.getEntity().equalsIgnoreCase(PDCodeTableConstants.CONTEXTS_CLASS)) {
	    				String contextKey = eProps.getContextKey().toLowerCase().trim();
	    				String searchKey = gEvent.getContextkey().toLowerCase().trim();	    				
	    					if(contextKey.equalsIgnoreCase(searchKey)) {
	    						matchedEntity = eProps;
	    						break;
	    					}
	    			} 
	    		}     		
		    } else if(gEvent.getEntityName()!=null){	       
		        while(i.hasNext())	{			
					EntityMappingProperties eProps = (EntityMappingProperties) i.next();
//					if(eProps.getEntity().startsWith(PDCodeTableConstants.ENTITIES_PACKAGE)&& !(eProps.getEntity().equalsIgnoreCase(PDCodeTableConstants.CONTEXTS_CLASS))) {
					if(!(eProps.getEntity().equalsIgnoreCase(PDCodeTableConstants.CONTEXTS_CLASS))) {	
						if(eProps.getEntity().equalsIgnoreCase(eProps.getContextKey())) {
							String contextKey = eProps.getContextKey().toLowerCase().trim();
		    				String searchName = gEvent.getEntityName().toLowerCase().trim();		    				
		    					if(contextKey.equalsIgnoreCase(searchName)) {
		    						matchedEntity = eProps;
		    						break;
		    					}
		    					
						}
					}
				}	        	       	    
		    } 
		    if(matchedEntity!=null) {		    		
		    	String compoundName = null; if(gEvent.getName()!=null ) compoundName = gEvent.getName().trim() ;
		    	String compoundId = null; if(gEvent.getId()!=null ) compoundId = gEvent.getId().trim();
		    	List dbItemsForEntity = new ArrayList();
		    	if(matchedEntity.getSaveCallbackProperties()!=null) {
					List fields = matchedEntity.getSaveCallbackProperties().getFields();
					if(fields!=null && !fields.isEmpty()) {
						Iterator fieldsIte = fields.iterator();
						while(fieldsIte.hasNext()) {
							FieldMappingProperties fmp = (FieldMappingProperties)fieldsIte.next();
							String dbItemName = fmp.getDataItemName();							
							dbItemName = dbItemName.trim();								
							dbItemsForEntity.add(dbItemName);							
						}
					}
					if(!(dbItemsForEntity.contains(compoundId))) {
						ErrorResponseEvent errRespEvt=new ErrorResponseEvent();
						errRespEvt.setMessage("error.codetable.nocompoundid");				
						dispatch.postEvent(errRespEvt);
						return;
					}
					if(compoundName!=null) {
						if(!(dbItemsForEntity.contains(compoundName))) {
							ErrorResponseEvent errRespEvt=new ErrorResponseEvent();
							errRespEvt.setMessage("error.codetable.nocompoundname");				
							dispatch.postEvent(errRespEvt);
							return;
						}
					}
				} else {
					ErrorResponseEvent errRespEvt=new ErrorResponseEvent();
					errRespEvt.setMessage("error.codetable.nocallbacksforcompound");				
					dispatch.postEvent(errRespEvt);
					return;
				}
		    } else {
		    	ErrorResponseEvent errRespEvt=new ErrorResponseEvent();
				errRespEvt.setMessage("error.codetable.nomatchingcompoundentity");				
				dispatch.postEvent(errRespEvt);
				return;
		    }
		    
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


