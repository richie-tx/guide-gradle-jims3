//Source file: C:\\views\\archproduction\\app\\src\\messaging\\security\\ValidateUserGroupDetailsEvent.java

package messaging.security;

import mojo.km.messaging.RequestEvent;

public class ValidateUserGroupDetailsEvent extends RequestEvent
{
	public String agencyId;
	public String category;
	public String userGroupDescription;
	public String userGroupName;

	/**
	 * @roseuid 429720C00283
	 */
	public ValidateUserGroupDetailsEvent()
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
	 * @return
	 */
	public String getCategory()
	{
		return category;
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
	 * Access method for the userGroupName property.
	 * 
	 * @return   the current value of the userGroupName property
	 */
	public String getUserGroupName()
	{
		return userGroupName;
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
	 * @param category
	 */
	public void setCategory(String category)
	{
		this.category = category;
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
	 * Sets the value of the userGroupName property.
	 * 
	 * @param aUserGroupName the new value of the userGroupName property
	 */
	public void setUserGroupName(String aUserGroupName)
	{
		userGroupName = aUserGroupName;
	}

}