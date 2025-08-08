//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilewarrant\\transactions\\ValidateOfficerCommand.java

package pd.contact.officer.transactions;

import java.util.Iterator;
import pd.contact.officer.OfficerProfile;
import pd.contact.officer.PDOfficerProfileHelper;
import naming.OfficerProfileControllerServiceNames;
import naming.PDOfficerProfileConstants;
import naming.UserControllerServiceNames;
import messaging.contact.officer.reply.OfficerNotFoundResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.officer.GetOfficerProfilesEvent;
import messaging.officer.ValidateOfficerOrUserEvent;
import messaging.user.GetUserProfileEvent;
import messaging.user.reply.InvalidUserResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;

/**
 * @author dgibler
 *
 */
public class ValidateOfficerOrUserCommand implements ICommand
{
	 
	 

	/**
	 * @roseuid 416C383B014E
	 */
	public ValidateOfficerOrUserCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 41659D76039B
	 */
	public void execute(IEvent event)
	{   
		IDispatch requestDispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		IDispatch replyDispatch = EventManager.getSharedInstance(EventManager.REPLY);

		ValidateOfficerOrUserEvent validateEvent = (ValidateOfficerOrUserEvent) event;

		OfficerProfileResponseEvent officerProfileResponseEvent = this.fetchOfficerProfile(validateEvent);

		if (officerProfileResponseEvent == null)
		{
			String logonId = validateEvent.getLogonId();
			if (logonId != null && !logonId.equals(""))
			{
				UserResponseEvent userResponseEvent = this.fetchUserProfile(validateEvent,requestDispatch,replyDispatch);
				if (userResponseEvent != null)
				{
					replyDispatch.postEvent(userResponseEvent);
				}
				else
				{
					InvalidUserResponseEvent invalidUser = new InvalidUserResponseEvent();
					replyDispatch.postEvent(invalidUser);
				}
			}
			else
			{
				OfficerNotFoundResponseEvent officerNotFound = new OfficerNotFoundResponseEvent();
				replyDispatch.postEvent(officerNotFound);
			}
		}
		else
		{
			replyDispatch.postEvent(officerProfileResponseEvent);
		}

	}

	/**
	 * @param validateEvent
	 * @return
	 */
	private UserResponseEvent fetchUserProfile(ValidateOfficerOrUserEvent validateEvent, IDispatch requestDispatch, IDispatch replyDispatch)
	{
		 
		GetUserProfileEvent getUserProfileEvent =
			(GetUserProfileEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERPROFILE);

		getUserProfileEvent.setLogonId(validateEvent.getLogonId());
		getUserProfileEvent.setThinResponseInd(true);
		requestDispatch.postEvent(getUserProfileEvent);

		CompositeResponse responses = (CompositeResponse) requestDispatch.getReply();
		UserResponseEvent userResponse = (UserResponseEvent) MessageUtil.filterComposite(responses, UserResponseEvent.class);
		
		if (userResponse != null)
		{
			replyDispatch.postEvent(userResponse);
		}

		return userResponse;
	}

	/**
	 * @param validateEvent
	 * @return
	 */
	private OfficerProfileResponseEvent fetchOfficerProfile(ValidateOfficerOrUserEvent validateEvent)
	{
		Iterator i = null;
		String logonId = validateEvent.getLogonId();

		if (logonId == null || logonId.trim().equals(""))
		{
			GetOfficerProfilesEvent getOfficerProfilesEvent =
				(GetOfficerProfilesEvent) EventFactory.getInstance(
					OfficerProfileControllerServiceNames.GETOFFICERPROFILES);
			getOfficerProfilesEvent.setDepartmentId(validateEvent.getDepartmentId());
			getOfficerProfilesEvent.setLogonId(validateEvent.getLogonId());

			if (validateEvent.getOfficerIdType().equalsIgnoreCase(PDOfficerProfileConstants.BADGE_NUM_ID))
			{
				getOfficerProfilesEvent.setBadgeNum(validateEvent.getOfficerId());
			}

			if (validateEvent.getOfficerIdType().equalsIgnoreCase(PDOfficerProfileConstants.OTHER_NUM_ID))
			{
				getOfficerProfilesEvent.setOtherIdNum(validateEvent.getOfficerId());
			}
			i = OfficerProfile.findAll(getOfficerProfilesEvent);
		}
		else
		{
			i = OfficerProfile.findAll("logonId", logonId.toUpperCase());
		}

		OfficerProfileResponseEvent officerProfileResponse = null;
		if (i != null && i.hasNext())
		{
			OfficerProfile officerProfile = (OfficerProfile) i.next();
			if (!officerProfile.getStatusId().equals("I") && !officerProfile.getStatusId().equals("INACTIVE"))
			{
			    officerProfileResponse = PDOfficerProfileHelper.getThinOfficerProfileResponseEvent(officerProfile);
			}
		}
		return officerProfileResponse;
	}

	/**
	 * @param event
	 * @roseuid 41659D7603A9
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 41659D7603AB
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 41659D7603AD
	 */
	public void update(Object anObject)
	{

	}
}