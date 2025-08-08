//Source file: C:\\views\\archproduction\\app\\src\\messaging\\security\\UpdateUserGroupUsersEvent.java

package messaging.security;

import mojo.km.messaging.Composite.CompositeRequest;

public class UpdateUserGroupUsersEvent extends CompositeRequest
{
	public String userGroupId;

	/**
	 * @roseuid 429720C00245
	 */
	public UpdateUserGroupUsersEvent()
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
