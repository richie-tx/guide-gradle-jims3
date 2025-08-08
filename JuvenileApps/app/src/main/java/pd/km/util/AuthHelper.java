/*
 * Created on Feb 4, 2005
 */
package pd.km.util;

import mojo.km.context.ContextManager;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;

/**
 * @author glyons
 */
public final class AuthHelper
{

	private AuthHelper()
	{
	}

	/**
	 * Returns the currently authenticated user for the thread session.
	 * @return userId
	 */
	public static String getCurrentUser()
	{
		ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
		String userId = null;
		if (manager != null)
		{
			IUserInfo user = manager.getIUserInfo();
			userId = user.getJIMSLogonId();
		} else {
			//TODO: Remove this once authentication works and a correctly populated user exists
			userId = "JIDJN";
		}
		return userId;
	}
	/**
	 * 
	 * @return userName
	 */
	public static String getCurrentUserName()
	{
		ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
		IUserInfo user = null;
		String userName = null;
		if (manager != null)
		{
			user = manager.getIUserInfo();
			String lastName = null;
			String firstName = null;
			if (user != null) {
				lastName = user.getLastName();
				firstName = user.getFirstName();
			}
			
			if (lastName != null)
			{
				userName = lastName + ", " + firstName;
			}
		} else {
			//TODO: Remove this once authentication works and a correctly populated user exists
			userName = "Nikolis, Debbie";
		}
		return userName;
	}
}
