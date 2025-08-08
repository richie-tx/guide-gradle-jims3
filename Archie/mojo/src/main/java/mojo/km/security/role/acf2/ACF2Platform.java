package mojo.km.security.role.acf2;// no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.
/*
 * Created on Feb 8, 2005
 
package mojo.km.security.role.acf2;

import java.util.Iterator;
import javax.security.auth.login.LoginException;
import messaging.authentication.UpdatePasswordEvent;
import mojo.km.persistence.Home;
import mojo.km.security.NamingException;
import mojo.km.security.authentication.ExpiredProfileException;
import mojo.km.security.authentication.IncorrectPasswordException;
import mojo.km.security.authentication.IncorrectUserException;
import mojo.km.security.authentication.SuspendedProfileException;
import mojo.km.security.role.IRoles;
import mojo.km.security.role.Roles;
import com.ibm.os390.security.PlatformReturned;
import com.ibm.os390.security.PlatformUser;

*//**
 * Handles user authentication and role lookup for ACF2 platform.
 * 
 * @author jmcnabb
 *//*
public class ACF2Platform {
	public static final int USER_AUTHENTICATED = 999;
	public static final int PASSWORD_EXPIRED = 168;
	public static final int UNKNOWN_USER = 143;
	public static final int INVALID_PASSWORD = 111;
	public static final int LOGIN_ATTEMPT_LIMIT_PASSED = 163;

	public static IRoles login(ACF2User acfUser) throws NamingException {
		if (acfUser == null) {
			throw new NamingException("Username was not provided");
		}

		try {
			if (authenticateUser(acfUser.getUserID(), acfUser.getPassword())) {
				return ACF2Platform.getRoles(acfUser.getUserID());
			}
		}
		catch (LoginException le) {
			throw new NamingException(le.getMessage());
		}
		return getRoles(acfUser.getUserID());
	}

	public static boolean authenticateUser(String userID, String password) throws LoginException {
		PlatformReturned pr = null;
		try {
			System.out.println("inside authenticateUser" + userID);

			pr = PlatformUser.authenticate(userID, password);
		}
		catch (Exception e) {
			System.out.println(userID + " Throwing Login Exception - ACF2Platform");
			throw new LoginException(e.getMessage());
		}
		if (pr != null) {
			System.out.println(userID + " - ACF2Platform errno     = " + pr.errno);
			System.out.println(userID + " - ACF2Platform errno2    = " + pr.errno2);
			System.out.println(userID + " - ACF2Platform errnoMsg  = " + pr.errnoMsg);
			System.out.println(userID + " - ACF2Platform stringRet = " + pr.stringRet);
			if (pr.errno == UNKNOWN_USER) {
				System.out.println(userID + " Throwing Incorrect User Exception - ACF2Platform");
				throw new IncorrectUserException("User Profile is suspended or invalid. Please contact the ITC Help Desk at 713-755-6624 or contact your department's security administrator: ");
			}
			else if (pr.errno == INVALID_PASSWORD) {
				System.out.println(userID + " Throwing Incorrect Password Attempt Exception - ACF2Platform");
				throw new IncorrectPasswordException("Incorrect password supplied.  Supply the correct password or your account will be suspended.");
			}
			else if (pr.errno == PASSWORD_EXPIRED) {
				System.out.println(userID + " Throwing Profile Expired Exception - ACF2Platform");
				throw new ExpiredProfileException("Password has expired. Please contact the ITC Help Desk at 713-755-6624 or contact your department's security administrator: ");
			}
			else if (pr.errno == LOGIN_ATTEMPT_LIMIT_PASSED) {
				System.out.println(userID + " Throwing Profile Suspended Exception - ACF2Platform");
				throw new SuspendedProfileException("User ID has been suspended for password violations. Please contact the ITC Help Desk at 713-755-6624 or contact your department's security administrator: ");
			}
			return true;
		}
		return false;

	}

	public static Roles getRoles(String userID) throws NamingException {
		Roles newRoles = new Roles();

		Iterator roles = new Home().findAll("userID", userID, Role.class);
		while (roles.hasNext()) {
			Role role = (Role) roles.next();
			newRoles.add(role.getRoleName());
		}

		return newRoles;
	}

	*//**
	 * Method updatePassword.
	 * @param aPassword
	 * @param aNewPassword
	 * @return boolean
	 * @throws LoginException
	 * @throws IncorrectPasswordException
	 * This method updates a users password in ACF2
	 *//*
	public static boolean updatePassword(UpdatePasswordEvent anEvent)
		throws LoginException, IncorrectPasswordException {
		PlatformReturned pr =
			PlatformUser.changePassword(anEvent.getUserID(), anEvent.getPassword(), anEvent.getNewPassword());
		if (pr != null) {
			System.out.println("errno     = " + pr.errno);
			System.out.println("errno2    = " + pr.errno2);
			System.out.println("errnoMsg  = " + pr.errnoMsg);
			System.out.println("objectRet = " + pr.objectRet);
			System.out.println("stringRet = " + pr.stringRet);
			System.out.println("success   = " + pr.success);
			System.out.println(anEvent.getUserID() + " Throwing Incorrect Password Exception");
			throw new IncorrectPasswordException("Incorrect password");
		}
		else {
			System.out.println(anEvent.getUserID() + " Setting Profile to Active");
		}
		return true;
	}

}
*/