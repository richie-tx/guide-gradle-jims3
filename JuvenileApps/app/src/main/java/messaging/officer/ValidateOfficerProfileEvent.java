//Source file: C:\\views\\dev\\app\\src\\messaging\\officer\\ValidateOfficerProfileEvent.java

package messaging.officer;

import mojo.km.messaging.RequestEvent;

public class ValidateOfficerProfileEvent extends RequestEvent
{
	public String badgeNum;
	public String departmentId;
	public String departmentName;
	public String orgCode;
	public String agencyId;
	public String logonId;
	public String otherIdNum;
	private String officerProfileId;
	private String officerTypeId;

	/**
	 * @roseuid 42E67A010378
	 */
	public ValidateOfficerProfileEvent()
	{

	}
	
	
	/**
	 * @return the departmentName
	 */
	public String getDepartmentName()
	{
	    return departmentName;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName)
	{
	    this.departmentName = departmentName;
	}

	/**
	 * @return the orgCode
	 */
	public String getOrgCode()
	{
	    return orgCode;
	}

	/**
	 * @param orgCode the orgCode to set
	 */
	public void setOrgCode(String orgCode)
	{
	    this.orgCode = orgCode;
	}

	/**
	 * @return the agencyId
	 */
	public String getAgencyId()
	{
	    return agencyId;
	}

	/**
	 * @param agencyId the agencyId to set
	 */
	public void setAgencyId(String agencyId)
	{
	    this.agencyId = agencyId;
	}
	

	/**
	 * Access method for the badgeNum property.
	 * 
	 * @return   the current value of the badgeNum property
	 */
	public String getBadgeNum()
	{
		return badgeNum;
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
	 * Access method for the logonId property.
	 * 
	 * @return   the current value of the logonId property
	 */
	public String getLogonId()
	{
		return logonId;
	}

	/**
	 * Access method for the otherIdNum property.
	 * 
	 * @return   the current value of the otherIdNum property
	 */
	public String getOtherIdNum()
	{
		return otherIdNum;
	}

	/**
	 * Sets the value of the badgeNum property.
	 * 
	 * @param aBadgeNum the new value of the badgeNum property
	 */
	public void setBadgeNum(String aBadgeNum)
	{
		badgeNum = aBadgeNum;
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
	 * Sets the value of the logonId property.
	 * 
	 * @param aLogonId the new value of the logonId property
	 */
	public void setLogonId(String aLogonId)
	{
		logonId = aLogonId;
	}

	/**
	 * Sets the value of the otherIdNum property.
	 * 
	 * @param otherIdNum the new value of the otherIdNum property
	 */
	public void setOtherIdNum(String aOtherIdNum)
	{
		otherIdNum = aOtherIdNum;
	}

	/**
	 * @return
	 */
	public String getOfficerProfileId()
	{
		return officerProfileId;
	}

	/**
	 * @param string
	 */
	public void setOfficerProfileId(String string)
	{
		officerProfileId = string;
	}

	/**
	 * @return Returns the officerTypeId.
	 */
	public String getOfficerTypeId() {
		return officerTypeId;
	}
	/**
	 * @param officerTypeId The officerTypeId to set.
	 */
	public void setOfficerTypeId(String officerTypeId) {
		this.officerTypeId = officerTypeId;
	}
}
