//Source file: C:\\views\\archproduction\\app\\src\\messaging\\user\\GetUsersEvent.java

package messaging.user;

import mojo.km.messaging.RequestEvent;

public class GetUsersEvent extends RequestEvent
{
	private String agencyId;
	private String agencyName;
	private String departmentId;
	private String departmentName;
	private String firstName;
	private String lastName;
	private String middleName;
	private String userTypeId;
	private String logonId;
	
	/**
	 * @roseuid 4297206D02D0
	 */
	public GetUsersEvent()
	{

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
	 * Access method for the agencyName property.
	 * 
	 * @return   the current value of the agencyName property
	 */
	public String getAgencyName()
	{
		return agencyName;
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
	 * Sets the value of the agencyId property.
	 * 
	 * @param aAgencyId the new value of the agencyId property
	 */
	public void setAgencyId(String aAgencyId)
	{
		agencyId = aAgencyId;
	}

	/**
	 * Sets the value of the agencyName property.
	 * 
	 * @param aAgencyName the new value of the agencyName property
	 */
	public void setAgencyName(String aAgencyName)
	{
		agencyName = aAgencyName;
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
	 * @return
	 */
	public String getUserTypeId()
	{
		return userTypeId;
	}

	/**
	 * @param string
	 */
	public void setUserTypeId(String string)
	{
		userTypeId = string;
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
	public String getLogonId()
	{
		return logonId;
	}

	/**
	 * @param string
	 */
	public void setLogonId(String string)
	{
		logonId = string;
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
}