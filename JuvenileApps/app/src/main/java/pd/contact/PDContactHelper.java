/*
 * Created on Aug 4, 2004
 */
package pd.contact;

import java.util.Collection;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.contact.agency.reply.DepartmentContactResponseEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.contact.user.reply.SecurityUserResponseEvent;
import messaging.contact.user.reply.UserNotFoundResponseEvent;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.officer.ValidateOfficerProfileEvent;
import messaging.security.reply.DuplicateRecordErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.security.UserEntityBean;
import naming.PDContactConstants;
import naming.PDSecurityConstants;
import pd.codetable.Code;
import pd.contact.agency.Agency;
import pd.contact.agency.Department;
import pd.contact.agency.DepartmentContact;
import pd.contact.user.UserProfile;
import pd.km.util.Name;

/**
 * @author glyons
 */
public final class PDContactHelper
{
	/**
	 * 
	 */
	private PDContactHelper()
	{
		super();
	}

	/**
	 * Creates thin Agency response event from entity.
	 * @param agency
	 * @param isThinResponse
	 * @return AgencyResponseEvent
	 */
	public static AgencyResponseEvent getAgencyResponseEvent(Agency agency, boolean isThinResponse)
	{
		AgencyResponseEvent are = null;

		if (isThinResponse)
		{
			are = new AgencyResponseEvent();
			are.setAgencyId(agency.getAgencyId());
			are.setAgencyName(agency.getAgencyName());
		}
		else
		{
			are = getAgencyResponseEvent(agency);
		}

		return are;
	}

	/**
	 * Creates Agency response event from entity.
	 * @param agency
	 * @return AgencyResponseEvent
	 */
	public static AgencyResponseEvent getAgencyResponseEvent(Agency agency)
	{
		AgencyResponseEvent are = new AgencyResponseEvent();
		are.setAgencyId(agency.getAgencyId());
		are.setAgencyName(agency.getAgencyName());
		if ((agency.getAgencyTypeId() != null) && (!(agency.getAgencyTypeId().equals(""))))
		{
			Code code = agency.getAgencyType();
			if (code != null)
			{
				are.setAgencyTypeId(code.getCode());
				are.setAgencyType(code.getDescription());
			}
		}
//87191		//are.setProjectAnalystInd(agency.getProjectAnalystInd());
		are.setTopic(PDContactConstants.AGENCY_EVENT_TOPIC);
		return are;
	}

	/**
	 * Creates Department response event from entity.
	 * @param department
	 * @return DepartmentResponseEvent
	 */
	public static DepartmentResponseEvent getDepartmentResponseEvent(Department department)
	{
		DepartmentResponseEvent dre = new DepartmentResponseEvent();
		dre.setAgencyId(department.getAgencyId());
		dre.setAgencyName(department.getAgencyName());
		//87191
		/*if(agencyId != null && !agencyId.equals("")){
			dre.setAgencyId(department.getAgencyId());
			Agency agency = department.getAgency();//Agency.find(agencyId); //87191
			if(agency != null){
				dre.setAgencyName(agency.getAgencyName());
			}
		}*/
		dre.setDepartmentId(department.getDepartmentId());
		dre.setDepartmentName(department.getDepartmentName());
		dre.setOrgCode(department.getOrgCode());
		dre.setLabelInd(department.getLabelInd());
		dre.setComments(department.getComments());
		dre.setGritsInd(department.getGritsInd());
		dre.setCreateOfficerProfileInd(department.getCreateOfficerProfileInd());
		if (department.getInUseInd() != null && department.getInUseInd().equalsIgnoreCase("Y"))
		{
			dre.setInUse(true);
		}
		else
		{
			dre.setInUse(false);
		}
		Code code = null;
		// Agency Status
		if ((department.getStatusId() != null) && (!(department.getStatusId().equals(""))))
		{
			code = department.getStatus();
			if (code != null)
			{
				dre.setDepartmentStatusId(code.getCode());
				dre.setDepartmentStatus(code.getDescription());
			}
		}

		if ((department.getCountyId() != null) && (!(department.getCountyId().equals(""))))
		{
			code = department.getCounty();
			if (code != null)
			{
				dre.setCountyId(code.getCode());
				dre.setCountyName(code.getDescription());
			}
		}

		dre.setOriginatingAgencyId(department.getOriginatingAgencyId());
		// Agency Type
		if ((department.getAgencyTypeId() != null) && (!(department.getAgencyTypeId().equals(""))))
		{
			code = department.getAgencyType();
			if (code != null)
			{
				dre.setAgencyTypeId(code.getCode());
				dre.setAgencyType(code.getDescription());
			}
		}
		if ((department.getAccessTypeId() != null) && (!(department.getAccessTypeId().equals(""))))
		{
			code = department.getAccessType();
			if (code != null)
			{
				dre.setAccessTypeId(code.getCode());
				dre.setAccessType(code.getDescription());
			}
		}
		if (department.getFax() != null)
		{
			dre.setFax(department.getFax());
		}
		if (department.getActivationDate() != null)
		{
			dre.setActivationDate(department.getActivationDate());
		}
		if (department.getTerminationDate() != null)
		{
			dre.setTerminationDate(department.getTerminationDate());
		}
		if (department.getOriginatingAgencyId() != null)
		{
			dre.setOriginatingAgencyId(department.getOriginatingAgencyId());
		}

		if ((department.getSetcicAccessId() != null) && (!(department.getSetcicAccessId().equals(""))))
		{
			code = department.getSetcicAccess();
			if (code != null)
			{
				dre.setSetcicAccessId(code.getCode());
				dre.setSetcicAccess(code.getDescription());
			}
		}

		if (department.getSetcicDate() != null)
		{
			dre.setSetcicDate(department.getSetcicDate());
		}

		if (department.getSetcicRenewDate() != null)
		{
			dre.setSetcicRenewDate(department.getSetcicRenewDate());
		}

		if (department.getSetcicInactiveDate() != null)
		{
			dre.setSetcicInactiveDate(department.getSetcicInactiveDate());
		}
		if (department.getWarrantConfirmationPhone() != null)
		{
			dre.setWarrantConfirmationPhone(department.getWarrantConfirmationPhone());
		}
		if (department.getWarrantConfirmationPhoneExt() != null)
		{
			dre.setWarrantConfirmationPhoneExt(department.getWarrantConfirmationPhoneExt());
		}

		// Get Subcriber Access Information
		if (department.getSubscriberCivilActivationDate() != null)
		{
			dre.setSubscriberCivilActivationDate(department.getSubscriberCivilActivationDate());
		}
		if (department.getSubscriberCriminalTerminationDate() != null)
		{
			dre.setSubscriberCivilTerminationDate(department.getSubscriberCivilTerminationDate());
		}
		if (department.getSubscriberCriminalActivationDate() != null)
		{
			dre.setSubscriberCriminalActivationDate(department.getSubscriberCriminalActivationDate());
		}
		if (department.getSubscriberCriminalTerminationDate() != null)
		{
			dre.setSubscriberCriminalTerminationDate(department.getSubscriberCriminalTerminationDate());
		}
		dre.setTopic(dre.getAgencyDepartmentTopic());
		return dre;
	}

	/**
	  * @param department
	 * @return DepartmentResponseEvent
	 */
	public static DepartmentResponseEvent getThinDepartmentResponseEvent(Department department)
	{
		DepartmentResponseEvent dre = new DepartmentResponseEvent();

		dre.setAgencyName(department.getAgencyName());
		dre.setAgencyId(department.getAgencyId());

		dre.setDepartmentId(department.getDepartmentId());
		dre.setDepartmentName(department.getDepartmentName());
		dre.setOrgCode(department.getOrgCode());
		dre.setOriginatingAgencyId(department.getOriginatingAgencyId());

		String setcicAccessId = department.getSetcicAccessId();
		if (setcicAccessId != null && setcicAccessId.equals("") == false)
		{
			dre.setSetcicAccessId(department.getSetcicAccessId());
			dre.setSetcicAccess(department.getSetcicAccess().getDescription());
		}

		String status = department.getStatusId();
		if (status != null && status.equals("") == false)
		{
			dre.setStatusId(status);
			dre.setStatus(department.getStatus().getDescription());
		}

		if (department.getInUseInd() != null && department.getInUseInd().equalsIgnoreCase("Y"))
		{
			dre.setInUse(true);
		}
		else
		{
			dre.setInUse(false);
		}

		dre.setTopic(dre.getAgencyDepartmentTopic());
		return dre;
	}

	/**
	 * Used to return simple AgencyResponseEvents that do not return the Code relationship
	 * values.  For example, it would be used for an Agency Drop Down.
	 * @param agency
	 * @return AgencyResponseEvent
	 */
	public static AgencyResponseEvent getThinAgencyResponseEvent(Agency agency)
	{
		AgencyResponseEvent are = new AgencyResponseEvent();
		are.setAgencyId(agency.getAgencyId());
		are.setAgencyName(agency.getAgencyName());
		return are;
	}

	/**
	 * Creates UserResponseEVent from UserProfile entity
	 * @param userProfile
	 * @return
	 */
	public static UserResponseEvent getThinUserResponseEvent(UserProfile userProfile)
	{
		UserResponseEvent ure = new UserResponseEvent();

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
	 * @param userProfile
	 * @return UserResponseEvent
	 */
	public static UserResponseEvent getUserResponseEvent(UserProfile userProfile)
	{
		UserResponseEvent ure = new UserResponseEvent();

		//Set the UserProfile attributes
		ure.setAgencyId(userProfile.getAgencyId());
		ure.setTransferDate(userProfile.getDeptTransferDate());
		ure.setTransferTime(userProfile.getDeptTransferTime());
		ure.setComments(userProfile.getComments());
		ure.setDateOfBirth(userProfile.getDateOfBirth());
		ure.setDepartmentId(userProfile.getDepartmentId());
		ure.setEmail(userProfile.getEmail());
		ure.setFirstName(userProfile.getFirstName());
		ure.setGenericUserType(userProfile.getGenericUserType().getCode());
		ure.setTitle(userProfile.getTitle());
		ure.setLastName(userProfile.getLastName());
		ure.setMiddleName(userProfile.getMiddleName());
		ure.setPhoneNum(userProfile.getPhoneNum());
		ure.setPhoneExt(userProfile.getPhoneExt());
		ure.setSsn(userProfile.getSsn());
		ure.setTrainingInd(userProfile.getTrainingInd());
		ure.setLogonId(userProfile.getLogonId());

		//Set the Agency for the User
		if ((userProfile.getDepartmentId() != null) && (!(userProfile.getDepartmentId().equals(""))))
		{
			Department dept = userProfile.getDepartment();
			if (dept != null)
			{
				ure.setDepartmentId(dept.getDepartmentId());
				ure.setDepartmentName(dept.getDepartmentName());
				ure.setAgencyName(dept.getAgencyName());
			}
		}

		ure.setActivationDate(userProfile.getActivationDate());
		//87191
	/*	if ((userProfile.getCreateUserID() != null) && (!(userProfile.getCreateUserID().equals(""))))
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
		ure.setTopic(PDContactConstants.USER_EVENT_TOPIC);
		return ure;
	}

	/**
	 * Creates User response event from UserProfile entity.
	 * @param userProfile
	 * @return UserResponseEvent
	 */
    public static SecurityUserResponseEvent getSecurityUserResponseEvent(UserProfile userProfile)
    {
	SecurityUserResponseEvent ure = new SecurityUserResponseEvent();
	if (userProfile != null)
	{
	    ure.setAgencyId(userProfile.getAgencyId());
	    ure.setAgencyName(userProfile.getAgencyName());
	    ure.setDepartmentName(userProfile.getDepartmentName());
	    ure.setDepartmentId(userProfile.getDepartmentId());
	    ure.setFirstName(userProfile.getFirstName());
	    ure.setLastName(userProfile.getLastName());
	    ure.setLogonId(userProfile.getLogonId());
	    ure.setMiddleName("");
	    //87191
	    //ure.setAgencyName(userProfile.getAgencyName());
	    //ure.setUserTypeId(userProfile.getUserTypeId());//check with latha
	    //ure.setHomePhoneNum(userProfile.getHomePhoneNum());
	   // ure.setPhoneExt(userProfile.getPhoneExt());
	    ure.setPhoneNum(userProfile.getPhoneNum());
	    //ure.setCellPhone(userProfile.getCellPhone());
	    //ure.setPager(userProfile.getPager());
	    //ure.setFaxLocation(userProfile.getFaxLocation());
	    ure.setEmail(userProfile.getEmail());
	    ure.setWorkPhoneNum(userProfile.getWorkPhoneNum());
	    //ure.setFaxNum(userProfile.getFaxNum());
	    //ure.setEmail(userProfile.getEmail());
	    //ure.setSsn(userProfile.getSsn());
	    //ure.setTopic(PDContactConstants.USER_EVENT_TOPIC);

	}
	return ure;

    }

	/**
	 * Creates response event from UserHistory entity.
	 * @param userHistory
	 * @return UserHistoryResponseEvent
	 */
	 // no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.
	/*public static UserHistoryResponseEvent getUserHistoryResponseEvent(UserHistory userHistory)
	{
		UserHistoryResponseEvent uhre = new UserHistoryResponseEvent();
		uhre.setAction(userHistory.getActionInd());
		uhre.setChangeTime(userHistory.getChangeTime());
		uhre.setFieldName(userHistory.getFieldName());
		uhre.setNewValue(userHistory.getNewValue());
		uhre.setOldValue(userHistory.getOldValue());
		uhre.setTransaction(userHistory.getTransaction());
		uhre.setChangedByLogonId(userHistory.getTransactionLogonId());
		uhre.setTransactionDate(userHistory.getTransactionDate());

		if ((userHistory.getTransactionLogonId() != null) && (!(userHistory.getTransactionLogonId().equals(""))))
		{
			if ((userHistory.getTransactionUser() != null) && (!(userHistory.getTransactionUser().equals(""))))
			{
				uhre.setChangedByUserFirstName(userHistory.getTransactionUser().getFirstName());
				uhre.setChangedByUserLastName(userHistory.getTransactionUser().getLastName());
			}
		}
		/// Don't need this -- uhre.setUserHistoryId(userHistory.getUserHistoryId());
		uhre.setLogonId(userHistory.getLogonId());
		uhre.setTransferDate(userHistory.getTransferDate());
		//uhre.setTransferTime(userHistory.getTransferTime());
		uhre.setTopic(PDContactConstants.USER_HISTORY_EVENT_TOPIC);
		return uhre;
	}*/

	/**
	 * @param item String 
	 */
	public static void sendDuplicateRecordErrorResponseEvent(String item)
	{
		DuplicateRecordErrorResponseEvent errorEvent = new DuplicateRecordErrorResponseEvent();
		errorEvent.setMessage(item + " already exists.");
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(errorEvent);
	}
	
	/**
	 * @param departmentContact
	 * @return DepartmentContactResponseEvent
	 */
	public static DepartmentContactResponseEvent getDepartmentContactResponseEvent(DepartmentContact departmentContact)
	{
		DepartmentContactResponseEvent cRE = null;
		if (departmentContact != null)
		{
			cRE = new DepartmentContactResponseEvent();
			cRE.setDepartmentId(departmentContact.getDepartmentId());
			cRE.setFirstName(departmentContact.getFirstName());
			cRE.setLastName(departmentContact.getLastName());
			cRE.setMiddleName(departmentContact.getMiddleName());
			cRE.setTitle(departmentContact.getTitle());
			cRE.setUserId(departmentContact.getLogonId());
			cRE.setPhone(departmentContact.getPhoneNum());
			cRE.setPhoneExt(departmentContact.getPhoneExt());
			cRE.setLiasonTrainingInd(departmentContact.getLiaisonTrainingInd());
			if (departmentContact.getLiaisonTrainingInd() != null
				&& departmentContact.getLiaisonTrainingInd().equals(PDSecurityConstants.Y))
			{
				cRE.setLiasonTraining(PDSecurityConstants.YES);
			}
			else
			{
				cRE.setLiasonTraining(PDSecurityConstants.NO);
			}
			cRE.setPrimaryContact(departmentContact.getPrimaryContact());
			cRE.setEmail(departmentContact.getEmail());
			cRE.setContactTypeId(departmentContact.getContactTypeId());
			cRE.setContactId(departmentContact.getContactId());
		}
		return cRE;
	}

	/**
	 * @param department
	 */
	public static void postDepartment(Department department)
	{
		if (department != null)
		{
			DepartmentResponseEvent departmentResponseEvent = PDContactHelper.getDepartmentResponseEvent(department);

			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			if (departmentResponseEvent != null)
			{
				departmentResponseEvent.setTopic(PDSecurityConstants.DEPARTMENT_EVENT_TOPIC);
			}
			dispatch.postEvent(departmentResponseEvent);
		}
	}

	/**
	 * @param collection
	 */
	public static void postDepartmentContactList(Collection contacts)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		if (contacts != null && contacts.size() > 0)
		{
			Iterator contactsIter = contacts.iterator();
			while (contactsIter.hasNext())
			{
				DepartmentContact contact = (DepartmentContact) contactsIter.next();
				if (contact != null)
				{
					DepartmentContactResponseEvent contactResponseEvent =
						PDContactHelper.getDepartmentContactResponseEvent(contact);
					if (contactResponseEvent.getContactTypeId() != null
						&& contactResponseEvent.getContactTypeId().equals(
							PDSecurityConstants.DEAPRTMENT_SETCIC_CONTACTTYPE))
					{
						contactResponseEvent.setTopic(
							PDSecurityConstants.DEPARTMENT_SETCIC_CONTACT_LISTITEM_EVENT_TOPIC);
					}
					else
					{
						contactResponseEvent.setTopic(PDSecurityConstants.DEPARTMENT_CONTACT_LISTITEM_EVENT_TOPIC);
					}
					dispatch.postEvent(contactResponseEvent);
				}
			}
		}
	}

	/**
	 * @param contactRequestEvent
	 * @param departmentContact
	 * @return DepartmentContact
	 */
	 //87191
/*	public static DepartmentContact getDepartmentContact(
		UpdateContactEvent contactRequestEvent,
		DepartmentContact departmentContact)
	{
		departmentContact.setLastName(contactRequestEvent.getLastName());
		departmentContact.setFirstName(contactRequestEvent.getFirstName());
		departmentContact.setMiddleName(contactRequestEvent.getMiddleName());
		departmentContact.setTitle(contactRequestEvent.getTitle());
		departmentContact.setLogonId(contactRequestEvent.getLogonId());
		departmentContact.setPhoneNum(contactRequestEvent.getPhone());
		departmentContact.setPhoneExt(contactRequestEvent.getPhoneExt());
		departmentContact.setEmail(contactRequestEvent.getEmail());
		departmentContact.setLiaisonTrainingInd(contactRequestEvent.getLiaisonTrainingInd());
		departmentContact.setContactId(contactRequestEvent.getContactId());
		departmentContact.setContactTypeId(contactRequestEvent.getContactTypeId());
		departmentContact.setDepartmentId(contactRequestEvent.getDepartmentId());
		departmentContact.setPrimaryContact(contactRequestEvent.getPrimaryContact());
		return departmentContact;
	}*/

	/**
	 * 87191
	 * @param users
	 * @return
	 */
    public static Iterator<UserEntityBean> sortedByUserName(UserEntityBean user)
    {

	SortedMap<String, UserEntityBean> map = new TreeMap<String, UserEntityBean>();
	if (user != null)
	{
	    String lastName = (user.getLastname() == null) ? "" : user.getLastname();
	    String firstName = (user.getFirstname() == null) ? "" : user.getFirstname();
	    String middileName = "";
	    String loginId = (user.getUsername() == null) ? "" : user.getUsername();
	    map.put(lastName + firstName + middileName + loginId, user);
	}

	if (map != null && !(map.isEmpty()))
	{
	    return map.values().iterator();
	}
	return null;

    }
    
    //87191
/*    public static Iterator sortedByUserName(Iterator users)
	{
		if(users == null){
			return users;
		}
		SortedMap map = new TreeMap();
		while (users.hasNext())
		{
			User user = (User) users.next();
			if(user != null){
				String lastName = (user.getLastName() == null)?"":user.getLastName();
				String firstName = (user.getFirstName() == null)?"":user.getFirstName();
				String middileName = (user.getMiddleName() == null)?"":user.getMiddleName();
				String loginId = (user.getJIMSLogonId() == null)?"":user.getJIMSLogonId();
				map.put(lastName + firstName + middileName+loginId,user);
			}
		}
		if (map != null && !(map.isEmpty()))
		{
			return map.values().iterator();
		}
		else
		{
			return users;
		}
	}
*/

	/**
	 * @param validateOfficerProfileEvent
	 * String key
	 */
	public static void sendUserNotFoundErrorResponseEvent(ValidateOfficerProfileEvent validateOfficerProfileEvent, String key) {
		UserNotFoundResponseEvent errorEvent = new UserNotFoundResponseEvent();
		errorEvent.setDepartmentId(validateOfficerProfileEvent.getDepartmentId());
		errorEvent.setUserId(validateOfficerProfileEvent.getLogonId());
		errorEvent.setErrorKey(key);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(errorEvent);
	}
	
	/**
	 * Creates UserResponseEVent from UserProfile entity
	 * @param userProfile
	 * @return
	 */
	public static String getUserProfileName(String userLogonId)
	{
		UserProfile userProfile = UserProfile.find(userLogonId);
		if (userProfile != null) {
			Name fullName = new Name(userProfile.getFirstName(),userProfile.getMiddleName(),userProfile.getLastName());
			String userProfileName = fullName.getFormattedName();	
			return userProfileName;
		} else {
			return "";
		}
	}
}
