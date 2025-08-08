/*
 * Created on Aug 15, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.contact.user;

import messaging.contact.user.reply.UserResponseEvent;
import mojo.km.utilities.Name;
import naming.PDContactConstants;
import pd.common.ResponseCommonUtil;
import pd.common.ResponseCreator;
import pd.contact.agency.Department;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserProfileResponseCreatorImpl extends ResponseCommonUtil implements ResponseCreator{
     
	/**
	 * Creates UserResponseEVent from UserProfile entity
	 * @param object UserProfile
	 * @return
	 */
	public Object createThinest(Object object)
	{
		UserResponseEvent ure = new UserResponseEvent();
		UserProfile userProfile = (UserProfile) object;
		//Set the UserProfile attributes
		Name name = new Name();
		name.setFirstName(userProfile.getFirstName());
		name.setLastName(userProfile.getLastName());
		name.setMiddleName(userProfile.getMiddleName());
		ure.setFormattedName(name.getFormattedName());
		ure.setLogonId(userProfile.getLogonId());
        ure.setUserTypeId(userProfile.getUserTypeId());
        ure.setTopic(PDContactConstants.USER_EVENT_TOPIC);
		return ure;
	}
	
	public Object createThin(Object object)
	{
		UserResponseEvent ure = new UserResponseEvent();

		UserProfile userProfile = (UserProfile) object;
		//Set the UserProfile attributes
		ure.setAgencyId(userProfile.getAgencyId());
		ure.setEmail(userProfile.getEmail());
		ure.setFirstName(userProfile.getFirstName());
		ure.setGenericUserType(userProfile.getGenericUserType().getCode());
		ure.setTitle(userProfile.getTitle());
		ure.setLastName(userProfile.getLastName());
		ure.setMiddleName(userProfile.getMiddleName());
		ure.setPhoneNum(userProfile.getPhoneNum());
		ure.setPhoneExt(userProfile.getPhoneExt());
		ure.setSsn(userProfile.getSsn());
		ure.setLogonId(userProfile.getLogonId());

		//Set the Agency for the User
		if ((userProfile.getAgencyId() != null) && (!(userProfile.getAgencyId().equals(""))))
		{
			Department dept = userProfile.getDepartment();
			if (dept != null)
			{
				ure.setDepartmentId(dept.getDepartmentId());
				ure.setDepartmentName(dept.getDepartmentName());
				ure.setAgencyName(dept.getAgencyName());
			}
		}

		ure.setTopic(PDContactConstants.USER_EVENT_TOPIC);
		return ure;
	}
	
	/**
	 * Creates User response event from UserProfile entity.
	 * @param object UserProfile
	 * @return UserResponseEvent
	 */
	public Object create(Object object)
	{
		UserProfile userProfile = (UserProfile) object;
		UserResponseEvent ure = (UserResponseEvent) this.createThin(userProfile);
		//Set the UserProfile attributes
		ure.setTransferDate(userProfile.getDeptTransferDate());
		ure.setTransferTime(userProfile.getDeptTransferTime());
		ure.setComments(userProfile.getComments());
		ure.setDateOfBirth(userProfile.getDateOfBirth());
		ure.setDepartmentId(userProfile.getDepartmentId());
		ure.setTrainingInd(userProfile.getTrainingInd());
		ure.setActivationDate(userProfile.getActivationDate());
		//87191
		/*if ((userProfile.getCreateUserID() != null) && (!(userProfile.getCreateUserID().equals(""))))
		{
			String creatorId = userProfile.getCreateUserID();
			//ure.setCreate(creatorId);
			UserProfile user = UserProfile.find(creatorId);
			if (user != null)
			{
				ure.setCreatedByFirstName(user.getFirstName());
				ure.setCreatedByLastName(user.getLastName());
			}
		}*/
		ure.setCreateDate(userProfile.getCreationDate());
		if ((userProfile.getInactivatedById() != null) && (!(userProfile.getInactivatedById().equals(""))))
		{
			String deactivatorId = userProfile.getInactivatedById();
			///ure.setInactivatorId(deactivatorId);
			UserProfile user = UserProfile.find(deactivatorId);
			if (user != null)
			{
				ure.setInactivatedByFirstName(user.getFirstName());
				ure.setInactivatedByLastName(user.getLastName());
			}
		}
		ure.setInactivationDate(userProfile.getInactivationDate());
		ure.setLastLoginDate(userProfile.getLastLoginDate());
		///ure.setSuspendDate(userProfile.getSuspendDate());
		ure.setUserStatus(userProfile.getUserStatus());
		ure.setRequestorFirstName(userProfile.getRequestorFirstName());
		ure.setRequestorLastName(userProfile.getRequestorLastName());
		return ure;
	}
}
