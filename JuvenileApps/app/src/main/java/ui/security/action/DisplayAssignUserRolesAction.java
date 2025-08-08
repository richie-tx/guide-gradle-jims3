//Source file: C:\\views\\archproduction\\app\\src\\ui\\security\\action\\DisplayAssignUserRolesAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.contact.user.reply.SecurityUserResponseEvent;
import messaging.contact.user.reply.UserResponseEvent;
//import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.security.GetRolesForUserEvent;
import messaging.security.reply.RoleResponseEvent;
import messaging.user.GetUserProfileEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.SecurityAdminControllerServiceNames;
import naming.UIConstants;
import naming.UserControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.security.form.AssignRolesForm;

/**
 * 
 * 
 * @author awidjaja
 * @description Get the security roles of the selected User. 
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayAssignUserRolesAction extends Action
{

	/**
	 * description public constructor
	 * @roseuid 42972012039A
	 */
	public DisplayAssignUserRolesAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4294862500EE
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		AssignRolesForm assignRolesForm = (AssignRolesForm) aForm;

		//reset availableRoles to empty collection to remove previous search cache
		Collection emptyColl = new ArrayList();
		assignRolesForm.setAvailableRoles(MessageUtil.processEmptyCollection(emptyColl));

		String userId = aRequest.getParameter("userId").trim();
		assignRolesForm.setUserId(userId);	
		Collection users = assignRolesForm.getUsers();
		boolean nameFound = false;
		if(users != null && users.size() > 0 )
		{	
			Iterator iter = users.iterator();
			while (iter.hasNext())
			{
				SecurityUserResponseEvent userResponseEvent = (SecurityUserResponseEvent) iter.next();
				if (userResponseEvent.getLogonId().equalsIgnoreCase(userId))
				{
					assignRolesForm.setUserLastName(userResponseEvent.getLastName());
					assignRolesForm.setUserFirstName(userResponseEvent.getFirstName());
					assignRolesForm.setUserMiddleName(userResponseEvent.getMiddleName());
					nameFound = true;	
					break;
				}
			}
		}	
		if(users.size()==0 || nameFound == false)
		{
			GetUserProfileEvent requestEvent =
				(GetUserProfileEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERPROFILE);
			requestEvent.setLogonId(userId);
			requestEvent.setThinResponseInd(true);
			IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch1.postEvent(requestEvent);
			CompositeResponse compositeResponse1 = (CompositeResponse) dispatch1.getReply();
			//MessageUtil.processReturnException(compositeResponse);

			Object obj = MessageUtil.filterComposite(compositeResponse1, UserResponseEvent.class);
			if (obj != null)
			{
				UserResponseEvent user = (UserResponseEvent)obj;
				assignRolesForm.setUserLastName(user.getLastName());
				assignRolesForm.setUserFirstName(user.getFirstName());
				assignRolesForm.setUserMiddleName(user.getMiddleName());
			}
		}

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetRolesForUserEvent requestEvent =
			(GetRolesForUserEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.GETROLESFORUSER);
		requestEvent.setLogonId(assignRolesForm.getUserId());
		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection roles = MessageUtil.compositeToCollection(compositeResponse, RoleResponseEvent.class);
		//no need to display error if there's no roles found because it's not a user action
		assignRolesForm.setCurrentRoles(roles);
		assignRolesForm.setErrorMessage("");
		return aMapping.findForward(UIConstants.SUCCESS);
	}
}
