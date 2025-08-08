package mojo.km.security;/*
//U.S #79250
 * Created on Apr 29, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 
package mojo.km.security;

import mojo.km.exceptionhandling.ExceptionHandler;
import mojo.km.security.authentication.ExpiredProfileException;
import mojo.km.security.authentication.IncorrectPasswordException;
import mojo.km.security.authentication.IncorrectUserException;
import mojo.km.security.authentication.SuspendedProfileException;
import mojo.km.security.role.acf2.*;
import mojo.naming.CommonConstants;

import java.util.*;

import javax.security.auth.login.LoginException;

*//**
 * @author fsjodin
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 *//*

*//**
 * 
 * @author rdassharma
 *
 * REFACTORED 10/18/2006
 * THIS CLASS SHOULD ONLY CONTAIN CODE TO AUTHENTICATE AGAINST ACF2 AND NOTHING ELSE
 *//*
public class RACFAuthenticator implements IAuthenticator
{

	 (non-Javadoc)
	 * @see mojo.km.security.IAuthenticator#authenticate(java.lang.String, java.lang.String)
	 
	public String authenticate(String userID, String password) throws AuthenticationFailedException
	{
		StringBuffer message = new StringBuffer();
		try
		{
			if (userID == null || password == null)
			{
				throw new AuthenticationFailedException("RACF authentication failed - User with user login name " + userID + " could not be located");
			}
			boolean isInError = ACF2Platform.authenticateUser(userID, password);
			if (isInError)
			{
				System.out.println(userID + " Throwing Unknown Login Exception - RACFAuthenticator");
				throw new AuthenticationFailedException(CommonConstants.UNKNOWN_LOGIN_ERROR,
					"Could not authenticate UserID. Please contact the ITC Help Desk at 713-755-6624 or contact your department's security administrator: "
						+ message.toString());
			}
		}
		catch (IncorrectUserException iue)
		{
			System.out.println(userID + " Throwing Incorrect User Exception - RACFAuthenticator");
			String errorMessage = iue.getMessage() + message.toString();
			ExceptionHandler.executeCallbacks(new AuthenticationFailedException(CommonConstants.INCORRECT_USER_ERROR,errorMessage));
			throw new AuthenticationFailedException(CommonConstants.INCORRECT_USER_ERROR,errorMessage);
		}
		catch (IncorrectPasswordException ipe)
		{
			System.out.println(userID + " Throwing Incorrect Password Attempt Exception - RACFAuthenticator" + ipe.getMessage());
			ExceptionHandler.executeCallbacks(new AuthenticationFailedException(CommonConstants.INCORRECT_PASSWORD_ERROR, ipe.getMessage()));
		//	ipe.printStackTrace();
			throw new AuthenticationFailedException(CommonConstants.INCORRECT_PASSWORD_ERROR, ipe.getMessage());
		}
		catch (ExpiredProfileException epe)
		{   String errorMessage = epe.getMessage() + message.toString(); 
		ExceptionHandler.executeCallbacks(new AuthenticationFailedException(CommonConstants.PROFILE_EXPIRED_ERROR,errorMessage));
		//epe.printStackTrace();
		System.out.println(userID + " Throwing Expired Profile Exception - RACFAuthenticator" + message.toString());
			throw new AuthenticationFailedException(CommonConstants.PROFILE_EXPIRED_ERROR,errorMessage);
		}
		catch (SuspendedProfileException spe)
		{String errorMessage = spe.getMessage() + message.toString();
			System.out.println(userID + " Throwing Suspended Profile Exception - RACFAuthenticator" + message.toString());
			ExceptionHandler.executeCallbacks(new AuthenticationFailedException(CommonConstants.PROFILE_SUSPENDED_ERROR,errorMessage));
		//	spe.printStackTrace();
			throw new AuthenticationFailedException(CommonConstants.PROFILE_SUSPENDED_ERROR,errorMessage);
		}
		catch (LoginException spe)
		{String errorMessage = spe.getMessage() + message.toString();
			System.out.println(userID + " Throwing Login Exception - RACFAuthenticator" + message.toString());
			ExceptionHandler.executeCallbacks(new AuthenticationFailedException(CommonConstants.UNKNOWN_LOGIN_ERROR, errorMessage));
		//	spe.printStackTrace();
			throw new AuthenticationFailedException(CommonConstants.UNKNOWN_LOGIN_ERROR, errorMessage);
		}
		return null;
	}
}
*/