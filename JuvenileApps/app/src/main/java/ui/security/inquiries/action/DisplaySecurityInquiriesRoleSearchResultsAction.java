//Source file: C:\\views\\Security\\app\\src\\ui\\security\\inquiries\\action\\DisplaySecurityInquiriesRoleSearchResultsAction.java

package ui.security.inquiries.action;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.security.GetRolesByConstraintsEvent;
import messaging.security.reply.RoleResponseEvent;
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

import ui.security.SecurityUIHelper;
import ui.security.inquiries.form.SecurityInquiriesForm;

public class DisplaySecurityInquiriesRoleSearchResultsAction extends Action
{
   
   /**
    * @roseuid 44E9D1DE001F
    */
   public DisplaySecurityInquiriesRoleSearchResultsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 44D2218B0016
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
	   	securityInquiriesForm.setRoles(emptyColl);
	   	securityInquiriesForm.setSearchResultsCount("");
	   	
		GetRolesByConstraintsEvent requestEvent = new GetRolesByConstraintsEvent();
		requestEvent.setRoleName(securityInquiriesForm.getRoleName());
		requestEvent.setRoleDescription(securityInquiriesForm.getRoleDescription());
		requestEvent.setAgencyName(securityInquiriesForm.getRoleAgencyName());
		requestEvent.setAgencyId(securityInquiriesForm.getRoleAgencyId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);
				
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
	
		Collection roles = MessageUtil.compositeToCollection(compositeResponse, RoleResponseEvent.class);
		if (roles != null && !(roles.isEmpty())){
			size = roles.size();
			if (size == 1){
				RoleResponseEvent role = (RoleResponseEvent) roles.toArray()[0];
				securityInquiriesForm.setRoleId(role.getRoleId());
				forward = UIConstants.ROLE_SUCCESS;
		    }else{
		    	String searchResultSize = String.valueOf(size);
		    	securityInquiriesForm.setSearchResultsCount(searchResultSize);
		    	securityInquiriesForm.setRoles(SecurityUIHelper.sortRoleNames(roles));
				forward = UIConstants.ROLE_LIST_SUCCESS;
		    }
		}else{
			ActionErrors errors = new ActionErrors();
			if (msg == null)
			{
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.roles.found"));
			}
			saveErrors(aRequest, errors);
		}	
		return aMapping.findForward(forward);
   }
}
