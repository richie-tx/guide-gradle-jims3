//Source file: C:\\views\\archproduction\\app\\src\\messaging\\security\\UpdateUserGroupEvent.java

package messaging.security;

import mojo.km.messaging.RequestEvent;

public class UpdateUserGroupEvent extends RequestEvent
{
	public String agencyId;
	public String status;
	public String userGroupDescription;
	public String userGroupId;
	public String userGroupName;
	
	/**
	 * @roseuid 429720C001F7
	 */
	public UpdateUserGroupEvent()
	{

	}

	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
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
	 * Access method for the userGroupDescription property.
	 * 
	 * @return   the current value of the userGroupDescription property
	 */
	public String getUserGroupDescription()
	{
		return userGroupDescription;
	}
	/**
	 * @return
	 */
	public String getUserGroupId()
	{
		return userGroupId;
	}

	/**
	 * Access method for the userGroupName property.
	 * 
	 * @return   the current value of the userGroupName property
	 */
	public String getUserGroupName()
	{
		return userGroupName;
	}

	/**
	 * @param agencyId
	 */
	public void setAgencyId(String agencyId)
	{
		this.agencyId = agencyId;
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
	 * Sets the value of the userGroupDescription property.
	 * 
	 * @param aUserGroupDescription the new value of the userGroupDescription property
	 */
	public void setUserGroupDescription(String aUserGroupDescription)
	{
		userGroupDescription = aUserGroupDescription;
	}

	/**
	 * @param userGroupId
	 */
	public void setUserGroupId(String aUserGroupId)
	{
		userGroupId = aUserGroupId;
	}

	/**
	 * Sets the value of the userGroupName property.
	 * 
	 * @param aUserGroupName the new value of the userGroupName property
	 */
	public void setUserGroupName(String aUserGroupName)
	{
		userGroupName = aUserGroupName;
	}

}