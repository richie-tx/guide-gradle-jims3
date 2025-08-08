//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\agency\\transactions\\ValidateAgencyDeleteRequirementsCommand.java

package pd.contact.agency.transactions;

import java.util.Collection;
import java.util.Map;

import messaging.agency.ValidateAgencyDeleteRequirementsEvent;
import messaging.contact.agency.reply.AgencyInUseErrorResponseEvent;
import messaging.security.GetUserGroupsEvent;
import messaging.security.reply.UserGroupResponseEvent;
import messaging.user.GetUsersEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.Constraint;
import mojo.km.utilities.MessageUtil;
import naming.PDContactConstants;
import naming.SecurityAdminControllerServiceNames;
import naming.UserControllerServiceNames;
import pd.contact.agency.Agency;

/**
 * @author mchowdhury
 * @description validate an agency for delete -- duplicate check  
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class ValidateAgencyDeleteRequirementsCommand implements ICommand
{

	/**
	 * @roseuid 42C594140205
	 */
	public ValidateAgencyDeleteRequirementsCommand()
	{

	}

	/**
	* @param event
	* @roseuid 42C58F380171
	*/
	public void execute(IEvent event)
	{
		ValidateAgencyDeleteRequirementsEvent thisEvent = (ValidateAgencyDeleteRequirementsEvent) event;
		Agency agency = Agency.find(thisEvent.getAgencyId());
		if (agency != null)
		{
			// check user is present in any agencies and send error response event
			if (isUsersPresent(thisEvent.getAgencyId()))
			{
				this.notifyUI("Users");
				return;
			}
			// check userGroup is present in any agencies and send error response event
			if (isUserGroupsPresent(thisEvent.getAgencyId()))
			{
				this.notifyUI("UserGroups");
			}
			//		check Role is present in any agencies and send error response event
			if (areRolesPresent(thisEvent.getAgencyId()))
			{
				this.notifyUI("Roles");
			}
		}
	}

	/**
		* @param item string
		*/
	private void notifyUI(String item)
	{
		AgencyInUseErrorResponseEvent errorEvent = new AgencyInUseErrorResponseEvent();
		errorEvent.setTopic(PDContactConstants.ERROR_VALIDATE_AGENCY_DELETE_EVENT_TOPIC);
		errorEvent.setMessage(item);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(errorEvent);
	}

	/**
	    * @param agencyId string 
	    * @return boolean true/false
	    */
	private boolean isUserGroupsPresent(String agencyId)
	{
		IDispatch dispatchUserEvent = EventManager.getSharedInstance(EventManager.REQUEST);

		boolean isUserGroupsPresent = false;
	/*	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetUserGroupsEvent ugEvent =
			(GetUserGroupsEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.GETUSERGROUPS);
		ugEvent.setAgencyId(agencyId);
		dispatch.postEvent(ugEvent);
		CompositeResponse replies = (CompositeResponse) dispatch.getReply();
		Map map = MessageUtil.groupByTopic(replies);
		MessageUtil.processReturnException(map);
		Collection userGroups = MessageUtil.compositeToCollection(replies, UserGroupResponseEvent.class);
		if (userGroups != null && userGroups.size() > 0)
		{
			isUserGroupsPresent = true;
		}*/ //87191
		return isUserGroupsPresent;
	}

	/**
	 * @param agencyId string 
	 * @return boolean true/false
	 */
	private boolean isUsersPresent(String agencyId)
	{

		boolean isUsersPresent = false;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetUsersEvent requestEvent = (GetUsersEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERS);
		requestEvent.setAgencyId(agencyId);
		dispatch.postEvent(requestEvent);

		CompositeResponse replies = (CompositeResponse) dispatch.getReply();
		Map map = MessageUtil.groupByTopic(replies);
		MessageUtil.processReturnException(map);
		Collection users = (Collection) map.get(PDContactConstants.USER_EVENT_TOPIC);
		if (users != null && users.size() > 0)
		{
			isUsersPresent = true;
		}
		return isUsersPresent;
	}

	/**
	 * @param event
	 * @roseuid 42C58F380173
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42C58F380175
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 42C594140215
	 */
	public void update(Object updateObject)
	{

	}
	private boolean areRolesPresent(String agencyId)
	{
		boolean areRolesPresent = false;
		/*Collection constraints = Constraint.findByConstrainerId(agencyId, "AGENCY", "ROLE");
		if (constraints != null && constraints.size() > 0)
		{
			areRolesPresent = true;
		}*/ //87191
		return areRolesPresent;
	}

}
