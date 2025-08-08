//Source file: C:\\views\\dev\\app\\src\\messaging\\agency\\UpdateDepartmentEvent.java

package messaging.agency;

import java.util.Date;

import mojo.km.messaging.Composite.CompositeRequest;

public class UpdateDepartmentEvent extends CompositeRequest
{
	private String accessType;
	private Date activationDate;
	private String agencyTypeId;
	private String agencyId;
	private String comments;
	private String county;
	private String departmentId;
	private String departmentName;
	private String fax;
	private String gritsAccessInd;
	private Date inactiveDate;
	private String labelInd;
	private String orgCode;
	private String originatingAgencyId;
	private String createOfficerProfileInd;
	private String setcicAccessInd;
	private Date setcicDate;
	private Date setcicInactiveDate;
	private Date setcicRenewDate;
	private String status;
	private Date subscriberCivilActivationDate;
	private Date subscriberCivilTerminationDate;
	private Date subscriberCriminalActivationDate;
	private Date subscriberCriminalTerminationDate;
	private String warrantConfirmationPhone;
	private String warrantConfirmationPhoneExt;
	private String setcicContactPhone;
	private String setcicContactPhoneExt;
	private String setcicContactLastName;
	private	String setcicContactFirstName;
	private String setcicContactMiddleName;
	

	/**
	 * @roseuid 4306348E0398
	 */
	public UpdateDepartmentEvent()
	{

	}

	/**
	 * Access method for the accessType property.
	 * 
	 * @return   the current value of the accessType property
	 */
	public String getAccessType()
	{
		return accessType;
	}

	/**
	 * Access method for the activationDate property.
	 * 
	 * @return   the current value of the activationDate property
	 */
	public Date getActivationDate()
	{
		return activationDate;
	}

	/**
	 * Access method for the agencyId property.
	 * 
	 * @return   the current value of the agencyId property
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	 * Access method for the comments property.
	 * 
	 * @return   the current value of the comments property
	 */
	public String getComments()
	{
		return comments;
	}

	/**
	 * Access method for the county property.
	 * 
	 * @return   the current value of the county property
	 */
	public String getCounty()
	{
		return county;
	}

	/**
	 * Access method for the departmentId property.
	 * 
	 * @return   the current value of the departmentId property
	 */
	public String getDepartmentId()
	{
		return departmentId;
	}

	/**
	 * Access method for the departmentName property.
	 * 
	 * @return   the current value of the departmentName property
	 */
	public String getDepartmentName()
	{
		return departmentName;
	}

	/**
	 * Access method for the fax property.
	 * 
	 * @return   the current value of the fax property
	 */
	public String getFax()
	{
		return fax;
	}

	/**
	 * Access method for the gritsAccessInd property.
	 * 
	 * @return   the current value of the gritsAccessInd property
	 */
	public String getGritsAccessInd()
	{
		return gritsAccessInd;
	}

	/**
	 * Access method for the inactiveDate property.
	 * 
	 * @return   the current value of the inactiveDate property
	 */
	public Date getInactiveDate()
	{
		return inactiveDate;
	}

	/**
	 * Access method for the labelInd property.
	 * 
	 * @return   the current value of the labelInd property
	 */
	public String getLabelInd()
	{
		return labelInd;
	}

	/**
	 * Access method for the orgCode property.
	 * 
	 * @return   the current value of the orgCode property
	 */
	public String getOrgCode()
	{
		return orgCode;
	}

	/**
	 * Access method for the originatingAgencyId property.
	 * 
	 * @return   the current value of the originatingAgencyId property
	 */
	public String getOriginatingAgencyId()
	{
		return originatingAgencyId;
	}

	/**
	 * Access method for the setcicAccessInd property.
	 * 
	 * @return   the current value of the setcicAccessInd property
	 */
	public String getSetcicAccessInd()
	{
		return setcicAccessInd;
	}

	/**
	 * Access method for the setcicDate property.
	 * 
	 * @return   the current value of the setcicDate property
	 */
	public Date getSetcicDate()
	{
		return setcicDate;
	}

	/**
	 * Access method for the setcicInactiveDate property.
	 * 
	 * @return   the current value of the setcicInactiveDate property
	 */
	public Date getSetcicInactiveDate()
	{
		return setcicInactiveDate;
	}

	/**
	 * Access method for the setcicRenewDate property.
	 * 
	 * @return   the current value of the setcicRenewDate property
	 */
	public Date getSetcicRenewDate()
	{
		return setcicRenewDate;
	}

	/**
	 * Access method for the status property.
	 * 
	 * @return   the current value of the status property
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * Access method for the subscriberCivilActivationDate property.
	 * 
	 * @return   the current value of the subscriberCivilActivationDate property
	 */
	public Date getSubscriberCivilActivationDate()
	{
		return subscriberCivilActivationDate;
	}

	/**
	 * Access method for the subscriberCivilTerminationDate property.
	 * 
	 * @return   the current value of the subscriberCivilTerminationDate property
	 */
	public Date getSubscriberCivilTerminationDate()
	{
		return subscriberCivilTerminationDate;
	}

	/**
	 * Access method for the subscriberCriminalActivationDate property.
	 * 
	 * @return   the current value of the subscriberCriminalActivationDate property
	 */
	public Date getSubscriberCriminalActivationDate()
	{
		return subscriberCriminalActivationDate;
	}

	/**
	 * Access method for the subscriberCriminalTerminationDate property.
	 * 
	 * @return   the current value of the subscriberCriminalTerminationDate property
	 */
	public Date getSubscriberCriminalTerminationDate()
	{
		return subscriberCriminalTerminationDate;
	}

	/**
	 * Access method for the warrantConfirmationPhone property.
	 * 
	 * @return   the current value of the warrantConfirmationPhone property
	 */
	public String getWarrantConfirmationPhone()
	{
		return warrantConfirmationPhone;
	}

	/**
	 * Access method for the warrantConfirmationPhoneExt property.
	 * 
	 * @return   the current value of the warrantConfirmationPhoneExt property
	 */
	public String getWarrantConfirmationPhoneExt()
	{
		return warrantConfirmationPhoneExt;
	}

	/**
	 * Sets the value of the accessType property.
	 * 
	 * @param aAccessType the new value of the accessType property
	 */
	public void setAccessType(String aAccessType)
	{
		accessType = aAccessType;
	}

	/**
	 * Sets the value of the activationDate property.
	 * 
	 * @param aActivationDate the new value of the activationDate property
	 */
	public void setActivationDate(Date aActivationDate)
	{
		activationDate = aActivationDate;
	}

	/**
	 * Sets the value of the agencyId property.
	 * 
	 * @param aAgencyId the new value of the agencyId property
	 */
	public void setAgencyId(String aAgencyId)
	{
		agencyId = aAgencyId;
	}

	/**
	 * Sets the value of the comments property.
	 * 
	 * @param aComments the new value of the comments property
	 */
	public void setComments(String aComments)
	{
		comments = aComments;
	}

	/**
	 * Sets the value of the county property.
	 * 
	 * @param aCounty the new value of the county property
	 */
	public void setCounty(String aCounty)
	{
		county = aCounty;
	}

	/**
	 * Sets the value of the departmentId property.
	 * 
	 * @param aDepartmentId the new value of the departmentId property
	 */
	public void setDepartmentId(String aDepartmentId)
	{
		departmentId = aDepartmentId;
	}

	/**
	 * Sets the value of the departmentName property.
	 * 
	 * @param aDepartmentName the new value of the departmentName property
	 */
	public void setDepartmentName(String aDepartmentName)
	{
		departmentName = aDepartmentName;
	}

	/**
	 * Sets the value of the fax property.
	 * 
	 * @param aFax the new value of the fax property
	 */
	public void setFax(String aFax)
	{
		fax = aFax;
	}

	/**
	 * Sets the value of the gritsAccessInd property.
	 * 
	 * @param aGritsAccessInd the new value of the gritsAccessInd property
	 */
	public void setGritsAccessInd(String aGritsAccessInd)
	{
		gritsAccessInd = aGritsAccessInd;
	}

	/**
	 * Sets the value of the inactiveDate property.
	 * 
	 * @param aInactiveDate the new value of the inactiveDate property
	 */
	public void setInactiveDate(Date aInactiveDate)
	{
		inactiveDate = aInactiveDate;
	}

	/**
	 * Sets the value of the labelInd property.
	 * 
	 * @param aLabelInd the new value of the labelInd property
	 */
	public void setLabelInd(String aLabelInd)
	{
		labelInd = aLabelInd;
	}

	/**
	 * Sets the value of the orgCode property.
	 * 
	 * @param aOrgCode the new value of the orgCode property
	 */
	public void setOrgCode(String aOrgCode)
	{
		orgCode = aOrgCode;
	}

	/**
	 * Sets the value of the originatingAgencyId property.
	 * 
	 * @param aOriginatingAgencyId the new value of the originatingAgencyId property
	 */
	public void setOriginatingAgencyId(String aOriginatingAgencyId)
	{
		originatingAgencyId = aOriginatingAgencyId;
	}

	/**
	 * Sets the value of the setcicAccessInd property.
	 * 
	 * @param aSetcicAccessInd the new value of the setcicAccessInd property
	 */
	public void setSetcicAccessInd(String aSetcicAccessInd)
	{
		setcicAccessInd = aSetcicAccessInd;
	}

	/**
	 * Sets the value of the setcicDate property.
	 * 
	 * @param aSetcicDate the new value of the setcicDate property
	 */
	public void setSetcicDate(Date aSetcicDate)
	{
		setcicDate = aSetcicDate;
	}

	/**
	 * Sets the value of the setcicInactiveDate property.
	 * 
	 * @param aSetcicInactiveDate the new value of the setcicInactiveDate property
	 */
	public void setSetcicInactiveDate(Date aSetcicInactiveDate)
	{
		setcicInactiveDate = aSetcicInactiveDate;
	}

	/**
	 * Sets the value of the setcicRenewDate property.
	 * 
	 * @param aSetcicRenewDate the new value of the setcicRenewDate property
	 */
	public void setSetcicRenewDate(Date aSetcicRenewDate)
	{
		setcicRenewDate = aSetcicRenewDate;
	}

	/**
	 * Sets the value of the status property.
	 * 
	 * @param aStatus the new value of the status property
	 */
	public void setStatus(String aStatus)
	{
		status = aStatus;
	}

	/**
	 * Sets the value of the subscriberCivilActivationDate property.
	 * 
	 * @param aSubscriberCivilActivationDate the new value of the subscriberCivilActivationDate property
	 */
	public void setSubscriberCivilActivationDate(Date aSubscriberCivilActivationDate)
	{
		subscriberCivilActivationDate = aSubscriberCivilActivationDate;
	}

	/**
	 * Sets the value of the subscriberCivilTerminationDate property.
	 * 
	 * @param aSubscriberCivilTerminationDate the new value of the subscriberCivilTerminationDate property
	 */
	public void setSubscriberCivilTerminationDate(Date aSubscriberCivilTerminationDate)
	{
		subscriberCivilTerminationDate = aSubscriberCivilTerminationDate;
	}

	/**
	 * Sets the value of the subscriberCriminalActivationDate property.
	 * 
	 * @param aSubscriberCriminalActivationDate the new value of the subscriberCriminalActivationDate property
	 */
	public void setSubscriberCriminalActivationDate(Date aSubscriberCriminalActivationDate)
	{
		subscriberCriminalActivationDate = aSubscriberCriminalActivationDate;
	}

	/**
	 * Sets the value of the subscriberCriminalTerminationDate property.
	 * 
	 * @param aSubscriberCriminalTerminationDate the new value of the subscriberCriminalTerminationDate property
	 */
	public void setSubscriberCriminalTerminationDate(Date aSubscriberCriminalTerminationDate)
	{
		subscriberCriminalTerminationDate = aSubscriberCriminalTerminationDate;
	}

	/**
	 * Sets the value of the warrantConfirmationPhone property.
	 * 
	 * @param aWarrantConfirmationPhone the new value of the warrantConfirmationPhone property
	 */
	public void setWarrantConfirmationPhone(String aWarrantConfirmationPhone)
	{
		warrantConfirmationPhone = aWarrantConfirmationPhone;
	}

	/**
	 * Sets the value of the warrantConfirmationPhoneExt property.
	 * 
	 * @param aWarrantConfirmationPhoneExt the new value of the warrantConfirmationPhoneExt property
	 */
	public void setWarrantConfirmationPhoneExt(String aWarrantConfirmationPhoneExt)
	{
		warrantConfirmationPhoneExt = aWarrantConfirmationPhoneExt;
	}
	/**
	 * @return
	 */
	public String getSetcicContactPhone()
	{
		return setcicContactPhone;
	}

	/**
	 * @return
	 */
	public String getSetcicContactPhoneExt()
	{
		return setcicContactPhoneExt;
	}

	/**
	 * @param string
	 */
	public void setSetcicContactPhone(String string)
	{
		setcicContactPhone = string;
	}

	/**
	 * @param string
	 */
	public void setSetcicContactPhoneExt(String string)
	{
		setcicContactPhoneExt = string;
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
	public String getSetcicContactMiddleName()
	{
		return setcicContactMiddleName;
	}

	/**
	 * @param string
	 */
	public void setSetcicContactFirstName(String string)
	{
		setcicContactFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setSetcicContactLastName(String string)
	{
		setcicContactLastName = string;
	}

	/**
	 * @param string
	 */
	public void setSetcicContactMiddleName(String string)
	{
		setcicContactMiddleName = string;
	}

	/**
	 * @return
	 */
	public String getAgencyTypeId()
	{
		return agencyTypeId;
	}

	/**
	 * @param string
	 */
	public void setAgencyTypeId(String string)
	{
		agencyTypeId = string;
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

}