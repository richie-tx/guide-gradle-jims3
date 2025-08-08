/*
 * Created on Jun 1, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.security;

import mojo.km.messaging.RequestEvent;

/**
 * @author dnikolis
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CreateUserGroupEvent extends RequestEvent
{
	public String agencyId;
	public String status;
	public String userGroupDescription;
	public String userGroupName;
	public String category;
	
	/**
	 * @roseuid 429720C001F7
	 */
	public CreateUserGroupEvent()
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
	 * Access method for the userGroupName property.
	 * 
	 * @return   the current value of the userGroupName property
	 */
	public String getUserGroupName()
	{
		return userGroupName;
	}

	/**
	 * @return
	 */
	public String getCategory()
	{
		return category;
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
	 * Sets the value of the userGroupName property.
	 * 
	 * @param aUserGroupName the new value of the userGroupName property
	 */
	public void setUserGroupName(String aUserGroupName)
	{
		userGroupName = aUserGroupName;
	}

	/**
	 * @param category
	 */
	public void setCategory(String category)
	{
		this.category = category;
	}

}