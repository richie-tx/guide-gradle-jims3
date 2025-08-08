//Source file: C:\\views\\archproduction\\app\\src\\messaging\\security\\UpdateSecurityRolesForUserEvent.java

package messaging.security;

import java.util.Collection;

import mojo.km.messaging.Composite.CompositeRequest;

public class UpdateSecurityRolesForUserEvent extends CompositeRequest
{
	private String logonId;
	private Collection roles;

	/**
	 * @roseuid 429720DD014B
	 */
	public UpdateSecurityRolesForUserEvent()
	{

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
	 * Sets the value of the logonId property.
	 * 
	 * @param aLogonId the new value of the logonId property
	 */
	public void setLogonId(String aLogonId)
	{
		logonId = aLogonId;
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
