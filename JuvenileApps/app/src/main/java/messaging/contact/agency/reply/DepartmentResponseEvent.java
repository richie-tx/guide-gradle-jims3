package messaging.contact.agency.reply;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import messaging.address.reply.AddressResponseEvent;
import messaging.codetable.reply.ICode;
import messaging.contact.user.reply.UserResponseEvent;
import mojo.km.messaging.ResponseEvent;
import naming.PDConstants;
import naming.PDContactConstants;

/**
 * @author dnikolis
 *
 */
public class DepartmentResponseEvent extends ResponseEvent implements ICode, Comparable
{
	private String accessType; // code
	private String accessTypeId; // code
	private Date activationDate;
	private String activationTime;
	private String agencyId;
	private String agencyName;
	private String departmentId;
	private String departmentName;
	private String departmentStatus; // code
	private String departmentStatusId; // code
	private String agencyType; // code
	private String agencyTypeId; // code
	private String city;
	private String comments;
	private String countyId;
	private String countyName;
	private String orgCode;
	private String fax;
	private String firstName;
	private String gritsInd;
	private boolean inUse = false;
	private String labelInd;
	private String lastName;
	private String mailingCity;
	private String mailingState; // code
	private String mailingStateId; // code
	private String mailingStreetName;
	private String mailingStreetNum;
	private String mailingAddress2;
	private String mailingZipCode;
	private String originatingAgencyId;
	private String createOfficerProfileInd;
	private String phone;
	private String phoneExt;
	private String projectAnalystInd;
	private String setcicAccess; // code
	private String setcicAccessId; // code
	private String setcicAccessInd;
	private String setcicContactFirstName;
	private String setcicContactMiddleName;
	private String setcicContactLastName;
	private Date setcicDate;
	private Date setcicInactiveDate;
	private String setcicInactiveTime;
	private String setcicPhone;
	private String setcicPhoneExt;
	private Date setcicRenewDate;
	private String setcicRenewTime;
	private String setcicTime;
	private String state;
	private String stateId;
	private String statusId;
	private String status;
	private String streetName;
	private String streetNum;
	private String address2;
	private Date subscriberCivilActivationDate;
	private String subscriberCivilActivationTime;
	private Date subscriberCivilTerminationDate;
	private String subscriberCivilTerminationTime;
	private Date subscriberCriminalActivationDate;
	private String subscriberCriminalActivationTime;
	private Date subscriberCriminalTerminationDate;
	private String subscriberCriminalTerminationTime;
	private Date terminationDate;
	private String terminationTime;
	private String title;
	private String warrantConfirmationPhone;
	private String warrantConfirmationPhoneExt;
	private String zipCode;
	private String userCount;

	// collection
	private Collection departmentContacts;
	private Collection liasonUsers = new ArrayList();
	private Collection asaUsers = new ArrayList();
	private Collection saUsers = new ArrayList();
	private AddressResponseEvent billingAddress;
	private AddressResponseEvent physicalAddress;
	private AddressResponseEvent mailingAddress;

	private Collection departmentContactResponseEvents;

	public String toString()
	{
		return agencyName;
	}

	public String getAgencyDepartmentTopic()
	{
		return PDContactConstants.AGENCY_EVENT_TOPIC
			+ "."
			+ this.getAgencyId()
			+ "."
			+ PDContactConstants.DEPARTMENT_EVENT_TOPIC
			+ "."
			+ PDConstants.LIST_ITEM;
	}

	/**
	 * @return
	 */
	public String getAccessType()
	{
		return accessType;
	}

	/**
	 * @return
	 */
	public String getAccessTypeId()
	{
		return accessTypeId;
	}
	/**
	 * @return
	 */
	public Date getActivationDate()
	{
		return activationDate;
	}
	/**
	 * @return
	 */
	public String getActivationTime()
	{
		return activationTime;
	}

	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	 * @return
	 */
	public String getAgencyName()
	{
		return agencyName;
	}
	/**
	 * @return
	 */
	public String getAgencyStatus()
	{
		return departmentStatus;
	}
	/**
	 * @return
	 */
	public String getAgencyStatusId()
	{
		return departmentStatusId;
	}

	/**
	 * @return
	 */
	public String getAgencyType()
	{
		return agencyType;
	}

	/**
	 * @return
	 */
	public String getAgencyTypeId()
	{
		return agencyTypeId;
	}
	/**
	 * @return
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * @return
	 */
	public String getComments()
	{
		return comments;
	}

	/**
	 * @return
	 */
	public String getOrgCode()
	{
		return orgCode;
	}

	/**
	 * @return
	 */
	public String getFax()
	{
		return fax;
	}

	/**
	 * @return
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @return
	 */
	public String getLabelInd()
	{
		return labelInd;
	}

	/**
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @return
	 */
	public String getMailingCity()
	{
		return mailingCity;
	}

	/**
	 * @return
	 */
	public String getMailingState()
	{
		return mailingState;
	}

	/**
	 * @return
	 */
	public String getMailingStateId()
	{
		return mailingStateId;
	}

	/**
	 * @return
	 */
	public String getMailingStreetName()
	{
		return mailingStreetName;
	}

	/**
	 * @return
	 */
	public String getMailingStreetNum()
	{
		return mailingStreetNum;
	}

	/**
	 * @return
	 */
	public String getMailingZipCode()
	{
		return mailingZipCode;
	}

	/**
	 * @return
	 */
	public String getOriginatingAgencyId()
	{
		return originatingAgencyId;
	}

	/**
	 * @return
	 */
	public String getPhone()
	{
		return phone;
	}

	/**
	 * @return
	 */
	public String getPhoneExt()
	{
		return phoneExt;
	}

	/**
	 * @return
	 */
	public String getProjectAnalystInd()
	{
		return projectAnalystInd;
	}

	/**
	 * @return
	 */
	public String getSetcicAccess()
	{
		return setcicAccess;
	}

	/**
	 * @return
	 */
	public String getSetcicAccessId()
	{
		return setcicAccessId;
	}

	/**
	 * @return
	 */
	public String getSetcicContactFirstName()
	{
		return setcicContactFirstName;
	}

	/**
	 * @return
	 */
	public String getSetcicContactLastName()
	{
		return setcicContactLastName;
	}

	/**
	 * @return
	 */
	public Date getSetcicDate()
	{
		return setcicDate;
	}

	/**
	 * @return
	 */
	public Date getSetcicInactiveDate()
	{
		return setcicInactiveDate;
	}

	/**
	 * @return
	 */
	public String getSetcicInactiveTime()
	{
		return setcicInactiveTime;
	}

	/**
	 * @return
	 */
	public String getSetcicPhone()
	{
		return setcicPhone;
	}

	/**
	 * @return
	 */
	public String getSetcicPhoneExt()
	{
		return setcicPhoneExt;
	}

	/**
	 * @return
	 */
	public Date getSetcicRenewDate()
	{
		return setcicRenewDate;
	}

	/**
	 * @return
	 */
	public String getSetcicRenewTime()
	{
		return setcicRenewTime;
	}

	/**
	 * @return
	 */
	public String getSetcicTime()
	{
		return setcicTime;
	}

	/**
	 * @return
	 */
	public String getState()
	{
		return state;
	}

	/**
	 * @return
	 */
	public String getStateId()
	{
		return stateId;
	}

	/**
	 * @return
	 */
	public String getStreetName()
	{
		return streetName;
	}

	/**
	 * @return
	 */
	public String getStreetNum()
	{
		return streetNum;
	}

	/**
	 * @return
	 */
	public Date getSubscriberCivilActivationDate()
	{
		return subscriberCivilActivationDate;
	}

	/**
	 * @return
	 */
	public String getSubscriberCivilActivationTime()
	{
		return subscriberCivilActivationTime;
	}

	/**
	 * @return
	 */
	public Date getSubscriberCivilTerminationDate()
	{
		return subscriberCivilTerminationDate;
	}

	/**
	 * @return
	 */
	public String getSubscriberCivilTerminationTime()
	{
		return subscriberCivilTerminationTime;
	}

	/**
	 * @return
	 */
	public Date getSubscriberCriminalActivationDate()
	{
		return subscriberCriminalActivationDate;
	}

	/**
	 * @return
	 */
	public String getSubscriberCriminalActivationTime()
	{
		return subscriberCriminalActivationTime;
	}

	/**
	 * @return
	 */
	public Date getSubscriberCriminalTerminationDate()
	{
		return subscriberCriminalTerminationDate;
	}

	/**
	 * @return
	 */
	public String getSubscriberCriminalTerminationTime()
	{
		return subscriberCriminalTerminationTime;
	}

	/**
	 * @return
	 */
	public Date getTerminationDate()
	{
		return terminationDate;
	}

	/**
	 * @return
	 */
	public String getTerminationTime()
	{
		return terminationTime;
	}

	/**
	 * @return
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @return
	 */
	public String getWarrantConfirmationPhone()
	{
		return warrantConfirmationPhone;
	}

	/**
	 * @return
	 */
	public String getWarrantConfirmationPhoneExt()
	{
		return warrantConfirmationPhoneExt;
	}

	/**
	 * @return
	 */
	public String getZipCode()
	{
		return zipCode;
	}

	/**
	 * @param accessType
	 */
	public void setAccessType(String accessType)
	{
		this.accessType = accessType;
	}

	/**
	 * @param accessTypeId
	 */
	public void setAccessTypeId(String accessTypeId)
	{
		this.accessTypeId = accessTypeId;
	}
	/**
	 * @param activationDate
	 */
	public void setActivationDate(Date activationDate)
	{
		this.activationDate = activationDate;
	}

	/**
	 * @param activationTime
	 */
	public void setActivationTime(String activationTime)
	{
		this.activationTime = activationTime;
	}

	/**
	 * @param agencyId
	 */
	public void setAgencyId(String agencyId)
	{
		this.agencyId = agencyId;
	}

	/**
	 * @param agencyName
	 */
	public void setAgencyName(String agencyName)
	{
		this.agencyName = agencyName;
	}
	/**
	 * @param departmentStatus 
	 */
	public void setDepartmentStatus(String agencyStatus)
	{
		this.departmentStatus = agencyStatus;
	}
	/**
	 * @param statusInd 
	 */
	public void setDepartmentStatusId(String agencyStatusId)
	{
		this.departmentStatusId = agencyStatusId;
	}

	/**
	 * @param agencyType
	 */
	public void setAgencyType(String agencyType)
	{
		this.agencyType = agencyType;
	}

	/**
	 * @param agencyTypeId
	 */
	public void setAgencyTypeId(String agencyTypeId)
	{
		this.agencyTypeId = agencyTypeId;
	}

	/**
	 * @param city
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	/**
	 * @param comments
	 */
	public void setComments(String comments)
	{
		this.comments = comments;
	}

	/**
	 * @param deptCode
	 */
	public void setOrgCode(String orgCode)
	{
		this.orgCode = orgCode;
	}

	/**
	 * @param fax
	 */
	public void setFax(String fax)
	{
		this.fax = fax;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @param labelInd
	 */
	public void setLabelInd(String labelInd)
	{
		this.labelInd = labelInd;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @param mailingCity
	 */
	public void setMailingCity(String mailingCity)
	{
		this.mailingCity = mailingCity;
	}

	/**
	 * @param mailingState
	 */
	public void setMailingState(String mailingState)
	{
		this.mailingState = mailingState;
	}

	/**
	 * @param mailingStateId
	 */
	public void setMailingStateId(String mailingStateId)
	{
		this.mailingStateId = mailingStateId;
	}

	/**
	 * @param mailingStreetName
	 */
	public void setMailingStreetName(String mailingStreetName)
	{
		this.mailingStreetName = mailingStreetName;
	}

	/**
	 * @param mailingStreetNum
	 */
	public void setMailingStreetNum(String mailingStreetNum)
	{
		this.mailingStreetNum = mailingStreetNum;
	}

	/**
	 * @param mailingZipCode
	 */
	public void setMailingZipCode(String mailingZipCode)
	{
		this.mailingZipCode = mailingZipCode;
	}

	/**
	 * @param originatingAgencyId
	 */
	public void setOriginatingAgencyId(String originatingAgencyId)
	{
		this.originatingAgencyId = originatingAgencyId;
	}

	/**
	 * @param phone
	 */
	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	/**
	 * @param phoneExt
	 */
	public void setPhoneExt(String phoneExt)
	{
		this.phoneExt = phoneExt;
	}

	/**
	 * @param projectAnalystInd
	 */
	public void setProjectAnalystInd(String projectAnalystInd)
	{
		this.projectAnalystInd = projectAnalystInd;
	}
	/**
	 * @param setcicAccess
	 */
	public void setSetcicAccess(String setcicAccess)
	{
		this.setcicAccess = setcicAccess;
	}

	/**
	 * @param setcicAccessId
	 */
	public void setSetcicAccessId(String setcicAccessId)
	{
		this.setcicAccessId = setcicAccessId;
	}

	/**
	 * @param setcicContactFirstName
	 */
	public void setSetcicContactFirstName(String setcicContactFirstName)
	{
		this.setcicContactFirstName = setcicContactFirstName;
	}

	/**
	 * @param setcicContactLastName
	 */
	public void setSetcicContactLastName(String setcicContactLastName)
	{
		this.setcicContactLastName = setcicContactLastName;
	}

	/**
	 * @param setcicDate
	 */
	public void setSetcicDate(Date setcicDate)
	{
		this.setcicDate = setcicDate;
	}

	/**
	 * @param setcicInactiveDate
	 */
	public void setSetcicInactiveDate(Date setcicInactiveDate)
	{
		this.setcicInactiveDate = setcicInactiveDate;
	}

	/**
	 * @param setcicInactiveTime
	 */
	public void setSetcicInactiveTime(String setcicInactiveTime)
	{
		this.setcicInactiveTime = setcicInactiveTime;
	}

	/**
	 * @param setcicPhone
	 */
	public void setSetcicPhone(String setcicPhone)
	{
		this.setcicPhone = setcicPhone;
	}

	/**
	 * @param setcicPhoneExt
	 */
	public void setSetcicPhoneExt(String setcicPhoneExt)
	{
		this.setcicPhoneExt = setcicPhoneExt;
	}

	/**
	 * @param setcicRenewDate
	 */
	public void setSetcicRenewDate(Date setcicRenewDate)
	{
		this.setcicRenewDate = setcicRenewDate;
	}

	/**
	 * @param setcicRenewTime
	 */
	public void setSetcicRenewTime(String setcicRenewTime)
	{
		this.setcicRenewTime = setcicRenewTime;
	}

	/**
	 * @param setcicTime
	 */
	public void setSetcicTime(String setcicTime)
	{
		this.setcicTime = setcicTime;
	}

	/**
	 * @param state
	 */
	public void setState(String state)
	{
		this.state = state;
	}

	/**
	 * @param stateId
	 */
	public void setStateId(String stateId)
	{
		this.stateId = stateId;
	}
	/**
	 * @param streetName
	 */
	public void setStreetName(String streetName)
	{
		this.streetName = streetName;
	}

	/**
	 * @param streetNum
	 */
	public void setStreetNum(String streetNum)
	{
		this.streetNum = streetNum;
	}

	/**
	 * @param subscriberCivilActivationDate
	 */
	public void setSubscriberCivilActivationDate(Date subscriberCivilActivationDate)
	{
		this.subscriberCivilActivationDate = subscriberCivilActivationDate;
	}

	/**
	 * @param subscriberCivilActivationTime
	 */
	public void setSubscriberCivilActivationTime(String subscriberCivilActivationTime)
	{
		this.subscriberCivilActivationTime = subscriberCivilActivationTime;
	}

	/**
	 * @param subscriberCivilTerminationDate
	 */
	public void setSubscriberCivilTerminationDate(Date subscriberCivilTerminationDate)
	{
		this.subscriberCivilTerminationDate = subscriberCivilTerminationDate;
	}

	/**
	 * @param subscriberCivilTerminationTime
	 */
	public void setSubscriberCivilTerminationTime(String subscriberCivilTerminationTime)
	{
		this.subscriberCivilTerminationTime = subscriberCivilTerminationTime;
	}

	/**
	 * @param subscriberCriminalActivationDate
	 */
	public void setSubscriberCriminalActivationDate(Date subscriberCriminalActivationDate)
	{
		this.subscriberCriminalActivationDate = subscriberCriminalActivationDate;
	}

	/**
	 * @param subscriberCriminalActivationTime
	 */
	public void setSubscriberCriminalActivationTime(String subscriberCriminalActivationTime)
	{
		this.subscriberCriminalActivationTime = subscriberCriminalActivationTime;
	}

	/**
	 * @param subscriberCriminalTerminationDate
	 */
	public void setSubscriberCriminalTerminationDate(Date subscriberCriminalTerminationDate)
	{
		this.subscriberCriminalTerminationDate = subscriberCriminalTerminationDate;
	}

	/**
	 * @param subscriberCriminalTerminationTime
	 */
	public void setSubscriberCriminalTerminationTime(String subscriberCriminalTerminationTime)
	{
		this.subscriberCriminalTerminationTime = subscriberCriminalTerminationTime;
	}

	/**
	 * @param terminationDate
	 */
	public void setTerminationDate(Date terminationDate)
	{
		this.terminationDate = terminationDate;
	}

	/**
	 * @param terminationTime
	 */
	public void setTerminationTime(String terminationTime)
	{
		this.terminationTime = terminationTime;
	}

	/**
	 * @param title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * @param warrantConfirmationPhone
	 */
	public void setWarrantConfirmationPhone(String warrantConfirmationPhone)
	{
		this.warrantConfirmationPhone = warrantConfirmationPhone;
	}

	/**
	 * @param warrantConfirmationPhoneExt
	 */
	public void setWarrantConfirmationPhoneExt(String warrantConfirmationPhoneExt)
	{
		this.warrantConfirmationPhoneExt = warrantConfirmationPhoneExt;
	}

	/**
	 * @param zipCode
	 */
	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}
	/**
	 * @return
	 */
	public String getAddress2()
	{
		return address2;
	}

	/**
	 * @param string
	 */
	public void setAddress2(String string)
	{
		address2 = string;
	}

	/**
	 * @return
	 */
	public String getMailingAddress2()
	{
		return mailingAddress2;
	}

	/**
	 * @param string
	 */
	public void setMailingAddress2(String string)
	{
		mailingAddress2 = string;
	}
	/**
	 * @return
	 */
	public String getDepartmentId()
	{
		return departmentId;
	}

	/**
	 * @return
	 */
	public String getDepartmentName()
	{
		return departmentName;
	}

	/**
	 * @param string
	 */
	public void setDepartmentId(String string)
	{
		departmentId = string;
	}

	/**
	 * @param string
	 */
	public void setDepartmentName(String string)
	{
		departmentName = string;
	}

	/**
	 * @return
	 */
	public Collection getDepartmentContacts()
	{
		return departmentContacts;
	}

	/**
	 * @param collection
	 */
	public void setDepartmentContacts(Collection collection)
	{
		departmentContacts = collection;
	}
	
	/**
	 * @return
	 */
	public Collection getDepartmentContactResponseEvents()
	{
		return departmentContactResponseEvents;
	}

	/**
	 * @return
	 */
	public String getDepartmentStatus()
	{
		return departmentStatus;
	}

	/**
	 * @return
	 */
	public String getDepartmentStatusId()
	{
		return departmentStatusId;
	}

	/**
	 * @param collection
	 */
	public void setDepartmentContactResponseEvents(Collection collection)
	{
		departmentContactResponseEvents = collection;
	}

	/**
	 * @return
	 */
	public String getCountyId()
	{
		return countyId;
	}

	/**
	 * @param string
	 */
	public void setcountyId(String string)
	{
		countyId = string;
	}

	/**
	 * @return
	 */
	public String getCountyName()
	{
		return countyName;
	}

	/**
	 * @param string
	 */
	public void setCountyName(String string)
	{
		countyName = string;
	}

	/**
	 * @return
	 */
	public String getGritsInd()
	{
		return gritsInd;
	}

	/**
	 * @param string
	 */
	public void setGritsInd(String string)
	{
		gritsInd = string;
	}

	/**
	 * @return
	 */
	public String getSetcicAccessInd()
	{
		return setcicAccessInd;
	}

	/**
	 * @param string
	 */
	public void setSetcicAccessInd(String string)
	{
		setcicAccessInd = string;
	}

	/**
	 * @param string
	 */
	public void setCountyId(String string)
	{
		countyId = string;
	}

	/**
	 * @return
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * @return
	 */
	public String getStatusId()
	{
		return statusId;
	}

	/**
	 * @param string
	 */
	public void setStatus(String string)
	{
		status = string;
	}

	/**
	 * @param string
	 */
	public void setStatusId(String string)
	{
		statusId = string;
	}

	/**
	 * @return
	 */
	public AddressResponseEvent getBillingAddress()
	{
		return billingAddress;
	}

	/**
	 * @return
	 */
	public AddressResponseEvent getMailingAddress()
	{
		return mailingAddress;
	}

	/**
	 * @return
	 */
	public AddressResponseEvent getPhysicalAddress()
	{
		return physicalAddress;
	}

	/**
	 * @param event
	 */
	public void setBillingAddress(AddressResponseEvent event)
	{
		billingAddress = event;
	}

	/**
	 * @param event
	 */
	public void setMailingAddress(AddressResponseEvent event)
	{
		mailingAddress = event;
	}

	/**
	 * @param event
	 */
	public void setPhysicalAddress(AddressResponseEvent event)
	{
		physicalAddress = event;
	}

	/**
	 * @return
	 */
	public boolean isInUse()
	{
		return inUse;
	}

	/**
	 * @param b
	 */
	public void setInUse(boolean b)
	{
		inUse = b;
	}

	/**
	 * @return
	 */
	public String getSetcicContactMiddleName()
	{
		return setcicContactMiddleName;
	}

	/**
	 * @param string
	 */
	public void setSetcicContactMiddleName(String string)
	{
		setcicContactMiddleName = string;
	}

	/* (non-Javadoc)
	 * @see messaging.codetable.reply.ICode#getCode()
	 */
	public String getCode()
	{
		return this.departmentId;
	}

	/* (non-Javadoc)
	 * @see messaging.codetable.reply.ICode#getDescription()
	 */
	public String getDescription()
	{
		return this.departmentName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj)
	{
		DepartmentResponseEvent evt = (DepartmentResponseEvent) obj;
		return this.departmentName.compareTo(evt.getDepartmentName());
	}

	/**
	 * @return
	 */
	public String getCreateOfficerProfileInd()
	{
		return createOfficerProfileInd;
	}

	/**
	 * @param string
	 */
	public void setCreateOfficerProfileInd(String string)
	{
		createOfficerProfileInd = string;
	}

	/**
	 * @return Returns the liasonUsers.
	 */
	public Collection getLiasonUsers() {
		return liasonUsers;
	}
	/**
	 * @param liasonUsers The liasonUsers to set.
	 */
	public void addLiasonUser(UserResponseEvent liasonUser) {
		this.liasonUsers.add(liasonUser);
	}
	/**
	 * @return Returns the asaUsers.
	 */
	public Collection getAsaUsers() {
		return asaUsers;
	}
	/**
	 * @param user The user to add.
	 */
	public void addAsaUser(UserResponseEvent user) {
		this.asaUsers.add(user);
	}
	/**
	 * @return Returns the saUsers.
	 */
	public Collection getSaUsers() {
		return saUsers;
	}
	/**
	 * @param user The user to add.
	 */
	public void addSaUser(UserResponseEvent user) {
		this.saUsers.add(user);
	}
	/**
	 * @return Returns the userCount.
	 */
	public String getUserCount() {
		return userCount;
	}
	/**
	 * @param userCount The userCount to set.
	 */
	public void setUserCount(String userCount) {
		this.userCount = userCount;
	}
}