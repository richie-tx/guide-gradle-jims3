/*
 * Class LdapUser.java
 * Created on May 9, 2002, 2:36:44 PM
 */

package mojo.km.security.role.ldap;

import java.util.Enumeration;

import mojo.km.security.NamingException;
import mojo.km.security.helper.IPermissions;
import mojo.km.security.helper.Permissions;
import mojo.km.security.role.User;

/**
 * Authenticates a user and retrieves all of the groups they have membership
 * with. The user account and groupmembership information exists in a LDAP Server.
 * @author Chad Oliver
 * @modelguid {E773B17C-D524-4659-B14B-CB0B7D00904E}
 */
public class LdapUser extends User {
	/** @modelguid {94747C98-5FCA-498C-B487-BCC6F344E683} */
    private IUserContext lnkIUserContext = null;

    /**
     * After setting both the username and password fields.  This method is
     * called for authentication.
     * @exception NamingException
     * @modelguid {BCCBF218-8709-4BFC-A607-280BF7274A34}
     */
    public void login() throws NamingException {
        lnkRole = Ldap.login(this);
    }

    /**
     * Set the username for a given user
     * @param userName - username for a given user
     * @modelguid {A7619B52-CFD6-4571-977A-C252B3BAB893}
     */
    public void setUserName(String userName) throws NamingException {
        if (userName != null) {
            String newUserName = "";

            if (userName.startsWith("cn="))
            {
                newUserName = userName;
            }
            else
            {
                newUserName = "cn=" + userName + ",o=the401k";
            }

            lnkIUserContext = UserContextFactory.getUserContext(newUserName);
        }
        else {
            throw new NamingException();
        }
    }

    /**
     * Retrieves the username which maintains LDAP context information.
     * @return IDistinguishedName
     * @modelguid {AD4FF0FD-ED98-4B78-A517-1BA861984058}
     */
    public IUserContext getUsername() {
        return this.lnkIUserContext;
    }

    /** Clear the user credentails information 
     * @modelguid {29546DE1-2735-497B-898F-FD9DD2EE715F}
     */
    public void clear() {
        this.password = null;
        this.lnkIUserContext = null;
    }

	/**
	 * Retrieves and stores the user's permissions.
	 */
	public IPermissions getPermissions()
	{
		Permissions permissions = new Permissions();
		
		Enumeration roles = lnkRole.getEnumeration();
		while (roles.hasMoreElements()) 
		{
			
		}
		
		return permissions;
	}

}
