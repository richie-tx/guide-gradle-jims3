//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\officer\\transactions\\ValidateOfficerProfileCommand.java

package pd.contact.officer.transactions;

import java.util.Iterator;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.officer.GetOfficerProfilesEvent;
import messaging.officer.ValidateOfficerProfileEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.contact.PDContactHelper;
import pd.contact.officer.OfficerProfile;
import pd.contact.officer.PDOfficerProfileHelper;

/**
 * 
 * 
 * @author mchowdhury
 * @description delete an agency  
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class ValidateOfficerProfileCommand implements ICommand
{
	/**
	 * @roseuid 42E67C2803BA
	 */
	public ValidateOfficerProfileCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42E65EA503A5
	 */
	public void execute(IEvent event)
	{
		ValidateOfficerProfileEvent validateEvent = (ValidateOfficerProfileEvent) event;
		//86318. must be in the security manager.
		/*if (validateEvent.getLogonId() != null && !(validateEvent.getLogonId().equals("")))
		{
			if (!this.checkValidUserProfile(validateEvent))
			{
				return;
			}
		}
		*/
		// check User Id
		if (validateEvent.getLogonId() != null && !(validateEvent.getLogonId().equals("")))
		{
			if (this.checkUserId(validateEvent))
			{
				return;
			}
		}

		// check badge number within department. In this scenerio we are only sending departmentId 
		// and badgeNumber, remaining info will be set to empty String
		String idNumber = validateEvent.getOtherIdNum();
		validateEvent.setLogonId("");

		if (validateEvent.getBadgeNum() != null && !(validateEvent.getBadgeNum().equals("")))
		{
			validateEvent.setOtherIdNum("");
			if (this.checkBadgeNumber(validateEvent))
			{
				return;
			}
		}

		// check ID number within department
		if (idNumber!= null && !(idNumber.equals("")))
		{
			validateEvent.setBadgeNum("");
			validateEvent.setOtherIdNum(idNumber);
			this.checkOtherIdNumber(validateEvent);
		}
	}

	/**
	 * @param validateEvent
	 * @return boolean true/false
	 */
	private boolean checkOtherIdNumber(ValidateOfficerProfileEvent validateEvent)
	{
		Iterator<OfficerProfile> iter = OfficerProfile.findAll(validateEvent);
		while (iter.hasNext())
		{
			iter.next();
			PDContactHelper.sendDuplicateRecordErrorResponseEvent("Other ID Number " + validateEvent.getOtherIdNum());
			return true;
		}
		return false;
	}

	/**
	 * @param validateEvent
	 * @return boolean true/false
	 */
	private boolean checkBadgeNumber(ValidateOfficerProfileEvent validateEvent)
	{
		Iterator<OfficerProfile> iter = OfficerProfile.findAll(validateEvent);
		while (iter.hasNext())
		{
			iter.next();
			PDContactHelper.sendDuplicateRecordErrorResponseEvent("Badge Number " + validateEvent.getBadgeNum());
			return true;
		}
		return false;
	}

	/**
	 * @param validateEvent
	 * @return boolean true/false
	 */
	private boolean checkUserId(ValidateOfficerProfileEvent validateEvent)
	{
		GetOfficerProfilesEvent event = new GetOfficerProfilesEvent();
		event.setUserID(validateEvent.getLogonId().toUpperCase());
		event.setStatus("A");
		Iterator<OfficerProfile> iter = OfficerProfile.findAll(event);
		while (iter.hasNext())
		{
			OfficerProfile officerProfile = (OfficerProfile) iter.next();
			OfficerProfileResponseEvent responseEvent = PDOfficerProfileHelper.getThinOfficerProfileResponseEvent(officerProfile);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(responseEvent);
			return true;
		}
		//UserProfile user2 = UserProfile.find(validateEvent.getLogonId());
		
//		if (user2 != null && !user2.getGenericUserTypeId().equals("N")&& !user2.getGenericUserTypeId().equals(""))
//		{
//			OfficerUpdateErrorResponseEvent errorEvent = new OfficerUpdateErrorResponseEvent();
//			errorEvent.setTopic(PDOfficerProfileConstants.OFFICER_PROFILE_ERROR_EVENT_TOPIC);
//			errorEvent.setMessage("Officer Profile LogonId Cannot be Generic");
//			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
//			dispatch.postEvent(errorEvent);
//
//			dispatch = EventManager.getSharedInstance(EventManager.REPLY);
//			dispatch.postEvent(errorEvent);
//			return true;
//		}
		return false;
	}
	
	/**
	 * 86318
	 * @param validateEvent
	 * @return
	 */
/*	private boolean checkValidUserProfile(ValidateOfficerProfileEvent validateEvent) {
		GetUserProfilesEvent userEvent = new GetUserProfilesEvent();
		userEvent.setLogonId(validateEvent.getLogonId());
		userEvent.setDepartmentId(validateEvent.getDepartmentId());
		userEvent.setUserStatus("A");
		Iterator iter = UserProfile.findAll(userEvent);
		if(iter.hasNext()){
			return true;
		}else{
			PDContactHelper.sendUserNotFoundErrorResponseEvent(validateEvent,"error.officer.ActiveUserInDepartmentDoesNotExist");
			return false;
		}
	}*/

	/**
	 * @param event
	 * @roseuid 42E65EA503A7
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42E65EA503AE
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42E65EA503B0
	 */
	public void update(Object anObject)
	{

	}
}