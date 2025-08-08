/*
 * Class FileUser.java
 * Created on June 18, 2002, 12:46:56 PM
 */
package mojo.km.security.role.file;

import java.util.Enumeration;

import mojo.km.security.NamingException;
import mojo.km.security.helper.IPermissions;
import mojo.km.security.helper.Permissions;
import mojo.km.security.role.User;


/**
 * Authenticates a user and retrieves all of the groups they have membership
 * with. The user account and groupmembership information exists in dcdirect-users.xml.
 * @author Chad Oliver
 * @modelguid {CB9C7546-5871-4782-BF08-5939C8292C7B}
 */
public class FileUser extends User {
	/** @modelguid {286D8C90-553A-4051-A821-04F247BF3400} */
    private String username = null;

    /**
     * After setting both the username and password fields.  This method is called for authentication.
     * @exception NamingException
     * @exception AuthenticationException
     * @modelguid {952957F7-8A56-4B4E-ABBF-883E69EBD2CB}
     */
    public void login() throws NamingException {
		lnkRole = FileRealm.login(this);
    }

	/** @modelguid {97BD6EEF-DFAE-4299-A5B8-BD8B367D7A81} */
    public String getUserName() {
        return this.username;
    }

    /**
     * Set the username for a given user
     * @param userName - username for a given user
     * @modelguid {70A618DC-C159-4CD3-8C35-730C7D4A1607}
     */
    public void setUserName(String userName) throws NamingException {
        this.username = userName;
    }

    /** Clear the user credentails information 
     * @modelguid {C49394AE-86EF-45FC-AB6C-2DB76C691204}
     */
    public void clear() {
        this.password = null;
        this.username = null;
    }
    
	/**
	 * Retrieves and stores the user's permissions.
	 */
	public IPermissions getPermissions()
	{
		IPermissions permissions = null;
		
		try
		{
			permissions = FileRealm.getACL(lnkRole);
			return permissions;
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Permissions();
	}

	/**
	 * Return the user's full name
	 * @return <{java.lang.String}> - user full name
	 */
    public String getFullName() {
		try
		{
			return FileRealm.getFullName(this.username);
		}
		catch (NamingException e)
		{
			return e.getMessage();
		}
    }
}
