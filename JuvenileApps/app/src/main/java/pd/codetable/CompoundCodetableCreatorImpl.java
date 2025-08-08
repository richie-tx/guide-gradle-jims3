//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\codetable\\transactions\\GetCodeTableRecordCommand.java

package pd.codetable;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import messaging.codetable.GetCodetableRecordEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.codetable.reply.CodetableAttributeResponseEvent;
import messaging.codetable.reply.CodetableCompoundListResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDCodeTableConstants;
import naming.ResponseLocatorConstants;
import pd.codetable.CodetableEntityMappingLoader;
import pd.codetable.CodetableReg;
import pd.codetable.CodetableRegAttribute;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.exception.CodetableException;
import pd.exception.ReflectionException;
import pd.juvenilewarrant.JJSPetition;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class CompoundCodetableCreatorImpl extends CodetableCreator implements ICodetableCreator
{   
	  /**
	   * 
	   * @roseuid 45B9521B01BF
	   */
	  public CompoundCodetableCreatorImpl() 
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
	    CodetableEntityMappingLoader loader = CodetableEntityMappingLoader.getInstance(reg.getCodetableEntityName());
    	HashMap entityMapping = loader.getEntityMapping(reg.getCodetableEntityName());
	    
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
		   	    
		   	    // send additional compound list
   	            if(regAttr.getCompoundAttributeName() != null && !regAttr.getCompoundAttributeName().equals("")){
	   				this.postCompoundListResponseEvent(regAttr,entityMapping,dispatch);
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
	 * @see pd.codetable.transactions.ICodetableCreator#save(mojo.km.messaging.IEvent)
	 */
	public void save(IEvent event) throws SecurityException, IllegalArgumentException, InstantiationException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, CodetableException {
		// both complex and compound have same implementation, so for compound, complex save method will be called.
	}
	
    /**
	 * @param regAttr
	 * @param mapping
	 * @param dispatch
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
	 */
	private void postCompoundListResponseEvent(CodetableRegAttribute regAttr, HashMap mapping, IDispatch dispatch) throws InstantiationException, ClassNotFoundException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
      	String codetableIdentifier = regAttr.getCompEntityName();
	    Iterator dataIter = null;
	    SortedMap map = new TreeMap();
	    CodetableCompoundListResponseEvent cResp = new CodetableCompoundListResponseEvent();
	   
	    String compoundAttributeId = (regAttr.getCompoundAttributeId() != null)?regAttr.getCompoundAttributeId():regAttr.getDbcolumn();
	    if ("CD.CODE".equals( compoundAttributeId ) ) {
		compoundAttributeId = "CODE";
	    }
	    String compoundAttributeName = regAttr.getCompoundAttributeName();
	    if ("CD.DESC".equals( compoundAttributeName ) ){
		compoundAttributeName = "DESCRIPTION";
	    }
	    try {
	    	Object entityObj = Class.forName(codetableIdentifier).newInstance();
	    	if(PDCodeTableConstants.SIMPLE_CODE.equalsIgnoreCase(regAttr.getCompoundType())){
	    		codetableIdentifier = regAttr.getCompContextKey();
	    		Collection data = (Collection) getResults(PDCodeTableConstants.STRING, PDCodeTableConstants.FINDALL, codetableIdentifier, entityObj);
	    		dataIter = data.iterator();
   	    	}else{	    	
   	    		dataIter = (Iterator) this.getResults(entityObj,PDCodeTableConstants.FINDALL);
   	    		
   	    	}
   	    
	    	CodetableEntityMappingLoader loader = CodetableEntityMappingLoader.getInstance(codetableIdentifier);
	    	HashMap entityMapping = loader.getEntityMapping(codetableIdentifier);
	    	//System.out.println("Entity mapping" + entityMapping + " " + entityMapping.get("CODE") );
   	    
	    	// Read this from compoundAttributeId. If dbcolumn and compAttributeId are same, no need to populate compoundAttributeId in the database.
	    	// system will read the dbcolumn as compAttributeId. otherwise system will read compAttributeId.
	    	compoundAttributeId = (String) entityMapping.get(compoundAttributeId);
	    	
	    	SortedMap valueMap = new TreeMap();
	    	compoundAttributeName = (String) entityMapping.get(compoundAttributeName );
	    	String compoundAttributeStatus = "";
	    	String simpleAttributeStatus = "";
	    	List<CodeResponseEvent>activeList = new ArrayList<>();
	    	
	    	CodeResponseEvent resp = null;   	    
	    	while(dataIter.hasNext()){
	    		entityObj = dataIter.next();
   	   	   		if(compoundAttributeId != null && compoundAttributeName != null){
   	   	   			Object objectIdValue = getResults(entityObj, getMethodNameFromAttribute(compoundAttributeId, PDCodeTableConstants.GET));
   	   	   			if(objectIdValue != null){
   	   	   				Object objectNameValue = getResults(entityObj, getMethodNameFromAttribute(compoundAttributeName, PDCodeTableConstants.GET));
   	   	   				Object objectStatusValue = null;
   	   	   				
   	   	   				if(objectNameValue != null){
   	   	   					resp = new CodeResponseEvent();
   	   	   					resp.setCodeId(objectIdValue.toString());
   	   	   					resp.setDescription(objectNameValue.toString());
   	   	   					if ("pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit".equals( codetableIdentifier )) {
   	   	   					    compoundAttributeStatus = (String) entityMapping.get("STATUSCD");    
   	   	   					    objectStatusValue = getResults(entityObj, getMethodNameFromAttribute(compoundAttributeStatus, PDCodeTableConstants.GET));
   	   	   					    if( objectStatusValue == null
           	   	   						||(objectStatusValue != null
           	   	   							&& "A".equals( objectStatusValue.toString() ))){
   	   	   						activeList.add(resp);
   	   	   					    }
   	   	   					} else if ("TJJD_INDICATOR".equals(codetableIdentifier)){
   	   	   					    	simpleAttributeStatus = (String) entityMapping.get("STATUS");
   	   	   					    	objectStatusValue = getResults(entityObj, getMethodNameFromAttribute(simpleAttributeStatus, PDCodeTableConstants.GET));
   	   	   					    	    if( objectStatusValue == null
               	   	   						||(objectStatusValue != null
               	   	   							&& "ACTIVE".equals( objectStatusValue.toString() ))){
        	   	   						activeList.add(resp);
        	   	   					    }
   	   	   					}
   	   	   					map.put(resp.getDescription() + resp.getCodeId(), resp);
   	   	   				}
   	   	   			}
   	   	   		}
	    	}
	    	if ( activeList != null 
	    		&& activeList.size() > 0 ){
	    	    Collections.sort(activeList,  new Comparator<CodeResponseEvent>(){
	    		@Override
			public int compare(CodeResponseEvent c1, CodeResponseEvent c2) {
				return c1.getDescription().compareTo( c2.getDescription() );
			}
	    	    });
	    	    cResp.setActiveList(activeList);
	    	}
	    	
	    } catch (NoSuchMethodException ex) {
			postError(ex.getMessage());
			ex.printStackTrace();
		}
   	    List dropdownList = new ArrayList();
	    dropdownList.addAll(map.values());
   	    cResp.setList(dropdownList);
   	    cResp.setCodetableIdentifier(codetableIdentifier);
   	    dispatch.postEvent(cResp);
	}
}
