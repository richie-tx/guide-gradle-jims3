/*
 * Class IUser.java
 * Created on May 21, 2002, 8:14:54 AM
 */

package mojo.km.security.role;

import mojo.km.security.NamingException;
import mojo.km.security.helper.IPermissions;

/**
 * Authenticates a user and retrieves all of the Roles they have membership
 * with.
 * @author Chad Oliver
 * @modelguid {98C70989-44B9-430F-A552-3AEEFEA57119}
 */
public interface IUser {
	/** @modelguid {D9E8D69E-D598-4F7F-8FAA-99A019988DB1} */
    public void login() throws NamingException, AuthenticationException;

	/** @modelguid {219352DA-5F2D-4552-8F87-B8A9A73D73CA} */
    public void setUserName(String userName) throws NamingException;

	/** @modelguid {6DC41BCC-E587-45B2-9769-023B3774B8CA} */
    public void setPassword(String password);

	/** @modelguid {20065795-EF83-4CF7-908D-1DB005E56AE5} */
    public void setPassword(char[] password);

    /**
     * Retrieves a collection of groups the given user has membership with.
     * Returns null when no memberships are found.
     * @modelguid {CF98F8DD-7BB0-465D-B0CA-7B67404DE574}
     */
    public IRoles getRole();

	/**
	 * Retrieves and stores the user's permissions.
	 */
	public IPermissions getPermissions();

	/** @modelguid {BC11F868-2788-4C88-AAE9-F9E1A0214C4F} */
    public void clear();
}
