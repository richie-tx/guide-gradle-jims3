//Source file: C:\\views\\Security\\app\\src\\ui\\security\\inquiries\\action\\DisplaySecurityInquiriesUserGroupDetailsAction.java

package ui.security.inquiries.action;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.inquiries.GetUserGroupSecurityInfoEvent;
import messaging.security.inquiries.reply.UserGroupSecurityInfoResponseEvent;
import messaging.security.reply.RoleResponseEvent;
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

public class DisplaySecurityInquiriesUserGroupDetailsAction extends Action
{
   
   /**
    * @roseuid 44E9D1DE0129
    */
   public DisplaySecurityInquiriesUserGroupDetailsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 44D221880280
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
			String groupId = aRequest.getParameter("groupId");
			securityInquiriesForm.setUserGroupId(groupId);
		} 		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetUserGroupSecurityInfoEvent ugEvent =
			(GetUserGroupSecurityInfoEvent) EventFactory.getInstance(InquiriesAdminControllerServiceNames.GETUSERGROUPSECURITYINFO);
		ugEvent.setUserGroupId(securityInquiriesForm.getUserGroupId());
		dispatch.postEvent(ugEvent);
		CompositeResponse replyEvent = (CompositeResponse) dispatch.getReply();
		
		UserGroupSecurityInfoResponseEvent ugRespEvent = (UserGroupSecurityInfoResponseEvent) MessageUtil.filterComposite(replyEvent, UserGroupSecurityInfoResponseEvent.class);
		if (ugRespEvent != null)
		{
			forward = UIConstants.SUCCESS;
			securityInquiriesForm.setUserGroupName(ugRespEvent.getName());
			securityInquiriesForm.setUserGroupDescription(ugRespEvent.getDescription());
			securityInquiriesForm.setUserGroupAgencyName(ugRespEvent.getAgencyName());
			securityInquiriesForm.setRoles(SecurityUIHelper.sortRoleNames(ugRespEvent.getRoles()));			
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
			Collection users = ugRespEvent.getUsers();
			if (users != null){
				Collections.sort((List) users);
				securityInquiriesForm.setIndividualUsers(users);
			}	
		} else {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.user.group.found"));
			saveErrors(aRequest, errors);
		}
	  	return aMapping.findForward(forward);
   }
}
