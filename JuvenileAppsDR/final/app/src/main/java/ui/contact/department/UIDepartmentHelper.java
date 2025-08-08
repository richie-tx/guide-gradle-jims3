package ui.contact.department;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import messaging.address.reply.AddressResponseEvent;
import messaging.agency.GetAgenciesEvent;
import messaging.agency.UpdateContactEvent;
import messaging.agency.UpdateDepartmentEvent;
import messaging.agency.ValidateDepartmentCreateRequirementsEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.contact.agency.reply.DepartmentContactResponseEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.contact.user.reply.SecurityUserResponseEvent;
import messaging.user.GetUsersEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.PDContactConstants;
import naming.PDSecurityConstants;
import naming.UIConstants;
import naming.UserControllerServiceNames;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.UIUtil;
import ui.contact.department.form.DepartmentForm;

/**
 * @author dapte
 *
 * This is a utility class for common operations required in Department actions.
 */
public final class UIDepartmentHelper
{

	/**
	 * 
	 * @param agencyName, agencyId
	 * @return Collection agencies
	 */
	public static Collection fetchThinAgencies(String agencyName, String agencyId)
	{
		/*Get the cautions out of response event and copy to form Caution*/
		GetAgenciesEvent requestEvent = (GetAgenciesEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETAGENCIES);

		requestEvent.setAgencyId(agencyId);
		requestEvent.setAgencyName(agencyName);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection agencies = (Collection) dataMap.get(PDContactConstants.AGENCY_EVENT_TOPIC);
		agencies = MessageUtil.processEmptyCollection(agencies);

		return agencies;
	}

	/**
	 * @param id, agencyIterator
	 * @return Collection agencies
	 */
	public static String getAgencyDescriptionById(String id, Iterator agencyIterator)
	{
		AgencyResponseEvent evt;
		while (agencyIterator.hasNext())
		{
			evt = (AgencyResponseEvent) agencyIterator.next();
			if (id != null && evt.getAgencyId().equals(id))
			{
				return evt.getAgencyName();
			}
		}
		return "";
	}

	/**
	 * @param officerForm
	 */
	public static void setManageDepartmentValuesFromDropDownList(DepartmentForm form)
	{

		CodeResponseEvent codeEvt = null;

		codeEvt = UIUtil.findCodeResponseEvent(form.getAccessTypes().iterator(), form.getAccessTypeId());
		if (codeEvt != null)
			form.setAccessType(codeEvt.getDescription());
		
		codeEvt = UIUtil.findCodeResponseEvent(form.getStatusTypes().iterator(), form.getStatusId());
		if (codeEvt != null)
			form.setStatus(codeEvt.getDescription());
		
		codeEvt = UIUtil.findCodeResponseEvent(form.getCountyList().iterator(), form.getCountyId());
		if (codeEvt != null)
			form.setCounty(codeEvt.getDescription());
		
		codeEvt = UIUtil.findCodeResponseEvent(form.getStateList().iterator(), form.getMailingAddress().getStateId());
		if (codeEvt != null)
			form.getMailingAddress().setState(codeEvt.getDescription());
		else
			form.getMailingAddress().setState("");
		
		codeEvt = UIUtil.findCodeResponseEvent(form.getStreetTypes().iterator(), form.getMailingAddress().getStreetTypeId());
		if (codeEvt != null)
			form.getMailingAddress().setStreetType(codeEvt.getDescription());
		else
			form.getMailingAddress().setStreetType("");
		
		codeEvt = UIUtil.findCodeResponseEvent(form.getStateList().iterator(), form.getSetcicBillingAddress().getStateId());
		if (codeEvt != null)
			form.getSetcicBillingAddress().setState(codeEvt.getDescription());
		else
			form.getSetcicBillingAddress().setState("");
		
		codeEvt = UIUtil.findCodeResponseEvent(form.getStreetTypes().iterator(), form.getSetcicBillingAddress().getStreetTypeId());
		if (codeEvt != null)
			form.getSetcicBillingAddress().setStreetType(codeEvt.getDescription());
		else
			form.getSetcicBillingAddress().setStreetType("");
		
		codeEvt = UIUtil.findCodeResponseEvent(form.getSetcicAccessTypes().iterator(), form.getSetcicAccessId());
		if (codeEvt != null)
			form.setSetcicAccess(codeEvt.getDescription());
		
		codeEvt = UIUtil.findCodeResponseEvent(form.getStateList().iterator(), form.getPhysicalAddress().getStateId());
		if (codeEvt != null)
			form.getPhysicalAddress().setState(codeEvt.getDescription());
		
		codeEvt = UIUtil.findCodeResponseEvent(form.getStreetTypes().iterator(), form.getPhysicalAddress().getStreetTypeId());
		if (codeEvt != null)
			form.getPhysicalAddress().setStreetType(codeEvt.getDescription());
	}

	/**
	 * 
	 * @param deptId, deptName, orgCode
	 * @return Collection users
	 */
	public static CompositeResponse validateDepartmentCreateRequirements(String deptId, String deptName, String orgCode)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		// Sending PD Request Event
		ValidateDepartmentCreateRequirementsEvent event =
			(ValidateDepartmentCreateRequirementsEvent) EventFactory.getInstance(
				AgencyControllerServiceNames.VALIDATEDEPARTMENTCREATEREQUIREMENTS);

		event.setDepartmentId(deptId);
		event.setDepartmentName(deptName);
		event.setOrgCode(orgCode);
		dispatch.postEvent(event);

		// Getting PD Response Event		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		return compositeResponse;

	}

	/**
	 * 
	 * @param lName, fName, userId,agencyId
	 * @return Collection users
	 */
	public static Collection fetchUsers(String lName, String fName, String userId, String agencyId)
	{
		GetUsersEvent requestEvent = (GetUsersEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERS);

		requestEvent.setFirstName(fName);
		requestEvent.setLastName(lName);
		requestEvent.setLogonId(userId);
		requestEvent.setAgencyId(agencyId);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection users = (Collection) dataMap.get(PDContactConstants.USER_EVENT_TOPIC);
		users = MessageUtil.processEmptyCollection(users);

		return users;
	}

	public static SecurityUserResponseEvent findUserByID(Collection users, String id)
	{
		if (id != null)
		{
			Iterator ite = users.iterator();
			SecurityUserResponseEvent evt = null;
			while (ite.hasNext())
			{
				evt = (SecurityUserResponseEvent) ite.next();
				if (evt.getLogonId().equalsIgnoreCase(id))
				{
					return evt;
				}
			}
		}
		return null;
	}

	public static UpdateContactEvent convertContactResponseEvent(DepartmentContactResponseEvent responseEvent)
	{
		UpdateContactEvent myContact = new UpdateContactEvent();
		if (responseEvent == null)
			return myContact;
		myContact.setContactId(responseEvent.getContactId());
		myContact.setDepartmentId(responseEvent.getDepartmentId());
		myContact.setLastName(responseEvent.getLastName());
		myContact.setFirstName(responseEvent.getFirstName());
		myContact.setMiddleName(responseEvent.getMiddleName());
		myContact.setTitle(responseEvent.getTitle());
		myContact.setLogonId(responseEvent.getUserId());
		if (responseEvent.getPhone() == null)
		{
			responseEvent.setPhone("");
		}
		PhoneNumber myPhone = new PhoneNumber(responseEvent.getPhone());
		myContact.setAreaCode(myPhone.getAreaCode());
		myContact.setPrefix(myPhone.getPrefix());
		myContact.setLast4Digit(myPhone.getLast4Digit());
		myContact.setPhoneExt(responseEvent.getPhoneExt());
		myContact.setLiaisonTrainingInd(responseEvent.getLiasonTrainingInd());
		myContact.setPrimaryContact(responseEvent.getPrimaryContact());
		myContact.setEmail(responseEvent.getEmail());
		return myContact;
	}

	public static UpdateDepartmentEvent getUpdateDepartmentEvent(DepartmentForm departmentForm)
	{
		UpdateDepartmentEvent event =
			(UpdateDepartmentEvent) EventFactory.getInstance(AgencyControllerServiceNames.UPDATEDEPARTMENT);
		event.setActivationDate(DateUtil.stringToDate(departmentForm.getActivationDate(), UIConstants.DATE_FMT_1));
		event.setAgencyId(departmentForm.getAgencyId());
		event.setAgencyTypeId(departmentForm.getAgencyTypeId());
		event.setComments(departmentForm.getComments().trim());
		event.setCounty(departmentForm.getCountyId());
		event.setDepartmentId(departmentForm.getDepartmentId());
		event.setDepartmentName(departmentForm.getDepartmentName());
		event.setFax(departmentForm.getDepartmentFaxNumber().getPhoneNumber());
		// Department Phone fields should go here
		event.setGritsAccessInd(departmentForm.getGritsInd());
		event.setLabelInd(departmentForm.getSetcicBillingLabelInd());
		event.setOriginatingAgencyId(departmentForm.getOriginatingAgencyId());
		event.setCreateOfficerProfileInd(departmentForm.getCreateOfficerProfileInd());
		event.setOrgCode(departmentForm.getOrgCode());
		event.setSetcicAccessInd(departmentForm.getSetcicAccessId());
		event.setAccessType(departmentForm.getAccessTypeId());
		event.setSetcicDate(DateUtil.stringToDate(departmentForm.getSetcicDate(), UIConstants.DATE_FMT_1));
		event.setSetcicInactiveDate(DateUtil.stringToDate(departmentForm.getSetcicInactiveDate(), UIConstants.DATE_FMT_1));
		event.setSetcicRenewDate(DateUtil.stringToDate(departmentForm.getSetcicRenewDate(), UIConstants.DATE_FMT_1));
		event.setStatus(departmentForm.getStatusId());
		event.setSubscriberCivilActivationDate(
			DateUtil.stringToDate(departmentForm.getSubscriberCivilActivationDate(), UIConstants.DATE_FMT_1));
		event.setSubscriberCivilTerminationDate(
			DateUtil.stringToDate(departmentForm.getSubscriberCivilTerminationDate(), UIConstants.DATE_FMT_1));
		event.setSubscriberCriminalActivationDate(
			DateUtil.stringToDate(departmentForm.getSubscriberCriminalActivationDate(), UIConstants.DATE_FMT_1));
		event.setSubscriberCriminalTerminationDate(
			DateUtil.stringToDate(departmentForm.getSubscriberCriminalTerminationDate(), UIConstants.DATE_FMT_1));
		event.setInactiveDate(DateUtil.stringToDate(departmentForm.getTerminationDate(), UIConstants.DATE_FMT_1));
		event.setWarrantConfirmationPhone(departmentForm.getWarrantConfPhoneNumber().getPhoneNumber());
		event.setWarrantConfirmationPhoneExt(departmentForm.getWarrantConfPhoneNumber().getExt());

		//Setcic Contact Event
		if (isGoodCicEvent(departmentForm))
		{
			event.addRequest(setSetcicContactEvent(departmentForm));
		}
		// Physical Address Events
		if (UIUtil.isGoodAddress(departmentForm.getPhysicalAddress()))
		{
			event.addRequest(UIUtil.getAddressRequestEvent(departmentForm.getPhysicalAddress()));
		}

		//	Billing Address Events
		if (UIUtil.isGoodAddress(departmentForm.getSetcicBillingAddress()))
		{
			event.addRequest(UIUtil.getAddressRequestEvent(departmentForm.getSetcicBillingAddress()));
		}

		//	Mailing Address Events
		if (UIUtil.isGoodAddress(departmentForm.getMailingAddress()))
		{
			event.addRequest(UIUtil.getAddressRequestEvent(departmentForm.getMailingAddress()));
		}

		//Department Contact Events
		Collection myColl = departmentForm.getContactList();
		if (myColl != null && myColl.size() > 0)
		{
			Iterator iter = myColl.iterator();
			while (iter.hasNext())
			{
				UpdateContactEvent contactEvent = (UpdateContactEvent) iter.next();
				event.addRequest(contactEvent);
			}
		}
		return event;
	}

	public static UpdateContactEvent setSetcicContactEvent(DepartmentForm deptForm)
	{
		UpdateContactEvent myEvent = new UpdateContactEvent();
		myEvent.setContactTypeId("S");
		myEvent.setDepartmentId(deptForm.getDepartmentId());
		myEvent.setContactId(deptForm.getSetcicContactId());
		myEvent.setFirstName(deptForm.getSetcicContactName().getFirstName());
		myEvent.setMiddleName(deptForm.getSetcicContactName().getMiddleName());
		myEvent.setLastName(deptForm.getSetcicContactName().getLastName());
		myEvent.setPhone(deptForm.getSetcicPhoneNumber().getPhoneNumber());
		myEvent.setPhoneExt(deptForm.getSetcicPhoneNumber().getExt());
		return myEvent;
	}

	public static boolean isGoodCicEvent(DepartmentForm deptForm)
	{
		if ((deptForm.getSetcicContactName().getFirstName() == null
			|| deptForm.getSetcicContactName().getFirstName().trim().equals(""))
			&& (deptForm.getSetcicContactName().getLastName() == null
				|| deptForm.getSetcicContactName().getLastName().trim().equals(""))
			&& (deptForm.getSetcicPhoneNumber().getPhoneNumber() == null
				|| deptForm.getSetcicPhoneNumber().getPhoneNumber().trim().equals(""))
			&& (deptForm.getSetcicPhoneNumber().getExt() == null || deptForm.getSetcicPhoneNumber().getExt().trim().equals("")))
		{
			return false;
		}
		return true;
	}

	public static void setContactResponseEvents(DepartmentForm departmentForm, Collection contacts)
	{

		ArrayList contactList = new ArrayList();
		if (contacts == null)
		{
			departmentForm.setContactList(contactList);
			return;
		}

		Iterator iter = contacts.iterator();
		while (iter.hasNext())
		{
			DepartmentContactResponseEvent contactResponseEvent = (DepartmentContactResponseEvent) iter.next();
			if (contactResponseEvent != null)
			{
				if ((contactResponseEvent.getContactTypeId() == null)
					|| !(contactResponseEvent.getContactTypeId().equalsIgnoreCase("S")))
				{
					contactList.add(UIDepartmentHelper.convertContactResponseEvent(contactResponseEvent));
				}
				else
				{
					setSetcicContactResponseEvent(departmentForm, contactResponseEvent);
				}
			}

		}
		departmentForm.setContactList(contactList);

	}

	public static void setSetcicContactResponseEvent(DepartmentForm departmentForm, DepartmentContactResponseEvent event)
	{
		if (event == null || departmentForm == null)
			return;
		departmentForm.setSetcicContactId(event.getContactId());
		departmentForm.setSetcicPhoneNumber(new PhoneNumber(event.getPhone()));
		departmentForm.getSetcicPhoneNumber().setExt(event.getPhoneExt());
		departmentForm.setSetcicContactName(new Name());
		departmentForm.getSetcicContactName().setFirstName(event.getFirstName());
		departmentForm.getSetcicContactName().setLastName(event.getLastName());
		departmentForm.getSetcicContactName().setMiddleName(event.getMiddleName());

	}

	public static void setSetcicContactResponseEvent(DepartmentForm departmentForm, Collection events)
	{

		if (events == null || departmentForm == null)
			return;
		Iterator iter = events.iterator();
		if (iter.hasNext())
			setSetcicContactResponseEvent(departmentForm, (DepartmentContactResponseEvent) iter.next());

	}

	/**
	 * @param departmentForm
	 * @param addresses
	 */
	public static void setAddressResponseEvents(DepartmentForm departmentForm, Collection addresses)
	{
		if (addresses == null)
		{

			return;
		}
		Iterator iter = addresses.iterator();
		while (iter.hasNext())
		{
			AddressResponseEvent addressResponseEvent = (AddressResponseEvent) iter.next();
			if (addressResponseEvent != null
				&& addressResponseEvent.getAddressTypeId().equals(PDSecurityConstants.DEPARTMENT_BILLINGADDRESS_TYPE))
			{
				departmentForm.setSetcicBillingAddress(UIUtil.convertAddressResponseEvent(addressResponseEvent));
			}
			else if (
				addressResponseEvent != null
					&& addressResponseEvent.getAddressTypeId().equals(PDSecurityConstants.DEPARTMENT_MAILINGADDRESS_TYPE))
			{
				departmentForm.setMailingAddress(UIUtil.convertAddressResponseEvent(addressResponseEvent));
			}
			else if (
				addressResponseEvent != null
					&& addressResponseEvent.getAddressTypeId().equals(PDSecurityConstants.DEPARTMENT_PHYSICALADDRESS_TYPE))
			{
				departmentForm.setPhysicalAddress(UIUtil.convertAddressResponseEvent(addressResponseEvent));
			}
		}
	}

	public static void setDepartmentProfileForm(DepartmentResponseEvent departmentResponseEvent, DepartmentForm departmentForm)
	{
		if (departmentResponseEvent != null)
		{
			departmentForm.setAccessTypeId(departmentResponseEvent.getAccessTypeId());
			if(departmentResponseEvent.getActivationDate() != null){
				departmentForm.setActivationDate(
				DateUtil.dateToString(departmentResponseEvent.getActivationDate(), UIConstants.DATE_FMT_1));
			}
			departmentForm.setAgencyId(departmentResponseEvent.getAgencyId());
			departmentForm.setAgencyName(departmentResponseEvent.getAgencyName());
			departmentForm.setAgencyTypeId(departmentResponseEvent.getAgencyTypeId());
			departmentForm.setAgencyType(departmentResponseEvent.getAgencyType());
			departmentForm.setComments(departmentResponseEvent.getComments());
			departmentForm.setCountyId(departmentResponseEvent.getCountyId());
			departmentForm.setCounty(departmentResponseEvent.getCountyName());
			departmentForm.setDepartmentId(departmentResponseEvent.getDepartmentId());
			departmentForm.setDepartmentName(departmentResponseEvent.getDepartmentName());
			departmentForm.getDepartmentPhoneNumber().setPhoneNumber(departmentResponseEvent.getPhone());
			departmentForm.getDepartmentFaxNumber().setExt(departmentResponseEvent.getPhoneExt());
			departmentForm.getDepartmentFaxNumber().setPhoneNumber(departmentResponseEvent.getFax());
			departmentForm.setGritsInd(departmentResponseEvent.getGritsInd());
			departmentForm.setOriginatingAgencyId(departmentResponseEvent.getOriginatingAgencyId());
			departmentForm.setOrgCode(departmentResponseEvent.getOrgCode());
			departmentForm.setSetcicAccessId(departmentResponseEvent.getSetcicAccessId());
			departmentForm.setSetcicAccessInd(departmentResponseEvent.getSetcicAccessInd());
			if(departmentResponseEvent.getSetcicDate() != null){
				departmentForm.setSetcicDate(DateUtil.dateToString(departmentResponseEvent.getSetcicDate(), UIConstants.DATE_FMT_1));
			}
			if(departmentResponseEvent.getSetcicInactiveDate() != null){
				departmentForm.setSetcicInactiveDate(DateUtil.dateToString(departmentResponseEvent.getSetcicInactiveDate(), UIConstants.DATE_FMT_1));
			}
			if(departmentResponseEvent.getSetcicRenewDate() != null){
				departmentForm.setSetcicRenewDate(DateUtil.dateToString(departmentResponseEvent.getSetcicRenewDate(), UIConstants.DATE_FMT_1));
			}
			departmentForm.setStatusId(departmentResponseEvent.getStatusId());
			departmentForm.setStatus(departmentResponseEvent.getStatus());
			if(departmentResponseEvent.getSubscriberCivilActivationDate() != null){
				departmentForm.setSubscriberCivilActivationDate(
				DateUtil.dateToString(departmentResponseEvent.getSubscriberCivilActivationDate(), UIConstants.DATE_FMT_1));
			}
			if(departmentResponseEvent.getSubscriberCivilTerminationDate() != null){
				departmentForm.setSubscriberCivilTerminationDate(
				DateUtil.dateToString(departmentResponseEvent.getSubscriberCivilTerminationDate(), UIConstants.DATE_FMT_1));
			}
			
			if(departmentResponseEvent.getSubscriberCriminalActivationDate() != null){
				departmentForm.setSubscriberCriminalActivationDate(
				DateUtil.dateToString(departmentResponseEvent.getSubscriberCriminalActivationDate(), UIConstants.DATE_FMT_1));
			}
			
			if(departmentResponseEvent.getSubscriberCriminalTerminationDate() != null){
				departmentForm.setSubscriberCriminalTerminationDate(
				DateUtil.dateToString(departmentResponseEvent.getSubscriberCriminalTerminationDate(), UIConstants.DATE_FMT_1));
			}
			
			if(departmentResponseEvent.getTerminationDate() != null){
				departmentForm.setTerminationDate(
				DateUtil.dateToString(departmentResponseEvent.getTerminationDate(), UIConstants.DATE_FMT_1));
			}
			departmentForm.getWarrantConfPhoneNumber().setPhoneNumber(departmentResponseEvent.getWarrantConfirmationPhone());
			departmentForm.getWarrantConfPhoneNumber().setExt(departmentResponseEvent.getWarrantConfirmationPhoneExt());
			departmentForm.setSetcicBillingLabelInd(departmentResponseEvent.getLabelInd());
			departmentForm.setInUse(departmentResponseEvent.isInUse());
		}
		departmentForm.setCreateOfficerProfileInd(departmentResponseEvent.getCreateOfficerProfileInd());		
	}

	public static Collection generateAgencyDepartmentResponseEvent(Collection departments){
		if(departments == null || departments.isEmpty()){
			return null;
		}
		
		SortedMap agencyMap = new TreeMap();
		Iterator deptIter = departments.iterator();
		while(deptIter.hasNext()){
			DepartmentResponseEvent dResp = (DepartmentResponseEvent) deptIter.next();
			String key = dResp.getAgencyName() + dResp.getAgencyId();
			if(agencyMap == null || agencyMap.isEmpty() || !agencyMap.containsKey(key)){
				AgencyResponseEvent aResp = new AgencyResponseEvent();
				aResp.setAgencyId(dResp.getAgencyId());
				aResp.setAgencyName(dResp.getAgencyName());
				Collection depts = new ArrayList();
				depts.add(dResp);
				aResp.setDepartments(depts);
				agencyMap.put(key,aResp);
				}else{
					AgencyResponseEvent oldResp = (AgencyResponseEvent) agencyMap.get(key);
					agencyMap.remove(key);
					Collection oldDepts = oldResp.getDepartments();
					oldDepts.add(dResp);
					oldResp.setDepartments(oldDepts);
					agencyMap.put(key,oldResp);
			}
		}
		
		// sort the departments
		Iterator iter = agencyMap.values().iterator();
		Collection agencies = new ArrayList();
		while(iter.hasNext()){
			AgencyResponseEvent agResp = (AgencyResponseEvent) iter.next();
			Iterator deptIterator = agResp.getDepartments().iterator();
			SortedMap map = new TreeMap();
			while(deptIterator.hasNext()){
				DepartmentResponseEvent depResp = (DepartmentResponseEvent) deptIterator.next();
				map.put(depResp.getDepartmentName()+ depResp.getDepartmentId(),depResp);
			}
			agResp.setDepartments(map.values());
			agencies.add(agResp);
		}
		return agencyMap.values(); 
	}
	
	/**
	 * @param userGroups
	 * @return
	 */
	public static Collection sortUserNames(Collection userNames)
	{
		if (userNames != null)
		{
			SortedMap map = new TreeMap();
			Iterator iter = userNames.iterator();
			while (iter.hasNext())
			{
				SecurityUserResponseEvent userName = (SecurityUserResponseEvent) iter.next();
				String firstName = userName.getFirstName();
				String lastName = userName.getLastName();
				String middleName = userName.getMiddleName();				
				if (firstName == null){
					firstName = "";
				}
				if (lastName == null){
					lastName = "";
				}
				if (middleName == null){
					middleName = "";
				}				
				map.put(lastName + firstName + middleName + userName.getLogonId(), userName);
			}
			return new ArrayList(map.values());
		}
		else
		{
			return null;
		}
	}	
	/**
	 * @param userGroups
	 * @return
	 */
	public static Collection sortContactNames(Collection contactNames)
	{
		if (contactNames != null)
		{
			SortedMap map = new TreeMap();
			Iterator iter = contactNames.iterator();
			while (iter.hasNext())
			{
				UpdateContactEvent contactName = (UpdateContactEvent) iter.next();
				String firstName = contactName.getFirstName();
				String lastName = contactName.getLastName();
				String middleName = contactName.getMiddleName();				
				if (firstName == null){
					firstName = "";
				}
				if (lastName == null){
					lastName = "";
				}
				if (middleName == null){
					middleName = "";
				}				
				map.put(lastName + firstName + middleName + contactName.getLogonId(), contactName);				
			}
			return new ArrayList(map.values());
		}
		else
		{
			return null;
		}
	}	
}