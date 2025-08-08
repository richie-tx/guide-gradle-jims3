//Source file: C:\\views\\Security\\app\\src\\ui\\security\\inquiries\\action\\DisplaySecurityInquiriesAgencySearchResultsAction.java

package ui.security.inquiries.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.agency.GetAgenciesEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.contact.UIContactHelper;
import ui.security.inquiries.form.SecurityInquiriesForm;


public class DisplaySecurityInquiriesAgencySearchResultsAction extends Action
{
   
   /**
    * @roseuid 44E9D1A402F6
    */
   public DisplaySecurityInquiriesAgencySearchResultsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 44D2218D0114
    */
   public ActionForward execute(
   		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
	   	String forward = UIConstants.FAILURE;
	   	String msg = null;
	   	int size = 0;
	   	Collection emptyColl = new ArrayList();
	   	SecurityInquiriesForm securityInquiriesForm = (SecurityInquiriesForm) aForm; 
	   	securityInquiriesForm.setAgencies(emptyColl);
	   	securityInquiriesForm.setSearchResultsCount("");
	   	
		GetAgenciesEvent requestEvent = new GetAgenciesEvent();
		requestEvent.setAgencyName(securityInquiriesForm.getAgencyName());
		requestEvent.setAgencyId(securityInquiriesForm.getAgencyIdPrompt());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);
				
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
	
		Collection agencies = MessageUtil.compositeToCollection(compositeResponse, AgencyResponseEvent.class);
		if (agencies != null && !(agencies.isEmpty())){
			size = agencies.size();
			if (size == 1){
				AgencyResponseEvent agency = (AgencyResponseEvent) agencies.toArray()[0];		
				securityInquiriesForm.setAgencyId(agency.getAgencyId());
				forward = UIConstants.AGENCY_SUCCESS;
		    }else{
		    	String searchResultSize = String.valueOf(size); 
		    	securityInquiriesForm.setSearchResultsCount(searchResultSize);
				securityInquiriesForm.setAgencies(UIContactHelper.sortAgencyList(agencies));
				forward = UIConstants.AGENCY_LIST_SUCCESS;
			}
		}
		else
		{
			ActionErrors errors = new ActionErrors();
			if (msg == null)
			{
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.agency.found"));
			}
			saveErrors(aRequest, errors);
		}	
		return aMapping.findForward(forward);
	   }
}
