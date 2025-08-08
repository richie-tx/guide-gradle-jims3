// Source file:
// C:\\views\\MSA\\app\\src\\pd\\contact\\user\\transactions\\UpdateUserProfileCommand.java

package pd.contact.user.transactions;

import java.util.Iterator;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.officer.GetOfficerProfileByIdEvent;
import messaging.officer.UpdateOfficerProfileEvent;
import messaging.user.UpdateUserProfileEvent;
import messaging.user.reply.InvalidUserResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import naming.OfficerProfileControllerServiceNames;
import naming.PDContactConstants;
import naming.PDSecurityConstants;
import pd.contact.officer.OfficerProfile;
import pd.contact.user.UserProfile;
import pd.security.JIMS2Account;
import pd.security.JIMS2AccountType;
import messaging.security.reply.DuplicateRecordErrorResponseEvent;

public class UpdateUserProfileCommand implements ICommand {

	/**
	 * @roseuid 43F4F8130110
	 */
	public UpdateUserProfileCommand() {

	}

	/**
	 * @param event
	 * @roseuid 43EA4DC40092
	 */
	public void execute(IEvent event) {
		// cast the event properly
		UpdateUserProfileEvent uupe = (UpdateUserProfileEvent) event;
		UserProfile userProfile = null;
		UserResponseEvent userResponseEvent = null;

		// check for create instead of update
		if (uupe.isCreate()) {
			// Check for a custom logon ID
			if (uupe.getUvCodeGeneration()) {
				// use UV for logon prefix
				uupe.setCustomCodeGeneration(PDSecurityConstants.JUVENILE_PREFIX);
			}

			userProfile = new UserProfile();
			String message = userProfile.setDefaultCreateValues(uupe.getCustomCodeGeneration());
			if (message != null) {
				InvalidUserResponseEvent responseEvent = new InvalidUserResponseEvent();
				responseEvent.setMessage(message);
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
				dispatch.postEvent(responseEvent);
				return;
			}

			//			check for Oficer Profile create / update
			DuplicateRecordErrorResponseEvent errorEvent = null;
			if (!(uupe.getBadgeNum().equals("")) || !(uupe.getOtherIdNum().equals(""))) {

				errorEvent = updateOfficerProfile(uupe, userProfile.getLogonId());
				if (errorEvent != null) {
					IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
					dispatch.postEvent(errorEvent);
					return;
				}
			}
			//set the User Profile details
			userProfile.updateUserProfile(uupe);
			IHome home = new Home();
			home.bind(userProfile);
			//new Home().bind(userProfile);

			userResponseEvent = new UserResponseEvent();
			// return the new logonId
			userResponseEvent.setLogonId(userProfile.getLogonId());
			//Create the Officer Profile for the User
			if (!(uupe.getBadgeNum().equals("")) || !(uupe.getOtherIdNum().equals(""))) {
				if (errorEvent == null) {
					UpdateOfficerProfileEvent officerProfileEvent = (UpdateOfficerProfileEvent) EventFactory
							.getInstance(OfficerProfileControllerServiceNames.UPDATEOFFICERPROFILE);
					officerProfileEvent.setLogonId(userProfile.getLogonId());
					officerProfileEvent.setLastName(uupe.getLastName());
					officerProfileEvent.setFirstName(uupe.getFirstName());
					officerProfileEvent.setDepartmentId(uupe.getDepartmentId());
					officerProfileEvent.setOtherIdNum(uupe.getOtherIdNum());
					officerProfileEvent.setBadgeNum(uupe.getBadgeNum());
					officerProfileEvent.setMiddleName(uupe.getMiddleName());
					officerProfileEvent.setSsn(uupe.getSsn());
					officerProfileEvent.setHomePhone(uupe.getPhoneNum());
					officerProfileEvent.setEmail(uupe.getEmail());
					OfficerProfile officerProfile = new OfficerProfile();
					officerProfile.setOfficerProfile(officerProfileEvent);
					home.bind(officerProfile);
				}
			}

		} else
		// update
		{
			// lookup the user profile using the logon ID
			userProfile = UserProfile.find(uupe.getLogonId());

			// set the User Profile details
			userProfile.updateUserProfile(uupe);

			//for NonGeneric User, update the JIMS2Account record
			String userType = userProfile.getGenericUserTypeId();
			if (userType != null && userType.equals(PDContactConstants.NO)) {
				Iterator jims2AccountTypes = JIMS2AccountType.findAll(PDContactConstants.LOGON_ID, uupe.getLogonId());
				while (jims2AccountTypes.hasNext()) {
					JIMS2AccountType j2Account = (JIMS2AccountType) jims2AccountTypes.next();
					String acctId = j2Account.getJIMS2AccountId();
					JIMS2Account j2acct = JIMS2Account.find(acctId);
					if (j2acct != null) {
						j2acct.setLastName(uupe.getLastName());
						j2acct.setFirstName(uupe.getFirstName());
						j2acct.setMiddleName(uupe.getMiddleName());
						//inactivate the JIMS2Account record
						if (userProfile.getUserStatus() != null
								&& userProfile.getUserStatus().equals(PDContactConstants.INACTIVE)) {
							j2acct.setStatus(PDContactConstants.INACTIVE);
						}
					}
				}
			}
		}

		if (userResponseEvent != null) {
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(userResponseEvent);
		}
	}

	/**
	 * @param event
	 * @roseuid 43EA4DC40094
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 43EA4DC4009D
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param updateObject
	 * @roseuid 43EA4DC400A7
	 */
	public void update(Object updateObject) {

	}

	private DuplicateRecordErrorResponseEvent updateOfficerProfile(UpdateUserProfileEvent uupe, String logonId) {
		// check for existing Officer Profile first
		GetOfficerProfileByIdEvent getOfficerEvent = (GetOfficerProfileByIdEvent) EventFactory
				.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILEBYID);
		getOfficerEvent.setBadgeNum(uupe.getBadgeNum());
		getOfficerEvent.setOtherIdNum(uupe.getOtherIdNum());
		getOfficerEvent.setDepartmentId(uupe.getDepartmentId());
		Iterator iter = OfficerProfile.findAll(getOfficerEvent);
		//OfficerProfile officerProfile =
		// (OfficerProfile)OfficerProfile.findAll(getOfficerEvent).next();
		//OfficerProfileResponseEvent officerProfileResponseEvent=null;
		//IDispatch
		// dispatch=EventManager.getSharedInstance(EventManager.REPLY);
		OfficerProfile officerProfile = null;
		while (iter.hasNext()) {
			officerProfile = (OfficerProfile) iter.next();
		}

		//if the officer profile exists throw duplicate record error else
		// create a new one
		if (officerProfile != null) {
			//PDOfficerProfileHelper.setOfficerProfile(officerProfile,
			// officerProfileEvent);
			//throw a duplicate record error
			DuplicateRecordErrorResponseEvent errorEvent = new DuplicateRecordErrorResponseEvent();
			if (!uupe.getOtherIdNum().equals(""))
				errorEvent.setMessage("Other ID Number already exists.");
			if (!uupe.getBadgeNum().equals(""))
				errorEvent.setMessage("Badge Number already exists.");
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(errorEvent);
			return errorEvent;
		} else
			return null;
		//officerProfileEvent.setOfficerProfileId(officerProfile.getOfficerProfileId());

	}
}
