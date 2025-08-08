package pd.contact.agency;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import messaging.agency.UpdateDepartmentEvent;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;
import naming.PDSecurityConstants;
import pd.address.Address;
import pd.codetable.Code;
import pd.transferobjects.helper.DepartmentHelper;

/**
* @roseuid 430A181D027A
*/
public class Department extends PersistentObject
{
	/**
	* Properties for agency
	*/
	private Agency agency = null;
	private Date subscriberCivilTerminationDate;
	private String countyId;
	private Date setcicInactiveDate;
	/**
	* Properties for agencyType
	* @referencedType pd.codetable.Code
	* @contextKey AGENCY_TYPE
	* @detailerDoNotGenerate true
	*/
	private Code agencyType = null;
	/**
	* Properties for accessType
	* @referencedType pd.codetable.Code
	* @contextKey AGENCY_ACCESS_TYPE
	* @detailerDoNotGenerate true
	*/
	private Code accessType = null;
	private Date setcicDate;
	private String comments;
	private String setcicAccessId;
	private String departmentId;
	private String fax;
	private String mailingAddressId;
	/**
	* Properties for contacts
	* @referencedType pd.contact.agency.DepartmentContact
	*/
	private Collection contacts = null;
	private String agencyName;
	private String originatingAgencyId;//needed
	private String warrantConfirmationPhoneExt;
	private String statusId;
	/**
	* Properties for setcicAccess
	* @referencedType pd.codetable.Code
	* @contextKey SETCIC_ACCESS
	* @detailerDoNotGenerate true
	*/
	private Code setcicAccess = null;
	private String departmentName;
	private Date setcicRenewDate;
	private Date terminationDate;
	private String labelInd;
	/**
	* Properties for status
	* @referencedType pd.codetable.Code
	* @contextKey AGENCY_STATUS
	* @detailerDoNotGenerate true
	*/
	private Code status = null;
	private String gritsInd;
	private String addressId;
	private Date activationDate;
	private String agencyId;
	private String warrantConfirmationPhone;
	private Date subscriberCivilActivationDate;
	/**
	* Properties for mailingAddress
	* @useParent true
	* @detailerDoNotGenerate true
	*/
	private Address mailingAddress = null;
	private Date subscriberCriminalActivationDate;
	private String agencyTypeId;
	private String createOfficerProfileInd;
	private Date subscriberCriminalTerminationDate;
	private String billingAddressId;
	/**
	* Properties for address
	* @useParent true
	* @detailerDoNotGenerate true
	*/
	private Address address = null;
	/**
	* Properties for county
	* @referencedType pd.codetable.Code
	* @contextKey COUNTY
	* @detailerDoNotGenerate true
	*/
	private Code county = null;
	private String orgCode;
	private String inUseInd;
	/**
	* Properties for billingAddress
	* @useParent true
	* @detailerDoNotGenerate true
	*/
	private Address billingAddress = null;
	private String accessTypeId;
	private String departmentUserCount;
	/**
	* @roseuid 430A181D027A
	*/
	public Department()
	{
	}
	/**
	* Clears all pd.contact.agency.DepartmentContact from class relationship collection.
	*/
	public void clearContacts()
	{
		initContacts();
		contacts.clear();
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getAccessType()
	{
		//fetch(); 87191
		//initAccessType();crmcd
		return accessType;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getAccessTypeId()
	{
		//fetch();
		return accessTypeId;
	}
	/**
	* Access method for the activationDate property.
	* @return the current value of the activationDate property
	*/
	public Date getActivationDate()
	{
		//fetch();
		return activationDate;
	}
	/**
	* Gets referenced type pd.address.Address
	*/
	public Address getAddress()
	{
		//fetch();
		initAddress();
		return address;
	}
	/**
	* Get the reference value to class :: pd.address.Address
	*/
	public String getAddressId()
	{
		//fetch();
		return addressId;
	}
	/**
	* Gets referenced type pd.contact.agency.Agency
	*/
	public Agency getAgency()
	{
		//fetch();
		initAgency();
		return agency;
	}
	/**
	* Get the reference value to class :: pd.contact.agency.Agency
	*/
	public String getAgencyId()
	{
		//fetch();
		return agencyId;
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getAgencyType()
	{
		//fetch();
		//initAgencyType();CRMCD
		return agencyType;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getAgencyTypeId()
	{
		//fetch();
		return agencyTypeId;
	}
	/**
	* Gets referenced type pd.address.Address
	*/
	public Address getBillingAddress()
	{
		//fetch();
		initBillingAddress();
		return billingAddress;
	}
	/**
	* Get the reference value to class :: pd.address.Address
	*/
	public String getBillingAddressId()
	{
		//fetch();
		return billingAddressId;
	}
	/**
	* Access method for the comments property.
	* @return the current value of the comments property
	*/
	public String getComments()
	{
		//fetch();
		return comments;
	}
	/**
	* returns a collection of pd.contact.agency.DepartmentContact
	*/
	public Collection getContacts()
	{
		//fetch();
		initContacts();
		return contacts;
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getCounty()
	{
		//fetch();
		initCounty();
		return county;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getCountyId()
	{
		//fetch();
		return countyId;
	}
	/**
	* Access method for the departmentId property.
	* @return the current value of the departmentId property
	*/
	public String getDepartmentId()
	{
		return departmentId;
	}
	/**
	* Access method for the departmentName property.
	* @return the current value of the departmentName property
	*/
	public String getDepartmentName()
	{
		//fetch();
		return departmentName;
	}
	/**
	* Access method for the fax property.
	* @return the current value of the fax property
	*/
	public String getFax()
	{
		//fetch();
		return fax;
	}
	/**
	* Access method for the gritsInd property.
	* @return the current value of the gritsInd property
	*/
	public String getGritsInd()
	{
		//fetch();
		return gritsInd;
	}
	/**
	* Access method for the labelInd property.
	* @return the current value of the labelInd property
	*/
	public String getLabelInd()
	{
		//fetch();
		return labelInd;
	}
	/**
	* Gets referenced type pd.address.Address
	*/
	public Address getMailingAddress()
	{
		//fetch();
		initMailingAddress();
		return mailingAddress;
	}
	/**
	* Get the reference value to class :: pd.address.Address
	*/
	public String getMailingAddressId()
	{
		//fetch();
		return mailingAddressId;
	}
	/**
	* Access method for the orgCode property.
	* @return the current value of the orgCode property
	*/
	public String getOrgCode()
	{
		//fetch();
		return orgCode;
	}
	/**
	* Access method for the originatingAgencyId property.
	* @return the current value of the originatingAgencyId property
	*/
	public String getOriginatingAgencyId()
	{
		//fetch();
		return originatingAgencyId;
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getSetcicAccess()
	{
		//fetch();
		//initSetcicAccess(); CRMCD
		return setcicAccess;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getSetcicAccessId()
	{
		//fetch();
		return setcicAccessId;
	}
	/**
	* Access method for the setcicDate property.
	* @return the current value of the setcicDate property
	*/
	public Date getSetcicDate()
	{
		//fetch();
		return setcicDate;
	}
	/**
	* Access method for the setcicInactiveDate property.
	* @return the current value of the setcicInactiveDate property
	*/
	public Date getSetcicInactiveDate()
	{
		//fetch();
		return setcicInactiveDate;
	}
	/**
	* Access method for the setcicRenewDate property.
	* @return the current value of the setcicRenewDate property
	*/
	public Date getSetcicRenewDate()
	{
		//fetch();
		return setcicRenewDate;
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getStatus()
	{
		//fetch();
		//initStatus();
		return status;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getStatusId()
	{
		//fetch();
		return statusId;
	}
	/**
	* Access method for the subscriberCivilActivationDate property.
	* @return the current value of the subscriberCivilActivationDate property
	*/
	public Date getSubscriberCivilActivationDate()
	{
		//fetch();
		return subscriberCivilActivationDate;
	}
	/**
	* Access method for the subscriberCivilTerminationDate property.
	* @return the current value of the subscriberCivilTerminationDate property
	*/
	public Date getSubscriberCivilTerminationDate()
	{
		//fetch();
		return subscriberCivilTerminationDate;
	}
	/**
	* Access method for the subscriberCriminalActivationDate property.
	* @return the current value of the subscriberCriminalActivationDate property
	*/
	public Date getSubscriberCriminalActivationDate()
	{
		//fetch();
		return subscriberCriminalActivationDate;
	}
	/**
	* Access method for the subscriberCriminalTerminationDate property.
	* @return the cujrrent value of the subscriberCriminalTerminationDate property
	*/
	public Date getSubscriberCriminalTerminationDate()
	{
		//fetch();
		return subscriberCriminalTerminationDate;
	}
	/**
	* Access method for the terminationDate property.
	* @return the current value of the terminationDate property
	*/
	public Date getTerminationDate()
	{
		//fetch();
		return terminationDate;
	}
	/**
	* Access method for the warrantConfirmationPhone property.
	* @return the current value of the warrantConfirmationPhone property
	*/
	public String getWarrantConfirmationPhone()
	{
		//fetch();
		return warrantConfirmationPhone;
	}
	/**
	* Access method for the warrantConfirmationPhoneExt property.
	* @return the current value of the warrantConfirmationPhoneExt property
	*/
	public String getWarrantConfirmationPhoneExt()
	{
		//fetch();
		return warrantConfirmationPhoneExt;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*///CRMCD
	private void initAccessType()
	{
		if (accessType == null)
		{
			accessType =
				(Code) new mojo
					.km
					.persistence
					.Reference(accessTypeId, Code.class, PDCodeTableConstants.AGENCY_ACCESS_TYPE)
					.getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.address.Address
	*/
	private void initAddress()
	{
		if (address == null)
		{
			address =
				(Address) new mojo
					.km
					.persistence
					.Reference(
						addressId,
						Address.class,
						(mojo.km.persistence.PersistentObject) this,
						"physicalAddress")
					.getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.contact.agency.Agency
	*/
	private void initAgency()
	{
		if (agency == null)
		{
			agency =
				(Agency) new mojo
					.km
					.persistence
					.Reference(agencyId, Agency.class)
					.getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*///CRMCD
	private void initAgencyType()
	{
		if (agencyType == null)
		{
			agencyType =
				(Code) new mojo
					.km
					.persistence
					.Reference(agencyTypeId, Code.class, "AGENCY_TYPE")
					.getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.address.Address
	*/
	private void initBillingAddress()
	{
		if (billingAddress == null)
		{
			/*billingAddress =
				(pd.address.Address) new mojo
					.km
					.persistence
					.Reference(
						billingAddressId,
						pd.address.Address.class,
						(mojo.km.persistence.PersistentObject) this,
						"billingAddress")
					.getObject();*/
		}
	}
	/**
	* Initialize class relationship implementation for pd.contact.agency.DepartmentContact
	*/
	private void initContacts()
	{
		if (contacts == null)
		{
			/*if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}*/
			try
			{
				contacts = Department.find(departmentId).getContacts();
					/*new mojo.km.persistence.ArrayList(
						pd.contact.agency.DepartmentContact.class,
						"departmentId",
						"" + getOID());*/
			}
			catch (Throwable t)
			{
				contacts = new java.util.ArrayList();
			}
		}
	}

	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initCounty()
	{
		if (county == null)
		{
			county =
				(Code) new mojo
					.km
					.persistence
					.Reference(countyId, Code.class, PDCodeTableConstants.COUNTY)
					.getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.address.Address
	*/
	private void initMailingAddress()
	{
		if (mailingAddress == null)
		{
			//mailingAddress =
				/*(pd.address.Address) new mojo
					.km
					.persistence
					.Reference(
						mailingAddressId,
						pd.address.Address.class,
						(mojo.km.persistence.PersistentObject) this,
						"mailingAddress")
					.getObject();*/
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initSetcicAccess()
	{
		if (setcicAccess == null)
		{
			setcicAccess =
				(Code) new mojo
					.km
					.persistence
					.Reference(setcicAccessId, Code.class, "SETCIC_ACCESS")
					.getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initStatus() //CRMCD
	{
		if (status == null)
		{
			status =
				(Code) new mojo
					.km
					.persistence
					.Reference(statusId, Code.class, PDCodeTableConstants.AGENCY_STATUS)
					.getObject();
		}
	}
	/**
	* insert a pd.contact.agency.DepartmentContact into class relationship collection.
	*/
	public void insertContacts(DepartmentContact anObject)
	{
		initContacts();
		contacts.add(anObject);
	}
	/**
	* Removes a pd.contact.agency.DepartmentContact from class relationship collection.
	*/
	public void removeContacts(DepartmentContact anObject)
	{
		initContacts();
		contacts.remove(anObject);
	}
	/**
	* set the type reference for class member accessType
	*/
	public void setAccessType(Code accessType)
	{
		if (this.accessType == null || !this.accessType.equals(accessType))
		{
			//markModified();
		}
		if (accessType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(accessType);
		}
		setAccessTypeId("" + accessType.getOID());
		this.accessType = (Code) new mojo.km.persistence.Reference(accessType).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setAccessTypeId(String accessTypeId)
	{
		if (this.accessTypeId == null || !this.accessTypeId.equals(accessTypeId))
		{
			//markModified();
		}
		accessType = null;
		this.accessTypeId = accessTypeId;
	}
	/**
	* Sets the value of the activationDate property.
	* @param aActivationDate the new value of the activationDate property
	*/
	public void setActivationDate(Date aActivationDate)
	{
		if (this.activationDate == null || !this.activationDate.equals(aActivationDate))
		{
			//markModified();
		}
		activationDate = aActivationDate;
	}
	/**
	* set the type reference for class member address
	*/
	public void setAddress(Address address)
	{
		if (this.address == null || !this.address.equals(address))
		{
			//markModified();
		}
		//address.setContext(this, "physicalAddress");
		if (address.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(address);
		}
		setAddressId("" + address.getOID());
		this.address = (Address) new mojo.km.persistence.Reference(address).getObject();
	}
	/**
	* Set the reference value to class :: pd.address.Address
	*/
	public void setAddressId(String addressId)
	{
		if (this.addressId == null || !this.addressId.equals(addressId))
		{
			//markModified();
		}
		address = null;
		this.addressId = addressId;
	}
	/**
	* set the type reference for class member agency
	* 87191
	*/
	public void setAgency(Agency agency)
	{
		/*if (this.agency == null || !this.agency.equals(agency))
		{
			//markModified();
		}
		if (agency.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(agency);
		}*/
		setAgencyId("" + agency.getAgencyId());
		this.agency = agency;//(pd.contact.agency.Agency) new mojo.km.persistence.Reference(agency).getObject();
	}
	/**
	* Set the reference value to class :: pd.contact.agency.Agency
	*/
	public void setAgencyId(String agencyId)
	{
		if (this.agencyId == null || !this.agencyId.equals(agencyId))
		{
			//markModified();
		}
		agency = null;
		this.agencyId = agencyId;
	}
	/**
	* set the type reference for class member agencyType
	*/
	public void setAgencyType(Code agencyType)
	{
		if (this.agencyType == null || !this.agencyType.equals(agencyType))
		{
			//markModified();
		}
		if (agencyType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(agencyType);
		}
		setAgencyTypeId("" + agencyType.getOID());
		this.agencyType = (Code) new mojo.km.persistence.Reference(agencyType).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setAgencyTypeId(String agencyTypeId)
	{
		if (this.agencyTypeId == null || !this.agencyTypeId.equals(agencyTypeId))
		{
			//markModified();
		}
		agencyType = null;
		this.agencyTypeId = agencyTypeId;
	}
	/**
	* set the type reference for class member billingAddress
	*/
	public void setBillingAddress(Address billingAddress)
	{
		if (this.billingAddress == null || !this.billingAddress.equals(billingAddress))
		{
			//markModified();
		}
		//billingAddress.setContext(this, "billingAddress");
		if (billingAddress.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(billingAddress);
		}
		setBillingAddressId("" + billingAddress.getOID());
		this.billingAddress = (Address) new mojo.km.persistence.Reference(billingAddress).getObject();
	}
	/**
	* Set the reference value to class :: pd.address.Address
	*/
	public void setBillingAddressId(String billingAddressId)
	{
		if (this.billingAddressId == null || !this.billingAddressId.equals(billingAddressId))
		{
			//markModified();
		}
		billingAddress = null;
		this.billingAddressId = billingAddressId;
	}
	/**
	* Sets the value of the comments property.
	* @param aComments the new value of the comments property
	*/
	public void setComments(String aComments)
	{
		if (this.comments == null || !this.comments.equals(aComments))
		{
			//markModified();
		}
		comments = aComments;
	}
	/**
	* set the type reference for class member county
	*/
	public void setCounty(Code county)
	{
		if (this.county == null || !this.county.equals(county))
		{
			//markModified();
		}
		if (county.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(county);
		}
		setCountyId("" + county.getOID());
		this.county = (Code) new mojo.km.persistence.Reference(county).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setCountyId(String countyId)
	{
		if (this.countyId == null || !this.countyId.equals(countyId))
		{
			//markModified();
		}
		county = null;
		this.countyId = countyId;
	}
	/**
	* Sets the value of the departmentId property.
	* @param aDepartmentId the new value of the departmentId property
	*/
	public void setDepartmentId(String aDepartmentId)
	{
		if (this.departmentId == null || !this.departmentId.equals(aDepartmentId))
		{
			//markModified();
		}
		departmentId = aDepartmentId;
	}
	/**
	* Sets the value of the departmentName property.
	* @param aDepartmentName the new value of the departmentName property
	*/
	public void setDepartmentName(String aDepartmentName)
	{
		if (this.departmentName == null || !this.departmentName.equals(aDepartmentName))
		{
			//markModified();
		}
		departmentName = aDepartmentName;
	}
	/**
	* Sets the value of the fax property.
	* @param aFax the new value of the fax property
	*/
	public void setFax(String aFax)
	{
		if (this.fax == null || !this.fax.equals(aFax))
		{
			//markModified();
		}
		fax = aFax;
	}
	/**
	* Sets the value of the gritsInd property.
	* @param aGritsInd the new value of the gritsInd property
	*/
	public void setGritsInd(String aGritsInd)
	{
		if (this.gritsInd == null || !this.gritsInd.equals(aGritsInd))
		{
			//markModified();
		}
		gritsInd = aGritsInd;
	}
	/**
	* Sets the value of the labelInd property.
	* @param aLabelInd the new value of the labelInd property
	*/
	public void setLabelInd(String aLabelInd)
	{
		if (this.labelInd == null || !this.labelInd.equals(aLabelInd))
		{
			//markModified();
		}
		labelInd = aLabelInd;
	}
	/**
	* set the type reference for class member mailingAddress
	*/
	public void setMailingAddress(Address mailingAddress)
	{
		if (this.mailingAddress == null || !this.mailingAddress.equals(mailingAddress))
		{
			//markModified();
		}
		//mailingAddress.setContext(this, "mailingAddress");
		if (mailingAddress.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(mailingAddress);
		}
		setMailingAddressId("" + mailingAddress.getOID());
		this.mailingAddress = (Address) new mojo.km.persistence.Reference(mailingAddress).getObject();
	}
	/**
	* Set the reference value to class :: pd.address.Address
	*/
	public void setMailingAddressId(String mailingAddressId)
	{
		if (this.mailingAddressId == null || !this.mailingAddressId.equals(mailingAddressId))
		{
			//markModified();
		}
		mailingAddress = null;
		this.mailingAddressId = mailingAddressId;
	}
	/**
	* Sets the value of the orgCode property.
	* @param aOrgCode the new value of the orgCode property
	*/
	public void setOrgCode(String aOrgCode)
	{
		if (this.orgCode == null || !this.orgCode.equals(aOrgCode))
		{
			//markModified();
		}
		orgCode = aOrgCode;
	}
	/**
	* Sets the value of the originatingAgencyId property.
	* @param aOriginatingAgencyId the new value of the originatingAgencyId property
	*/
	public void setOriginatingAgencyId(String aOriginatingAgencyId)
	{
		if (this.originatingAgencyId == null || !this.originatingAgencyId.equals(aOriginatingAgencyId))
		{
			//markModified();
		}
		originatingAgencyId = aOriginatingAgencyId;
	}
	/**
	* set the type reference for class member setcicAccess
	*/
	public void setSetcicAccess(Code setcicAccess)
	{
		if (this.setcicAccess == null || !this.setcicAccess.equals(setcicAccess))
		{
			//markModified();
		}
		if (setcicAccess.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(setcicAccess);
		}
		setSetcicAccessId("" + setcicAccess.getOID());
		this.setcicAccess = (Code) new mojo.km.persistence.Reference(setcicAccess).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setSetcicAccessId(String setcicAccessId)
	{
		if (this.setcicAccessId == null || !this.setcicAccessId.equals(setcicAccessId))
		{
			//markModified();
		}
		setcicAccess = null;
		this.setcicAccessId = setcicAccessId;
	}
	/**
	* Sets the value of the setcicDate property.
	* @param aSetcicDate the new value of the setcicDate property
	*/
	public void setSetcicDate(Date aSetcicDate)
	{
		if (this.setcicDate == null || !this.setcicDate.equals(aSetcicDate))
		{
			//markModified();
		}
		setcicDate = aSetcicDate;
	}
	/**
	* Sets the value of the setcicInactiveDate property.
	* @param aSetcicInactiveDate the new value of the setcicInactiveDate property
	*/
	public void setSetcicInactiveDate(Date aSetcicInactiveDate)
	{
		if (this.setcicInactiveDate == null || !this.setcicInactiveDate.equals(aSetcicInactiveDate))
		{
			//markModified();
		}
		setcicInactiveDate = aSetcicInactiveDate;
	}
	/**
	* Sets the value of the setcicRenewDate property.
	* @param aSetcicRenewDate the new value of the setcicRenewDate property
	*/
	public void setSetcicRenewDate(Date aSetcicRenewDate)
	{
		if (this.setcicRenewDate == null || !this.setcicRenewDate.equals(aSetcicRenewDate))
		{
			//markModified();
		}
		setcicRenewDate = aSetcicRenewDate;
	}
	/**
	* set the type reference for class member status
	*/
	public void setStatus(Code status)
	{
		if (this.status == null || !this.status.equals(status))
		{
			//markModified();
		}
		if (status.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(status);
		}
		setStatusId("" + status.getOID());
		this.status = (Code) new mojo.km.persistence.Reference(status).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setStatusId(String statusId)
	{
		if (this.statusId == null || !this.statusId.equals(statusId))
		{
			//markModified();
		}
		status = null;
		this.statusId = statusId;
	}
	/**
	* Sets the value of the subscriberCivilActivationDate property.
	* @param aSubscriberCivilActivationDate the new value of the subscriberCivilActivationDate property
	*/
	public void setSubscriberCivilActivationDate(Date aSubscriberCivilActivationDate)
	{
		if (this.subscriberCivilActivationDate == null
			|| !this.subscriberCivilActivationDate.equals(aSubscriberCivilActivationDate))
		{
			//markModified();
		}
		subscriberCivilActivationDate = aSubscriberCivilActivationDate;
	}
	/**
	* Sets the value of the subscriberCivilTerminationDate property.
	* @param aSubscriberCivilTerminationDate the new value of the subscriberCivilTerminationDate property
	*/
	public void setSubscriberCivilTerminationDate(Date aSubscriberCivilTerminationDate)
	{
		if (this.subscriberCivilTerminationDate == null
			|| !this.subscriberCivilTerminationDate.equals(aSubscriberCivilTerminationDate))
		{
			//markModified();
		}
		subscriberCivilTerminationDate = aSubscriberCivilTerminationDate;
	}
	/**
	* Sets the value of the subscriberCriminalActivationDate property.
	* @param aSubscriberCriminalActivationDate the new value of the subscriberCriminalActivationDate property
	*/
	public void setSubscriberCriminalActivationDate(Date aSubscriberCriminalActivationDate)
	{
		if (this.subscriberCriminalActivationDate == null
			|| !this.subscriberCriminalActivationDate.equals(aSubscriberCriminalActivationDate))
		{
			//markModified();
		}
		subscriberCriminalActivationDate = aSubscriberCriminalActivationDate;
	}
	/**
	* Sets the value of the subscriberCriminalTerminationDate property.
	* @param aSubscriberCriminalTerminationDate the new value of the subscriberCriminalTerminationDate property
	*/
	public void setSubscriberCriminalTerminationDate(Date aSubscriberCriminalTerminationDate)
	{
		if (this.subscriberCriminalTerminationDate == null
			|| !this.subscriberCriminalTerminationDate.equals(aSubscriberCriminalTerminationDate))
		{
			//markModified();
		}
		subscriberCriminalTerminationDate = aSubscriberCriminalTerminationDate;
	}
	/**
	* Sets the value of the terminationDate property.
	* @param aTerminationDate the new value of the terminationDate property
	*/
	public void setTerminationDate(Date aTerminationDate)
	{
		if (this.terminationDate == null || !this.terminationDate.equals(aTerminationDate))
		{
			//markModified();
		}
		terminationDate = aTerminationDate;
	}
	/**
	* Sets the value of the warrantConfirmationPhone property.
	* @param aWarrantConfirmationPhone the new value of the warrantConfirmationPhone property
	*/
	public void setWarrantConfirmationPhone(String aWarrantConfirmationPhone)
	{
		if (this.warrantConfirmationPhone == null || !this.warrantConfirmationPhone.equals(aWarrantConfirmationPhone))
		{
			//markModified();
		}
		warrantConfirmationPhone = aWarrantConfirmationPhone;
	}
	/**
	* Sets the value of the warrantConfirmationPhoneExt property.
	* @param aWarrantConfirmationPhoneExt the new value of the warrantConfirmationPhoneExt property
	*/
	public void setWarrantConfirmationPhoneExt(String aWarrantConfirmationPhoneExt)
	{
		if (this.warrantConfirmationPhoneExt == null
			|| !this.warrantConfirmationPhoneExt.equals(aWarrantConfirmationPhoneExt))
		{
			//markModified();
		}
		warrantConfirmationPhoneExt = aWarrantConfirmationPhoneExt;
	}
	/**
	* @return pd.contact.Department
	* @param departmentId
	* @roseuid 4107B06D01B5
	*/
	static public Department find(String departmentId)
	{
		Department department = null;
	/*	IHome home = new Home();
		department = (Department) home.find(departmentId, Department.class);*/
		if(departmentId!=null && !departmentId.isEmpty()){
		    department =  DepartmentHelper.getDepartmentFromSecurityManager(departmentId); //87191
		}
		return department;
	}
	/**
	* @return java.util.Iterator
	* @roseuid 41123AE00111
	*/
	/*static public Iterator findAll()//87191
	{
		IHome home = new Home();
		Iterator iter = home.findAll(Department.class);
		return iter;
	}*/
	/**
	* @return java.util.Iterator
	* @param event
	* @roseuid 4107B06D01BB
	*/
	/*static public Iterator findAll(IEvent event) //87191
	{
		IHome home = new Home();
		Iterator iter = home.findAll(event, Department.class);
		return iter;
	}*/
	/**
	* @return java.util.Iterator
	* @param agencyId
	* @roseuid 4177C29D03A9
	*/
	/*static public Iterator findAll(String attrName, String attrValue) //87191
	{
		IHome home = new Home();
		Iterator departments = null;
		departments = home.findAll(attrName, attrValue, Department.class);
		return departments;
	}*/

	/**
	* @param deptEvent
	*/
	/*public static MetaDataResponseEvent findMeta(IEvent deptEvent) { //87191
		IHome home = new Home();
		MetaDataResponseEvent mdre = home.findMeta(deptEvent, Department.class);
		return mdre;
	}*/

	/*public void createOID()
	{
		//markModified();
		//IHome home = new Home();
		//home.bind(this);
	}*/
	/**
	* @param departmentEvent
	*/
	public void setDepartment(UpdateDepartmentEvent departmentEvent)
	{

		this.setDepartmentId(departmentEvent.getDepartmentId());
		this.setDepartmentName(departmentEvent.getDepartmentName());
		this.setOrgCode(departmentEvent.getOrgCode());
		this.setAgencyId(departmentEvent.getAgencyId());
		this.setOriginatingAgencyId(departmentEvent.getOriginatingAgencyId());
		this.setCreateOfficerProfileInd(departmentEvent.getCreateOfficerProfileInd());
		this.setAccessTypeId(departmentEvent.getAccessType());
		this.setAgencyTypeId(departmentEvent.getAgencyTypeId());
		if (departmentEvent.getStatus() == null || departmentEvent.getStatus().trim().equals(""))
		{
			this.setStatusId(PDSecurityConstants.ACTIVE);
		}
		else
		{
			this.setStatusId(departmentEvent.getStatus());
		}
		this.setFax(departmentEvent.getFax());
		this.setActivationDate(departmentEvent.getActivationDate());
		this.setTerminationDate(departmentEvent.getInactiveDate());
		this.setSubscriberCivilActivationDate(departmentEvent.getSubscriberCivilActivationDate());
		this.setSubscriberCivilTerminationDate(departmentEvent.getSubscriberCivilTerminationDate());
		this.setSubscriberCriminalActivationDate(departmentEvent.getSubscriberCriminalActivationDate());
		this.setSubscriberCriminalTerminationDate(departmentEvent.getSubscriberCriminalTerminationDate());
		this.setLabelInd(departmentEvent.getLabelInd());
		this.setComments(departmentEvent.getComments());
		this.setCountyId(departmentEvent.getCounty());
		this.setGritsInd(departmentEvent.getGritsAccessInd());
		this.setWarrantConfirmationPhone(departmentEvent.getWarrantConfirmationPhone());
		this.setWarrantConfirmationPhoneExt(departmentEvent.getWarrantConfirmationPhoneExt());
		this.setSetcicDate(departmentEvent.getSetcicDate());
		this.setSetcicInactiveDate(departmentEvent.getSetcicInactiveDate());
		this.setSetcicRenewDate(departmentEvent.getSetcicRenewDate());
		this.setSetcicAccessId(departmentEvent.getSetcicAccessInd());
	}
	/**
	* description: delete contacts
	*/
/*	public void deleteContacts()
	{
		//markModified();
		Collection contacts = this.getContacts();
		if (contacts != null && contacts.size() > 0)
		{
			Iterator iter = contacts.iterator();
			while (iter.hasNext())
			{
				Contact contact = (Contact) iter.next();
				contact.delete();
			}
		}
	}*/
	/**
	* @param departmentContact
	*/
/*	public void addContact(DepartmentContact departmentContact)
	{
		//markModified();
		UpdateContactEvent contactEvent = new UpdateContactEvent();
		departmentContact = new DepartmentContact();
		departmentContact.setDepartmentId(contactEvent.getDepartmentId());
		departmentContact.setFirstName(contactEvent.getFirstName());
		departmentContact.setLastName(contactEvent.getLastName());
		departmentContact.setMiddleName(contactEvent.getMiddleName());
		departmentContact.setTitle(contactEvent.getTitle());
		departmentContact.setUserID(contactEvent.getUserID());
		departmentContact.setPhoneNum(contactEvent.getPhone());
		departmentContact.setPhoneExt(contactEvent.getPhoneExt());
		departmentContact.setLiaisonTrainingInd(contactEvent.getLiaisonTrainingInd());
		departmentContact.setEmail(contactEvent.getEmail());
	}*/
	/**
	* @return 
	*/
	public String getInUseInd()
	{
		//fetch();
		return inUseInd;
	}
	/**
	* @param string
	*/
	public void setInUseInd(String aInUseInd)
	{
		/*if (this.inUseInd == null || !this.inUseInd.equals(aInUseInd))
		{
			markModified();
		}*/
		inUseInd = aInUseInd;
	}
	/**
	* @return 
	*/
	public String getAgencyName()
	{
		//fetch();
		return agencyName;
	}
	/**
	* @param string
	*/
	public void setAgencyName(String agencyName)
	{
		/*if (this.agencyName == null || !this.agencyName.equals(agencyName))
		{
			markModified();
		}*/
		this.agencyName = agencyName;
	}
	/**
	* @param collection
	*/
	public void setContacts(Collection collection)
	{
		/*if (this.contacts == null || !this.contacts.equals(collection))
		{
			markModified();
		}*/
		contacts = collection;
	}
	/**
	* returns a collection of pd.contact.agency.DepartmentContact
	*/
	public DepartmentContact getPrimaryContact()
	{
		//fetch();
		initContacts();
		DepartmentContact contact = null;
		Iterator contactIter = contacts.iterator();
		while (contactIter.hasNext())
		{
			contact = (DepartmentContact) contactIter.next();
			if (contact.getPrimaryContact().equals("Y"))
			{
				return contact;
			}
		}
		return null;
	}
	/**
	* @return 
	*/
	public String getCreateOfficerProfileInd()
	{
	//	fetch();
		return createOfficerProfileInd;
	}
	/**
	* @param string
	*/
	public void setCreateOfficerProfileInd(String string)
	{
		if (this.createOfficerProfileInd == null || !this.createOfficerProfileInd.equals(string))
		{
			//markModified();
		}
		createOfficerProfileInd = string;
	}
	/**
	 * @return Returns the departmentUserCount.
	 */
	public String getDepartmentUserCount() {
		//fetch();
		return this.departmentUserCount;
	}
	/**
	 * @param departmentUserCount The departmentUserCount to set.
	 */
	public void setDepartmentUserCount(String departmentUserCount) {
		if (this.departmentUserCount == null || !this.departmentUserCount.equals(departmentUserCount))
		{
			//markModified();
		}
		this.departmentUserCount = departmentUserCount;
	}
}
