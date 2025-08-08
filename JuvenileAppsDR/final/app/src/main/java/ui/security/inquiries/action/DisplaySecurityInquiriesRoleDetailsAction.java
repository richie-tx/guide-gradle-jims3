//Source file: C:\\views\\Security\\app\\src\\ui\\security\\inquiries\\action\\DisplaySecurityInquiriesRoleDetailsAction.java

package ui.security.inquiries.action;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.inquiries.GetRoleSecurityInfoEvent;
import messaging.security.inquiries.reply.RoleSecurityInfoResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.InquiriesAdminControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.security.SecurityUIHelper;
import ui.security.inquiries.form.SecurityInquiriesForm;

public class DisplaySecurityInquiriesRoleDetailsAction extends Action
{
	
   /**
    * @roseuid 44E9D1DD03B9
    */
   public DisplaySecurityInquiriesRoleDetailsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 44D2218B022A
    */
   public ActionForward execute(
   		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
   		String forward = UIConstants.FAILURE;	
   		SecurityInquiriesForm securityInquiriesForm = (SecurityInquiriesForm) aForm; 
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetRoleSecurityInfoEvent rsEvent =
			(GetRoleSecurityInfoEvent) EventFactory.getInstance(InquiriesAdminControllerServiceNames.GETROLESECURITYINFO);
		rsEvent.setRoleId(securityInquiriesForm.getRoleId());
		dispatch.postEvent(rsEvent);
		CompositeResponse replyEvent = (CompositeResponse) dispatch.getReply();
		
		RoleSecurityInfoResponseEvent respEvent = (RoleSecurityInfoResponseEvent) MessageUtil.filterComposite(replyEvent, RoleSecurityInfoResponseEvent.class);
		if (respEvent != null)
		{
			securityInquiriesForm.setAgencyName(respEvent.getAgencyName());
			securityInquiriesForm.setRoleAgencyName(respEvent.getAgencyName());
			securityInquiriesForm.setRoleName(respEvent.getRoleName());
			securityInquiriesForm.setRoleDescription(respEvent.getRoleDescription());
			securityInquiriesForm.setFeatures(SecurityUIHelper.sortFeatures(respEvent.getFeatures()));
			securityInquiriesForm.setUserGroups(SecurityUIHelper.sortUserGroups(respEvent.getUserGroups()));
			Collection users = respEvent.getUsers();
			if (users != null && !(users.isEmpty())){
				Collections.sort((List) users);
				securityInquiriesForm.setIndividualUsers(users);
			}
			forward = UIConstants.SUCCESS;
		} else {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.roles.found"));
			saveErrors(aRequest, errors);			
		}
		return aMapping.findForward(forward);
	   }

}
