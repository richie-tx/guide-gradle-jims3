//Source file: C:\\views\\dev\\app\\src\\messaging\\officer\\GetOfficerProfilesEvent.java

package messaging.officer;

import mojo.km.messaging.RequestEvent;

public class GetOfficerProfilesEvent extends RequestEvent
{
	private String agencyId;
	private String badgeNum;
	private String departmentName;
	private String departmentId;
	private String firstName;
	private String lastName;
	private String logonId;
	private String officerTypeId;
	private String otherIdNum;
	private String status;
	private String middleName;
	private String managerId;

	/**
	 * @roseuid 42E679FE0339
	 */
	public GetOfficerProfilesEvent()
	{

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
	 * Access method for the departmentName property.
	 * 
	 * @return   the current value of the departmentName property
	 */
	public String getDepartmentName()
	{
		return departmentName;
	}

	/**
	 * Access method for the firstName property.
	 * 
	 * @return   the current value of the firstName property
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * Access method for the lastName property.
	 * 
	 * @return   the current value of the lastName property
	 */
	public String getLastName()
	{
		return lastName;
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
	 * Access method for the officerTypeId property.
	 * 
	 * @return   the current value of the officerTypeId property
	 */
	public String getOfficerTypeId()
	{
		return officerTypeId;
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
	 * Sets the value of the departmentName property.
	 * 
	 * @param aDepartmentName the new value of the departmentName property
	 */
	public void setDepartmentName(String aDepartmentName)
	{
		departmentName = aDepartmentName;
	}

	/**
	 * Sets the value of the firstName property.
	 * 
	 * @param aFirstName the new value of the firstName property
	 */
	public void setFirstName(String aFirstName)
	{
		firstName = aFirstName;
	}

	/**
	 * Sets the value of the lastName property.
	 * 
	 * @param aLastName the new value of the lastName property
	 */
	public void setLastName(String aLastName)
	{
		lastName = aLastName;
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
	 * Sets the value of the officerTypeId property.
	 * 
	 * @param aOfficerTypeId the new value of the officerTypeId property
	 */
	public void setOfficerTypeId(String aOfficerTypeId)
	{
		officerTypeId = aOfficerTypeId;
	}

	/**
	 * Sets the value of the otherIdNum property.
	 * 
	 * @param aOtherIdNum the new value of the otherIdNum property
	 */
	public void setOtherIdNum(String aOtherIdNum)
	{
		otherIdNum = aOtherIdNum;
	}
	/**
	 * @return
	 */
	public String getDepartmentId()
	{
		return departmentId;
	}

	/**
	 * @param string
	 */
	public void setDepartmentId(String string)
	{
		departmentId = string;
	}

	/**
	 * @return
	 */
	public String getStatus()
	{
		return status;
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
	public void setAgencyId(String string)
	{
		agencyId = string;
		
	}
	
	/**
	 * @param string
	 */
	public String getAgencyId()
	{
		return agencyId;
	
	}
	/**
	 * @return Returns the middleName.
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName The middleName to set.
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
}
