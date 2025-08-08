//Source file: C:\\views\\archproduction\\app\\src\\messaging\\security\\DeleteUserGroupEvent.java

package messaging.security;

import mojo.km.messaging.RequestEvent;

public class DeleteUserGroupEvent extends RequestEvent
{
	public String userGroupId;

	/**
	 * @roseuid 429720C0008F
	 */
	public DeleteUserGroupEvent()
	{

	}

	/**
	 * Access method for the userGroupId property.
	 * 
	 * @return   the current value of the userGroupId property
	 */
	public String getUserGroupId()
	{
		return userGroupId;
	}

	/**
	 * Sets the value of the userGroupId property.
	 * 
	 * @param aUserGroupId the new value of the userGroupId property
	 */
	public void setUserGroupId(String aUserGroupId)
	{
		userGroupId = aUserGroupId;
	}
}