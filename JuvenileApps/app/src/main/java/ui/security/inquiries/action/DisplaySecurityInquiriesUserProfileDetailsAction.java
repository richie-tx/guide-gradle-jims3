//Source file: C:\\views\\Security\\app\\src\\ui\\security\\inquiries\\action\\DisplaySecurityInquiriesUserProfileDetailsAction.java

package ui.security.inquiries.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.inquiries.GetUserSecurityInfoEvent;
import messaging.security.inquiries.reply.UserSecurityInfoResponseEvent;
import messaging.security.reply.RoleResponseEvent;
import messaging.security.reply.UserGroupResponseEvent;
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
public class DisplaySecurityInquiriesUserProfileDetailsAction extends Action
{
	
   /**
    * @roseuid 44E9D1DE01C5
    */
   public DisplaySecurityInquiriesUserProfileDetailsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 44D221810049
    */
   public ActionForward execute(
   		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
		String forward = UIConstants.FAILURE;
		SecurityInquiriesForm securityInquiriesForm = (SecurityInquiriesForm) aForm; 
		securityInquiriesForm.setAction("");
		String action = aRequest.getParameter("action");
		if (action != null){
			securityInquiriesForm.setAction(action);
		} 
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetUserSecurityInfoEvent ugEvent =
			(GetUserSecurityInfoEvent) EventFactory.getInstance(InquiriesAdminControllerServiceNames.GETUSERSECURITYINFO);
		ugEvent.setLogonId(securityInquiriesForm.getLogonId());
		dispatch.postEvent(ugEvent);
		CompositeResponse replyEvent = (CompositeResponse) dispatch.getReply();
		
		UserSecurityInfoResponseEvent uRespEvent = (UserSecurityInfoResponseEvent) MessageUtil.filterComposite(replyEvent, UserSecurityInfoResponseEvent.class);
		if (uRespEvent != null)
		{
			forward = UIConstants.SUCCESS;
			securityInquiriesForm.setFormattedName(uRespEvent.getFormattedName());
			securityInquiriesForm.setLogonId(uRespEvent.getLogonId());
			//securityInquiriesForm.setJims2LogonId(uRespEvent.getJims2LogonId()); 79250
			securityInquiriesForm.setAgencyId(uRespEvent.getAgencyId());
			securityInquiriesForm.setDepartmentName(uRespEvent.getDepartmentName());
			securityInquiriesForm.setDepartmentId(uRespEvent.getDepartmentId());
//			securityInquiriesForm.setUserGroups(SecurityUIHelper.sortUserGroups(uRespEvent.getUserGroups()));
			securityInquiriesForm.setServiceProviderName(uRespEvent.getServiceProviderName());
			List wrkList = new ArrayList();
			Collection userGroups = SecurityUIHelper.sortUserGroups(uRespEvent.getUserGroups());
			if (userGroups != null){
				Iterator iterUG = userGroups.iterator();
				while (iterUG.hasNext()){
					UserGroupResponseEvent ugResp = (UserGroupResponseEvent) iterUG.next();
					// 08/15/2013  defect 75959 - remove inactive user group from results
					if ("A".equalsIgnoreCase(ugResp.getStatusId() ) ) {
						Collection ugRoles = ugResp.getRoles();	
						if (ugRoles != null){
							Collections.sort((List) ugRoles);
							Iterator iterUGRoles = ugRoles.iterator();	
							while (iterUGRoles.hasNext()){
								RoleResponseEvent roleResp = (RoleResponseEvent) iterUGRoles.next();
								Collection features = roleResp.getFeatures();
								if (features != null){
									Collections.sort((List) features);
								}
							}	
						}
						wrkList.add(ugResp);
					}	
				}
			}
			securityInquiriesForm.setUserGroups(wrkList);	
			securityInquiriesForm.setRoles(SecurityUIHelper.sortRoleNames(uRespEvent.getRoles()));
			Collection roles = securityInquiriesForm.getRoles();
			if (roles != null){
				Iterator iterRoles = roles.iterator();
				while (iterRoles.hasNext()){
					RoleResponseEvent roleResp = (RoleResponseEvent) iterRoles.next();
					Collection features = roleResp.getFeatures();	
					if (features != null){
						Collections.sort((List) features);
					}
				}
			}	
		} else {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.userProfile.found"));
			saveErrors(aRequest, errors);
		}
	  	return aMapping.findForward(forward);
   }
}
