//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\system\\codetable\\action\\HandleCodeSelectionAction.java

package ui.codetable.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.codetable.reply.CodetableAttributeResponseEvent;
import messaging.codetable.reply.CodetableDataResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.codetable.form.CodetableForm;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HandleCodeSelectionAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 45B9595500C1
    */
   public HandleCodeSelectionAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 45B94F4F0100
    */
   public ActionForward view(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		CodetableForm cForm = (CodetableForm)aForm;
   		
   		Collection col = cForm.getCodetableDataList();
   		if(col != null && !col.isEmpty()){
   			for(Iterator iter = col.iterator(); iter.hasNext();)
   	   		{
   	   			CodetableDataResponseEvent data = 
   	   				(CodetableDataResponseEvent)iter.next();
   	   			if(data.getCodetableDataId().equals(cForm.getSelCodetableDataId()))
   	   			{
   	   				cForm.setCurrentCode((CodetableDataResponseEvent)data.clone());
   	   			}
   	   		}   			
   	   		cForm.setOpType(UIConstants.VIEW);
   	   	    return aMapping.findForward(UIConstants.VIEW_SUCCESS);
   		}
		else
		{
			return aMapping.findForward(UIConstants.FAILURE);
		}
   		
   		
   		
   }
   
   public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
	   	return aMapping.findForward(UIConstants.BACK);
	}
   
   public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		CodetableForm cForm = (CodetableForm) aForm;
		return aMapping.findForward(UIConstants.CANCEL);
	}
   
   public ActionForward reset(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		CodetableForm cForm = (CodetableForm) aForm;
		cForm.setFilterAttributeId("");
		cForm.setFilterAttributeValue("");
		cForm.setFilterType("");
		cForm.setSecondFilterAttributeId("");
		cForm.setSecondFilterAttributeValue("");
		cForm.setSecondFilterType("");
	   	return aMapping.findForward(UIConstants.RESET);
	}
   
   public ActionForward addNewCode(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		CodetableForm cForm = (CodetableForm)aForm;
   		CodetableDataResponseEvent newCode = new CodetableDataResponseEvent();
   		
   		newCode.setValueMap(new TreeMap());
   		
   		cForm.setCurrentCode(newCode);
   		cForm.setOpType(UIConstants.CREATE);
   		
   		return aMapping.findForward(UIConstants.CREATE_SUCCESS);
   }
   
   public ActionForward update(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {	
   		/*
		Collection dataList = form.getCodetableDataList();
		//we always assume that this collection will never be empty 
		//otherwise, we won't be able to populate necessary data
		//for new code table entry.
		 CodetableDataResponseEvent dataRE = 
		(CodetableDataResponseEvent)dataList.iterator().next();
		newCode.setCodetableDataId(dataRE.getCodetableDataId());
		//newCode.setEntityName(dataRE.getEntityName());*/
   		CodetableForm cForm = (CodetableForm) aForm;
   		cForm.setOpType(UIConstants.UPDATE);
   	
   	
   		return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
   }
   
   public ActionForward filter(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		CodetableForm cForm = (CodetableForm) aForm;
		String filteredValue = cForm.getFilterAttributeValue().toUpperCase().trim();
		String filterType = cForm.getFilterType().trim();
		String filterAttributeId = cForm.getFilterAttributeId().trim();
		
		String secondFilteredValue 	= cForm.getSecondFilterAttributeValue().toUpperCase().trim();
		String secondFilterType	   	= cForm.getSecondFilterType().trim();
		String secondFilterAttributeId	= cForm.getSecondFilterAttributeId().trim();
		
		if("".equals(filteredValue) || "".equals(filterType) || "".equals(filterAttributeId)){
			return aMapping.findForward(UIConstants.FILTER_SUCCESS);
		}
		
		if(filteredValue.equals("*")){
			cForm.setFilteredCodetables(cForm.getCodetableDataList());
		}else{
			Iterator iter = cForm.getCodetableDataList().iterator();
			
			if(iter != null){
			    Collection<CodetableDataResponseEvent> filteredCodetables = new ArrayList();
			    Collection<CodetableDataResponseEvent> secondFilteredCodetables = new ArrayList();
			    	while(iter.hasNext()){
			    	    CodetableDataResponseEvent cResp = (CodetableDataResponseEvent) iter.next();
			    	    Iterator headerIter = cForm.getCodetableAttributes().iterator();
			    	    while(headerIter.hasNext()){
			    		CodetableAttributeResponseEvent attr = (CodetableAttributeResponseEvent) headerIter.next();
			    			if(filterAttributeId.equalsIgnoreCase(attr.getCodetableRegAttributeId())){
			    			    String value = (String) cResp.getValueMap().get(new Integer(attr.getDisplayOrder()));
			    			    if(value == null || value.equalsIgnoreCase("")){
    							continue;
			    			    }
        						
			    			    value = value.toUpperCase();
			    			    //System.out.println("filterAttributeId" + filterAttributeId);
			    			    //System.out.println("CodetableRegAttributeId" + attr.getCodetableRegAttributeId() );
			    			    //System.out.println("Value: " + value + " --  filtered value: " + filteredValue + " INDEX: " + value.indexOf(filteredValue) + " 1---"   );
			    			    if(attr.isCompound()){
    							String compoundIdentifier = (attr.getCompundType().equalsIgnoreCase("SL"))?attr.getContextKey():attr.getEntityName();
    							Iterator iterator = cForm.getCompoundList(compoundIdentifier).iterator();
    							while(iterator.hasNext()){
    								CodeResponseEvent resp = (CodeResponseEvent) iterator.next();
    								if(value.equalsIgnoreCase(resp.getCodeId())){
    								    value = resp.getDescription().toUpperCase();
    								    break;
    							    }
    							}
    							
    							//System.out.println("filterAttributeId" + filterAttributeId);
    			    			    	//System.out.println("CodetableRegAttributeId" + attr.getCodetableRegAttributeId() );
    			    			    	//System.out.println("Value: " + value + " --  filtered value: " + filteredValue + " INDEX: " + value.indexOf(filteredValue) + " 2---"   );
			    			    }
			    			    if( filterType.equalsIgnoreCase("contains") ){
        				   	    	if(value.indexOf(filteredValue) < 0){
        				   	    		continue;
        				   	    	}
        				   	    } else if ( filterType.equalsIgnoreCase("starts with") ){
        				   	    	if(!value.startsWith(filteredValue)){
        				   	    		continue;
        				   	    	}
        				   	    } else if ( filterType.equalsIgnoreCase("is exactly") ){
        				   	    	if(!value.equalsIgnoreCase(filteredValue)){
        				   	    		continue;
        				   	    	}
        				   	    }
        						filteredCodetables.add(cResp);
        						break;	
        					}
        				}
        			}
        			
        			if( "".equals(secondFilteredValue) || "".equals(secondFilterType) || "".equals(secondFilterAttributeId) ){
        			    	cForm.setFilteredCodetables(filteredCodetables);
        			} else {
        			    if ( filteredCodetables != null
        				    && filteredCodetables.size() > 0 ) {
                			    for ( CodetableDataResponseEvent codeResp : filteredCodetables ) {
                				Iterator headerIter = cForm.getCodetableAttributes().iterator();
                				while( headerIter.hasNext() ){
                				    CodetableAttributeResponseEvent attr = (CodetableAttributeResponseEvent) headerIter.next();
                        				if ( secondFilterAttributeId.equalsIgnoreCase( attr.getCodetableRegAttributeId() ) ) {
                        				    String value = (String) codeResp.getValueMap().get(new Integer(attr.getDisplayOrder()));
                        				    if(value == null || value.equalsIgnoreCase("")){
        							continue;
                        				    }
                        				    
                        				    value = value.toUpperCase();
                        				    
                        				    if(attr.isCompound()){
            							String compoundIdentifier = (attr.getCompundType().equalsIgnoreCase("SL"))?attr.getContextKey():attr.getEntityName();
            							Iterator iterator = cForm.getCompoundList(compoundIdentifier).iterator();
            							while(iterator.hasNext()){
            								CodeResponseEvent resp = (CodeResponseEvent) iterator.next();
            								if(value.equalsIgnoreCase(resp.getCodeId())){
            								    value = resp.getDescription().toUpperCase();
            								    break;
            							    }
            							}
        			    			    }
                        				   
                        				    
                        				    if( secondFilterType.equalsIgnoreCase("contains") ){
                        					if( value.indexOf(secondFilteredValue) < 0 ){
                        					    continue;
                        					}
                        				    } else if ( secondFilterType.equalsIgnoreCase("starts with") ){
                        					if( !value.startsWith(secondFilteredValue) ){
        	        				   	   continue;
                        					}
                        				    } else if ( secondFilterType.equalsIgnoreCase("is exactly") ){
                        					if( !value.equalsIgnoreCase(secondFilteredValue) ){
                        					    continue;
                        					}
                        				    }
        							
                        				    secondFilteredCodetables.add(codeResp);
                        				    break;
                        				    
                        				}
                        				
                				}
                			    }
        			    
                			    cForm.setFilteredCodetables(secondFilteredCodetables);
        			    }
        			}
        			
			}
		}
        return aMapping.findForward(UIConstants.FILTER_SUCCESS);
	}
   
   protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.view", "view");
		buttonMap.put("button.add", "addNewCode");
		buttonMap.put("button.update", "update");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.back", "back");
		buttonMap.put("button.filter", "filter");
		buttonMap.put("button.reset", "reset");
		return buttonMap;
	}
}
