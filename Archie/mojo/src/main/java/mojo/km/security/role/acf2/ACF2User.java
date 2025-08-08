package mojo.km.security.role.acf2;// no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.
/*package mojo.km.security.role.acf2;

import java.util.Enumeration;
import java.util.Iterator;

import mojo.km.security.NamingException;
import mojo.km.security.helper.IPermissions;
import mojo.km.security.helper.Permissions;

*//**
 * Authenticates a user and retrieves all of the groups they have membership
 * with. The user account and group membership information exists in dcdirect-users.xml.
 * @author James McNabb
 *//*
public class ACF2User extends mojo.km.security.role.User
{
	private String userID = null;
	private User user = null;

	*//**
	 * After setting both the username and password fields.  This method is called for authentication.
	 * @exception NamingException
	 * @exception AuthenticationException
	 *//*
	public void login() throws NamingException
	{
		lnkRole = mojo.km.security.role.acf2.ACF2Platform.login(this);
	}

	*//**
	 * Return the user ID for a given user
	 * @return String - user id for a given user
	 *//*
	public String getUserID()
	{
		return this.userID;
	}

	*//**
	 * Set the user ID for a given user
	 * @param userID - username for a given user
	 *//*
	public void setUserName(String userID) throws NamingException
	{
		this.userID = userID;
	}

	*//** Clear the user credentails information 
	 *//*
	public void clear()
	{
		this.password = null;
		this.userID = null;
	}

	*//**
	 * Retrieves and stores the user's permissions.
	 *//*
	public IPermissions getPermissions()
	{
		Permissions permissions = new Permissions();
		
		Enumeration roles = lnkRole.getEnumeration();
		while (roles.hasMoreElements()) 
		{
			Role role = (Role)roles.nextElement();
			Iterator permIter = role.getPermissions().iterator();
			while (permIter.hasNext())
			{
				Permission permission = (Permission)permIter.next();
				try
				{
					mojo.km.security.helper.Permission permissionDel = 
								new mojo.km.security.helper.Permission();
					String features = permission.getFeatures().toString();
					permissionDel.setFeatureList(features);
					permissions.put(permission.getName(), permissionDel);
				}
				catch(NamingException ne)
				{
				}
			}
		}
		
		return permissions;
	}
}
*/