//Source file: C:\\views\\MSA\\app\\src\\pd\\contact\\user\\transactions\\ValidateUserCreateUpdateRequirementsCommand.java

package pd.contact.user.transactions;

import java.util.Date;

import pd.contact.user.UserProfile;
import messaging.user.GetMatchingUserProfilesEvent;
import messaging.user.ValidateUserCreateUpdateRequirementsEvent;
import messaging.user.reply.DuplicateUserResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import naming.PDSecurityConstants;
import naming.UserControllerServiceNames;


public class ValidateUserCreateUpdateRequirementsCommand implements ICommand
{

	/**
	 * 
	 */
	public ValidateUserCreateUpdateRequirementsCommand()
	{

	}

	/**
	 * @param event
	 */
	public void execute(IEvent event)
	{
		ValidateUserCreateUpdateRequirementsEvent validateEvent = (ValidateUserCreateUpdateRequirementsEvent) event;
		String logonId = validateEvent.getLogonId();
		String lastName = validateEvent.getLastName();
		String firstName = validateEvent.getFirstName();
		String middleName = validateEvent.getMiddleName();
		Date dateOfBirth = validateEvent.getDateOfBirth();
// 08/10/2007 publicInd removed from screen		
//		String publicInd = validateEvent.getPublicInd();
		String ssn = validateEvent.getSsn();
		String genericUserType = validateEvent.getGenericUserType();
		String customCodegenerationId = validateEvent.getCustomCodeGenerationId();
		

		// validate first
		/* String message =
			validateCreateRequirements(
				publicInd,
				genericUserType,
				lastName,
				firstName,
				departmentId,
				phoneNumber,
				dateOfBirth); */
	//	if (message == null)
	//	{
			// verify that user profile does not already exist
		//	Collection matchingProfiles =
				verifyNotAnExistingProfile(
					logonId,
//					publicInd,
					genericUserType,
					lastName,
					firstName,
					middleName,
					ssn,
					dateOfBirth,customCodegenerationId);
			/*if (!matchingProfiles.isEmpty())
			{
				// return the matching profiles in a response event
				IDispatch dispatch = getReplyDispatch();

				Iterator userProfiles = matchingProfiles.iterator();
				while (userProfiles.hasNext())
				{
					UserResponseEvent responseEvent = (UserResponseEvent) userProfiles.next();
					dispatch.postEvent(responseEvent);
					if (userProfile != null)
					{
						UserResponseEvent responseEvent = new UserResponseEvent();
						// the 'true' boolean parameter is requesting a thin response for the user
						userProfile.fillUserProfile(responseEvent, true);
						dispatch.postEvent(responseEvent);
					}*/
				//}
			//}
	/*	}
		else
		{
			// send validation failed message
			IDispatch dispatch = getReplyDispatch();
			InvalidUserResponseEvent responseEvent = new InvalidUserResponseEvent();
			responseEvent.setMessage(message);
			dispatch.postEvent(responseEvent);
		}*/

	}

	/**
	 * @param event
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 */
	public void update(Object anObject)
	{

	}

	private String validateCreateRequirements(
//		boolean publicInd,
		String genericUserType,
		String lastName,
		String firstName,
		String departmentId,
		String phoneNumber,
		Date dateOfBirth)
	{
//	08/10/2007 - revised if statement for removal of publicInd 	
//		if (publicInd || !genericUserType.equals(PDSecurityConstants.NON_GENERIC_USER))		
		if (!genericUserType.equals(PDSecurityConstants.NON_GENERIC_USER))
		{
			return null;
		}
		StringBuffer errors = new StringBuffer();
		boolean valid = true;

		// required attributes are First Name, Last Name, Date of Birth, Department, and Phone Number
		if (lastName == null || lastName.equals(""))
		{
			errors.append("Last Name");
			valid = false;
		}
		if (firstName == null || firstName.equals(""))
		{
			if (!valid)
			{
				errors.append(", ");
			}
			errors.append("First Name");
			valid = false;
		}
		if (departmentId == null || departmentId.equals(""))
		{
			if (!valid)
			{
				errors.append(", ");
			}
			errors.append("Department");
			valid = false;
		}
		if (phoneNumber == null || phoneNumber.equals(""))
		{
			if (!valid)
			{
				errors.append(", ");
			}
			errors.append("Phone Number");
			valid = false;
		}
		if (dateOfBirth == null)
		{
			if (!valid)
			{
				errors.append(", and ");
			}
			errors.append("Date of Birth ");
			valid = false;
		}

		if (!valid)
		{
			errors.append(" must be provided.");
			return errors.toString();
		}
		else
		{
			return null;
		}
	}
// 08/10/2007 publicInd removed from screen
	private void verifyNotAnExistingProfile(
		String currentLogonId,
//		String publicInd,
		String genericUserType,
		String lastName,
		String firstName,
		String middleName,
		String ssn,
		Date dateOfBirth, String customCodegenerationId)
	{
		
		/*Map matchingProfiles = new HashMap();

		System.out.print("Public Ind = "+publicInd);
		System.out.print("Generic User Type = "+genericUserType);*/
		
// 08/10/2007 - revised if statement for removal of publicInd 
//		if ((publicInd.equals("N")) && (genericUserType.equals(PDSecurityConstants.NON_GENERIC_USER)))
		if (genericUserType.equals(PDSecurityConstants.NON_GENERIC_USER))			
		{
			GetMatchingUserProfilesEvent searchEvent =
				(GetMatchingUserProfilesEvent) EventFactory.getInstance(UserControllerServiceNames.GETMATCHINGUSERPROFILES);
			searchEvent.setFirstName(firstName);
			searchEvent.setLastName(lastName);
			searchEvent.setMiddleName(middleName);
			//searchEvent.setLogonId(currentLogonId);
			searchEvent.setSsn(ssn);
			searchEvent.setDateOfBirth(dateOfBirth);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(searchEvent);
			//CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			//Collection matchingUserProfiles = MessageUtil.compositeToCollection(compositeResponse, UserResponseEvent.class);
			//return matchingUserProfiles;
			/*Iterator profiles = UserProfile.findAll(searchEvent);
			while (profiles.hasNext())
			{
				UserProfile user = (UserProfile)profiles.next();
				String logonId = user.getLogonId();
				if (!logonId.equals(currentLogonId))
				{
					matchingProfiles.put(logonId, user);
				}
			}

			// Since SSN is not a required field for creation of a User Profile, check value before
			// performing the lookup
			if (ssn != null && !ssn.equals(""))
			{
				searchEvent = (GetMatchingUserProfilesEvent) EventFactory.getInstance(UserControllerServiceNames.GETMATCHINGUSERPROFILES);
				searchEvent.setSsn(ssn);
				profiles = UserProfile.findAll(searchEvent);
				while (profiles.hasNext())
				{
					UserProfile user = (UserProfile)profiles.next();
					String logonId = user.getLogonId();
					if (!logonId.equals(currentLogonId) && !matchingProfiles.containsKey(logonId))
					{
						matchingProfiles.put(logonId, user);
					}
				}
			}

			searchEvent = (GetUserProfilesEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERPROFILES);
			searchEvent.setLastName(lastName);
			searchEvent.setDateOfBirthFrom(dateOfBirth);
			searchEvent.setDateOfBirthTo(dateOfBirth);
			profiles = UserProfile.findAll(searchEvent);
			while (profiles.hasNext())
			{
				UserProfile user = (UserProfile)profiles.next();
				String logonId = user.getLogonId();
				if (!logonId.equals(currentLogonId) && !matchingProfiles.containsKey(logonId))
				{
					matchingProfiles.put(logonId, user);
				}
			}*/

		}
		
		if(customCodegenerationId != null && !customCodegenerationId.equals("")){
			UserProfile user = UserProfile.find(customCodegenerationId);
			if(user != null){
				DuplicateUserResponseEvent dup = new DuplicateUserResponseEvent();
				dup.setMessage("error.user.CustomCodeGeneration.duplicateRecord");
				dup.setLogonId(customCodegenerationId);
				IDispatch dispatch = this.getReplyDispatch();
				dispatch.postEvent(dup);
			}
		}

		//return null;
	}

	private IDispatch getReplyDispatch()
	{
		return EventManager.getSharedInstance(EventManager.REPLY);
	}

}
