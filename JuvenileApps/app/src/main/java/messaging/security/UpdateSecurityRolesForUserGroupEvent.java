//Source file: C:\\views\\archproduction\\app\\src\\messaging\\security\\UpdateSecurityRolesForUserGroupEvent.java

package messaging.security;

import java.util.Collection;

import mojo.km.messaging.Composite.CompositeRequest;

public class UpdateSecurityRolesForUserGroupEvent extends CompositeRequest
{
	private String userGroupId;
	private Collection roles;

	/**
	 * @roseuid 429720DD018A
	 */
	public UpdateSecurityRolesForUserGroupEvent()
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
	/**
	 * @return
	 */
	public Collection getRoles()
	{
		return roles;
	}

	/**
	 * @param collection
	 */
	public void setRoles(Collection collection)
	{
		roles = collection;
	}

}
